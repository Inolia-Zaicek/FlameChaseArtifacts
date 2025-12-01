package com.inolia_zaicek.flame_chase_artifacts.item;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SuppressWarnings({"all", "removal"})
public class OriginSinCuriosItem extends Item implements ICurioItem {
    public OriginSinCuriosItem() {
        super((new Properties()).stacksTo(1).fireResistant().rarity(Rarity.EPIC));
    }
    //死亡不掉落
    @NotNull
    public ICurio.@NotNull DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }    // 鼠标经过物品时显示文字，按下shift键后显示另一行提示文字
    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        //alt——介绍特殊能力
        if(Screen.hasAltDown()) {
            //天空
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.sky_curios"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x4ED5A8))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.sky_curios.text",
                            (int) (FCAconfig.skyCuriosRange.get() * 1),(int) (FCAconfig.skyCuriosRange.get() * 1) ,(float) (FCAconfig.skyCuriosNumber.get() * 100) )
                    .withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //大地
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.earth_curios"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xE5CE38))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.earth_curios.text",
                    (int) (FCAconfig.earthCurios.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //海洋
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.ocean_curios"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x4D63D0))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.ocean_curios.text",
                    (float) (FCAconfig.oceanCurios.get() * 1)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //浪漫
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.romance_curios"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xD8D835))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.romance_curios.text",
                    (int) (FCAconfig.romanceCurios.get() * 1),(int) (FCAconfig.romanceCurios.get() * 1)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //负世
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.worldbearing_curios"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x6DCEDC))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.worldbearing_curios.text",
                    (float) (FCAconfig.worldbearingCurios.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //理性
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.reason_curios"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x00DA49))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.reason_curios.text",
                    (int) (FCAconfig.reasonCuriosFortune.get() * 1),(int) (FCAconfig.reasonCuriosLooting.get() * 1)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //诡计
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.trickery_curios"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x3D23C0))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.trickery_curios.text",
                    (int) (FCAconfig.trickeryCurios.get() * 1)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //纷争
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.strife_curios"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xC74720))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.strife_curios.text",
                    (float) (FCAconfig.strifeCurios.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //死亡
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.death_curios"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x7D3DBD))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.death_curios.text",
                    (float) (FCAconfig.deathCurios.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //岁月
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.time_curios"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xCE85B8))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.time_curios.text",
                    (float) (FCAconfig.timeCurios.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //律法
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.law_curios"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x35C8D2))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.law_curios.text",
                    (float) (FCAconfig.lawCurios.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //门径
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.passage_curios"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xDF2121))));
            //保留2位小数写法"%2.f".formatted((float) (FCAconfig.passageOverCurse.get() * 100))
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.passage_curios.text",
                    (int) (FCAconfig.passageCurios.get() * 1),(int) (FCAconfig.passageCurios.get() * 1)
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //铁墓
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.iron_tomb_curios"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x404040))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.iron_tomb_curios_text",
                    (int) (FCAconfig.ironTombHatred.get() * 100),
                    (float) (FCAconfig.ironTombHatredDamage.get() * 100)
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
        }
        //shift————祝福
        if(Screen.hasShiftDown()) {
            //天空
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.sky_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x4ED5A8))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.sky_curse",
                    (int) (FCAconfig.skyOverCurse.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //大地
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.earth_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xE5CE38))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.earth_curse",
                    (int) (FCAconfig.earthOverCurse.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //海洋
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.ocean_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x4D63D0))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.ocean_curse",
                    (float) (FCAconfig.oceanOverCurse.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //浪漫
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.romance_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xD8D835))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.romance_curse",
                    (float) (FCAconfig.romanceOverCurse.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //负世
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.worldbearing_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x6DCEDC))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.worldbearing_curse",
                    (float) (FCAconfig.worldbearingOverCurse.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //理性
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.reason_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x00DA49))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.reason_curse",
                    (int) (FCAconfig.reasonOverCurse.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //诡计
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.trickery_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x3D23C0))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.trickery_curse",
                    (float) (FCAconfig.trickeryOverCurse.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //纷争
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.strife_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xC74720))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.strife_curse",
                    (float) (FCAconfig.strifeOverCurse.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //死亡
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.death_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x7D3DBD))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.death_curse",
                    (int) (FCAconfig.deathOverCurse.get() * 1)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //岁月
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.time_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xCE85B8))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.time_curse",
                    (float) (FCAconfig.timeOverCurse.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //律法
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.law_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x35C8D2))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.law_curse",
                    (float) (FCAconfig.lawOverCurse.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //门径
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.passage_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xDF2121))));
            //保留2位小数写法"%2.f".formatted((float) (FCAconfig.passageOverCurse.get() * 100))
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.passage_curse",
                    (int) (FCAconfig.passageOverCurse.get() * 100)
                    ).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
            //铁墓
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.iron_tomb"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x404040))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.iron_tomb_text",
                    (int) (FCAconfig.ironTombLevel.get() * 1),
                    (float) (FCAconfig.ironTombDamageDown.get() * 100),
                    (float) (FCAconfig.ironTombDamageUp.get() * 100)
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xC00000))));
        }
        if( !(Screen.hasShiftDown()||Screen.hasAltDown()) ){
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.text" )
                    .withStyle(style -> style.withColor(TextColor.fromRgb(0xFFFFFF))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.text1" )
                    .withStyle(style -> style.withColor(TextColor.fromRgb(0xFFFFFF))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.text2" )
                    .withStyle(style -> style.withColor(TextColor.fromRgb(0xFFFFFF))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.text3" )
                    .withStyle(style -> style.withColor(TextColor.fromRgb(0xFFFFFF))));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
    //提升抢夺等级
    @Override
    public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting, ItemStack stack) {
        return (int)(FCAconfig.reasonCuriosLooting.get()*1) ;
    }
    //提升时运等级
    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        return (int)(FCAconfig.reasonCuriosFortune.get()*1) ;
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
        //增加属性的例子
        atts.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "origin_sin_world_bearing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "origin_sin_world_bearing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(Attributes.ARMOR, new AttributeModifier(uuid, "origin_sin_world_bearing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "origin_sin_world_bearing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "origin_sin_world_bearing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "origin_sin_world_bearing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "origin_sin_world_bearing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(Attributes.LUCK, new AttributeModifier(uuid, "origin_sin_world_bearing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(ForgeMod.STEP_HEIGHT_ADDITION.get(), new AttributeModifier(uuid, "world_bearing_blessing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, "world_bearing_blessing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        return atts;
    }
    public boolean canUnequip(SlotContext context, ItemStack stack) {
        LivingEntity var4 = context.entity();
        if (var4 instanceof Player player) {
            if (player.isCreative()) {
                return ICurioItem.super.canUnequip(context, stack);
            }
        }

        return false;
    }
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips, ItemStack stack) {
        return Collections.emptyList();
    }
}
