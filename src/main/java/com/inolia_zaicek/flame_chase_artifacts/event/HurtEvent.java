package com.inolia_zaicek.flame_chase_artifacts.event;

import com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAEffectsRegister;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

import static net.minecraft.tags.DamageTypeTags.IS_FIRE;

public class HurtEvent {
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
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        LivingEntity attacked = event.getEntity();
        var strifeKey = "strife_curios_get_nbt";
        //挨打
        if (event.getEntity() != null) {
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
            ///伤害减免属性
            //实体伤害增幅属性叠加
            double damageDown = attacked.getAttributeValue(FCAAttributes.DAMAGE_AMPLIFIER.get());
            if(damageDown>0){
                number*= (float) ((float) 1-damageDown);
            }
            if (number != 1) {
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
                    CompoundTag compoundTag = event.getEntity().getPersistentData();
                    if(!compoundTag.getBoolean(strifeKey) && damage>=player.getMaxHealth()*0.9F && player.getHealth()>0){
                        player.addItem(new ItemStack( FCAItemRegister.StrifeCurios.get() ));
                        compoundTag.putBoolean(strifeKey, true);
                    }
                }
            }else{
                if(event.getEntity() instanceof ServerPlayer player && FCAUtil.isCurioEquipped(player, FCAItemRegister.HatredInundate.get()) ){
                    CompoundTag compoundTag = event.getEntity().getPersistentData();
                    if(!compoundTag.getBoolean(strifeKey) && event.getAmount()>=player.getMaxHealth()*0.9F && player.getHealth()>0 && event.getAmount()<player.getHealth() ){
                        player.addItem(new ItemStack( FCAItemRegister.StrifeCurios.get() ));
                        compoundTag.putBoolean(strifeKey, true);
                    }
                }
            }
        }
        //攻击
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            float number = 1;
            //昔涟13饰品增伤
            if(event.getSource().getEntity() instanceof LivingEntity livingEntity){
                var mobList = FCAUtil.mobList( (int)( (FCAconfig.egoCuriosRange.get()-1)/2) , livingEntity);
                var playerList = FCAUtil.PlayerList((int)( (FCAconfig.egoCuriosRange.get()-1)/2), livingEntity);
                for (Mob mobs : mobList) {
                    //有主人的随从——且有全套饰品
                    if ( (mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null)
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
            CompoundTag compoundTag = attacker.getPersistentData();
            //电磁弹射器冷却
            compoundTag.putInt(electromagnetic_catapult_TIME_NBT, 200);
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
            if(attacked.hasEffect(FCAEffectsRegister.Weaken.get())){
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
                if (random.nextInt(101) <= 100*luckHitChance) {
                    finalLuckHitDamage += (float) luckHitAmplifier;
                }
            }
            if(finalLuckHitDamage>0){
                number *= (1+finalLuckHitDamage);
            }
            //死亡饰品本体||加冕
            if(FCAUtil.isCurioEquipped(attacker, FCAItemRegister.DeathCurios.get()) || FCAUtil.isCurioEquipped(attacked, FCAItemRegister.OriginSin.get()) ){
                fixedDamage+= (float) (attacked.getMaxHealth()*FCAconfig.deathCurios.get());
            }
            /// 最终结算
            if(fixedDamage>0){
                event.setAmount(event.getAmount() * number + fixedDamage);
            }else{
                event.setAmount(event.getAmount() * number);
            }
        }else if (event.getSource().getDirectEntity() instanceof LivingEntity attacker) {
            float number = 1;
            //昔涟13饰品增伤
            if(event.getSource().getEntity() instanceof LivingEntity livingEntity){
                var mobList = FCAUtil.mobList( (int)( (FCAconfig.egoCuriosRange.get()-1)/2) , livingEntity);
                var playerList = FCAUtil.PlayerList((int)( (FCAconfig.egoCuriosRange.get()-1)/2), livingEntity);
                for (Mob mobs : mobList) {
                    //有主人的随从——且有全套饰品
                    if ( (mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null)
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
            CompoundTag compoundTag = attacker.getPersistentData();
            //电磁弹射器冷却
            compoundTag.putInt(electromagnetic_catapult_TIME_NBT, 200);
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
            if(attacked.hasEffect(FCAEffectsRegister.Weaken.get())){
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
                if (random.nextInt(101) <= 100*luckHitChance) {
                    finalLuckHitDamage += (float) luckHitAmplifier;
                }
            }
            if(finalLuckHitDamage>0){
                number *= (1+finalLuckHitDamage);
            }
            //死亡饰品本体||加冕
            if(FCAUtil.isCurioEquipped(attacker, FCAItemRegister.DeathCurios.get()) || FCAUtil.isCurioEquipped(attacked, FCAItemRegister.OriginSin.get()) ){
                fixedDamage+= (float) (attacked.getMaxHealth()*FCAconfig.deathCurios.get());
            }
            /// 最终结算
            if(fixedDamage>0){
                event.setAmount(event.getAmount() * number + fixedDamage);
            }else{
                event.setAmount(event.getAmount() * number);
            }
        }
    }
}
