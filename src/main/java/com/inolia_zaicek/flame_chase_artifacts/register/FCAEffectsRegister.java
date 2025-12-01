package com.inolia_zaicek.flame_chase_artifacts.register;

import com.inolia_zaicek.flame_chase_artifacts.register.buff.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts.MODID;

public class FCAEffectsRegister {
    public static final DeferredRegister<MobEffect> INOEFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,MODID);
    //决斗
    public static final RegistryObject<MobEffect> IronTombErosion = INOEFFECT.register("iron_tomb_erosion", IronTombErosionBuff::new);
    public static final RegistryObject<MobEffect> Fragility = INOEFFECT.register("fragility", FragilityBuff::new);
    public static final RegistryObject<MobEffect> Weaken = INOEFFECT.register("weaken", WeakenBuff::new);
    public static final RegistryObject<MobEffect> WeaponMaster = INOEFFECT.register("weapon_master", WeaponMasterBuff::new);
    public static final RegistryObject<MobEffect> ConfidenceInjector = INOEFFECT.register("confidence_injector", ConfidenceInjectorBuff::new);
    public static final RegistryObject<MobEffect> AdaptiveExoskeleton = INOEFFECT.register("adaptive_exoskeleton", AdaptiveExoskeletonBuff::new);
    public static final RegistryObject<MobEffect> ElectromagneticCatapult = INOEFFECT.register("electromagnetic_catapult", ElectromagneticCatapultBuff::new);
    public static final RegistryObject<MobEffect> FlagOfVictory = INOEFFECT.register("flag_of_victory", FlagOfVictoryBuff::new);
    public static final RegistryObject<MobEffect> RingOfLife = INOEFFECT.register("ring_of_life", RingOfLifeBuff::new);
    public static final RegistryObject<MobEffect> PainBlockChip = INOEFFECT.register("pain_block_chip", PainBlockChipBuff::new);
    public static final RegistryObject<MobEffect> VeryHardArmor = INOEFFECT.register("very_hard_armor", VeryHardArmorBuff::new);
    public static final RegistryObject<MobEffect> AntiSatelliteSniperRifle = INOEFFECT.register("anti_satellite_sniper_rifle", AntiSatelliteSniperRifleBuff::new);
    public static final RegistryObject<MobEffect> AntiGravBoots = INOEFFECT.register("anti_grav_boots", AntiGravBootsBuff::new);
}
