package com.lothrazar.potatobread.content;

import com.lothrazar.potatobread.PotatoModMain;
import com.lothrazar.potatobread.item.BreadToppingTintSource;
import com.lothrazar.potatobread.jei.GuiBlockStateRenderState;
import com.lothrazar.potatobread.jei.GuiBlockStateRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterPictureInPictureRenderersEvent;

@EventBusSubscriber(modid = PotatoModMain.MODID, value = Dist.CLIENT)
public class ClientRegistryPotato {

  //item colors are fully data-driven now; this just registers the tint source *type*,
  //actual per-item tint assignment happens in assets/potatobread/items/*.json
  @SubscribeEvent
  public static void onRegisterItemColorHandlers(RegisterColorHandlersEvent.ItemTintSources event) {
    event.register(Identifier.fromNamespaceAndPath(PotatoModMain.MODID, "bread_topping"), BreadToppingTintSource.MAP_CODEC);
  }

  @SubscribeEvent
  public static void onRegisterPictureInPictureRenderers(RegisterPictureInPictureRenderersEvent event) {
    event.register(GuiBlockStateRenderState.class, GuiBlockStateRenderer::new);
  }
}
