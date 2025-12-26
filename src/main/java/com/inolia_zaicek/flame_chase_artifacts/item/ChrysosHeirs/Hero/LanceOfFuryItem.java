package com.inolia_zaicek.flame_chase_artifacts.item.ChrysosHeirs.Hero;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
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
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class LanceOfFuryItem extends Item implements ICurioItem {
    public LanceOfFuryItem() {
        super((new Properties()).stacksTo(1).fireResistant());
    }
    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }
    protected int getTooltipShiftColor() {
        return 0x7BEBFC; // 返回你想要的RGB 值
    }
    @NotNull
    public ICurio.@NotNull DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
        //增加属性的例子
        atts.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, this.getTooltipItemName(), 0.5, AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(Attributes.ARMOR, new AttributeModifier(uuid, this.getTooltipItemName(), -1, AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(FCAAttributes.HEAL_AMPLIFIER.get(), new AttributeModifier(uuid, this.getTooltipItemName(), 0.75, AttributeModifier.Operation.ADDITION));
        atts.put(FCAAttributes.DAMAGE_AMPLIFIER.get(), new AttributeModifier(uuid, this.getTooltipItemName(), 0.3, AttributeModifier.Operation.ADDITION));
        atts.put(FCAAttributes.DAMAGE_REDUCTION.get(), new AttributeModifier(uuid, this.getTooltipItemName(), 0.15, AttributeModifier.Operation.ADDITION));
        return atts;
    }
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.lance_of_fury.shift_text"
            ).withStyle(style -> style.withColor(ChatFormatting.GOLD)));
        }else{
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.lance_of_fury.text"
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x7BEBFC))));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips, ItemStack stack) {
        return Collections.emptyList();
    }
}