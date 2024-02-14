package net.megal.uselessadditions.block;

import com.mojang.serialization.MapCodec;
import net.megal.uselessadditions.UAdd;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Pipe extends ConnectingBlockWithEntity {
    public final float itemsPerSecond;

    public Pipe(Settings settings, float itemsPerSecond) {
        super(0.125f, settings);
        this.itemsPerSecond = itemsPerSecond;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return withConnectionProperties(ctx.getWorld(), ctx.getBlockPos(), this.getDefaultState());
    }

    public static BlockState withConnectionProperties(BlockView world, BlockPos pos, BlockState state) {
        BlockState stateD = world.getBlockState(pos.down());
        BlockState stateU = world.getBlockState(pos.up());
        BlockState stateN = world.getBlockState(pos.north());
        BlockState stateE = world.getBlockState(pos.east());
        BlockState stateS = world.getBlockState(pos.south());
        BlockState stateW = world.getBlockState(pos.west());

        @Nullable BlockEntity entityD = world.getBlockEntity(pos.down());
        @Nullable BlockEntity entityU = world.getBlockEntity(pos.up());
        @Nullable BlockEntity entityN = world.getBlockEntity(pos.north());
        @Nullable BlockEntity entityE = world.getBlockEntity(pos.east());
        @Nullable BlockEntity entityS = world.getBlockEntity(pos.south());
        @Nullable BlockEntity entityW = world.getBlockEntity(pos.west());

        return state.withIfExists(DOWN, stateD.getBlock() instanceof Pipe || (stateD.getBlock() instanceof PipeEngine && (stateD.get(PipeEngine.DIRECTION) == Direction.DOWN || stateD.get(PipeEngine.DIRECTION) == Direction.UP)) || entityD instanceof Inventory)
                .withIfExists(UP, stateU.getBlock() instanceof Pipe || (stateU.getBlock() instanceof PipeEngine && (stateU.get(PipeEngine.DIRECTION) == Direction.DOWN || stateU.get(PipeEngine.DIRECTION) == Direction.UP)) || entityU instanceof Inventory)
                .withIfExists(NORTH, stateN.getBlock() instanceof Pipe || (stateN.getBlock() instanceof PipeEngine && (stateN.get(PipeEngine.DIRECTION) == Direction.NORTH || stateN.get(PipeEngine.DIRECTION) == Direction.SOUTH)) || entityN instanceof Inventory)
                .withIfExists(EAST, stateE.getBlock() instanceof Pipe || (stateE.getBlock() instanceof PipeEngine && (stateE.get(PipeEngine.DIRECTION) == Direction.EAST || stateE.get(PipeEngine.DIRECTION) == Direction.WEST)) || entityE instanceof Inventory)
                .withIfExists(SOUTH, stateS.getBlock() instanceof Pipe || (stateS.getBlock() instanceof PipeEngine && (stateS.get(PipeEngine.DIRECTION) == Direction.NORTH || stateS.get(PipeEngine.DIRECTION) == Direction.SOUTH)) || entityS instanceof Inventory)
                .withIfExists(WEST, stateW.getBlock() instanceof Pipe || (stateW.getBlock() instanceof PipeEngine && (stateW.get(PipeEngine.DIRECTION) == Direction.EAST || stateW.get(PipeEngine.DIRECTION) == Direction.WEST)) || entityW instanceof Inventory);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            world.scheduleBlockTick(pos, this, 1);
            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        } else {
            boolean bl = neighborState.isOf(this) || neighborState.getBlock() instanceof Pipe || world.getBlockEntity(neighborPos) instanceof Inventory;

            boolean bl2 = neighborState.getBlock() instanceof PipeEngine && switch (direction) {
                case UP, DOWN -> neighborState.get(PipeEngine.DIRECTION) == Direction.UP || neighborState.get(PipeEngine.DIRECTION) == Direction.DOWN;
                case NORTH, SOUTH -> neighborState.get(PipeEngine.DIRECTION) == Direction.NORTH || neighborState.get(PipeEngine.DIRECTION) == Direction.SOUTH;
                case EAST, WEST -> neighborState.get(PipeEngine.DIRECTION) == Direction.EAST || neighborState.get(PipeEngine.DIRECTION) == Direction.WEST;
            };

            return state.with(FACING_PROPERTIES.get(direction), bl || bl2);
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PipeEntity pipeEntity) {
                for (ItemStack stack : pipeEntity.getItems()) {
                    ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                }
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        PipeEntity pipeEntity = new PipeEntity(pos, state);
        pipeEntity.setItemsPerSecond(itemsPerSecond);
        return pipeEntity;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, UBlocks.PIPE_ENTITY, PipeEntity::pipeCommonTick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
