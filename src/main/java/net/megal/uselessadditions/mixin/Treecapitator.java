package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class Treecapitator {
    @Unique
    private static final int maxCount = 128;
    @Inject(at = @At("TAIL"),
            method = "onBreak")
    private void breakTree(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfoReturnable<BlockState> cir) {
        ItemStack stack = player.getMainHandStack();
        if (UItemHelper.getEffects(stack).contains(SpecialEffects.TREE_FELLING)) {
            int i = tryApplyBreak(world, player, pos, state, stack, 0);
            stack.damage(i / 2, player, item -> {
                item.sendToolBreakStatus(player.getActiveHand());
            });
        }
    }

    @Unique
    private static int tryApplyBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, ItemStack stack, int i) {
        if (i > maxCount) return i;
        if (state.getBlock() instanceof PillarBlock && stack.isSuitableFor(state)) {
            i = applyBreak(world, player, pos.north(), state, stack, i);
            i = applyBreak(world, player, pos.east(), state, stack, i);
            i = applyBreak(world, player, pos.south(), state, stack, i);
            i = applyBreak(world, player, pos.west(), state, stack, i);
            i = applyBreak(world, player, pos.up(), state, stack, i);
            i = applyBreak(world, player, pos.down(), state, stack, i);

            i = applyBreak(world, player, pos.up().north(), state, stack, i);
            i = applyBreak(world, player, pos.up().east(), state, stack, i);
            i = applyBreak(world, player, pos.up().south(), state, stack, i);
            i = applyBreak(world, player, pos.up().west(), state, stack, i);

            i = applyBreak(world, player, pos.down().north(), state, stack, i);
            i = applyBreak(world, player, pos.down().east(), state, stack, i);
            i = applyBreak(world, player, pos.down().south(), state, stack, i);
            i = applyBreak(world, player, pos.down().west(), state, stack, i);

            i = applyBreak(world, player, pos.north().east(), state, stack, i);
            i = applyBreak(world, player, pos.north().west(), state, stack, i);
            i = applyBreak(world, player, pos.south().east(), state, stack, i);
            i = applyBreak(world, player, pos.south().west(), state, stack, i);

            i = applyBreak(world, player, pos.up().north().east(), state, stack, i);
            i = applyBreak(world, player, pos.up().north().west(), state, stack, i);
            i = applyBreak(world, player, pos.up().south().east(), state, stack, i);
            i = applyBreak(world, player, pos.up().south().west(), state, stack, i);

            i = applyBreak(world, player, pos.down().north().east(), state, stack, i);
            i = applyBreak(world, player, pos.down().north().west(), state, stack, i);
            i = applyBreak(world, player, pos.down().south().east(), state, stack, i);
            i = applyBreak(world, player, pos.down().south().west(), state, stack, i);
        }
        return i;
    }

    @Unique
    private static int applyBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, ItemStack stack, int i) {
        BlockState state2 = world.getBlockState(pos);
        if (state2.getBlock() != state.getBlock() || stack.getDamage() + (i / 2f) >= stack.getMaxDamage() || i > maxCount) return i;
        if (!world.isClient()) {
            world.removeBlock(pos, false);
            BlockEntity entity = state2.hasBlockEntity() ? world.getBlockEntity(pos) : null;
            Block.dropStacks(state2, world, pos, entity, player, stack);
        }
        return tryApplyBreak(world, player, pos, state2, stack, i + 1);
    }
}
