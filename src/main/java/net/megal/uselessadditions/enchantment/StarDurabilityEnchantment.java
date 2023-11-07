package net.megal.uselessadditions.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import org.jetbrains.annotations.Nullable;

import java.util.function.UnaryOperator;

public class StarDurabilityEnchantment extends AugmentEnchantment {
    public StarDurabilityEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
        super(weight, EnchantmentTarget.BREAKABLE, slotTypes);
    }
    @Override
    public int tooltipCount() {
        return 2;
    }
    @Override
    public int getMinPower(int level) {
        return 1;
    }
    @Override
    public int getMaxPower(int level) {
        return 1;
    }
    @Override
    public int getMaxLevel() {
        return 6;
    }
    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return false;
    }
    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
    @Override
    public int getDurability(int level) {
        return level * 125;
    }
    @Override
    public int getAugmentSlots(int level) {
        return -1;
    }
    @Override
    public @Nullable UnaryOperator<Style> getColor() {
        return style -> style.withColor(0xb9c9c9);
    }
}
