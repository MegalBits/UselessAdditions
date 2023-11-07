package net.megal.uselessadditions.enchantment;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AugmentEnchantment extends UEnchantment {
    protected AugmentEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }
    @Override
    protected boolean canAccept(Enchantment other) {
        return other instanceof AugmentEnchantment;
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

    public float getMiningSpeed(int level, BlockState block) {
        return 0;
    }
    public float getMiningMultiplier(int level, BlockState block) {
        return 1;
    }
    public int getStatusDuration(int level) {
        return 0;
    }
    public int getAmplifier(int level) {
        return 0;
    }
    public int getDurability(int level) {
        return 0;
    }
    public int getAugmentSlots(int level) {
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
    public @Nullable List<StatusEffect> immuneEffects() {
        return null;
    }
    public @Nullable List<StatusEffectInstance> tickEffects(int level) {
        return null;
    }
    public void equippedTick(LivingEntity user, ItemStack stack, int level) {}
    //Used exclusively for tooltips
    public float getDamageTooltip(int level) {
        return 0f;
    }
    public float getMiningMultiplierTooltip(int level) {
        return 1f;
    }
    public int getProtectionTooltip(int level) {
        return 0;
    }
    /*
    public @Nullable List<EquippedAttributeModifier> attributeModifiers() {
        return null;
    }
    public record EquippedAttributeModifier(EntityAttribute attribute, String uuid, double amount, EntityAttributeModifier.Operation operation) {}
     */
}
