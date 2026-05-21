package com.furglitch.vendingblock.neoforge;

import com.furglitch.vendingblock.client.screen.VendorScreen;
import com.furglitch.vendingblock.config.ConfigScreen;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public class VendingBlockNeoForgeClient {
    public static void init(IEventBus modEventBus) {
        ModLoadingContext.get().registerExtensionPoint(
            IConfigScreenFactory.class,
            () -> (container, screen) -> ConfigScreen.create(screen)
        );
        modEventBus.addListener(VendingBlockNeoForgeClient::registerMenuScreens);
    }

    private static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(
            com.furglitch.vendingblock.registry.MenuRegistry.VENDOR_MENU.get(),
            VendorScreen::new
        );
    }
}
