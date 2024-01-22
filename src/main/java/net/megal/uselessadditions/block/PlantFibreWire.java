package net.megal.uselessadditions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TripwireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlantFibreWire extends TripwireBlock {
    public PlantFibreWire(Block hookBlock, Settings settings) {
        super(hookBlock, settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);

        world.breakBlock(pos, true);
    }
}
