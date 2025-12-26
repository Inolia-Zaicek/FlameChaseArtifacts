package com.inolia_zaicek.flame_chase_artifacts.item;

import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.register.craftItem.FCACraftItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class HopeAndDespairItem extends Item {
    public HopeAndDespairItem() {
        super(new Properties()
                        .stacksTo(1).fireResistant()
        );
    }
    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        String itemName = getTooltipItemName(); // 获取物品 ID
        {
            pTooltipComponents.add(Component.translatable("tooltip." + "flame_chase_artifacts" + "." + itemName + ".text")
                    .withStyle(ChatFormatting.GRAY));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand pUsedHand) {
        if (!level.isClientSide() && FCAUtil.isCurioEquipped(player, FCAItemRegister.HatredInundate.get()) &&
                /// 希望与绝望
                player.getMainHandItem().getItem() == FCACraftItemRegister.Hope.get() && player.getOffhandItem().getItem() == FCACraftItemRegister.Despair.get() ) {
            // 执行饰品替换
            replaceFirstHatredInundate(player);
            //消耗物品
            player.getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
            player.getItemInHand(InteractionHand.OFF_HAND).shrink(1);
            player.getCooldowns().addCooldown(this, 20);//设置冷却时间
        }
        return super.use(level, player, pUsedHand);
    }
    // 核心替换方法
    private static void replaceFirstHatredInundate(Player player) {
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
                        ItemStack newStack = new ItemStack(FCAItemRegister.OriginSin.get());
                        // 替换该槽的Item
                        stacksHandler.setStackInSlot(i, newStack);
                        return; // 只替换第一个符合条件的饰品
                    }
                }
            }
        });
    }
}
