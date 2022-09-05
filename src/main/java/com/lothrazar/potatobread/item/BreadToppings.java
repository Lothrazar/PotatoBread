package com.lothrazar.potatobread.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public enum BreadToppings {

  BERRIES, CHOCOLATE, GLOW, MELON;

  //?? apples? blue something? double-icing? melon->pink icing?
  //what flowers are edible in rela life?
  //kelp lol
  //healthy sandwich toppings? cooked meat?
  //eggs into mayonase. same exact thing as icing
  public static BreadToppings fromString(String tag) {
    try {
      return BreadToppings.valueOf(tag.toUpperCase());
    }
    catch (Exception e) {
      return null;
    }
  }

  public static BreadToppings fromStack(ItemStack stack) {
    if (stack.hasTag() && stack.getTag().contains("topping")) {
      return fromString(stack.getTag().getString("topping"));
    }
    return null;
  }

  @Override
  public String toString() {
    return name().toLowerCase();
  }

  int color() {
    switch (this) {
      case BERRIES:
        return 0x00941600;
      case CHOCOLATE:
        return 0x00593B1D;
      case GLOW:
        return 0x00FEA233;
      case MELON:
        return 0x00FF3059;
    }
    //      default:
    System.out.println("missing col " + this);
    return 0xFFFFFFFF;
  }

  MobEffectInstance getEffect() {
    switch (this) {
      case BERRIES:
        return new MobEffectInstance(MobEffects.DIG_SPEED, 20 * 200, 1);
      case CHOCOLATE:
        return new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20 * 60, 0);
      case GLOW:
        return new MobEffectInstance(MobEffects.ABSORPTION, 20 * 200, 1);
      case MELON:
        return new MobEffectInstance(MobEffects.REGENERATION, 20 * 30, 0);
    }
    return null;
  }

  void finishEating(LivingEntity entity) {
    entity.addEffect(getEffect());
    switch (this) {
      case GLOW: //secret hidden effect
        entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 20 * 200, 1));
      break;
      case MELON:
      break;
      case BERRIES:
      break;
      case CHOCOLATE:
      break;
    }
  }
}