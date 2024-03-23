package net.megal.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Objects;

public class USpawnEgg extends Item {
    private final EntityType<?> type;

    public USpawnEgg(Settings settings, EntityType<? extends MobEntity> type) {
        super(settings);
        this.type = type;
    }

    public EntityType<?> getEntityType() {
        return type;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (!(world instanceof ServerWorld)) {
            return ActionResult.SUCCESS;
        }
        ItemStack itemStack = context.getStack();
        BlockPos blockPos = context.getBlockPos();
        Direction direction = context.getSide();
        BlockState blockState = world.getBlockState(blockPos);
        BlockPos blockPos2 = blockState.getCollisionShape(world, blockPos).isEmpty() ? blockPos : blockPos.offset(direction);
        if (type.spawnFromItemStack((ServerWorld)world, itemStack, context.getPlayer(), blockPos2, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockPos, blockPos2) && direction == Direction.UP) != null) {
            itemStack.decrement(1);
            world.emitGameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockPos);
        }
        return ActionResult.CONSUME;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult hitResult = SpawnEggItem.raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
        if (((HitResult)hitResult).getType() != HitResult.Type.BLOCK) {
            return TypedActionResult.pass(itemStack);
        }
        if (!(world instanceof ServerWorld)) {
            return TypedActionResult.success(itemStack);
        }
        BlockPos blockPos = hitResult.getBlockPos();
        if (!(world.getBlockState(blockPos).getBlock() instanceof FluidBlock)) {
            return TypedActionResult.pass(itemStack);
        }
        if (!world.canPlayerModifyAt(user, blockPos) || !user.canPlaceOn(blockPos, hitResult.getSide(), itemStack)) {
            return TypedActionResult.fail(itemStack);
        }
        Entity entity = type.spawnFromItemStack((ServerWorld)world, itemStack, user, blockPos, SpawnReason.SPAWN_EGG, false, false);
        if (entity == null) {
            return TypedActionResult.pass(itemStack);
        }
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        world.emitGameEvent(user, GameEvent.ENTITY_PLACE, entity.getPos());
        return TypedActionResult.consume(itemStack);
    }
}