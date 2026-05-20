package com.furglitch.vendingblock.registry;

import com.furglitch.vendingblock.VendingBlock;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TabRegistry {

    private static final DeferredRegister<CreativeModeTab> TABS =
        DeferredRegister.create(VendingBlock.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static RegistrySupplier<CreativeModeTab> VENDOR_TAB;

    public static void init() {
        VENDOR_TAB = TABS.register("vendor_tab",
            () -> CreativeTabRegistry.create(Component.translatable("creative.vendingblock.tab"),
                () -> new ItemStack(ItemRegistry.VENDING_BLOCK)
            )
        );

        TABS.register();
    }

}
