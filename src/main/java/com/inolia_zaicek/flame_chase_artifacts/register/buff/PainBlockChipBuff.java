package com.inolia_zaicek.flame_chase_artifacts.register.buff;

import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class PainBlockChipBuff extends MobEffect {
    private static final String UUID_CU = "5BAF86AE-1136-1EE7-6CF9-371CEB0DBCFD";
    private static final double CU = 0.15;
    public PainBlockChipBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(FCAAttributes.DAMAGE_REDUCTION.get(), UUID_CU, CU, AttributeModifier.Operation.ADDITION);
    }
}
