package com.furglitch.vendingblock.registry;

import java.util.function.Supplier;

import com.furglitch.vendingblock.VendingBlock;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

public class ItemRegistry {

    private static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(VendingBlock.MOD_ID, Registries.ITEM);

    public static RegistrySupplier<Item> VENDING_BLOCK;
    public static RegistrySupplier<Item> DISPLAY_BLOCK;

    public static void init() {
        VENDING_BLOCK = registerItem("vending_block", () -> new BlockItem(BlockRegistry.VENDING_BLOCK.get(), baseProperties("vending_block")
            .arch$tab(TabRegistry.VENDOR_TAB)
        ));

        DISPLAY_BLOCK = registerItem("display_block", () -> new BlockItem(BlockRegistry.DISPLAY_BLOCK.get(), baseProperties("display_block")
            .arch$tab(TabRegistry.VENDOR_TAB)
        ));

        ITEMS.register();
    }

    public static RegistrySupplier<Item> registerItem(String name, Supplier<Item> item){
        return ITEMS.register(new ResourceLocation(VendingBlock.MOD_ID, name), item);
    }

    public static Item.Properties baseProperties(String name){
        return new Item.Properties();
    }

}
