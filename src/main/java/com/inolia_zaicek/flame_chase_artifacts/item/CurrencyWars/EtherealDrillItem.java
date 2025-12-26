package com.inolia_zaicek.flame_chase_artifacts.item.CurrencyWars;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.inolia_zaicek.flame_chase_artifacts.item.CuriosTootip1Item;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class EtherealDrillItem extends CuriosTootip1Item {
    //ForgeMod.BLOCK_REACH.get()
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
        atts.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, this.getTooltipItemName(), 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
        return atts;
    }
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips, ItemStack stack) {
        return Collections.emptyList();
    }
}
