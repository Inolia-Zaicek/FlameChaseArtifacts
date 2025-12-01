package com.inolia_zaicek.flame_chase_artifacts.register.craftItem;

import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.inolia_zaicek.flame_chase_artifacts.FlameChaseArtifacts.MODID;


public class FCACraftTab {

    public static final DeferredRegister<CreativeModeTab> creative_mode_tab_1= DeferredRegister.create(Registries.CREATIVE_MODE_TAB,MODID);
    public static final String MoreModTetraTab="item_group.flame_chase_artifacts.craft_item_tab";
    public static final Supplier<CreativeModeTab> materials=creative_mode_tab_1.register("craft_item",()-> CreativeModeTab.builder()
            //槽位位置
            .withTabsBefore(CreativeModeTabs.FOOD_AND_DRINKS)
            //物品栏名称
            .title(Component.translatable(MoreModTetraTab))
            //图标
            .icon(FCACraftItemRegister.MemoryCrystalShard.get()::getDefaultInstance)
            .displayItems((itemDisplayParameters, output) -> {
                // 遍历 CommonItem 列表中的物品
                for(RegistryObject<Item> curios: FCACraftItemRegister.CommonItem){
                    if(curios.isPresent()){
                        output.accept(curios.get());
                    }
                }
                //这个物品栏当中包含的物品
            })
            .build()
    );
    public static void register(IEventBus bus){
        creative_mode_tab_1.register(bus);
    }

}
