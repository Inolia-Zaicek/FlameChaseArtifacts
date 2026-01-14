package com.inolia_zaicek.flame_chase_artifacts.event;

import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class FCAEntityAttributeGiveEvent {
    @SubscribeEvent
    public static void addCustomAttributes(EntityAttributeModificationEvent event) {
        for (EntityType<? extends LivingEntity> entityType : event.getTypes()) {
            //有最大生命值时，为其新增
            if (event.has(entityType, Attributes.MAX_HEALTH)) {
                event.add(entityType, FCAAttributes.DAMAGE_AMPLIFIER.get());
                event.add(entityType, FCAAttributes.DAMAGE_REDUCTION.get());
                event.add(entityType, FCAAttributes.LUCK_HIT_CHANCE.get());
                event.add(entityType, FCAAttributes.LUCK_HIT_AMPLIFIER.get());
                event.add(entityType, FCAAttributes.HEAL_AMPLIFIER.get());
                event.add(entityType, FCAAttributes.Break_Efficiency.get());
                event.add(entityType, FCAAttributes.Break_Damage.get());
                event.add(entityType, FCAAttributes.Break_Amplifier.get());
                event.add(entityType, FCAAttributes.Break_Time.get());
            }
        }
    }

}