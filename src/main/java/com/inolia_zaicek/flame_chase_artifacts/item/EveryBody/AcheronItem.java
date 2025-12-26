package com.inolia_zaicek.flame_chase_artifacts.item.EveryBody;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class AcheronItem extends Item implements ICurioItem {
    public AcheronItem() {
        super((new Properties()).stacksTo(1).fireResistant());
    }
    @NotNull
    public ICurio.@NotNull DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.acheron.shift_text"
            ).withStyle(style -> style.withColor(ChatFormatting.WHITE)));
        }else{
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.acheron.text"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x7BEBFC))));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips, ItemStack stack) {
        return Collections.emptyList();
    }
}
