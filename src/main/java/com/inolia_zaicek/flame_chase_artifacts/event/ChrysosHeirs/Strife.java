package com.inolia_zaicek.flame_chase_artifacts.event.ChrysosHeirs;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;
import java.util.UUID;

@SuppressWarnings({"all", "removal"})
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = FlameChaseArtifacts.MODID)
public class Strife {
    private static final UUID uuid = UUID.fromString("9FE96140-BE0B-CCBD-C20C-F4E8009ADC20");

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //挨打
        if (event.getEntity()!=null) {
            LivingEntity livingEntity = event.getEntity();
            Optional<SlotResult> stack = CuriosApi.getCuriosHelper().findFirstCurio(livingEntity, FCAItemRegister.HatredInundate.get());

            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.HatredInundate.get()) && event.getEntity().getAttributeValue(Attributes.ARMOR)>0
                    && !FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.StrifeCurios.get()) ) {
                Optional.of(event.getEntity())
                        .map(LivingEntity::getAttributes)
                        .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                        .map(manager -> manager.getInstance(Attributes.ARMOR))
                        .filter(instance -> instance.getModifier(uuid) == null)
                        .ifPresent(instance -> instance.addTransientModifier(
                                new AttributeModifier(uuid, "strife_curse", -(1-FCAconfig.strifeCurse.get() ), AttributeModifier.Operation.MULTIPLY_BASE)));
            }
            if (event.getEntity().getAttributeValue(Attributes.ARMOR)>0 &&
                    //二选一（德谬歌/纷争+加冕
                    (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) ||
                            (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.StrifeCurios.get())&&FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.HatredInundate.get()) )
                    )
            ) {
                Optional.of(event.getEntity())
                        .map(LivingEntity::getAttributes)
                        .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                        .map(manager -> manager.getInstance(Attributes.ARMOR))
                        .filter(instance -> instance.getModifier(uuid) == null)
                        .ifPresent(instance -> instance.addTransientModifier(
                                new AttributeModifier(uuid, "strife_blessing", (1-FCAconfig.strifeBlessing.get() ), AttributeModifier.Operation.MULTIPLY_BASE)));
            }
            //加冕
            if (event.getEntity().getAttributeValue(Attributes.ARMOR)>0 && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get()) ) {
                Optional.of(event.getEntity())
                        .map(LivingEntity::getAttributes)
                        .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                        .map(manager -> manager.getInstance(Attributes.ARMOR))
                        .filter(instance -> instance.getModifier(uuid) == null)
                        .ifPresent(instance -> instance.addTransientModifier(
                                new AttributeModifier(uuid, "strife_over_urse", -(1-FCAconfig.strifeOverCurse.get() ), AttributeModifier.Operation.MULTIPLY_BASE)));
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
}