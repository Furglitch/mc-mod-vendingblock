package com.furglitch.vendingblock.blockentity.transaction;

import com.furglitch.vendingblock.blockentity.VendorBE;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class VendorBETransaction {

    public static void purchase(Level level, Player buyer, VendorBE vendor) {
        ItemStack product = vendor.getItem(10);
        ItemStack price = vendor.getItem(9);
        if (product.isEmpty() && price.isEmpty()) return;

        boolean playerHasPayment = checkInventory(buyer, price);
        boolean playerHasSpace = checkInventorySpace(buyer, price, product);
        boolean blockHasStock = checkStock(vendor, product);
        boolean blockHasSpace = checkStockSpace(vendor, product, price);

        if (playerHasPayment && playerHasSpace && blockHasStock && blockHasSpace && !product.isEmpty() && !price.isEmpty()) {
            // Purchase: player pays price, receives product
            giveProduct(buyer, vendor, product);
            receivePayment(buyer, vendor, price);
            syncVendor(vendor);
            buyer.sendSystemMessage(Component.translatable("msg.vendingblock.sell", product.getCount(), product.getHoverName(), price.getCount(), price.getHoverName()));

        } else if (playerHasSpace && blockHasStock && !product.isEmpty() && price.isEmpty()) {
            // Giveaway: product is free
            giveProduct(buyer, vendor, product);
            syncVendor(vendor);
            buyer.sendSystemMessage(Component.translatable("msg.vendingblock.giveaway", product.getCount(), product.getHoverName()));

        } else if (playerHasPayment && blockHasSpace && product.isEmpty() && !price.isEmpty()) {
            // Donation: player sends price, receives nothing
            receivePayment(buyer, vendor, price);
            syncVendor(vendor);
            buyer.sendSystemMessage(Component.translatable("msg.vendingblock.request", price.getCount(), price.getHoverName()));

        } else if (!playerHasPayment) {
            buyer.sendSystemMessage(Component.translatable("msg.vendingblock.empty.player", price.getHoverName()));

        } else if (!playerHasSpace) {
            buyer.sendSystemMessage(Component.translatable("msg.vendingblock.full.player"));

        } else if (!blockHasStock) {
            buyer.sendSystemMessage(Component.translatable("msg.vendingblock.sold"));

        } else if (!blockHasSpace) {
            buyer.sendSystemMessage(Component.translatable("msg.vendingblock.full"));
        }
    }

    public static boolean checkInventory(Player buyer, ItemStack price) {
        if (price.isEmpty()) return true;

        int count = 0;
        for (int i = 0; i < buyer.getInventory().getContainerSize(); i++) {
            ItemStack slot = buyer.getInventory().getItem(i);
            if (!slot.isEmpty() && ItemStack.isSameItemSameComponents(slot, price)) {
                count += slot.getCount();
            }
        }
        return count >= price.getCount();
    }

    public static boolean checkInventorySpace(Player buyer, ItemStack price, ItemStack product) {
        ItemStack[] fakeInv = new ItemStack[36];
        for (int i = 0; i < 36; i++) {
            fakeInv[i] = buyer.getInventory().getItem(i).copy();
        }

        // Simulate removing price from player inventory
        int priceCount = price.getCount();
        for (int i = 0; i < 36 && priceCount > 0; i++) {
            ItemStack slot = fakeInv[i];
            if (!slot.isEmpty() && ItemStack.isSameItemSameComponents(slot, price)) {
                int available = slot.getCount();
                int toRemove = Math.min(available, priceCount);
                slot.shrink(toRemove);
                priceCount -= toRemove;
            }
        }

        // Simulate adding product to player inventory
        int remaining = product.getCount();
        for (int i = 0; i < 36 && remaining > 0; i++) {
            ItemStack slot = fakeInv[i];
            if (slot.isEmpty()) {
                int canFit = Math.min(remaining, product.getMaxStackSize());
                remaining -= canFit;
            } else if (ItemStack.isSameItemSameComponents(slot, product)) {
                int availableSpace = slot.getMaxStackSize() - slot.getCount();
                int canFit = Math.min(remaining, availableSpace);
                remaining -= canFit;
            }
        }

        return remaining <= 0;
    }

    public static boolean checkStock(VendorBE vendor, ItemStack product) {
        if (product.isEmpty()) return true;

        int stock = 0;
        for (int i = 0; i <= 8; i++) {
            ItemStack slot = vendor.getItem(i);
            if (!slot.isEmpty() && ItemStack.isSameItemSameComponents(slot, product)) {
                stock += slot.getCount();
            }
        }
        return stock >= product.getCount();
    }

    public static boolean checkStockSpace(VendorBE vendor, ItemStack product, ItemStack price) {
        if (price.isEmpty()) return true;

        ItemStack[] fakeInv = new ItemStack[9];
        for (int i = 0; i < 9; i++) {
            fakeInv[i] = vendor.getItem(i).copy();
        }

        // Simulate removing product from vendor stock
        int productCount = product.getCount();
        for (int i = 0; i < 9 && productCount > 0; i++) {
            ItemStack slot = fakeInv[i];
            if (slot.isEmpty()) continue;
            if (ItemStack.isSameItemSameComponents(slot, product)) {
                int available = slot.getCount();
                int toRemove = Math.min(available, productCount);
                slot.shrink(toRemove);
                productCount -= toRemove;
            }
        }

        // Simulate adding price to vendor stock
        int remaining = price.getCount();
        for (int i = 0; i < 9 && remaining > 0; i++) {
            ItemStack slot = fakeInv[i];
            if (slot.isEmpty()) {
                int canFit = Math.min(remaining, price.getMaxStackSize());
                remaining -= canFit;
            } else if (ItemStack.isSameItemSameComponents(slot, price)) {
                int availableSpace = slot.getMaxStackSize() - slot.getCount();
                int canFit = Math.min(remaining, availableSpace);
                remaining -= canFit;
            }
        }

        return remaining <= 0;
    }

    private static void receivePayment(Player buyer, VendorBE vendor, ItemStack price) {
        int cost = price.getCount();
        // Remove price from player inventory
        for (int i = 0; i < buyer.getInventory().getContainerSize() && cost > 0; i++) {
            ItemStack slot = buyer.getInventory().getItem(i);
            if (!slot.isEmpty() && ItemStack.isSameItemSameComponents(slot, price)) {
                int toRemove = Math.min(slot.getCount(), cost);
                slot.shrink(toRemove);
                cost -= toRemove;
            }
        }

        // Add price to vendor stock slots (0-8)
        int transfer = price.getCount() - cost;
        for (int i = 0; i <= 8 && transfer > 0; i++) {
            ItemStack slot = vendor.getItem(i);
            if (slot.isEmpty()) {
                int space = Math.min(transfer, price.getMaxStackSize());
                ItemStack insert = price.copy();
                insert.setCount(space);
                vendor.setItem(i, insert);
                transfer -= space;
            } else if (ItemStack.isSameItemSameComponents(slot, price)) {
                int space = slot.getMaxStackSize() - slot.getCount();
                int freeSpace = Math.min(space, transfer);
                if (freeSpace > 0) {
                    slot.grow(freeSpace);
                    transfer -= freeSpace;
                }
            }
        }
    }

    private static void giveProduct(Player buyer, VendorBE vendor, ItemStack product) {
        int stock = product.getCount();
        // Remove product from vendor stock slots (0-8)
        for (int i = 0; i <= 8 && stock > 0; i++) {
            ItemStack slot = vendor.getItem(i);
            if (slot.isEmpty()) continue;
            if (ItemStack.isSameItemSameComponents(slot, product)) {
                int available = slot.getCount();
                int slotStock = Math.min(available, stock);
                slot.shrink(slotStock);
                stock -= slotStock;
            }
        }

        // Add product to player inventory
        int transfer = product.getCount() - stock;
        for (int i = 0; i < 36 && transfer > 0; i++) {
            ItemStack slot = buyer.getInventory().getItem(i);
            if (slot.isEmpty()) {
                int space = Math.min(transfer, product.getMaxStackSize());
                ItemStack insert = product.copy();
                insert.setCount(space);
                buyer.getInventory().setItem(i, insert);
                transfer -= space;
            } else if (ItemStack.isSameItemSameComponents(slot, product)) {
                int space = slot.getMaxStackSize() - slot.getCount();
                int freeSpace = Math.min(space, transfer);
                if (freeSpace > 0) {
                    slot.grow(freeSpace);
                    transfer -= freeSpace;
                }
            }
        }
    }

    private static void syncVendor(VendorBE vendor) {
        vendor.setChanged();
        if (vendor.getLevel() != null && !vendor.getLevel().isClientSide()) {
            vendor.getLevel().sendBlockUpdated(vendor.getBlockPos(), vendor.getBlockState(), vendor.getBlockState(), 3);
        }
    }
}