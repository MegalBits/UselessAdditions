package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.megal.uselessadditions.UAdd;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerEntity.class)
public abstract class CapSweepingDamage {
    @ModifyVariable(at = @At("STORE"),
            method = "attack(Lnet/minecraft/entity/Entity;)V",
            ordinal = 4)
    private float limitDamageMultiplier(float f, Entity entity, @Local(ordinal = 0) float damage) {
        return Math.min(f, damage);
    }
}
