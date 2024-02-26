package net.megal.uselessadditions.block;

import net.minecraft.block.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class SlimePile extends SlimeBlock {
    protected static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2.0,0.0,2.0,10.0,3.0,10.0),
            Block.createCuboidShape(5.0,0.0,4.0,14.0,5.0,14.0)
    );

    public SlimePile(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Vec3d vec3d = state.getModelOffset(world, pos);
        return SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
    }
}
