package com.inolia_zaicek.flame_chase_artifacts.util;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class FCAClientHandler {
    // 注册按键映射方法，接受Forge事件RegisterKeyMappingsEvent作为参数
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        // 创建第一个按键映射，描述为"jcrj.copy.recipe.json"，默认无绑定键(-1)，类别为"jei.key.category.dev.tools"
        event.register(FCAKeyMappingUtil.KEYMAPPING = new KeyMapping(
                "flame_chase_artifacts.key.coin_of_whimsy",
                GLFW.GLFW_KEY_X,  // -1表示默认不绑定任何键，GLFW.GLFW_KEY_C则是C键
                "flame_chase_artifacts.key" // 按键归类于"XXX"
        ));
    }
}