package net.megal.uselessadditions.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class WitherEnchantment extends Enchantment {
    public WitherEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
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
        return 3;
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
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        int duration = (level-1) * 100 + 200;
        if (target instanceof LivingEntity) ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, duration, 0));
    }
    @Override
    public Text getName(int level) {
        MutableText mutableText = Text.translatable(this.getTranslationKey());
        if (this.isCursed()) {
            mutableText.formatted(Formatting.DARK_GRAY);
        } else {
            mutableText.formatted(Formatting.GRAY);
        }
        if (level != 1 || this.getMaxLevel() != 1) {
            mutableText.append(ScreenTexts.SPACE).append(Text.translatable("enchantment.level." + level));
        }
        return mutableText;
    }
}
