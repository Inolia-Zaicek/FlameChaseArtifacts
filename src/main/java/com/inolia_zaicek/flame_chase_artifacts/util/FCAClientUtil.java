package com.inolia_zaicek.flame_chase_artifacts.util;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

public class FCAClientUtil {
    public static Player isCurioEquipped() {
        if(Minecraft.getInstance().player!=null) {
            return Minecraft.getInstance().player;
        }
        return null;

    }
}