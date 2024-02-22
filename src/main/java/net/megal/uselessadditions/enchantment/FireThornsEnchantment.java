package net.megal.uselessadditions.enchantment;

import com.chocohead.mm.api.ClassTinkerers;
import net.megal.uselessadditions.EarlyRiser;
import net.megal.uselessadditions.item.SpecialEffects;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;

import java.util.Map;
import java.util.function.Consumer;

public class FireThornsEnchantment extends ThornsVariant {

    public FireThornsEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
        super(weight, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 40;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        Random random = user.getRandom();
        Map.Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper.chooseEquipmentWith(UEnchantments.FIRE_THORNS, user);
        if (shouldBurnAttacker(level, random)) {
            if (attacker != null) {
                attacker.setFireTicks(random.nextBetween(level + 1, level + 4) * 10);
            }

            if (entry != null) {
                (entry.getValue()).damage(1, user, e -> {
                    e.sendEquipmentBreakStatus(entry.getKey());
                });
            }
        }

    }

    public static boolean shouldBurnAttacker(int level, Random random) {
        if (level <= 0) {
            return false;
        } else {
            return random.nextFloat() < 0.15F * (float)level;
        }
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
