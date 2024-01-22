package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.effect.UStatusEffects;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class ApplyDragonsCurse {
    @Inject(at = @At("HEAD"),
            method = "tick()V")
    private void applyCurse(CallbackInfo ci) {
        PlayerEntity player = ((PlayerEntity)(Object)this);
        World world = player.getWorld();

        if (world instanceof ServerWorld serverWorld) {
            EnderDragonFight enderDragonFight = serverWorld.getEnderDragonFight();
            Vec3d pos = player.getPos();
            if (enderDragonFight != null && !enderDragonFight.hasPreviouslyKilled() && world.getRegistryKey() == World.END && (pos.x > 500 || pos.x < -500 || pos.z > 500 || pos.z < -500)) {
                player.addStatusEffect(new StatusEffectInstance(UStatusEffects.DRAGONS_CURSE, 6000, 0, true, true));
            }
            else {
                player.removeStatusEffect(UStatusEffects.DRAGONS_CURSE);
            }
        }

    }
}
