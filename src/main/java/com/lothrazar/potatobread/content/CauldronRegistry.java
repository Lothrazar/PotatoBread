package com.lothrazar.potatobread.content;

import com.lothrazar.library.registry.RecipeCauldronFactory;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CauldronRegistry {

  public static void setup(FMLCommonSetupEvent event) {
    if (ConfigRegistryPotato.CAULDRON_WASH.get()) {
      RecipeCauldronFactory.addWater(Items.POTATO, PotatoModRegistry.PEELED.get());
      RecipeCauldronFactory.setup(event);
    }
  }
}
