package net.megal.uselessadditions.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.UnaryOperator;

public class FinnedEnchantment extends AugmentEnchantment {
    public FinnedEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
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
        return 2;
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
                new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, level * 10 + 10, 0, true, true, true)
        );
    }
    @Override
    public boolean tickEffectCondition(LivingEntity user, ItemStack stack, int level) {
        return user.isSwimming();
    }
    @Override
    public @Nullable UnaryOperator<Style> getColor() {
        return style -> style.withColor(8954814);
    }
    
}
