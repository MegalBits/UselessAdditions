package net.megal.uselessadditions.enchantment;

import net.megal.uselessadditions.UAdd;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import org.jetbrains.annotations.Nullable;

import java.util.function.UnaryOperator;

public class ObsidianSmashingEnchantment extends AugmentEnchantment {
    public ObsidianSmashingEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
        super(weight, EnchantmentTarget.BREAKABLE, slotTypes);
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
        return 1;
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
    public float getMiningSpeed(int level, BlockState block) {
        if (block != null && block.isIn(UAdd.OBSIDIAN_BLOCKS)) return level*25;
        return 0;
    }

    @Override
    public int getAugmentSlots(int level) {
        return -1;
    }
    @Override
    public @Nullable UnaryOperator<Style> getColor() {
        return style -> style.withColor(0x3b2754);
    }
}
