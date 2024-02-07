package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.base.ScytheItem;
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

import static net.minecraft.enchantment.EnchantmentHelper.getEquipmentLevel;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperHooks {
    @Inject(at = @At("HEAD"),
            method = "onTargetDamaged(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/Entity;)V")
    private static void targetDamaged(LivingEntity user, Entity target, CallbackInfo ci) {
        if (user == null) return;
        ItemStack stack = user.getMainHandStack();
        List<String> specialEffects = UItemHelper.getEffects(stack);

        if (specialEffects.contains(SpecialEffects.AUTO_SMELT)) {
            target.setFireTicks(60);
        }
    }

    @ModifyReturnValue(at = @At("RETURN"),
            method = "getKnockback(Lnet/minecraft/entity/LivingEntity;)I")
    private static int knockbackForHooking(int i, LivingEntity entity) {
        return i += getEquipmentLevel(UEnchantments.HOOKING, entity);
    }

    @ModifyReturnValue(at = @At("RETURN"),
            method = "getSweepingMultiplier(Lnet/minecraft/entity/LivingEntity;)F")
    private static float scytheSweepingIncrease(float f, LivingEntity entity) {
        ItemStack stack = entity.getMainHandStack();
        if (stack.getItem() instanceof ScytheItem || UItemHelper.getEffects(stack).contains(SpecialEffects.IMPROVED_SWEEPING)) {
            return f + .25f;
        }
        return f;
    }
}
