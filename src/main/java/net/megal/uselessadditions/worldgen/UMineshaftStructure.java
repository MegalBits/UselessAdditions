package net.megal.uselessadditions.worldgen;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.structure.MineshaftGenerator;
import net.minecraft.structure.StructurePiecesCollector;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.structure.MineshaftStructure;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

public class UMineshaftStructure extends Structure {
    public static final Codec<UMineshaftStructure> CODEC = RecordCodecBuilder.create((instance) -> instance.group(configCodecBuilder(instance)).apply(instance, UMineshaftStructure::new));

    protected UMineshaftStructure(Config config) {
        super(config);
    }

    @Override
    public Optional<StructurePosition> getStructurePosition(Structure.Context context) {
        context.random().nextDouble();
        ChunkPos chunkPos = context.chunkPos();
        BlockPos blockPos = new BlockPos(chunkPos.getCenterX(), 50, chunkPos.getStartZ());
        StructurePiecesCollector structurePiecesCollector = new StructurePiecesCollector();
        int i = this.addPieces(structurePiecesCollector, context);
        return Optional.of(new Structure.StructurePosition(blockPos.add(0, i, 0), Either.right(structurePiecesCollector)));
    }

    private int addPieces(StructurePiecesCollector collector, Structure.Context context) {
        ChunkPos chunkPos = context.chunkPos();
        ChunkRandom chunkRandom = context.random();
        ChunkGenerator chunkGenerator = context.chunkGenerator();

        Direction direction = switch (chunkRandom.nextBetween(0, 3)) {
            default -> Direction.NORTH;
            case 1 -> Direction.EAST;
            case 2 -> Direction.SOUTH;
            case 3 -> Direction.WEST;
        };

        UMineshaftGenerator.Start start = new UMineshaftGenerator.Start(0, chunkRandom, chunkPos.getOffsetX(4), chunkPos.getOffsetZ(4), direction);
        collector.addPiece(start);
        start.fillOpenings(start, collector, chunkRandom);

        return collector.shiftInto(chunkGenerator.getSeaLevel() - 20, chunkGenerator.getMinimumY() + 3, chunkRandom, 10);
    }

    @Override
    public StructureType<?> getType() {
        return UStructures.MINESHAFT;
    }
}
