package com.lothrazar.potatobread.content;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CauldronRegistry {

  public static void setup(FMLCommonSetupEvent event) {
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
