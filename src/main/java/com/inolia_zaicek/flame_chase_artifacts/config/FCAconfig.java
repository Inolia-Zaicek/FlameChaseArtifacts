package com.inolia_zaicek.flame_chase_artifacts.config;

import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;

public class FCAconfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    //——————————————————————————————————————————————配置类型————配置名称
    //public static final ForgeConfigSpec.ConfigValue<Boolean> bbbbb;
    //public static final ForgeConfigSpec.ConfigValue<Float> ccccc;
    //开启合成素材
    public static final ForgeConfigSpec.ConfigValue<Boolean> craftItem;
    //诅咒
    public static final ForgeConfigSpec.DoubleValue skyCurse;
    public static final ForgeConfigSpec.DoubleValue earthCurse;
    public static final ForgeConfigSpec.DoubleValue oceanCurse;
    public static final ForgeConfigSpec.DoubleValue romanceCurse;
    public static final ForgeConfigSpec.DoubleValue worldbearingCurse;
    public static final ForgeConfigSpec.DoubleValue reasonCurse;
    public static final ForgeConfigSpec.DoubleValue trickeryCurse;
    public static final ForgeConfigSpec.DoubleValue strifeCurse;
    public static final ForgeConfigSpec.ConfigValue<Boolean> deathCurse;
    public static final ForgeConfigSpec.DoubleValue timeCurse;
    public static final ForgeConfigSpec.DoubleValue lawCurse;
    public static final ForgeConfigSpec.DoubleValue passageCurse;
    //祝福
    public static final ForgeConfigSpec.DoubleValue skyBlessing;
    public static final ForgeConfigSpec.DoubleValue earthBlessing;
    public static final ForgeConfigSpec.DoubleValue oceanBlessing;
    public static final ForgeConfigSpec.DoubleValue romanceBlessing;
    public static final ForgeConfigSpec.DoubleValue worldbearingBlessing;
    public static final ForgeConfigSpec.DoubleValue reasonBlessing;
    public static final ForgeConfigSpec.DoubleValue trickeryBlessing;
    public static final ForgeConfigSpec.DoubleValue strifeBlessing;
    public static final ForgeConfigSpec.DoubleValue deathBlessing;
    public static final ForgeConfigSpec.DoubleValue timeBlessing;
    public static final ForgeConfigSpec.DoubleValue lawBlessing;
    public static final ForgeConfigSpec.DoubleValue passageBlessing;
    //饰品
    public static final ForgeConfigSpec.DoubleValue skyCuriosNumber;
    public static final ForgeConfigSpec.DoubleValue skyCuriosRange;
    public static final ForgeConfigSpec.DoubleValue earthCurios;
    public static final ForgeConfigSpec.DoubleValue oceanCurios;
    public static final ForgeConfigSpec.DoubleValue romanceCurios;
    public static final ForgeConfigSpec.DoubleValue romanceCuriosNumber;
    public static final ForgeConfigSpec.DoubleValue worldbearingCurios;
    public static final ForgeConfigSpec.DoubleValue reasonCuriosFortune;
    public static final ForgeConfigSpec.DoubleValue reasonCuriosLooting;
    public static final ForgeConfigSpec.DoubleValue trickeryCurios;
    public static final ForgeConfigSpec.DoubleValue strifeCurios;
    public static final ForgeConfigSpec.DoubleValue deathCurios;
    public static final ForgeConfigSpec.DoubleValue timeCurios;
    public static final ForgeConfigSpec.DoubleValue lawCurios;
    public static final ForgeConfigSpec.DoubleValue passageCurios;
    //真我
    public static final ForgeConfigSpec.DoubleValue skyEgoCurios;
    public static final ForgeConfigSpec.DoubleValue skyEgoCuriosRegenerationLevel;
    public static final ForgeConfigSpec.DoubleValue skyEgoCuriosSpeedLevel;
    public static final ForgeConfigSpec.DoubleValue earthEgoCurios;
    public static final ForgeConfigSpec.DoubleValue earthEgoCuriosPowerLevel;
    public static final ForgeConfigSpec.DoubleValue earthEgoCuriosReLevel;
    public static final ForgeConfigSpec.DoubleValue oceanEgoCurios;
    public static final ForgeConfigSpec.DoubleValue romanceEgoCuriosNumber;
    public static final ForgeConfigSpec.DoubleValue romanceEgoCuriosTime;
    public static final ForgeConfigSpec.DoubleValue worldbearingEgoCurios;
    public static final ForgeConfigSpec.DoubleValue reasonEgoCurios;
    public static final ForgeConfigSpec.ConfigValue<Boolean> reasonEgoCuriosTreasure;
    public static final ForgeConfigSpec.DoubleValue trickeryEgoCurios;
    public static final ForgeConfigSpec.DoubleValue strifeEgoCurios;
    public static final ForgeConfigSpec.DoubleValue deathEgoCurios;
    public static final ForgeConfigSpec.DoubleValue timeEgoCurios;
    public static final ForgeConfigSpec.DoubleValue lawEgoCurios;
    public static final ForgeConfigSpec.DoubleValue passageEgoCurios;
    public static final ForgeConfigSpec.DoubleValue egoCuriosHasSourseDamageDown;
    public static final ForgeConfigSpec.DoubleValue egoCuriosNoSourseDamage;
    public static final ForgeConfigSpec.DoubleValue egoCuriosRange;
    public static final ForgeConfigSpec.DoubleValue egoCuriosAttack;
    //加冕
    public static final ForgeConfigSpec.DoubleValue skyOverCurse;
    public static final ForgeConfigSpec.DoubleValue earthOverCurse;
    public static final ForgeConfigSpec.DoubleValue oceanOverCurse;
    public static final ForgeConfigSpec.DoubleValue romanceOverCurse;
    public static final ForgeConfigSpec.DoubleValue worldbearingOverCurse;
    public static final ForgeConfigSpec.DoubleValue reasonOverCurse;
    public static final ForgeConfigSpec.DoubleValue trickeryOverCurse;
    public static final ForgeConfigSpec.DoubleValue strifeOverCurse;
    public static final ForgeConfigSpec.DoubleValue deathOverCurse;
    public static final ForgeConfigSpec.DoubleValue timeOverCurse;
    public static final ForgeConfigSpec.DoubleValue lawOverCurse;
    public static final ForgeConfigSpec.DoubleValue passageOverCurse;
    public static final ForgeConfigSpec.DoubleValue ironTombLevel;
    public static final ForgeConfigSpec.DoubleValue ironTombDamageUp;
    public static final ForgeConfigSpec.DoubleValue ironTombDamageDown;
    public static final ForgeConfigSpec.DoubleValue ironTombHatred;
    public static final ForgeConfigSpec.DoubleValue ironTombHatredDamage;
    
    static {
        //这个builder的push里面写的是“分组”
        //push是一组的开始，pop是结束
        //常规分组:general
        //诅咒
        BUILDER.push("craft_item");
        craftItem = BUILDER.comment("合成素材").define("craftItem",true);
        BUILDER.pop();
        //诅咒
        BUILDER.push("curse");
        /*
                //bool
        bbbbb = BUILDER
                .comment("是否YYYY")
                .define("YY",false);
        //Float
        ccccc = BUILDER
                .comment("ZZZZ的数值")
                .define("ZZ",1.0F);
         */

        skyCurse = BUILDER
                //写在配置文件上方的默认备注
                .comment("天空减疗数额")
                //XX = ture
                .defineInRange("skyCurse",0.75F,0F,2147483647F);
        earthCurse = BUILDER.comment("大地增防数额").defineInRange("earthCurse",1.25F,0F,2147483647F);
        oceanCurse = BUILDER.comment("海洋延时数额").defineInRange("oceanCurse",1.5F,0F,2147483647F);
        romanceCurse = BUILDER.comment("浪漫无敌数额").defineInRange("romanceCurse",1.5F,0F,2147483647F);
        worldbearingCurse = BUILDER.comment("负世灼烧数额").defineInRange("worldbearingCurse",2.0F,0F,2147483647F);
        reasonCurse = BUILDER.comment("理性经验数额").defineInRange("reasonCurse",0.75F,0F,2147483647F);
        trickeryCurse = BUILDER.comment("诡计时长数额").defineInRange("trickeryCurse",0.5F,0F,2147483647F);
        strifeCurse = BUILDER.comment("纷争减防数额").defineInRange("strifeCurse",0.5F,0F,2147483647F);
        deathCurse = BUILDER.comment("死亡禁用开关").define("deathCurse",true);
        timeCurse = BUILDER.comment("岁月无敌数额").defineInRange("timeCurse",0.75F,0F,2147483647F);
        lawCurse = BUILDER.comment("律法减伤数额").defineInRange("lawCurse",0.5F,0F,2147483647F);
        passageCurse = BUILDER.comment("门径血量数额").defineInRange("passageCurse",0.2F,0F,2147483647F);
        BUILDER.pop();
        //祝福
        BUILDER.push("blessing");
        skyBlessing = BUILDER.comment("天空加疗数额").defineInRange("skyBlessing",1.5F,0F,2147483647F);
        earthBlessing = BUILDER.comment("大地穿防数额").defineInRange("earthBlessing",0.75F,0F,2147483647F);
        oceanBlessing = BUILDER.comment("海洋延时数额").defineInRange("oceanBlessing",0.5F,0F,2147483647F);
        romanceBlessing = BUILDER.comment("浪漫无敌数额").defineInRange("romanceBlessing",0.75F,0F,2147483647F);
        worldbearingBlessing = BUILDER.comment("负世灼烧数额").defineInRange("worldbearingBlessing",0.5F,0F,2147483647F);
        reasonBlessing = BUILDER.comment("理性经验数额").defineInRange("reasonBlessing",2.0F,0F,2147483647F);
        trickeryBlessing = BUILDER.comment("诡计时长数额").defineInRange("trickeryBlessing",1.5F,0F,2147483647F);
        strifeBlessing = BUILDER.comment("纷争加防数额").defineInRange("strifeBlessing",1.25F,0F,2147483647F);
        deathBlessing = BUILDER.comment("死亡冷却数额").defineInRange("deathBlessing",300.0F,0F,2147483647F);
        timeBlessing = BUILDER.comment("岁月无敌数额").defineInRange("timeBlessing",1.25F,0F,2147483647F);
        lawBlessing = BUILDER.comment("律法减伤数额").defineInRange("lawBlessing",1.25F,0F,2147483647F);
        passageBlessing = BUILDER.comment("门径限伤数额").defineInRange("passageBlessing",0.6F,0F,2147483647F);
        BUILDER.pop();
        BUILDER.push("curios");
        skyCuriosNumber = BUILDER.comment("天空饰品").defineInRange("skyCuriosNumber",0.02F,0F,2147483647F);
        skyCuriosRange = BUILDER.comment("天空饰品").defineInRange("skyCuriosRange",15F,3F,2147483647F);
        earthCurios = BUILDER.comment("大地饰品").defineInRange("earthCurios",0.2F,0F,2147483647F);
        oceanCurios = BUILDER.comment("海洋饰品").defineInRange("oceanCurios",10F,1F,2147483647F);
        romanceCurios = BUILDER.comment("浪漫饰品").defineInRange("romanceCurios",25F,3F,2147483647F);
        romanceCuriosNumber = BUILDER.comment("负世饰品属性").defineInRange("romanceCuriosNumber",0.1F,0F,2147483647F);
        worldbearingCurios = BUILDER.comment("负世饰品").defineInRange("worldbearingCurios",0.1F,0F,2147483647F);
        reasonCuriosFortune = BUILDER.comment("理性饰品").defineInRange("reasonCuriosFortune",1F,0F,2147483647F);
        reasonCuriosLooting = BUILDER.comment("理性饰品").defineInRange("reasonCuriosLooting",1F,0F,2147483647F);
        trickeryCurios = BUILDER.comment("诡计饰品").defineInRange("trickeryCurios",1F,1F,2147483647F);
        strifeCurios = BUILDER.comment("纷争饰品").defineInRange("strifeCurios",2.0F,0F,2147483647F);
        deathCurios = BUILDER.comment("死亡饰品").defineInRange("deathCurios",0.01F,0F,2147483647F);
        timeCurios = BUILDER.comment("岁月饰品").defineInRange("timeCurios",0.25F,0F,2147483647F);
        lawCurios = BUILDER.comment("律法饰品").defineInRange("lawCurios",1.0F,0F,2147483647F);
        passageCurios = BUILDER.comment("门径饰品").defineInRange("passageCurios",25F,0F,2147483647F);
        BUILDER.pop();
        BUILDER.push("ego");
        egoCuriosHasSourseDamageDown = BUILDER.comment("真我有源减伤").defineInRange("egoCuriosHasSourseDamageDown", 0.3F,0F,2147483647F);
        egoCuriosNoSourseDamage = BUILDER.comment("真我无源减伤").defineInRange("egoCuriosNoSourseDamage", 0.5F,0F,2147483647F);
        egoCuriosRange = BUILDER.comment("真我范围").defineInRange("egoCuriosRange", 15F,3F,2147483647F);
        egoCuriosAttack = BUILDER.comment("真我增伤").defineInRange("egoCuriosAttack", 0.5F,0F,2147483647F);
        BUILDER.pop();
        BUILDER.push("ego_curios");
        skyEgoCurios = BUILDER.comment("天空饰品").defineInRange("skyEgoCurios", 15F,3F,2147483647F);
        skyEgoCuriosRegenerationLevel = BUILDER.comment("天空饰品等级").defineInRange("skyEgoCuriosRegenerationLevel", 1F,1F,2147483647F);
        skyEgoCuriosSpeedLevel = BUILDER.comment("天空饰品等级").defineInRange("skyEgoCuriosSpeedLevel", 1F,1F,2147483647F);
        earthEgoCurios = BUILDER.comment("大地饰品").defineInRange("earthEgoCurios", 15F,3F,2147483647F);
        earthEgoCuriosPowerLevel = BUILDER.comment("大地饰品等级").defineInRange("earthEgoCuriosPowerLevel", 1F,1F,2147483647F);
        earthEgoCuriosReLevel = BUILDER.comment("大地饰品等级").defineInRange("earthEgoCuriosReLevel", 1F,1F,2147483647F);
        oceanEgoCurios = BUILDER.comment("海洋饰品").defineInRange("oceanEgoCurios", 0.1F,0F,2147483647F);
        romanceEgoCuriosNumber = BUILDER.comment("浪漫饰品最高状态等级").defineInRange("romanceEgoCuriosNumber", 5F,0F,2147483647F);
        romanceEgoCuriosTime = BUILDER.comment("浪漫饰品最高状态时间").defineInRange("romanceEgoCuriosTime", 10F,0F,2147483647F);
        worldbearingEgoCurios = BUILDER.comment("负世饰品").defineInRange("worldbearingEgoCurios", 1.0F,0F,2147483647F);
        reasonEgoCurios = BUILDER.comment("理性饰品").defineInRange("reasonEgoCurios", 10F,0F,2147483647F);
        reasonEgoCuriosTreasure = BUILDER.comment("理性饰品是否启用宝藏").define("reasonEgoCuriosTreasure",false);
        trickeryEgoCurios = BUILDER.comment("诡计饰品").defineInRange("trickeryEgoCurios", 0.5F,0F,2147483647F);
        strifeEgoCurios = BUILDER.comment("纷争饰品").defineInRange("strifeEgoCurios", 0.4F,0F,2147483647F);
        deathEgoCurios = BUILDER.comment("死亡饰品").defineInRange("deathEgoCurios", 240F,0F,2147483647F);
        timeEgoCurios = BUILDER.comment("岁月饰品").defineInRange("timeEgoCurios", 0.25F,0F,2147483647F);
        lawEgoCurios = BUILDER.comment("律法饰品").defineInRange("lawEgoCurios", 0.5F,0F,2147483647F);
        passageEgoCurios = BUILDER.comment("门径饰品").defineInRange("passageEgoCurios", 0.2F,0F,2147483647F);
        BUILDER.pop();
        //加冕
        BUILDER.push("over_curse");
        skyOverCurse = BUILDER.comment("天空减疗数额").defineInRange("skyOverCurse",0.25F,0F,2147483647F);
        earthOverCurse = BUILDER.comment("大地穿防数额").defineInRange("earthOverCurse",0.25F,0F,2147483647F);
        oceanOverCurse = BUILDER.comment("海洋加时数额").defineInRange("oceanOverCurse",2.0F,0F,2147483647F);
        romanceOverCurse = BUILDER.comment("浪漫削免数额").defineInRange("romanceOverCurse",0.5F,0F,2147483647F);
        worldbearingOverCurse = BUILDER.comment("负世灼烧数额").defineInRange("worldbearingOverCurse",4.0F,0F,2147483647F);
        reasonOverCurse = BUILDER.comment("理性经验数额").defineInRange("reasonOverCurse",3.0F,0F,2147483647F);
        trickeryOverCurse = BUILDER.comment("诡计加时数额").defineInRange("trickeryOverCurse",2.0F,0F,2147483647F);
        strifeOverCurse = BUILDER.comment("纷争减防数额").defineInRange("strifeOverCurse",0.25F,0F,2147483647F);
        deathOverCurse = BUILDER.comment("死亡冷却数额").defineInRange("deathOverCurse",300.0F,0F,2147483647F);
        timeOverCurse = BUILDER.comment("岁月减免数额").defineInRange("timeOverCurse",0.5F,0F,2147483647F);
        lawOverCurse = BUILDER.comment("律法增伤数额").defineInRange("lawOverCurse",1.75F,0F,2147483647F);
        passageOverCurse = BUILDER.comment("门径提伤数额").defineInRange("passageOverCurse",0.4F,0F,2147483647F);
        ironTombLevel = BUILDER.comment("侵蚀等级").defineInRange("ironTombLevel",10F,0F,2147483647F);
        ironTombDamageDown = BUILDER.comment("侵蚀自我减伤").defineInRange("ironTombDamageDown",0.05F,0F,2147483647F);
        ironTombDamageUp = BUILDER.comment("侵蚀受伤提升").defineInRange("ironTombDamageUp",0.2F,0F,2147483647F);
        ironTombHatred = BUILDER.comment("攻击提升恨意百分比").defineInRange("ironTombHatred",0.05F,0F,2147483647F);
        ironTombHatredDamage = BUILDER.comment("恨意增伤").defineInRange("ironTombHatredDamage",0.02F,0F,2147483647F);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
