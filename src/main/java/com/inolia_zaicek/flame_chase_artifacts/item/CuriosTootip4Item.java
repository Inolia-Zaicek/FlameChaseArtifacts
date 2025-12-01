package com.inolia_zaicek.flame_chase_artifacts.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;

public class CuriosTootip4Item extends Item implements ICurioItem {
    public CuriosTootip4Item() {
        super((new Properties()).stacksTo(1).fireResistant());
        //.rarity(Rarity.EPIC)
    }
    //死亡不掉落
    @NotNull
    public ICurio.@NotNull DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }
    // 鼠标经过物品时显示文字，按下shift键后显示另一行提示文字
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.text".formatted(this.getTooltipItemName() ))
                .withStyle(style -> style.withColor(TextColor.fromRgb( this.getTooltipShiftColor() ))));
        pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.text1".formatted(this.getTooltipItemName() ))
                .withStyle(style -> style.withColor(TextColor.fromRgb( this.getTooltipShiftColor() ))));
        pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.text2".formatted(this.getTooltipItemName() ))
                .withStyle(style -> style.withColor(TextColor.fromRgb( this.getTooltipShiftColor() ))));
        pTooltipComponents.add(Component.translatable("tooltip.flame_chase_artifacts.%s.text3".formatted(this.getTooltipItemName() ))
                .withStyle(style -> style.withColor(TextColor.fromRgb( this.getTooltipShiftColor() ))));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
    //获取物品id以自动更新文本
    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }
    // 新增方法：提供一个默认的Shift文本颜色，子类可以覆盖它
    protected int getTooltipShiftColor() {
        return 0xA0A0A0; // 默认颜色：灰
    }
    //隐藏属性提示
    /*
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips, ItemStack stack) {
        return Collections.emptyList();
    }

     */
}
