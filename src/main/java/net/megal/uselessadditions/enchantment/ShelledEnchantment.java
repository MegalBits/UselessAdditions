package net.megal.uselessadditions.enchantment;

import net.megal.uselessadditions.UAdd;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.util.Pair;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.UnaryOperator;

public class ShelledEnchantment extends AugmentEnchantment {
    public ShelledEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
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
    public @Nullable List<StatusEffectInstance> tickEffects(int level) {
        return List.of(
                new StatusEffectInstance(StatusEffects.RESISTANCE, level * 40, level+2, true, true, true),
                new StatusEffectInstance(StatusEffects.SLOWNESS, level * 40, level+3, true, true, true)
        );
    }

    @Override
    public boolean tickEffectCondition(LivingEntity user, ItemStack stack, int level) {
        if (!user.getWorld().isClient) {
            List<?> vel = UAdd.calculateVelocity(user);
            return (double)vel.get(0) == 0d && (float)vel.get(2) >= 60;
        }
        return false;
    }

    @Override
    public @Nullable UnaryOperator<Style> getColor() {
        return style -> style.withColor(0x47bf4a);
    }
}
