package com.lothrazar.potatobread.content;

import com.lothrazar.potatobread.item.ItemBread;
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
      if (stack.getItem() == PotatoModRegistry.BREAD.get()
          || stack.getItem() == PotatoModRegistry.BUN.get()) {
        if (tintIndex == 0) { //layer zero is outline, ignore this 
          return 0xFFFFFFFF;
        }
        //else layer 1 is overlay  
        else if (tintIndex == 1) {
          return ItemBread.getColour(stack);
        }
      }
      return -1;
    }, PotatoModRegistry.BUN.get(), PotatoModRegistry.BREAD.get());
  }
}
