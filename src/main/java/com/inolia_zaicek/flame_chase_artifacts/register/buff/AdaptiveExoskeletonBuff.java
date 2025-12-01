package com.inolia_zaicek.flame_chase_artifacts.register.buff;

import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class AdaptiveExoskeletonBuff extends MobEffect {
    private static final String UUID_CU = "A42ADF39-2F9C-0F27-0B7C-437637B007A5";
    private static final double CU = 0.2;
    public AdaptiveExoskeletonBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(FCAAttributes.DAMAGE_AMPLIFIER.get(), UUID_CU, CU, AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, UUID_CU, CU, AttributeModifier.Operation.ADDITION);
    }
}
