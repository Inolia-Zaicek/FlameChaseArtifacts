package com.inolia_zaicek.flame_chase_artifacts.event.ChrysosHeirs;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;

@SuppressWarnings({"all", "removal"})
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = FlameChaseArtifacts.MODID)
public class Sky {
    @SubscribeEvent
    public static void heal(LivingHealEvent event) {
        LivingEntity livingEntity = event.getEntity();
        //天空
        //计算
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.HatredInundate.get()) && !FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.SkyCurios.get())) {
            event.setAmount((float) (event.getAmount() * FCAconfig.skyCurse.get()));
        }
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) ||  FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.SkyCurios.get())) {
            event.setAmount((float) (event.getAmount() * FCAconfig.skyBlessing.get()));
        }
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())) {
            event.setAmount((float) (event.getAmount() * FCAconfig.skyOverCurse.get()));
        }
        //饰品
        CompoundTag compoundTag = livingEntity.getPersistentData();
        var skyKey = "sky_curios_get_nbt";
        if(FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.HatredInundate.get()) &&
                event.getAmount()>=livingEntity.getMaxHealth()*0.2f && !compoundTag.getBoolean(skyKey) &&
                livingEntity instanceof ServerPlayer player && livingEntity.getY()>=300){
            player.addItem(new ItemStack( FCAItemRegister.SkyCurios.get() ));
            compoundTag.putBoolean(skyKey, true);
        }
    }
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if(livingEntity.level().getGameTime() % 20L == 0 ) {
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.SkyCurios.get()) || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())) {
                livingEntity.heal((float) (livingEntity.getMaxHealth() * FCAconfig.skyCuriosNumber.get()));
                var mobList = FCAUtil.mobList((int) ((FCAconfig.skyCuriosRange.get() - 1) / 2), livingEntity);
                var playerList = FCAUtil.PlayerList(((FCAconfig.skyCuriosRange.get() - 1) / 2), livingEntity);
                for (Mob mobs : mobList) {
                    //有主人的随从
                    if ((mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null) && ownableEntity != livingEntity) {
                        mobs.heal((float) (mobs.getMaxHealth() * FCAconfig.skyCuriosNumber.get()));
                    }
                }
                for (Player player : playerList) {
                    if (player != livingEntity) {
                        player.heal((float) (player.getMaxHealth() * FCAconfig.skyCuriosNumber.get()));
                    }
                }
            }
        }
        if(livingEntity.level().getGameTime() % 10L == 0 &&  FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.SkyCurios.get()) ){
            var mobList = FCAUtil.mobList( (int)( (FCAconfig.skyEgoCurios.get()-1)/2) , livingEntity);
            var playerList = FCAUtil.PlayerList(( (FCAconfig.skyEgoCurios.get()-1)/2), livingEntity);
            for (Mob mobs : mobList) {
                //有主人的随从
                if ( (mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null)) {
                    mobs.addEffect(new MobEffectInstance(MobEffects.REGENERATION,100,(int)( (FCAconfig.skyEgoCuriosRegenerationLevel.get()*1)-1 ) ));
                    mobs.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,(int)( (FCAconfig.skyEgoCuriosSpeedLevel.get()*1)-1 )));
                }
            }
            for (Player player : playerList) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,100,(int)( (FCAconfig.skyEgoCuriosRegenerationLevel.get()*1)-1 )));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,(int)( (FCAconfig.skyEgoCuriosSpeedLevel.get()*1)-1 )));
            }
        }
    }
}
