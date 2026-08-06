package com.lothrazar.potatobread.jei;

import com.lothrazar.library.registry.RecipeCauldronFactory.CauldronFakeRecipe;
import com.lothrazar.potatobread.PotatoModMain;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CauldronWashCategory implements IRecipeCategory<CauldronFakeRecipe> {

  public static final RecipeType<CauldronFakeRecipe> TYPE =
      RecipeType.create(PotatoModMain.MODID, "cauldron_wash", CauldronFakeRecipe.class);

  private final IDrawable background;
  private final IDrawable icon;
  private final IDrawable arrow;
  private final IDrawable cauldron;

  public CauldronWashCategory(IGuiHelper guiHelper) {
    background = guiHelper.createBlankDrawable(116, 36);
    BlockState full = Blocks.WATER_CAULDRON.defaultBlockState()
        .setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(3));
    icon = new BlockStateDrawable(full, 16, 16);
    cauldron = new BlockStateDrawable(full, 32, 32);

    arrow = guiHelper.createDrawable(
        Identifier.withDefaultNamespace("textures/gui/container/furnace.png"),
        79, 14, 24, 16);
  }

  @Override
  public RecipeType<CauldronFakeRecipe> getRecipeType() {
    return TYPE;
  }

  @Override
  public Component getTitle() {
    return Component.translatable("jei." + PotatoModMain.MODID + ".cauldron_wash");
  }

  @Override
  public int getWidth() {
    return 116;
  }

  @Override
  public int getHeight() {
    return 36;
  }

  @Override
  public IDrawable getIcon() {
    return icon;
  }

  @Override
  public void setRecipe(IRecipeLayoutBuilder builder, CauldronFakeRecipe recipe, IFocusGroup focuses) {
    builder.addSlot(RecipeIngredientRole.INPUT, 1, 10)
        .addItemStack(new ItemStack(recipe.input.asItem()));
    builder.addSlot(RecipeIngredientRole.OUTPUT, 88, 10)
        .addItemStack(new ItemStack(recipe.output.asItem()));
  }

  @Override
  public void draw(CauldronFakeRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
    background.draw(guiGraphics, 0, 0);
    cauldron.draw(guiGraphics, 24, 4);
    arrow.draw(guiGraphics, 55, 10);
  }

  @Override
  public void getTooltip(ITooltipBuilder tooltip, CauldronFakeRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
    if (mouseX >= 24 && mouseX <= 56 && mouseY >= 4 && mouseY <= 36) {
      tooltip.add(Component.translatable("item.potatobread.potato_peeled.tooltip"));
    }
  }
}
