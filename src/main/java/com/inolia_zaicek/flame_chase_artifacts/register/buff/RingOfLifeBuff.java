package com.inolia_zaicek.flame_chase_artifacts.register.buff;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class RingOfLifeBuff extends MobEffect {
    private static final String UUID_CU = "7B77D84D-F195-E3E7-9E62-9BE44CF08F34";
    private static final double CU = 0.15;
    public RingOfLifeBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.MAX_HEALTH, UUID_CU, CU, AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
