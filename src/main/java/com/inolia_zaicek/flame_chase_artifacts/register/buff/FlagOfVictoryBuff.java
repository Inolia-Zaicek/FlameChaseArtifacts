package com.inolia_zaicek.flame_chase_artifacts.register.buff;

import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class FlagOfVictoryBuff extends MobEffect {
    private static final String UUID_CU = "0C4D2745-F1F3-DA06-A893-832E4C1934BF";
    private static final double CU = 0.12;
    public FlagOfVictoryBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(FCAAttributes.LUCK_HIT_CHANCE.get(), UUID_CU, CU, AttributeModifier.Operation.ADDITION);
    }
}
