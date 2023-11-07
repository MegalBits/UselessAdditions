package net.megal.uselessadditions.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import org.jetbrains.annotations.Nullable;

import java.util.function.UnaryOperator;

public class StarDamageEnchantment extends AugmentEnchantment {
    public StarDamageEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
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
    public float getAttackDamage(int level, EntityGroup group) {
        return (level-1) * .5f + 1;
    }
    @Override
    public float getDamageTooltip(int level) {
        return (level-1) * .5f + 1;
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
