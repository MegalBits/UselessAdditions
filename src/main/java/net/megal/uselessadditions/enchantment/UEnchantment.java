package net.megal.uselessadditions.enchantment;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
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

    public float getMiningSpeed(int level, BlockState block) {
        return 0;
    }

    public float getMiningSpeedMultiplier(int level, BlockState block) {
        return 1;
    }

    public int getDurability(int level) {
        return 0;
    }

    public int getExperience(int level) {
        return 0;
    }

    public float getExperienceMultiplier(int level) {
        return 1;
    }

    public boolean isFireproof() {
        return false;
    }

    public boolean tickEffectCondition(LivingEntity user, ItemStack stack, int level) {
        return false;
    }

    public void equippedTick(LivingEntity user, ItemStack stack, int level) {}

    @Override
    public Text getName(int level) {
        MutableText mutableText = (MutableText) super.getName(level);
        if (getColor() != null) {
            mutableText.styled(getColor());
        }
        return mutableText;
    }
}
