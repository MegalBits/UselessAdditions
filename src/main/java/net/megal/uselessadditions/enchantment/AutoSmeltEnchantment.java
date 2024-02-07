package net.megal.uselessadditions.enchantment;

import com.chocohead.mm.api.ClassTinkerers;
import net.megal.uselessadditions.EarlyRiser;
import net.megal.uselessadditions.item.SpecialEffects;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class AutoSmeltEnchantment extends SpecEffEnchantment {

    public AutoSmeltEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
        super(weight, ClassTinkerers.getEnum(EnchantmentTarget.class, EarlyRiser.TOOL), slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return 43;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 5;
    }

    @Override
    public String getRelatedEffect() {
        return SpecialEffects.AUTO_SMELT;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof SwordItem || stack.getItem() instanceof BowItem || super.isAcceptableItem(stack);
    }
    @Override
    public boolean canAccept(Enchantment other) {
        if (other instanceof FireAspectEnchantment || other instanceof FlameEnchantment) {
            return false;
        }
        return super.canAccept(other);
    }
    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        target.setFireTicks(level * 60);
    }
}
