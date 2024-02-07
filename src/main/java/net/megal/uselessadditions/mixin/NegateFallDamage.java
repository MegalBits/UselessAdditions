package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.effect.UStatusEffects;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class NegateFallDamage {
    @ModifyVariable(at = @At("HEAD"),
            method = "handleFallDamage",
            ordinal = 1, argsOnly = true)
    public float onEntityLand(float value) {
        LivingEntity livingEntity = (LivingEntity)(Object)this;
        if (!livingEntity.bypassesLandingEffects() && livingEntity.hasStatusEffect(UStatusEffects.SLIMED)) {
            return 0f;
        }
        return value;
    }
}
