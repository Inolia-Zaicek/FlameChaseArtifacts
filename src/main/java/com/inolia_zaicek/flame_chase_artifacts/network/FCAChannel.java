package com.inolia_zaicek.flame_chase_artifacts.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class FCAChannel {
    private static final String PROTOCOL_VERSION = "1.0";

    // 延迟初始化，避免重复注册
    public static SimpleChannel INSTANCE;

    public static void init() {
        if (INSTANCE != null) return; // 防止多次初始化
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation("flame_chase_artifacts", "channel"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );
        int id = 0;
        /*
        INSTANCE.registerMessage(id++, ReplaceCurioPacket.class,
                ReplaceCurioPacket::encode,
                ReplaceCurioPacket::decode,
                ReplaceCurioPacket::handle
        );

         */
    }
}