package com.furglitch.vendingblock.client.screen;

import com.furglitch.vendingblock.VendingBlock;
import com.furglitch.vendingblock.menu.VendorMenu;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class VendorScreen extends AbstractContainerScreen<VendorMenu> {

    private static final ResourceLocation TEXTURE =
        ResourceLocation.fromNamespaceAndPath(VendingBlock.MOD_ID, "textures/gui/container/vendor.png");

    private static final int TAB_X = 176;
    private static final int TAB_Y = 7;
    private static final int TAB_W = 20;
    private static final int TAB_H = 22;

    private static final int ADMIN_PANEL_W = 83;
    private static final int ADMIN_PANEL_H = 152;

    private boolean showAdmin = false;

    public VendorScreen(VendorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth  = 176;
        this.imageHeight = 166;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (this.width  - this.imageWidth)  / 2;
        int y = (this.height - this.imageHeight) / 2;

        // Main trade panel
        graphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight, 384, 256);

        // Admin panel
        if (!showAdmin) {
            graphics.blit(TEXTURE, x + TAB_X, y + TAB_Y, 0, 224, TAB_W, TAB_H, 384, 256);
        } else if (showAdmin) {
            graphics.blit(TEXTURE, x + this.imageWidth, y + TAB_Y, TAB_X, 0, ADMIN_PANEL_W, ADMIN_PANEL_H, 384, 256);
        }

        // Tab icon (always visible)
        int iconU = isMouseOverTab(mouseX, mouseY) ? 16 : 0;
        graphics.blit(TEXTURE, x + TAB_X, y + TAB_Y + 3, iconU, 192, 16, 16, 384, 256);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        this.renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && isMouseOverTab((int) mouseX, (int) mouseY)) {
            showAdmin = !showAdmin;
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean isMouseOverTab(int mouseX, int mouseY) {
        int x = (this.width  - this.imageWidth)  / 2;
        int y = (this.height - this.imageHeight) / 2;
        int tabScreenX = x + TAB_X;
        int tabScreenY = y + TAB_Y;
        return mouseX >= tabScreenX && mouseX < tabScreenX + TAB_W
            && mouseY >= tabScreenY && mouseY < tabScreenY + TAB_H;
    }
}
