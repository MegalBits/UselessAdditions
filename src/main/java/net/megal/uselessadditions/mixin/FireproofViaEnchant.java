package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.enchantment.AugmentEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(ItemEntity.class)
public abstract class FireproofViaEnchant {
    @Inject(at = @At("HEAD"),
            method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z",
            cancellable = true)
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        ItemEntity itemEntity = ((ItemEntity) (Object) this);
        ItemStack stack = itemEntity.getStack();
        if (source.isIn(DamageTypeTags.IS_FIRE) && stack.hasEnchantments()) {
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
            for (Enchantment ench : enchantments.keySet()) {
                if (ench instanceof AugmentEnchantment aug && aug.isFireproof()) cir.setReturnValue(true);
            }
        }
    }
    @Inject(at = @At("HEAD"),
            method = "isFireImmune()Z",
            cancellable = true)
    private void isFireImmune(CallbackInfoReturnable<Boolean> cir) {
        ItemEntity itemEntity = ((ItemEntity) (Object) this);
        ItemStack stack = itemEntity.getStack();
        if (stack.hasEnchantments()) {
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
            for (Enchantment ench : enchantments.keySet()) {
                if (ench instanceof AugmentEnchantment aug && aug.isFireproof()) cir.setReturnValue(true);
            }
        }
    }
}