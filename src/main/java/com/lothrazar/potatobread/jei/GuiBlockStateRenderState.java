package com.lothrazar.potatobread.jei;

import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public record GuiBlockStateRenderState(
    BlockState blockState,
    int tintColor,
    int x0,
    int y0,
    int x1,
    int y1,
    float scale,
    @Nullable ScreenRectangle scissorArea,
    @Nullable ScreenRectangle bounds
) implements PictureInPictureRenderState {

  public GuiBlockStateRenderState(BlockState blockState, int tintColor, int x0, int y0, int x1, int y1, float scale, @Nullable ScreenRectangle scissorArea) {
    this(blockState, tintColor, x0, y0, x1, y1, scale, scissorArea, PictureInPictureRenderState.getBounds(x0, y0, x1, y1, scissorArea));
  }
}
