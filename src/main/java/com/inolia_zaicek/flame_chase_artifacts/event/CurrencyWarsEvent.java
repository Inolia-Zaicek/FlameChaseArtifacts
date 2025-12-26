package com.inolia_zaicek.flame_chase_artifacts.event;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAEffectsRegister;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = FlameChaseArtifacts.MODID)
public class CurrencyWarsEvent {
    private static final String permansor_terrae_TIME_NBT = FlameChaseArtifacts.MODID + ":permansor_terrae_time";
    //货币战争
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        LivingEntity attacked = event.getEntity();
        //以牙还牙甲，挨打
        if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.ToothForToothArmor.get())) {
            int curiosCount = FCAUtil.getCuriosCount(attacked, FCAItemRegister.ToothForToothArmor.get());
            if (event.getSource().getEntity() instanceof LivingEntity attacker) {
                float armor1 = (float) attacked.getAttributeValue(Attributes.ARMOR);
                float armor2 = (float) attacked.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
                int damage = (int)( (armor1+armor2)*curiosCount / 4 );
                var DamageType = FCAUtil.hasSource(attacked.level(), DamageTypes.MAGIC, attacked);
                attacker.invulnerableTime = 0;
                attacker.hurt(DamageType, damage);
            }else if (event.getSource().getDirectEntity() instanceof LivingEntity attacker) {
                float armor1 = (float) attacked.getAttributeValue(Attributes.ARMOR);
                float armor2 = (float) attacked.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
                int damage = (int)( (armor1+armor2)*curiosCount / 4 );
                var DamageType = FCAUtil.hasSource(attacked.level(), DamageTypes.MAGIC, attacked);
                attacker.invulnerableTime = 0;
                attacker.hurt(DamageType, damage);
            }
        }
    }
    @SubscribeEvent
    public static void heal(LivingHealEvent event) {

    }
    @SubscribeEvent
    public static void entityKilled(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            CompoundTag compoundTag = attacker.getPersistentData();
            //杀红眼
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.KillRedEye.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.KillRedEye.get());
                //层数
                int nbtNumber = compoundTag.getInt(kill_red_eye_Number_NBT);
                compoundTag.putInt(kill_red_eye_Number_NBT, Math.min(5 * curiosCount, nbtNumber + curiosCount));
                compoundTag.putInt(kill_red_eye_TIME_NBT, 200);
            }
        } else if (event.getSource().getDirectEntity() instanceof LivingEntity attacker) {
            CompoundTag compoundTag = attacker.getPersistentData();
            //杀红眼
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.KillRedEye.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.KillRedEye.get());
                //层数
                int nbtNumber = compoundTag.getInt(kill_red_eye_Number_NBT);
                compoundTag.putInt(kill_red_eye_Number_NBT, Math.min(5 * curiosCount, nbtNumber + curiosCount));
                compoundTag.putInt(kill_red_eye_TIME_NBT, 200);
            }
        }
    }
    private static final String Firestorm_Surge_TIME_NBT = FlameChaseArtifacts.MODID + ":firestorm_surge_time";
    private static final String Firestorm_Surge_Number_NBT = FlameChaseArtifacts.MODID + ":firestorm_surge_number";
    private static final String kill_red_eye_TIME_NBT = FlameChaseArtifacts.MODID + ":kill_red_eye_time";
    private static final String kill_red_eye_Number_NBT = FlameChaseArtifacts.MODID + ":kill_red_eye_number";
    private static final String meteoric_wings_TIME_NBT = FlameChaseArtifacts.MODID + ":meteoric_wings_time";
    private static final String kinetic_stimulus_sword_TIME_NBT = FlameChaseArtifacts.MODID + ":kinetic_stimulus_sword_time";
    private static final String mad_joke_engine_TIME_NBT = FlameChaseArtifacts.MODID + ":mad_joke_engine_time";
    private static final String mad_joke_engine_Number_NBT = FlameChaseArtifacts.MODID + ":mad_joke_engine_number";
    private static final String substance_decomposition_liquid_TIME_NBT = FlameChaseArtifacts.MODID + ":substance_decomposition_liquid_time";
    private static final String perpetual_motion_machine_TIME_NBT = FlameChaseArtifacts.MODID + ":perpetual_motion_machine_time";
    private static final String perpetual_motion_machine_Number_NBT = FlameChaseArtifacts.MODID + ":perpetual_motion_machine_number";
    private static final String electromagnetic_catapult_TIME_NBT = FlameChaseArtifacts.MODID + ":electromagnetic_catapult_time";
    private static final String photon_shield_TIME_NBT = FlameChaseArtifacts.MODID + ":photon_shield_time";
    //统一降低时间
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        CompoundTag compoundTag = livingEntity.getPersistentData();
        //电光履（饱和度）+蓄能帆（饥饿度）
        if (livingEntity.level().getGameTime() % 20L == 0&&livingEntity instanceof Player player) {
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.ElectricSparkBoot.get())) {
                int curiosCount = FCAUtil.getCuriosCount(livingEntity, FCAItemRegister.ElectricSparkBoot.get());
                int food = player.getFoodData().getFoodLevel();
                player.getFoodData().setFoodLevel(Math.min(food + curiosCount, 20));
            }
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.EnergySail.get())) {
                int curiosCount = FCAUtil.getCuriosCount(livingEntity, FCAItemRegister.EnergySail.get());
                float saturation = player.getFoodData().getSaturationLevel();
                player.getFoodData().setSaturation(Math.min(saturation + curiosCount, 20));
            }
        }
        //绝对热量——范围回血
        if (livingEntity.level().getGameTime() % 40L == 0) {
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.AbsoluteHeat.get())) {
                int curiosCount = FCAUtil.getCuriosCount(livingEntity, FCAItemRegister.AbsoluteHeat.get());
                var mobList = FCAUtil.mobList(7, livingEntity);
                var playerList = FCAUtil.PlayerList(7, livingEntity);
                float healNumber = livingEntity.getMaxHealth()*curiosCount*0.05f;
                //上buff
                livingEntity.heal(healNumber);
                livingEntity.addEffect(new MobEffectInstance(FCAEffectsRegister.AfterRain.get(),100,0));
                for (Mob mobs : mobList) {
                    //有主人的随从
                    if ((mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null  && ownableEntity.getOwner() == livingEntity) && mobs != livingEntity) {
                        mobs.heal(healNumber);
                        mobs.addEffect(new MobEffectInstance(FCAEffectsRegister.AfterRain.get(),100,0));
                    }
                }
                for (Player players : playerList) {
                    if (players != livingEntity) {
                        players.heal(healNumber);
                        players.addEffect(new MobEffectInstance(FCAEffectsRegister.AfterRain.get(),100,0));
                    }
                }
            }
            //晨昏之眼
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.EyeOfTwilight.get())) {
                var mobList = FCAUtil.mobList(16, livingEntity);
                var playerList = FCAUtil.PlayerList(16, livingEntity);
                float healNumber = livingEntity.getMaxHealth()*0.2F;
                //上buff
                livingEntity.heal(healNumber);
                for (Mob mobs : mobList) {
                    //有主人的随从
                    if ((mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null && ownableEntity.getOwner()==livingEntity) && mobs != livingEntity) {
                        mobs.heal(healNumber);
                    }
                }
                for (Player players : playerList) {
                    if (players != livingEntity) {
                        players.heal(healNumber);
                    }
                }
            }
        }
        //掩体生成枪————10s赋予一次护盾
        if (livingEntity.level().getGameTime() % 200L == 0) {
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.CoverGeneratingGun.get())) {
                int curiosCount = FCAUtil.getCuriosCount(livingEntity, FCAItemRegister.CoverGeneratingGun.get());
                var mobList = FCAUtil.mobList(7, livingEntity);
                var playerList = FCAUtil.PlayerList(7, livingEntity);
                //计算理应等级【如果是26血就是2，对应3级（2+1）
                int effectLevel = (int)(livingEntity.getMaxHealth()/13)*curiosCount;
                //上buff
                livingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,300,effectLevel ));
                for (Mob mobs : mobList) {
                    //有主人的随从
                    if ((mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null  && ownableEntity.getOwner() == livingEntity) && mobs != livingEntity) {
                        mobs.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,300,effectLevel ));
                    }
                }
                for (Player players : playerList) {
                    if (players != livingEntity) {
                        players.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,300,effectLevel ));
                    }
                }
            }
        }
        //腾荒
        int dragonTime = compoundTag.getInt(permansor_terrae_TIME_NBT);
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PermansorTerrae.get()) ) {
            if (dragonTime == 0) {
                var mobList = FCAUtil.mobList(16, livingEntity);
                var playerList = FCAUtil.PlayerList(16, livingEntity);
                int effectLevel = (int) (livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.23F);
                //上buff
                livingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 300, effectLevel));
                livingEntity.addEffect(new MobEffectInstance(FCAEffectsRegister.Bondmate.get(), 300, 0));
                for (Mob mobs : mobList) {
                    //有主人的随从
                    if ((mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null  && ownableEntity.getOwner() == livingEntity) && mobs != livingEntity) {
                        mobs.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 300, effectLevel));
                        mobs.addEffect(new MobEffectInstance(FCAEffectsRegister.Bondmate.get(), 300, 0));
                    }
                }
                for (Player players : playerList) {
                    if (players != livingEntity) {
                        players.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 300, effectLevel));
                        players.addEffect(new MobEffectInstance(FCAEffectsRegister.Bondmate.get(), 300, 0));
                    }
                }
                compoundTag.putInt(permansor_terrae_TIME_NBT, 200);
            }else{
                compoundTag.putInt(permansor_terrae_TIME_NBT, dragonTime - 1);
            }
        }
        //雅努斯——神启
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.Janus.get()) ) {
            if (dragonTime == 0) {
                var mobList = FCAUtil.mobList(16, livingEntity);
                var playerList = FCAUtil.PlayerList(16, livingEntity);
                int effectLevel = (int) (livingEntity.getMaxHealth()*0.05F);
                //上buff
                livingEntity.addEffect(new MobEffectInstance(FCAEffectsRegister.Numinosity.get(), 300, effectLevel));
                for (Mob mobs : mobList) {
                    //有主人的随从
                    if ((mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null  && ownableEntity.getOwner() == livingEntity) && mobs != livingEntity) {
                        mobs.addEffect(new MobEffectInstance(FCAEffectsRegister.Numinosity.get(), 300, effectLevel));
                    }
                }
                for (Player players : playerList) {
                    if (players != livingEntity) {
                        players.addEffect(new MobEffectInstance(FCAEffectsRegister.Numinosity.get(), 300, effectLevel));
                    }
                }
                compoundTag.putInt(permansor_terrae_TIME_NBT, 200);
            }else{
                compoundTag.putInt(permansor_terrae_TIME_NBT, dragonTime - 1);
            }
        }
        //魔术技巧
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.Cerces.get()) ) {
            var mobList = FCAUtil.mobList(16, livingEntity);
            for (Mob mobs : mobList) {
                //有主人的随从
                if (!(mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null && ownableEntity.getOwner() == livingEntity) && mobs != livingEntity) {
                    mobs.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1));
                    mobs.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 1));
                }
            }
        }
        //光能盾牌
        int photon_shield_TIME = compoundTag.getInt(photon_shield_TIME_NBT);
        if(livingEntity.getHealth()<livingEntity.getMaxHealth()*0.5F&&FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PhotonShield.get())
        &&photon_shield_TIME==0){
            int curiosCount = FCAUtil.getCuriosCount(livingEntity, FCAItemRegister.PhotonShield.get());
            float armor1 = (float) livingEntity.getAttributeValue(Attributes.ARMOR);
            float armor2 = (float) livingEntity.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
            int effectLevel = (int)( (armor1+armor2)*curiosCount / 6.5 );
            //上buff
            livingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,120,effectLevel ));
            compoundTag.putInt(photon_shield_TIME_NBT, 15*20);
        }
        //上buff
        if (livingEntity.level().getGameTime() % 5L == 0) {
            //武器大师
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.WeaponMaster.get())) {
                int curiosCount = FCAUtil.getCuriosCount(livingEntity, FCAItemRegister.WeaponMaster.get());
                var mobList = FCAUtil.mobList(7,livingEntity);
                var playerList = FCAUtil.PlayerList(7,livingEntity);
                //上buff
                livingEntity.addEffect(new MobEffectInstance(FCAEffectsRegister.WeaponMaster.get(),100,curiosCount-1 ));
                for (Mob mobs : mobList) {
                    //有主人的随从
                    if ( (mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null  && ownableEntity.getOwner() == livingEntity) && mobs!=livingEntity) {
                        mobs.addEffect(new MobEffectInstance(FCAEffectsRegister.WeaponMaster.get(),100,curiosCount-1 ));
                    }
                }
                for (Player players : playerList) {
                    if(players!=livingEntity) {
                        players.addEffect(new MobEffectInstance(FCAEffectsRegister.WeaponMaster.get(), 100, curiosCount - 1));
                    }
                }
            }
            //信心注入器
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.ConfidenceInjector.get())) {
                int curiosCount = FCAUtil.getCuriosCount(livingEntity, FCAItemRegister.ConfidenceInjector.get());
                var mobList = FCAUtil.mobList(7,livingEntity);
                var playerList = FCAUtil.PlayerList(7,livingEntity);
                //上buff
                for (Mob mobs : mobList) {
                    //有主人的随从
                    if ( (mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null  && ownableEntity.getOwner() == livingEntity) && mobs!=livingEntity) {
                        mobs.addEffect(new MobEffectInstance(FCAEffectsRegister.ConfidenceInjector.get(),100,curiosCount-1 ));
                    }
                }
                for (Player players : playerList) {
                    if(players!=livingEntity) {
                        players.addEffect(new MobEffectInstance(FCAEffectsRegister.ConfidenceInjector.get(), 100, curiosCount - 1));
                    }
                }
            }
            //反卫星狙击枪
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.AntiSatelliteSniperRifle.get())) {
                int curiosCount = FCAUtil.getCuriosCount(livingEntity, FCAItemRegister.AntiSatelliteSniperRifle.get());
                var mobList = FCAUtil.mobList(7,livingEntity);
                var playerList = FCAUtil.PlayerList(7,livingEntity);
                //上buff
                for (Mob mobs : mobList) {
                    //有主人的随从
                    if ( (mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null  && ownableEntity.getOwner() == livingEntity) && mobs!=livingEntity) {
                        mobs.addEffect(new MobEffectInstance(FCAEffectsRegister.AntiSatelliteSniperRifle.get(),100,curiosCount-1 ));
                    }
                }
                for (Player players : playerList) {
                    if(players!=livingEntity) {
                        players.addEffect(new MobEffectInstance(FCAEffectsRegister.AntiSatelliteSniperRifle.get(), 100, curiosCount - 1));
                    }
                }
            }
            //自适应外骨骼
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.AdaptiveExoskeleton.get())) {
                int curiosCount = FCAUtil.getCuriosCount(livingEntity, FCAItemRegister.AdaptiveExoskeleton.get());
                var mobList = FCAUtil.mobList(7,livingEntity);
                var playerList = FCAUtil.PlayerList(7,livingEntity);
                //上buff
                for (Mob mobs : mobList) {
                    //有主人的随从
                    if ( (mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null  && ownableEntity.getOwner() == livingEntity) && mobs!=livingEntity) {
                        mobs.addEffect(new MobEffectInstance(FCAEffectsRegister.AdaptiveExoskeleton.get(),100,curiosCount-1 ));
                    }
                }
                for (Player players : playerList) {
                    if(players!=livingEntity) {
                        players.addEffect(new MobEffectInstance(FCAEffectsRegister.AdaptiveExoskeleton.get(), 100, curiosCount - 1));
                    }
                }
            }
            //胜利之旗
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.FlagOfVictory.get())) {
                int curiosCount = FCAUtil.getCuriosCount(livingEntity, FCAItemRegister.FlagOfVictory.get());
                var mobList = FCAUtil.mobList(7,livingEntity);
                var playerList = FCAUtil.PlayerList(7,livingEntity);
                //上buff
                livingEntity.addEffect(new MobEffectInstance(FCAEffectsRegister.FlagOfVictory.get(),100,curiosCount-1 ));
                for (Mob mobs : mobList) {
                    //有主人的随从
                    if ( (mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null  && ownableEntity.getOwner() == livingEntity) && mobs!=livingEntity) {
                        mobs.addEffect(new MobEffectInstance(FCAEffectsRegister.FlagOfVictory.get(),100,curiosCount-1 ));
                    }
                }
                for (Player players : playerList) {
                    if(players!=livingEntity) {
                        players.addEffect(new MobEffectInstance(FCAEffectsRegister.FlagOfVictory.get(), 100, curiosCount - 1));
                    }
                }
            }
            //生命之环
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.RingOfLife.get())) {
                int curiosCount = FCAUtil.getCuriosCount(livingEntity, FCAItemRegister.RingOfLife.get());
                var mobList = FCAUtil.mobList(7,livingEntity);
                var playerList = FCAUtil.PlayerList(7,livingEntity);
                //上buff
                for (Mob mobs : mobList) {
                    //有主人的随从
                    if ( (mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null  && ownableEntity.getOwner() == livingEntity) && mobs!=livingEntity) {
                        mobs.addEffect(new MobEffectInstance(FCAEffectsRegister.RingOfLife.get(),100,curiosCount-1 ));
                    }
                }
                for (Player players : playerList) {
                    if(players!=livingEntity) {
                        players.addEffect(new MobEffectInstance(FCAEffectsRegister.RingOfLife.get(), 100, curiosCount - 1));
                    }
                }
            }
            //痛觉阻断
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PainBlockChip.get())) {
                int curiosCount = FCAUtil.getCuriosCount(livingEntity, FCAItemRegister.PainBlockChip.get());
                var mobList = FCAUtil.mobList(7,livingEntity);
                var playerList = FCAUtil.PlayerList(7,livingEntity);
                //上buff
                for (Mob mobs : mobList) {
                    //有主人的随从
                    if ( (mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null  && ownableEntity.getOwner() == livingEntity) && mobs!=livingEntity) {
                        mobs.addEffect(new MobEffectInstance(FCAEffectsRegister.PainBlockChip.get(),100,curiosCount-1 ));
                    }
                }
                for (Player players : playerList) {
                    if(players!=livingEntity) {
                        players.addEffect(new MobEffectInstance(FCAEffectsRegister.PainBlockChip.get(), 100, curiosCount - 1));
                    }
                }
            }
            //很硬的甲
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.VeryHardArmor.get())) {
                int curiosCount = FCAUtil.getCuriosCount(livingEntity, FCAItemRegister.VeryHardArmor.get());
                var mobList = FCAUtil.mobList(7,livingEntity);
                var playerList = FCAUtil.PlayerList(7,livingEntity);
                //上buff
                livingEntity.addEffect(new MobEffectInstance(FCAEffectsRegister.VeryHardArmor.get(),100,curiosCount-1 ));
                for (Mob mobs : mobList) {
                    //有主人的随从
                    if ( (mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null  && ownableEntity.getOwner() == livingEntity) && mobs!=livingEntity) {
                        mobs.addEffect(new MobEffectInstance(FCAEffectsRegister.VeryHardArmor.get(),100,curiosCount-1 ));
                    }
                }
                for (Player players : playerList) {
                    if(players!=livingEntity) {
                        players.addEffect(new MobEffectInstance(FCAEffectsRegister.VeryHardArmor.get(), 100, curiosCount - 1));
                    }
                }
            }
        }
        //电磁弹射器
        int electromagnetic_catapult_TIME = compoundTag.getInt(electromagnetic_catapult_TIME_NBT);
        if (electromagnetic_catapult_TIME > 0) {
            compoundTag.putInt(electromagnetic_catapult_TIME_NBT, electromagnetic_catapult_TIME - 1);
            livingEntity.removeEffect(FCAEffectsRegister.ElectromagneticCatapult.get());
        }else{
            int curiosCount = FCAUtil.getCuriosCount(livingEntity, FCAItemRegister.ElectromagneticCatapult.get());
            if(curiosCount>0) {
                livingEntity.addEffect(new MobEffectInstance(FCAEffectsRegister.ElectromagneticCatapult.get(), 100, curiosCount - 1));
            }
        }
        //其他冷却处理
        int Firestorm_Surge_TIME = compoundTag.getInt(Firestorm_Surge_TIME_NBT);
        if (Firestorm_Surge_TIME > 0) {
            compoundTag.putInt(Firestorm_Surge_TIME_NBT, Firestorm_Surge_TIME - 1);
        }else{
            compoundTag.putInt(Firestorm_Surge_Number_NBT, 0);
        }
        int kill_red_eye_TIME = compoundTag.getInt(kill_red_eye_TIME_NBT);
        if (kill_red_eye_TIME > 0) {
            compoundTag.putInt(kill_red_eye_TIME_NBT, kill_red_eye_TIME - 1);
        }else{
            compoundTag.putInt(kill_red_eye_Number_NBT, 0);
        }
        int meteoric_wings_TIME = compoundTag.getInt(meteoric_wings_TIME_NBT);
        if (meteoric_wings_TIME > 0) {
            compoundTag.putInt(meteoric_wings_TIME_NBT, meteoric_wings_TIME - 1);
        }
        int kinetic_stimulus_sword_TIME = compoundTag.getInt(kinetic_stimulus_sword_TIME_NBT);
        if (kinetic_stimulus_sword_TIME > 0) {
            compoundTag.putInt(kinetic_stimulus_sword_TIME_NBT, kinetic_stimulus_sword_TIME - 1);
        }
        int mad_joke_engine_TIME = compoundTag.getInt(mad_joke_engine_TIME_NBT);
        if (mad_joke_engine_TIME > 0) {
            compoundTag.putInt(mad_joke_engine_TIME_NBT, mad_joke_engine_TIME - 1);
        }else{
            compoundTag.putInt(mad_joke_engine_Number_NBT, 0);
        }
        int substance_decomposition_liquid_TIME = compoundTag.getInt(substance_decomposition_liquid_TIME_NBT);
        if (substance_decomposition_liquid_TIME > 0) {
            compoundTag.putInt(substance_decomposition_liquid_TIME_NBT, substance_decomposition_liquid_TIME - 1);
        }
        int perpetual_motion_machine_TIME = compoundTag.getInt(perpetual_motion_machine_TIME_NBT);
        if (perpetual_motion_machine_TIME > 0) {
            compoundTag.putInt(perpetual_motion_machine_TIME_NBT, perpetual_motion_machine_TIME - 1);
        }else{
            compoundTag.putInt(perpetual_motion_machine_Number_NBT, 0);
        }
        if (photon_shield_TIME > 0) {
            compoundTag.putInt(photon_shield_TIME_NBT, photon_shield_TIME - 1);
        }
    }
}
