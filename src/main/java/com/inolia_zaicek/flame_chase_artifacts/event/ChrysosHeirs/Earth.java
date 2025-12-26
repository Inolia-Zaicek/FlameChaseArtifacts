package com.inolia_zaicek.flame_chase_artifacts.event.ChrysosHeirs;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;
import java.util.UUID;

@SuppressWarnings({"all", "removal"})
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = FlameChaseArtifacts.MODID)
public class Earth {
    private static final UUID uuid = UUID.fromString("A13CE647-B306-BC9C-B928-9D3728B71FBD");

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {

            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.HatredInundate.get()) && event.getEntity().getAttributeValue(Attributes.ARMOR) > 0
                    && !FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.EarthCurios.get())) {
                Optional.of(event.getEntity())
                        .map(LivingEntity::getAttributes)
                        .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                        .map(manager -> manager.getInstance(Attributes.ARMOR))
                        .filter(instance -> instance.getModifier(uuid) == null)
                        .ifPresent(instance -> instance.addTransientModifier(
                                new AttributeModifier(uuid, "earth_curse", (FCAconfig.earthCurse.get() - 1), AttributeModifier.Operation.MULTIPLY_BASE)));
            }
            if (event.getEntity().getAttributeValue(Attributes.ARMOR) > 0 &&
                    //二选一（德谬歌/纷争+大地
                    (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) ||
                            (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.EarthCurios.get())
                                    && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.HatredInundate.get()))
                    )
            ) {
                Optional.of(event.getEntity())
                        .map(LivingEntity::getAttributes)
                        .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                        .map(manager -> manager.getInstance(Attributes.ARMOR))
                        .filter(instance -> instance.getModifier(uuid) == null)
                        .ifPresent(instance -> instance.addTransientModifier(
                                new AttributeModifier(uuid, "earth_blessing", -(1 - FCAconfig.earthBlessing.get()), AttributeModifier.Operation.MULTIPLY_BASE)));
            }
            //加冕
            if (event.getEntity().getAttributeValue(Attributes.ARMOR) > 0 && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())) {
                Optional.of(event.getEntity())
                        .map(LivingEntity::getAttributes)
                        .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                        .map(manager -> manager.getInstance(Attributes.ARMOR))
                        .filter(instance -> instance.getModifier(uuid) == null)
                        .ifPresent(instance -> instance.addTransientModifier(
                                new AttributeModifier(uuid, "earth_over_curse", -(1 - FCAconfig.earthOverCurse.get()), AttributeModifier.Operation.MULTIPLY_BASE)));
            }
            /// 灰黯之手
            if (event.getEntity().getAttributeValue(Attributes.ARMOR) > 0 && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.HandOfShadow.get())
                    && event.getEntity().getHealth() >= event.getEntity().getMaxHealth() * 0.5F) {
                Optional.of(event.getEntity())
                        .map(LivingEntity::getAttributes)
                        .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                        .map(manager -> manager.getInstance(Attributes.ARMOR))
                        .filter(instance -> instance.getModifier(uuid) == null)
                        .ifPresent(instance -> instance.addTransientModifier(
                                new AttributeModifier(uuid, "hand_of_shadow", -1, AttributeModifier.Operation.MULTIPLY_TOTAL)));
            }
        } else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {

            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.HatredInundate.get()) && event.getEntity().getAttributeValue(Attributes.ARMOR) > 0
                    && !FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.EarthCurios.get())) {
                Optional.of(event.getEntity())
                        .map(LivingEntity::getAttributes)
                        .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                        .map(manager -> manager.getInstance(Attributes.ARMOR))
                        .filter(instance -> instance.getModifier(uuid) == null)
                        .ifPresent(instance -> instance.addTransientModifier(
                                new AttributeModifier(uuid, "earth_curse", (FCAconfig.earthCurse.get() - 1), AttributeModifier.Operation.MULTIPLY_BASE)));
            }
            if (event.getEntity().getAttributeValue(Attributes.ARMOR) > 0 &&
                    //二选一（德谬歌/纷争+大地
                    (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) ||
                            (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.EarthCurios.get())
                                    && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.HatredInundate.get()))
                    )
            ) {
                Optional.of(event.getEntity())
                        .map(LivingEntity::getAttributes)
                        .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                        .map(manager -> manager.getInstance(Attributes.ARMOR))
                        .filter(instance -> instance.getModifier(uuid) == null)
                        .ifPresent(instance -> instance.addTransientModifier(
                                new AttributeModifier(uuid, "earth_blessing", -(1 - FCAconfig.earthBlessing.get()), AttributeModifier.Operation.MULTIPLY_BASE)));
            }
            //加冕
            if (event.getEntity().getAttributeValue(Attributes.ARMOR) > 0 && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())) {
                Optional.of(event.getEntity())
                        .map(LivingEntity::getAttributes)
                        .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                        .map(manager -> manager.getInstance(Attributes.ARMOR))
                        .filter(instance -> instance.getModifier(uuid) == null)
                        .ifPresent(instance -> instance.addTransientModifier(
                                new AttributeModifier(uuid, "earth_over_curse", -(1 - FCAconfig.earthOverCurse.get()), AttributeModifier.Operation.MULTIPLY_BASE)));
            }
            /// 灰黯之手
            if (event.getEntity().getAttributeValue(Attributes.ARMOR) > 0 && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.HandOfShadow.get())
                    && event.getEntity().getHealth() >= event.getEntity().getMaxHealth() * 0.5F) {
                Optional.of(event.getEntity())
                        .map(LivingEntity::getAttributes)
                        .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                        .map(manager -> manager.getInstance(Attributes.ARMOR))
                        .filter(instance -> instance.getModifier(uuid) == null)
                        .ifPresent(instance -> instance.addTransientModifier(
                                new AttributeModifier(uuid, "hand_of_shadow", -1, AttributeModifier.Operation.MULTIPLY_TOTAL)));
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        Optional.of(event.getEntity())
                .map(LivingEntity::getAttributes)
                .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                .map(manager -> manager.getInstance(Attributes.ARMOR))
                .ifPresent(instance -> instance.removeModifier(uuid));

    }

    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity.level().getGameTime() % 10L == 0) {
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.EarthCurios.get())) {
                var mobList = FCAUtil.mobList((int) ((FCAconfig.earthEgoCurios.get() - 1) / 2), livingEntity);
                var playerList = FCAUtil.PlayerList(((FCAconfig.earthEgoCurios.get() - 1) / 2), livingEntity);
                for (Mob mobs : mobList) {
                    //有主人的随从
                    if ((mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null)) {
                        mobs.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, (int) ((FCAconfig.earthEgoCuriosPowerLevel.get() * 1) - 1)));
                        mobs.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, (int) ((FCAconfig.earthEgoCuriosReLevel.get() * 1) - 1)));
                    }
                }
                for (Player player : playerList) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, (int) ((FCAconfig.earthEgoCuriosPowerLevel.get() * 1) - 1)));
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, (int) ((FCAconfig.earthEgoCuriosReLevel.get() * 1) - 1)));
                }
            }
            //满溢之杯
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.Phagousa.get())){
                livingEntity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 100, 0));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 100, 0));
            }
        }
    }
}