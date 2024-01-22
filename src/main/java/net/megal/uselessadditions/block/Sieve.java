package net.megal.uselessadditions.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.Mesh;
import net.megal.uselessadditions.item.MeshType;
import net.megal.uselessadditions.item.UItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Function;

public class Sieve extends BlockWithEntity {
    public static final EnumProperty<MeshType> MESH = EnumProperty.of("mesh", MeshType.class);
    protected static final VoxelShape EMPTY = VoxelShapes.union(
            //Corners
            Block.createCuboidShape(0.0,0.0,0.0,2.0,9.0,2.0),
            Block.createCuboidShape(0.0,0.0,14.0,2.0,9.0,16.0),
            Block.createCuboidShape(14.0,0.0,0.0,16.0,9.0,2.0),
            Block.createCuboidShape(14.0,0.0,14.0,16.0,9.0,16.0),
            //Sides
            Block.createCuboidShape(0.0,7.0,2.0,2.0,9.0,14.0),
            Block.createCuboidShape(14.0,7.0,2.0,16.0,9.0,14.0),
            Block.createCuboidShape(2.0,7.0,0.0,14.0,9.0,2.0),
            Block.createCuboidShape(2.0,7.0,14.0,14.0,9.0,16.0)
    );
    protected static final VoxelShape WITH_MESH = VoxelShapes.union(
            //Corners and sides
            EMPTY,
            //Mesh
            Block.createCuboidShape(2.0,7.9,2.0,14.0,8.0,14.0)
    );

    protected Sieve(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(MESH, MeshType.NONE));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MESH);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (world.getBlockEntity(pos) instanceof SieveEntity sieveEntity && sieveEntity.getMesh() != null) return WITH_MESH;
        return EMPTY;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof SieveEntity sieveEntity) {
            if (world.isClient) {
                if (sieveEntity.getMesh() != null) return ActionResult.SUCCESS;
                return ActionResult.PASS;
            }
            if (sieveEntity.getBlock() == null) {
                if (sieveEntity.getMesh() != null) {
                    if (stack.getItem() instanceof BlockItem blockItem && sieveEntity.getMesh().meshType.canAccept(blockItem)) {
                        sieveEntity.setBlock(blockItem.getBlock());
                        if (!player.getAbilities().creativeMode) stack.decrement(1);
                        return ActionResult.SUCCESS;
                    }
                    if ((stack.isEmpty() || (stack.equals(sieveEntity.getMesh().getDefaultStack()) && stack.getCount() < stack.getMaxCount())) && player.isSneaking()) {
                        if (stack.isEmpty()) player.setStackInHand(hand, sieveEntity.getMesh().getDefaultStack());
                        else stack.increment(1);
                        sieveEntity.setMesh(null);
                        world.setBlockState(pos, state.with(Sieve.MESH, MeshType.NONE));
                        return ActionResult.SUCCESS;
                    }
                }
                else if (stack.getItem() instanceof Mesh mesh && sieveEntity.getMesh() == null) {
                    sieveEntity.setMesh(mesh);
                    if (!player.getAbilities().creativeMode) stack.decrement(1);
                    return ActionResult.SUCCESS;
                }
            }
            else if (sieveEntity.getUses() > 0) {
                sieveEntity.use((ServerWorld) world, new Vec3d(pos.getX(), pos.getY()+.7f, pos.getZ()));
                return ActionResult.SUCCESS;
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        List<ItemStack> drops = super.getDroppedStacks(state, builder);

        BlockEntity blockEntity = builder.getOptional(LootContextParameters.BLOCK_ENTITY);
        if (blockEntity instanceof SieveEntity sieveEntity && sieveEntity.getMesh() != null) {
            drops.add(sieveEntity.getMesh().getDefaultStack());
        }
        return drops;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, UBlocks.SIEVE_ENTITY, world.isClient ? SieveEntity::clientTick : SieveEntity::serverTick);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SieveEntity(pos, state);
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
