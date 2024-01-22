package net.megal.uselessadditions.enchantment.target;

import net.megal.uselessadditions.item.base.HammerItem;
import net.megal.uselessadditions.mixin.ExtendableEnchantmentTarget;
import net.minecraft.item.Item;

public class HammerEnchantmentTarget extends ExtendableEnchantmentTarget {
    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof HammerItem;
    }
}
