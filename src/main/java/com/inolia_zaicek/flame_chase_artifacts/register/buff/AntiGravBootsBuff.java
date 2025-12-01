package com.inolia_zaicek.flame_chase_artifacts.register.buff;

import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class AntiGravBootsBuff extends MobEffect {
    private static final String UUID_CU = "F8D4D0E7-5F6A-2819-A681-DFA4D4AFE074";
    private static final double CU = 0.1;
    public AntiGravBootsBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, UUID_CU, CU, AttributeModifier.Operation.MULTIPLY_BASE);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, UUID_CU, CU, AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
