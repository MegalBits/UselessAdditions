package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.block.UBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FarmlandBlock.class)
public abstract class FarmlandOverhaul {
    private static boolean isFluidNearby(WorldView world, BlockPos pos, int range, TagKey<Fluid> fluidType) {
        for (BlockPos blockPos : BlockPos.iterate(pos.add(-range, 0, -range), pos.add(range, 0, range))) {
            if (!world.getFluidState(blockPos).isIn(fluidType)) continue;
            return true;
        }
        return false;
    }
    private static boolean isBlockNearby(WorldView world, BlockPos pos, int range, int yOffset, Block block) {
        for (BlockPos blockPos : BlockPos.iterate(pos.add(-range, yOffset, -range), pos.add(range, yOffset, range))) {
            if (!world.getBlockState(blockPos).isOf(block)) continue;
            return true;
        }
        return false;
    }
    private static boolean hasCrop(BlockView world, BlockPos pos) {
        return world.getBlockState(pos.up()).isIn(BlockTags.MAINTAINS_FARMLAND);
    }

    @Shadow
    public static IntProperty MOISTURE;

    @Overwrite
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i = state.get(MOISTURE);
        boolean sprinkler = isBlockNearby(world, pos, 4, 1, UBlocks.SPRINKLER);
        boolean water = isFluidNearby(world, pos, 2, FluidTags.WATER);
        boolean rain = world.hasRain(pos.up());
        boolean wet = sprinkler || water || rain;

        boolean lava = isFluidNearby(world, pos, 6, FluidTags.LAVA);
        boolean crop = hasCrop(world, pos);
        if (wet && !lava) {
            if (i < 7 && Random.create().nextBoolean()) {
                world.setBlockState(pos, (BlockState)state.with(MOISTURE, Math.min(i + 3, 7)), Block.NOTIFY_LISTENERS);
            }
        } else if (i > 0) {
            int ii = 1;
            if (lava) ii++;
            world.setBlockState(pos, (BlockState)state.with(MOISTURE, Math.max(i - ii, 0)), Block.NOTIFY_LISTENERS);
        } else if (!crop || Random.create().nextBetween(0,20) == 0 || lava) {
            FarmlandBlock.setToDirt(null, state, world, pos);
        }
    }
}
