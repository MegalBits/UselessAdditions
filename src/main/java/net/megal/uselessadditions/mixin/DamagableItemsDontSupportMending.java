package net.megal.uselessadditions.mixin;

import com.chocohead.mm.api.ClassTinkerers;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.megal.uselessadditions.EarlyRiser;
import net.megal.uselessadditions.item.DamageableItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.enchantment.SweepingEnchantment;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Enchantment.class)
public class DamagableItemsDontSupportMending {
    @ModifyReturnValue(at = @At("RETURN"),
            method = "isAcceptableItem(Lnet/minecraft/item/ItemStack;)Z")
    private boolean isDamageableItem(boolean b, ItemStack stack) {
        Enchantment enchantment = ((Enchantment)(Object)this);
        if (stack.getItem() instanceof DamageableItem && enchantment instanceof MendingEnchantment) {
            return false;
        }
        return b;
    }
}
