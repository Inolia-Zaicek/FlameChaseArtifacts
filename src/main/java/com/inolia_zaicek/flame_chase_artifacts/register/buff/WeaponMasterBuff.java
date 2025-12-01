package com.inolia_zaicek.flame_chase_artifacts.register.buff;

import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class WeaponMasterBuff extends MobEffect {
    private static final String UUID_CU = "4FBA2D90-A33A-5D4A-5047-8BB27CBBC746";
    private static final double CU = 0.25;
    public WeaponMasterBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(FCAAttributes.DAMAGE_AMPLIFIER.get(), UUID_CU, CU, AttributeModifier.Operation.ADDITION);
    }
}
