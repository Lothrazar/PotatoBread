package com.lothrazar.potatobread.content;

import com.lothrazar.library.item.ItemFlib;
import com.lothrazar.potatobread.PotatoModMain;
import com.lothrazar.potatobread.item.ItemBread;
import com.lothrazar.potatobread.item.ItemCraftTool;
import com.lothrazar.potatobread.item.ItemRaw;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PotatoModRegistry {

  public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PotatoModMain.MODID);
  public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PotatoModMain.MODID);
  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, PotatoModMain.MODID);
  public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PotatoModMain.MODID);

  public static final FoodProperties FOOD_HEAVY_BREAD = (new FoodProperties.Builder()).nutrition(8).saturationModifier(1.2F).build(); // 5, 0.6 is normal bread
  public static final FoodProperties COOKED_BEEF = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.8F).build(); // 8, 0.8 is COOKED_BEEF
  public static final FoodProperties GOLDEN_CARROT = (new FoodProperties.Builder()).nutrition(6).saturationModifier(1.2F).build(); // 6, 1.2 GOLDEN_CARROT
  public static final FoodProperties BAKED_POTATO = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.6F).build(); // 5, 0.6
  //peeled potatoes and 3 ingredients
  public static final DeferredHolder<Item, ItemRaw> PEELED = ITEMS.registerItem("potato_peeled", props -> new ItemRaw(props.food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.3F).build()), new ItemFlib.Settings().tooltip())); //edible same as raw
  public static final DeferredHolder<Item, ItemRaw> STARCH = ITEMS.registerItem("starch", props -> new ItemRaw(props, new ItemFlib.Settings().noTooltip()));
  public static final DeferredHolder<Item, ItemRaw> DOUGH = ITEMS.registerItem("dough", props -> new ItemRaw(props, new ItemFlib.Settings().tooltip()));
  public static final DeferredHolder<Item, ItemRaw> ICING = ITEMS.registerItem("icing", props -> new ItemRaw(props, new ItemFlib.Settings().tooltip()));
  //  public static final DeferredHolder<Item, ItemRaw> MAYO = ITEMS.registerItem("mayo", props -> new ItemRaw(props));
  //2 tools
  public static final DeferredHolder<Item, ItemCraftTool> ROLLING_PIN = ITEMS.registerItem("rolling_pin", props -> new ItemCraftTool(props.rarity(Rarity.UNCOMMON)));
  //  public static final DeferredHolder<Item, ItemCraftTool> PASTRY_KNIFE = ITEMS.registerItem("pastry_knife", props -> new ItemCraftTool(props.rarity(Rarity.UNCOMMON)));
  // potato_flour
  // potato_flakes // joke cereal
  //FOOD
  public static final DeferredHolder<Item, ItemRaw> LOAF_RAW = ITEMS.registerItem("potato_loaf_raw", props -> new ItemRaw(props));
  public static final DeferredHolder<Item, ItemRaw> BREAD_RAW = ITEMS.registerItem("potato_bread_raw", props -> new ItemRaw(props));
  public static final DeferredHolder<Item, ItemRaw> ROLL_RAW = ITEMS.registerItem("potato_roll_raw", props -> new ItemRaw(props));
  public static final DeferredHolder<Item, ItemRaw> BUN_RAW = ITEMS.registerItem("potato_bun_raw", props -> new ItemRaw(props));
  //
  public static final DeferredHolder<Item, ItemBread> LOAF = ITEMS.registerItem("potato_loaf", props -> new ItemBread(props.food(FOOD_HEAVY_BREAD).rarity(Rarity.UNCOMMON), new ItemFlib.Settings().tooltip()));
  public static final DeferredHolder<Item, ItemBread> BREAD = ITEMS.registerItem("potato_bread", props -> new ItemBread(props.food(BAKED_POTATO)));
  public static final DeferredHolder<Item, ItemBread> ROLL = ITEMS.registerItem("potato_roll", props -> new ItemBread(props.food(BAKED_POTATO)));
  public static final DeferredHolder<Item, ItemBread> BUN = ITEMS.registerItem("potato_bun", props -> new ItemBread(props.food(BAKED_POTATO)));
  //BREADMAKER BLOCK!?!?

  public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = TABS.register("tab", () ->
      CreativeModeTab.builder()
          .icon(() -> new ItemStack(ROLLING_PIN.get()))
          .title(Component.translatable("itemGroup." + PotatoModMain.MODID))
          .displayItems((enabledFlags, populator) -> {
            for (DeferredHolder<Item, ? extends Item> entry : ITEMS.getEntries()) {
              populator.accept(entry.get());
            }
          }).build());

  //round one is a BUN
  // a ROLL
  // pretzel
  // paper measuring cup
  //paper_cup of milk for
  //
  //rolling pin: sticks and maybe a fence
  //raw potato + ? = peeled potato
  //
}
