package com.inolia_zaicek.flame_chase_artifacts.event;

import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAEffectsRegister;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class IronTombErosionEvent {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        LivingEntity attacked = event.getEntity();
        //挨打的有buff
        if (attacked.hasEffect(FCAEffectsRegister.IronTombErosion.get())) {
            int level = 1+attacked.getEffect(FCAEffectsRegister.IronTombErosion.get()).getAmplifier();
            float number = (float) (FCAconfig.ironTombDamageUp.get()*1);
            event.setAmount(event.getAmount()*(1+number*level));
        }
        //攻击的有buff
        if(event.getSource().getEntity() instanceof LivingEntity attacker && attacker.hasEffect(FCAEffectsRegister.IronTombErosion.get())) {
            int level = 1+attacker.getEffect(FCAEffectsRegister.IronTombErosion.get()).getAmplifier();
            float number = (float) (FCAconfig.ironTombDamageUp.get()*1);
            event.setAmount(event.getAmount()*(1-number*level));
        }else if(event.getSource().getDirectEntity() instanceof LivingEntity attacker && attacker.hasEffect(FCAEffectsRegister.IronTombErosion.get())) {
            int level = 1+attacker.getEffect(FCAEffectsRegister.IronTombErosion.get()).getAmplifier();
            float number = (float) (FCAconfig.ironTombDamageUp.get()*1);
            event.setAmount(event.getAmount()*(1-number*level));
        }
    }
}
