package com.lothrazar.potatobread.content;

import com.lothrazar.library.config.ConfigTemplate;
import com.lothrazar.potatobread.PotatoModMain;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class ConfigRegistryPotato extends ConfigTemplate {

  private static final ForgeConfigSpec CONFIG;
  public static BooleanValue CAULDRON_WASH;
  static {
    final ForgeConfigSpec.Builder BUILDER = builder();
    BUILDER.comment("General settings").push(PotatoModMain.MODID);
    CAULDRON_WASH = BUILDER.comment("If potatoes turn into Peeled Potatoes using a water cauldron").define("water_cauldron.potato", true);
    BUILDER.pop(); // one pop for every push
    CONFIG = BUILDER.build();
  }

  public ConfigRegistryPotato() {
    CONFIG.setConfig(setup(PotatoModMain.MODID));
  }
}
