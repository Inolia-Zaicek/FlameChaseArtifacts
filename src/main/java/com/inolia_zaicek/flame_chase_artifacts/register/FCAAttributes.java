package com.inolia_zaicek.flame_chase_artifacts.register;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FCAAttributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, FlameChaseArtifacts.MODID);
    //伤害
    public static final RegistryObject<Attribute> DAMAGE_AMPLIFIER = ATTRIBUTES.register("damage_amplifier",
            () -> new RangedAttribute(FlameChaseArtifacts.MODID + ".damage_amplifier",
                    0,//基础值
                    0,//最小值
                    10000//最大值
            ).setSyncable(true));

    public static final RegistryObject<Attribute> DAMAGE_REDUCTION = ATTRIBUTES.register("damage_reduction",
            () -> new RangedAttribute(FlameChaseArtifacts.MODID + ".damage_reduction",
                    0,//基础值
                    0,//最小值
                    10000//最大值
            ).setSyncable(true));
    //幸运一击
    public static final RegistryObject<Attribute> LUCK_HIT_CHANCE = ATTRIBUTES.register("luck_hit_chance",
            () -> new RangedAttribute(FlameChaseArtifacts.MODID + ".luck_hit_chance",
                    0,//基础值
                    0,//最小值
                    100//最大值
            ).setSyncable(true));
    public static final RegistryObject<Attribute> LUCK_HIT_AMPLIFIER = ATTRIBUTES.register("luck_hit_amplifier",
            () -> new RangedAttribute(FlameChaseArtifacts.MODID + ".luck_hit_amplifier",
                    0.5,//基础值
                    0,//最小值
                    1000//最大值
            ).setSyncable(true));
    //治疗量
    public static final RegistryObject<Attribute> HEAL_AMPLIFIER = ATTRIBUTES.register("heal_amplifier",
            () -> new RangedAttribute(FlameChaseArtifacts.MODID + ".heal_amplifier",
                    0,//基础值
                    0,//最小值
                    10000//最大值
            ).setSyncable(true));
    //击破效率
    public static final RegistryObject<Attribute> Break_Efficiency = ATTRIBUTES.register("break_efficiency",
            () -> new RangedAttribute(FlameChaseArtifacts.MODID + ".break_efficiency",
                    1,//基础值
                    0,//最小值
                    10000//最大值
            ).setSyncable(true));
    //击破伤害倍率
    public static final RegistryObject<Attribute> Break_Damage = ATTRIBUTES.register("break_damage",
            () -> new RangedAttribute(FlameChaseArtifacts.MODID + ".break_damage",
                    0.25,//基础值
                    0,//最小值
                    10000//最大值
            ).setSyncable(true));
    //超击破倍率
    public static final RegistryObject<Attribute> Break_Amplifier = ATTRIBUTES.register("break_amplifier",
            () -> new RangedAttribute(FlameChaseArtifacts.MODID + ".break_amplifier",
                    0.1,//基础值
                    0,//最小值
                    10000//最大值
            ).setSyncable(true));
    //击破时长——基础5秒
    public static final RegistryObject<Attribute> Break_Time = ATTRIBUTES.register("break_time",
            () -> new RangedAttribute(FlameChaseArtifacts.MODID + ".break_time",
                    5*20,//基础值
                    0,//最小值
                    10000//最大值
            ).setSyncable(true));
}