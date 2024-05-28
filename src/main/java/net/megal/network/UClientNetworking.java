package net.megal.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.megal.entity.HasSecondaryStack;
import net.megal.player.PlayerNbt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;

@Environment(EnvType.CLIENT)
public class UClientNetworking {
    public static void registerReceivers() {
        ClientPlayNetworking.registerGlobalReceiver(SyncSaturationPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                context.client().player.getHungerManager().setSaturationLevel(payload.getSaturation());
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(SyncExhaustionPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                context.client().player.getHungerManager().setExhaustion(payload.getExhaustion());
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(SyncSecondaryStackPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                Entity entity = context.client().world.getEntityById(payload.getEntity());
                if (!(entity instanceof HasSecondaryStack hasSecondaryStack)) return;

                hasSecondaryStack.setSecondaryStack(payload.getStack());
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(SyncHealthIncreasesPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                ((PlayerNbt)context.client().player).UAdd$setHealthIncreases(payload.getHealthIncreases());
            });
        });
    }
}
