package net.megal.uselessadditions.enchantment;

import com.chocohead.mm.api.ClassTinkerers;
import net.megal.uselessadditions.EarlyRiser;
import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import org.jetbrains.annotations.Nullable;

public class CactusLiningEnchantment extends DamagePreventingEnch {
    public CactusLiningEnchantment(Rarity weight) {
        super(weight);
    }

    @Override
    public int getMinPower(int level) {
        return 3;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 15;
    }

    @Override
    public String getRelatedEffect() {
        return SpecialEffects.CACTUS_LINING;
    }

    @Override
    public @Nullable RegistryKey<DamageType> damageTypeToPrevent() {
        return DamageTypes.CACTUS;
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
