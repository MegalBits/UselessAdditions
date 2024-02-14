package net.megal.uselessadditions.block;

import com.mojang.serialization.MapCodec;
import net.megal.uselessadditions.UAdd;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PipeEngine extends BlockWithEntity implements Waterloggable {
    public static final EnumProperty<Direction> DIRECTION = EnumProperty.of("direction", Direction.class);

    protected PipeEngine(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DIRECTION, Properties.WATERLOGGED);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(DIRECTION, ctx.getPlayerLookDirection().getOpposite())
                .with(Properties.WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(Properties.WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(DIRECTION)) {
            case UP, DOWN -> VoxelShapes.union(
                    Block.createCuboidShape(5.0,1.0, 5.0,11.0,15.0, 11.0),
                    Block.createCuboidShape(6.0,0.0, 6.0,10.0,1.0, 10.0),
                    Block.createCuboidShape(6.0,15.0, 6.0,10.0,16.0, 10.0)
            );
            case NORTH, SOUTH -> VoxelShapes.union(
                    Block.createCuboidShape(5.0,5.0, 1.0,11.0,11.0, 15.0),
                    Block.createCuboidShape(6.0,6.0, 0.0,10.0,10.0, 1.0),
                    Block.createCuboidShape(6.0,6.0, 15.0,10.0,10.0, 16.0)
            );
            case EAST, WEST -> VoxelShapes.union(
                    Block.createCuboidShape(1.0,5.0, 5.0,15.0,11.0, 11.0),
                    Block.createCuboidShape(0.0,6.0, 6.0,1.0,10.0, 10.0),
                    Block.createCuboidShape(15.0,6.0, 6.0,16.0,10.0, 10.0)
            );
        };
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(Properties.WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
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

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        PipeEntity pipeEntity = new PipeEntity(pos, state);
        pipeEntity.setItemsPerSecond(8);
        return pipeEntity;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, UBlocks.PIPE_ENTITY, PipeEntity::engineCommonTick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
