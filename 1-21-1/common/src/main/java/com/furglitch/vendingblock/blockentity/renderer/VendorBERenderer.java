package com.furglitch.vendingblock.blockentity.renderer;

import com.furglitch.vendingblock.blockentity.VendorBE;
import com.furglitch.vendingblock.blockentity.renderer.VendorBEProductRenderer;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemStack;

public class VendorBERenderer implements BlockEntityRenderer<VendorBE> {

    private final VendorBEProductRenderer productRenderer;

    public VendorBERenderer(BlockEntityRendererProvider.Context context) {
        this.productRenderer = new VendorBEProductRenderer();
    }

    @Override
    public void render(VendorBE entity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ItemStack product = entity.getItem(10);

        if (!product.isEmpty()) {
            productRenderer.renderItem(product, poseStack, bufferSource, entity.getLevel(), entity.getBlockPos());
        }
    }
}