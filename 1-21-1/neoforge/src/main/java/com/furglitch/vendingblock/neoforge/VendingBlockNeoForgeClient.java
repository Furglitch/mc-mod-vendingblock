package com.furglitch.vendingblock.neoforge;

import com.furglitch.vendingblock.blockentity.VendorBE;
import com.furglitch.vendingblock.blockentity.renderer.VendorBERenderer;
import com.furglitch.vendingblock.client.screen.VendorScreen;
import com.furglitch.vendingblock.config.ConfigScreen;
import com.furglitch.vendingblock.registry.BlockEntityRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public class VendingBlockNeoForgeClient {
    public static void init(IEventBus modEventBus) {
        ModLoadingContext.get().registerExtensionPoint(
            IConfigScreenFactory.class,
            () -> (container, screen) -> ConfigScreen.create(screen)
        );
        modEventBus.addListener(VendingBlockNeoForgeClient::registerMenuScreens);
        modEventBus.addListener(VendingBlockNeoForgeClient::registerRenderers);
    }

    private static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(
            com.furglitch.vendingblock.registry.MenuRegistry.VENDOR_MENU.get(),
            VendorScreen::new
        );
    }

    @SuppressWarnings("unchecked")
    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
            (BlockEntityType<VendorBE>) (BlockEntityType<?>) BlockEntityRegistry.VENDOR_BE.get(),
            VendorBERenderer::new
        );
    }
}
