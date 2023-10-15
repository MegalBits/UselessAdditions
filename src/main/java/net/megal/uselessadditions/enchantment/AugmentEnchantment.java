package net.megal.uselessadditions.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class AugmentEnchantment extends Enchantment {
    protected AugmentEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return other instanceof AugmentEnchantment;
    }
}
