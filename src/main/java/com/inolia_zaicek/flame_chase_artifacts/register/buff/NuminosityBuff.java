package com.inolia_zaicek.flame_chase_artifacts.register.buff;

import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class NuminosityBuff extends MobEffect {
    private static final String UUID_CU = "3E419DCC-27FA-700E-81E8-FF3EC4C3C8F7";
    private static final double CU = 0.15;
    public NuminosityBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(FCAAttributes.DAMAGE_AMPLIFIER.get(), UUID_CU, CU, AttributeModifier.Operation.ADDITION);
    }
}
