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

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, PotatoModMain.MODID);
  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, PotatoModMain.MODID);
  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, PotatoModMain.MODID);
  public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PotatoModMain.MODID);

  public static final FoodProperties FOOD_HEAVY_BREAD = (new FoodProperties.Builder()).nutrition(8).saturationModifier(1.2F).build(); // 5, 0.6 is normal bread
  public static final FoodProperties COOKED_BEEF = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.8F).build(); // 8, 0.8 is COOKED_BEEF
  public static final FoodProperties GOLDEN_CARROT = (new FoodProperties.Builder()).nutrition(6).saturationModifier(1.2F).build(); // 6, 1.2 GOLDEN_CARROT
  public static final FoodProperties BAKED_POTATO = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.6F).build(); // 5, 0.6
  //peeled potatoes and 3 ingredients
  public static final DeferredHolder<Item, ItemRaw> PEELED = ITEMS.register("potato_peeled", () -> new ItemRaw(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.3F).build()), new ItemFlib.Settings().tooltip())); //edible same as raw
  public static final DeferredHolder<Item, ItemRaw> STARCH = ITEMS.register("starch", () -> new ItemRaw(new Item.Properties(), new ItemFlib.Settings().noTooltip()));
  public static final DeferredHolder<Item, ItemRaw> DOUGH = ITEMS.register("dough", () -> new ItemRaw(new Item.Properties(), new ItemFlib.Settings().tooltip()));
  public static final DeferredHolder<Item, ItemRaw> ICING = ITEMS.register("icing", () -> new ItemRaw(new Item.Properties(), new ItemFlib.Settings().tooltip()));
  //  public static final DeferredHolder<Item, ItemRaw> MAYO = ITEMS.register("mayo", () -> new ItemRaw(new Item.Properties()));
  //2 tools
  public static final DeferredHolder<Item, ItemCraftTool> ROLLING_PIN = ITEMS.register("rolling_pin", () -> new ItemCraftTool(new Item.Properties().rarity(Rarity.UNCOMMON)));
  //  public static final DeferredHolder<Item, ItemCraftTool> PASTRY_KNIFE = ITEMS.register("pastry_knife", () -> new ItemCraftTool(new Item.Properties().rarity(Rarity.UNCOMMON)));
  // potato_flour
  // potato_flakes // joke cereal
  //FOOD
  public static final DeferredHolder<Item, ItemRaw> LOAF_RAW = ITEMS.register("potato_loaf_raw", () -> new ItemRaw(new Item.Properties()));
  public static final DeferredHolder<Item, ItemRaw> BREAD_RAW = ITEMS.register("potato_bread_raw", () -> new ItemRaw(new Item.Properties()));
  public static final DeferredHolder<Item, ItemRaw> ROLL_RAW = ITEMS.register("potato_roll_raw", () -> new ItemRaw(new Item.Properties()));
  public static final DeferredHolder<Item, ItemRaw> BUN_RAW = ITEMS.register("potato_bun_raw", () -> new ItemRaw(new Item.Properties()));
  //
  public static final DeferredHolder<Item, ItemBread> LOAF = ITEMS.register("potato_loaf", () -> new ItemBread(new Item.Properties().food(FOOD_HEAVY_BREAD).rarity(Rarity.UNCOMMON), new ItemFlib.Settings().tooltip()));
  public static final DeferredHolder<Item, ItemBread> BREAD = ITEMS.register("potato_bread", () -> new ItemBread(new Item.Properties().food(BAKED_POTATO)));
  public static final DeferredHolder<Item, ItemBread> ROLL = ITEMS.register("potato_roll", () -> new ItemBread(new Item.Properties().food(BAKED_POTATO)));
  public static final DeferredHolder<Item, ItemBread> BUN = ITEMS.register("potato_bun", () -> new ItemBread(new Item.Properties().food(BAKED_POTATO)));
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
