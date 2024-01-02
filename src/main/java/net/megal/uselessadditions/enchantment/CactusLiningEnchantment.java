package net.megal.uselessadditions.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class CactusLiningEnchantment extends UEnchantment {
    public CactusLiningEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.BREAKABLE, slotTypes);
    }
    @Override
    public int getMinPower(int level) {
        return 3;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 35;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return super.isAcceptableItem(stack);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
