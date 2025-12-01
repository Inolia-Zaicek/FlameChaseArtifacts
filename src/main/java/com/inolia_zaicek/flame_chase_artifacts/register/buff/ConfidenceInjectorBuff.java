package com.inolia_zaicek.flame_chase_artifacts.register.buff;

import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ConfidenceInjectorBuff extends MobEffect {
    private static final String UUID_CU = "19894997-D282-64DA-9B99-B46F55755F87";
    private static final double CU = 0.25;
    private static final double CU1 = 0.1;
    public ConfidenceInjectorBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(FCAAttributes.DAMAGE_AMPLIFIER.get(), UUID_CU, CU, AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(FCAAttributes.DAMAGE_REDUCTION.get(), UUID_CU, CU1, AttributeModifier.Operation.ADDITION);
    }
}
