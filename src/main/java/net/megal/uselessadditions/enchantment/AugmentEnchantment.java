package net.megal.uselessadditions.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class AugmentEnchantment extends UEnchantment {
    protected AugmentEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }
    @Override
    protected boolean canAccept(Enchantment other) {
        return other instanceof AugmentEnchantment;
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
    public int getAugmentSlots(int level) {return 0;}
    public int getExperience(int level) {return 0;}
    public float getExperienceMultiplier(int level) {return 1;}
    public boolean isFireproof() {
        return false;
    }
    //Used exclusively for tooltips
    public float getDamage(int level) {
        return 0f;
    }
}
