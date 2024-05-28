package net.megal.network;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.megal.UAdd;
import net.megal.entity.HasSecondaryStack;
import net.megal.player.PlayerNbt;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.HashMap;
import java.util.UUID;

public class UNetworking {
    public static void registerPayloads() {
        PayloadTypeRegistry.playS2C().register(SyncSaturationPayload.ID, SyncSaturationPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(SyncExhaustionPayload.ID, SyncExhaustionPayload.CODEC);

        PayloadTypeRegistry.playS2C().register(SyncSecondaryStackPayload.ID, SyncSecondaryStackPayload.CODEC);

        PayloadTypeRegistry.playS2C().register(SyncHealthIncreasesPayload.ID, SyncHealthIncreasesPayload.CODEC);
    }

    public static void registerEvents() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, serverWorld) -> {
            if (entity instanceof MobEntity && entity instanceof HasSecondaryStack) {
                UNetworking.syncSecondaryStack((MobEntity & HasSecondaryStack) entity);
            }
        });
    }

    private static final HashMap<UUID, Float> prevSaturationValues = new HashMap<>();
    private static final HashMap<UUID, Float> prevExhaustionValues = new HashMap<>();
    public static void syncHungerValues(ServerPlayerEntity player) {
        UUID uuid = player.getUuid();
        Float prevSaturation = prevSaturationValues.get(uuid);
        float saturation = player.getHungerManager().getSaturationLevel();

        Float prevExhaustion = prevExhaustionValues.get(uuid);
        float exhaustion = player.getHungerManager().getExhaustion();

        if (prevSaturation == null || prevSaturation != saturation) {
            ServerPlayNetworking.send(player, new SyncSaturationPayload(saturation));
            prevSaturationValues.put(uuid, saturation);
        }

        if (prevExhaustion == null || prevExhaustion != exhaustion) {
            ServerPlayNetworking.send(player, new SyncExhaustionPayload(exhaustion));
            prevExhaustionValues.put(uuid, exhaustion);
        }
    }

    public static <T extends MobEntity & HasSecondaryStack> void syncSecondaryStack(T entity) {
        for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld) entity.getWorld())) {
            ServerPlayNetworking.send(player, new SyncSecondaryStackPayload(entity.getId(), entity.getSecondaryStack()));
        }
    }

    public static void syncHealthIncreases(ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new SyncHealthIncreasesPayload(((PlayerNbt)player).UAdd$getHealthIncreases()));
    }
}
