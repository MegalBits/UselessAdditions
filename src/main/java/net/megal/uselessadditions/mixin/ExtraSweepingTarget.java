package net.megal.uselessadditions.mixin;

import com.chocohead.mm.api.ClassTinkerers;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.megal.uselessadditions.EarlyRiser;
import net.megal.uselessadditions.item.base.ScytheItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.SweepingEnchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SweepingEnchantment.class)
public class ExtraSweepingTarget {
    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;<init>(Lnet/minecraft/enchantment/Enchantment$Rarity;Lnet/minecraft/enchantment/EnchantmentTarget;[Lnet/minecraft/entity/EquipmentSlot;)V"),
            method = "<init>(Lnet/minecraft/enchantment/Enchantment$Rarity;[Lnet/minecraft/entity/EquipmentSlot;)V")
    private static EnchantmentTarget changeTarget(EnchantmentTarget target) {
        return ClassTinkerers.getEnum(EnchantmentTarget.class, EarlyRiser.SWEEPING);
    }
}
