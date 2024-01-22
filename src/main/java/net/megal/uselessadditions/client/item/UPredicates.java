package net.megal.uselessadditions.client.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.uselessadditions.item.UItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.BundleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class UPredicates {
    public static void registerPredicates() {
        bundlePredicate(UItems.BUNDLE);
        bundlePredicate(UItems.IRON_BUNDLE);
        bundlePredicate(UItems.DIAMOND_BUNDLE);
        bundlePredicate(UItems.NETHERITE_BUNDLE);
        bundlePredicate(UItems.DRAGON_BUNDLE);
    }

    private static void bundlePredicate(Item item) {
        ModelPredicateProviderRegistry.register(item, new Identifier("filled"), (stack, world, entity, seed) -> BundleItem.getAmountFilled((ItemStack)stack));
    }
}
