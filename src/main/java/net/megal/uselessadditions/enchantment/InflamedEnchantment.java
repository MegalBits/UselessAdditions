package net.megal.uselessadditions.enchantment;

import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.text.Style;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.UnaryOperator;

public class InflamedEnchantment extends AugmentEnchantment {
    public InflamedEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
        super(weight, EnchantmentTarget.BREAKABLE, slotTypes);
    }
    @Override
    public int tooltipCount() {
        return 2;
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
    public boolean isAcceptableItem(ItemStack stack) {
        return false;
    }
    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        Random random = user.getRandom();
        Map.Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper.chooseEquipmentWith(UEnchantments.INFLAMED, user);
        if (InflamedEnchantment.shouldDamageAttacker(level, random)) {
            if (attacker != null && !attacker.isFireImmune()) {
                attacker.setFireTicks((int)Math.max(40*level*1.5f, attacker.getFireTicks()));
            }
            if (entry != null) {
                entry.getValue().damage(2, user, entity -> entity.sendEquipmentBreakStatus((EquipmentSlot)((Object)((Object)entry.getKey()))));
            }
        }
    }
    public static boolean shouldDamageAttacker(int level, Random random) {
        if (level <= 0) {
            return false;
        }
        return random.nextFloat() < 0.1f * (float)level;
    }
    @Override
    public int getProtectionAmount(int level, DamageSource source) {
        if (source.isIn(DamageTypeTags.IS_FIRE)) {
            return level;
        }
        return 0;
    }
    @Override
    public int getProtectionTooltip(int level) {
        return level*2;
    }
    @Override
    public @Nullable UnaryOperator<Style> getColor() {
        return style -> style.withColor(0xdf6b00);
    }
}
