package net.megal.uselessadditions.enchantment;

import net.megal.uselessadditions.enchantment.DamagePreventingEnch;
import net.megal.uselessadditions.enchantment.SpecEffEnchantment;
import net.megal.uselessadditions.item.SpecialEffects;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.Nullable;

public class ObsidianPaddingEnchantment extends DamagePreventingEnch {
    public ObsidianPaddingEnchantment(Rarity weight) {
        super(weight);
    }

    @Override
    public int getMinPower(int level) {
        return 12;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 15;
    }

    @Override
    public String getRelatedEffect() {
        return SpecialEffects.OBSIDIAN_PADDING;
    }

    @Override
    public @Nullable TagKey<DamageType> damageTypesToPrevent() {
        return DamageTypeTags.IS_EXPLOSION;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return super.isAcceptableItem(stack);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
