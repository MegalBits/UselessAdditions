package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
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
public abstract class Harvesting {
    //Todo: Make harvesting, vein mine and tree-capitator better

    @Inject(at = @At("TAIL"),
            method = "onBreak")
    private void harvestCrops(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfoReturnable<BlockState> cir) {
        ItemStack stack = player.getMainHandStack();
        if (UItemHelper.getEffects(stack).contains(SpecialEffects.HARVESTING)) {
            int i = tryApplyBreak(world, player, pos, state, stack, 0);
            stack.damage(i / 2, player, item -> {
                item.sendToolBreakStatus(player.getActiveHand());
            });
        }
    }

    @Unique
    private static int tryApplyBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, ItemStack stack, int i) {
        if (state.getBlock() instanceof CropBlock) {
            i = applyBreak(world, player, pos.north(), state, stack, i);
            i = applyBreak(world, player, pos.east(), state, stack, i);
            i = applyBreak(world, player, pos.south(), state, stack, i);
            i = applyBreak(world, player, pos.west(), state, stack, i);

            i = applyBreak(world, player, pos.north().east(), state, stack, i);
            i = applyBreak(world, player, pos.north().west(), state, stack, i);
            i = applyBreak(world, player, pos.south().east(), state, stack, i);
            i = applyBreak(world, player, pos.south().west(), state, stack, i);
        }
        return i;
    }

    @Unique
    private static int applyBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, ItemStack stack, int i) {
        BlockState state2 = world.getBlockState(pos);

        if (state2.getBlock() != state.getBlock() || (state2.get(CropBlock.AGE) != state.get(CropBlock.AGE) && state.get(CropBlock.AGE) == ((CropBlock) state.getBlock()).getMaxAge()) || stack.getDamage() + (i / 2f) >= stack.getMaxDamage()) return i;
        if (!world.isClient()) {
            world.removeBlock(pos, false);
            BlockEntity entity = state2.hasBlockEntity() ? world.getBlockEntity(pos) : null;
            Block.dropStacks(state2, world, pos, entity, player, stack);
        }
        return i + 1;
    }
}
