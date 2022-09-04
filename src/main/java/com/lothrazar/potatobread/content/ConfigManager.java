package com.lothrazar.potatobread.content;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.lothrazar.potatobread.PotatoModMain;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.fml.loading.FMLPaths;

public class ConfigManager {

  private static final ForgeConfigSpec.Builder CFG = new ForgeConfigSpec.Builder();
  private static ForgeConfigSpec COMMON_CONFIG;
  public static BooleanValue CAULDRON_WASH;
  static {
    initConfig();
  }

  private static void initConfig() {
    CFG.comment("General settings").push(PotatoModMain.MODID);
    CAULDRON_WASH = CFG.comment("If potatoes turn into Peeled Potatoes using a water cauldron").define("water_cauldron.potato", true);
    CFG.pop(); // one pop for every push
    COMMON_CONFIG = CFG.build();
  }

  public static void setup() {
    final CommentedFileConfig configData = CommentedFileConfig.builder(FMLPaths.CONFIGDIR.get().resolve(PotatoModMain.MODID + ".toml"))
        .sync()
        .autosave()
        .writingMode(WritingMode.REPLACE)
        .build();
    configData.load();
    COMMON_CONFIG.setConfig(configData);
  }
}
