package com.inolia_zaicek.flame_chase_artifacts.register.craftItem;

import com.inolia_zaicek.flame_chase_artifacts.item.AsWrittenByWhomItem;
import com.inolia_zaicek.flame_chase_artifacts.item.HopeAndDespairItem;
import com.inolia_zaicek.flame_chase_artifacts.item.TooltipItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts.MODID;


public class FCACraftItemRegister {
    public static final DeferredRegister<Item> ZeroingITEM=DeferredRegister.create(Registries.ITEM,MODID);
    public static List<RegistryObject<Item>> CommonItem=new ArrayList<>(List.of());

    public static RegistryObject<Item> registerCommonMaterials(DeferredRegister<Item> register,String name, Supplier<? extends Item> sup){
        RegistryObject<Item> object = register.register(name,sup);
        CommonItem.add(object);
        return object;
    }
    //素材
    public static final RegistryObject<Item> AsWrittenByWhom = registerCommonMaterials(ZeroingITEM,"as_written_by_whom", AsWrittenByWhomItem::new);
    public static final RegistryObject<Item> Hope = registerCommonMaterials(ZeroingITEM,"hope", HopeAndDespairItem::new);
    public static final RegistryObject<Item> Despair = registerCommonMaterials(ZeroingITEM,"despair", HopeAndDespairItem::new);
    public static final RegistryObject<Item> MemoryCrystalShard = registerCommonMaterials(ZeroingITEM,"memory_crystal_shard", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GoldenRemains = registerCommonMaterials(ZeroingITEM,"golden_remains", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> UndyingEmbers = registerCommonMaterials(ZeroingITEM,"undying_embers", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> UndyingStarlight = registerCommonMaterials(ZeroingITEM,"undying_starlight", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> JadeFeather = registerCommonMaterials(ZeroingITEM,"jade_feather", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> FruitOfTime = registerCommonMaterials(ZeroingITEM,"fruit_of_time", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LoneStardust = registerCommonMaterials(ZeroingITEM,"lone_stardust", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> VanquishedFlowSReticence = registerCommonMaterials(ZeroingITEM,"vanquished_flow_s_reticence", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> EternalLament = registerCommonMaterials(ZeroingITEM,"eternal_lament", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GloryAspersedTorso = registerCommonMaterials(ZeroingITEM,"glory_aspersed_torso", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));


    public FCACraftItemRegister(){
        //这是个获取配置文件的例子
        //float aaa = FCAconfig.ccccc.get();
    }

    public static void register(IEventBus bus){

            ZeroingITEM.register(bus);
    }
}
