package com.lothrazar.potatobread.content;

import com.lothrazar.potatobread.PotatoModMain;
import com.lothrazar.potatobread.item.ItemBread;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = PotatoModMain.MODID, value = Dist.CLIENT)
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
