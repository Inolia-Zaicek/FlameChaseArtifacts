package com.inolia_zaicek.flame_chase_artifacts.event.ChrysosHeirs;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.item.FCACuriosItem;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAEffectsRegister;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;

@SuppressWarnings({"all", "removal"})
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = FlameChaseArtifacts.MODID)
public class Sin {

    private static final String HATRED_NUMBER_NBT = FlameChaseArtifacts.MODID + ":hatred_number";
    private static final String SIN_TIME_NBT = FlameChaseArtifacts.MODID + ":sin_time";

    //免死
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void LivingDeathVampire(LivingDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();
        CompoundTag compoundTag = livingEntity.getPersistentData();
        int hatred = compoundTag.getInt(HATRED_NUMBER_NBT);
        //恨意值满+有装备
        if (hatred >= 100 && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())) {
            livingEntity.setHealth(livingEntity.getMaxHealth());
            livingEntity.deathTime = -2;
            livingEntity.isAlive();
            livingEntity.invulnerableTime = 10;
            event.setCanceled(true);
            compoundTag.putInt(HATRED_NUMBER_NBT, 0);
        }
    }

    //加冕
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get())) {
            CompoundTag compoundTag = livingEntity.getPersistentData();
            //恨意值
            int hatred = compoundTag.getInt(HATRED_NUMBER_NBT);
            //每级提升
            int level = (int) (FCAconfig.ironTombHatred.get() * 1);
            if (hatred > 0) {
                event.setAmount((float) (event.getAmount() * (1 + hatred * FCAconfig.ironTombHatredDamage.get())));
                compoundTag.putInt(HATRED_NUMBER_NBT, Math.min(100, hatred + level));
            } else {
                event.setAmount((float) (event.getAmount() * (1 + FCAconfig.ironTombHatredDamage.get())));
                compoundTag.putInt(HATRED_NUMBER_NBT, level);
            }
            //10s时间
            compoundTag.putInt(SIN_TIME_NBT, 20 * 10);
        }
    }

    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.OriginSin.get()) && livingEntity.level().getGameTime() % 20L == 0) {
            var mobList = FCAUtil.mobList(13, livingEntity);
            for (Mob mobs : mobList) {
                var map = mobs.getActiveEffectsMap();
                int max = (int) (FCAconfig.ironTombLevel.get() * 1) - 1;
                if (!(mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null) && mobs != livingEntity) {
                    if (!mobs.hasEffect(FCAEffectsRegister.IronTombErosion.get())) {
                        map.put(FCAEffectsRegister.IronTombErosion.get(), new MobEffectInstance(FCAEffectsRegister.IronTombErosion.get(), 200, 0));
                    } else {
                        int level = mobs.getEffect(FCAEffectsRegister.IronTombErosion.get()).getAmplifier();
                        map.put(FCAEffectsRegister.IronTombErosion.get(), new MobEffectInstance(FCAEffectsRegister.IronTombErosion.get(), 200, Math.min(max, level + 1)));
                    }
                }
            }
        }
        CompoundTag compoundTag = livingEntity.getPersistentData();
        int cooldown = compoundTag.getInt(SIN_TIME_NBT);
        if (cooldown > 0) {
            // 计时器还在倒计时，将其减 1。
            compoundTag.putInt(SIN_TIME_NBT, cooldown - 1);
        } else {
            compoundTag.putInt(HATRED_NUMBER_NBT, 0);
        }
    }

    @SubscribeEvent
    public static void tooltip(ItemTooltipEvent event) {
        LivingEntity livingEntity = Minecraft.getInstance().player;
        ItemStack itemStack = event.getItemStack();
        if (livingEntity != null&&itemStack.getItem() == FCAItemRegister.OriginSin.get() ) {
            //获取工具nbt数值
            CompoundTag compoundTag = livingEntity.getPersistentData();
            //不存在就是0
            int hatred = compoundTag.getInt(HATRED_NUMBER_NBT);
            if (hatred > 0) {
                event.getToolTip().add(Component.translatable("tooltip.flame_chase_artifacts.origin_sin.hatred", hatred).withStyle(ChatFormatting.DARK_RED)); // 使用灰色
            }
        }
    }
}