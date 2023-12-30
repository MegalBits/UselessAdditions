package net.megal.uselessadditions.block;

import net.megal.uselessadditions.UAdd;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.*;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Nameable;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.collection.Weighted;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Function;

public class SurvivalSpawnerEntity extends BlockEntity {
    private Entity renderedEntity;
    private EntityType<?> entity;
    private int timeRemaining = 0;
    private int spawnDelay = 2;
    private int minSpawnDelay = 400;
    private int maxSpawnDelay = 1200;
    private int spawnCount = 4;
    private double rotation;
    private double lastRotation;
    private int requiredPlayerRange = 16;
    private int spawnRange = 4;
    public SurvivalSpawnerEntity(BlockPos pos, BlockState state) {
        super(UBlocks.SURVIVAL_SPAWNER_ENTITY, pos, state);
    }
    private boolean isPlayerInRange(World world, BlockPos pos) {
        return world.isPlayerInRange((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, (double)this.requiredPlayerRange);
    }
    public void setTimeRemaining(int ticks) {
        timeRemaining = ticks;
    }
    public void setTimeRemaining(int ticks, int particleCount, World world, BlockPos pos) {
        timeRemaining = ticks;
        if (world.isClient()) {
            Random random = world.getRandom();
            for (int i = 0; i < Math.max(8,particleCount); i++) {
                world.addParticle(ParticleTypes.END_ROD, pos.getX()+.5d, pos.getY()+.5d, pos.getZ()+.5d, (random.nextDouble() - random.nextDouble())/3d, (random.nextDouble() - random.nextDouble())/3d, (random.nextDouble() - random.nextDouble())/3d);
            }
        }
    }
    public int getTimeRemaining() {
        return timeRemaining;
    }
    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbt = new NbtCompound();
        if (entity != null) nbt.putString("EntityStored", getIdFromEntity(entity));
        else UAdd.LOGGER.warn("Entity was null in spawner");
        nbt.putInt("TimeActive", timeRemaining);
        return nbt;
    }
    public static void clientTick(World world, BlockPos pos, BlockState state, SurvivalSpawnerEntity blockEntity) {
        blockEntity.clientTick(world, pos);
    }

    public void clientTick(World world, BlockPos pos) {
        if (!isPlayerInRange(world, pos)) {
            lastRotation = rotation;
        } else if (entity != null) {
            Random random = world.getRandom();
            double d = (double)pos.getX() + random.nextDouble();
            double e = (double)pos.getY() + random.nextDouble();
            double f = (double)pos.getZ() + random.nextDouble();
            //world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
            if (timeRemaining > 0) world.addParticle(ParticleTypes.INSTANT_EFFECT, d, e, f, 0.0, 0.0, 0.0);
            else world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
            if (timeRemaining > 0) {
                timeRemaining--;
                if (spawnDelay > 0) {
                    spawnDelay--;
                }
            }
            lastRotation = rotation;
            rotation = (rotation + (double)((1000.0F / ((float)spawnDelay + 200.0F)) / (timeRemaining > 0 ? 1 : 20))) % 360.0;
        }
    }
    public static void serverTick(World world, BlockPos pos, BlockState state, SurvivalSpawnerEntity blockEntity) {
        blockEntity.serverTick((ServerWorld) world, pos);
    }
    public void serverTick(ServerWorld world, BlockPos pos) {
        if (timeRemaining > 0) {
            timeRemaining--;
            if (spawnDelay > 0) {
                spawnDelay--;
            }
            else {
                Random random = world.getRandom();
                if (entity != null && entity != EntityType.PLAYER) {
                    for (int i = 0; i < spawnCount; ++i) {
                        double sX = (double) pos.getX() + (random.nextDouble() - random.nextDouble()) * (double) spawnRange + 0.5;
                        double sY = pos.getY() + random.nextInt(3) - 1;
                        double sZ = (double) pos.getZ() + (random.nextDouble() - random.nextDouble()) * (double) spawnRange + 0.5;
                        if (!world.isSpaceEmpty(entity.createSimpleBoundingBox(sX, sY, sZ))) continue;
                        BlockPos blockPos = BlockPos.ofFloored(sX, sY, sZ);
                        if (!entity.getSpawnGroup().isPeaceful() && world.getDifficulty() == Difficulty.PEACEFUL || !SpawnRestriction.canSpawn(entity, world, SpawnReason.SPAWNER, blockPos, random))
                            continue;
                        Entity spawnedEntity = EntityType.loadEntityWithPassengers(getEntityAsNbt("id"), world, entity -> {
                            entity.refreshPositionAndAngles(sX, sY, sZ, entity.getYaw(), entity.getPitch());
                            return entity;
                        });
                        world.spawnEntity(spawnedEntity);
                        if (spawnedEntity instanceof MobEntity mob) {
                            mob.playSpawnEffects();
                        }
                    }
                }
                spawnDelay = world.random.nextBetween(minSpawnDelay, maxSpawnDelay);
            }
        }
    }
    @Nullable
    public Entity getRenderedEntity(World world, Random random, BlockPos pos) {
        if (this.renderedEntity == null) {
            NbtCompound nbtCompound = getEntityAsNbt("id");
            if (!nbtCompound.contains("id")) {
                return null;
            }
            this.renderedEntity = EntityType.loadEntityWithPassengers(nbtCompound, world, Function.identity());
            //if (nbtCompound.getSize() == 1 && this.renderedEntity instanceof MobEntity) {}
        }

        return this.renderedEntity;
    }
    public double getRotation() {
        return this.rotation;
    }
    public double getLastRotation() {
        return this.lastRotation;
    }
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        setEntityFromNbt(nbt);
        setTimeRemaining(nbt.getInt("TimeActive"));
    }
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putString("EntityStored", getIdFromEntity(entity));
        nbt.putInt("TimeActive", timeRemaining);
    }
    public void setEntityFromNbt(NbtCompound nbt) {
        if (nbt.contains("EntityStored")) {
            Registries.ENTITY_TYPE.getOrEmpty(new Identifier(nbt.getString("EntityStored"))).ifPresentOrElse(
                    e -> entity = e,
                    () -> entity = EntityType.ZOMBIE
            );
        }
        markDirty();
    }
    public NbtCompound getEntityAsNbt(String key) {
        NbtCompound nbt = new NbtCompound();
        nbt.putString(key, getIdFromEntity(entity));
        return nbt;
    }
    private String getIdFromEntity(EntityType<?> entity) {
        return Registries.ENTITY_TYPE.getId(entity).toString();
    }
}
