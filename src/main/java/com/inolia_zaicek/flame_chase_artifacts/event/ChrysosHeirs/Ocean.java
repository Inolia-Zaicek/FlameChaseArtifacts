package com.inolia_zaicek.flame_chase_artifacts.event.ChrysosHeirs;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAEntityHelper;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;
import java.util.Random;

@SuppressWarnings({"all", "removal"})
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = FlameChaseArtifacts.MODID)
public class Ocean {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //挨打
        if (event.getSource().getEntity() instanceof LivingEntity mob) {
            if (FCAUtil.isCurioEquipped(event.getEntity(), FCAItemRegister.OriginSin.get())
                    || FCAUtil.isCurioEquipped(event.getEntity(), FCAItemRegister.OceanCurios.get())) {
                var map = mob.getActiveEffectsMap();
                Random random = new Random();
                int debuffNumber = 0;
                if (random.nextInt(40) < 10) {
                    mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    if (!mob.hasEffect(MobEffects.WEAKNESS)) {
                        map.put(MobEffects.WEAKNESS, new MobEffectInstance(MobEffects.WEAKNESS, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    }
                } else if (random.nextInt(40) < 20 && random.nextInt(40) >= 10) {
                    mob.addEffect(new MobEffectInstance(MobEffects.POISON, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    if (!mob.hasEffect(MobEffects.POISON)) {
                        map.put(MobEffects.POISON, new MobEffectInstance(MobEffects.POISON, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    }
                } else if (random.nextInt(40) < 30 && random.nextInt(40) >= 20) {
                    mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    if (!mob.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                        map.put(MobEffects.MOVEMENT_SLOWDOWN, new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    }
                } else if (random.nextInt(40) >= 30) {
                    mob.addEffect(new MobEffectInstance(MobEffects.WITHER, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    if (!mob.hasEffect(MobEffects.WITHER)) {
                        map.put(MobEffects.WITHER, new MobEffectInstance(MobEffects.WITHER, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    }
                }
            }
        } else if (event.getSource().getDirectEntity() instanceof LivingEntity mob) {
            if (FCAUtil.isCurioEquipped(event.getEntity(), FCAItemRegister.OriginSin.get())
                    || FCAUtil.isCurioEquipped(event.getEntity(), FCAItemRegister.OceanCurios.get())) {
                var map = mob.getActiveEffectsMap();
                Random random = new Random();
                int debuffNumber = 0;
                if (random.nextInt(40) < 10) {
                    mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    if (!mob.hasEffect(MobEffects.WEAKNESS)) {
                        map.put(MobEffects.WEAKNESS, new MobEffectInstance(MobEffects.WEAKNESS, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    }
                } else if (random.nextInt(40) < 20 && random.nextInt(40) >= 10) {
                    mob.addEffect(new MobEffectInstance(MobEffects.POISON, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    if (!mob.hasEffect(MobEffects.POISON)) {
                        map.put(MobEffects.POISON, new MobEffectInstance(MobEffects.POISON, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    }
                } else if (random.nextInt(40) < 30 && random.nextInt(40) >= 20) {
                    mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    if (!mob.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                        map.put(MobEffects.MOVEMENT_SLOWDOWN, new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    }
                } else if (random.nextInt(40) >= 30) {
                    mob.addEffect(new MobEffectInstance(MobEffects.WITHER, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    if (!mob.hasEffect(MobEffects.WITHER)) {
                        map.put(MobEffects.WITHER, new MobEffectInstance(MobEffects.WITHER, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    }
                }
            }
        }
        //攻击
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())
                    || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OceanCurios.get())) {
                LivingEntity mob = event.getEntity();
                var map = mob.getActiveEffectsMap();
                Random random = new Random();
                int debuffNumber = 0;
                if (random.nextInt(40) < 10) {
                    mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    if (!mob.hasEffect(MobEffects.WEAKNESS)) {
                        map.put(MobEffects.WEAKNESS, new MobEffectInstance(MobEffects.WEAKNESS, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    }
                } else if (random.nextInt(40) < 20 && random.nextInt(40) >= 10) {
                    mob.addEffect(new MobEffectInstance(MobEffects.POISON, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    if (!mob.hasEffect(MobEffects.POISON)) {
                        map.put(MobEffects.POISON, new MobEffectInstance(MobEffects.POISON, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    }
                } else if (random.nextInt(40) < 30 && random.nextInt(40) >= 20) {
                    mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    if (!mob.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                        map.put(MobEffects.MOVEMENT_SLOWDOWN, new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    }
                } else if (random.nextInt(40) >= 30) {
                    mob.addEffect(new MobEffectInstance(MobEffects.WITHER, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    if (!mob.hasEffect(MobEffects.WITHER)) {
                        map.put(MobEffects.WITHER, new MobEffectInstance(MobEffects.WITHER, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    }
                }
            }
        } else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())
                    || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OceanCurios.get())) {
                LivingEntity mob = event.getEntity();
                var map = mob.getActiveEffectsMap();
                Random random = new Random();
                int debuffNumber = 0;
                if (random.nextInt(40) < 10) {
                    mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    if (!mob.hasEffect(MobEffects.WEAKNESS)) {
                        map.put(MobEffects.WEAKNESS, new MobEffectInstance(MobEffects.WEAKNESS, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    }
                } else if (random.nextInt(40) < 20 && random.nextInt(40) >= 10) {
                    mob.addEffect(new MobEffectInstance(MobEffects.POISON, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    if (!mob.hasEffect(MobEffects.POISON)) {
                        map.put(MobEffects.POISON, new MobEffectInstance(MobEffects.POISON, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    }
                } else if (random.nextInt(40) < 30 && random.nextInt(40) >= 20) {
                    mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    if (!mob.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                        map.put(MobEffects.MOVEMENT_SLOWDOWN, new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    }
                } else if (random.nextInt(40) >= 30) {
                    mob.addEffect(new MobEffectInstance(MobEffects.WITHER, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    if (!mob.hasEffect(MobEffects.WITHER)) {
                        map.put(MobEffects.WITHER, new MobEffectInstance(MobEffects.WITHER, (int) (FCAconfig.oceanCurios.get() * 20), 1));
                    }
                }
            }
        }
    }
}
