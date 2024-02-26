package net.megal.uselessadditions.worldgen;

import com.mojang.serialization.Codec;
import net.megal.uselessadditions.UAdd;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.ChunkRandom;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;
import java.util.List;

public class SlimePileFeature extends Feature<SlimePileConfig> {
    public SlimePileFeature(Codec configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext context) {
        StructureWorldAccess world = context.getWorld();

        BlockPos origin = context.getOrigin();

        Random random = context.getRandom();
        SlimePileConfig config = (SlimePileConfig) context.getConfig();

        BlockPos pos = new BlockPos(origin);
        BlockState blockState = Registries.BLOCK.get(config.blockId()).getDefaultState();

        ChunkPos chunkPos = new ChunkPos(pos);
        boolean bl = ChunkRandom.getSlimeRandom(chunkPos.x, chunkPos.z, world.getSeed(), 987234911L).nextInt(10) == 0;
        if (!(random.nextInt(10) == 0 && bl && pos.getY() < 40)) {
            return false;
        }

        List<Integer> validHeights = new ArrayList<>();
        for (int y = 0; y < 40; y++) {
            pos = pos.up();
            if (world.getBlockState(pos).isIn(BlockTags.BASE_STONE_OVERWORLD) && world.getBlockState(pos.up()).isOf(Blocks.AIR)) {
                validHeights.add(pos.getY());
            }
        }

        if (!validHeights.isEmpty()) {
            world.setBlockState(pos.withY(validHeights.get(random.nextInt(validHeights.size())) + 1), blockState, Block.FORCE_STATE);
            return true;
        }
        return false;

    }
}
