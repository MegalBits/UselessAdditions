package net.megal.uselessadditions.block;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.UItems;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShulkerBoxBlock;
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
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SurvivalSpawner extends BlockWithEntity {
    public static int EMERALD_TIME = 300;
    public static Item EMERALD = Items.EMERALD;
    public static int DIAMOND_TIME = 36000;
    public static Item DIAMOND = Items.DIAMOND;
    public static int NETHERITE_TIME = 216000;
    public static Item NETHERITE = Items.NETHERITE_INGOT;
    protected SurvivalSpawner(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        boolean wasItemUsed = false;
        if (world.getBlockEntity(pos) instanceof SurvivalSpawnerEntity blockEntity) {
            Item handItem = player.getStackInHand(hand).getItem();
            wasItemUsed = itemUsedToRecharge(handItem, blockEntity, world, pos);
        }
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        if (wasItemUsed && !player.isCreative()) player.getStackInHand(hand).decrement(1);
        return ActionResult.CONSUME;
    }
    private boolean itemUsedToRecharge(Item item, SurvivalSpawnerEntity blockEntity ,World world, BlockPos pos) {
        if (item == EMERALD) {
            blockEntity.setTimeRemaining(blockEntity.getTimeRemaining() + EMERALD_TIME, 8, world, pos);
            return true;
        }
        if (item == DIAMOND) {
            blockEntity.setTimeRemaining(blockEntity.getTimeRemaining() + DIAMOND_TIME, 12, world, pos);
            return true;
        }
        if (item == NETHERITE) {
            blockEntity.setTimeRemaining(blockEntity.getTimeRemaining() + NETHERITE_TIME, 16, world, pos);
            return true;
        }
        return false;
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