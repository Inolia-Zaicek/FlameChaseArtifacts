package com.inolia_zaicek.flame_chase_artifacts.register.buff;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class AfterRainBuff extends MobEffect {
    private static final String UUID_CU = "20EA6BE5-956E-D99D-0EF6-FB4973FC9CD3";
    private static final double CU = 0.75;
    private static final double CU1 = 28;
    public AfterRainBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.MAX_HEALTH, UUID_CU, CU1, AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(Attributes.MAX_HEALTH, UUID_CU, CU, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
