package com.inolia_zaicek.flame_chase_artifacts.event.ChrysosHeirs;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.damage.FCADamageType;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAEffectsRegister;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAKeyMappingUtil;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import dev.architectury.event.events.common.BlockEvent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
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
    private static final String coronal_radiance_time_nbt = FlameChaseArtifacts.MODID + ":coronal_radiance_time";
    private static final String lance_of_fury_time_nbt = FlameChaseArtifacts.MODID + ":lance_of_fury_time";
    private static final String hand_of_shadow_time_nbt = FlameChaseArtifacts.MODID + ":hand_of_shadow_time";
    private static final String deepest_dark_Number_NBT = FlameChaseArtifacts.MODID + ":deepest_dark_number";
    private static final String deepest_dark_TIME_NBT = FlameChaseArtifacts.MODID + ":deepest_dark_time";
    private static final String eye_of_twilight_Number_NBT = FlameChaseArtifacts.MODID + ":eye_of_twilight_number";
    private static final String eye_of_twilight_TIME_NBT = FlameChaseArtifacts.MODID + ":eye_of_twilight_time";

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
        int cooldown2 = compoundTag.getInt(coronal_radiance_time_nbt);
        int cooldown3 = compoundTag.getInt(lance_of_fury_time_nbt);
        int cooldown4 = compoundTag.getInt(hand_of_shadow_time_nbt);
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
        }else if(FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.CoronalRadiance.get()) && cooldown2==0){
            livingEntity.setHealth(livingEntity.getMaxHealth() / 4);
            livingEntity.deathTime = -2;
            livingEntity.isAlive();
            livingEntity.invulnerableTime = 40;
            event.setCanceled(true);
            compoundTag.putInt(coronal_radiance_time_nbt, 600);
        }else if(FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.LanceOfFury.get()) && cooldown3==0){
            livingEntity.setHealth(livingEntity.getMaxHealth() / 2);
            livingEntity.deathTime = -2;
            livingEntity.isAlive();
            livingEntity.invulnerableTime = 40;
            event.setCanceled(true);
            compoundTag.putInt(lance_of_fury_time_nbt, 60);
        }else if(FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.HandOfShadow.get()) && cooldown3==0){
            livingEntity.setHealth(livingEntity.getMaxHealth());
            livingEntity.deathTime = -2;
            livingEntity.isAlive();
            livingEntity.invulnerableTime = 40;
            event.setCanceled(true);
            compoundTag.putInt(lance_of_fury_time_nbt, 1200*20);
        }
    }
    private static final String coin_of_whimsy_Number_NBT = FlameChaseArtifacts.MODID + ":coin_of_whimsy_number";
    private static final String mnestia_Number_NBT = FlameChaseArtifacts.MODID + ":mnestia_number";
    private static final String mnestia_TIME_NBT = FlameChaseArtifacts.MODID + ":mnestia_time";
    //免死冷却
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        CompoundTag compoundTag = livingEntity.getPersistentData();
        int cooldown = compoundTag.getInt(DEATH_BLESSING_TIME_NBT);
        int cooldown2 = compoundTag.getInt(coronal_radiance_time_nbt);
        int cooldown3 = compoundTag.getInt(lance_of_fury_time_nbt);
        if (cooldown > 0) {
            compoundTag.putInt(DEATH_BLESSING_TIME_NBT, cooldown - 1);
        }
        if (cooldown2 > 0) {
            compoundTag.putInt(coronal_radiance_time_nbt, cooldown2 - 1);
        }
        if (cooldown3 > 0) {
            compoundTag.putInt(lance_of_fury_time_nbt, cooldown3 - 1);
        }
        int cooldown4 = compoundTag.getInt(hand_of_shadow_time_nbt);
        if (cooldown4 > 0) {
            compoundTag.putInt(hand_of_shadow_time_nbt, cooldown4 - 1);
        }
        //容器冷却
        int cooldown5 = compoundTag.getInt(deepest_dark_TIME_NBT);
        //容器数量
        int deepsetNumber = compoundTag.getInt(deepest_dark_Number_NBT);
        if (cooldown5 > 0&&deepsetNumber<=3) {
            compoundTag.putInt(deepest_dark_TIME_NBT, deepsetNumber+1);
            compoundTag.putInt(deepest_dark_TIME_NBT, cooldown5 - 1);
        }
        int cooldown6 = compoundTag.getInt(eye_of_twilight_TIME_NBT);
        if (cooldown6 > 0) {
            compoundTag.putInt(eye_of_twilight_TIME_NBT, cooldown6 - 1);
        }else{
            compoundTag.putInt(eye_of_twilight_Number_NBT, 0);
        }
        //翻飞之币
        int coin = compoundTag.getInt(coin_of_whimsy_Number_NBT);
        if (coin > 0 && livingEntity.level().getGameTime() % 20L == 0 ) {
            compoundTag.putInt(hand_of_shadow_time_nbt, (int) (coin-coin*0.01F));
        }
        if (FCAKeyMappingUtil.KEYMAPPING.isDown() && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.CoinOfWhimsy.get())) {
            livingEntity.addEffect(new MobEffectInstance(FCAEffectsRegister.KittyPhantomThief.get(),200,0));
        }
        //阿雅
        int cooldown7 = compoundTag.getInt(mnestia_TIME_NBT);
        if (cooldown7 > 0) {
            compoundTag.putInt(mnestia_TIME_NBT, cooldown7 - 1);
        }else{
            compoundTag.putInt(mnestia_Number_NBT, 0);
        }
        /// 死龙
        if(livingEntity.level().getGameTime() % 20L == 0 ) {
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.HandOfShadow.get())) {
                var DamageType = FCADamageType.source(livingEntity.level(), DamageTypes.WITHER);
                var mobList = FCAUtil.mobList(13, livingEntity);
                for (Mob mobs : mobList) {
                    //非随从or非是自己为主的随从
                    if (!(mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null && ownableEntity==livingEntity) && mobs!=livingEntity) {
                        mobs.hurt(DamageType, (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.1F );
                    }
                }
            }
        }
    }
}
