package net.megal.uselessadditions.block;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.Mesh;
import net.megal.uselessadditions.item.MeshType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ExplosiveCakeEntity extends BlockEntity {
    public int ticksUntilExplosion = -1;
    private boolean hasExploded = false;

    public ExplosiveCakeEntity(BlockPos pos, BlockState state) {
        super(UBlocks.EXPLOSIVE_CAKE_ENTITY, pos, state);
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, ExplosiveCakeEntity explosiveCakeEntity) {}

    public static void serverTick(World world, BlockPos pos, BlockState state, ExplosiveCakeEntity explosiveCakeEntity) {
        if (explosiveCakeEntity.ticksUntilExplosion > 0) explosiveCakeEntity.ticksUntilExplosion -= 1;
        if (explosiveCakeEntity.ticksUntilExplosion == 0 && !explosiveCakeEntity.hasExploded) {
            explosiveCakeEntity.hasExploded = true;
            world.createExplosion(null, Explosion.createDamageSource(world, null), null, pos.getX() + 0.5d, pos.getY() + 0.25d, pos.getZ() + 0.5d, 3, false, World.ExplosionSourceType.BLOCK);
        }
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    private void updateListeners() {
        this.markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("explodeTicks", ticksUntilExplosion);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        ticksUntilExplosion = nbt.getInt("explodeTicks");
    }
}
