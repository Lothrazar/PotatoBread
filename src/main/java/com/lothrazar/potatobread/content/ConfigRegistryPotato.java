package com.lothrazar.potatobread.content;

import com.lothrazar.potatobread.PotatoModMain;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;

public class ConfigRegistryPotato {

  public static final ModConfigSpec CONFIG;
  public static BooleanValue CAULDRON_WASH;
  static {
    final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    BUILDER.comment("General settings").push(PotatoModMain.MODID);
    CAULDRON_WASH = BUILDER.comment("If potatoes turn into Peeled Potatoes using a water cauldron").define("water_cauldron.potato", true);
    BUILDER.pop(); // one pop for every push
    CONFIG = BUILDER.build();
  }

}
