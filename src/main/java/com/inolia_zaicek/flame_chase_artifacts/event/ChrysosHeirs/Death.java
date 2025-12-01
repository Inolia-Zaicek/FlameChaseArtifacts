package com.inolia_zaicek.flame_chase_artifacts.event.ChrysosHeirs;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;

@SuppressWarnings({"all", "removal"})
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = FlameChaseArtifacts.MODID)
public class Death {
    // NBT 键的常量，用于计时器。
    // 建议使用 String 常量，而不是 ResourceLocation 来直接作为 NBT 键。
    private static final String DEATH_BLESSING_TIME_NBT = FlameChaseArtifacts.MODID + ":death_blessing_time";

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingDamage(LivingUseTotemEvent event) {
        LivingEntity livingEntity = event.getEntity();
        Optional<SlotResult> stack = CuriosApi.getCuriosHelper().findFirstCurio(livingEntity, FCAItemRegister.HatredInundate.get());
        //是true————启用
        if (stack.isPresent() && FCAconfig.deathCurse.get() && !FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.DeathCurios.get()) ) {
            event.setCanceled(true);
        }
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get()) ) {
            event.setCanceled(true);
        }
    }
    //免死
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void LivingDeathVampire(LivingDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();
        CompoundTag compoundTag = livingEntity.getPersistentData();
        int cooldown = compoundTag.getInt(DEATH_BLESSING_TIME_NBT);
        if(FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.DeathCurios.get()) && cooldown==0){
            livingEntity.setHealth(livingEntity.getMaxHealth() / 4);
            livingEntity.deathTime = -2;
            livingEntity.isAlive();
            livingEntity.invulnerableTime = 40;
            event.setCanceled(true);
            //德谬歌
            if(FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.DeathCurios.get()) && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get())){
                compoundTag.putInt(DEATH_BLESSING_TIME_NBT, (int) (20*(FCAconfig.deathBlessing.get() - FCAconfig.deathEgoCurios.get() ) ));
                //无德谬歌
            }else{
                compoundTag.putInt(DEATH_BLESSING_TIME_NBT, (int) (20*FCAconfig.deathBlessing.get()));
            }
        }
    }
    //免死冷却
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        CompoundTag compoundTag = livingEntity.getPersistentData();
        int cooldown = compoundTag.getInt(DEATH_BLESSING_TIME_NBT);
        if (cooldown > 0) {
            // 计时器还在倒计时，将其减 1。
            compoundTag.putInt(DEATH_BLESSING_TIME_NBT, cooldown - 1);
        }
    }
}
