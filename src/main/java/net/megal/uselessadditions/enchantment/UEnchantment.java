package net.megal.uselessadditions.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

import java.util.function.UnaryOperator;

public class UEnchantment extends Enchantment {
    protected UEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot ... slotTypes) {
        super(weight, target, slotTypes);
    }
    @Nullable
    public UnaryOperator<Style> getColor() {
        //style -> style.withColor(<color>)
        return null;
    }
    public int tooltipCount() {
        return 1;
    }
    @Override
    public Text getName(int level) {
        MutableText mutableText = Text.translatable(this.getTranslationKey());
        if (getColor() != null) {
            mutableText.styled(getColor());
        }
        else {
            if (this.isCursed()) {
                mutableText.formatted(Formatting.RED);
            } else {
                mutableText.formatted(Formatting.GRAY);
            }
        }
        if (level != 1 || this.getMaxLevel() != 1) {
            mutableText.append(ScreenTexts.SPACE).append(Text.translatable("enchantment.level." + level));
        }
        return mutableText;
    }
}
