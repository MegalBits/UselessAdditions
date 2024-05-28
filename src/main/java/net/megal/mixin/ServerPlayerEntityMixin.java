package net.megal.mixin;

import com.mojang.authlib.GameProfile;
import net.megal.network.UNetworking;
import net.megal.player.PlayerNbt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(
            at = @At("HEAD"),
            method = "copyFrom"
    )
    private void copyExtraData(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
        ((PlayerNbt)player).UAdd$setHealthIncreases(((PlayerNbt)oldPlayer).UAdd$getHealthIncreases());
        UNetworking.syncHealthIncreases(oldPlayer);
    }

    @Inject(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V",
                    ordinal = 5,
                    shift = At.Shift.BEFORE
            ),
            method = "moveToWorld"
    )
    private void copyExtraDataWhenMovingWorld(ServerWorld destination, CallbackInfoReturnable<Entity> cir) {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
        UNetworking.syncHealthIncreases(player);
    }

    @Inject(
            at = @At("HEAD"),
            method = "tick"
    )
    private void sendHungerValuesToClient(CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
        UNetworking.syncHungerValues(player);
    }
}
