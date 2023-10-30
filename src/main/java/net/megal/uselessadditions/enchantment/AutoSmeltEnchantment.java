package net.megal.uselessadditions.enchantment;

import net.megal.uselessadditions.UAdd;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

import java.util.function.UnaryOperator;

public class AutoSmeltEnchantment extends UEnchantment {

    public AutoSmeltEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
        super(weight, EnchantmentTarget.DIGGER, slotTypes);
    }
    @Override
    public int tooltipCount() {
        return 2;
    }
    @Override
    public int getMinPower(int level) {
        return 5;
    }
    @Override
    public int getMaxPower(int level) {
        return 40;
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
        return stack.isIn(UAdd.AUTO_SMELT);
    }
    @Override
    public boolean canAccept(Enchantment other) {
        if (other instanceof LuckEnchantment || other instanceof FireAspectEnchantment) {
            return false;
        }
        return super.canAccept(other);
    }
    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        int duration = (level-1) * 20 + 20;
        if (target.getFireTicks() < duration) target.setFireTicks(duration);
    }
    @Override
    public @Nullable UnaryOperator<Style> getColor() {
        return style -> style.withColor(0xFF9900);
    }
}
