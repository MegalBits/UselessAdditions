package net.megal.uselessadditions.enchantment;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

import java.util.List;

public class SpecEffEnchantment extends UEnchantment {
    public SpecEffEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot ... slotTypes) {
        super(weight, target, slotTypes);
    }

    public String getRelatedEffect() {
        return "";
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return super.isAcceptableItem(stack) && !UItemHelper.getEffects(stack).contains(getRelatedEffect());
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other) && !SpecialEffects.effects.get(getRelatedEffect()).enchantments.contains(other);
    }
}
