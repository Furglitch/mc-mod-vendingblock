package com.furglitch.vendingblock.registry;

import java.util.function.Supplier;

import com.furglitch.vendingblock.VendingBlock;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

public class ItemRegistry {

    private static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(VendingBlock.MOD_ID, Registries.ITEM);

    // private static RegistrySupplier<Item> EXAMPLE_ITEM;
    // private static RegistrySupplier<Item> EXAMPLE_BLOCK_ITEM;

    public static void init() {
        // EXAMPLE_ITEM = registerItem("example_item", () -> new Item(baseProperties("example_item")
        //     .arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
        // ));

        // EXAMPLE_BLOCK_ITEM = registerItem("example_block", () -> new BlockItem(BlockRegistry.EXAMPLE_BLOCK.get(), baseProperties("example_block")
        //     .arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
        // ));

        ITEMS.register();
    }

    public static RegistrySupplier<Item> registerItem(String name, Supplier<Item> item){
        return ITEMS.register(ResourceLocation.fromNamespaceAndPath(VendingBlock.MOD_ID, name), item);
    }

    public static Item.Properties baseProperties(String name){
        return new Item.Properties();
    }

}
