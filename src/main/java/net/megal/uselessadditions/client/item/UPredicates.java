package net.megal.uselessadditions.client.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.uselessadditions.effect.UStatusEffects;
import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.UItems;
import net.megal.uselessadditions.item.WarpPearl;
import net.megal.uselessadditions.item.base.UBowItem;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BundleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class UPredicates {
    public static void registerPredicates() {
        ModelPredicateProviderRegistry.register(UItems.WARP_PEARL, new Identifier("inactive"), (stack, world, entity, seed) -> {
            if (entity != null && entity.hasStatusEffect(UStatusEffects.DESTABILIZED)) return 1;
            return 0;
        });

        ModelPredicateProviderRegistry.register(UItems.WARP_PEARL, new Identifier("charge"), (stack, world, entity, seed) -> {
            if (entity != null && stack == entity.getActiveItem()) return WarpPearl.getUseProgress(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 10;
            return 0;
        });

        bowPredicate(UItems.STONE_BOW);
        bowPredicate(UItems.IRON_BOW);
        bowPredicate(UItems.GOLDEN_BOW);
        bowPredicate(UItems.DIAMOND_BOW);
        bowPredicate(UItems.NETHERITE_BOW);
        bowPredicate(UItems.AMETHYST_BOW);
        bowPredicate(UItems.EMERALD_BOW);
        bowPredicate(UItems.BLAZE_METAL_BOW);
        bowPredicate(UItems.DRAGON_SCALE_BOW);
        bowPredicate(UItems.ANCIENT_BOW);

        bundlePredicate(UItems.BUNDLE);
        bundlePredicate(UItems.IRON_BUNDLE);
        bundlePredicate(UItems.DIAMOND_BUNDLE);
        bundlePredicate(UItems.NETHERITE_BUNDLE);
        bundlePredicate(UItems.DRAGON_BUNDLE);
    }

    private static void bowPredicate(Item item) {
        float bowUseTime;
        if (item instanceof UBowItem bowItem) bowUseTime = bowItem.useTime;
        else {
            bowUseTime = 1;
        }

        ModelPredicateProviderRegistry.register(item, new Identifier("pull"), (stack, world, entity, seed) -> {
            float maxUseTime = bowUseTime;
            if (UItemHelper.getEffects(stack).contains(SpecialEffects.CRYSTALLIZED_XP) && entity instanceof PlayerEntity player) {
                maxUseTime = bowUseTime - (Math.min(Math.min(player.experienceLevel, 80) * SpecialEffects.CXP_CHARGE_SPEED, bowUseTime));
            }

            if (entity != null && stack == entity.getActiveItem()) return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / (maxUseTime * 20);
            return 0;
        });

        ModelPredicateProviderRegistry.register(item, new Identifier("pulling"), (stack, world, entity, seed) -> {
            if (entity != null && entity.isUsingItem() && entity.getActiveItem() == stack) return 1;
            return 0;
        });
    }

    private static void bundlePredicate(Item item) {
        ModelPredicateProviderRegistry.register(item, new Identifier("filled"), (stack, world, entity, seed) -> BundleItem.getAmountFilled(stack));
    }
}
