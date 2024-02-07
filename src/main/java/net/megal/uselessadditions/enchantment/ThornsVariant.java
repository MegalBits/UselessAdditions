package net.megal.uselessadditions.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.entity.EquipmentSlot;

public class ThornsVariant extends UEnchantment {
    protected ThornsVariant(Rarity weight, EquipmentSlot ... slotTypes) {
        super(weight, EnchantmentTarget.ARMOR, slotTypes);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        if (other instanceof ThornsEnchantment || other instanceof ThornsVariant) {
            return false;
        }
        return super.canAccept(other);
    }
}
