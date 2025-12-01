package com.inolia_zaicek.flame_chase_artifacts.item.ChrysosHeirs;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.item.FCACuriosItem;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;


public class romanceCurios extends FCACuriosItem {
    public romanceCurios() {
        super();
    }
    @Override
    protected int getTooltipShiftColor() {
        return 0xFFFF3C; // 返回你想要的RGB 值
    }
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        var romanceKey = "romance_curios_get_nbt";
        if(Minecraft.getInstance().player!=null && ! Minecraft.getInstance().player.getPersistentData().getBoolean(romanceKey) ){
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.get_text".formatted(this.getTooltipItemName())
            ).withStyle(ChatFormatting.DARK_GRAY));
        }
        pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.up_text".formatted(this.getTooltipItemName()))
                .withStyle(style -> style.withColor(TextColor.fromRgb( this.getTooltipShiftColor() ))));

            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.text".formatted(this.getTooltipItemName() )
                            ,(int) (FCAconfig.romanceCurios.get() * 1),(int) (FCAconfig.romanceCurios.get() * 1))
                    .withStyle(ChatFormatting.GRAY));
        if(Minecraft.getInstance().player!=null&& FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.PristineLove.get()) ){

            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.ego_text".formatted(this.getTooltipItemName()))
                    .withStyle(style -> style.withColor(TextColor.fromRgb( this.getTooltipShiftColor() ))));

            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_romance_blessing",
                    (int) (FCAconfig.romanceEgoCuriosTime.get() * 1), (int) (FCAconfig.romanceEgoCuriosNumber.get() * 1)).withStyle(style -> style.withColor(TextColor.fromRgb(0xFFFF3C))));

        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
        atts.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "romance_curios", FCAconfig.romanceCuriosNumber.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "romance_curios", FCAconfig.romanceCuriosNumber.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        return atts;
    }
}
