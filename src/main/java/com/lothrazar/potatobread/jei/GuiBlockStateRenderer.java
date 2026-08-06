package com.lothrazar.potatobread.jei;

import java.util.ArrayList;
import java.util.List;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.QuadInstance;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

// GUI content is 2D-only now (GuiGraphicsExtractor#pose() returns a Matrix3x2fStack, not a PoseStack),
// so true 3D content in a screen (entity previews, book models, etc.) renders to an off-screen texture
// via this PictureInPictureRenderer mechanism, registered through
// RegisterPictureInPictureRenderersEvent, then blits that texture into GUI space. This is the block-state
// equivalent of vanilla's own GuiEntityRenderer/GuiBookModelRenderer.
public class GuiBlockStateRenderer extends PictureInPictureRenderer<GuiBlockStateRenderState> {

  private static final Direction[] DIRECTIONS = Direction.values();

  public GuiBlockStateRenderer(MultiBufferSource.BufferSource bufferSource) {
    super(bufferSource);
  }

  @Override
  public Class<GuiBlockStateRenderState> getRenderStateClass() {
    return GuiBlockStateRenderState.class;
  }

  @Override
  protected void renderToTexture(GuiBlockStateRenderState state, PoseStack poseStack) {
    poseStack.mulPose(Axis.XP.rotationDegrees(30));
    poseStack.mulPose(Axis.YP.rotationDegrees(225));
    poseStack.translate(-0.5, -0.5, -0.5);

    BlockState blockState = state.blockState();
    Minecraft mc = Minecraft.getInstance();
    // no real world to sample light/AO from here, so this renders with a fixed full-bright QuadInstance
    // instead of the AO-aware light lookup ModelBlockRenderer#tesselateBlock would use in-world.
    BlockAndTintGetter tintGetter = mc.level instanceof BlockAndTintGetter batg ? batg : BlockAndTintGetter.EMPTY;
    BlockStateModel model = mc.getModelManager().getBlockStateModelSet().get(blockState);
    List<BlockStateModelPart> parts = new ArrayList<>();
    model.collectParts(tintGetter, BlockPos.ZERO, blockState, RandomSource.create(blockState.getSeed(BlockPos.ZERO)), parts);

    QuadInstance instance = new QuadInstance();
    instance.setLightCoords(LightCoordsUtil.FULL_BRIGHT);
    instance.setOverlayCoords(OverlayTexture.NO_OVERLAY);
    instance.setColor(0xFFFFFFFF);

    MultiBufferSource tinted = renderType ->
        new com.lothrazar.library.render.TintedVertexConsumer(this.bufferSource.getBuffer(renderType), state.tintColor());

    for (BlockStateModelPart part : parts) {
      for (Direction direction : DIRECTIONS) {
        for (BakedQuad quad : part.getQuads(direction)) {
          putQuad(poseStack, tinted, quad, instance);
        }
      }
      for (BakedQuad quad : part.getQuads(null)) {
        putQuad(poseStack, tinted, quad, instance);
      }
    }
  }

  private static void putQuad(PoseStack poseStack, MultiBufferSource tinted, BakedQuad quad, QuadInstance instance) {
    var renderType = switch (quad.materialInfo().layer()) {
      case SOLID -> RenderTypes.solidMovingBlock();
      case CUTOUT -> RenderTypes.cutoutMovingBlock();
      case TRANSLUCENT -> RenderTypes.translucentMovingBlock();
    };
    tinted.getBuffer(renderType).putBakedQuad(poseStack.last(), quad, instance);
  }

  @Override
  protected String getTextureLabel() {
    return "block state";
  }
}
