package com.lothrazar.potatobread;

import com.lothrazar.library.item.ItemFlib;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class ItemBread extends ItemFlib {

  public static final FoodProperties BREAD = Foods.BREAD; // 4
  public static final FoodProperties COOKED_BEEF = Foods.COOKED_BEEF; // 8, 0.8  first number is best
  public static final FoodProperties GOLDEN_CARROT = Foods.GOLDEN_CARROT; // 6, 1.2 best sat
  public static final FoodProperties BAKED_POTATO = Foods.BAKED_POTATO; // 5 

  public static enum Toppings {

    BERRIES, CHOCOLATE;

    public static Toppings fromString(String tag) {
      try {
        return Toppings.valueOf(tag);
      }
      catch (Exception e) {
        return null;
      }
    }

    public static Toppings fromStack(ItemStack stack) {
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
          return 0xFFFF0000;
        case CHOCOLATE:
          return 0x00FF0000;
        default:
          return 0xFFFFFFFF;
      }
    }

    void finishEating(LivingEntity entity) {
      switch (this) {
        case BERRIES:
          entity.addEffect(new MobEffectInstance(MobEffects.BAD_OMEN, 1111, 1));
        break;
        case CHOCOLATE:
          entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1111, 1));
        break;
      }
    }
  }

  public ItemBread(Properties prop) {
    super(prop.tab(PotatoModRegistry.TAB).food(BREAD), new ItemFlib.Settings().tooltip());
  }

  @Override
  public Rarity getRarity(ItemStack stack) {
    if (Toppings.fromStack(stack) != null) {
      return Rarity.EPIC;
    }
    return Rarity.COMMON;
  }

  @Override
  public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
    Toppings top = Toppings.fromStack(stack);
    if (top != null) {
      top.finishEating(entity);
    }
    return super.finishUsingItem(stack, level, entity);
  }

  public static int getColour(ItemStack stack) {
    Toppings top = Toppings.fromStack(stack);
    if (top != null) {
      return top.color();
    }
    return 0xFFFFFFFF;
  }
}
