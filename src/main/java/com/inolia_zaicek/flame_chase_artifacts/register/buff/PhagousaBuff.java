package com.inolia_zaicek.flame_chase_artifacts.register.buff;

import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class PhagousaBuff extends MobEffect {
    private static final String UUID_CU = "A42ADF39-2F9C-0F27-0B7C-437637B007A5";
    private static final double CU = 0.2;
    public PhagousaBuff() {
        super(MobEffectCategory.HARMFUL, 0);
    }
}
