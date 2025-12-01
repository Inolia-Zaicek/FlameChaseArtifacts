package com.inolia_zaicek.flame_chase_artifacts.register.buff;

import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class VeryHardArmorBuff extends MobEffect {
    private static final String UUID_CU = "EF5E1704-00D6-09B6-3FFF-2470AC98A928";
    private static final double CU = 0.18;
    public VeryHardArmorBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(FCAAttributes.DAMAGE_REDUCTION.get(), UUID_CU, CU, AttributeModifier.Operation.ADDITION);
    }
}
