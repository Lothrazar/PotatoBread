package com.lothrazar.potatobread.jei;

import com.lothrazar.library.registry.RecipeCauldronFactory;
import com.lothrazar.potatobread.PotatoModMain;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class PotatoBreadJeiPlugin implements IModPlugin {

  @Override
  public ResourceLocation getPluginUid() {
    return ResourceLocation.fromNamespaceAndPath(PotatoModMain.MODID, "jei_plugin");
  }

  @Override
  public void registerCategories(IRecipeCategoryRegistration registration) {
    registration.addRecipeCategories(
        new CauldronWashCategory(registration.getJeiHelpers().getGuiHelper()));
  }

  @Override
  public void registerRecipes(IRecipeRegistration registration) {
    registration.addRecipes(CauldronWashCategory.TYPE,
        RecipeCauldronFactory.getWaterRecipes());
  }
}
