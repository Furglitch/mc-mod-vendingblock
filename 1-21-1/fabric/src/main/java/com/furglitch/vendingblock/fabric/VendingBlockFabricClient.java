package com.furglitch.vendingblock.fabric;

import com.furglitch.vendingblock.blockentity.VendorBE;
import com.furglitch.vendingblock.blockentity.renderer.VendorBERenderer;
import com.furglitch.vendingblock.client.screen.VendorScreen;
import com.furglitch.vendingblock.registry.BlockEntityRegistry;
import com.furglitch.vendingblock.registry.BlockRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;

import dev.architectury.registry.menu.MenuRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;

public final class VendingBlockFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.VENDING_BLOCK.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.DISPLAY_BLOCK.get(), RenderType.cutout());
        MenuRegistry.registerScreenFactory(
            com.furglitch.vendingblock.registry.MenuRegistry.VENDOR_MENU.get(),
            VendorScreen::new
        );

        BlockEntityRendererRegistry.register(
            (BlockEntityType<VendorBE>) (BlockEntityType<?>) BlockEntityRegistry.VENDOR_BE.get(),
            VendorBERenderer::new
        );
    }
}
