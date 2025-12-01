package com.inolia_zaicek.flame_chase_artifacts.damage;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import static com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts.MODID;


//source的create方法
public class FCADamageType {
    private static ResourceKey<DamageType> create(String name){
        return ResourceKey.create(Registries.DAMAGE_TYPE,new ResourceLocation(MODID,name));
    }
    public FCADamageType(){}
    //生成source
    public static DamageSource source(Level level, ResourceKey<DamageType> type, @Nullable Entity direct, @Nullable Entity causing){
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type), direct,causing);
    }
    //有源
    public static DamageSource hasSource(Level level, ResourceKey<DamageType> type, @Nullable Entity entity){
        return source(level,type,entity, entity);
    }
    //无源
    public static DamageSource source(Level level, ResourceKey<DamageType> type){
        return source(level,type,null, null);
    }
    //DamageType注册
    //注册项（type和tag是两个东西【参考注册物品就行
    public static final TagKey<DamageType> witch_resistant_to = TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("witch_resistant_to"));
    public static final TagKey<DamageType> mc_witch_resistant_to = TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("minecraft","witch_resistant_to"));
    //真伤
    public static final ResourceKey<DamageType> TRUEDAMAGE = create("truedamage");
    //dot
    public static final ResourceKey<DamageType> DOTDAMAGE = create("dotdamage");
    //不造成无敌帧or无视无敌帧（物理伤害
    public static final ResourceKey<DamageType> TICKAMAGE = create("tickdamage");
    //不造成无敌帧or无视无敌帧的法伤
    public static final ResourceKey<DamageType> TICKMAMAGE = create("tickmdamage");
    //不造成无敌帧or无视无敌帧的火-冰-雷-毒-凋零
    public static final ResourceKey<DamageType> TickFireDamage = create("tick_fire_damage");
    public static final ResourceKey<DamageType> TickFreezeDamage = create("tick_freeze_damage");
    public static final ResourceKey<DamageType> TickLightningDamage = create("tick_lightning_damage");
    public static final ResourceKey<DamageType> TickPoisonDamage = create("tick_poison_damage");
    public static final ResourceKey<DamageType> TickWitherDamage = create("tick_wither_damage");
    }
