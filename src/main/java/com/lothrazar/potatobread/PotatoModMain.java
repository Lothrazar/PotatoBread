package com.lothrazar.potatobread;

import net.neoforged.fml.ModContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lothrazar.potatobread.content.CauldronRegistry;
import com.lothrazar.potatobread.content.ConfigRegistryPotato;
import com.lothrazar.potatobread.content.PotatoModRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(PotatoModMain.MODID)
public class PotatoModMain {

  public static final String MODID = "potatobread";
  public static final Logger LOGGER = LogManager.getLogger();

  public PotatoModMain(IEventBus modEventBus, ModContainer modContainer) {
    PotatoModRegistry.BLOCKS.register(modEventBus);
    PotatoModRegistry.ITEMS.register(modEventBus);
    PotatoModRegistry.BLOCK_ENTITIES.register(modEventBus);
    PotatoModRegistry.TABS.register(modEventBus);
    new ConfigRegistryPotato(modContainer);
    modEventBus.addListener(this::setup);
  }
  //    MinecraftForge.EVENT_BUS.register(new WhateverEvents());

  //cyclic recipes for icing and dough, maybe liquid milk
  //farmers delight cookpot recipes
  //PLAIN GRINDER : grind starch
  //cyclic crusher: grind starch
  //
  //
  private void setup(final FMLCommonSetupEvent event) {
    CauldronRegistry.setup(event);
  }
}
