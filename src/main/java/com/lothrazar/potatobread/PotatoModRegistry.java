package com.lothrazar.potatobread;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PotatoModRegistry {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PotatoModMain.MODID);
  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PotatoModMain.MODID);
  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, PotatoModMain.MODID);
  //
  public static final CreativeModeTab TAB = new CreativeModeTab(PotatoModMain.MODID) {

    @Override
    public ItemStack makeIcon() {
      return new ItemStack(ROLLING_PIN.get());
    }
  };
  //
  public static final RegistryObject<Item> PEELED = ITEMS.register("potato_peeled", () -> new ItemRaw(new Item.Properties()));
  public static final RegistryObject<Item> STARCH = ITEMS.register("starch", () -> new ItemRaw(new Item.Properties()));
  public static final RegistryObject<Item> icing = ITEMS.register("icing", () -> new ItemRaw(new Item.Properties()));
  public static final RegistryObject<Item> DOUGH = ITEMS.register("dough", () -> new ItemRaw(new Item.Properties()));
  public static final RegistryObject<Item> ROLLING_PIN = ITEMS.register("rolling_pin", () -> new ItemCraftTool(new Item.Properties()));
  public static final RegistryObject<Item> pastry_knife = ITEMS.register("pastry_knife", () -> new ItemCraftTool(new Item.Properties()));
  // potato_flour 
  // potato_flakes // joke cereal
  //FOOD
  public static final RegistryObject<Item> BREAD_RAW = ITEMS.register("potato_bread_raw", () -> new ItemRaw(new Item.Properties()));
  public static final RegistryObject<Item> BREAD = ITEMS.register("potato_bread", () -> new ItemBread(new Item.Properties()));
  public static final RegistryObject<Item> LOAF_RAW = ITEMS.register("potato_loaf_raw", () -> new ItemRaw(new Item.Properties()));
  public static final RegistryObject<Item> LOAF = ITEMS.register("potato_loaf", () -> new ItemBread(new Item.Properties()));
  public static final RegistryObject<Item> ROLL_RAW = ITEMS.register("potato_roll_raw", () -> new ItemRaw(new Item.Properties()));
  public static final RegistryObject<Item> ROLL = ITEMS.register("potato_roll", () -> new ItemBread(new Item.Properties()));
  public static final RegistryObject<Item> BUN_RAW = ITEMS.register("potato_bun_raw", () -> new ItemRaw(new Item.Properties()));
  public static final RegistryObject<Item> BUN = ITEMS.register("potato_bun", () -> new ItemBread(new Item.Properties()));
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
