package com.inolia_zaicek.flame_chase_artifacts.item.CurrencyWars;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.inolia_zaicek.flame_chase_artifacts.item.CuriosTootip4Item;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import top.theillusivec4.curios.api.SlotContext;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class BeheadingOperation extends CuriosTootip4Item {
    //
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
        atts.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, this.getTooltipItemName(), 0.25, AttributeModifier.Operation.MULTIPLY_BASE));
        atts.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, this.getTooltipItemName(), 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
        atts.put(FCAAttributes.DAMAGE_AMPLIFIER.get(), new AttributeModifier(uuid, this.getTooltipItemName(), 0.2, AttributeModifier.Operation.ADDITION));
        return atts;
    }
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips, ItemStack stack) {
        return Collections.emptyList();
    }
}
