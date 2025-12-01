package com.inolia_zaicek.flame_chase_artifacts.item;


import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class StrawberryRoselleItem extends Item {
    public StrawberryRoselleItem() {
        super(new Item.Properties()
                        .stacksTo(64)
                        .food(new FoodProperties.Builder()
                                .nutrition(10)         // 回复10饥饿值
                                .saturationMod(20f)    // 饱和度10
                                .build())
                // 你可以调节吃的速度，默认为标准，若需要可用 .fastEat(); 但这里用默认即可
        );
    }

    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        String itemName = getTooltipItemName(); // 获取物品 ID
        {
            // 鼠标经过时的默认文本
            // 翻译键格式: tooltip.<你的ModID>.<物品ID>_text
            pTooltipComponents.add(Component.translatable("tooltip." + "flame_chase_artifacts" + "." + itemName + ".text")
                    .withStyle(ChatFormatting.GRAY));
            pTooltipComponents.add(Component.translatable("tooltip." + "flame_chase_artifacts" + "." + itemName + ".text1")
                    .withStyle(ChatFormatting.GRAY));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,200,0));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,200,6));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION,200,2));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST,200,1));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,200,1));
        }
        return super.finishUsingItem(itemStack, level, livingEntity);
    }
}
