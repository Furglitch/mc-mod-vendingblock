package com.furglitch.vendingblock.menu;

import org.jetbrains.annotations.Nullable;

import com.furglitch.vendingblock.blockentity.VendorBE;
import com.furglitch.vendingblock.registry.MenuRegistry;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class VendorMenu extends AbstractContainerMenu {

    private final Container container;
    @Nullable public final VendorBE blockEntity;

    public static final int VENDOR_SLOTS   = 9;
    public static final int PRICE_SLOT     = 1;
    public static final int PAYMENT_SLOT   = 1;
    public static final int CONTAINER_SIZE = VENDOR_SLOTS + PRICE_SLOT + PAYMENT_SLOT;

    // CLIENT
    public VendorMenu(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(CONTAINER_SIZE));
    }

    // SERVER
    public VendorMenu(int syncId, Inventory playerInventory, VendorBE blockEntity) {
        this(syncId, playerInventory, (Container) blockEntity);
    }

    private VendorMenu(int syncId, Inventory playerInventory, Container container) {
        super(MenuRegistry.VENDOR_MENU.get(), syncId);
        this.container = container;
        this.blockEntity = container instanceof VendorBE be ? be : null;
        checkContainerSize(container, CONTAINER_SIZE);

        // Vendor stock slots (3x3)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                addSlot(new Slot(container, row * 3 + col, 44 + col * 18, 17 + row * 18));
            }
        }

        // Price slot
        addSlot(new Slot(container, 9, 116, 17));

        // Payment slot
        addSlot(new Slot(container, 10, 116, 53));

        // Player inventory (27 slots)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        // Player hotbar (9 slots)
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        ItemStack returnStack = ItemStack.EMPTY;
        final Slot slot = this.slots.get(slotIndex);
        if (slot.hasItem()) {
            final ItemStack slotStack = slot.getItem();
            returnStack = slotStack.copy();

            final int playerInventoryEnd = CONTAINER_SIZE + 27;
            final int playerHotbarEnd    = playerInventoryEnd + 9;

            if (slotIndex < CONTAINER_SIZE) {
                if (!moveItemStackTo(slotStack, CONTAINER_SIZE, playerHotbarEnd, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotIndex < playerInventoryEnd) {
                if (!moveItemStackTo(slotStack, 0, CONTAINER_SIZE, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotIndex < playerHotbarEnd) {
                if (!moveItemStackTo(slotStack, CONTAINER_SIZE, playerInventoryEnd, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (slotStack.getCount() == returnStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, slotStack);
        }
        return returnStack;
    }
}
