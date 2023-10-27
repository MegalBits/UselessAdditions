package net.megal.uselessadditions.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class DraconicEnchantment extends AugmentEnchantment {
    public DraconicEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
        super(weight, EnchantmentTarget.BREAKABLE, slotTypes);
    }
    @Override
    public int tooltipCount() {
        return 3;
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
    public int getDurability(int level) {
        return level * 1800;
    }
    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        return level * 8f;
    }
    @Override
    public float getDamage(int level) {
        return level * 11f;
    }
    @Override
    public int getAugmentSlots(int level) {
        return level * 14 + 1;
    }
    @Override
    public Text getName(int level) {
        MutableText mutableText = Text.translatable(this.getTranslationKey());
        if (this.isCursed()) {
            mutableText.styled(style -> style.withColor(0x3c1e48));
        } else {
            mutableText.formatted(Formatting.GRAY);
        }
        if (level != 1 || this.getMaxLevel() != 1) {
            mutableText.append(ScreenTexts.SPACE).append(Text.translatable("enchantment.level." + level));
        }
        return mutableText;
    }
}
