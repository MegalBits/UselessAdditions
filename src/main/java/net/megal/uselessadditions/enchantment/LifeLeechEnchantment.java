package net.megal.uselessadditions.enchantment;

import com.chocohead.mm.api.ClassTinkerers;
import net.megal.uselessadditions.EarlyRiser;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class LifeLeechEnchantment extends UEnchantment {

    public LifeLeechEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
        super(weight, ClassTinkerers.getEnum(EnchantmentTarget.class, EarlyRiser.SCYTHE), slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return 4 + 10 * level;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 24;
    }

    @Override
    public int getMaxLevel() {
        return 2;
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
        return super.canAccept(other);
    }
}
