package com.inolia_zaicek.flame_chase_artifacts.event.ChrysosHeirs;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;

@SuppressWarnings({"all", "removal"})
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = FlameChaseArtifacts.MODID)
public class Reason {
    @SubscribeEvent
    public static void even1t(LivingExperienceDropEvent event){
        LivingEntity livingEntity = event.getEntity();
        Player player = event.getAttackingPlayer();
        float number = 1;
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.HatredInundate.get()) && !FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.ReasonCurios.get())) {
            number*=FCAconfig.reasonCurse.get();
        }
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) || FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.ReasonCurios.get())) {
            number*=FCAconfig.reasonBlessing.get();
        }
        //加冕
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())) {
            number*=FCAconfig.reasonOverCurse.get();
        }
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.Cerces.get())) {
            number*=10;
        }
        event.setDroppedExperience((int) (event.getDroppedExperience() * number));
    }
}
