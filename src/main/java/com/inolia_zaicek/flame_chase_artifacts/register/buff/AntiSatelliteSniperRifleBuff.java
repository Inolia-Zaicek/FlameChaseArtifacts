package com.inolia_zaicek.flame_chase_artifacts.register.buff;

import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class AntiSatelliteSniperRifleBuff extends MobEffect {
    private static final String UUID_CU = "36A126C7-9457-C653-CF2B-373C62B1C2E2";
    private static final double CU = 0.1;
    public AntiSatelliteSniperRifleBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(FCAAttributes.LUCK_HIT_AMPLIFIER.get(), UUID_CU, CU, AttributeModifier.Operation.ADDITION);
    }
}
