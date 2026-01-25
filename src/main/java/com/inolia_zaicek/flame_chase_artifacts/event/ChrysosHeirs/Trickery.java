package com.inolia_zaicek.flame_chase_artifacts.event.ChrysosHeirs;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAEntityHelper;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@SuppressWarnings({"all", "removal"})
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = FlameChaseArtifacts.MODID)
public class Trickery {
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if(FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.CoronalRadiance.get())||FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.LanceOfFury.get())
        ) {
            // 遍历当前所有状态效果，移除所有非正面buff（即负面debuff）
            for (MobEffectInstance effect : livingEntity.getActiveEffects()) {
                // 判断是否为负面状态
                if (!effect.getEffect().getCategory().equals(MobEffectCategory.BENEFICIAL)) {
                    livingEntity.removeEffect(effect.getEffect());
                }
            }
        }
    }
    @SubscribeEvent
    public static void buff(MobEffectEvent.Added event) {
        LivingEntity livingEntity = event.getEntity();
        Optional<SlotResult> stack = CuriosApi.getCuriosHelper().findFirstCurio(livingEntity, FCAItemRegister.HatredInundate.get());
        ///【诡计
        if (stack.isPresent() && FCAconfig.trickeryCurse.get()>=0 && !FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.TrickeryCurios.get())) {
            MobEffectInstance effect = event.getEffectInstance();
            MobEffect type = effect.getEffect();
            //正面
            if (type.getCategory().equals(MobEffectCategory.BENEFICIAL) ) {
                //这里返回的是缩短了多久，不是最终时间————例如effect.getDuration()/4是缩短25%时间，即1-(1/4)=3/4————
                //想获得缩短50%（0.5——即获取time/[1/0.5]=time/2
                FCAEntityHelper.shortenEffect(effect, livingEntity, (int) (effect.getDuration()/(1/FCAconfig.trickeryCurse.get())));
            }
        }
        //祝福
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.TrickeryCurios.get()) ) {
            MobEffectInstance effect = event.getEffectInstance();
            MobEffect type = effect.getEffect();
            //正面
            if (type.getCategory().equals(MobEffectCategory.BENEFICIAL) ) {
                //提升了多久——————150%-1)*time=提升了50%time
                FCAEntityHelper.extendEffect(effect, livingEntity, (int) (effect.getDuration() * (FCAconfig.trickeryBlessing.get()-1)));
            }
        }
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get()) ) {
            MobEffectInstance effect = event.getEffectInstance();
            MobEffect type = effect.getEffect();
            //正面
            if (type.getCategory().equals(MobEffectCategory.BENEFICIAL) ) {
                FCAEntityHelper.extendEffect(effect, livingEntity, (int) (effect.getDuration() * (FCAconfig.trickeryOverCurse.get()-1)));
            }
        }
        /// 海洋
        if (stack.isPresent() && FCAconfig.oceanCurse.get() >= 0 && !FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OceanCurios.get())) {
            if (FCAconfig.oceanCurse.get() != 1) {
                MobEffectInstance effect = event.getEffectInstance();
                MobEffect type = effect.getEffect();
                //非正面
                if (!type.getCategory().equals(MobEffectCategory.BENEFICIAL)) {
                    FCAEntityHelper.extendEffect(effect, livingEntity, (int) (effect.getDuration() * (FCAconfig.oceanCurse.get()-1)));
                }
            }
        }
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OceanCurios.get())) {
            if (FCAconfig.oceanBlessing.get() != 1) {
                MobEffectInstance effect = event.getEffectInstance();
                MobEffect type = effect.getEffect();
                //非正面
                if (!type.getCategory().equals(MobEffectCategory.BENEFICIAL)) {
                    FCAEntityHelper.shortenEffect(effect, livingEntity, (int) (effect.getDuration()/(1/FCAconfig.oceanBlessing.get())));
                }
            }
        }
        //加冕
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())) {
            MobEffectInstance effect = event.getEffectInstance();
            MobEffect type = effect.getEffect();
            //非正面
            if (!type.getCategory().equals(MobEffectCategory.BENEFICIAL)) {
                FCAEntityHelper.extendEffect(effect, livingEntity, (int) (effect.getDuration() * (FCAconfig.oceanOverCurse.get()-1)));
            }
        }
    }
    private static final String coronal_radiance_kill_number = FlameChaseArtifacts.MODID + ":coronal_radiance_number";
    private static final String coronal_radiance_damage = FlameChaseArtifacts.MODID + ":coronal_radiance_damage";
    @SubscribeEvent
    public static void entityKilled(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity&&event.getEntity()!=null) {
                if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.TrickeryCurios.get())
                        || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())
                        || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.CoinOfWhimsy.get())
                ) {
                    int number = 0;
                    if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.TrickeryCurios.get()) || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())) {
                        number += (int) (FCAconfig.trickeryCurios.get() * 1);
                    }
                    if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.CoinOfWhimsy.get())) {
                        number += 3;
                    }
                    if (number > 0&&event.getEntity()!=null) {
                        for (int i = 0; i < number; i++) {
                            Level level = livingEntity.level();
                            LootTable loot = ((MinecraftServer) Objects.requireNonNull(level.getServer())).getLootData().getLootTable(event.getEntity().getType().getDefaultLootTable());
                            LootParams context = (new LootParams.Builder((ServerLevel) level)).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(event.getEntity().blockPosition())).withParameter(LootContextParams.THIS_ENTITY, event.getEntity()).withParameter(LootContextParams.DAMAGE_SOURCE, livingEntity.damageSources().playerAttack((Player) livingEntity)).create(LootContextParamSets.ENTITY);
                            List<ItemStack> drops = loot.getRandomItems(context);
                            for (ItemStack drop : drops) {
                                ItemEntity itementity = new ItemEntity(level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), drop);
                                itementity.setDefaultPickUpDelay();
                                itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F), (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                                level.addFreshEntity(itementity);
                            }
                        }
                    }
                }
            }
        }else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.TrickeryCurios.get())
                    || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())
                    || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.CoinOfWhimsy.get())
            ) {
                int number = 0;
                if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.TrickeryCurios.get()) || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())) {
                    number += (int) (FCAconfig.trickeryCurios.get() * 1);
                }
                if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.CoinOfWhimsy.get())) {
                    number += 3;
                }
                if (number > 0) {
                    for (int i = 0; i < number; i++) {
                        Level level = livingEntity.level();
                        LootTable loot = ((MinecraftServer) Objects.requireNonNull(level.getServer())).getLootData().getLootTable(event.getEntity().getType().getDefaultLootTable());
                        LootParams context = (new LootParams.Builder((ServerLevel) level)).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(event.getEntity().blockPosition())).withParameter(LootContextParams.THIS_ENTITY, event.getEntity()).withParameter(LootContextParams.DAMAGE_SOURCE, livingEntity.damageSources().playerAttack((Player) livingEntity)).create(LootContextParamSets.ENTITY);
                        List<ItemStack> drops = loot.getRandomItems(context);
                        for (ItemStack drop : drops) {
                            ItemEntity itementity = new ItemEntity(level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), drop);
                            itementity.setDefaultPickUpDelay();
                            itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F), (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                            level.addFreshEntity(itementity);
                        }
                    }
                }
            }
        }
        /// 日冕
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.CoronalRadiance.get())){
            CompoundTag compoundTag = livingEntity.getPersistentData();
            int killNumber = compoundTag.getInt(coronal_radiance_kill_number);
            int damage = compoundTag.getInt(coronal_radiance_damage);
            if(damage<33550336) {
                if (killNumber < 496) {
                    compoundTag.putInt(coronal_radiance_kill_number, killNumber + 1);
                } else {
                    compoundTag.putInt(coronal_radiance_kill_number, 0);
                    compoundTag.putInt(coronal_radiance_damage, damage + 1);
                }
            }
        }else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.CoronalRadiance.get())){
            CompoundTag compoundTag = livingEntity.getPersistentData();
            int killNumber = compoundTag.getInt(coronal_radiance_kill_number);
            int damage = compoundTag.getInt(coronal_radiance_damage);
            if(damage<33550336) {
                if (killNumber < 496) {
                    compoundTag.putInt(coronal_radiance_kill_number, killNumber + 1);
                } else {
                    compoundTag.putInt(coronal_radiance_kill_number, 0);
                    compoundTag.putInt(coronal_radiance_damage, damage + 1);
                }
            }
        }
    }
}
