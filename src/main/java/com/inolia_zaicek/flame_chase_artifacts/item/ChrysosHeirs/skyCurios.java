package com.inolia_zaicek.flame_chase_artifacts.item.ChrysosHeirs;

import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.item.FCACuriosItem;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class skyCurios extends FCACuriosItem {
    public skyCurios() {
        super();
    }
    @Override
    protected int getTooltipShiftColor() {
        return 0x5AFFC9; // 返回你想要的RGB 值
    }
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        var skyKey = "sky_curios_get_nbt";
        if(Minecraft.getInstance().player!=null && ! Minecraft.getInstance().player.getPersistentData().getBoolean(skyKey) ){
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.get_text".formatted(this.getTooltipItemName())
            ).withStyle(ChatFormatting.DARK_GRAY));
        }
        pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.up_text".formatted(this.getTooltipItemName()))
                .withStyle(style -> style.withColor(TextColor.fromRgb( this.getTooltipShiftColor() ))));

        pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.text".formatted(this.getTooltipItemName() )
                        ,(int) (FCAconfig.skyCuriosRange.get() * 1),(int) (FCAconfig.skyCuriosRange.get() * 1) ,(float) (FCAconfig.skyCuriosNumber.get() * 100) )
                .withStyle(ChatFormatting.GRAY));

        if(Minecraft.getInstance().player!=null&& FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.PristineLove.get()) ){

            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.ego_text".formatted(this.getTooltipItemName()))
                    .withStyle(style -> style.withColor(TextColor.fromRgb( this.getTooltipShiftColor() ))));

            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_sky_blessing",
                    (int) (FCAconfig.skyEgoCurios.get() * 1) ,(int) (FCAconfig.skyEgoCurios.get() * 1)
                    ,(int) (FCAconfig.skyEgoCuriosRegenerationLevel.get() * 1),(int) (FCAconfig.skyEgoCuriosSpeedLevel.get() * 1)
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x5AFFC9))));

        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
