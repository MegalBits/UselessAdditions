package net.megal.uselessadditions.enchantment.target;

import net.megal.uselessadditions.item.base.ScytheItem;
import net.megal.uselessadditions.mixin.ExtendableEnchantmentTarget;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class SweepingEnchantmentTarget extends ExtendableEnchantmentTarget {
    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof ScytheItem || item instanceof SwordItem;
    }
}
