package net.megal.uselessadditions.block;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UCakeBlock extends BlockWithEntity {
    private static final List<StatusEffect> rainbowCakeEffects = List.of(
            StatusEffects.FIRE_RESISTANCE,
            StatusEffects.HUNGER,
            StatusEffects.POISON,
            StatusEffects.RESISTANCE,
            StatusEffects.MINING_FATIGUE,
            StatusEffects.HASTE,
            StatusEffects.BLINDNESS,
            StatusEffects.INVISIBILITY,
            StatusEffects.JUMP_BOOST,
            StatusEffects.LEVITATION,
            StatusEffects.NAUSEA,
            StatusEffects.NIGHT_VISION,
            StatusEffects.REGENERATION,
            StatusEffects.SLOW_FALLING,
            StatusEffects.SLOWNESS,
            StatusEffects.SPEED,
            StatusEffects.STRENGTH,
            StatusEffects.WATER_BREATHING,
            StatusEffects.WEAKNESS,
            StatusEffects.WITHER
    );
    public static final IntProperty BITES = CakeBlock.BITES;
    public static final VoxelShape[] BITES_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(3.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(5.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(7.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(9.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(11.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(13.0, 0.0, 1.0, 15.0, 8.0, 15.0)
    };

    public final FoodComponent foodComponent;
    public final boolean canHaveCandle;

    public UCakeBlock(Settings settings, FoodComponent foodComponent, boolean canHaveCandle) {
        super(settings);
        this.foodComponent = foodComponent;
        this.canHaveCandle = canHaveCandle;

        this.setDefaultState((this.stateManager.getDefaultState()).with(BITES, 0));
    }

    @Override
    public MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BITES_TO_SHAPE[state.get(BITES)];
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (canHaveCandle && itemStack.isIn(ItemTags.CANDLES) && state.get(BITES) == 0) {
            Block block = Block.getBlockFromItem(item);
            if (block instanceof CandleBlock) {
                if (!player.isCreative()) {
                    itemStack.decrement(1);
                }

                world.playSound(null, pos, SoundEvents.BLOCK_CAKE_ADD_CANDLE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.setBlockState(pos, CandleCakeBlock.getCandleCakeFromCandle(block));
                world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                return ActionResult.SUCCESS;
            }
        }

        if (world.isClient) {
            if (this.eat(world, pos, state, player).isAccepted()) {
                return ActionResult.SUCCESS;
            }

            if (itemStack.isEmpty()) {
                return ActionResult.CONSUME;
            }
        }

        return this.eat(world, pos, state, player);
    }

    private ActionResult eat(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canConsume(foodComponent.isAlwaysEdible())) {
            return ActionResult.PASS;
        } else {
            boolean tnt = state.isOf(UBlocks.EXPLOSIVE_CAKE);

            player.incrementStat(Stats.EAT_CAKE_SLICE);
            player.getHungerManager().add(foodComponent.getHunger(), foodComponent.getSaturationModifier());
            applyEffects(world, state, player);

            @Nullable BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity != null) {
                if (tnt && blockEntity instanceof ExplosiveCakeEntity explosiveCakeEntity) {
                    if (explosiveCakeEntity.ticksUntilExplosion == -1) {
                        world.playSoundAtBlockCenter(pos, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1, 1, true);
                        explosiveCakeEntity.ticksUntilExplosion = 80;
                    }
                    else if (explosiveCakeEntity.ticksUntilExplosion > 0) explosiveCakeEntity.ticksUntilExplosion = Math.max(explosiveCakeEntity.ticksUntilExplosion - 20, 0);
                }
            }

            int i = state.get(BITES);

            world.emitGameEvent(player, GameEvent.EAT, pos);
            if (i < 6) {
                world.setBlockState(pos, state.with(BITES, i + 1), Block.NOTIFY_ALL);
            }
            else {
                world.removeBlock(pos, false);
                world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }

            return ActionResult.SUCCESS;
        }
    }

    private void applyEffects(World world, BlockState state, PlayerEntity player) {
        Random random = world.random;
        List<Pair<StatusEffectInstance, Float>> list = foodComponent.getStatusEffects();

        if (!world.isClient) {
            if (state.isOf(UBlocks.RAINBOW_CAKE)) {
                List<StatusEffect> rainbowEffects = random.nextBetween(0, 3) == 0 ? rainbowCakeEffects.stream().filter(effect -> !effect.isBeneficial()).toList() : rainbowCakeEffects.stream().filter(effect -> effect.isBeneficial()).toList();

                if (!rainbowEffects.isEmpty()) player.addStatusEffect(new StatusEffectInstance(rainbowEffects.get(random.nextBetweenExclusive(0, rainbowEffects.size())), random.nextBetween(40, 160), 0));
            }

            for (Pair<StatusEffectInstance, Float> pair : list) {
                if (pair.getFirst() != null && random.nextFloat() < pair.getSecond()) {
                    player.addStatusEffect(new StatusEffectInstance(pair.getFirst()));
                }
            }
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isSolid();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BITES);
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return getComparatorOutput(state.get(BITES));
    }

    public static int getComparatorOutput(int bites) {
        return (7 - bites) * 2;
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (state.isOf(UBlocks.EXPLOSIVE_CAKE)) return validateTicker(type, UBlocks.EXPLOSIVE_CAKE_ENTITY, world.isClient ? ExplosiveCakeEntity::clientTick : ExplosiveCakeEntity::serverTick);
        return null;
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        if (state.isOf(UBlocks.EXPLOSIVE_CAKE)) return new ExplosiveCakeEntity(pos, state);
        return null;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
