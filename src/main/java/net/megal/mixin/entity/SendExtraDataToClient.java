package net.megal.mixin.entity;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.megal.UAdd;
import net.megal.entity.RangedMob;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.EntityTrackerEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityTrackerEntry.class)
public abstract class SendExtraDataToClient {
    @Shadow @Final private Entity entity;

    @Inject(
            at = @At("TAIL"),
            method = "startTracking"
    )
    private void sendData(ServerPlayerEntity player, CallbackInfo ci) {
        if (entity instanceof PersistentProjectileEntity projectile) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeItemStack(projectile.stack);
            buf.writeInt(projectile.getId());

            ServerPlayNetworking.send(player, new Identifier(UAdd.ID, "sync_projectile_item"), buf);
        }
        if (entity instanceof AbstractSkeletonEntity abstractSkeleton) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeString(Registries.ITEM.getId(((RangedMob)abstractSkeleton).UAdd$getProjectile()).toString());
            buf.writeInt(abstractSkeleton.getId());

            ServerPlayNetworking.send(player, new Identifier(UAdd.ID, "sync_mob_projectile"), buf);
        }
    }
}
