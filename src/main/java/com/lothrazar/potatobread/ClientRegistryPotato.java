package com.lothrazar.potatobread;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistryPotato {

  public static void setupClient(final FMLClientSetupEvent event) {
    initColours();
  }

  @OnlyIn(Dist.CLIENT)
  private static void initColours() {
    Minecraft.getInstance().getItemColors().register((stack, tintIndex) -> {
      if (stack.getItem() == PotatoModRegistry.ROLL.get()) {
        // ok
        if (tintIndex == 0) { //layer zero is outline, ignore this 
          return 0xFFFFFFFF;
        }
        //layer 1 is overlay  
        int c = ItemBread.getColour(stack);
        return c;
      }
      //      else if (stack.getItem() == ItemRegistry.MOB_CONTAINER.get()) {
      //        if (stack.hasTag() && tintIndex > 0) {
      //          //what entity is inside
      //          EntityType<?> thing = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(stack.getTag().getString(EntityMagicNetEmpty.NBT_ENTITYID)));
      //          //pull the colours from the egg
      //          for (SpawnEggItem spawneggitem : SpawnEggItem.eggs()) {
      //            if (spawneggitem.getType(null) == thing) {
      //              return spawneggitem.getColor(tintIndex - 1);
      //            }
      //          }
      //        }
      //      }
      return -1;
    }, PotatoModRegistry.ROLL.get());
  }
}
