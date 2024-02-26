package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.megal.uselessadditions.effect.UStatusEffects;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public class ApplyJumpEffects {
    @ModifyReturnValue(at = @At("RETURN"),
            method = "getJumpVelocity"
    )
    private float cancelJump(float f) {
        LivingEntity entity = ((LivingEntity)(Object)this);
        if (entity.hasStatusEffect(UStatusEffects.STUNNED)) f *= 0.05f;

        return f;
    }
}
