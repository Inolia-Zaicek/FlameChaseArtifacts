package com.inolia_zaicek.flame_chase_artifacts.Mixins;

import java.util.List;

import com.inolia_zaicek.flame_chase_artifacts.config.FCAconfig;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import com.inolia_zaicek.flame_chase_artifacts.util.FCAUtil;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.EnchantmentMenu; // 被混入的目标
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.EnchantedBookItem; // 书籍对象
import net.minecraft.world.item.ItemStack; // 物品堆栈
import net.minecraft.world.item.Items; // 常规物品列表
import net.minecraft.world.item.enchantment.EnchantmentHelper; // 附魔帮助工具
import net.minecraft.world.item.enchantment.EnchantmentInstance; // 附魔实例
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentMenu.class)
public abstract class FCAMixinEnchantmentContainer extends AbstractContainerMenu {

    protected FCAMixinEnchantmentContainer(MenuType<?> type, int id) {
        super(type, id);
    }

    @Inject(
            at = @At("HEAD"),
            method = "clickMenuButton",
            cancellable = true
    )
    private void onEnchantedItem(Player player, int clickedID, CallbackInfoReturnable<Boolean> info) {
        EnchantmentMenu container = null;
        try {
            container = EnchantmentMenu.class.cast(this);
        } catch (Exception ignored) {}
        if(container == null) return;

        // 检查玩家是否佩戴理性+德谬歌
        if (FCAUtil.isCurioEquipped(player, FCAItemRegister.ReasonCurios.get())
                &&FCAUtil.isCurioEquipped(player, FCAItemRegister.PristineLove.get()) ) {
            ItemStack inputItem = container.enchantSlots.getItem(0);
            int levelsRequired = clickedID + 1;

            if (container.costs[clickedID] <= 0 || inputItem.isEmpty() || (player.experienceLevel < levelsRequired || player.experienceLevel < container.costs[clickedID]) && !player.getAbilities().instabuild) {
                info.setReturnValue(false);
                return; // false;
            } else {
                EnchantmentMenu finalContainer = container;
                container.access.execute((world, blockPos) -> {
                    ItemStack enchantedItem = inputItem;
                    List<EnchantmentInstance> rolledEnchantments = finalContainer.getEnchantmentList(
                            inputItem, clickedID, finalContainer.costs[clickedID]
                    );
                    if (!rolledEnchantments.isEmpty()) {
                        Integer up = (int) (FCAconfig.reasonEgoCurios.get()*1);
                        ItemStack doubleRoll = EnchantmentHelper.enchantItem(
                                player.getRandom(), inputItem.copy(),
                                //附魔台等级
                                finalContainer.costs[clickedID] + up,
                                FCAconfig.reasonEgoCuriosTreasure.get() //是否应用宝藏附魔
                        );
                        player.onEnchantmentPerformed(inputItem, levelsRequired);
                        boolean isBookStack = inputItem.getItem() == Items.BOOK;
                        if (isBookStack) {
                            enchantedItem = new ItemStack(Items.ENCHANTED_BOOK);
                            CompoundTag compoundnbt = inputItem.getTag();
                            if (compoundnbt != null) {
                                enchantedItem.setTag(compoundnbt.copy());
                            }

                            finalContainer.enchantSlots.setItem(0, enchantedItem);
                        }

                        for (EnchantmentInstance enchantmentdata : rolledEnchantments) {
                            if (isBookStack) {
                                EnchantedBookItem.addEnchantment(enchantedItem, enchantmentdata);
                            } else {
                                enchantedItem.enchant(enchantmentdata.enchantment, enchantmentdata.level);
                            }
                        }

                        enchantedItem = FCAUtil.mergeEnchantments(enchantedItem, doubleRoll, false, false);
                        finalContainer.enchantSlots.setItem(0, enchantedItem);

                        player.awardStat(Stats.ENCHANT_ITEM);
                        if (player instanceof ServerPlayer) {
                            CriteriaTriggers.ENCHANTED_ITEM.trigger((ServerPlayer) player, enchantedItem, levelsRequired);
                        }

                        finalContainer.enchantSlots.setChanged();
                        finalContainer.enchantmentSeed.set(player.getEnchantmentSeed());
                        finalContainer.slotsChanged(finalContainer.enchantSlots);
                        world.playSound(null, blockPos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1.0F, world.random.nextFloat() * 0.1F + 0.9F);
                    }

                });

                info.setReturnValue(true);
                info.cancel();
            }
        }

    }
}