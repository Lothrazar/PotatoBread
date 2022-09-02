package com.lothrazar.potatobread;

import com.lothrazar.library.item.ItemFlib;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.ItemStack;

public class ItemBread extends ItemFlib {

  public static final FoodProperties BREAD = Foods.BREAD; // 4
  public static final FoodProperties COOKED_BEEF = Foods.COOKED_BEEF; // 8, 0.8  first number is best
  public static final FoodProperties GOLDEN_CARROT = Foods.GOLDEN_CARROT; // 6, 1.2 best sat
  public static final FoodProperties BAKED_POTATO = Foods.BAKED_POTATO; // 5 

  public ItemBread(Properties prop) {
    super(prop.tab(PotatoModRegistry.TAB).food(BREAD), new ItemFlib.Settings().tooltip());
  }

  public static int getColour(ItemStack stack) {
    // TODO Auto-generated method stub
    if (stack.hasTag()) {
      String flav = stack.getTag().getString("topping");
      if ("berries".equals(flav)) {
        return 0xFFFF0000;
      }
    }
    return 0xFFFFFFFF;
  }
}
