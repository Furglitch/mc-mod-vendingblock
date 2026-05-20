package com.furglitch.vendingblock.neoforge;

import com.furglitch.vendingblock.config.ConfigScreen;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public class VendingBlockNeoForgeClient {
    public static void init() {
        ModLoadingContext.get().registerExtensionPoint(
            IConfigScreenFactory.class,
            () -> (container, screen) -> ConfigScreen.create(screen)
        );
    }
}
