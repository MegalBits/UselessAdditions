package net.megal.uselessadditions.enchantment.target;

import net.megal.uselessadditions.mixin.ExtendableEnchantmentTarget;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;

public class AnyEnchantmentTarget extends ExtendableEnchantmentTarget {
    @Override
    public boolean isAcceptableItem(Item item) {
        return true;
    }
}
