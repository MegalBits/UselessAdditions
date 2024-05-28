package net.megal.network;

import net.megal.UAdd;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class SyncSecondaryStackPayload implements CustomPayload {
    public static final Id<SyncSecondaryStackPayload> ID = new Id<>(new Identifier(UAdd.ID, "snyc_secondary_stack"));
    public static final PacketCodec<RegistryByteBuf, SyncSecondaryStackPayload> CODEC = CustomPayload.codecOf(SyncSecondaryStackPayload::write, SyncSecondaryStackPayload::new);
    private final int entity;
    private final ItemStack stack;

    public SyncSecondaryStackPayload(int entity, ItemStack stack) {
        this.stack = stack;
        this.entity = entity;
    }

    public SyncSecondaryStackPayload(RegistryByteBuf buf) {
        this(buf.readInt(), ItemStack.PACKET_CODEC.decode(buf));
    }

    public void write(RegistryByteBuf buf) {
        buf.writeInt(entity);
        ItemStack.PACKET_CODEC.encode(buf, stack);
    }

    public ItemStack getStack() {
        return stack;
    }

    public int getEntity() {
        return entity;
    }

    @Override
    public Id<SyncSecondaryStackPayload> getId() {
        return ID;
    }
}
