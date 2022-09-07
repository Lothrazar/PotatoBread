package com.lothrazar.potatobread.item;

import com.lothrazar.library.item.ItemFlib;
import com.lothrazar.potatobread.content.PotatoModRegistry;

public class ItemRaw extends ItemFlib {

  public ItemRaw(Properties prop) {
    super(prop.tab(PotatoModRegistry.TAB), new ItemFlib.Settings().noTooltip());
  }

  public ItemRaw(Properties prop, ItemFlib.Settings s) {
    super(prop.tab(PotatoModRegistry.TAB), s);
  }
}
