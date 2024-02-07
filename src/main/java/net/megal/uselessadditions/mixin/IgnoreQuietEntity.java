package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.effect.UStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.Vibrations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Vibrations.VibrationListener.class)
public abstract class IgnoreQuietEntity {
    @Inject(at = @At("HEAD"),
            method = "listen(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/world/event/GameEvent;Lnet/minecraft/world/event/GameEvent$Emitter;Lnet/minecraft/util/math/Vec3d;)Z",
            cancellable = true)
    private void ignoreEntity(ServerWorld world, GameEvent event, GameEvent.Emitter emitter, Vec3d emitterPos, CallbackInfoReturnable<Boolean> cir) {
        if (emitter.sourceEntity() != null && emitter.sourceEntity() instanceof LivingEntity livingEntity && livingEntity.hasStatusEffect(UStatusEffects.MUTED)) {
            cir.setReturnValue(false);
        }
    }
}
