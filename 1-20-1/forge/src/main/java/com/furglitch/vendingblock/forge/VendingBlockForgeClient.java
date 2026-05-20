package com.furglitch.vendingblock.forge;

import com.furglitch.vendingblock.config.ConfigScreen;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;

public class VendingBlockForgeClient {
    public static void init() {
        ModLoadingContext.get().registerExtensionPoint(
            ConfigScreenHandler.ConfigScreenFactory.class,
            () -> new ConfigScreenHandler.ConfigScreenFactory((mc, screen) -> ConfigScreen.create(screen))
        );
    }
}
