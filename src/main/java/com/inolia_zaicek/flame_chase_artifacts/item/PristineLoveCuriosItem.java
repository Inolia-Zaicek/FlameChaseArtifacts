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
public class PristineLoveCuriosItem extends Item implements ICurioItem {
    public PristineLoveCuriosItem() {
        super((new Properties()).stacksTo(1).fireResistant().rarity(Rarity.EPIC));
    }
    //死亡不掉落
    @NotNull
    public ICurio.@NotNull DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }    // 鼠标经过物品时显示文字，按下shift键后显示另一行提示文字
    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        var bookKey = "book_get_nbt";
        if(Minecraft.getInstance().player!=null && ! Minecraft.getInstance().player.getPersistentData().getBoolean(bookKey) ){
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.get_text").withStyle(ChatFormatting.DARK_GRAY));
        }
        //alt——介绍特殊能力
        if(Screen.hasAltDown()) {
            //天空
            if(Minecraft.getInstance().player!=null&&FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.SkyCurios.get()) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_sky_awaking_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x5AFFC9))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_sky_blessing",
                        (int) (FCAconfig.skyEgoCurios.get() * 1) ,(int) (FCAconfig.skyEgoCurios.get() * 1)
                        ,(int) (FCAconfig.skyEgoCuriosRegenerationLevel.get() * 1),(int) (FCAconfig.skyEgoCuriosSpeedLevel.get() * 1)
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x5AFFC9))));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_sky_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x5AFFC9))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_sky_blessing",
                        (int) (FCAconfig.skyEgoCurios.get() * 1) ,(int) (FCAconfig.skyEgoCurios.get() * 1)
                        ,(int) (FCAconfig.skyEgoCuriosRegenerationLevel.get() * 1),(int) (FCAconfig.skyEgoCuriosSpeedLevel.get() * 1)
                ).withStyle(ChatFormatting.GRAY));
            }
            //大地
            if(Minecraft.getInstance().player!=null&&FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.EarthCurios.get()) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_earth_awaking_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFAE241))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_earth_blessing",
                        (int) (FCAconfig.earthEgoCurios.get() * 1),(int) (FCAconfig.earthEgoCurios.get() * 1)
                ,(int) (FCAconfig.earthEgoCuriosPowerLevel.get() * 1),(int) (FCAconfig.earthEgoCuriosReLevel.get() * 1)
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFAE241))));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_earth_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFAE241))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_earth_blessing",
                        (int) (FCAconfig.earthEgoCurios.get() * 1),(int) (FCAconfig.earthEgoCurios.get() * 1)
                        ,(int) (FCAconfig.earthEgoCuriosPowerLevel.get() * 1),(int) (FCAconfig.earthEgoCuriosReLevel.get() * 1)
                ).withStyle(ChatFormatting.GRAY));
            }
            //海洋
            if(Minecraft.getInstance().player!=null&&FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.OceanCurios.get()) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_ocean_awaking_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x647EFF))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_ocean_blessing",
                        (float) (FCAconfig.oceanEgoCurios.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0x647EFF))));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_ocean_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x647EFF))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_ocean_blessing",
                        (float) (FCAconfig.oceanEgoCurios.get() * 100)                ).withStyle(ChatFormatting.GRAY));
            }
            //浪漫
            if(Minecraft.getInstance().player!=null&&FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.RomanceCurios.get()) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_romance_awaking_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFFFF3C))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_romance_blessing",
                        (float) (FCAconfig.romanceEgoCuriosNumber.get() * 100),(int) (FCAconfig.romanceEgoCuriosTime.get() * 1)).withStyle(style -> style.withColor(TextColor.fromRgb(0xFFFF3C))));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_romance_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFFFF3C))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_romance_blessing",
                        (float) (FCAconfig.romanceEgoCuriosNumber.get() * 100),(int) (FCAconfig.romanceEgoCuriosTime.get() * 1)                ).withStyle(ChatFormatting.GRAY));
            }
            //负世
            if(Minecraft.getInstance().player!=null&&FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.WorldBearingCurios.get()) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_worldbearing_awaking_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x7BEBFC))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_worldbearing_blessing",
                        (float) (FCAconfig.worldbearingEgoCurios.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0x7BEBFC))));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_worldbearing_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x7BEBFC))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_worldbearing_blessing",
                        (float) (FCAconfig.worldbearingEgoCurios.get() * 100)                ).withStyle(ChatFormatting.GRAY));
            }
            //理性
            if(Minecraft.getInstance().player!=null&&FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.ReasonCurios.get()) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_reason_awaking_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x00FF55))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_reason_blessing",
                        (int) (FCAconfig.reasonEgoCurios.get() * 1)).withStyle(style -> style.withColor(TextColor.fromRgb(0x00FF55))));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_reason_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x00FF55))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_reason_blessing",
                        (int) (FCAconfig.reasonEgoCurios.get() * 1)                ).withStyle(ChatFormatting.GRAY));
            }
            //诡计
            if(Minecraft.getInstance().player!=null&&FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.TrickeryCurios.get()) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_trickery_awaking_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x4B29F5))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_trickery_blessing",
                        (float) (FCAconfig.trickeryEgoCurios.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0x4B29F5))));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_trickery_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x4B29F5))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_trickery_blessing",
                        (float) (FCAconfig.trickeryEgoCurios.get() * 100)                ).withStyle(ChatFormatting.GRAY));
            }
            //纷争
            if(Minecraft.getInstance().player!=null&&FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.StrifeCurios.get()) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_strife_awaking_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xF95928))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_strife_blessing",
                        (float) (FCAconfig.strifeEgoCurios.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xF95928))));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_strife_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xF95928))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_strife_blessing",
                        (float) (FCAconfig.strifeEgoCurios.get() * 100)                ).withStyle(ChatFormatting.GRAY));
            }
            //死亡
            if(Minecraft.getInstance().player!=null&&FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.DeathCurios.get()) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_death_awaking_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xA951FF))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_death_blessing",
                        (int) (FCAconfig.deathEgoCurios.get() * 1)).withStyle(style -> style.withColor(TextColor.fromRgb(0xA951FF))));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_death_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xA951FF))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_death_blessing",
                        (int) (FCAconfig.deathEgoCurios.get() * 1)                ).withStyle(ChatFormatting.GRAY));
            }
            //岁月
            if(Minecraft.getInstance().player!=null&&FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.TimeCurios.get()) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_time_awaking_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFFA4E4))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_time_blessing",
                        (float) (FCAconfig.timeEgoCurios.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xFFA4E4))));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_time_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFFA4E4))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_time_blessing",
                        (float) (FCAconfig.timeEgoCurios.get() * 100)                ).withStyle(ChatFormatting.GRAY));
            }
            //律法
            if(Minecraft.getInstance().player!=null&&FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.LawCurios.get()) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_law_awaking_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x37F3FF))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_law_blessing",
                        (float) (FCAconfig.lawEgoCurios.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0x37F3FF))));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_law_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0x37F3FF))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_law_blessing",
                        (float) (FCAconfig.lawEgoCurios.get() * 100)                ).withStyle(ChatFormatting.GRAY));
            }
            //门径
            if(Minecraft.getInstance().player!=null&&FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.PassageCurios.get()) ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_passage_awaking_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFF1E1E))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_passage_blessing",
                        (float) (FCAconfig.passageEgoCurios.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xFF1E1E))));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_passage_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFF1E1E))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_passage_blessing",
                        (float) (FCAconfig.passageEgoCurios.get() * 100)                ).withStyle(ChatFormatting.GRAY));
            }
            //真我
            if(Minecraft.getInstance().player!=null
                    && FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.SkyCurios.get())
                    && FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.EarthCurios.get())
                    && FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.OceanCurios.get())
                    && FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.RomanceCurios.get())
                    && FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.WorldBearingCurios.get())
                    && FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.ReasonCurios.get())
                    && FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.TrickeryCurios.get())
                    && FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.StrifeCurios.get())
                    && FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.DeathCurios.get())
                    && FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.TimeCurios.get())
                    && FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.LawCurios.get())
                    && FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.PassageCurios.get())
            ) {
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_ego_awaking_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFB61FF))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_ego_blessing",
                        (int) (FCAconfig.egoCuriosRange.get() * 1),(int) (FCAconfig.egoCuriosRange.get() * 1),(float) (FCAconfig.egoCuriosAttack.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xFB61FF))));
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_ego_text"
                ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFB61FF))));
                pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_ego_blessing",
                        (int) (FCAconfig.egoCuriosRange.get() * 1),(int) (FCAconfig.egoCuriosRange.get() * 1) , (float) (FCAconfig.egoCuriosAttack.get() * 100)                ).withStyle(ChatFormatting.GRAY));
            }
        }

        //shift————祝福
        if(Screen.hasShiftDown()) {
            //天空
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.sky_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x5AFFC9))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.sky_curse",
                    (int) (FCAconfig.skyBlessing.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0x5AFFC9))));
            //大地
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.earth_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFAE241))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.earth_curse",
                    (int) (FCAconfig.earthBlessing.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xFAE241))));
            //海洋
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ocean_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x647EFF))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.ocean_curse",
                    (float) (FCAconfig.oceanBlessing.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0x647EFF))));
            //浪漫
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.romance_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFFFF3C))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.romance_curse",
                    (float) (FCAconfig.romanceBlessing.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xFFFF3C))));
            //负世
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.worldbearing_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x7BEBFC))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.worldbearing_curse",
                    (float) (FCAconfig.worldbearingBlessing.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0x7BEBFC))));
            //理性
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.reason_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x00FF55))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.reason_curse",
                    (int) (FCAconfig.reasonBlessing.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0x00FF55))));
            //诡计
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.trickery_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x4B29F5))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.trickery_curse",
                    (float) (FCAconfig.trickeryBlessing.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0x4B29F5))));
            //纷争
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.strife_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xF95928))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.strife_curse",
                    (float) (FCAconfig.strifeBlessing.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xF95928))));
            //死亡
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.death_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xA951FF))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.death_blessing",
                    (int) (FCAconfig.deathBlessing.get() * 1)).withStyle(style -> style.withColor(TextColor.fromRgb(0xA951FF))));
            //岁月
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.time_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFFA4E4))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.time_curse",
                    (float) (FCAconfig.timeBlessing.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0xFFA4E4))));
            //律法
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.law_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x37F3FF))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.law_curse",
                    (float) (FCAconfig.lawBlessing.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0x37F3FF))));
            //门径
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.passage_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFF1E1E))));
            //保留2位小数写法"%2.f".formatted((float) (FCAconfig.passageBlessing.get() * 100))
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.hatred_inundate.passage_blessing",
                    (int) (FCAconfig.passageBlessing.get() * 100)
                    ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFF1E1E))));
            //真我
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ego_titan"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFB61FF))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ego_text",
                    (int) (FCAconfig.egoCuriosNoSourseDamage.get() * 100),(int) (FCAconfig.egoCuriosHasSourseDamageDown.get() * 100)
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0xFB61FF))));
        }
        if( !(Screen.hasShiftDown()||Screen.hasAltDown()) ){
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.text" )
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
}
