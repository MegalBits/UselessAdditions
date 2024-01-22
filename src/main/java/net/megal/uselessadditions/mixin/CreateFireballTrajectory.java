package net.megal.uselessadditions.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.UAddClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ExplosiveProjectileEntity.class)
public abstract class CreateFireballTrajectory {
    @Inject(at = @At("TAIL"),
            method = "<init>(Lnet/minecraft/entity/EntityType;DDDDDDLnet/minecraft/world/World;)V")
    private void addTrajectory(EntityType<? extends ExplosiveProjectileEntity> type, double x, double y, double z, double directionX, double directionY, double directionZ, World world, CallbackInfo ci) {
        ExplosiveProjectileEntity projectile = ((ExplosiveProjectileEntity)(Object)this);
        UAddClient.addTrajectory(projectile);
    }
}
