package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.enchantment.UEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(ItemStack.class)
public abstract class ExtendedDurability {

    @Inject(at = @At("HEAD"),
            method = "getMaxDamage()I",
            cancellable = true)
    private void getMaxDamage(CallbackInfoReturnable<Integer> cir) {
        ItemStack stack = ((ItemStack)(Object)this);
        int f = 0;
        if (stack.hasEnchantments()) {
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
            for (Enchantment ench : enchantments.keySet()) {
                int level = enchantments.get(ench);
                if (ench instanceof UEnchantment uEnch && uEnch.getDurability(level) != 0) {
                    f += uEnch.getDurability(level);
                }
            }
        }
        if (f > 0) cir.setReturnValue(Math.max(stack.getItem().getMaxDamage() + f, 1));
    }

    /*
    @Inject(at = @At("HEAD"),
            method = "getItemBarColor()I",
            cancellable = true)
    private void getItemBarColor(CallbackInfoReturnable<Integer> cir) {
        ItemStack stack = ((ItemStack)(Object)this);
        int damage = stack.getDamage();
        int maxDamage = stack.getMaxDamage();
        int itemMaxDamage = stack.getItem().getMaxDamage();
        if (maxDamage > itemMaxDamage && damage < maxDamage - itemMaxDamage) {
            cir.setReturnValue(0xa63bcc);
        }
    }
     */
}