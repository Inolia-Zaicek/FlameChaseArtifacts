package com.inolia_zaicek.flame_chase_artifacts.register.buff;

import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ElectromagneticCatapultBuff extends MobEffect {
    private static final String UUID_CU = "9FDD3AAC-8A59-9A36-1440-C8B321967849";
    private static final double CU = 0.2;
    public ElectromagneticCatapultBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(FCAAttributes.DAMAGE_REDUCTION.get(), UUID_CU, CU, AttributeModifier.Operation.ADDITION);
    }
}
