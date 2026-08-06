package com.lothrazar.potatobread.jei;

import mezz.jei.api.gui.drawable.IDrawable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.block.state.BlockState;

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
  public void draw(GuiGraphicsExtractor guiGraphics, int xOffset, int yOffset) {
    Minecraft mc = Minecraft.getInstance();
    int waterColor = mc.player != null && mc.level != null
        ? BiomeColors.getAverageWaterColor(mc.level, mc.player.blockPosition())
        : 0x3F76E4;

    GuiBlockStateRenderState state = new GuiBlockStateRenderState(
        blockState, waterColor,
        xOffset, yOffset, xOffset + width, yOffset + height,
        width * 0.6f,
        guiGraphics.peekScissorStack());
    guiGraphics.submitPictureInPictureRenderState(state);
  }
}
