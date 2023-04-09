package com.lothrazar.potatobread.item;

import java.util.List;
import com.lothrazar.library.item.ItemFlib;
import com.lothrazar.library.util.ChatUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemBread extends ItemFlib {

  public ItemBread(Properties prop) {
    super(prop, new ItemFlib.Settings());
  }

  public ItemBread(Properties prop, ItemFlib.Settings s) {
    super(prop, s);
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
    BreadToppings top = BreadToppings.fromStack(stack);
    if (top != null) {
      // tooltip.add(new TranslatableComponent(stack.getDescriptionId() + "." + top.toString()).withStyle(ChatFormatting.AQUA));
      MobEffectInstance mobeffectinstance = top.getEffect();
      MutableComponent mutablecomponent = ChatUtil.ilang(mobeffectinstance.getDescriptionId());
      if (mobeffectinstance.getAmplifier() > 0) {
        mutablecomponent = Component.translatable("potion.withAmplifier", mutablecomponent, ChatUtil.ilang("potion.potency." + mobeffectinstance.getAmplifier()));
      }
      if (mobeffectinstance.getDuration() > 20) {
        mutablecomponent = Component.translatable("potion.withDuration", mutablecomponent, MobEffectUtil.formatDuration(mobeffectinstance, 1));
      }
      tooltip.add(mutablecomponent.withStyle(mobeffectinstance.getEffect().getCategory().getTooltipFormatting()));
    }
    super.appendHoverText(stack, worldIn, tooltip, flagIn);
  }

  @Override
  public boolean isFoil(ItemStack stack) {
    return false; // ?? do we want shimmerToppings.fromStack(stack) != null;
  }

  @Override
  public Rarity getRarity(ItemStack stack) {
    if (BreadToppings.fromStack(stack) != null) {
      return Rarity.EPIC;
    }
    return Rarity.COMMON;
  }

  @Override
  public Component getName(ItemStack st) {
    BreadToppings top = BreadToppings.fromStack(st);
    if (top != null) {
      return Component.translatable(this.getDescriptionId(st) + "." + top.toString());
    }
    return super.getName(st); //  new TranslatableComponent(this.getDescriptionId(st) );
  }

  @Override
  public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
    BreadToppings top = BreadToppings.fromStack(stack);
    if (top != null) {
      top.finishEating(entity);
    }
    return super.finishUsingItem(stack, level, entity);
  }

  public static int getColour(ItemStack stack) {
    BreadToppings top = BreadToppings.fromStack(stack);
    if (top != null) {
      return top.color();
    }
    return 0xFFFFFFFF;
  }
}
