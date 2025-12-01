package com.inolia_zaicek.flame_chase_artifacts.network.parket;

import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.register.craftItem.FCACraftItemRegister;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;
import java.util.function.Supplier;

public class ReplaceCurioPacket {

    // 构造方法，空，有需要可以扩展传递参数
    public ReplaceCurioPacket() { }

    // 编码写出
    public void encode(FriendlyByteBuf buf) {
        // 无参数，无需写入数据
    }

    // 解码
    public static ReplaceCurioPacket decode(FriendlyByteBuf buf) {
        // 无参数，直接返回新实例
        return new ReplaceCurioPacket();
    }

    // 处理
    public static void handle(ReplaceCurioPacket msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player != null) {
                // 判断主手物品是否为特殊物品（请替换为实际物品）
                ItemStack mainHandStack = player.getMainHandItem();
                if (mainHandStack.getItem() == FCACraftItemRegister.AsWrittenByWhom.get()) {
                    // 扣减主手物品1
                    if (!mainHandStack.isEmpty()) {
                        mainHandStack.shrink(1);
                        // 执行饰品替换
                        replaceFirstHatredInundate(player);
                        // 发送更新，确保客户端同步
                        player.inventoryMenu.broadcastChanges();
                    }
                }
            }
        });
        ctx.setPacketHandled(true);
    }

    // 核心替换方法
    private static void replaceFirstHatredInundate(ServerPlayer player) {
        LazyOptional<ICuriosItemHandler> curiosOpt = CuriosApi.getCuriosInventory(player);
        curiosOpt.ifPresent(curios -> {
            Map<String, ICurioStacksHandler> curiosMap = curios.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curiosMap.entrySet()) {
                ICurioStacksHandler handler = entry.getValue();
                IDynamicStackHandler stacksHandler = handler.getStacks();
                int slotCount = stacksHandler.getSlots();
                for (int i = 0; i < slotCount; i++) {
                    ItemStack stack = stacksHandler.getStackInSlot(i);
                    if (!stack.isEmpty() && stack.getItem() == FCAItemRegister.HatredInundate.get()) {
                        // 创建新ItemStack（目标饰品）
                        ItemStack newStack = new ItemStack(FCAItemRegister.PristineLove.get());
                        // 替换该槽的Item
                        stacksHandler.setStackInSlot(i, newStack);
                        return; // 只替换第一个符合条件的饰品
                    }
                }
            }
        });
    }
}