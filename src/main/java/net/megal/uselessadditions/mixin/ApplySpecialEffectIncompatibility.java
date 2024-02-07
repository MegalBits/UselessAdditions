package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.megal.uselessadditions.item.DamageableItem;
import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(Enchantment.class)
public class ApplySpecialEffectIncompatibility {
    @ModifyReturnValue(at = @At("RETURN"),
            method = "isAcceptableItem(Lnet/minecraft/item/ItemStack;)Z")
    private boolean applyIncompatibility(boolean b, ItemStack stack) {
        Enchantment enchantment = ((Enchantment)(Object)this);

        List<String> effects = UItemHelper.getEffects(stack);
        for (String eff : effects) {
            if (SpecialEffects.effects.get(eff).enchantments.contains(enchantment)) return false;
        }

        return b;
    }
}
