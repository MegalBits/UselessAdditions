package net.megal.network;

import net.megal.UAdd;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class SyncSaturationPayload implements CustomPayload {
    public static final Id<SyncSaturationPayload> ID = new Id<>(new Identifier(UAdd.ID, "snyc_saturation"));
    public static final PacketCodec<PacketByteBuf, SyncSaturationPayload> CODEC = CustomPayload.codecOf(SyncSaturationPayload::write, SyncSaturationPayload::new);
    private final float saturation;

    public SyncSaturationPayload(float saturation) {
        this.saturation = saturation;
    }

    public SyncSaturationPayload(PacketByteBuf buf) {
        this(buf.readFloat());
    }

    public void write(PacketByteBuf buf) {
        buf.writeFloat(saturation);
    }

    public float getSaturation() {
        return saturation;
    }

    @Override
    public Id<SyncSaturationPayload> getId() {
        return ID;
    }
}
