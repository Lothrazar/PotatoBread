package com.lothrazar.potatobread.content;

import com.lothrazar.potatobread.PotatoModMain;
import com.lothrazar.potatobread.item.ItemBread;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PotatoModMain.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistryPotato {

  /**
   * Replaces Minecraft.getInstance().getItemColors().register
   * 
   * @param event
   */
  @SubscribeEvent
  public static void onRegisterItemColorHandlers(RegisterColorHandlersEvent.Item event) {
    event.register((stack, tintIndex) -> {
      if (stack.is(PotatoModRegistry.BREAD.get())
          || stack.is(PotatoModRegistry.BUN.get())) {
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
