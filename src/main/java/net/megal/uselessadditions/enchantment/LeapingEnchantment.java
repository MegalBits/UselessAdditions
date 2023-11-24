package net.megal.uselessadditions.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.UnaryOperator;

public class LeapingEnchantment extends AugmentEnchantment {
    public LeapingEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
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
        return 4;
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
    public @Nullable List<StatusEffectInstance> tickEffects(int level) {
        int amplifier = 2;
        if (level < 2) amplifier = 0;
        if (level < 4) amplifier = 1;
        return List.of(
                new StatusEffectInstance(StatusEffects.JUMP_BOOST, level * 40 + 20, amplifier, true, true, true)
        );
    }
    @Override
    public @Nullable UnaryOperator<Style> getColor() {
        return style -> style.withColor(0x6aa625);
    }
    
}
