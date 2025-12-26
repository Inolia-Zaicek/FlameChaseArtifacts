package com.inolia_zaicek.flame_chase_artifacts.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import com.inolia_zaicek.flame_chase_artifacts.register.FCAItemRegister;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings({"all", "removal"})
public class FCAUtil {
    public static FCAUtil INSTANCE;
    public static FCAUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FCAUtil();
        }
        return INSTANCE;
    }

    /**
     * 检查实体是否装备了指定的curio
     * @param entity 目标实体
     * @param itemStackSupplier 获取特定物品的Supplier
     * @return 如果装备了返回true，否则false
     */
    public static boolean isCurioEquipped(LivingEntity entity, Item itemStackSupplier) {
        Optional<SlotResult> slotResult = CuriosApi.getCuriosHelper().findFirstCurio(entity,itemStackSupplier);
        return slotResult.isPresent();
    }
    //获取周围敌人列表
    public static List<Mob> mobList(double range, LivingEntity entity){
        double x =entity.getX();
        double y =entity.getY();
        double z =entity.getZ();
        return entity.getCommandSenderWorld().getEntitiesOfClass(Mob.class,new AABB(x+range,y+range,z+range,x-range,y-range,z-range));
    }
    //获取周围玩家列表
    public static List<Player> PlayerList(double range, LivingEntity entity){
        double x =entity.getX();
        double y =entity.getY();
        double z =entity.getZ();
        return entity.getCommandSenderWorld().getEntitiesOfClass(Player.class,new AABB(x+range,y+range,z+range,x-range,y-range,z-range));
    }

    public static boolean isMeleeAttack(DamageSource source) {
        return !source.isIndirect() && (source.is(DamageTypes.MOB_ATTACK) || source.is(DamageTypes.PLAYER_ATTACK) || source.is(DamageTypes.MOB_ATTACK_NO_AGGRO));
    }

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
    public static boolean isBossEntity(EntityType<?> entity) {
        // 检查 "flame_chase_artifacts:bosses" tag
        boolean isMoreTetraBoss = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.tags()).getTag(
                TagKey.create(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), new ResourceLocation("flame_chase_artifacts", "bosses"))
        ).contains(entity);
        // 检查 "forge:bosses" tag
        boolean isForgeBoss = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.tags()).getTag(
                TagKey.create(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), new ResourceLocation("forge", "bosses"))
        ).contains(entity);
        // 只要满足其中一个 tag 即可
        return isMoreTetraBoss || isForgeBoss;
    }
    //判断饰品数量
    public static int getCuriosCount(LivingEntity entity, Item targetItem) {
        Optional<ICuriosItemHandler> curiosOpt = CuriosApi.getCuriosInventory(entity).resolve();
        if (!curiosOpt.isPresent()) {
            return 0;
        }
        ICuriosItemHandler curiosHandler = curiosOpt.get();
        IItemHandlerModifiable itemHandler = curiosHandler.getEquippedCurios();
        if (itemHandler == null) {
            return 0;
        }
        int count = 0;
        int slots = itemHandler.getSlots();
        for (int i = 0; i < slots; i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (!stack.isEmpty() && stack.getItem() == targetItem) {
                count++;
            }
        }
        return count;
    }
    //生成source
    public static DamageSource source(Level level, ResourceKey<DamageType> type, @Nullable Entity direct, @Nullable Entity causing){
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type), direct,causing);
    }
    //有源
    public static DamageSource hasSource(Level level, ResourceKey<DamageType> type, @Nullable Entity entity){
        return source(level,type,entity, entity);
    }
    //无源
    public static DamageSource source(Level level, ResourceKey<DamageType> type){
        return source(level,type,null, null);
    }
}