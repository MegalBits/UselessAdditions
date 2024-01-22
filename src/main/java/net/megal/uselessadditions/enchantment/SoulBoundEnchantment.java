package net.megal.uselessadditions.enchantment;

import com.chocohead.mm.api.ClassTinkerers;
import net.megal.uselessadditions.EarlyRiser;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class SoulBoundEnchantment extends UEnchantment {
    public SoulBoundEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.BREAKABLE, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return 45;
    }

    @Override
    public int getMaxPower(int level) {
        return 60;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return false;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }
    
    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return super.isAcceptableItem(stack);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        if (other == Enchantments.VANISHING_CURSE || other == Enchantments.BINDING_CURSE) {
            return false;
        }
        return super.canAccept(other);
    }
}
