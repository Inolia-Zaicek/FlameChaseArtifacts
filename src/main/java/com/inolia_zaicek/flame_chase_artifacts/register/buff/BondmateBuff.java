package com.inolia_zaicek.flame_chase_artifacts.register.buff;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BondmateBuff extends MobEffect {
    private static final String UUID_CU = "50135DBC-0700-1423-6D06-37C067B6FA47";
    private static final double CU = 0.6;
    public BondmateBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, UUID_CU, CU, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
