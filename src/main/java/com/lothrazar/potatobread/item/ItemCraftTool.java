package com.lothrazar.potatobread.item;

import com.lothrazar.library.item.ItemFlib;
import com.lothrazar.potatobread.content.PotatoModRegistry;
import net.minecraft.world.item.ItemStack;

public class ItemCraftTool extends ItemFlib {

  public ItemCraftTool(Properties prop) {
    super(prop.tab(PotatoModRegistry.TAB).stacksTo(1), new ItemFlib.Settings().tooltip());
  }

  @Override
  public ItemStack getContainerItem(ItemStack itemStack) {
    if (!hasContainerItem(itemStack)) {
      return ItemStack.EMPTY;
    }
    //TODO: i guess durability could go here
    return new ItemStack(this);
  }

  /**
   * ItemStack sensitive version of hasContainerItem
   *
   * @param stack
   *          The current item stack
   * @return True if this item has a 'container'
   */
  @Override
  public boolean hasContainerItem(ItemStack stack) {
    return stack.getItem() == this;
  }
}
