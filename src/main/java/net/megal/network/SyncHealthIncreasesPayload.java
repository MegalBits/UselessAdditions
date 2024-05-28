package net.megal.network;

import net.megal.UAdd;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class SyncHealthIncreasesPayload implements CustomPayload {
    public static final Id<SyncHealthIncreasesPayload> ID = new Id<>(new Identifier(UAdd.ID, "snyc_health_increases"));
    public static final PacketCodec<PacketByteBuf, SyncHealthIncreasesPayload> CODEC = CustomPayload.codecOf(SyncHealthIncreasesPayload::write, SyncHealthIncreasesPayload::new);
    private final HashMap<String, Integer> healthIncreases;

    public SyncHealthIncreasesPayload(HashMap<String, Integer> healthIncreases) {
        this.healthIncreases = healthIncreases;
    }

    public SyncHealthIncreasesPayload(PacketByteBuf buf) {
        this((HashMap<String, Integer>) buf.readMap(PacketByteBuf::readString, PacketByteBuf::readInt));
    }

    public void write(PacketByteBuf buf) {
        buf.writeMap(healthIncreases, PacketByteBuf::writeString, PacketByteBuf::writeInt);
    }

    public HashMap<String, Integer> getHealthIncreases() {
        return healthIncreases;
    }

    @Override
    public Id<SyncHealthIncreasesPayload> getId() {
        return ID;
    }
}
