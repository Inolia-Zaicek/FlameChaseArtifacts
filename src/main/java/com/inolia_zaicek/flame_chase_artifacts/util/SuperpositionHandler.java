package com.inolia_zaicek.flame_chase_artifacts.util;

import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.Map;

public class SuperpositionHandler {
    public static ItemStack mergeEnchantments(ItemStack input, ItemStack mergeFrom, boolean overmerge, boolean onlyTreasure) {
        ItemStack returnedStack = input.copy();
        Map<Enchantment, Integer> inputEnchants = EnchantmentHelper.getEnchantments(returnedStack);
        Map<Enchantment, Integer> mergedEnchants = EnchantmentHelper.getEnchantments(mergeFrom);

        for(Enchantment mergedEnchant : mergedEnchants.keySet()) {
            if (mergedEnchant != null) {
                int inputEnchantLevel = (Integer)inputEnchants.getOrDefault(mergedEnchant, 0);
                int mergedEnchantLevel = (Integer)mergedEnchants.get(mergedEnchant);
                if (!overmerge) {
                    mergedEnchantLevel = inputEnchantLevel == mergedEnchantLevel ? (mergedEnchantLevel + 1 > mergedEnchant.getMaxLevel() ? mergedEnchant.getMaxLevel() : mergedEnchantLevel + 1) : Math.max(mergedEnchantLevel, inputEnchantLevel);
                } else {
                    mergedEnchantLevel = inputEnchantLevel > 0 ? Math.max(mergedEnchantLevel, inputEnchantLevel) + 1 : Math.max(mergedEnchantLevel, inputEnchantLevel);
                    mergedEnchantLevel = Math.min(mergedEnchantLevel, 10);
                }

                boolean compatible = mergedEnchant.canEnchant(input);
                if (input.getItem() instanceof EnchantedBookItem) {
                    compatible = true;
                }

                for(Enchantment originalEnchant : inputEnchants.keySet()) {
                    if (originalEnchant != mergedEnchant && !mergedEnchant.isCompatibleWith(originalEnchant)) {
                        compatible = false;
                    }
                }

                if (compatible && (!onlyTreasure || mergedEnchant.isTreasureOnly() || mergedEnchant.isCurse())) {
                    inputEnchants.put(mergedEnchant, mergedEnchantLevel);
                }
            }
        }

        EnchantmentHelper.setEnchantments(inputEnchants, returnedStack);
        return returnedStack;
    }
}
