package com.inolia_zaicek.flame_chase_artifacts;

// import com.inolia_zaicek.daily_delight.Curios.BentoBox.WoodenBentoBoxCurios; // This class might be removed or adapted if you fully replace it

import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.event.ChrysosHeirs.*;
import com.inolia_zaicek.flame_chase_artifacts.event.CurrencyWarsEvent;
import com.inolia_zaicek.flame_chase_artifacts.event.HurtEvent;
import com.inolia_zaicek.flame_chase_artifacts.event.IronTombErosionEvent;
import com.inolia_zaicek.flame_chase_artifacts.event.curiosGive.CuriosGiveEvent;
import com.inolia_zaicek.flame_chase_artifacts.loot.ModLootModifiers;
import com.inolia_zaicek.flame_chase_artifacts.network.FCAChannel;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAAttributes;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAEffectsRegister;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.register.FCATab;
import com.inolia_zaicek.flame_chase_artifacts.register.craftItem.FCACraftItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.register.craftItem.FCACraftTab;
import com.inolia_zaicek.flame_chase_artifacts.register.sponsorItem.FCASponsorItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.register.sponsorItem.FCASponsorTab;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAClientHandler;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

import java.util.*;

@SuppressWarnings({"all", "removal"})
@Mod.EventBusSubscriber(modid = FlameChaseArtifacts.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
@Mod(FlameChaseArtifacts.MODID)
public class FlameChaseArtifacts {

    public static final String MODID = "flame_chase_artifacts";
    public static final Logger LOGGER = LogUtils.getLogger();
    public FlameChaseArtifacts() {
        if(FMLEnvironment.dist == Dist.CLIENT) {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(FCAClientHandler::registerKeyMappings);
        }
        init();
        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        //全局，全部存档都注册这个配置文件
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, FCAconfig.SPEC);
        //每个存档都注册一个配置文件
        //modLoadingContext.registerConfig(ModConfig.Type.SERVER, FCAconfig.SPEC);
        //只有本地config，每个客户端分开
        //modLoadingContext.registerConfig(ModConfig.Type.CLIENT, FCAconfig.SPEC);

    }
    public void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        FCAAttributes.ATTRIBUTES.register(bus);
        FCAItemRegister.register(bus);
        FCATab.register(bus);
            FCACraftItemRegister.register(bus);
            FCACraftTab.register(bus);
        ModLootModifiers.register(bus);
        FCASponsorItemRegister.register(bus);
        FCASponsorTab.register(bus);
        FCAEffectsRegister.INOEFFECT.register(bus);
        MinecraftForge.EVENT_BUS.register(Death.class);
        MinecraftForge.EVENT_BUS.register(Earth.class);
        MinecraftForge.EVENT_BUS.register(HurtEvent.class);
        MinecraftForge.EVENT_BUS.register(Ocean.class);
        MinecraftForge.EVENT_BUS.register(Passage.class);
        MinecraftForge.EVENT_BUS.register(Reason.class);
        MinecraftForge.EVENT_BUS.register(Romance.class);
        MinecraftForge.EVENT_BUS.register(Sky.class);
        MinecraftForge.EVENT_BUS.register(Strife.class);
        MinecraftForge.EVENT_BUS.register(Time.class);
        MinecraftForge.EVENT_BUS.register(Trickery.class);
        MinecraftForge.EVENT_BUS.register(Sin.class);
        MinecraftForge.EVENT_BUS.register(IronTombErosionEvent.class);
        MinecraftForge.EVENT_BUS.register(CuriosGiveEvent.class);
        MinecraftForge.EVENT_BUS.register(CurrencyWarsEvent.class);
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
        });
    }

    // 客户端设置事件，用于注册渲染器和GUI屏幕
    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
        });
    }

    //注册掉落物
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
    }

    public static ResourceLocation prefix(String name){
        return new ResourceLocation(MODID,name.toLowerCase(Locale.ROOT));
    }

}
