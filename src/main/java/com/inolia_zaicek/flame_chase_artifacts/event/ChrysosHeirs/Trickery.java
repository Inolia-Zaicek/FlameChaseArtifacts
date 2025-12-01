package com.inolia_zaicek.flame_chase_artifacts.event.ChrysosHeirs;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAEntityHelper;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
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
    public static void buff(MobEffectEvent.Added event) {
        LivingEntity livingEntity = event.getEntity();
        Optional<SlotResult> stack = CuriosApi.getCuriosHelper().findFirstCurio(livingEntity, FCAItemRegister.HatredInundate.get());
        ///【诡计
        if (stack.isPresent() && FCAconfig.trickeryCurse.get()>=0 && !FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.TrickeryCurios.get())) {
            MobEffectInstance effect = event.getEffectInstance();
            MobEffect type = effect.getEffect();
            //正面
            if (type.getCategory().equals(MobEffectCategory.BENEFICIAL) ) {
                float number = (float) ((float) 1 / (FCAconfig.trickeryCurse.get()*1));
                int finish = (int) (effect.getDuration() / (1-number) );
                FCAEntityHelper.shortenEffect(effect, livingEntity, finish);
            }
        }
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.TrickeryCurios.get()) ) {
            MobEffectInstance effect = event.getEffectInstance();
            MobEffect type = effect.getEffect();
            //正面
            if (type.getCategory().equals(MobEffectCategory.BENEFICIAL) ) {
                float number = (float) (FCAconfig.trickeryBlessing.get() * 1);
                int finish = (int) (effect.getDuration() * (number-1) );
                FCAEntityHelper.extendEffect(effect, livingEntity, finish);
            }
        }
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get()) ) {
            MobEffectInstance effect = event.getEffectInstance();
            MobEffect type = effect.getEffect();
            //正面
            if (type.getCategory().equals(MobEffectCategory.BENEFICIAL) ) {
                float number = (float) (FCAconfig.trickeryOverCurse.get() * 1);
                int finish = (int) (effect.getDuration() * (number-1) );
                FCAEntityHelper.extendEffect(effect, livingEntity, finish);
            }
        }
        /// 海洋
        if (stack.isPresent() && FCAconfig.oceanCurse.get() >= 0 && !FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OceanCurios.get())) {
            if (FCAconfig.oceanCurse.get() != 1) {
                MobEffectInstance effect = event.getEffectInstance();
                MobEffect type = effect.getEffect();
                //非正面
                if (!type.getCategory().equals(MobEffectCategory.BENEFICIAL)) {
                    float number = (float) (FCAconfig.oceanCurse.get() * 1);
                    int finish = (int) (effect.getDuration() * (number-1) );
                    FCAEntityHelper.extendEffect(effect, livingEntity, finish);
                }
            }
        }
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OceanCurios.get())) {
            if (FCAconfig.oceanBlessing.get() != 1) {
                MobEffectInstance effect = event.getEffectInstance();
                MobEffect type = effect.getEffect();
                //非正面
                if (!type.getCategory().equals(MobEffectCategory.BENEFICIAL)) {
                    float number = (float) ((float) 1 / (FCAconfig.oceanBlessing.get() * 1));
                    int finish = (int) (effect.getDuration() / (1-number) );
                    FCAEntityHelper.shortenEffect(effect, livingEntity, finish);
                }
            }
        }
        //加冕
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())) {
            MobEffectInstance effect = event.getEffectInstance();
            MobEffect type = effect.getEffect();
            //非正面
            if (!type.getCategory().equals(MobEffectCategory.BENEFICIAL)) {
                float number = (float) (FCAconfig.oceanOverCurse.get() * 1);
                int finish = (int) (effect.getDuration() * (number-1) );
                FCAEntityHelper.extendEffect(effect, livingEntity, finish);
            }
        }
    }
    @SubscribeEvent
    public static void entityKilled(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.TrickeryCurios.get()) || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())) {
                    int number = (int) (FCAconfig.trickeryCurios.get() * 1);
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
            } else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
                if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.TrickeryCurios.get()) || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())) {
                    int number = (int) (FCAconfig.trickeryCurios.get() * 1);
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
    }
}
