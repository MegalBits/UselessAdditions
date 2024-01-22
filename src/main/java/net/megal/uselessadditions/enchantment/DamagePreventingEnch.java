package net.megal.uselessadditions.enchantment;

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

public class DamagePreventingEnch extends SpecEffEnchantment {
    public DamagePreventingEnch(Rarity weight) {
        super(weight, EnchantmentTarget.BREAKABLE, EquipmentSlot.values());
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    public @Nullable RegistryKey<DamageType> damageTypeToPrevent() {
        return null;
    }

    public @Nullable TagKey<DamageType> damageTypesToPrevent() {
        return null;
    }
}
