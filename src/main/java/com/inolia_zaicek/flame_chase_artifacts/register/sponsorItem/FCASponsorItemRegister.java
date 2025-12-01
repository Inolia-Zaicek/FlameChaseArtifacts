package com.inolia_zaicek.flame_chase_artifacts.register.sponsorItem;

import com.inolia_zaicek.flame_chase_artifacts.item.StrawberryRoselleItem;
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


public class FCASponsorItemRegister {
    public static final DeferredRegister<Item> ZeroingITEM=DeferredRegister.create(Registries.ITEM,MODID);
    public static List<RegistryObject<Item>> CommonItem=new ArrayList<>(List.of());

    public static RegistryObject<Item> registerCommonMaterials(DeferredRegister<Item> register,String name, Supplier<? extends Item> sup){
        RegistryObject<Item> object = register.register(name,sup);
        CommonItem.add(object);
        return object;
    }
    public static final RegistryObject<Item> StrawberryRoselle = registerCommonMaterials(ZeroingITEM,"strawberry_roselle", StrawberryRoselleItem::new);
    //public static final RegistryObject<Item> LuoShu = registerCommonMaterials(ZeroingITEM,"luoshu_puppet", StrawberryRoselleItem::new);


    public FCASponsorItemRegister(){
        //这是个获取配置文件的例子
        //float aaa = FCAconfig.ccccc.get();
    }

    public static void register(IEventBus bus){

            ZeroingITEM.register(bus);
    }
}
