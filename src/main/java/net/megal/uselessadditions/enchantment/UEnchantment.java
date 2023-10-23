package net.megal.uselessadditions.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class UEnchantment extends Enchantment {
    protected UEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot ... slotTypes) {
        super(weight, target, slotTypes);
    }
    public int tooltipCount() {
        return 1;
    }
}
