package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.enchantment.AutoSmeltEnchantment;
import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperHooks {
    @Inject(at = @At("HEAD"),
            method = "onTargetDamaged(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/Entity/entity;)V")
    private static void targetDamaged(LivingEntity user, Entity target, CallbackInfo ci) {
        ItemStack stack = user.getMainHandStack();
        List<String> specialEffects = UItemHelper.getEffects(stack);

        if (specialEffects.contains(SpecialEffects.AUTO_SMELT)) {
            target.setFireTicks(60);
        }
    }
}
