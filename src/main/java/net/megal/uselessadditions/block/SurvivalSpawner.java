package net.megal.uselessadditions.block;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SurvivalSpawner extends BlockWithEntity {
    protected SurvivalSpawner(Settings settings) {
        super(settings);
    }
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (world.getBlockEntity(pos) instanceof SurvivalSpawnerEntity blockEntity) {
            blockEntity.setEntityFromNbt(itemStack.getOrCreateNbt());
        }
    }
    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        if (world.getBlockEntity(pos) instanceof SurvivalSpawnerEntity blockEntity) {
            ItemStack stack = new ItemStack(this.asItem());
            stack.setNbt(blockEntity.getEntityAsNbt("EntityStored"));
            return stack;
        }
        return super.getPickStack(world, pos, state);
    }
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, @Nullable BlockState state) {
        //SurvivalSpawnerEntity blockEntity = new SurvivalSpawnerEntity(pos, state);
        return new SurvivalSpawnerEntity(pos, state);
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, UBlocks.SURVIVAL_SPAWNER_ENTITY, world.isClient ? SurvivalSpawnerEntity::clientTick : SurvivalSpawnerEntity::serverTick);
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
