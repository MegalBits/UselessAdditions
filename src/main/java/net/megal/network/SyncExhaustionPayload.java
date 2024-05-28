package net.megal.network;

import net.megal.UAdd;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class SyncExhaustionPayload implements CustomPayload {
    public static final Id<SyncExhaustionPayload> ID = new Id<>(new Identifier(UAdd.ID, "snyc_exhaustion"));
    public static final PacketCodec<PacketByteBuf, SyncExhaustionPayload> CODEC = CustomPayload.codecOf(SyncExhaustionPayload::write, SyncExhaustionPayload::new);
    private final float exhaustion;

    public SyncExhaustionPayload(float saturation) {
        this.exhaustion = saturation;
    }

    public SyncExhaustionPayload(PacketByteBuf buf) {
        this(buf.readFloat());
    }

    public void write(PacketByteBuf buf) {
        buf.writeFloat(exhaustion);
    }

    public float getExhaustion() {
        return exhaustion;
    }

    @Override
    public Id<SyncExhaustionPayload> getId() {
        return ID;
    }
}
