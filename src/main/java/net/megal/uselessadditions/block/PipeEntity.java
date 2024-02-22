package net.megal.uselessadditions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PipeEntity extends BlockEntity {
    private static final int maxPower = 8;
    public List<StoredItemStack> storedStacks = new ArrayList<>();
    public List<StoredItemStack> stacksToRemove = new ArrayList<>();
    private float itemsPerSecond = 1;
    private int cooldown = 0;
    public int power = 0;
    public boolean pulling = false;
    public PipeEntity(BlockPos pos, BlockState state) {
        super(UBlocks.PIPE_ENTITY, pos, state);
    }

    public void setItemsPerSecond(float value) {
        itemsPerSecond = value;
    }

    public List<ItemStack> getItems() {
        List<ItemStack> stacks = new ArrayList<>();
        for(StoredItemStack storedStack : storedStacks) {
            stacks.add(storedStack.stack);
        }
        return stacks;
    }

    public static void engineCommonTick(World world, BlockPos pos, BlockState state, PipeEntity pipeEntity) {
        pipeEntity.power = maxPower;
        Direction direction = state.get(PipeEngine.DIRECTION);
        BlockEntity front = world.getBlockEntity(pos.offset(direction));
        BlockEntity back = world.getBlockEntity(pos.offset(direction.getOpposite()));
        if (front instanceof PipeEntity frontPipe) frontPipe.pulling = false;
        if (back instanceof PipeEntity backPipe) backPipe.pulling = true;

        commonTick(world, pos, state, pipeEntity);
    }

    public static void pipeCommonTick(World world, BlockPos pos, BlockState state, PipeEntity pipeEntity) {
        int newPower = 0;
        boolean isPulling = false;
        for (Direction dir : Direction.values()) {
            BlockEntity entity = world.getBlockEntity(pos.offset(dir));
            if (entity == null) continue;

            if (entity instanceof PipeEntity nextPipeEntity && nextPipeEntity.power > newPower) {
                BlockState nextState = entity.getCachedState();

                newPower = nextPipeEntity.power - 1;
                if (nextPipeEntity.pulling || (nextState.getBlock() instanceof PipeEngine && dir == nextState.get(PipeEngine.DIRECTION))) isPulling = true;
            }

            takeFromInventory(world, pos, state, pipeEntity, entity, dir);
        }
        pipeEntity.power = newPower;
        pipeEntity.pulling = isPulling;

        commonTick(world, pos, state, pipeEntity);
    }

    private static void takeFromInventory(World world, BlockPos pos, BlockState state, PipeEntity pipeEntity, BlockEntity entity, Direction dir) {
        boolean wasItemTaken = false;

        if (pipeEntity.power > 0 && pipeEntity.pulling && pipeEntity.cooldown <= 0 && pipeEntity.storedStacks.size() < 10 && entity instanceof Inventory inventory) {
            if (inventory instanceof SidedInventory sidedInventory) {
                int[] slots = sidedInventory.getAvailableSlots(dir.getOpposite());
                for (int slot : slots) {
                    ItemStack stack = sidedInventory.getStack(slot);
                    if (stack.isEmpty() || !sidedInventory.canExtract(slot, stack, dir.getOpposite())) continue;

                    pipeEntity.addItem(world.getRandom(), state, stack, dir);
                    stack.decrement(1);

                    wasItemTaken = true;
                    break;
                }
            }
            else {
                int size = inventory.size();

                for(int i = 0; i < size; ++i) {
                    ItemStack stack = inventory.getStack(i);
                    if (stack.isEmpty()) continue;

                    pipeEntity.addItem(world.getRandom(), state, stack, dir);
                    stack.decrement(1);

                    wasItemTaken = true;
                    break;
                }
            }
        }

        if (wasItemTaken) pipeEntity.updateListeners();
    }

    public static void commonTick(World world, BlockPos pos, BlockState state, PipeEntity pipeEntity) {
        for (StoredItemStack storedStack : pipeEntity.storedStacks) {
            if (pipeEntity.power > 0) {
                if (storedStack.direction != null) storedStack.tickStack();
                if (storedStack.direction == null || storedStack.time <= 10) storedStack.direction = getItemDirection(world, pos, state, pipeEntity, storedStack.prevDirection, storedStack.stack);

                if (!storedStack.shouldMove() || storedStack.direction == null) continue;

                BlockEntity entity = world.getBlockEntity(pos.offset(storedStack.direction));
                if (entity instanceof PipeEntity pipeEntity2 && pipeEntity2.power > 0 && pipeEntity2.cooldown <= 0 && pipeEntity2.storedStacks.size() < 10) {
                    pipeEntity2.addItem(storedStack);
                    pipeEntity.stacksToRemove.add(storedStack);
                }
                else if (entity instanceof Inventory inventory && !pipeEntity.pulling && (storedStack.prevDirection == null || storedStack.direction != storedStack.prevDirection.getOpposite())) {
                    if (inventory instanceof SidedInventory sidedInventory) {
                        int[] slots = sidedInventory.getAvailableSlots(storedStack.direction.getOpposite());
                        for (int slot : slots) {
                            ItemStack stack2 = sidedInventory.getStack(slot);

                            if ((stack2.isEmpty() || canMergeItems(storedStack.stack, stack2)) && sidedInventory.canInsert(slot, storedStack.stack, storedStack.direction.getOpposite())) {
                                ItemStack newStack = storedStack.stack;
                                newStack.increment(stack2.getCount());
                                sidedInventory.setStack(slot, newStack);
                                pipeEntity.stacksToRemove.add(storedStack);
                                break;
                            }
                        }

                    }
                    else {
                        int size = inventory.size();
                        for(int i = 0; i < size; ++i) {
                            ItemStack stack2 = inventory.getStack(i);

                            if (stack2.isEmpty() || canMergeItems(storedStack.stack, stack2)) {
                                ItemStack newStack = storedStack.stack;
                                newStack.increment(stack2.getCount());
                                inventory.setStack(i, newStack);
                                pipeEntity.stacksToRemove.add(storedStack);
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (pipeEntity.cooldown > 0) pipeEntity.cooldown--;
        if (!pipeEntity.stacksToRemove.isEmpty()) {
            pipeEntity.storedStacks.removeAll(pipeEntity.stacksToRemove);
            pipeEntity.stacksToRemove.clear();

            pipeEntity.updateListeners();
        }
    }

    public void addItem(StoredItemStack previousStack) {
        storedStacks.add(new StoredItemStack(previousStack.stack, this.itemsPerSecond, StoredItemStack.posFromDirection(previousStack.direction), previousStack.rotation, null, previousStack.direction));
        cooldown = (int)(20 / itemsPerSecond);
    }

    public void addItem(Random random, BlockState state, ItemStack stack, Direction direction) {
        ItemStack stack2 = stack.copy().split(1);
        storedStacks.add(new PipeEntity.StoredItemStack(stack2, this.itemsPerSecond, PipeEntity.StoredItemStack.posFromDirection(direction.getOpposite()), random.nextInt(360), getItemDirection(world, pos, state, this, null, stack2), null));
        cooldown = (int)(20 / itemsPerSecond);
    }

    @Nullable
    public static Direction getItemDirection(World world, BlockPos pos, BlockState state, PipeEntity pipeEntity, Direction prevDirection, @Nullable ItemStack stack) {
        if (state.getBlock() instanceof PipeEngine) return state.get(PipeEngine.DIRECTION);

        @Nullable
        Direction newDirection = null;
        int selectedPower = 0;

        List<Direction> availablePipeDirections = new ArrayList<>();
        List<Direction> availableChestDirections = new ArrayList<>();

        for (Direction dir : Direction.values()) {
            BlockEntity entity = world.getBlockEntity(pos.offset(dir));
            if (prevDirection != null && dir == prevDirection.getOpposite()) continue;

            if (entity instanceof PipeEntity nextPipeEntity) {
                BlockState nextState = nextPipeEntity.getCachedState();
                if (nextState.getBlock() instanceof PipeEngine) {
                    if (nextState.get(PipeEngine.DIRECTION) == dir) {
                        if (nextPipeEntity.power > selectedPower) availablePipeDirections.clear();

                        selectedPower = nextPipeEntity.power;
                        availablePipeDirections.add(dir);
                    }

                    continue;
                }

                if (nextPipeEntity.pulling && nextPipeEntity.power >= selectedPower) {
                    if (nextPipeEntity.power > selectedPower) availablePipeDirections.clear();

                    selectedPower = nextPipeEntity.power;
                    availablePipeDirections.add(dir);
                    continue;
                }

                if (!pipeEntity.pulling && nextPipeEntity.power > 0 && maxPower - nextPipeEntity.power >= selectedPower) {
                    if (maxPower - nextPipeEntity.power > selectedPower) availablePipeDirections.clear();

                    selectedPower = maxPower - nextPipeEntity.power;
                    availablePipeDirections.add(dir);
                    continue;
                }
            }

            if (!pipeEntity.pulling && entity instanceof Inventory inventory && stack != null) {
                if (inventory instanceof SidedInventory sidedInventory) {
                    int[] slots = sidedInventory.getAvailableSlots(dir.getOpposite());
                    for (int slot : slots) {
                        ItemStack stack2 = sidedInventory.getStack(slot);

                        if ((stack2.isEmpty() || canMergeItems(stack, stack2)) && sidedInventory.canInsert(slot, stack, dir.getOpposite())) {
                            availableChestDirections.add(dir);
                        }
                    }
                    continue;
                }

                int size = inventory.size();
                for(int i = 0; i < size; ++i) {
                    ItemStack stack2 = inventory.getStack(i);

                    if (stack2.isEmpty() || canMergeItems(stack, stack2)) {
                        availableChestDirections.add(dir);
                    }
                }
                continue;
            }
        }

        if (!availableChestDirections.isEmpty()) newDirection = availableChestDirections.get(world.random.nextInt(availableChestDirections.size()));
        else if (!availablePipeDirections.isEmpty()) newDirection = availablePipeDirections.get(world.random.nextInt(availablePipeDirections.size()));

        return newDirection;
    }

    private static boolean canMergeItems(ItemStack stack1, ItemStack stack2) {
        return stack1.getCount() < stack1.getMaxCount() && stack2.getCount() < stack2.getMaxCount() && ItemStack.canCombine(stack1, stack2);
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
        nbt.putFloat("speed", itemsPerSecond);
        nbt.putInt("cooldown", cooldown);
        nbt.putInt("power", power);
        nbt.putBoolean("pulling", pulling);

        NbtList list = new NbtList();
        for (StoredItemStack storedStack : storedStacks) {
            NbtCompound storedStackCompound = createStoredItemNbt(storedStack);
            storedStackCompound.put("stack", storedStack.stack.writeNbt(new NbtCompound()));
            list.add(storedStackCompound);
        }

        nbt.put("items", list);
    }

    private static NbtCompound createStoredItemNbt(StoredItemStack storedStack) {
        NbtCompound storedStackCompound = new NbtCompound();
        storedStackCompound.putFloat("time", storedStack.getTime());
        storedStackCompound.putDouble("x", storedStack.initialPos.getX());
        storedStackCompound.putDouble("y", storedStack.initialPos.getY());
        storedStackCompound.putDouble("z", storedStack.initialPos.getZ());
        storedStackCompound.putInt("rotation", storedStack.rotation);
        if (storedStack.direction != null) storedStackCompound.putInt("direction", storedStack.direction.getId());
        if (storedStack.prevDirection != null) storedStackCompound.putInt("prevDirection", storedStack.prevDirection.getId());
        return storedStackCompound;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        itemsPerSecond = nbt.getFloat("speed");
        cooldown = nbt.getInt("cooldown");
        power = nbt.getInt("power");
        pulling = nbt.getBoolean("pulling");

        storedStacks = new ArrayList<>();
        nbt.getList("items", NbtElement.COMPOUND_TYPE).forEach(e -> {
            if (e instanceof NbtCompound compound) {
                Vec3d initialPos = new Vec3d(compound.getDouble("x"), compound.getDouble("y"), compound.getDouble("z"));

                StoredItemStack storedStack = new StoredItemStack(ItemStack.fromNbt(compound.getCompound("stack")), itemsPerSecond, initialPos, compound.getInt("rotation"), compound.contains("direction") ? Direction.byId(compound.getInt("direction")) : null, compound.contains("prevDirection") ? Direction.byId(compound.getInt("prevDirection")) : null);
                storedStack.overrideTime(compound.getFloat("time"));

                storedStacks.add(storedStack);
            }
        });
    }

    public static class StoredItemStack {
        public final ItemStack stack;
        private final float itemsPerSecond;
        public final int rotation;
        public final Vec3d initialPos;
        @Nullable
        public final Direction prevDirection;
        @Nullable
        public Direction direction;
        private float time = 0;

        public StoredItemStack(ItemStack stack, float itemsPerSecond, Vec3d pos, int rotation, @Nullable Direction direction, @Nullable Direction prevDirection) {
            this.stack = stack;
            this.itemsPerSecond = itemsPerSecond;
            this.initialPos = pos;
            this.rotation = rotation;
            this.direction = direction;
            this.prevDirection = prevDirection;
        }

        private static Vec3d posFromDirection(Direction direction) {
            return Vec3d.ZERO.offset(direction, 1);
        }

        public void overrideTime(float value) {
            time = value;
        }

        public void tickStack() {
            if (time < 20) time = Math.min(time + itemsPerSecond, 20);
        }

        public boolean shouldMove() {
            return time >= 20;
        }

        public float getTime() {
            return time;
        }
    }
}
