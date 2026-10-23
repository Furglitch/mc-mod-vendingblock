package com.furglitch.vendingblock.blockentity.renderer;

import com.furglitch.vendingblock.VendingBlock;
import com.furglitch.vendingblock.config.ConfigClient;
import com.furglitch.vendingblock.config.ConfigServer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class VendorBlockProductRenderer {

    private float rotation;
    private long lastTime = System.currentTimeMillis();

    public void renderItem(ItemStack item, PoseStack poseStack, MultiBufferSource bufferSource, Level level, BlockPos pos) {
        if (item.isEmpty()) return;

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        poseStack.pushPose();
        poseStack.translate(0.5f, 0.5f + getBobbingOffset(), 0.5f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(getRotation(level, pos)));

        int lightLevel = getLightLevel(level, pos);
        itemRenderer.renderStatic(item, ItemDisplayContext.FIXED, lightLevel, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, level, 1);

        poseStack.popPose();
    }

    private float getRotation(Level level, BlockPos pos) {
        ConfigClient.Animation.ItemMode clientMode = VendingBlock.getConfig().animation.getItemMode();
        ConfigServer.Animation.ItemMode serverMode = VendingBlock.getServerConfig().animation.getItemMode();

        boolean spinning = clientMode == ConfigClient.Animation.ItemMode.SPINNING
            || (clientMode == ConfigClient.Animation.ItemMode.SERVER && serverMode == ConfigServer.Animation.ItemMode.SPINNING);
        boolean facingPlayer = clientMode == ConfigClient.Animation.ItemMode.FACING_PLAYER
            || (clientMode == ConfigClient.Animation.ItemMode.SERVER && serverMode == ConfigServer.Animation.ItemMode.FACING_PLAYER);

        if (spinning || (level == null || level.getNearestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 8, false) == null)) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastTime;
            lastTime = currentTime;

            rotation += (elapsedTime / 16.0f);
            if (rotation >= 360.0f) rotation -= 360.0f;
            return rotation;
        } else if (facingPlayer) {
            double blockX = pos.getX() + 0.5;
            double blockZ = pos.getZ() + 0.5;
            double playerX = level.getNearestPlayer(blockX, pos.getY() + 0.5, blockZ, 10, false).getX();
            double playerZ = level.getNearestPlayer(blockX, pos.getY() + 0.5, blockZ, 10, false).getZ();

            double angle = Math.atan2(playerZ - blockZ, playerX - blockX);
            return -(float) Math.toDegrees(angle) - 90.0f;
        } else {
            return 0.0f;
        }
    }

    private float getBobbingOffset() {
        ConfigClient.Animation.ItemMode clientMode = VendingBlock.getConfig().animation.getItemMode();
        ConfigServer.Animation.ItemMode serverMode = VendingBlock.getServerConfig().animation.getItemMode();

        boolean bobbing = clientMode == ConfigClient.Animation.ItemMode.BOBBING
            || (clientMode == ConfigClient.Animation.ItemMode.SERVER && serverMode == ConfigServer.Animation.ItemMode.BOBBING);
        boolean spinning = clientMode == ConfigClient.Animation.ItemMode.SPINNING
            || (clientMode == ConfigClient.Animation.ItemMode.SERVER && serverMode == ConfigServer.Animation.ItemMode.SPINNING);

        if (bobbing || spinning) {
            long currentTime = System.currentTimeMillis();
            return (float) Math.sin(currentTime / 500.0) * 0.1f;
        }
        return 0.0f;
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}