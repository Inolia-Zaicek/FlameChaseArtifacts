package com.inolia_zaicek.flame_chase_artifacts.register.buff;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ToughnessBreakBuff extends MobEffect {
    private static final String UUID_CU = "19D65106-D661-C66F-44F5-E0FEE12B1602";
    private static final double CU = -1;
    public ToughnessBreakBuff() {
        super(MobEffectCategory.NEUTRAL, 0);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, UUID_CU, CU, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, UUID_CU, CU, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, UUID_CU, CU, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.FLYING_SPEED, UUID_CU, CU, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
    //治疗物品
    @Override
    public List<ItemStack> getCurativeItems() {
        //治疗物品组
        ArrayList<ItemStack> ret = new ArrayList<>();
        //把牛奶删了ret.remove(new ItemStack(Items.MILK_BUCKET));
        //ret.remove(new ItemStack(Items.MILK_BUCKET));
        //返回空的数组，啥都没有，没法牛奶解除
        return ret;
    }
}
