package net.megal.uselessadditions.block;

import com.mojang.serialization.MapCodec;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.UItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SurvivalSpawner extends BlockWithEntity {
    protected SurvivalSpawner(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (world.getBlockEntity(pos) instanceof SurvivalSpawnerEntity blockEntity) {
            blockEntity.setEntityFromNbt(itemStack.getOrCreateNbt());
        }
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        BlockEntity blockEntity = builder.getOptional(LootContextParameters.BLOCK_ENTITY);
        ItemStack stackWithNbt = UBlocks.SURVIVAL_SPAWNER.asItem().getDefaultStack();
        if (blockEntity instanceof SurvivalSpawnerEntity survivalSpawnerEntity) {
            stackWithNbt.setNbt(survivalSpawnerEntity.getEntityAsNbt("EntityStored"));
        }
        List <ItemStack> stacks = super.getDroppedStacks(state, builder);
        stacks.add(stackWithNbt);
        return stacks;
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        if (world.getBlockEntity(pos) instanceof SurvivalSpawnerEntity blockEntity) {
            ItemStack stack = new ItemStack(this.asItem());
            stack.setNbt(blockEntity.getEntityAsNbt("EntityStored"));
            return stack;
        }
        return super.getPickStack(world, pos, state);
    }
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, @Nullable BlockState state) {
        return new SurvivalSpawnerEntity(pos, state);
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, UBlocks.SURVIVAL_SPAWNER_ENTITY, world.isClient ? SurvivalSpawnerEntity::clientTick : SurvivalSpawnerEntity::serverTick);
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}