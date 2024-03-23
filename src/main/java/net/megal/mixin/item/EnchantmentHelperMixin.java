package net.megal.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.megal.UAdd;
import net.megal.item.ScytheItem;
import net.megal.item.modifier.Modifiers;
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
public abstract class EnchantmentHelperMixin {
    @Inject(
            at = @At("HEAD"),
            method = "onTargetDamaged"
    )
    private static void targetDamaged(LivingEntity user, Entity target, CallbackInfo ci) {
        if (user == null) return;

        ItemStack stack = user.getMainHandStack();
        List<String> modifiers = Modifiers.getModifiersFromStack(stack);

        if (modifiers.contains(Modifiers.AUTO_SMELTING)) {
            target.setFireTicks(60);
        }
    }

    @ModifyReturnValue(
            at = @At("RETURN"),
            method = "getSweepingMultiplier"
    )
    private static float scytheSweepingIncrease(float f, LivingEntity entity) {
        ItemStack stack = entity.getMainHandStack();
        if (stack.getItem() instanceof ScytheItem || Modifiers.getModifiersFromStack(stack).contains(Modifiers.IMPROVED_SWEEPING)) {
            return f + .25f;
        }
        return f;
    }
}
