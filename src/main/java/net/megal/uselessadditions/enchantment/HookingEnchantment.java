package net.megal.uselessadditions.enchantment;

import com.chocohead.mm.api.ClassTinkerers;
import net.megal.uselessadditions.EarlyRiser;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.KnockbackEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class HookingEnchantment extends UEnchantment {

    public HookingEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
        super(weight, ClassTinkerers.getEnum(EnchantmentTarget.class, EarlyRiser.SCYTHE), slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return 7 + 20 * (level - 1);
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 32;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return super.isAcceptableItem(stack);
    }
    @Override
    public boolean canAccept(Enchantment other) {
        if (other instanceof KnockbackEnchantment) {
            return false;
        }
        return super.canAccept(other);
    }
}
