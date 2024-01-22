package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.item.base.ScytheItem;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageEnchantment.class)
public abstract class DamageEnchantmentExtra {
    @Inject(at = @At("HEAD"),
            method = "isAcceptableItem(Lnet/minecraft/item/ItemStack;)Z",
            cancellable = true)
    private void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof ScytheItem) cir.setReturnValue(true);
    }
}
