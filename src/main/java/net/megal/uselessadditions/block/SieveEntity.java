package net.megal.uselessadditions.block;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.Mesh;
import net.megal.uselessadditions.item.MeshType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SieveEntity extends BlockEntity {
    public final int defaultUses = 30;
    @Nullable
    private Mesh mesh;
    @Nullable
    private Block block;
    private int uses;
    private boolean wasInteractedWith = false;

    public SieveEntity(BlockPos pos, BlockState state) {
        super(UBlocks.SIEVE_ENTITY, pos, state);
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, SieveEntity sieveEntity) {}

    public static void serverTick(World world, BlockPos pos, BlockState state, SieveEntity sieveEntity) {
        world.setBlockState(pos, state.with(Sieve.MESH, sieveEntity.mesh != null ? sieveEntity.mesh.meshType : MeshType.NONE));
        sieveEntity.wasInteractedWith = false;
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

    @Nullable
    public Mesh getMesh() {
        return mesh;
    }

    @Nullable
    public Block getBlock() {
        return block;
    }

    public int getUses() {
        return uses;
    }

    public void setMesh(@Nullable Mesh mesh2) {
        mesh = mesh2;
        updateListeners();
    }

    public void setBlock(Block block2) {
        block = block2;
        uses = mesh != null ? mesh.meshType.uses : defaultUses;
        updateListeners();
    }

    public void use(ServerWorld world, Vec3d pos) {
        if (!wasInteractedWith) {
            uses--;
            wasInteractedWith = true;
        }
        if (uses <= 0 && mesh != null) {
            for (BlockEntry entry : mesh.meshType.entries.keySet()) {
                if (!entry.testBlock(block)) continue;
                LootTable lootTable = world.getServer().getLootManager().getLootTable(mesh.meshType.entries.get(entry));
                LootContextParameterSet lootContextParameterSet = new LootContextParameterSet.Builder(world).build(LootContextTypes.EMPTY);
                ObjectArrayList<ItemStack> list = lootTable.generateLoot(lootContextParameterSet);
                for (ItemStack stack : list) {
                    ItemEntity itemEntity = new ItemEntity(world, pos.x + world.random.nextFloat(), pos.y, pos.z + world.random.nextFloat(), stack);
                    world.spawnEntity(itemEntity);
                }
                break;
            }
            block = null;
        }
        updateListeners();
    }

    private void updateListeners() {
        this.markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putBoolean("interacted", wasInteractedWith);
        if (mesh != null) {
            nbt.putString("mesh", Registries.ITEM.getId(mesh).toString());
        }
        nbt.putString("block", block != null ? Registries.BLOCK.getId(block).toString() : "empty");
        nbt.putInt("uses", uses);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        wasInteractedWith = nbt.getBoolean("interacted");
        if (nbt.contains("mesh")) {
            Item item = Registries.ITEM.get(new Identifier(nbt.getString("mesh")));
            if (item instanceof Mesh mesh2) mesh = mesh2;
            else UAdd.LOGGER.warn("Incorrect item set as mesh!");
        }
        if (nbt.contains("block")) {
            block = Objects.equals(nbt.getString("block"), "empty") ? null : Registries.BLOCK.get(new Identifier(nbt.getString("block")));
            uses = nbt.getInt("uses");
        }
    }
}