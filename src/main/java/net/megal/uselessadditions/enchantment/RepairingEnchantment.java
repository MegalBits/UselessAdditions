package net.megal.uselessadditions.enchantment;

import net.megal.uselessadditions.item.DamageableItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.InfinityEnchantment;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class RepairingEnchantment extends UEnchantment {
    protected RepairingEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
        super(weight, EnchantmentTarget.BREAKABLE, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return level * 24;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 30;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        if (stack.getItem() instanceof DamageableItem) {
            return false;
        }
        return super.isAcceptableItem(stack);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        if (other instanceof MendingEnchantment || other instanceof InfinityEnchantment) {
            return false;
        }
        return super.canAccept(other);
    }
}
