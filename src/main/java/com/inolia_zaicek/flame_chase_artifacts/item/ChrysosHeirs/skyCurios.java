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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAClientUtil;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAClientUtil;

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
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.up_text".formatted(this.getTooltipItemName()))
                .withStyle(style -> style.withColor(TextColor.fromRgb( this.getTooltipShiftColor() ))));

        pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.text".formatted(this.getTooltipItemName() )
                        ,(int) (FCAconfig.skyCuriosRange.get() * 1),(int) (FCAconfig.skyCuriosRange.get() * 1) ,(float) (FCAconfig.skyCuriosNumber.get() * 100) )
                .withStyle(ChatFormatting.GRAY));

        if(FCAClientUtil.isCurioEquipped()!=null&& FCAUtil.isCurioEquipped(FCAClientUtil.isCurioEquipped(), FCAItemRegister.PristineLove.get()) ){

            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.ego_text".formatted(this.getTooltipItemName()))
                    .withStyle(style -> style.withColor(TextColor.fromRgb( this.getTooltipShiftColor() ))));

            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_sky_blessing",
                    (int) (FCAconfig.skyEgoCurios.get() * 1) ,(int) (FCAconfig.skyEgoCurios.get() * 1)
                    ,(int) (FCAconfig.skyEgoCuriosRegenerationLevel.get() * 1),(int) (FCAconfig.skyEgoCuriosSpeedLevel.get() * 1)
            ).withStyle(style -> style.withColor(TextColor.fromRgb(0x5AFFC9))));

        }
        var skyKey = "sky_curios_get_nbt";
        if(FCAClientUtil.isCurioEquipped()!=null && ! FCAClientUtil.isCurioEquipped().getPersistentData().getBoolean(skyKey) && FCAconfig.sky_get.get() ){
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.get_text".formatted(this.getTooltipItemName()),
                    (int)(FCAconfig.sky_get_y.get()*1),(float)(FCAconfig.sky_get_number.get()*100)
            ).withStyle(ChatFormatting.DARK_GRAY));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
