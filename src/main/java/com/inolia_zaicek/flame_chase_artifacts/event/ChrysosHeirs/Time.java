package com.inolia_zaicek.flame_chase_artifacts.event.ChrysosHeirs;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;

@SuppressWarnings({"all", "removal"})
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = FlameChaseArtifacts.MODID)
public class Time {
    //存储生物信息
    public static final String KEY_ENTITY = "entity";
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (event.getEntity()!=null) {
            LivingEntity livingEntity = event.getEntity();
            Optional<SlotResult> stack = CuriosApi.getCuriosHelper().findFirstCurio(livingEntity, FCAItemRegister.HatredInundate.get());

            if (stack.isPresent() && FCAconfig.timeCurse.get() >= 0 && !FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.TimeCurios.get())) {
                //记录原始时间
                float time = livingEntity.invulnerableTime;
                livingEntity.invulnerableTime = (int) (time * FCAconfig.timeCurse.get());
            }
            //德谬歌or铁墓+岁月
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) ||
                    (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.TimeCurios.get())
                            && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()))) {
                //记录原始时间
                float time = livingEntity.invulnerableTime;
                livingEntity.invulnerableTime = (int) (time * FCAconfig.timeBlessing.get());
                //岁月
                if( FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.TimeCurios.get()) ) {
                    livingEntity.heal((float) (event.getAmount() * FCAconfig.timeCurios.get()));
                }
            }
            //加冕
            if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())) {
                //记录原始时间
                float time = livingEntity.invulnerableTime;
                livingEntity.invulnerableTime = (int) (time * FCAconfig.timeOverCurse.get());
                livingEntity.heal((float) (event.getAmount() * FCAconfig.timeCurios.get()));
            }
        }
    }
}
