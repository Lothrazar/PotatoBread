package com.lothrazar.potatobread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lothrazar.potatobread.content.CauldronRegistry;
import com.lothrazar.potatobread.content.ClientRegistryPotato;
import com.lothrazar.potatobread.content.ConfigManager;
import com.lothrazar.potatobread.content.PotatoModRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(PotatoModMain.MODID)
public class PotatoModMain {

  public static final String MODID = "potatobread";
  public static final Logger LOGGER = LogManager.getLogger();

  public PotatoModMain() {
    IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    PotatoModRegistry.BLOCKS.register(eventBus);
    PotatoModRegistry.ITEMS.register(eventBus);
    PotatoModRegistry.BLOCK_ENTITIES.register(eventBus);
    ConfigManager.setup();
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientRegistryPotato::setupClient);
  }
  //    MinecraftForge.EVENT_BUS.register(new WhateverEvents()); 

  //KNIFE and ROLLING PIN: tooltip about how theyre multi use
  //potato_loaf.tooltip : and possibly rarity: most filling, cannot be decorated
  //
  private void setup(final FMLCommonSetupEvent event) {
    CauldronRegistry.setup(event);
  }
}
