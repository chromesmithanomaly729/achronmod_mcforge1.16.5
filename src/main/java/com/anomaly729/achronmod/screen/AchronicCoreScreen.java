package com.anomaly729.achronmod.screen;

import com.anomaly729.achronmod.AchronMod;
import com.anomaly729.achronmod.container.AchronicCoreContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class AchronicCoreScreen extends ContainerScreen<AchronicCoreContainer> {
    private final ResourceLocation GUI = new ResourceLocation(AchronMod.MODID,
        "textures/gui/achronic_core_gui.png");

    public AchronicCoreScreen(AchronicCoreContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bind(GUI);
        //int i = this.guiLeft;
        //int j = this.guiTop;
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(matrixStack, i, j, 0, 0, this.getXSize(), this.getYSize());

        /*if(container.isLightningStorm()) {
            this.blit(matrixStack, i + 82, j + 9, 176, 0, 13, 17);
        }*/
    }
}


