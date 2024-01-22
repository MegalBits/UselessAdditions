package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.enchantment.UEnchantment;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(PlayerEntity.class)
public abstract class ReverseHookingVelocity {
    @ModifyArgs(at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;takeKnockback(DDD)V"),
            method = "attack(Lnet/minecraft/entity/Entity;)V")
    private void invertKnockback(Args args, @Local(ordinal = 2) float cooldownProgress) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        if (EnchantmentHelper.getEquipmentLevel(UEnchantments.HOOKING, player) > 0 && cooldownProgress >= .2f) {
            double x = args.get(1);
            double z = args.get(2);
            args.set(1, -x * cooldownProgress / 2);
            args.set(2, -z * cooldownProgress / 2);
        }
    }
}
