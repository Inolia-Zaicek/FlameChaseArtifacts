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
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.ForgeMod;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class worldbearingCurios extends FCACuriosItem {
    public worldbearingCurios() {
        super();
    }
    @Override
    protected int getTooltipShiftColor() {
        return 0x7BEBFC; // 返回你想要的RGB 值
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
        //增加属性的例子
        atts.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "world_bearing_blessing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "world_bearing_blessing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(Attributes.ARMOR, new AttributeModifier(uuid, "world_bearing_blessing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "world_bearing_blessing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "world_bearing_blessing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "world_bearing_blessing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "world_bearing_blessing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(Attributes.LUCK, new AttributeModifier(uuid, "world_bearing_blessing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(ForgeMod.STEP_HEIGHT_ADDITION.get(), new AttributeModifier(uuid, "world_bearing_blessing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        atts.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, "world_bearing_blessing", FCAconfig.worldbearingCurios.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        return atts;
    }
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        var worldbearingKey = "worldbearing_curios_get_nbt";
        if(Minecraft.getInstance().player!=null && ! Minecraft.getInstance().player.getPersistentData().getBoolean(worldbearingKey) ){
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.get_text".formatted(this.getTooltipItemName())
            ).withStyle(ChatFormatting.DARK_GRAY));
        }
        pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.up_text".formatted(this.getTooltipItemName()))
                .withStyle(style -> style.withColor(TextColor.fromRgb( this.getTooltipShiftColor() ))));
            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.text".formatted(this.getTooltipItemName() )
                            ,(float) (FCAconfig.worldbearingCurios.get() * 100))
                    .withStyle(ChatFormatting.GRAY));

        if(Minecraft.getInstance().player!=null&& FCAUtil.isCurioEquipped(Minecraft.getInstance().player, FCAItemRegister.PristineLove.get()) ){

            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.ego_text".formatted(this.getTooltipItemName()))
                    .withStyle(style -> style.withColor(TextColor.fromRgb( this.getTooltipShiftColor() ))));

            pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.pristine_love.ode_to_worldbearing_blessing",
                    (float) (FCAconfig.worldbearingEgoCurios.get() * 100)).withStyle(style -> style.withColor(TextColor.fromRgb(0x7BEBFC))));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips, ItemStack stack) {
        return Collections.emptyList();
    }

}