package com.inolia_zaicek.flame_chase_artifacts.event.ChrysosHeirs;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;

@SuppressWarnings({"all", "removal"})
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = FlameChaseArtifacts.MODID)
public class Romance {
    private static final String mnestia_Number_NBT = FlameChaseArtifacts.MODID + ":mnestia_number";
    private static final String mnestia_TIME_NBT = FlameChaseArtifacts.MODID + ":mnestia_time";
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
            LivingEntity mob = event.getEntity();
            Optional<SlotResult> stack = CuriosApi.getCuriosHelper().findFirstCurio(livingEntity, FCAItemRegister.HatredInundate.get());
            //记录livingEntity.sendSystemMessage(Component.literal(String.valueOf("1")).withStyle(ChatFormatting.GOLD));
            int number = 1;
            float invulnerableTime = mob.invulnerableTime;
            if (stack.isPresent() && FCAconfig.romanceCurse.get() >= 0 && !FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.RomanceCurios.get())) {
                number*=FCAconfig.romanceCurse.get();
            }
            //德谬歌or饰品
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.RomanceCurios.get())) {
                number*=FCAconfig.romanceBlessing.get();
            }
            //加冕
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())) {
                number*=FCAconfig.romanceOverCurse.get();
            }
            CompoundTag compoundTag = livingEntity.getPersistentData();
            int romanceNumber = compoundTag.getInt(mnestia_Number_NBT);
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.Mnestia.get()) && romanceNumber>0) {
                mob.invulnerableTime = Math.max(0, (int) (number * invulnerableTime - romanceNumber) );
            }else{
                mob.invulnerableTime = Math.max(0, (int) (number * invulnerableTime) );
            }
            //德谬歌+饰品
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.RomanceCurios.get())) {
                int max = (int) (1*FCAconfig.romanceEgoCuriosNumber.get());
                int time = (int) (1*FCAconfig.romanceEgoCuriosTime.get());
                if(livingEntity.hasEffect(MobEffects.MOVEMENT_SPEED)){
                    int level = livingEntity.getEffect(MobEffects.MOVEMENT_SPEED).getAmplifier();
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,20*time,Math.min(max-1,level+1) ));
                }else{
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,20*time,0 ));
                }
                if(livingEntity.hasEffect(MobEffects.DIG_SPEED)){
                    int level = livingEntity.getEffect(MobEffects.DIG_SPEED).getAmplifier();
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,20*time,Math.min(max-1,level+1) ));
                }else{
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,20*time,0 ));
                }
            }
        }else if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
            LivingEntity mob = event.getEntity();
            Optional<SlotResult> stack = CuriosApi.getCuriosHelper().findFirstCurio(livingEntity, FCAItemRegister.HatredInundate.get());

            if (stack.isPresent() && FCAconfig.romanceCurse.get() >= 0 && !FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.RomanceCurios.get())) {
                //记录原始时间
                float time = mob.invulnerableTime;
                mob.invulnerableTime= (int) (time*FCAconfig.romanceCurse.get());
            }
            //德谬歌or饰品
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.RomanceCurios.get())) {
                float time = mob.invulnerableTime;
                mob.invulnerableTime = (int) (time * FCAconfig.romanceBlessing.get());
            }
            //加冕
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())) {
                //记录原始时间
                float time = mob.invulnerableTime;
                mob.invulnerableTime = (int) (time * FCAconfig.romanceOverCurse.get());
            }
            //德谬歌+饰品
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.RomanceCurios.get())) {
                int max = (int) (1*FCAconfig.romanceEgoCuriosNumber.get());
                int time = (int) (1*FCAconfig.romanceEgoCuriosTime.get());
                if(livingEntity.hasEffect(MobEffects.MOVEMENT_SPEED)){
                    int level = livingEntity.getEffect(MobEffects.MOVEMENT_SPEED).getAmplifier();
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,20*time,Math.min(max-1,level+1) ));
                }else{
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,20*time,0 ));
                }
                if(livingEntity.hasEffect(MobEffects.DIG_SPEED)){
                    int level = livingEntity.getEffect(MobEffects.DIG_SPEED).getAmplifier();
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,20*time,Math.min(max-1,level+1) ));
                }else{
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,20*time,0 ));
                }
            }
        }
    }
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if(FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.RomanceCurios.get()) || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get()) ){
            var mobList = FCAUtil.mobList( (int)( (FCAconfig.romanceCurios.get()-1)/2) , livingEntity);
            for (Mob mobs : mobList) {
                var map = mobs.getActiveEffectsMap();
                mobs.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0));
                if (!mobs.hasEffect(MobEffects.GLOWING)) {
                    map.put(MobEffects.GLOWING, new MobEffectInstance(MobEffects.GLOWING, 100, 0));
                }
                if (!(mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null) &&mobs!=livingEntity) {
                    mobs.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
                    if (!mobs.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                        map.put(MobEffects.MOVEMENT_SLOWDOWN, new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
                    }
                }
            }
        }
    }
}
