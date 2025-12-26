package com.inolia_zaicek.flame_chase_artifacts.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class FCAKeyMappingUtil {
    // 声明三个静态KeyMapping变量，用于存储按键映射实例
    public static KeyMapping KEYMAPPING;
    public static KeyMapping KEYMAPPING2;
    public static KeyMapping KEYMAPPING3;

    // 判断按键映射中的修饰键是否被按下，调用KeyMapping中修饰键状态的isActive方法并传入冲突上下文
    public static boolean isModifierPressed(KeyMapping keyMapping) {
        return keyMapping.getKeyModifier().isActive(keyMapping.getKeyConflictContext());
    }

    // 判断主键是否按下，使用Minecraft窗口实例检测指定键码的按键状态
    public static boolean isCommonKeyPressed(KeyMapping keyMapping) {
        return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), keyMapping.getKey().getValue());
    }

    // 判断按键映射是否被激活，条件是修饰键和主键同时按下
    public static boolean isKeyMappingPressed(KeyMapping keyMapping) {
        return isModifierPressed(keyMapping) && isCommonKeyPressed(keyMapping);
    }
}