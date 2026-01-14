package com.inolia_zaicek.flame_chase_artifacts.event;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAEffectsRegister;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.inolia_zaicek.flame_chase_artifacts.event.HurtEvent.break_progress_NBT;

@Mod.EventBusSubscriber(modid = FlameChaseArtifacts.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ToughnessBreakEvent {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    //buff到时间
    public static void buffOut(MobEffectEvent.Expired event) {
        MobEffectInstance expiredInstance = event.getEffectInstance();
        if (expiredInstance != null) {
            MobEffect expiredEffect = expiredInstance.getEffect();
            LivingEntity livingEntity = event.getEntity();
            if (expiredEffect == FCAEffectsRegister.ToughnessBreak.get()) {
                CompoundTag persistentData = livingEntity.getPersistentData();
                persistentData.putBoolean("NoAI",false);
                if(livingEntity instanceof Mob mob){
                    mob.setNoAi(false);
                }
                //击破进度回复
                CompoundTag breakCompoundTag = livingEntity.getPersistentData();
                breakCompoundTag.putInt(break_progress_NBT, 0);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    //失去buff
    public static void buffOut(MobEffectEvent.Remove event) {
        MobEffectInstance expiredInstance = event.getEffectInstance();
        if (expiredInstance != null) {
            MobEffect expiredEffect = expiredInstance.getEffect();
            LivingEntity livingEntity = event.getEntity();
            if (expiredEffect == FCAEffectsRegister.ToughnessBreak.get()) {
                CompoundTag persistentData = livingEntity.getPersistentData();
                persistentData.putBoolean("NoAI",false);
                if(livingEntity instanceof Mob mob){
                    mob.setNoAi(false);
                }
                //击破进度回复
                CompoundTag breakCompoundTag = livingEntity.getPersistentData();
                breakCompoundTag.putInt(break_progress_NBT, 0);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    //获得buff
    public static void buffGet(MobEffectEvent.Added event) {
        MobEffectInstance expiredInstance = event.getEffectInstance();
        MobEffect expiredEffect = expiredInstance.getEffect();
        LivingEntity livingEntity = event.getEntity();
        if (expiredEffect == FCAEffectsRegister.ToughnessBreak.get()) {
            CompoundTag persistentData = livingEntity.getPersistentData();
            persistentData.putBoolean("NoAI",true);
            if(livingEntity instanceof Mob mob){
                mob.setNoAi(true);
            }
        }
    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    //tick持续赋予
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity.hasEffect(FCAEffectsRegister.ToughnessBreak.get())) {
            CompoundTag persistentData = livingEntity.getPersistentData();
            persistentData.putBoolean("NoAI",true);
            if(livingEntity instanceof Mob mob){
                mob.setNoAi(true);
            }
        }
    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity&&livingEntity.hasEffect(FCAEffectsRegister.ToughnessBreak.get())) {
            event.setAmount(0);
        }
        if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity&&livingEntity.hasEffect(FCAEffectsRegister.ToughnessBreak.get())) {
            event.setAmount(0);
        }
        if (event.getEntity().hasEffect(FCAEffectsRegister.ToughnessBreak.get())) {
            int buffLevel = event.getEntity().getEffect(FCAEffectsRegister.ToughnessBreak.get()).getAmplifier();
            event.setAmount(event.getAmount()*(1+buffLevel*0.1f));
        }
    }
}
