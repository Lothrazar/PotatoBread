package com.lothrazar.potatobread.item;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

// Item colors are fully data-driven now (RegisterColorHandlersEvent has no Item variant anymore);
// this is a custom ItemTintSource type, registered via RegisterColorHandlersEvent.ItemTintSources
// and referenced by id from the "tints" array in assets/potatobread/items/*.json, since the topping
// overlay color depends on per-stack state (BreadToppings.fromStack) that none of the built-in tint
// source types cover.
public record BreadToppingTintSource() implements ItemTintSource {

  public static final MapCodec<BreadToppingTintSource> MAP_CODEC = MapCodec.unit(BreadToppingTintSource::new);

  @Override
  public int calculate(ItemStack stack, ClientLevel level, LivingEntity owner) {
    return ItemBread.getColour(stack);
  }

  @Override
  public MapCodec<BreadToppingTintSource> type() {
    return MAP_CODEC;
  }
}
