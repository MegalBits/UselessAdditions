package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.megal.uselessadditions.effect.UStatusEffects;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HungerManager.class)
public abstract class BleedingStopsHealing {
    @WrapWithCondition(at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/entity/player/HungerManager;addExhaustion(F)V"
            ),
            method = "update")
    private boolean stopConsumption(HungerManager hungerManager, float f, @Local PlayerEntity player) {
        return !player.hasStatusEffect(UStatusEffects.BLEEDING);
    }

    @WrapWithCondition(at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/entity/player/PlayerEntity;heal(F)V"
            ),
            method = "update")
    private boolean stopConsumption(PlayerEntity player, float f) {
        return !player.hasStatusEffect(UStatusEffects.BLEEDING);
    }
}
