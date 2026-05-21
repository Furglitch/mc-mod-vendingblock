package com.furglitch.vendingblock.registry;

import com.furglitch.vendingblock.VendingBlock;
import com.furglitch.vendingblock.menu.VendorMenu;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;

public class MenuRegistry {

    private static final DeferredRegister<MenuType<?>> MENUS =
        DeferredRegister.create(VendingBlock.MOD_ID, Registries.MENU);

    public static RegistrySupplier<MenuType<VendorMenu>> VENDOR_MENU;

    @SuppressWarnings({"unchecked", "unused"})
    public static void init() {
        VENDOR_MENU = (RegistrySupplier<MenuType<VendorMenu>>) (RegistrySupplier<?>)
            MENUS.register(
                new ResourceLocation(VendingBlock.MOD_ID, "vendor_menu"),
                () -> dev.architectury.registry.menu.MenuRegistry.of(VendorMenu::new)
            );
        MENUS.register();
    }

}
