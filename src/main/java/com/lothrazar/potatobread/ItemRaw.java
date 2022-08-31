package com.lothrazar.potatobread;

import com.lothrazar.library.item.ItemFlib;

public class ItemRaw extends ItemFlib {

  public ItemRaw(Properties prop) {
    super(prop.tab(PotatoModRegistry.TAB), new ItemFlib.Settings().tooltip());
  }
}
