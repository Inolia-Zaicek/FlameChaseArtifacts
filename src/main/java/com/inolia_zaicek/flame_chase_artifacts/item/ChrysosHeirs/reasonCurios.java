package com.inolia_zaicek.flame_chase_artifacts.item.ChrysosHeirs;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.item.FCACuriosItem;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class reasonCurios extends FCACuriosItem {
    public reasonCurios() {
        super();
    }
    @Override
    protected int getTooltipShiftColor() {
        return 0x00FF55; // 返回你想要的RGB 值
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
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        var reasonKey = "reason_curios_get_nbt";
        if(Minecraft.getInstance().player!=null && ! Minecraft.getInstance().player.getPersistentData().getBoolean(reasonKey) ){
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.get_text".formatted(this.getTooltipItemName())
            ).withStyle(ChatFormatting.DARK_GRAY));
        }
        pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.up_text".formatted(this.getTooltipItemName()))
                .withStyle(style -> style.withColor(TextColor.fromRgb( this.getTooltipShiftColor() ))));

            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.text".formatted(this.getTooltipItemName()),
                            (int) (FCAconfig.reasonCuriosFortune.get() * 1),(int) (FCAconfig.reasonCuriosLooting.get() * 1))
                    .withStyle(ChatFormatting.GRAY));

        if(Minecraft.getInstance().player!=null&& FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.PristineLove.get()) ){

            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.ego_text".formatted(this.getTooltipItemName()))
                    .withStyle(style -> style.withColor(TextColor.fromRgb( this.getTooltipShiftColor() ))));

            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_reason_blessing",
                    (int) (FCAconfig.reasonEgoCurios.get() * 1)).withStyle(style -> style.withColor(TextColor.fromRgb(0x00FF55))));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}