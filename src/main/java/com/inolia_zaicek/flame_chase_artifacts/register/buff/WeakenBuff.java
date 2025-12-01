package com.inolia_zaicek.flame_chase_artifacts.register.buff;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class WeakenBuff extends MobEffect {
    public WeakenBuff() {
        super(MobEffectCategory.HARMFUL, 0);
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
