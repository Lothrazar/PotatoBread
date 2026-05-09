package com.lothrazar.potatobread.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mezz.jei.api.gui.drawable.IDrawable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;

public class BlockStateDrawable implements IDrawable {

  private final BlockState blockState;
  private final int width;
  private final int height;

  public BlockStateDrawable(BlockState blockState, int width, int height) {
    this.blockState = blockState;
    this.width = width;
    this.height = height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public void draw(GuiGraphics guiGraphics, int xOffset, int yOffset) {
    Minecraft mc = Minecraft.getInstance();
    MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();

    int waterColor = mc.player != null && mc.level != null
        ? BiomeColors.getAverageWaterColor(mc.level, mc.player.blockPosition())
        : 0x3F76E4;

    MultiBufferSource tinted = renderType ->
        new com.lothrazar.library.render.TintedVertexConsumer(bufferSource.getBuffer(renderType), waterColor);

    PoseStack poseStack = guiGraphics.pose();
    poseStack.pushPose();
    poseStack.translate(xOffset + width / 2.0, yOffset + height / 2.0, 100);
    poseStack.scale(width * 0.6f, -height * 0.6f, width * 0.6f);
    poseStack.mulPose(Axis.XP.rotationDegrees(30));
    poseStack.mulPose(Axis.YP.rotationDegrees(225));
    poseStack.translate(-0.5, -0.5, -0.5);

    mc.getBlockRenderer().renderSingleBlock(
        blockState, poseStack, tinted,
        LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY,
        ModelData.EMPTY, null);
    bufferSource.endBatch();

    poseStack.popPose();
  }
}
