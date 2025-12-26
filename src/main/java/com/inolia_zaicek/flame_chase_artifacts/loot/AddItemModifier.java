package com.inolia_zaicek.flame_chase_artifacts.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public class AddItemModifier extends LootModifier {
    public static final Supplier<Codec<AddItemModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.create(inst -> codecStart(inst)
                    .and(ForgeRegistries.ITEMS.getCodec().listOf().fieldOf("items").forGetter(m -> m.items))
                    .and(Codec.FLOAT.fieldOf("chance").forGetter(m -> m.chance))
                    .apply(inst, AddItemModifier::new)));

    private final List<Item> items;
    private final float chance;  //独立概率

    public AddItemModifier(LootItemCondition[] conditionsIn, List<Item> items, float chance) {
        super(conditionsIn);
        this.items = items;
        this.chance = chance;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(context)) {
                return generatedLoot;
            }
        }

        // 每个物品独立判定概率
        for (Item item : items) {
            if (context.getRandom().nextFloat() < chance) {
                generatedLoot.add(new ItemStack(item));
            }
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}