package com.lothrazar.potatobread.item;

import com.lothrazar.library.item.ItemFlib;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStackTemplate;

public class ItemCraftTool extends ItemFlib {

  public ItemCraftTool(Properties prop) {
    super(prop.stacksTo(1), new ItemFlib.Settings().tooltip());
  }

  //TODO: i guess durability could go here
  @Override
  public ItemStackTemplate getCraftingRemainder(ItemInstance instance) {
    return new ItemStackTemplate(this);
  }
}
