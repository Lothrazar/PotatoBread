package com.lothrazar.potatobread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lothrazar.potatobread.content.ClientRegistryPotato;
import com.lothrazar.potatobread.content.ConfigManager;
import com.lothrazar.potatobread.content.PotatoModRegistry;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LayeredCauldronBlock;
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

  private void setup(final FMLCommonSetupEvent event) {
    event.enqueueWork(() -> {
      CauldronInteraction WASH_PEEL = (state, level, pos, player, hand, stack) -> {
        if (stack.is(Items.POTATO)) {
          //replace all the item, be generous. we could instead stack.shrink and drop just one
          player.setItemInHand(hand, new ItemStack(PotatoModRegistry.PEELED.get(), stack.getCount()));
          LayeredCauldronBlock.lowerFillLevel(state, level, pos);
          return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
      };
      if (ConfigManager.CAULDRON_WASH.get()) {
        CauldronInteraction.WATER.put(Items.POTATO, WASH_PEEL);
      }
    });
  }
}
