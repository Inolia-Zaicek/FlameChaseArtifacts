package com.inolia_zaicek.flame_chase_artifacts.event.ChrysosHeirs;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.damage.FCADamageType;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;

@SuppressWarnings({"all", "removal"})
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = FlameChaseArtifacts.MODID)
public class Passage {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //追击
        if(event.getSource().getEntity() instanceof LivingEntity livingEntity  && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PassageCurios.get())
                && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) ){
            LivingEntity mob =event.getEntity();
            var DamageType = FCADamageType.source(livingEntity.level(), FCADamageType.TRUEDAMAGE);
            if(livingEntity instanceof Player player) {
                mob.setLastHurtByPlayer(player);
            }
            mob.hurt(DamageType, (float) (event.getAmount()*FCAconfig.passageEgoCurios.get()));
        }
        else if(event.getSource().getDirectEntity() instanceof LivingEntity livingEntity  && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PassageCurios.get())
                && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) ){
            LivingEntity mob =event.getEntity();
            var DamageType = FCADamageType.source(livingEntity.level(), FCADamageType.TRUEDAMAGE);
            if(livingEntity instanceof Player player) {
                mob.setLastHurtByPlayer(player);
            }
            mob.hurt(DamageType, (float) (event.getAmount()*FCAconfig.passageEgoCurios.get()));
        }
        //日冕
        if(event.getSource().getEntity() instanceof LivingEntity livingEntity && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.CoronalRadiance.get())){
            LivingEntity mob =event.getEntity();
            var DamageType = FCADamageType.source(livingEntity.level(), FCADamageType.TRUEDAMAGE);
            if(livingEntity instanceof Player player) {
                mob.setLastHurtByPlayer(player);
            }
            mob.hurt(DamageType, (float) (event.getAmount()*0.13F));
        }else if(event.getSource().getDirectEntity() instanceof LivingEntity livingEntity && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.CoronalRadiance.get())){
            LivingEntity mob =event.getEntity();
            var DamageType = FCADamageType.source(livingEntity.level(), FCADamageType.TRUEDAMAGE);
            if(livingEntity instanceof Player player) {
                mob.setLastHurtByPlayer(player);
            }
            mob.hurt(DamageType, (float) (event.getAmount()*0.13F));
        }
    }
    @SubscribeEvent
    public static void event(EntityTeleportEvent event) {
        //有抑影，阻止传送
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            var mobList = FCAUtil.mobList( (int)( (FCAconfig.passageCurios.get()-1)/2) , livingEntity);
            var playerList = FCAUtil.PlayerList(( (FCAconfig.passageCurios.get()-1)/2), livingEntity);
            for (Mob mobs : mobList) {
                //有主人的随从有饰品
                if ( !(mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null)&&
                        (FCAUtil.isCurioEquipped(mobs, FCAItemRegister.PassageCurios.get() )||FCAUtil.isCurioEquipped(mobs, FCAItemRegister.OriginSin.get())
                        )) {
                    event.setCanceled(true);
                }
            }
            for (Player player : playerList) {
                //周围的玩家有饰品
                if(player!=livingEntity&& (FCAUtil.isCurioEquipped(player, FCAItemRegister.PassageCurios.get() )
                        ||FCAUtil.isCurioEquipped(player, FCAItemRegister.OriginSin.get())
                ) ) {
                    event.setCanceled(true);
                }
            }
        }
    }
}
