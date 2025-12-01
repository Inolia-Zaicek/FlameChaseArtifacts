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
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

@SuppressWarnings({"all", "removal"})
public class HatredInundateCuriosItem extends Item implements ICurioItem {
    public HatredInundateCuriosItem() {
        super((new Properties()).stacksTo(1).fireResistant().rarity(Rarity.EPIC));
    }
    //死亡不掉落
    @NotNull
    public ICurio.@NotNull DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }    // 鼠标经过物品时显示文字，按下shift键后显示另一行提示文字
    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()) {//按下shift键
            //天空
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.sky"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x5AFFC9))));
            if(Minecraft.getInstance().player!=null&&!FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.SkyCurios.get()) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.sky_curse", (int) (FCAconfig.skyCurse.get() * 100)
                ).withStyle(ChatFormatting.GRAY));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.sky_curse", (int) (FCAconfig.skyBlessing.get() * 100)
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x5AFFC9))));
            }
            //大地
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.earth"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFAE241))));
            if( (Minecraft.getInstance().player!=null&&!FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.EarthCurios.get()) ) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.earth_curse", (int) (FCAconfig.earthCurse.get() * 100)
                ).withStyle(ChatFormatting.GRAY));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.earth_curse", (int) (FCAconfig.earthBlessing.get() * 100)
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFAE241))));
            }
            //海洋
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.ocean"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x647EFF))));
            if( (Minecraft.getInstance().player!=null&&!FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.OceanCurios.get()) ) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.ocean_curse", (int) (FCAconfig.oceanCurse.get() * 100)
                ).withStyle(ChatFormatting.GRAY));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.ocean_curse", (int) (FCAconfig.oceanBlessing.get() * 100)
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x647EFF))));
            }
            //浪漫
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.romance"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFFFF3C))));
            if( (Minecraft.getInstance().player!=null&&!FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.RomanceCurios.get()) ) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.romance_curse", (int) (FCAconfig.romanceCurse.get() * 100)
                ).withStyle(ChatFormatting.GRAY));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.romance_curse", (int) (FCAconfig.romanceBlessing.get() * 100)
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFFFF3C))));
            }
            //负世
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.worldbearing"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x7BEBFC))));
            if( (Minecraft.getInstance().player!=null&&!FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.WorldBearingCurios.get()) ) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.worldbearing_curse", (int) (FCAconfig.worldbearingCurse.get() * 100)
                ).withStyle(ChatFormatting.GRAY));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.worldbearing_curse", (int) (FCAconfig.worldbearingBlessing.get() * 100)
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x7BEBFC))));
            }
            //理性
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.reason"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x00FF55))));
            if( (Minecraft.getInstance().player!=null&&!FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.ReasonCurios.get()) ) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.reason_curse", (int) (FCAconfig.reasonCurse.get() * 100)
                ).withStyle(ChatFormatting.GRAY));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.reason_curse", (int) (FCAconfig.reasonBlessing.get() * 100)
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x00FF55))));
            }
            //诡计
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.trickery"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x4B29F5))));
            if( (Minecraft.getInstance().player!=null&&!FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.TrickeryCurios.get()) ) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.trickery_curse", (int) (FCAconfig.trickeryCurse.get() * 100)
                ).withStyle(ChatFormatting.GRAY));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.trickery_curse", (int) (FCAconfig.trickeryBlessing.get() * 100)
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x4B29F5))));
            }
            //纷争
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.strife"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xF95928))));
            if( (Minecraft.getInstance().player!=null&&!FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.StrifeCurios.get()) ) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.strife_curse", (int) (FCAconfig.strifeCurse.get() * 100)
                ).withStyle(ChatFormatting.GRAY));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.strife_curse", (int) (FCAconfig.strifeBlessing.get() * 100)
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xF95928))));
            }
            //死亡
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.death"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xA951FF))));
            if( (Minecraft.getInstance().player!=null&&!FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.DeathCurios.get()) ) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.death_curse"
                ).withStyle(ChatFormatting.GRAY));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.death_blessing", (int) (FCAconfig.deathBlessing.get() * 1 )
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xA951FF))));
            }
            //岁月
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.time"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFFA4E4))));
            if( (Minecraft.getInstance().player!=null&&!FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.TimeCurios.get()) ) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.time_curse", (int) (FCAconfig.timeCurse.get() * 100)
                ).withStyle(ChatFormatting.GRAY));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.time_curse", (int) (FCAconfig.timeBlessing.get() * 100)
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFFA4E4))));
            }
            //律法
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.law"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x37F3FF))));
            if( (Minecraft.getInstance().player!=null&&!FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.LawCurios.get()) ) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.law_curse", (int) (FCAconfig.lawCurse.get() * 100)
                ).withStyle(ChatFormatting.GRAY));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.law_curse", (int) (FCAconfig.lawBlessing.get() * 100)
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x37F3FF))));
            }
            //门径
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.passage"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFF1E1E))));
            if( (Minecraft.getInstance().player!=null&&!FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.PassageCurios.get()) ) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.passage_curse", (int) (FCAconfig.passageCurse.get() * 100)
                ).withStyle(ChatFormatting.GRAY));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.passage_blessing", (int) (FCAconfig.passageBlessing.get() * 100)
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFF1E1E))));
            }
        }else{
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.text" )
                    .withStyle(style -> style.withColor(TextColor.fromRgb(0xFFFFFF))));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
        //增加属性的例子
        ///atts.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "speed_bonus", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
        //chrysos_heirs_curios，12个。
        CuriosApi.getCuriosHelper().addSlotModifier(atts, "chrysos_heirs_curios", uuid, 12.0, AttributeModifier.Operation.ADDITION);
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
}
