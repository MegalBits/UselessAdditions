package net.megal.uselessadditions.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class DiamondEnchantment extends AugmentEnchantment {
    public DiamondEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
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
        return 1;
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
    public boolean isCursed() {
        return true;
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
        return level * 250;
    }

    @Override
    public int getAugmentSlots(int level) {
        return level * 4 + 1;
    }

    @Override
    public Text getName(int level) {
        MutableText mutableText = Text.translatable(this.getTranslationKey());
        if (this.isCursed()) {
            mutableText.styled(style -> style.withColor(0x4aedd9));
        } else {
            mutableText.formatted(Formatting.GRAY);
        }
        if (level != 1 || this.getMaxLevel() != 1) {
            mutableText.append(ScreenTexts.SPACE).append(Text.translatable("enchantment.level." + level));
        }
        return mutableText;
    }
}