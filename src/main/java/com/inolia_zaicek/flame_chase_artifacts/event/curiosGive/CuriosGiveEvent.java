package com.inolia_zaicek.flame_chase_artifacts.event.curiosGive;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.network.FCAChannel;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.register.craftItem.FCACraftItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.register.sponsorItem.FCASponsorItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = FlameChaseArtifacts.MODID)
public class CuriosGiveEvent {
    @SubscribeEvent
    public static void entityKilled(LivingDeathEvent event) {
        if (event.getEntity().level().isClientSide) return;
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
            if(!FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.HatredInundate.get()) )return;
            //饰品
            CompoundTag compoundTag = livingEntity.getPersistentData();
            //海洋
            var oceanKey = "ocean_curios_get_nbt";  //名字随意
            if (!compoundTag.getBoolean(oceanKey) && event.getEntity() instanceof ElderGuardian) {
                int neutralAndHarmfulCount = 0;
                for (MobEffectInstance effect : livingEntity.getActiveEffects()) {
                    // 判断是否为Beneficial或Harmful
                    boolean isBeneficial = effect.getEffect().getCategory() == MobEffectCategory.BENEFICIAL;
                    boolean isHarmful = effect.getEffect().getCategory() == MobEffectCategory.HARMFUL;
                    // 统计非Beneficial且非Harmful的效果
                    if (isBeneficial || isHarmful) {
                        neutralAndHarmfulCount++;
                    }
                }
                if(neutralAndHarmfulCount>=4 && livingEntity instanceof ServerPlayer player ){
                    player.addItem(new ItemStack( FCAItemRegister.OceanCurios.get() ));
                    compoundTag.putBoolean(oceanKey, true);
                }
            }
            //负世
            var worldbearingKey = "worldbearing_curios_get_nbt";
            if(livingEntity instanceof ServerPlayer player && !compoundTag.getBoolean(worldbearingKey) && event.getEntity() instanceof EnderDragon && player.getRemainingFireTicks()>0){
                player.addItem(new ItemStack( FCAItemRegister.WorldBearingCurios.get() ));
                compoundTag.putBoolean(worldbearingKey, true);
            }
            //浪漫
            var romanceKey = "romance_curios_get_nbt";
            if(livingEntity instanceof ServerPlayer player && !compoundTag.getBoolean(romanceKey) && player.hasEffect(MobEffects.DARKNESS)){
                player.addItem(new ItemStack( FCAItemRegister.RomanceCurios.get() ));
                compoundTag.putBoolean(romanceKey, true);
            }
            //死亡
            var deathKey = "death_curios_get_nbt";
            if(livingEntity instanceof ServerPlayer player && !compoundTag.getBoolean(deathKey) && event.getEntity() instanceof WitherBoss && player.getHealth()<=player.getMaxHealth()*0.2f){
                player.addItem(new ItemStack( FCAItemRegister.DeathCurios.get() ));
                compoundTag.putBoolean(deathKey, true);
            }
            //诡计（获取抢夺等级
            int totalLootingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.MOB_LOOTING, livingEntity);
            var trickeryKey = "trickery_curios_get_nbt";
            if(totalLootingLevel>=3 && livingEntity instanceof ServerPlayer player && !compoundTag.getBoolean(trickeryKey) && event.getEntity() instanceof Vex && player.hasEffect(MobEffects.MOVEMENT_SPEED)){
                player.addItem(new ItemStack( FCAItemRegister.TrickeryCurios.get() ));
                compoundTag.putBoolean(trickeryKey, true);
            }
            //律法
            var lawKey = "law_curios_get_nbt";
            if(livingEntity instanceof ServerPlayer player && !compoundTag.getBoolean(lawKey) && event.getEntity() instanceof Warden){
                player.addItem(new ItemStack( FCAItemRegister.LawCurios.get() ));
                compoundTag.putBoolean(lawKey, true);
            }
        }
    }
    private static final String time_curios_tick_time_nbt = FlameChaseArtifacts.MODID + ":time_curios_tick_time";
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if(!FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.HatredInundate.get()) )return;
        CompoundTag compoundTag = livingEntity.getPersistentData();
        //理性
        var reasonKey = "reason_curios_get_nbt";
        if(livingEntity instanceof ServerPlayer player&&player.experienceLevel>=75 && !compoundTag.getBoolean(reasonKey) ){
            player.addItem(new ItemStack( FCAItemRegister.ReasonCurios.get() ));
            compoundTag.putBoolean(reasonKey, true);
        }
        //黄泉高度判断
        var acheronKey = "acheron_curios_get_nbt";
        if(livingEntity instanceof ServerPlayer player && !compoundTag.getBoolean(acheronKey) ){
            float Y = player.getOnPos().getY();
            if(Y<=-264) {
                player.addItem(new ItemStack(FCAItemRegister.ReasonCurios.get()));
                compoundTag.putBoolean(reasonKey, true);
            }
        }
        //岁月——玩家进游戏每tick提升，满额则给
        var timeKey = "time_curios_get_nbt";
        int timeTick = compoundTag.getInt(time_curios_tick_time_nbt);
        if(livingEntity instanceof ServerPlayer player && !compoundTag.getBoolean(timeKey)){
            if(timeTick<4*60*60*20){
                compoundTag.putInt(time_curios_tick_time_nbt, timeTick+1);
            }else{
                player.addItem(new ItemStack(FCAItemRegister.TimeCurios.get()));
                compoundTag.putBoolean(timeKey, true);
            }
        }
        //如我所书
        var skyKey = "sky_curios_get_nbt";
        var earthKey = "earth_curios_get_nbt";
        var oceanKey = "ocean_curios_get_nbt";  //名字随意
        var romanceKey = "romance_curios_get_nbt";
        var worldbearingKey = "worldbearing_curios_get_nbt";
        var deathKey = "death_curios_get_nbt";
        var strifeKey = "strife_curios_get_nbt";
        var trickeryKey = "trickery_curios_get_nbt";
        var lawKey = "law_curios_get_nbt";
        var passageKey = "passage_curios_get_nbt";
        var bookKey = "book_get_nbt";
        if(livingEntity instanceof ServerPlayer player && !compoundTag.getBoolean(bookKey)
        && compoundTag.getBoolean(skyKey) && compoundTag.getBoolean(earthKey) && compoundTag.getBoolean(oceanKey)
                && compoundTag.getBoolean(romanceKey) && compoundTag.getBoolean(worldbearingKey) && compoundTag.getBoolean(reasonKey)
                && compoundTag.getBoolean(deathKey) && compoundTag.getBoolean(strifeKey) && compoundTag.getBoolean(trickeryKey)
                && compoundTag.getBoolean(timeKey) && compoundTag.getBoolean(lawKey) && compoundTag.getBoolean(passageKey)
        ){
                player.addItem(new ItemStack(FCACraftItemRegister.AsWrittenByWhom.get()));
                compoundTag.putBoolean(bookKey, true);
        }
        //门径
        var passageKeyOVERWORLD = "passage_over_world";
        var passageKeyNETHER = "passage_nether";
        var passageKeyEND = "passage_end";
        if(livingEntity instanceof ServerPlayer player && !compoundTag.getBoolean(passageKey) ){
            //获取玩家当前维度
            ResourceKey<Level> levelResourceKey = player.level().dimension();
            if (levelResourceKey.equals(Level.OVERWORLD)) {
                compoundTag.putBoolean(passageKeyOVERWORLD, true);
            } else if (levelResourceKey.equals(Level.NETHER)) {
                compoundTag.putBoolean(passageKeyNETHER, true);
            } else if (levelResourceKey.equals(Level.END)) {
                compoundTag.putBoolean(passageKeyEND, true);
            }
            if(compoundTag.getBoolean(passageKeyOVERWORLD) && compoundTag.getBoolean(passageKeyNETHER) && compoundTag.getBoolean(passageKeyEND) ){
                player.addItem(new ItemStack(FCAItemRegister.PassageCurios.get()));
                compoundTag.putBoolean(passageKey, true);
            }
        }
        //大地
        var earthA = "earth_a";
        var earthB = "earth_b";
        var earthC = "earth_c";
        var earthD = "earth_d";
        var earthE = "earth_e";
        var earthF = "earth_f";
        if(livingEntity instanceof ServerPlayer player && !compoundTag.getBoolean(earthKey) ) {
            Level level = player.level();  // 获取玩家所在世界
            BlockPos pos = player.blockPosition();  // 获取玩家当前位置
            ResourceKey<Biome> biome = level.getBiome(pos).unwrapKey().get();  // 获取当前位置的群系
            //平原群系
            if (
                    biome.equals(Biomes.PLAINS) ||
                            biome.equals(Biomes.SUNFLOWER_PLAINS) ||
                            biome.equals(Biomes.SNOWY_PLAINS) ||
                            biome.equals(Biomes.ICE_SPIKES)
            ) {
                compoundTag.putBoolean(earthA, true);
            }
            //海洋群系
            if (
                    biome.equals(Biomes.OCEAN) ||
                            biome.equals(Biomes.COLD_OCEAN) ||
                            biome.equals(Biomes.DEEP_COLD_OCEAN) ||
                            biome.equals(Biomes.DEEP_OCEAN) ||
                            biome.equals(Biomes.DEEP_LUKEWARM_OCEAN) ||
                            biome.equals(Biomes.LUKEWARM_OCEAN) ||
                            biome.equals(Biomes.DEEP_FROZEN_OCEAN) ||
                            biome.equals(Biomes.FROZEN_OCEAN) ||
                            biome.equals(Biomes.WARM_OCEAN)
            ) {
                compoundTag.putBoolean(earthB, true);
            }
            //森林群系
            if (
                    biome.equals(Biomes.FOREST) ||
                            biome.equals(Biomes.TAIGA) ||
                            biome.equals(Biomes.SNOWY_TAIGA) ||
                            biome.equals(Biomes.OLD_GROWTH_PINE_TAIGA) ||
                            biome.equals(Biomes.OLD_GROWTH_SPRUCE_TAIGA) ||
                            biome.equals(Biomes.FLOWER_FOREST) ||
                            biome.equals(Biomes.BIRCH_FOREST) ||
                            biome.equals(Biomes.OLD_GROWTH_BIRCH_FOREST) ||
                            biome.equals(Biomes.DARK_FOREST) ||
                            biome.equals(Biomes.JUNGLE) ||
                            biome.equals(Biomes.SPARSE_JUNGLE) ||
                            biome.equals(Biomes.BAMBOO_JUNGLE)
            ) {
                compoundTag.putBoolean(earthC, true);
            }
            //山地群系
            if (
                    biome.equals(Biomes.JAGGED_PEAKS) ||
                            biome.equals(Biomes.FROZEN_PEAKS) ||
                            biome.equals(Biomes.STONY_PEAKS) ||
                            biome.equals(Biomes.WINDSWEPT_HILLS) ||
                            biome.equals(Biomes.WINDSWEPT_GRAVELLY_HILLS) ||
                            biome.equals(Biomes.WINDSWEPT_FOREST) ||
                            biome.equals(Biomes.WINDSWEPT_SAVANNA) ||
                            biome.equals(Biomes.SNOWY_SLOPES)
            ) {
                compoundTag.putBoolean(earthD, true);
            }
            //旱地群系
            if (
                    biome.equals(Biomes.DESERT) ||
                            biome.equals(Biomes.BADLANDS) ||
                            biome.equals(Biomes.ERODED_BADLANDS) ||
                            biome.equals(Biomes.SAVANNA) ||
                            biome.equals(Biomes.SAVANNA_PLATEAU)
            ) {
                compoundTag.putBoolean(earthE, true);
            }
            //湿地群系
            if (
                    biome.equals(Biomes.SWAMP) ||
                            biome.equals(Biomes.MANGROVE_SWAMP) ||
                            biome.equals(Biomes.RIVER) ||
                            biome.equals(Biomes.FROZEN_RIVER) ||
                            biome.equals(Biomes.BEACH) ||
                            biome.equals(Biomes.SNOWY_BEACH) ||
                            biome.equals(Biomes.STONY_SHORE)
            ) {
                compoundTag.putBoolean(earthF, true);
            }
            if(compoundTag.getBoolean(earthA) && compoundTag.getBoolean(earthB) && compoundTag.getBoolean(earthC) &&
                    compoundTag.getBoolean(earthD) && compoundTag.getBoolean(earthE) && compoundTag.getBoolean(earthF) ){
                player.addItem(new ItemStack(FCAItemRegister.EarthCurios.get()));
                compoundTag.putBoolean(earthKey, true);
            }
        }
    }
    //全局存储容器（例如：Map）存放玩家的NBT
    private static final Map<UUID, CompoundTag> playerNBTBackup = new HashMap<>();
    //开局or返还
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity().level().isClientSide) return;
        var player = event.getEntity();
        var data = player.getPersistentData();
        var key1 = "hatred_inundate_give";  //名字随意
        if (!data.getBoolean(key1)) {
            player.getInventory().add(FCAItemRegister.HatredInundate.get().getDefaultInstance());
            data.putBoolean(key1, true);
        }
        var key2 = "strawberry_roselle_give";
        if (!data.getBoolean(key2)) {
            player.getInventory().add(FCASponsorItemRegister.StrawberryRoselle.get().getDefaultInstance());
            data.putBoolean(key2, true);
        }
    }
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.getEntity().level().isClientSide) return;

        Player original = event.getOriginal();
        Player newPlayer = event.getEntity();

        CompoundTag originalData = original.getPersistentData();
        CompoundTag newData = newPlayer.getPersistentData();

        String[] keys = {
                "hatred_inundate_give",
                "strawberry_roselle_give",
                "sky_curios_get_nbt",
                "earth_curios_get_nbt",
                "earth_a",
                "earth_b",
                "earth_c",
                "earth_d",
                "earth_e",
                "earth_f",
                "ocean_curios_get_nbt",
                "romance_curios_get_nbt",
                "worldbearing_curios_get_nbt",
                "reason_curios_get_nbt",
                "strife_curios_get_nbt",
                "death_curios_get_nbt",
                "trickery_curios_get_nbt",
                "law_curios_get_nbt",
                "time_curios_get_nbt",
                "time_curios_tick_time",
                "passage_curios_get_nbt",
                "passage_over_world",
                "passage_nether",
                "passage_end",
                "coronal_radiance_number",
                "coronal_radiance_damage",
                "acheron_curios_get_nbt"
        };

        for (String key : keys) {
            if (originalData.contains(key)) {
                // 根据存储类型选择对应的get方法
                Tag tag = originalData.get(key);
                if (tag instanceof ByteTag) {
                    boolean value = originalData.getBoolean(key);
                    newData.putBoolean(key, value);
                } else if (tag instanceof IntTag) {
                    int value = originalData.getInt(key);
                    newData.putInt(key, value);
                } else if (tag instanceof StringTag) {
                    String value = originalData.getString(key);
                    newData.putString(key, value);
                }
                // 可以根据需要添加其他类型
            }
        }
    }
}
