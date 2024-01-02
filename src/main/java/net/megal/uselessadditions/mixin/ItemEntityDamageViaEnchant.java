package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.enchantment.CactusLiningEnchantment;
import net.megal.uselessadditions.enchantment.UEnchantment;
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
public abstract class ItemEntityDamageViaEnchant {
    @Inject(at = @At("HEAD"),
            method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z",
            cancellable = true)
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        ItemEntity itemEntity = ((ItemEntity) (Object) this);
        ItemStack stack = itemEntity.getStack();
        if (stack.hasEnchantments()) {
            boolean fireProof = false;
            boolean noCactus = false;
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
            for (Enchantment ench : enchantments.keySet()) {
                if (ench instanceof UEnchantment uEnch) {
                    if (uEnch.isFireproof()) fireProof = true;
                    if (uEnch instanceof CactusLiningEnchantment) noCactus = true;
                }
            }
            if (source.isIn(DamageTypeTags.IS_FIRE) && fireProof) {
                cir.setReturnValue(true);
            }
            if (!itemEntity.getWorld().isClient() && source == itemEntity.getWorld().getDamageSources().cactus() && noCactus) {
                cir.setReturnValue(true);
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
                if (ench instanceof UEnchantment uEnch && uEnch.isFireproof()) cir.setReturnValue(true);
            }
        }
    }
}