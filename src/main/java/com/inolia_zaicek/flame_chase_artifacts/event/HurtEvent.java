package com.inolia_zaicek.flame_chase_artifacts.event;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.damage.FCADamageType;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAEffectsRegister;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static net.minecraft.tags.DamageTypeTags.IS_FIRE;

public class HurtEvent {
    private static final UUID uuid = UUID.fromString("7279469D-4802-D726-D589-0F8F99193C2E");
    private static final String Firestorm_Surge_TIME_NBT = FlameChaseArtifacts.MODID + ":firestorm_surge_time";
    private static final String Firestorm_Surge_Number_NBT = FlameChaseArtifacts.MODID + ":firestorm_surge_number";
    private static final String kill_red_eye_TIME_NBT = FlameChaseArtifacts.MODID + ":kill_red_eye_time";
    private static final String kill_red_eye_Number_NBT = FlameChaseArtifacts.MODID + ":kill_red_eye_number";
    private static final String meteoric_wings_TIME_NBT = FlameChaseArtifacts.MODID + ":meteoric_wings_time";
    private static final String kinetic_stimulus_sword_TIME_NBT = FlameChaseArtifacts.MODID + ":kinetic_stimulus_sword_time";
    private static final String mad_joke_engine_TIME_NBT = FlameChaseArtifacts.MODID + ":mad_joke_engine_time";
    private static final String mad_joke_engine_Number_NBT = FlameChaseArtifacts.MODID + ":mad_joke_engine_number";
    private static final String substance_decomposition_liquid_TIME_NBT = FlameChaseArtifacts.MODID + ":substance_decomposition_liquid_time";
    private static final String perpetual_motion_machine_TIME_NBT = FlameChaseArtifacts.MODID + ":perpetual_motion_machine_time";
    private static final String perpetual_motion_machine_Number_NBT = FlameChaseArtifacts.MODID + ":perpetual_motion_machine_number";
    private static final String electromagnetic_catapult_TIME_NBT = FlameChaseArtifacts.MODID + ":electromagnetic_catapult_time";
    private static final String coronal_radiance_damage = FlameChaseArtifacts.MODID + ":coronal_radiance_damage";
    private static final String deepest_dark_Number_NBT = FlameChaseArtifacts.MODID + ":deepest_dark_number";
    private static final String deepest_dark_TIME_NBT = FlameChaseArtifacts.MODID + ":deepest_dark_time";
    private static final String eye_of_twilight_Number_NBT = FlameChaseArtifacts.MODID + ":eye_of_twilight_number";
    private static final String deepest_dark_Finish_nbt = FlameChaseArtifacts.MODID + ":deepest_dark_finish";
    private static final String evernight_Number_NBT = FlameChaseArtifacts.MODID + ":evernight_number";
    private static final String evernight_damage_NBT = FlameChaseArtifacts.MODID + ":evernight_damage";
    private static final String coin_of_whimsy_Number_NBT = FlameChaseArtifacts.MODID + ":coin_of_whimsy_number";
    private static final String permansor_terrae_TIME_NBT = FlameChaseArtifacts.MODID + ":permansor_terrae_time";
    private static final String mnestia_Number_NBT = FlameChaseArtifacts.MODID + ":mnestia_number";
    private static final String mnestia_TIME_NBT = FlameChaseArtifacts.MODID + ":mnestia_time";
    private static final String talanton_Number_NBT = FlameChaseArtifacts.MODID + ":talanton_number";
    private static final String acheron_Number_NBT = FlameChaseArtifacts.MODID + ":acheron_number";
    //击破进度
    static final String break_progress_NBT = FlameChaseArtifacts.MODID + ":break_progress";
    public static final TagKey<Item> break_curios = TagKey.create(Registries.ITEM,new ResourceLocation("flame_chase_artifacts","break_curios"));
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        LivingEntity attacked = event.getEntity();
        var strifeKey = "strife_curios_get_nbt";
        //挨打
        if (event.getEntity() != null) {
            CompoundTag compoundTag = event.getEntity().getPersistentData();
            //基础数额（都是乘算，岁月的减伤与德谬歌减伤是1-X然后乘，大部分都是“原数额*XXX”
            float number = 1;
            //昔涟
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.PristineLove.get())) {
                //有源
                if (event.getSource().getEntity() != null || event.getSource().getDirectEntity() != null) {
                    number *= (float) (1 - FCAconfig.egoCuriosHasSourseDamageDown.get() * 1);
                } else {
                    number *= (float) (1 - FCAconfig.egoCuriosNoSourseDamage.get() * 1);
                }
            }
            //德谬歌+律法
            if (event.getSource().getEntity() instanceof LivingEntity mob) {
                LivingEntity livingEntity = event.getEntity();
                if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.LawCurios.get())) {
                    float mobHp = mob.getHealth();
                    float livingEntityHp = livingEntity.getHealth();
                    if (mobHp != livingEntityHp) {
                        //怪物大于玩家
                        if (mobHp > livingEntityHp) {
                            //生命值差额
                            float dhp = Math.min(80, mobHp - livingEntityHp);
                            //增幅强度*生命值差额进度
                            number *= 1 - (float) (1 * FCAconfig.lawEgoCurios.get()) * (dhp / 80);
                        } else if (livingEntityHp > mobHp) {
                            //生命值差额
                            float dhp = Math.min(80, livingEntityHp - mobHp);
                            //增幅强度*生命值差额进度
                            number *= 1 - (float) (1 * FCAconfig.lawEgoCurios.get()) * (dhp / 80);
                        }
                    }
                }
            } else if (event.getSource().getDirectEntity() instanceof LivingEntity mob) {
                LivingEntity livingEntity = event.getEntity();
                if (FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.PristineLove.get()) && FCAUtil.isCurioEquipped(livingEntity, FCAItemRegister.LawCurios.get())) {
                    float mobHp = mob.getHealth();
                    float livingEntityHp = livingEntity.getHealth();
                    if (mobHp != livingEntityHp) {
                        //怪物大于玩家
                        if (mobHp > livingEntityHp) {
                            //生命值差额
                            float dhp = Math.min(80, mobHp - livingEntityHp);
                            //增幅强度*生命值差额进度
                            number *= 1 - (float) (1 * FCAconfig.lawEgoCurios.get()) * (dhp / 80);
                        } else if (livingEntityHp > mobHp) {
                            //生命值差额
                            float dhp = Math.min(80, livingEntityHp - mobHp);
                            //增幅强度*生命值差额进度
                            number *= 1 - (float) (1 * FCAconfig.lawEgoCurios.get()) * (dhp / 80);
                        }
                    }
                }
            }
            /// 负世
            if (event.getSource().is(IS_FIRE)) {
                //铁墓
                if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.HatredInundate.get()) && !FCAUtil.isCurioEquipped(attacked, FCAItemRegister.WorldBearingCurios.get())) {
                    number *= FCAconfig.worldbearingCurse.get();
                }
                //昔涟||负世饰品
                if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.PristineLove.get()) || FCAUtil.isCurioEquipped(attacked, FCAItemRegister.WorldBearingCurios.get())) {
                    number *= FCAconfig.worldbearingCurse.get();
                }
                //加冕
                if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.OriginSin.get())) {
                    number *= FCAconfig.worldbearingOverCurse.get();
                }
            }
            //大地饰品
            if (attacked.onGround()) {
                if ( FCAUtil.isCurioEquipped(attacked, FCAItemRegister.OriginSin.get())||FCAUtil.isCurioEquipped(attacked, FCAItemRegister.EarthCurios.get())){
                    number *= (float) (1 - FCAconfig.earthCurios.get());
                }
            }
            //昔涟+纷争
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.StrifeCurios.get()) && FCAUtil.isCurioEquipped(attacked, FCAItemRegister.PristineLove.get())) {
                float hp = attacked.getHealth();
                float mhp = attacked.getMaxHealth();
                float strifeNumber = (float) (FCAconfig.strifeEgoCurios.get() * 1);
                //最大生命值-当前生命值=已损失生命值 /最大生命值=已损失比例————比如100血只有11，那就是89%*最大减伤
                float dhp = ((mhp - hp) / mhp);
                number *= (1 - dhp * strifeNumber);
            }
            //昔涟+岁月
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.PristineLove.get()) && FCAUtil.isCurioEquipped(attacked, FCAItemRegister.TimeCurios.get())) {
                if (event.getSource().getEntity() instanceof LivingEntity mob) {
                    LivingEntity attacker = attacked.getLastAttacker();
                    if (attacker == mob && attacker != null) {
                        number *= (float) (1 - FCAconfig.timeEgoCurios.get());
                    }
                } else if (event.getSource().getDirectEntity() instanceof LivingEntity mob) {
                    LivingEntity attacker = attacked.getLastAttacker();
                    if (attacker == mob && attacker != null) {
                        number *= (float) (1 - FCAconfig.timeEgoCurios.get());
                    }
                }
            }
            //脆弱
            if(attacked.hasEffect(FCAEffectsRegister.Fragility.get())){
                number*=1.175F;
            }
            //暗流
            if(attacked.hasEffect(FCAEffectsRegister.Phagousa.get())){
                number*=1.5F;
            }
            //晨昏之眼
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.EyeOfTwilight.get()) ){
                attacked.heal(attacked.getMaxHealth()*0.05F+1);
            }
            //盗火
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.DeepestDark.get()) ){
                int deepsetNumber = compoundTag.getInt(deepest_dark_Number_NBT);
                if(deepsetNumber>0){
                    number*=1-(deepsetNumber*0.2F);
                }
            }
            //流萤
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.Firefly.get()) ){
                //已损失血量，距离70%血量还差多少
                float hpNumber = (float) ( ( attacked.getMaxHealth()-attacked.getHealth() )/(attacked.getMaxHealth()*0.7F) );
                number*=1-hpNumber*0.5F;
            }
            ///伤害减免属性
            //实体伤害减免属性叠加
            double damageDown = attacked.getAttributeValue(FCAAttributes.DAMAGE_REDUCTION.get());
            if(damageDown>0){
                number*= (float) ((float) 1-damageDown);
            }
                float damage = event.getAmount() * number;
                //门径判定
                if (event.getSource().getEntity() != null || event.getSource().getDirectEntity() != null) {
                    //铁墓
                    if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.HatredInundate.get()) && !FCAUtil.isCurioEquipped(attacked, FCAItemRegister.PassageCurios.get())
                    ) {
                        damage += (float) (FCAconfig.passageCurse.get() * attacked.getMaxHealth());
                    }
                    //加冕
                    if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.OriginSin.get())
                    ) {
                        damage += (float) (FCAconfig.passageOverCurse.get() * attacked.getMaxHealth());
                    }
                    //门径or德谬歌
                    if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.PristineLove.get()) || FCAUtil.isCurioEquipped(attacked, FCAItemRegister.PassageCurios.get())
                    ) {
                        float hpNumber = (float) (attacked.getMaxHealth() * FCAconfig.passageBlessing.get());
                        //减伤后依然达到阈值
                        if (damage > hpNumber) {
                            damage = hpNumber;
                        }
                    }
                }
                //永夜之帷记录
                if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.Evernight.get()) ){
                    float finish = damage/attacked.getMaxHealth();
                    if(finish>0){
                        compoundTag.putInt(evernight_damage_NBT, (int) (finish*100));
                    }
                    int damageNumber = compoundTag.getInt(evernight_Number_NBT);
                    compoundTag.putInt(evernight_Number_NBT, damageNumber+1);
                }
                ///最终结算
                //诡计闪避
                if(FCAUtil.isCurioEquipped(event.getEntity(), FCAItemRegister.TrickeryCurios.get())
                        && FCAUtil.isCurioEquipped(event.getEntity(), FCAItemRegister.PristineLove.get())){
                    Random random = new Random();
                    if (random.nextInt(100) <= FCAconfig.trickeryEgoCurios.get()) {
                        event.setAmount(0);
                    }else{
                        event.setAmount(damage);
                    }
                }else {
                    event.setAmount(damage);
                }
                //纷争获取
                if(event.getEntity() instanceof ServerPlayer player && FCAUtil.isCurioEquipped(player, FCAItemRegister.HatredInundate.get()) && event.getAmount()<player.getHealth() ){

                    if(!compoundTag.getBoolean(strifeKey) && damage>=player.getMaxHealth()*0.9F && player.getHealth()>0){
                        player.addItem(new ItemStack( FCAItemRegister.StrifeCurios.get() ));
                        compoundTag.putBoolean(strifeKey, true);
                    }
                }
        }
        //攻击
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            float number = 1;
            CompoundTag compoundTag = attacker.getPersistentData();
            ///击破——击破时敌人默认受到10%独立易伤，与超击破独立
            //判断有无击破饰品
            if(FCAUtil.curiosHasTag(attacker, String.valueOf(break_curios))){
                var map = attacked.getActiveEffectsMap();
                //击破进度x100【1%击破进度就是100数值
                CompoundTag breakCompoundTag = attacked.getPersistentData();
                int breakProgress = breakCompoundTag.getInt(break_progress_NBT);
                //击破效率
                float breakEfficiency =  (float) attacker.getAttributeValue(FCAAttributes.Break_Efficiency.get());
                //击破倍率
                float breakDamage = (float)  attacker.getAttributeValue(FCAAttributes.Break_Damage.get());
                //超击破倍率
                float breakAmplifier = (float) attacker.getAttributeValue(FCAAttributes.Break_Amplifier.get());
                //击破时长（基础5*20tick=5s）
                int breakTime = (int) attacker.getAttributeValue(FCAAttributes.Break_Time.get());
                //如果未击破
                if(!attacked.hasEffect(FCAEffectsRegister.ToughnessBreak.get())) {
                    ///削韧计算——初始削韧进度+100，满10000破韧
                    //结算韧性
                    int finishBreakProgress = (int) (breakProgress + breakProgress * breakEfficiency);
                    //精英敌人，削韧减半
                    if(FCAUtil.isBossEntity(attacked.getType())){
                        finishBreakProgress*= (int) (0.5F*finishBreakProgress);
                    }
                    compoundTag.putInt(break_progress_NBT, finishBreakProgress);
                    //击破
                    if (finishBreakProgress >= 10000&&breakTime>0) {
                        //击破伤害增幅
                        number*=1+breakDamage;
                        //击破状态赋予
                        attacked.addEffect(new MobEffectInstance(FCAEffectsRegister.ToughnessBreak.get(), breakTime, 0));
                        if (!attacked.hasEffect(FCAEffectsRegister.ToughnessBreak.get())) {map.put(FCAEffectsRegister.ToughnessBreak.get(),
                                new MobEffectInstance(FCAEffectsRegister.ToughnessBreak.get(), breakTime, 0));
                        }
                        //如果有流萤
                        if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.Firefly.get())){
                            attacked.addEffect(new MobEffectInstance(FCAEffectsRegister.FromShatteredSkyIFreeFall.get(), 7*20, 0));
                        }
                    }
                    //后面记得加上未触发击破时，大丽花饰品按比例触发超击破
                }
                //如果击破了
                else{
                    number*=1+breakAmplifier;
                }
            }
            //昔涟13饰品增伤
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                var mobList = FCAUtil.mobList((int) ((FCAconfig.egoCuriosRange.get() - 1) / 2), livingEntity);
                var playerList = FCAUtil.PlayerList((int) ((FCAconfig.egoCuriosRange.get() - 1) / 2), livingEntity);
                for (Mob mobs : mobList) {
                    //有主人的随从——且有全套饰品
                    if ((mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null&& ownableEntity.getOwner() == livingEntity )
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.SkyCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.EarthCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.OceanCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.RomanceCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.WorldBearingCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.ReasonCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.TrickeryCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.StrifeCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.DeathCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.TimeCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.LawCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.PassageCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.PristineLove.get())
                    ) {
                        number *= (float) (1 + FCAconfig.egoCuriosAttack.get() * 1);
                    }
                }
                for (Player player : playerList) {
                    if (FCAUtil.isCurioEquipped(player, FCAItemRegister.SkyCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.EarthCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.OceanCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.RomanceCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.WorldBearingCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.ReasonCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.TrickeryCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.StrifeCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.DeathCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.TimeCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.LawCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.PassageCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.PristineLove.get())
                    ) {
                        number *= (float) (1 + FCAconfig.egoCuriosAttack.get() * 1);
                    }
                }
            }
            //昔涟+海洋
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.PristineLove.get()) && FCAUtil.isCurioEquipped(attacker, FCAItemRegister.OceanCurios.get())) {
                int neutralAndHarmfulCount = 0;
                for (MobEffectInstance effect : attacked.getActiveEffects()) {
                    // 判断是否为NEUTRAL或Harmful
                    boolean isNEUTRAL = effect.getEffect().getCategory() == MobEffectCategory.NEUTRAL;
                    boolean isHarmful = effect.getEffect().getCategory() == MobEffectCategory.HARMFUL;
                    // 统计非NEUTRAL且非Harmful的效果
                    if (isNEUTRAL || isHarmful) {
                        neutralAndHarmfulCount++;
                    }
                }
                if (neutralAndHarmfulCount > 0) {
                    number *= (float) (1 + neutralAndHarmfulCount * FCAconfig.oceanEgoCurios.get());
                }
            }
            //昔涟+负世
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.WorldBearingCurios.get()) && FCAUtil.isCurioEquipped(attacker, FCAItemRegister.PristineLove.get())
                    && FCAUtil.isBossEntity(attacked.getType())) {
                number *= (float) (1 + FCAconfig.worldbearingEgoCurios.get());
            }
            //铁墓——律法
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.HatredInundate.get()) && !FCAUtil.isCurioEquipped(attacker, FCAItemRegister.LawCurios.get())) {
                number *= FCAconfig.lawCurse.get();
            }
            //昔涟||律法
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.PristineLove.get()) || FCAUtil.isCurioEquipped(attacker, FCAItemRegister.LawCurios.get())) {
                number *= FCAconfig.lawBlessing.get();
            }
            //加冕——律法
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.OriginSin.get())) {
                number *= FCAconfig.lawOverCurse.get();
            }
            //律法饰品本体
            float mobHp = attacked.getHealth();
            float attackerHp = attacker.getHealth();
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.LawCurios.get()) || FCAUtil.isCurioEquipped(attacker, FCAItemRegister.OriginSin.get())) {
                //怪物大于玩家
                if (mobHp > attackerHp) {
                    //生命值差额
                    float dhp = Math.min(80, mobHp - attackerHp);
                    //增幅强度*生命值差额进度
                    number *= (float) ((FCAconfig.lawCurios.get()) * (dhp / 80));
                } else if (attackerHp > mobHp) {
                    //生命值差额
                    float dhp = Math.min(80, attackerHp - mobHp);
                    //增幅强度*生命值差额进度
                    number *= (float) ((FCAconfig.lawCurios.get()) * (dhp / 80));
                }
            }
            //纷争饰品||加冕 增伤
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.StrifeCurios.get()) || FCAUtil.isCurioEquipped(attacker, FCAItemRegister.OriginSin.get())) {
                float hp = attacker.getHealth();
                float mhp = attacker.getMaxHealth();
                float strifeNumber = (float) (FCAconfig.strifeCurios.get() * 1);
                //最大生命值-当前生命值=已损失生命值 /最大生命值=已损失比例————比如100血只有11，那就是89%增伤
                float dhp = ((mhp - hp) / mhp);
                number *= (1 + dhp * strifeNumber);
            }
            /// 货币战争部分hbzz
            //基础伤害增幅
            float damageAmplifier = 0;
            /// 幸运一击
            //基础伤害增幅
            float finalLuckHitDamage = 0;
            //基础概率
            float finalLuckHitChance = 0;
            //实体伤害增幅属性叠加
            double damageUp = attacker.getAttributeValue(FCAAttributes.DAMAGE_AMPLIFIER.get());
            if (damageUp > 0) {
                damageAmplifier += (float) damageUp;
            }
            //冷却
            compoundTag.putInt(electromagnetic_catapult_TIME_NBT, 200);
            compoundTag.putInt(deepest_dark_TIME_NBT, 60);
            attacker.removeEffect(FCAEffectsRegister.ElectromagneticCatapult.get());
            //反重力皮靴
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.AntiGravBoots.get())) {
                int AntiGravBootsNumber = FCAUtil.getCuriosCount(attacker, FCAItemRegister.AntiGravBoots.get());
                if (attacker.hasEffect(FCAEffectsRegister.AntiGravBoots.get())) {
                    int effectLevel = attacker.getEffect(FCAEffectsRegister.AntiGravBoots.get()).getAmplifier();
                    attacker.addEffect(new MobEffectInstance(FCAEffectsRegister.AntiGravBoots.get(), 200, Math.min((15 * AntiGravBootsNumber - 1), effectLevel + AntiGravBootsNumber)));
                } else {
                    attacker.addEffect(new MobEffectInstance(FCAEffectsRegister.AntiGravBoots.get(), 200, AntiGravBootsNumber - 1));
                }
            }
            //火力风暴潮
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.FirestormSurge.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.FirestormSurge.get());
                //层数
                int currentTime = compoundTag.getInt(Firestorm_Surge_Number_NBT);
                //数量*层数*每层增幅
                damageAmplifier += curiosCount * currentTime * 0.05f;
                //最高10层
                compoundTag.putInt(Firestorm_Surge_Number_NBT, Math.min(10, currentTime + curiosCount));
                compoundTag.putInt(Firestorm_Surge_TIME_NBT, 200);
            }
            ///脆弱状态放在上面受伤部分计算
            //步步生花
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.BloomingStepByStep.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.BloomingStepByStep.get());
                if (attacker.getHealth() > attacker.getMaxHealth() * 0.7f) {
                    damageAmplifier += curiosCount * 0.3f;
                }
            }
            //光速螺旋桨
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.LightSpeedPropeller.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.LightSpeedPropeller.get());
                float speed = (float) attacker.getAttributeValue(Attributes.MOVEMENT_SPEED);
                if (speed > 0.1F) {
                    //速度-1后，0.1的个数
                    float speedNumber = speed - 0.099F - 0.001F;
                    int speedNumber1 = (int) (speedNumber / 0.01F);
                    if (speedNumber > 0) {
                        damageAmplifier += curiosCount * 0.02f * speedNumber1;
                    }
                }
            }
            //杀红眼【判断nbt数额，击杀叠层不在此处
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.KillRedEye.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.KillRedEye.get());
                //层数
                int nbtNumber = compoundTag.getInt(kill_red_eye_Number_NBT);
                int nbtTime = compoundTag.getInt(kill_red_eye_TIME_NBT);
                if (nbtNumber > 0 && nbtTime > 0) {
                    //数量*层数*每层增幅
                    damageAmplifier += curiosCount * nbtNumber * 0.125f;
                }
            }
            //流星飞翼
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.MeteoricWings.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.MeteoricWings.get());
                //冷却
                int nbtTime = compoundTag.getInt(meteoric_wings_TIME_NBT);
                if (nbtTime == 0) {
                    attacker.heal(attacker.getMaxHealth() * 0.05F * curiosCount);
                    attacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 8 * 20, curiosCount - 1));
                    compoundTag.putInt(meteoric_wings_TIME_NBT, 20 * 4);
                }
            }
            //动能激发剑
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.KineticStimulusSword.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.KineticStimulusSword.get());
                //冷却
                int nbtTime = compoundTag.getInt(kinetic_stimulus_sword_TIME_NBT);
                if (nbtTime == 0) {
                    attacker.heal(attacker.getMaxHealth() * 0.05F * curiosCount);
                    if (attacker.hasEffect(MobEffects.DAMAGE_BOOST)) {
                        int effectLevel = attacker.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier();
                        attacker.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 8 * 20, Math.min(2, curiosCount + effectLevel)));
                    } else {
                        attacker.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 8 * 20, Math.min(2, curiosCount - 1)));
                    }
                    compoundTag.putInt(kinetic_stimulus_sword_TIME_NBT, 20 * 4);
                }
            }
            //斩首行动
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.BeheadingOperation.get()) && FCAUtil.isBossEntity(attacked.getType())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.BeheadingOperation.get());
                number *= 1 + 0.3F * curiosCount;
            }
            //热血沸腾拳
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.HotBloodedBoilingFist.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.HotBloodedBoilingFist.get());
                if (attacker.getMaxHealth() > 20) {
                    //HP-20后，2的个数
                    int hpNumber = (int) ((attacker.getMaxHealth() - 20) / 2);
                    if (hpNumber > 0) {
                        finalLuckHitChance += Math.min(40 * curiosCount, curiosCount * 2 * hpNumber);
                    }
                }
            }
            //冷笑话引擎
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.MadJokeEngine.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.MadJokeEngine.get());
                //层数
                int currentTime = compoundTag.getInt(mad_joke_engine_Number_NBT);
                //数量*层数*每层增幅
                finalLuckHitChance += curiosCount * currentTime * 2.5f;
                //最高10层
                compoundTag.putInt(mad_joke_engine_Number_NBT, Math.min(16, currentTime + curiosCount));
                compoundTag.putInt(mad_joke_engine_TIME_NBT, 200);
            }
            //固定伤害
            float fixedDamage = 0;
            //物质分解液
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.SubstanceDecompositionLiquid.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.SubstanceDecompositionLiquid.get());
                //冷却
                int nbtTime = compoundTag.getInt(substance_decomposition_liquid_TIME_NBT);
                if (nbtTime == 0) {
                    fixedDamage += curiosCount * attacked.getMaxHealth() * 0.01f;
                    compoundTag.putInt(substance_decomposition_liquid_TIME_NBT, 40);
                }
            }
            //追逐星辰+行星钻地弹
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.ChasingStars.get())
                    || FCAUtil.isCurioEquipped(attacker, FCAItemRegister.PlanetaryDrillingBomb.get())) {
                var map = attacked.getActiveEffectsMap();
                attacked.addEffect(new MobEffectInstance(FCAEffectsRegister.Fragility.get(), 200, 0));
                if (!attacked.hasEffect(FCAEffectsRegister.Fragility.get())) {
                    map.put(FCAEffectsRegister.Fragility.get(), new MobEffectInstance(FCAEffectsRegister.Fragility.get(), 200, 0));
                }
            }
            //闪光手榴弹
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.FlashGrenade.get())) {
                var map = attacked.getActiveEffectsMap();
                attacked.addEffect(new MobEffectInstance(FCAEffectsRegister.Weaken.get(), 200, 0));
                if (!attacked.hasEffect(FCAEffectsRegister.Weaken.get())) {
                    map.put(FCAEffectsRegister.Weaken.get(), new MobEffectInstance(FCAEffectsRegister.Weaken.get(), 200, 0));
                }
            }
            //虚弱判断
            if (attacker.hasEffect(FCAEffectsRegister.Weaken.get())) {
                number *= 0.8F;
            }
            //永动机
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.PerpetualMotionMachine.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.PerpetualMotionMachine.get());
                //层数
                int currentNumber = compoundTag.getInt(perpetual_motion_machine_Number_NBT);
                //时间
                int currentTime = compoundTag.getInt(perpetual_motion_machine_TIME_NBT);
                //初次攻击
                if (currentTime == 0 && currentNumber == 0) {
                    //数量*层数*每层增幅
                    damageAmplifier += curiosCount * 0.5f;
                    compoundTag.putInt(perpetual_motion_machine_Number_NBT, 1);
                }
                /// 开始循环
                if (currentTime >= 0) {
                    //非第四次攻击【初次：0，第一次：1：第二次：2，进入1-4循环】
                    if (currentNumber < 4) {
                        compoundTag.putInt(perpetual_motion_machine_Number_NBT, currentNumber + 1);
                    } else {
                        //数量*层数*每层增幅
                        damageAmplifier += curiosCount * 0.5f;
                        compoundTag.putInt(perpetual_motion_machine_Number_NBT, 1);
                    }
                }
                //续时
                compoundTag.putInt(perpetual_motion_machine_TIME_NBT, 200);
            }
            /// 伤害增幅 加入结算
            if (damageAmplifier > 0) {
                number *= (1 + damageAmplifier);
            }
            ///幸运一击判断 并加入结算
            double luckHitChance = attacker.getAttributeValue(FCAAttributes.LUCK_HIT_CHANCE.get());
            if (luckHitChance > 0) {
                finalLuckHitChance += (float) (luckHitChance * 100);
            }
            double luckHitAmplifier = attacker.getAttributeValue(FCAAttributes.LUCK_HIT_AMPLIFIER.get());
            //是否触发
            if (finalLuckHitChance > 0 && luckHitAmplifier > 0) {
                Random random = new Random();
                //额外提升一点判断概率
                if (random.nextInt(100) <= 100 * luckHitChance * 1.3F) {
                    finalLuckHitDamage += (float) luckHitAmplifier;
                }
            }
            if (finalLuckHitDamage > 0) {
                number *= (1 + finalLuckHitDamage);
            }
            /// 黄金裔本体
            //暴击率
            float baseCriticalStrikeChance = 0;
            //爆伤
            float baseCriticalStrikeDamage = 0;
            ///日冕
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.CoronalRadiance.get())) {
                int damage = compoundTag.getInt(coronal_radiance_damage);
                baseCriticalStrikeDamage += (float) damage / 100;
            }
            //晨昏之眼
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.EyeOfTwilight.get())) {
                int healNumber = compoundTag.getInt(eye_of_twilight_Number_NBT);
                if (healNumber > 0) {
                    number *= 1 + ((float) healNumber / 10000);
                    compoundTag.putInt(eye_of_twilight_Number_NBT, 0);
                }
            }
            //盗火
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.DeepestDark.get())) {
                //容器数量
                int deepsetNumber = compoundTag.getInt(deepest_dark_Number_NBT);
                if (deepsetNumber > 0) {
                    number *= 1 + (deepsetNumber * 0.4F);
                    compoundTag.putInt(deepest_dark_Number_NBT, deepsetNumber - 1);
                }
                //灾难之力
                int deepsetFinish = compoundTag.getInt(deepest_dark_Finish_nbt);
                if (deepsetFinish >= 4) {
                    number *= 5;
                    compoundTag.putInt(deepest_dark_Number_NBT, 4);
                }
                var map = attacked.getActiveEffectsMap();
                if (attacked.hasEffect(FCAEffectsRegister.IronTombErosion.get())) {
                    int level = 1 + attacked.getEffect(FCAEffectsRegister.IronTombErosion.get()).getAmplifier();
                    attacker.heal(attacker.getMaxHealth() * level * 0.05F);
                    map.put(FCAEffectsRegister.IronTombErosion.get(), new MobEffectInstance(FCAEffectsRegister.IronTombErosion.get(),
                            200, Math.min(3, level + 1)));
                } else {
                    map.put(FCAEffectsRegister.IronTombErosion.get(), new MobEffectInstance(FCAEffectsRegister.IronTombErosion.get(),
                            200, 0));
                }
            }
            //「磐岩之脊」
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.PermansorTerrae.get())) {
                //缩减时间
                int dragonTime = compoundTag.getInt(permansor_terrae_TIME_NBT);
                if (dragonTime > 40) {
                    compoundTag.putInt(permansor_terrae_TIME_NBT, dragonTime - 10);
                }
                if (attacker.hasEffect(MobEffects.ABSORPTION)) {
                    int level = 1 + attacker.getEffect(MobEffects.ABSORPTION).getAmplifier();
                    number *= 1 + level * 0.23F;
                }
            }
            //裂分之枝
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.Cerces.get()) && attacker instanceof Player player) {
                int experienceLevel = player.experienceLevel;
                if (experienceLevel > 0) {
                    number *= 1 + Math.min(30, experienceLevel) * 0.05F;
                }
                if (FCAUtil.isMeleeAttack(event.getSource())) {
                    var DamageType = FCADamageType.hasSource(attacker.level(), DamageTypes.MAGIC, attacker);
                    var mobList = FCAUtil.mobList(7, player);
                    Mob nearestMob = null;
                    double nearestDist = Double.MAX_VALUE;
                    for (Mob mobs : mobList) {
                        if (mobs != null) {
                            double dist = mobs.distanceTo(player);
                            if (dist < nearestDist) {
                                nearestDist = dist;
                                nearestMob = mobs;
                            }
                        }
                    }
                    int attackNumber = 4;
                    if (nearestMob != null) {
                        if (experienceLevel > 0) {
                            attackNumber = 4;
                        }else{
                            attackNumber = 8;
                        }
                        for (int i = 0; i < attackNumber; i++) {
                            // 攻击找到的实体
                            nearestMob.invulnerableTime = 0;
                            nearestMob.hurt(DamageType, (float) (attacker.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.875F));
                            nearestMob.invulnerableTime = 0;
                        }
                    }
                }
            }
            //万径之门
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.Janus.get()) ) {
                fixedDamage+= (float) (attacker.getMaxHealth()*0.375);
                if(attacker.hasEffect(FCAEffectsRegister.Numinosity.get())) {
                    number *= 1.72F;
                }
            }
            //满溢之杯
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.Phagousa.get())){
                var map = attacked.getActiveEffectsMap();
                int fire = attacked.getRemainingFireTicks();
                map.put(FCAEffectsRegister.Phagousa.get(), new MobEffectInstance(FCAEffectsRegister.Phagousa.get(),
                        200, 0));
                if (attacked.hasEffect(MobEffects.WITHER)) {
                    number*=1.625F;
                }
                if (attacked.hasEffect(MobEffects.POISON)) {
                    number*=1.625F;
                }
                if (fire>0) {
                    number*=1.625F;
                }
            }
            //满溢之杯（周围有
            if (event.getEntity().getAttributeValue(Attributes.ARMOR)>0) {
                var mobList = FCAUtil.mobList(16, attacker);
                for (Mob mobs : mobList) {
                    //实体本身满足条件
                    if (!(attacker instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null && ownableEntity==attacked) && attacker!=mobs) {
                        //周围有穿戴的
                        if(FCAUtil.isCurioEquipped(mobs, FCAItemRegister.Phagousa.get())){
                            number*=0.7F;
                        }
                    }
                }
                var playerList = FCAUtil.PlayerList(16, attacker);
                for (Player player : playerList) {
                    //实体本身满足条件
                    if (!(attacker instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null && ownableEntity==attacked) && attacker!=player) {
                        //周围有穿戴的
                        if(FCAUtil.isCurioEquipped(player, FCAItemRegister.Phagousa.get())){
                            number*=0.7F;
                        }
                    }
                }
            }
            ///天谴之矛
            if(FCAUtil.isCurioEquipped(attacker, FCAItemRegister.LanceOfFury.get())){
                if(attacker.getHealth()<=attacker.getMaxHealth()*0.2F){
                    number *= 3;
                }
                baseCriticalStrikeChance += 50;
                var DamageType = FCAUtil.source(attacked.level(), DamageTypes.MAGIC);
                attacker.invulnerableTime = 0;
                //大于1血，受伤
                if(attacker.getHealth()>1) {
                    float hurtDamage = attacker.getHealth()*0.15F;
                    //如果造成的伤害不致死
                    if(attacker.getHealth()-hurtDamage>0) {
                        attacker.hurt(DamageType, hurtDamage);
                    }else{
                        attacker.hurt(DamageType, hurtDamage-1);
                    }
                }else{
                    attacker.hurt(DamageType, 0);
                }
            }
            /// 灰黯之手
            if(FCAUtil.isCurioEquipped(attacker, FCAItemRegister.HandOfShadow.get())){
                fixedDamage+=attacked.getMaxHealth()*0.025F;
                if(attacker.getMaxHealth()>=100) {
                    baseCriticalStrikeDamage += 50;
                }
                if(attacked.getHealth()>=attacked.getMaxHealth()*0.5){
                    number*=1.5F;
                }
                var DamageType = FCAUtil.source(attacked.level(), DamageTypes.WITHER);
                attacker.invulnerableTime = 0;
                //大于1血，受伤
                if(attacker.getHealth()>1) {
                    float hurtDamage = attacker.getHealth()*0.05F;
                    //如果造成的伤害不致死
                    if(attacker.getHealth()-hurtDamage>0) {
                        attacker.hurt(DamageType, hurtDamage);
                    }else{
                        attacker.hurt(DamageType, hurtDamage-1);
                    }
                }else{
                    attacker.hurt(DamageType, 0);
                }
            }
            //永夜之帷
            if(FCAUtil.isCurioEquipped(attacker, FCAItemRegister.Evernight.get())){
                if(attacker.getMaxHealth()>0){
                    number*=1+ attacker.getMaxHealth()*0.01F;
                    baseCriticalStrikeChance *= attacker.getMaxHealth()*0.03F;
                }
                var DamageType = FCAUtil.source(attacked.level(), DamageTypes.MAGIC);
                attacker.invulnerableTime = 0;
                //大于1血，受伤
                if(attacker.getHealth()>1) {
                    float hurtDamage = attacker.getHealth()*0.05F;
                    //如果造成的伤害不致死
                    if(attacker.getHealth()-hurtDamage>0) {
                        attacker.hurt(DamageType, hurtDamage);
                    }else{
                        attacker.hurt(DamageType, hurtDamage-1);
                    }
                }else{
                    attacker.hurt(DamageType, 0);
                }
                //受伤增幅
                int lastHurtDamage = compoundTag.getInt(evernight_damage_NBT);
                if(lastHurtDamage>0){
                    number*=1+ (float) lastHurtDamage /1000;
                    compoundTag.putInt(evernight_damage_NBT, 0);
                }
                //忆质数量
                int damageNumber = compoundTag.getInt(evernight_Number_NBT);
                if(damageNumber>=8){
                    number*=1 + 2.5F;
                    attacker.heal(attacker.getMaxHealth()*0.5F);
                    compoundTag.putInt(evernight_Number_NBT, 0);
                }
            }
            //刻律
            if(FCAUtil.isCurioEquipped(attacker, FCAItemRegister.Talanton.get())) {
                int militaryMerit = compoundTag.getInt(talanton_Number_NBT);
                baseCriticalStrikeDamage+=0.9F;
                if(attacker.getAttributeValue(Attributes.ATTACK_DAMAGE)>=8){
                    int atkNumber = (int) (attacker.getAttributeValue(Attributes.ATTACK_DAMAGE)-8);
                    if(atkNumber>0){
                        baseCriticalStrikeDamage+= (float) (0.1*atkNumber);
                    }
                }
                if(militaryMerit<6) {
                    compoundTag.putInt(talanton_Number_NBT, militaryMerit + 1);
                    baseCriticalStrikeChance *= militaryMerit*15;
                }else{
                    compoundTag.putInt(talanton_Number_NBT, militaryMerit - 6);
                    baseCriticalStrikeChance *= 100;
                    number*=1+2.88F;
                }
            }
            //黄泉
            if(FCAUtil.isCurioEquipped(attacker, FCAItemRegister.Acheron.get())) {
                if(!FCAUtil.isBossEntity(attacked.getType())){
                    number*=10;
                }
                int attackNumber = compoundTag.getInt(acheron_Number_NBT);
                int neutralAndHarmfulCount = 0;
                for (MobEffectInstance effect : attacked.getActiveEffects()) {
                    // 判断是否为NEUTRAL或Harmful【非正面
                    boolean isNEUTRAL = effect.getEffect().getCategory() == MobEffectCategory.NEUTRAL;
                    boolean isHarmful = effect.getEffect().getCategory() == MobEffectCategory.HARMFUL;
                    // 统计非NEUTRAL且非Harmful的效果
                    if (isNEUTRAL || isHarmful) {
                        neutralAndHarmfulCount++;
                    }
                }
                if(attackNumber+neutralAndHarmfulCount<9){
                    compoundTag.putInt(talanton_Number_NBT, attackNumber + 1);
                }else{
                    number*=6;
                    compoundTag.putInt(talanton_Number_NBT, 0);
                }
            }
            /// 暴击与爆伤
            //其中一个大于零，满足触发条件
            if(baseCriticalStrikeDamage>0||baseCriticalStrikeChance>0){
                baseCriticalStrikeChance+=20;
                baseCriticalStrikeDamage+=0.5F;
                Random random = new Random();
                //基础20暴击
                if (random.nextInt(100) <= baseCriticalStrikeChance*1.3F) {
                    number *= (float) (1+baseCriticalStrikeDamage);
                }
            }
            //死亡饰品本体||加冕
            if(FCAUtil.isCurioEquipped(attacker, FCAItemRegister.DeathCurios.get()) || FCAUtil.isCurioEquipped(attacked, FCAItemRegister.OriginSin.get()) ){
                fixedDamage+= (float) (attacked.getMaxHealth()*FCAconfig.deathCurios.get());
            }
            //阿雅增幅
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.Mnestia.get())){
                float speed = (float) attacker.getAttributeValue(Attributes.MOVEMENT_SPEED);
                if(speed>1) {
                    //速度-1后，0.1的个数
                    int speedNumber = (int) ((speed-1)/0.1F);
                    if(speedNumber>0) {
                        number *= 1 + 0.1f * speedNumber;
                    }
                }
            }
            float finishDamage = event.getAmount() * number + fixedDamage;
            //翻飞之币【硬币基础*10000
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.CoinOfWhimsy.get()) ){
                int coin = compoundTag.getInt(coin_of_whimsy_Number_NBT);
                if(finishDamage>0){
                    compoundTag.putInt(coin_of_whimsy_Number_NBT, (int) (coin+finishDamage*0.24F*10000) );
                }
                //如果处于猫咪怪盗状态
                if(attacker.hasEffect(FCAEffectsRegister.KittyPhantomThief.get())) {
                    fixedDamage+= (float) coin /10000;
                    compoundTag.putInt(coin_of_whimsy_Number_NBT, 0 );
                }
            }
            //阿雅
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.CoinOfWhimsy.get()) && FCAUtil.isMeleeAttack(event.getSource())){
                int romanceNumber = compoundTag.getInt(mnestia_Number_NBT);
                compoundTag.putInt(mnestia_TIME_NBT, 200);
                compoundTag.putInt(mnestia_Number_NBT, romanceNumber+1);
                var DamageType = FCADamageType.hasSource(attacker.level(), DamageTypes.LIGHTNING_BOLT, attacker);
                var mobList = FCAUtil.mobList(3, attacked);
                for (Mob mobs : mobList) {
                    if (!(mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null&& ownableEntity.getOwner() == attacker )) {
                        mobs.invulnerableTime = 0;
                        mobs.hurt(DamageType, finishDamage * 1.26F);
                        mobs.invulnerableTime = 0;
                    }
                }
                if(romanceNumber>=6){
                    attacked.hurt(DamageType, finishDamage * 1.26F);
                }
            }
            //流萤
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.Firefly.get()) && FCAUtil.isMeleeAttack(event.getSource()) ){
                attacker.heal(attacker.getMaxHealth()*0.05F);
                //超击破倍率
                float breakAmplifier = (float) attacker.getAttributeValue(FCAAttributes.Break_Amplifier.get());
                var DamageType = FCADamageType.hasSource(attacker.level(), FCADamageType.TRUEDAMAGE,attacker);
                var mobList = FCAUtil.mobList(3, attacked);
                for (Mob mobs : mobList) {
                    if (!(mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null&& ownableEntity.getOwner() == attacker )) {
                        mobs.invulnerableTime = 0;
                        mobs.hurt(DamageType, finishDamage * 0.5F * breakAmplifier);
                        mobs.invulnerableTime = 0;
                    }
                }
            }
            //确保清除
            attacker.removeEffect(FCAEffectsRegister.KittyPhantomThief.get());
            /// 最终结算
            if(fixedDamage>0){
                event.setAmount(event.getAmount() * number + fixedDamage);
            }else{
                event.setAmount(event.getAmount() * number);
            }
        }else if (event.getSource().getDirectEntity() instanceof LivingEntity attacker) {
            float number = 1;

            CompoundTag compoundTag = attacker.getPersistentData();
            ///击破——击破时敌人默认受到10%独立易伤，与超击破独立
            //判断有无击破饰品
            if(FCAUtil.curiosHasTag(attacker, String.valueOf(break_curios))){
                var map = attacked.getActiveEffectsMap();
                //击破进度x100【1%击破进度就是100数值
                CompoundTag breakCompoundTag = attacked.getPersistentData();
                int breakProgress = breakCompoundTag.getInt(break_progress_NBT);
                //击破效率
                float breakEfficiency =  (float) attacker.getAttributeValue(FCAAttributes.Break_Efficiency.get());
                //击破倍率
                float breakDamage = (float)  attacker.getAttributeValue(FCAAttributes.Break_Damage.get());
                //超击破倍率
                float breakAmplifier = (float) attacker.getAttributeValue(FCAAttributes.Break_Amplifier.get());
                //击破时长（基础5*20tick=5s）
                int breakTime = (int) attacker.getAttributeValue(FCAAttributes.Break_Time.get());
                //如果未击破
                if(!attacked.hasEffect(FCAEffectsRegister.ToughnessBreak.get())) {
                    ///削韧计算——初始削韧进度+100，满10000破韧
                    //结算韧性
                    int finishBreakProgress = (int) (breakProgress + breakProgress * breakEfficiency);
                    //精英敌人，削韧减半
                    if(FCAUtil.isBossEntity(attacked.getType())){
                        finishBreakProgress*= (int) (0.5F*finishBreakProgress);
                    }
                    compoundTag.putInt(break_progress_NBT, finishBreakProgress);
                    //击破
                    if (finishBreakProgress >= 10000&&breakTime>0) {
                        //击破伤害增幅
                        number*=1+breakDamage;
                        //击破状态赋予
                        attacked.addEffect(new MobEffectInstance(FCAEffectsRegister.ToughnessBreak.get(), breakTime, 0));
                        if (!attacked.hasEffect(FCAEffectsRegister.ToughnessBreak.get())) {map.put(FCAEffectsRegister.ToughnessBreak.get(),
                                new MobEffectInstance(FCAEffectsRegister.ToughnessBreak.get(), breakTime, 0));
                        }
                        //如果有流萤
                        if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.Firefly.get())){
                            attacked.addEffect(new MobEffectInstance(FCAEffectsRegister.FromShatteredSkyIFreeFall.get(), 7*20, 0));
                        }
                    }
                    //后面记得加上未触发击破时，大丽花饰品按比例触发超击破
                }
                //如果击破了
                else{
                    number*=1+breakAmplifier;
                }
            }
            //昔涟13饰品增伤
            if(event.getSource().getEntity() instanceof LivingEntity livingEntity){
                var mobList = FCAUtil.mobList( (int)( (FCAconfig.egoCuriosRange.get()-1)/2) , livingEntity);
                var playerList = FCAUtil.PlayerList((int)( (FCAconfig.egoCuriosRange.get()-1)/2), livingEntity);
                for (Mob mobs : mobList) {
                    //有主人的随从——且有全套饰品
                    if ( (mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null&& ownableEntity.getOwner() == livingEntity )
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.SkyCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.EarthCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.OceanCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.RomanceCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.WorldBearingCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.ReasonCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.TrickeryCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.StrifeCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.DeathCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.TimeCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.LawCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.PassageCurios.get())
                            && FCAUtil.isCurioEquipped(mobs, FCAItemRegister.PristineLove.get())
                    ) {
                        number *= (float) (1+FCAconfig.egoCuriosAttack.get()*1);
                    }
                }
                for (Player player : playerList) {
                    if(FCAUtil.isCurioEquipped(player, FCAItemRegister.SkyCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.EarthCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.OceanCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.RomanceCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.WorldBearingCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.ReasonCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.TrickeryCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.StrifeCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.DeathCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.TimeCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.LawCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.PassageCurios.get())
                            && FCAUtil.isCurioEquipped(player, FCAItemRegister.PristineLove.get())
                    ){
                        number *= (float) (1+FCAconfig.egoCuriosAttack.get()*1);
                    }
                }
            }
            //昔涟+海洋
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.PristineLove.get()) && FCAUtil.isCurioEquipped(attacker, FCAItemRegister.OceanCurios.get())) {
                int neutralAndHarmfulCount = 0;
                for (MobEffectInstance effect : attacked.getActiveEffects()) {
                    // 判断是否为NEUTRAL或Harmful【非正面
                    boolean isNEUTRAL = effect.getEffect().getCategory() == MobEffectCategory.NEUTRAL;
                    boolean isHarmful = effect.getEffect().getCategory() == MobEffectCategory.HARMFUL;
                    // 统计非NEUTRAL且非Harmful的效果
                    if (isNEUTRAL || isHarmful) {
                        neutralAndHarmfulCount++;
                    }
                }
                if (neutralAndHarmfulCount > 0) {
                    number *= (float) (1 + neutralAndHarmfulCount * FCAconfig.oceanEgoCurios.get());
                }
            }
            //昔涟+负世
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.WorldBearingCurios.get()) && FCAUtil.isCurioEquipped(attacker, FCAItemRegister.PristineLove.get())
                    && FCAUtil.isBossEntity(attacked.getType()) ) {
                number *= (float) (1 + FCAconfig.worldbearingEgoCurios.get());
            }
            //铁墓——律法
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.HatredInundate.get()) && !FCAUtil.isCurioEquipped(attacker, FCAItemRegister.LawCurios.get())) {
                number *= FCAconfig.lawCurse.get();
            }
            //昔涟||律法
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.PristineLove.get()) || FCAUtil.isCurioEquipped(attacker, FCAItemRegister.LawCurios.get())) {
                number *= FCAconfig.lawBlessing.get();
            }
            //加冕——律法
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.OriginSin.get())) {
                number *= FCAconfig.lawOverCurse.get();
            }
            //律法饰品本体
            float mobHp = attacked.getHealth();
            float attackerHp = attacker.getHealth();
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.LawCurios.get()) || FCAUtil.isCurioEquipped(attacker, FCAItemRegister.OriginSin.get()) ) {
                //怪物大于玩家
                if (mobHp > attackerHp) {
                    //生命值差额
                    float dhp =Math.min(80,mobHp-attackerHp);
                    //增幅强度*生命值差额进度
                    number *= (float) ( (FCAconfig.lawCurios.get() )*(dhp/80) );
                }
                else if (attackerHp > mobHp) {
                    //生命值差额
                    float dhp =Math.min(80,attackerHp-mobHp);
                    //增幅强度*生命值差额进度
                    number *= (float) ( (FCAconfig.lawCurios.get() )*(dhp/80) );
                }
            }
            //纷争饰品||加冕 增伤
            if(FCAUtil.isCurioEquipped(attacker, FCAItemRegister.StrifeCurios.get()) || FCAUtil.isCurioEquipped(attacker, FCAItemRegister.OriginSin.get()) ){
                float hp = attacker.getHealth();
                float mhp = attacker.getMaxHealth();
                float strifeNumber = (float) (FCAconfig.strifeCurios.get()*1);
                //最大生命值-当前生命值=已损失生命值 /最大生命值=已损失比例————比如100血只有11，那就是89%增伤
                float dhp =( (mhp-hp)/mhp );
                number *= (1+dhp*strifeNumber);
            }
            /// 货币战争部分hbzz
            //基础伤害增幅
            float damageAmplifier = 0;
            /// 幸运一击
            //基础伤害增幅
            float finalLuckHitDamage = 0;
            //基础概率
            float finalLuckHitChance = 0;
            //实体伤害增幅属性叠加
            double damageUp = attacker.getAttributeValue(FCAAttributes.DAMAGE_AMPLIFIER.get());
            if(damageUp>0){
                damageAmplifier += (float) damageUp;
            }
            //电磁弹射器冷却
            compoundTag.putInt(electromagnetic_catapult_TIME_NBT, 200);
            compoundTag.putInt(deepest_dark_TIME_NBT, 60);
            attacker.removeEffect(FCAEffectsRegister.ElectromagneticCatapult.get());
            //反重力皮靴
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.AntiGravBoots.get())) {
                int AntiGravBootsNumber = FCAUtil.getCuriosCount(attacker, FCAItemRegister.AntiGravBoots.get());
                if(attacker.hasEffect(FCAEffectsRegister.AntiGravBoots.get())){
                    int effectLevel = attacker.getEffect(FCAEffectsRegister.AntiGravBoots.get()).getAmplifier();
                    attacker.addEffect(new MobEffectInstance(FCAEffectsRegister.AntiGravBoots.get(),200, Math.min( (10*AntiGravBootsNumber-1) , effectLevel+AntiGravBootsNumber ) ));
                }else{
                    attacker.addEffect(new MobEffectInstance(FCAEffectsRegister.AntiGravBoots.get(),200, AntiGravBootsNumber-1 ) );
                }
            }
            //火力风暴潮
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.FirestormSurge.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.FirestormSurge.get());
                //层数
                int currentTime = compoundTag.getInt(Firestorm_Surge_Number_NBT);
                //数量*层数*每层增幅
                damageAmplifier+=curiosCount*currentTime*0.05f;
                //最高10层
                compoundTag.putInt(Firestorm_Surge_Number_NBT, Math.min(10,currentTime+curiosCount) );
                compoundTag.putInt(Firestorm_Surge_TIME_NBT, 200);
            }
            ///脆弱状态放在上面受伤部分计算
            //步步生花
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.BloomingStepByStep.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.BloomingStepByStep.get());
                if(attacker.getHealth()>attacker.getMaxHealth()*0.7f) {
                    damageAmplifier += curiosCount * 0.3f;
                }
            }
            //光速螺旋桨
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.LightSpeedPropeller.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.LightSpeedPropeller.get());
                float speed = (float) attacker.getAttributeValue(Attributes.MOVEMENT_SPEED);
                if(speed>1) {
                    //速度-1后，0.1的个数
                    int speedNumber = (int) ((speed-1)/0.1F);
                    if(speedNumber>0) {
                        damageAmplifier += curiosCount * 0.02f * speedNumber;
                    }
                }
            }
            //杀红眼【判断nbt数额，击杀叠层不在此处
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.KillRedEye.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.KillRedEye.get());
                //层数
                int nbtNumber = compoundTag.getInt(kill_red_eye_Number_NBT);
                int nbtTime = compoundTag.getInt(kill_red_eye_TIME_NBT);
                if(nbtNumber>0&&nbtTime>0) {
                    //数量*层数*每层增幅
                    damageAmplifier += curiosCount * nbtNumber * 0.125f;
                }
            }
            //流星飞翼
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.MeteoricWings.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.MeteoricWings.get());
                //冷却
                int nbtTime = compoundTag.getInt(meteoric_wings_TIME_NBT);
                if(nbtTime==0) {
                    attacker.heal(attacker.getMaxHealth()*0.05F*curiosCount);
                    attacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,8*20,curiosCount-1));
                    compoundTag.putInt(meteoric_wings_TIME_NBT, 20*4);
                }
            }
            //动能激发剑
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.KineticStimulusSword.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.KineticStimulusSword.get());
                //冷却
                int nbtTime = compoundTag.getInt(kinetic_stimulus_sword_TIME_NBT);
                if(nbtTime==0) {
                    attacker.heal(attacker.getMaxHealth()*0.05F*curiosCount);
                    if(attacker.hasEffect(MobEffects.DAMAGE_BOOST)){
                        int effectLevel = attacker.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier();
                        attacker.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,8*20,Math.min(2,curiosCount+effectLevel) ));
                    }else{
                        attacker.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,8*20,Math.min(2,curiosCount-1) ));
                    }
                    compoundTag.putInt(kinetic_stimulus_sword_TIME_NBT, 20*4);
                }
            }
            //斩首行动
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.BeheadingOperation.get()) && FCAUtil.isBossEntity(attacked.getType()) ) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.BeheadingOperation.get());
                number *= 1+0.3F*curiosCount;
            }
            //热血沸腾拳
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.HotBloodedBoilingFist.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.HotBloodedBoilingFist.get());
                if (attacker.getMaxHealth() > 20) {
                    //HP-20后，2的个数
                    int hpNumber = (int) ((attacker.getMaxHealth() - 20) / 2);
                    if (hpNumber > 0) {
                        finalLuckHitChance += Math.min(40 * curiosCount, curiosCount * 2 * hpNumber);
                    }
                }
            }
            //冷笑话引擎
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.MadJokeEngine.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.MadJokeEngine.get());
                //层数
                int currentTime = compoundTag.getInt(mad_joke_engine_Number_NBT);
                //数量*层数*每层增幅
                finalLuckHitChance+=curiosCount*currentTime*2.5f;
                //最高10层
                compoundTag.putInt(mad_joke_engine_Number_NBT, Math.min(16,currentTime+curiosCount) );
                compoundTag.putInt(mad_joke_engine_TIME_NBT, 200);
            }
            //固定伤害
            float fixedDamage = 0;
            //物质分解液
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.SubstanceDecompositionLiquid.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.SubstanceDecompositionLiquid.get());
                //冷却
                int nbtTime = compoundTag.getInt(substance_decomposition_liquid_TIME_NBT);
                if(nbtTime==0) {
                    fixedDamage +=curiosCount * attacked.getMaxHealth()*0.01f;
                }
            }
            //追逐星辰+行星钻地弹
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.ChasingStars.get())
                    || FCAUtil.isCurioEquipped(attacker, FCAItemRegister.PlanetaryDrillingBomb.get()) ) {
                var map = attacked.getActiveEffectsMap();
                attacked.addEffect(new MobEffectInstance(FCAEffectsRegister.Fragility.get(),200,0));
                if (!attacked.hasEffect(FCAEffectsRegister.Fragility.get())) {
                    map.put(FCAEffectsRegister.Fragility.get(), new MobEffectInstance(FCAEffectsRegister.Fragility.get(),200, 0));
                }
            }
            //闪光手榴弹
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.FlashGrenade.get())) {
                var map = attacked.getActiveEffectsMap();
                attacked.addEffect(new MobEffectInstance(FCAEffectsRegister.Weaken.get(),200,0));
                if (!attacked.hasEffect(FCAEffectsRegister.Weaken.get())) {
                    map.put(FCAEffectsRegister.Weaken.get(), new MobEffectInstance(FCAEffectsRegister.Weaken.get(),200, 0));
                }
            }
            //虚弱判断
            if(attacker.hasEffect(FCAEffectsRegister.Weaken.get())){
                number*=0.8F;
            }
            //永动机
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.PerpetualMotionMachine.get())) {
                int curiosCount = FCAUtil.getCuriosCount(attacker, FCAItemRegister.PerpetualMotionMachine.get());
                //层数
                int currentNumber = compoundTag.getInt(perpetual_motion_machine_Number_NBT);
                //时间
                int currentTime = compoundTag.getInt(perpetual_motion_machine_TIME_NBT);
                //初次攻击
                if(currentTime==0&&currentNumber==0){
                    //数量*层数*每层增幅
                    damageAmplifier+=curiosCount*0.5f;
                    compoundTag.putInt(perpetual_motion_machine_Number_NBT,1);
                }
                /// 开始循环
                if(currentTime>=0){
                    //非第四次攻击【初次：0，第一次：1：第二次：2，进入1-4循环】
                    if(currentNumber<4) {
                        compoundTag.putInt(perpetual_motion_machine_Number_NBT, currentNumber+1);
                    }else{
                        //数量*层数*每层增幅
                        damageAmplifier+=curiosCount*0.5f;
                        compoundTag.putInt(perpetual_motion_machine_Number_NBT,1);
                    }
                }
                //续时
                compoundTag.putInt(perpetual_motion_machine_TIME_NBT, 200);
            }
            /// 伤害增幅 加入结算
            if(damageAmplifier>0){
                number *= (1+damageAmplifier);
            }
            ///幸运一击判断 并加入结算
            double luckHitChance = attacker.getAttributeValue(FCAAttributes.LUCK_HIT_CHANCE.get());
            if(luckHitChance>0){
                finalLuckHitChance+= (float) (luckHitChance*100);
            }
            double luckHitAmplifier = attacker.getAttributeValue(FCAAttributes.LUCK_HIT_AMPLIFIER.get());
            //是否触发
            if(finalLuckHitChance>0&&luckHitAmplifier>0){
                Random random = new Random();
                //额外提升一点判断概率
                if (random.nextInt(100) <= 100*luckHitChance*1.3F) {
                    finalLuckHitDamage += (float) luckHitAmplifier;
                }
            }
            if(finalLuckHitDamage>0){
                number *= (1+finalLuckHitDamage);
            }
            /// 黄金裔本体
            //暴击率
            float baseCriticalStrikeChance = 0;
            //爆伤
            float baseCriticalStrikeDamage = 0;
            ///日冕
            if(FCAUtil.isCurioEquipped(attacker, FCAItemRegister.CoronalRadiance.get())){
                int damage = compoundTag.getInt(coronal_radiance_damage);
                baseCriticalStrikeDamage += (float) damage /100;
            }
            //盗火
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.DeepestDark.get()) ){
                //容器数量
                int deepsetNumber = compoundTag.getInt(deepest_dark_Number_NBT);
                if(deepsetNumber>0){
                    number*=1+(deepsetNumber*0.4F);
                    compoundTag.putInt(deepest_dark_Number_NBT, deepsetNumber-1);
                }
                //灾难之力
                int deepsetFinish = compoundTag.getInt(deepest_dark_Finish_nbt);
                if(deepsetFinish>=4){
                    number*=5;
                    compoundTag.putInt(deepest_dark_Number_NBT, 4);
                }
                var map = attacked.getActiveEffectsMap();
                if(attacked.hasEffect(FCAEffectsRegister.IronTombErosion.get())){
                    int level = 1+attacked.getEffect(FCAEffectsRegister.IronTombErosion.get()).getAmplifier();
                    attacker.heal(attacker.getMaxHealth()*level*0.05F);
                    map.put(FCAEffectsRegister.IronTombErosion.get(), new MobEffectInstance(FCAEffectsRegister.IronTombErosion.get(),
                            200, Math.min(3, level + 1)));
                }else{
                    map.put(FCAEffectsRegister.IronTombErosion.get(), new MobEffectInstance(FCAEffectsRegister.IronTombErosion.get(),
                            200, 0));
                }
            }

            //「磐岩之脊」
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.PermansorTerrae.get()) ) {
                //缩减时间
                int dragonTime = compoundTag.getInt(permansor_terrae_TIME_NBT);
                if (dragonTime > 40) {
                    compoundTag.putInt(permansor_terrae_TIME_NBT, dragonTime - 10);
                }
                if(attacker.hasEffect(MobEffects.ABSORPTION)){
                    int level = 1 + attacker.getEffect(MobEffects.ABSORPTION).getAmplifier();
                    number*=1+level*0.23F;
                }
            }
            //万径之门
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.Janus.get()) ) {
                fixedDamage+= (float) (attacker.getMaxHealth()*0.375);
                if(attacker.hasEffect(FCAEffectsRegister.Numinosity.get())) {
                    number *= 1.72F;
                }
            }
            //裂分之枝
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.Cerces.get()) && attacker instanceof Player player) {
                int experienceLevel = player.experienceLevel;
                if (experienceLevel > 0) {
                    number *= 1 + Math.min(30, experienceLevel) * 0.05F;
                }
                if (FCAUtil.isMeleeAttack(event.getSource())) {
                    var DamageType = FCADamageType.hasSource(attacker.level(), DamageTypes.MAGIC, attacker);
                    var mobList = FCAUtil.mobList(7, player);
                    Mob nearestMob = null;
                    double nearestDist = Double.MAX_VALUE;
                    for (Mob mobs : mobList) {
                        if (mobs != null) {
                            double dist = mobs.distanceTo(player);
                            if (dist < nearestDist) {
                                nearestDist = dist;
                                nearestMob = mobs;
                            }
                        }
                    }
                    int attackNumber = 4;
                    if (nearestMob != null) {
                        if (experienceLevel > 0) {
                            attackNumber = 4;
                        }else{
                            attackNumber = 8;
                        }
                        for (int i = 0; i < attackNumber; i++) {
                            // 攻击找到的实体
                            nearestMob.invulnerableTime = 0;
                            nearestMob.hurt(DamageType, (float) (attacker.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.875F));
                            nearestMob.invulnerableTime = 0;
                        }
                    }
                }
            }
            //满溢之杯
            if (FCAUtil.isCurioEquipped(attacker, FCAItemRegister.Phagousa.get())){
                var map = attacked.getActiveEffectsMap();
                int fire = attacked.getRemainingFireTicks();
                map.put(FCAEffectsRegister.Phagousa.get(), new MobEffectInstance(FCAEffectsRegister.Phagousa.get(),
                        200, 0));
                if (attacked.hasEffect(MobEffects.WITHER)) {
                    number*=1.625F;
                }
                if (attacked.hasEffect(MobEffects.POISON)) {
                    number*=1.625F;
                }
                if (fire>0) {
                    number*=1.625F;
                }
            }
            //满溢之杯（周围有
            if (event.getEntity().getAttributeValue(Attributes.ARMOR)>0) {
                var mobList = FCAUtil.mobList(16, attacker);
                for (Mob mobs : mobList) {
                    //实体本身满足条件
                    if (!(attacker instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null && ownableEntity==attacked) && attacker!=mobs) {
                        //周围有穿戴的
                        if(FCAUtil.isCurioEquipped(mobs, FCAItemRegister.Phagousa.get())){
                            number*=0.7F;
                        }
                    }
                }
                var playerList = FCAUtil.PlayerList(16, attacker);
                for (Player player : playerList) {
                    //实体本身满足条件
                    if (!(attacker instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null && ownableEntity==attacked) && attacker!=player) {
                        //周围有穿戴的
                        if(FCAUtil.isCurioEquipped(player, FCAItemRegister.Phagousa.get())){
                            number*=0.7F;
                        }
                    }
                }
            }
            ///天谴之矛
            if(FCAUtil.isCurioEquipped(attacker, FCAItemRegister.LanceOfFury.get())){
                if(attacker.getHealth()<=attacker.getMaxHealth()*0.2F){
                    number *= 3;
                }
                baseCriticalStrikeChance += 50;
                var DamageType = FCAUtil.source(attacked.level(), DamageTypes.MAGIC);
                attacker.invulnerableTime = 0;
                //大于1血，受伤
                if(attacker.getHealth()>1) {
                    float hurtDamage = attacker.getHealth()*0.15F;
                    //如果造成的伤害不致死
                    if(attacker.getHealth()-hurtDamage>0) {
                        attacker.hurt(DamageType, hurtDamage);
                    }else{
                        attacker.hurt(DamageType, hurtDamage-1);
                    }
                }else{
                    attacker.hurt(DamageType, 0);
                }
            }

            //永夜之帷
            if(FCAUtil.isCurioEquipped(attacker, FCAItemRegister.Evernight.get())){
                if(attacker.getMaxHealth()>0){
                    number*=1+ attacker.getMaxHealth()*0.01F;
                    baseCriticalStrikeChance *= attacker.getMaxHealth()*0.03F;
                }
                var DamageType = FCAUtil.source(attacked.level(), DamageTypes.MAGIC);
                attacker.invulnerableTime = 0;
                //大于1血，受伤
                if(attacker.getHealth()>1) {
                    float hurtDamage = attacker.getHealth()*0.05F;
                    //如果造成的伤害不致死
                    if(attacker.getHealth()-hurtDamage>0) {
                        attacker.hurt(DamageType, hurtDamage);
                    }else{
                        attacker.hurt(DamageType, hurtDamage-1);
                    }
                }else{
                    attacker.hurt(DamageType, 0);
                }
                //受伤增幅
                int lastHurtDamage = compoundTag.getInt(evernight_damage_NBT);
                if(lastHurtDamage>0){
                    number*=1+ (float) lastHurtDamage /100;
                    compoundTag.putInt(evernight_damage_NBT, 0);
                }
                //忆质数量
                int damageNumber = compoundTag.getInt(evernight_Number_NBT);
                if(damageNumber>=8){
                    number*=1 + 2.5F;
                    attacker.heal(attacker.getMaxHealth()*0.5F);
                    compoundTag.putInt(evernight_Number_NBT, 0);
                }
            }
            /// 灰黯之手
            if(FCAUtil.isCurioEquipped(attacker, FCAItemRegister.HandOfShadow.get())){
                fixedDamage+=attacked.getMaxHealth()*0.025F;
                if(attacker.getMaxHealth()>=100) {
                    baseCriticalStrikeDamage += 50;
                }
                if(attacked.getHealth()>=attacked.getMaxHealth()*0.5){
                    number*=1.5F;
                }
                var DamageType = FCAUtil.source(attacked.level(), DamageTypes.WITHER);
                attacker.invulnerableTime = 0;
                //大于1血，受伤
                if(attacker.getHealth()>1) {
                    float hurtDamage = attacker.getHealth()*0.05F;
                    //如果造成的伤害不致死
                    if(attacker.getHealth()-hurtDamage>0) {
                        attacker.hurt(DamageType, hurtDamage);
                    }else{
                        attacker.hurt(DamageType, hurtDamage-1);
                    }
                }else{
                    attacker.hurt(DamageType, 0);
                }
            }
            //刻律
            if(FCAUtil.isCurioEquipped(attacker, FCAItemRegister.Talanton.get())) {
                int militaryMerit = compoundTag.getInt(talanton_Number_NBT);
                baseCriticalStrikeDamage+=0.9F;
                if(attacker.getAttributeValue(Attributes.ATTACK_DAMAGE)>=8){
                    int atkNumber = (int) (attacker.getAttributeValue(Attributes.ATTACK_DAMAGE)-8);
                    if(atkNumber>0){
                        baseCriticalStrikeDamage+= (float) (0.1*atkNumber);
                    }
                }
                if(militaryMerit<6) {
                    compoundTag.putInt(talanton_Number_NBT, militaryMerit + 1);
                    baseCriticalStrikeChance *= militaryMerit*15;
                }else{
                    compoundTag.putInt(talanton_Number_NBT, militaryMerit - 6);
                    baseCriticalStrikeChance *= 100;
                    number*=1+2.88F;
                }
            }
            //黄泉
            if(FCAUtil.isCurioEquipped(attacker, FCAItemRegister.Acheron.get())) {
                if(!FCAUtil.isBossEntity(attacked.getType())){
                    number*=10;
                }
                int attackNumber = compoundTag.getInt(acheron_Number_NBT);
                int neutralAndHarmfulCount = 0;
                for (MobEffectInstance effect : attacked.getActiveEffects()) {
                    // 判断是否为NEUTRAL或Harmful【非正面
                    boolean isNEUTRAL = effect.getEffect().getCategory() == MobEffectCategory.NEUTRAL;
                    boolean isHarmful = effect.getEffect().getCategory() == MobEffectCategory.HARMFUL;
                    // 统计非NEUTRAL且非Harmful的效果
                    if (isNEUTRAL || isHarmful) {
                        neutralAndHarmfulCount++;
                    }
                }
                if(attackNumber+neutralAndHarmfulCount<9){
                    compoundTag.putInt(talanton_Number_NBT, attackNumber + 1);
                }else{
                    number*=6;
                    compoundTag.putInt(talanton_Number_NBT, 0);
                }
            }
            /// 暴击与爆伤
            //其中一个大于零，满足触发条件
            if(baseCriticalStrikeDamage>0||baseCriticalStrikeChance>0){
                baseCriticalStrikeChance+=20;
                baseCriticalStrikeDamage+=0.5F;
                Random random = new Random();
                //基础20暴击
                if (random.nextInt(100) <= baseCriticalStrikeChance*1.3F) {
                    number *= (float) (1+baseCriticalStrikeDamage);
                }
            }
            //死亡饰品本体||加冕
            if(FCAUtil.isCurioEquipped(attacker, FCAItemRegister.DeathCurios.get()) || FCAUtil.isCurioEquipped(attacked, FCAItemRegister.OriginSin.get()) ){
                fixedDamage+= (float) (attacked.getMaxHealth()*FCAconfig.deathCurios.get());
            }
            //阿雅增幅
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.Mnestia.get())){
                float speed = (float) attacker.getAttributeValue(Attributes.MOVEMENT_SPEED);
                if(speed>1) {
                    //速度-1后，0.1的个数
                    int speedNumber = (int) ((speed-1)/0.1F);
                    if(speedNumber>0) {
                        number *= 1 + 0.1f * speedNumber;
                    }
                }
            }
            float finishDamage = event.getAmount() * number + fixedDamage;
            //翻飞之币【硬币基础*10000
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.CoinOfWhimsy.get()) ){
                int coin = compoundTag.getInt(coin_of_whimsy_Number_NBT);
                if(finishDamage>0){
                    compoundTag.putInt(coin_of_whimsy_Number_NBT, (int) (coin+finishDamage*0.24F*10000) );
                }
                //如果处于猫咪怪盗状态
                if(attacker.hasEffect(FCAEffectsRegister.KittyPhantomThief.get())) {
                    fixedDamage+= (float) coin /10000;
                    compoundTag.putInt(coin_of_whimsy_Number_NBT, 0 );
                }
            }
            //阿雅
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.CoinOfWhimsy.get()) && FCAUtil.isMeleeAttack(event.getSource())){
                int romanceNumber = compoundTag.getInt(mnestia_Number_NBT);
                compoundTag.putInt(mnestia_TIME_NBT, 200);
                compoundTag.putInt(mnestia_Number_NBT, romanceNumber+1);
                var DamageType = FCADamageType.hasSource(attacker.level(), DamageTypes.LIGHTNING_BOLT, attacker);
                var mobList = FCAUtil.mobList(3, attacked);
                for (Mob mobs : mobList) {
                    if (!(mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null&& ownableEntity.getOwner() == attacker )&&mobs!=attacker) {
                        mobs.invulnerableTime = 0;
                        mobs.hurt(DamageType, finishDamage * 1.26F);
                        mobs.invulnerableTime = 0;
                    }
                }
                if(romanceNumber>=6){
                    attacked.hurt(DamageType, finishDamage * 1.26F);
                }
            }

            //流萤
            if (FCAUtil.isCurioEquipped(attacked, FCAItemRegister.Firefly.get()) && FCAUtil.isMeleeAttack(event.getSource()) ){
                attacker.heal(attacker.getMaxHealth()*0.05F);
                //超击破倍率
                float breakAmplifier = (float) attacker.getAttributeValue(FCAAttributes.Break_Amplifier.get());
                var DamageType = FCADamageType.hasSource(attacker.level(), FCADamageType.TRUEDAMAGE,attacker);
                var mobList = FCAUtil.mobList(3, attacked);
                for (Mob mobs : mobList) {
                    if (!(mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null&& ownableEntity.getOwner() == attacker )&&mobs!=attacker) {
                        mobs.invulnerableTime = 0;
                        mobs.hurt(DamageType, finishDamage * 0.5F * breakAmplifier);
                        mobs.invulnerableTime = 0;
                    }
                }
            }
            //确保清除
            attacker.removeEffect(FCAEffectsRegister.KittyPhantomThief.get());
            /// 最终结算
            if(fixedDamage>0){
                event.setAmount(event.getAmount() * number + fixedDamage);
            }else{
                event.setAmount(event.getAmount() * number);
            }
        }
    }
}
