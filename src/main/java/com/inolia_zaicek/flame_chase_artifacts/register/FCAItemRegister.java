package com.inolia_zaicek.flame_chase_artifacts.register;

import com.inolia_zaicek.flame_chase_artifacts.item.ChrysosHeirs.Hero.*;
import com.inolia_zaicek.flame_chase_artifacts.item.CurrencyWars.*;
import com.inolia_zaicek.flame_chase_artifacts.item.ChrysosHeirs.*;
import com.inolia_zaicek.flame_chase_artifacts.item.*;
import com.inolia_zaicek.flame_chase_artifacts.item.EveryBody.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts.MODID;


public class FCAItemRegister {
    public static final DeferredRegister<Item> ZeroingITEM=DeferredRegister.create(Registries.ITEM,MODID);
    public static List<RegistryObject<Item>> CommonItem=new ArrayList<>(List.of());

    public static RegistryObject<Item> registerCommonMaterials(DeferredRegister<Item> register,String name, Supplier<? extends Item> sup){
        RegistryObject<Item> object = register.register(name,sup);
        CommonItem.add(object);
        return object;
    }
    // tag
    public static final TagKey<Item> malum_scythe_effect = TagKey.create(Registries.ITEM,new ResourceLocation("more_mod_tetra","malum_scythe_effect"));
    //饰品
    public static final Supplier<Item> HatredInundate=registerCommonMaterials(ZeroingITEM,"hatred_inundate", HatredInundateCuriosItem::new);
    public static final Supplier<Item> PristineLove=registerCommonMaterials(ZeroingITEM,"pristine_love", PristineLoveCuriosItem::new);
    public static final Supplier<Item> OriginSin=registerCommonMaterials(ZeroingITEM,"origin_sin", OriginSinCuriosItem::new);
    public static final Supplier<Item> SkyCurios=registerCommonMaterials(ZeroingITEM,"sky_curios", skyCurios::new);
    public static final Supplier<Item> EarthCurios=registerCommonMaterials(ZeroingITEM,"earth_curios", earthCurios::new);
    public static final Supplier<Item> OceanCurios=registerCommonMaterials(ZeroingITEM,"ocean_curios", oceanCurios::new);
    public static final Supplier<Item> RomanceCurios=registerCommonMaterials(ZeroingITEM,"romance_curios", romanceCurios::new);
    public static final Supplier<Item> WorldBearingCurios=registerCommonMaterials(ZeroingITEM,"worldbearing_curios", worldbearingCurios::new);
    public static final Supplier<Item> ReasonCurios=registerCommonMaterials(ZeroingITEM,"reason_curios", reasonCurios::new);
    public static final Supplier<Item> TrickeryCurios=registerCommonMaterials(ZeroingITEM,"trickery_curios", trickeryCurios::new);
    public static final Supplier<Item> StrifeCurios=registerCommonMaterials(ZeroingITEM,"strife_curios", strifeCurios::new);
    public static final Supplier<Item> DeathCurios=registerCommonMaterials(ZeroingITEM,"death_curios", deathCurios::new);
    public static final Supplier<Item> TimeCurios=registerCommonMaterials(ZeroingITEM,"time_curios", timeCurios::new);
    public static final Supplier<Item> LawCurios=registerCommonMaterials(ZeroingITEM,"law_curios", lawCurios::new);
    public static final Supplier<Item> PassageCurios=registerCommonMaterials(ZeroingITEM,"passage_curios", passageCurios::new);

    public static final Supplier<Item> EyeOfTwilight=registerCommonMaterials(ZeroingITEM,"eye_of_twilight", EyeOfTwilightItem::new);
    public static final Supplier<Item> PermansorTerrae=registerCommonMaterials(ZeroingITEM,"permansor_terrae", PermansorTerraeItem::new);
    public static final Supplier<Item> Phagousa=registerCommonMaterials(ZeroingITEM,"phagousa", PhagousaItem::new);

    public static final Supplier<Item> Mnestia=registerCommonMaterials(ZeroingITEM,"mnestia", MnestiaItem::new);
    public static final Supplier<Item> CoronalRadiance=registerCommonMaterials(ZeroingITEM,"coronal_radiance", CoronalRadianceItem::new);
    public static final Supplier<Item> Cerces=registerCommonMaterials(ZeroingITEM,"cerces", CercesItem::new);

    public static final Supplier<Item> CoinOfWhimsy=registerCommonMaterials(ZeroingITEM,"coin_of_whimsy", CoinOfWhimsyItem::new);
    public static final Supplier<Item> LanceOfFury=registerCommonMaterials(ZeroingITEM,"lance_of_fury", LanceOfFuryItem::new);
    public static final Supplier<Item> HandOfShadow=registerCommonMaterials(ZeroingITEM,"hand_of_shadow", HandOfShadowItem::new);

    public static final Supplier<Item> Evernight=registerCommonMaterials(ZeroingITEM,"evernight", EvernightItem::new);
    public static final Supplier<Item> Talanton=registerCommonMaterials(ZeroingITEM,"talanton", TalantonItem::new);
    public static final Supplier<Item> Janus=registerCommonMaterials(ZeroingITEM,"janus", JanusItem::new);

    public static final Supplier<Item> DeepestDark=registerCommonMaterials(ZeroingITEM,"deepest_dark", DeepestDarkItem::new);
    /// 角色饰品
    public static final Supplier<Item> Acheron=registerCommonMaterials(ZeroingITEM,"acheron", AcheronItem::new);
    public static final Supplier<Item> Firefly=registerCommonMaterials(ZeroingITEM,"firefly", FireflyItem::new);
    /// 货币战争
    //散件
    public static final Supplier<Item> RollerSkates = registerCommonMaterials(ZeroingITEM, "roller_skates", RollerSkatesItem::new);
    public static final Supplier<Item> FoldingKnife = registerCommonMaterials(ZeroingITEM, "folding_knife", FoldingKnifeItem::new);
    public static final Supplier<Item> PeacePistol = registerCommonMaterials(ZeroingITEM, "peace_pistol", PeacePistolItem::new);
    public static final Supplier<Item> LuckyStar = registerCommonMaterials(ZeroingITEM, "lucky_star", LuckyStarItem::new);
    public static final Supplier<Item> FlowerOfLife = registerCommonMaterials(ZeroingITEM, "flower_of_life", FlowerOfLifeItem::new);
    public static final Supplier<Item> MassProductionArmor = registerCommonMaterials(ZeroingITEM, "mass_production_armor", MassProductionArmorItem::new);
    public static final Supplier<Item> EtherealDrill = registerCommonMaterials(ZeroingITEM, "ethereal_drill", EtherealDrillItem::new);
    public static final Supplier<Item> PhotonBattery = registerCommonMaterials(ZeroingITEM, "photon_battery", PhotonBatteryItem::new);
    //完全体

    public static final Supplier<Item> AntiGravBoots = registerCommonMaterials(ZeroingITEM, "anti_grav_boots", AntiGravBoots::new);
    public static final Supplier<Item> FirestormSurge = registerCommonMaterials(ZeroingITEM, "firestorm_surge", FirestormSurge::new);
    public static final Supplier<Item> ElectromagneticCatapult = registerCommonMaterials(ZeroingITEM, "electromagnetic_catapult", ElectromagneticCatapult::new);
    public static final Supplier<Item> ChasingStars = registerCommonMaterials(ZeroingITEM, "chasing_stars", ChasingStars::new);
    public static final Supplier<Item> BloomingStepByStep = registerCommonMaterials(ZeroingITEM, "blooming_step_by_step", BloomingStepByStep::new);
    public static final Supplier<Item> LightSpeedPropeller = registerCommonMaterials(ZeroingITEM, "light_speed_propeller", LightSpeedPropeller::new);
    public static final Supplier<Item> ElectricSparkBoot = registerCommonMaterials(ZeroingITEM, "electric_spark_boot", ElectricSparkBoot::new);
    public static final Supplier<Item> MeteorFragmentSlashingShip = registerCommonMaterials(ZeroingITEM, "meteor_fragment_slashing_ship", MeteorFragmentSlashingShip::new);
    public static final Supplier<Item> WeaponMaster = registerCommonMaterials(ZeroingITEM, "weapon_master", WeaponMaster::new);
    public static final Supplier<Item> HighFrequencyChainsaw = registerCommonMaterials(ZeroingITEM, "high_frequency_chainsaw", HighFrequencyChainsaw::new);
    public static final Supplier<Item> KillRedEye = registerCommonMaterials(ZeroingITEM, "kill_red_eye", KillRedEye::new);
    public static final Supplier<Item> MeteoricWings = registerCommonMaterials(ZeroingITEM, "meteoric_wings", MeteoricWings::new);
    public static final Supplier<Item> ConfidenceInjector = registerCommonMaterials(ZeroingITEM, "confidence_injector", ConfidenceInjector::new);
    public static final Supplier<Item> KineticStimulusSword = registerCommonMaterials(ZeroingITEM, "kinetic_stimulus_sword", KineticStimulusSword::new);
    public static final Supplier<Item> BattlefieldEvolutionManual = registerCommonMaterials(ZeroingITEM, "battlefield_evolution_manual", BattlefieldEvolutionManual::new);
    public static final Supplier<Item> OrbitalArk = registerCommonMaterials(ZeroingITEM, "orbital_ark", OrbitalArk::new);
    public static final Supplier<Item> AntiSatelliteSniperRifle = registerCommonMaterials(ZeroingITEM, "anti_satellite_sniper_rifle", AntiSatelliteSniperRifle::new);
    public static final Supplier<Item> CoverGeneratingGun = registerCommonMaterials(ZeroingITEM, "cover_generating_gun", CoverGeneratingGun::new);
    public static final Supplier<Item> AdaptiveExoskeleton = registerCommonMaterials(ZeroingITEM, "adaptive_exoskeleton", AdaptiveExoskeleton::new);
    public static final Supplier<Item> BeheadingOperation = registerCommonMaterials(ZeroingITEM, "beheading_operation", BeheadingOperation::new);
    public static final Supplier<Item> EnergySail = registerCommonMaterials(ZeroingITEM, "energy_sail", EnergySail::new);
    public static final Supplier<Item> RandomDice = registerCommonMaterials(ZeroingITEM, "random_dice", RandomDice::new);
    public static final Supplier<Item> HotBloodedBoilingFist = registerCommonMaterials(ZeroingITEM, "hot_blooded_fist", HotBloodedBoilingFist::new);
    public static final Supplier<Item> ToothForToothArmor = registerCommonMaterials(ZeroingITEM, "tooth_for_tooth_armor", ToothForToothArmor::new);
    public static final Supplier<Item> FlagOfVictory = registerCommonMaterials(ZeroingITEM, "flag_of_victory", FlagOfVictory::new);
    public static final Supplier<Item> MadJokeEngine = registerCommonMaterials(ZeroingITEM, "mad_joke_engine", MadJokeEngine::new);
    public static final Supplier<Item> RingOfLife = registerCommonMaterials(ZeroingITEM, "ring_of_life", RingOfLife::new);
    public static final Supplier<Item> PainBlockChip = registerCommonMaterials(ZeroingITEM, "pain_block_chip", PainBlockChip::new);
    public static final Supplier<Item> SubstanceDecompositionLiquid = registerCommonMaterials(ZeroingITEM, "substance_decomposition_liquid", SubstanceDecompositionLiquid::new);
    public static final Supplier<Item> AbsoluteHeat = registerCommonMaterials(ZeroingITEM, "absolute_heat", AbsoluteHeat::new);
    public static final Supplier<Item> VeryHardArmor = registerCommonMaterials(ZeroingITEM, "very_hard_armor", VeryHardArmor::new);
    public static final Supplier<Item> FlashGrenade = registerCommonMaterials(ZeroingITEM, "flash_grenade", FlashGrenade::new);
    public static final Supplier<Item> PhotonShield = registerCommonMaterials(ZeroingITEM, "photon_shield", PhotonShield::new);
    public static final Supplier<Item> WormholeTunnelingBit = registerCommonMaterials(ZeroingITEM, "wormhole_tunneling_bit", WormholeTunnelingBit::new);
    public static final Supplier<Item> PlanetaryDrillingBomb = registerCommonMaterials(ZeroingITEM, "planetary_drilling_bomb", PlanetaryDrillingBomb::new);
    public static final Supplier<Item> PerpetualMotionMachine = registerCommonMaterials(ZeroingITEM, "perpetual_motion_machine", PerpetualMotionMachine::new);
    //正常物品注册
    //public static final RegistryObject<Item> HatredInundate = registerCommonMaterials(ZeroingITEM,"hatred_inundate", () -> new FCACuriosItem(new Item.Properties().stacksTo(64)));
    public FCAItemRegister(){
        //这是个获取配置文件的例子
        //float aaa = FCAconfig.ccccc.get();
    }

    public static void register(IEventBus bus){
        ZeroingITEM.register(bus);
    }
}
