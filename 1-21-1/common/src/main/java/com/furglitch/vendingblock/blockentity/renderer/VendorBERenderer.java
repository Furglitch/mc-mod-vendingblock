package com.furglitch.vendingblock.blockentity.renderer;

import com.furglitch.vendingblock.blockentity.renderer.VendorBlockProductRenderer;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemStack;

public class VendorBERenderer implements BlockEntityRenderer<VendorBE> {

    private final VendorBlockProductRenderer productRenderer;

    public VendorBERenderer(BlockEntityRendererProvider.Context context) {
        this.productRenderer = new VendorBlockProductRenderer();
    }

    @Override
    public void render(VendorBE entity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        // Product slot is at index 10 (slot layout: 0-8 stock, 9 price, 10 product)
        ItemStack product = entity.getItem(10);

        if (!product.isEmpty()) {
            productRenderer.renderItem(product, poseStack, bufferSource, entity.getLevel(), entity.getBlockPos());
        }
    }
}