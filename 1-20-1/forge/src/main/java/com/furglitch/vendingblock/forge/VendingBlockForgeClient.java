package com.furglitch.vendingblock.forge;

import com.furglitch.vendingblock.blockentity.VendorBE;
import com.furglitch.vendingblock.blockentity.renderer.VendorBERenderer;
import com.furglitch.vendingblock.client.screen.VendorScreen;
import com.furglitch.vendingblock.config.ConfigScreen;
import com.furglitch.vendingblock.registry.BlockEntityRegistry;
import com.furglitch.vendingblock.registry.BlockRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class VendingBlockForgeClient {
    public static void init() {
        ModLoadingContext.get().registerExtensionPoint(
            ConfigScreenHandler.ConfigScreenFactory.class,
            () -> new ConfigScreenHandler.ConfigScreenFactory((mc, screen) -> ConfigScreen.create(screen))
        );
        FMLJavaModLoadingContext.get().getModEventBus().addListener(VendingBlockForgeClient::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(VendingBlockForgeClient::registerRenderers);
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.VENDING_BLOCK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.DISPLAY_BLOCK.get(), RenderType.cutout());
            MenuRegistry.registerScreenFactory(
                com.furglitch.vendingblock.registry.MenuRegistry.VENDOR_MENU.get(),
                VendorScreen::new
            );
        });
    }

    @SuppressWarnings("unchecked")
    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
            (BlockEntityType<VendorBE>) (BlockEntityType<?>) BlockEntityRegistry.VENDOR_BE.get(),
            VendorBERenderer::new
        );
    }
}
