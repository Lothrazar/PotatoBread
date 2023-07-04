package com.lothrazar.potatobread.content;

import com.lothrazar.library.item.ItemFlib;
import com.lothrazar.potatobread.PotatoModMain;
import com.lothrazar.potatobread.item.ItemBread;
import com.lothrazar.potatobread.item.ItemCraftTool;
import com.lothrazar.potatobread.item.ItemRaw;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PotatoModRegistry {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PotatoModMain.MODID);
  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PotatoModMain.MODID);
  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PotatoModMain.MODID);
  private static final ResourceKey<CreativeModeTab> TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(PotatoModMain.MODID, "tab"));

  @SubscribeEvent
  public static void onCreativeModeTabRegister(RegisterEvent event) {
    event.register(Registries.CREATIVE_MODE_TAB, helper -> {
      helper.register(TAB, CreativeModeTab.builder().icon(() -> new ItemStack(ROLLING_PIN.get()))
          .title(Component.translatable("itemGroup." + PotatoModMain.MODID))
          .displayItems((enabledFlags, populator) -> {
            for (RegistryObject<Item> entry : ITEMS.getEntries()) {
              populator.accept(entry.get());
            }
          }).build());
    });
  }

  public static final FoodProperties FOOD_HEAVY_BREAD = (new FoodProperties.Builder()).nutrition(8).saturationMod(1.2F).build(); // 5, 0.6 is normal bread
  public static final FoodProperties COOKED_BEEF = Foods.COOKED_BEEF; // 8, 0.8  is COOKED_BEEF
  public static final FoodProperties GOLDEN_CARROT = Foods.GOLDEN_CARROT; // 6, 1.2  GOLDEN_CARROT
  public static final FoodProperties BAKED_POTATO = Foods.BAKED_POTATO; // 5 ,0.6
  //peeled potatoes and 3 ingredients
  public static final RegistryObject<Item> PEELED = ITEMS.register("potato_peeled", () -> new ItemRaw(new Item.Properties().food(Foods.POTATO), new ItemFlib.Settings().tooltip())); //edible same as raw
  public static final RegistryObject<Item> STARCH = ITEMS.register("starch", () -> new ItemRaw(new Item.Properties(), new ItemFlib.Settings().noTooltip()));
  public static final RegistryObject<Item> DOUGH = ITEMS.register("dough", () -> new ItemRaw(new Item.Properties(), new ItemFlib.Settings().tooltip()));
  public static final RegistryObject<Item> ICING = ITEMS.register("icing", () -> new ItemRaw(new Item.Properties(), new ItemFlib.Settings().tooltip()));
  //  public static final RegistryObject<Item> MAYO = ITEMS.register("mayo", () -> new ItemRaw(new Item.Properties()));
  //2 tools
  public static final RegistryObject<Item> ROLLING_PIN = ITEMS.register("rolling_pin", () -> new ItemCraftTool(new Item.Properties().rarity(Rarity.UNCOMMON)));
  //  public static final RegistryObject<Item> PASTRY_KNIFE = ITEMS.register("pastry_knife", () -> new ItemCraftTool(new Item.Properties().rarity(Rarity.UNCOMMON)));
  // potato_flour 
  // potato_flakes // joke cereal
  //FOOD
  public static final RegistryObject<Item> LOAF_RAW = ITEMS.register("potato_loaf_raw", () -> new ItemRaw(new Item.Properties()));
  public static final RegistryObject<Item> BREAD_RAW = ITEMS.register("potato_bread_raw", () -> new ItemRaw(new Item.Properties()));
  public static final RegistryObject<Item> ROLL_RAW = ITEMS.register("potato_roll_raw", () -> new ItemRaw(new Item.Properties()));
  public static final RegistryObject<Item> BUN_RAW = ITEMS.register("potato_bun_raw", () -> new ItemRaw(new Item.Properties()));
  //
  public static final RegistryObject<Item> LOAF = ITEMS.register("potato_loaf", () -> new ItemBread(new Item.Properties().food(FOOD_HEAVY_BREAD).rarity(Rarity.UNCOMMON), new ItemFlib.Settings().tooltip()));
  public static final RegistryObject<Item> BREAD = ITEMS.register("potato_bread", () -> new ItemBread(new Item.Properties().food(Foods.BAKED_POTATO)));
  public static final RegistryObject<Item> ROLL = ITEMS.register("potato_roll", () -> new ItemBread(new Item.Properties().food(Foods.BAKED_POTATO)));
  public static final RegistryObject<Item> BUN = ITEMS.register("potato_bun", () -> new ItemBread(new Item.Properties().food(Foods.BAKED_POTATO)));
  //BREADMAKER BLOCK!?!?
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
