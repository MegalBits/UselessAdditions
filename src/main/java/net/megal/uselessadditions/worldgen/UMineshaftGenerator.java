package net.megal.uselessadditions.worldgen;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.block.*;
import net.minecraft.block.enums.RailShape;
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.entity.vehicle.ChestMinecartEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.structure.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.BuiltinBiomes;
import net.minecraft.world.biome.source.BiomeSources;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.block.Blocks.*;

public class UMineshaftGenerator {
    @Nullable
    private static Piece pickPiece(Piece prev, StructurePiecesHolder holder, Random random, int x, int y, int z, Direction orientation, int level, int chainLength) {
        BlockBox blockBox;
        int i = random.nextInt(100);

        if (prev instanceof Crossing || prev instanceof Stairs) {
            blockBox = Connector.getBoundingBox(holder, random, x, y, z, orientation);
            if (blockBox != null) {
                return new Connector(chainLength, random, blockBox, orientation, level, false, false);
            }
        }
        else if (prev instanceof Connector connector && connector.hasTurn) {
            blockBox = Crossing.getBoundingBox(holder, random, x, y, z, orientation);
            if (blockBox != null) {
                boolean bl = random.nextBoolean();
                boolean left = random.nextBoolean() || bl;
                boolean right = random.nextBoolean() || !bl;
                boolean forward = random.nextBoolean();

                boolean railsEnd = left && forward && right;

                return new Crossing(chainLength, random, blockBox, orientation, level, left, forward, right, railsEnd);
            }
        }
        else if (prev instanceof Connector connector && connector.hasStairs) {
            int newLevel = 0;
            if (level == 0) newLevel += random.nextBoolean() ? 1 : -1;

            blockBox = Stairs.getBoundingBox(holder, random, x, y, z, orientation, newLevel < level);
            if (blockBox != null) {
                return new Stairs(chainLength, random, blockBox, orientation, newLevel, level);
            }
        }

        if (i < 65) {
            blockBox = Tunnel.getBoundingBox(holder, random, x, y, z, orientation);
            if (blockBox != null) {
                return new Tunnel(chainLength, random, blockBox, orientation, level);
            }
        }
        if (i < 95) {
            blockBox = Connector.getBoundingBox(holder, random, x, y, z, orientation);
            if (blockBox != null) {
                boolean bl = random.nextFloat() < .7f ;
                return new Connector(chainLength, random, blockBox, orientation, level, bl, !bl);
            }
        }

        blockBox = FancyTerminator.getBoundingBox(holder, random, x, y, z, orientation);
        if (blockBox != null) {
            return new FancyTerminator(chainLength, random, blockBox, orientation, level);
        }

        return Terminator.create(holder, random, x, y, z, orientation, level, chainLength + 1);
    }

    private static void pieceGenerator(Piece prev, StructurePiece start, StructurePiecesHolder holder, Random random, int x, int y, int z, Direction orientation, int level, int chainLength) {
        if (chainLength <= 20 && Math.abs(x - start.getBoundingBox().getMinX()) <= 120 && Math.abs(z - start.getBoundingBox().getMinZ()) <= 120) {
            Piece piece = pickPiece(prev, holder, random, x, y, z, orientation, level, chainLength + 1);
            if (piece != null) {
                holder.addPiece(piece);
                piece.fillOpenings(start, holder, random);
            }
            return;
        }

        BlockBox blockBox = FancyTerminator.getBoundingBox(holder, random, x, y, z, orientation);
        if (blockBox != null) {
            holder.addPiece(new FancyTerminator(chainLength + 1, random, blockBox, orientation, level));
            return;
        }

        holder.addPiece(Terminator.create(holder, random, x, y, z, orientation, level, chainLength + 1));
    }

    public static class Start extends Piece {
        public Start(int length, Random random, int x, int z, Direction orientation) {
            super(UStructurePieces.MS_START, length, createBox(x, 50, z, orientation, 11, 5, 11), 0);
            setOrientation(orientation);
        }

        public Start(NbtCompound nbtCompound) {
            super(UStructurePieces.MS_START, nbtCompound);
        }

        @Nullable
        public static BlockBox getBoundingBox(StructurePiecesHolder holder, Random random, int x, int y, int z, Direction orientation) {
            BlockBox blockBox = BlockBox.rotated(x, y, z, -3, 0, 0, 11, 5, 11, orientation);
            return holder.getIntersecting(blockBox) == null ? blockBox : null;
        }

        @Override
        public void fillOpenings(StructurePiece start, StructurePiecesHolder holder, Random random) {
            Direction direction = this.getFacing();
            if (direction == null) return;

            int x1 = boundingBox.getMinX();
            int y1 = boundingBox.getMinY();
            int z1 = boundingBox.getMinZ();
            int x2 = boundingBox.getMaxX();
            int y2 = boundingBox.getMaxY();
            int z2 = boundingBox.getMaxZ();

            pieceGenerator(this, start, holder, random, x1 + 3, y1, z1 - 1, Direction.NORTH, level, chainLength);
            pieceGenerator(this, start, holder, random, x2 + 1, y1, z1 + 3, Direction.EAST, level, chainLength);
            pieceGenerator(this, start, holder, random, x1 + 3, y1, z2 + 1, Direction.SOUTH, level, chainLength);
            pieceGenerator(this, start, holder, random, x1 - 1, y1, z1 + 3, Direction.WEST, level, chainLength);
        }

        @Override
        public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
            if (!this.cannotGenerate(world, chunkBox)) {
                BlockState bricks = getBricks(world);
                BlockState cobblestone = getCobble(world);

                BlockState planks = getPlanks(world);
                BlockState stairs = getWoodStairs(world);

                int x1 = 0;
                int y1 = 0;
                int z1 = 0;
                int x2 = 10;
                int y2 = 4;
                int z2 = 10;

                this.fillWithOutline(world, chunkBox, x1 + 3, y1 + 1, z1, x2 - 3, y2, z2, AIR, AIR, false);
                this.fillWithOutline(world, chunkBox, x1, y1 + 1, z1 + 3, x2, y2, z2 - 3, AIR, AIR, false);

                this.fillFloor(world, chunkBox, x1 + 3, y1, z1, x1 + 3, y1, z2, bricks, planks);
                this.fillFloor(world, chunkBox, x2 - 3, y1, z1, x2 - 3, y1, z2, bricks, planks);
                this.fillFloor(world, chunkBox, x1, y1, z1 + 3, x2, y1, z1 + 3, bricks, planks);
                this.fillFloor(world, chunkBox, x1, y1, z2 - 3, x2, y1, z2 - 3, bricks, planks);

                this.fillFloor(world, chunkBox, x1 + 4, y1, z1, x2 - 4, y1, z2, cobblestone, planks);
                this.fillFloor(world, chunkBox, x1, y1, z1 + 4, x2, y1, z2 - 4, cobblestone, planks);

                this.fillRails(world, random, chunkBox, x1 + 5, y1 + 1, z1, x1 + 5, y1 + 1, z1 + 2, RailShape.NORTH_SOUTH, true);
                this.fillRails(world, random, chunkBox, x1 + 5, y1 + 1, z2 - 2, x1 + 5, y1 + 1, z2, RailShape.NORTH_SOUTH, true);
                this.fillRails(world, random, chunkBox, x1, y1 + 1, z1 + 5, x1 + 3, y1 + 1, z1 + 5, RailShape.EAST_WEST, true);
                this.fillRails(world, random, chunkBox, x2 - 2, y1 + 1, z1 + 5, x2, y1 + 1, z1 + 2, RailShape.EAST_WEST, true);

                this.addBlock(world, stairs.with(StairsBlock.FACING, Direction.SOUTH), x1 + 5, y1 + 1, z1 + 3, chunkBox);
                this.addBlock(world, stairs.with(StairsBlock.FACING, Direction.NORTH), x1 + 5, y1 + 1, z2 - 3, chunkBox);
                this.addBlock(world, stairs.with(StairsBlock.FACING, Direction.WEST), x1 + 3, y1 + 1, z1 + 5, chunkBox);
                this.addBlock(world, stairs.with(StairsBlock.FACING, Direction.EAST), x2 - 3, y1 + 1, z1 + 5, chunkBox);

                this.generateBoxSupport(world, random, chunkBox, x1 + 3, y1, z1 + 3);
            }
        }
    }

    public static class Crossing extends Piece {
        private final boolean hasLeft;
        private final boolean hasForward;
        private final boolean hasRight;
        private final boolean railEnds;

        public Crossing(int chainLength, Random random, BlockBox boundingBox, Direction orientation, int level, boolean left, boolean forward, boolean right, boolean railEnds) {
            super(UStructurePieces.MS_CROSSING, chainLength, boundingBox, level);
            setOrientation(orientation);
            this.hasLeft = left;
            this.hasForward = forward;
            this.hasRight = right;
            this.railEnds = railEnds;
        }

        public Crossing(NbtCompound nbtCompound) {
            super(UStructurePieces.MS_CROSSING, nbtCompound);
            this.hasLeft = nbtCompound.getBoolean("left");
            this.hasForward = nbtCompound.getBoolean("forward");
            this.hasRight = nbtCompound.getBoolean("right");
            this.railEnds = nbtCompound.getBoolean("railEnds");
        }

        @Override
        protected void writeNbt(StructureContext context, NbtCompound nbt) {
            super.writeNbt(context, nbt);
            nbt.putBoolean("left", hasLeft);
            nbt.putBoolean("forward", hasForward);
            nbt.putBoolean("right", hasRight);
            nbt.putBoolean("railEnds", railEnds);
        }


        @Nullable
        public static BlockBox getBoundingBox(StructurePiecesHolder holder, Random random, int x, int y, int z, Direction orientation) {
            BlockBox blockBox = BlockBox.rotated(x, y, z, 0, 0, 0, 5, 5, 5, orientation);

            return holder.getIntersecting(blockBox) == null ? blockBox : null;
        }

        @Override
        public void fillOpenings(StructurePiece start, StructurePiecesHolder holder, Random random) {
            Direction direction = this.getFacing();
            if (direction == null) return;

            int x1 = this.boundingBox.getMinX();
            int y1 = this.boundingBox.getMinY();
            int z1 = this.boundingBox.getMinZ();
            int x2 = this.boundingBox.getMaxX();
            int y2 = this.boundingBox.getMaxY();
            int z2 = this.boundingBox.getMaxZ();

            switch (direction) {
                case NORTH -> {
                    if (hasLeft) pieceGenerator(this, start, holder, random, x1 - 1, y1, z1, Direction.WEST, level, chainLength);
                    if (hasForward) pieceGenerator(this, start, holder, random, x1, y1, z1 - 1, Direction.NORTH, level, chainLength);
                    if (hasRight) pieceGenerator(this, start, holder, random, x2 + 1, y1, z1, Direction.EAST, level, chainLength);
                }
                case SOUTH -> {
                    if (hasLeft) pieceGenerator(this, start, holder, random, x2 + 1, y1, z1, Direction.EAST, level, chainLength);
                    if (hasForward) pieceGenerator(this, start, holder, random, x1, y1, z2 + 1, Direction.SOUTH, level, chainLength);
                    if (hasRight) pieceGenerator(this, start, holder, random, x1 - 1, y1, z1, Direction.WEST, level, chainLength);
                }
                case WEST -> {
                    if (hasLeft) pieceGenerator(this, start, holder, random, x1, y1, z2 + 1, Direction.SOUTH, level, chainLength);
                    if (hasForward) pieceGenerator(this, start, holder, random, x1 - 1, y1, z1, Direction.WEST, level, chainLength);
                    if (hasRight) pieceGenerator(this, start, holder, random, x1, y1, z1 - 1, Direction.NORTH, level, chainLength);
                }
                case EAST -> {
                    if (hasLeft) pieceGenerator(this, start, holder, random, x1, y1, z1 - 1, Direction.NORTH, level, chainLength);
                    if (hasForward) pieceGenerator(this, start, holder, random, x2 + 1, y1, z1, Direction.EAST, level, chainLength);
                    if (hasRight) pieceGenerator(this, start, holder, random, x1, y1, z2 + 1, Direction.SOUTH, level, chainLength);
                }
            }
        }

        @Override
        public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
            if (!this.cannotGenerate(world, chunkBox)) {
                BlockState bricks = getBricks(world);
                BlockState cobblestone = getCobble(world);

                BlockState planks = getPlanks(world);
                BlockState stairs = getWoodStairs(world);

                boolean southWest = this.getFacing() == Direction.SOUTH || this.getFacing() == Direction.WEST;

                int x1 = 0;
                int y1 = 0;
                int z1 = 0;
                int x2 = 4;
                int y2 = 4;
                int z2 = 4;

                this.fillWithOutline(world, chunkBox, x1, y1 + 1, z1, x2, y2, z2, AIR, AIR, false);

                this.fillFloor(world, chunkBox, x1, y1, z1, x2, y1, z2, cobblestone, planks);

                if (!southWest) {
                    if (!this.hasLeft) this.fillFloor(world, chunkBox, x1, y1, z1, x1, y1, z2, bricks, planks);
                    if (!this.hasRight) this.fillFloor(world, chunkBox, x2, y1, z1, x2, y1, z2, bricks, planks);
                }
                else {
                    if (!this.hasLeft) this.fillFloor(world, chunkBox, x2, y1, z1, x2, y1, z2, bricks, planks);
                    if (!this.hasRight) this.fillFloor(world, chunkBox, x1, y1, z1, x1, y1, z2, bricks, planks);
                }

                if (!this.hasForward) {
                    this.fillFloor(world, chunkBox, x1, y1, z2, x2, y1, z2, bricks, planks);
                    this.addFloorBlock(world, chunkBox, this.hasLeft ? x1 : x2, y1, z1, bricks, planks);
                }

                if (this.railEnds) {
                    this.addBlock(world, stairs.with(StairsBlock.FACING, Direction.NORTH), x1 + 2, y1 + 1, z1, chunkBox);
                    this.addBlock(world, stairs.with(StairsBlock.FACING, Direction.SOUTH), x1 + 2, y1 + 1, z2, chunkBox);
                    this.addBlock(world, stairs.with(StairsBlock.FACING, Direction.EAST), x1, y1 + 1, z1 + 2, chunkBox);
                    this.addBlock(world, stairs.with(StairsBlock.FACING, Direction.WEST), x2, y1 + 1, z1 + 2, chunkBox);
                }
                else {
                    this.fillRails(world, random, chunkBox, x1 + 2, y1 + 1, z1, x1 + 2, y1 + 1, this.hasForward ? z2 : z1 + 1, RailShape.NORTH_SOUTH, true);
                    if (!southWest) {
                        if (this.hasLeft) this.fillRails(world, random, chunkBox, x1, y1 + 1, z1 + 2, x1 + 2, y1 + 1, z1 + 2, RailShape.EAST_WEST, true);
                        if (this.hasRight) this.fillRails(world, random, chunkBox, x2 - 2, y1 + 1, z1 + 2, x2, y1 + 1, z1 + 2, RailShape.EAST_WEST, true);
                    }
                    else {
                        if (this.hasLeft) this.fillRails(world, random, chunkBox, x2 - 2, y1 + 1, z1 + 2, x2, y1 + 1, z1 + 2, RailShape.EAST_WEST, true);
                        if (this.hasRight) this.fillRails(world, random, chunkBox, x1, y1 + 1, z1 + 2, x1 + 2, y1 + 1, z1 + 2, RailShape.EAST_WEST, true);
                    }
                    RailShape shape;
                    if (!southWest) shape = this.hasLeft ? RailShape.SOUTH_WEST : RailShape.SOUTH_EAST;
                    else shape = this.hasLeft ? RailShape.SOUTH_EAST : RailShape.SOUTH_WEST;

                    if (random.nextFloat() < RAIL_CHANCE && ((this.hasForward && (this.hasLeft ^ this.hasRight)) || this.hasLeft && this.hasRight)) this.addRail(world, random, chunkBox, x1 + 2, y1 + 1, z1 + 2, RAIL.getDefaultState().with(RailBlock.SHAPE, shape), false);
                }

                this.generateBoxSupport(world, random, chunkBox, x1, y1, z1);
            }
        }
    }

    public static class Connector extends Piece {
        public final boolean hasTurn;
        public final boolean hasStairs;
        public Connector(int chainLength, Random random, BlockBox boundingBox, Direction orientation, int level, boolean hasTurn, boolean hasStairs) {
            super(UStructurePieces.MS_CONNECTOR, chainLength, boundingBox, level);
            setOrientation(orientation);
            this.hasTurn = hasTurn;
            this.hasStairs = hasStairs;
        }

        public Connector(NbtCompound nbtCompound) {
            super(UStructurePieces.MS_CONNECTOR, nbtCompound);
            this.hasTurn = nbtCompound.getBoolean("turn");
            this.hasStairs = nbtCompound.getBoolean("stairs");
        }

        @Override
        protected void writeNbt(StructureContext context, NbtCompound nbt) {
            super.writeNbt(context, nbt);
            nbt.putBoolean("turn", hasTurn);
            nbt.putBoolean("stairs", hasStairs);
        }

        @Nullable
        public static BlockBox getBoundingBox(StructurePiecesHolder holder, Random random, int x, int y, int z, Direction orientation) {
            BlockBox blockBox = BlockBox.rotated(x, y, z, 0, 0, 0, 5, 5, 3, orientation);

            return holder.getIntersecting(blockBox) == null ? blockBox : null;
        }

        @Override
        public void fillOpenings(StructurePiece start, StructurePiecesHolder holder, Random random) {
            Direction direction = this.getFacing();
            if (direction == null) return;

            int x1 = this.boundingBox.getMinX();
            int y1 = this.boundingBox.getMinY();
            int z1 = this.boundingBox.getMinZ();
            int x2 = this.boundingBox.getMaxX();
            int y2 = this.boundingBox.getMaxY();
            int z2 = this.boundingBox.getMaxZ();

            switch (direction) {
                case NORTH -> {
                    pieceGenerator(this, start, holder, random, x1, y1, z1 - 1, direction, level, chainLength);
                }
                case SOUTH -> {
                    pieceGenerator(this, start, holder, random, x1, y1, z2 + 1, direction, level, chainLength);
                }
                case WEST -> {
                    pieceGenerator(this, start, holder, random, x1 - 1, y1, z1, direction, level, chainLength);
                }
                case EAST -> {
                    pieceGenerator(this, start, holder, random, x2 + 1, y1, z1, direction, level, chainLength);
                }
            }
        }

        @Override
        public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
            if (!this.cannotGenerate(world, chunkBox)) {
                BlockState bricks = getBricks(world);
                BlockState cobblestone = getCobble(world);

                BlockState planks = getPlanks(world);

                int x1 = 0;
                int y1 = 0;
                int z1 = 0;
                int x2 = 4;
                int y2 = 4;
                int z2 = 2;

                this.fillWithOutline(world, chunkBox, x1, y1 + 1, z1, x2, y2, z2, AIR, AIR, false);

                this.fillFloor(world, chunkBox, x1, y1, z1, x1, y1, z2, bricks, planks);
                this.fillFloor(world, chunkBox, x2, y1, z1, x2, y1, z2, bricks, planks);

                this.fillFloor(world, chunkBox, x1 + 1, y1, z1, x2 - 1, y1, z2, cobblestone, planks);

                this.fillRails(world, random, chunkBox, x1 + 2, y1 + 1, z1, x1 + 2, y1 + 1, z2, RailShape.NORTH_SOUTH, true);
            }
        }
    }

    public static class Tunnel extends Piece {
        public Tunnel(int chainLength, Random random, BlockBox boundingBox, Direction orientation, int level) {
            super(UStructurePieces.MS_TUNNEL, chainLength, boundingBox, level);
            setOrientation(orientation);
        }

        public Tunnel(NbtCompound nbtCompound) {
            super(UStructurePieces.MS_TUNNEL, nbtCompound);
        }

        @Nullable
        public static BlockBox getBoundingBox(StructurePiecesHolder holder, Random random, int x, int y, int z, Direction orientation) {
            BlockBox blockBox = BlockBox.rotated(x, y, z, 0, 0, 0, 5, 5, 7, orientation);

            return holder.getIntersecting(blockBox) == null ? blockBox : null;
        }

        @Override
        public void fillOpenings(StructurePiece start, StructurePiecesHolder holder, Random random) {
            Direction direction = this.getFacing();
            if (direction == null) return;

            int x1 = this.boundingBox.getMinX();
            int y1 = this.boundingBox.getMinY();
            int z1 = this.boundingBox.getMinZ();
            int x2 = this.boundingBox.getMaxX();
            int y2 = this.boundingBox.getMaxY();
            int z2 = this.boundingBox.getMaxZ();

            switch (direction) {
                case NORTH -> {
                    pieceGenerator(this, start, holder, random, x1, y1, z1 - 1, direction, level, chainLength);
                }
                case SOUTH -> {
                    pieceGenerator(this, start, holder, random, x1, y1, z2 + 1, direction, level, chainLength);
                }
                case WEST -> {
                    pieceGenerator(this, start, holder, random, x1 - 1, y1, z1, direction, level, chainLength);
                }
                case EAST -> {
                    pieceGenerator(this, start, holder, random, x2 + 1, y1, z1, direction, level, chainLength);
                }
            }
        }

        @Override
        public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
            if (!this.cannotGenerate(world, chunkBox)) {
                BlockState bricks = getBricks(world);
                BlockState cobblestone = getCobble(world);

                BlockState planks = getPlanks(world);

                int x1 = 0;
                int y1 = 0;
                int z1 = 0;
                int x2 = 4;
                int y2 = 4;
                int z2 = 6;

                this.fillWithOutline(world, chunkBox, x1, y1 + 1, z1, x2, y2, z2, AIR, AIR, false);

                this.fillFloor(world, chunkBox, x1, y1, z1, x1, y1, z2, bricks, planks);
                this.fillFloor(world, chunkBox, x2, y1, z1, x2, y1, z2, bricks, planks);

                this.fillFloor(world, chunkBox, x1 + 1, y1, z1, x2 - 1, y1, z2, cobblestone, planks);

                this.fillRails(world, random, chunkBox, x1 + 2, y1 + 1, z1, x1 + 2, y1 + 1, z2, RailShape.NORTH_SOUTH, true);

                this.generateSupport(world, random, chunkBox, x1, y1, z1 + 3);
            }
        }
    }

    public static class Stairs extends Piece {
        public final int prevLevel;

        public Stairs(int chainLength, Random random, BlockBox boundingBox, Direction orientation, int level, int prevLevel) {
            super(UStructurePieces.MS_STAIRS, chainLength, boundingBox, level);
            setOrientation(orientation);
            this.prevLevel = prevLevel;
        }

        public Stairs(NbtCompound nbtCompound) {
            super(UStructurePieces.MS_STAIRS, nbtCompound);
            this.prevLevel = nbtCompound.getInt("prevLevel");
        }

        @Override
        protected void writeNbt(StructureContext context, NbtCompound nbt) {
            super.writeNbt(context, nbt);
            nbt.putInt("prevLevel", prevLevel);
        }

        @Nullable
        public static BlockBox getBoundingBox(StructurePiecesHolder holder, Random random, int x, int y, int z, Direction orientation, boolean isDown) {
            BlockBox blockBox = BlockBox.rotated(x, y, z, 0, isDown ? -6 : 0, 0, 5, 11, 10, orientation);

            return holder.getIntersecting(blockBox) == null ? blockBox : null;
        }

        @Override
        public void fillOpenings(StructurePiece start, StructurePiecesHolder holder, Random random) {
            Direction direction = this.getFacing();
            if (direction == null) return;

            int x1 = this.boundingBox.getMinX();
            int y1 = this.boundingBox.getMinY();
            int z1 = this.boundingBox.getMinZ();
            int x2 = this.boundingBox.getMaxX();
            int y2 = this.boundingBox.getMaxY();
            int z2 = this.boundingBox.getMaxZ();

            int yOffset = level > prevLevel ? 6 : 0;

            switch (direction) {
                case NORTH -> {
                    pieceGenerator(this, start, holder, random, x1, y1 + yOffset, z1 - 1, direction, level, chainLength);
                }
                case SOUTH -> {
                    pieceGenerator(this, start, holder, random, x1, y1 + yOffset, z2 + 1, direction, level, chainLength);
                }
                case WEST -> {
                    pieceGenerator(this, start, holder, random, x1 - 1, y1 + yOffset, z1, direction, level, chainLength);
                }
                case EAST -> {
                    pieceGenerator(this, start, holder, random, x2 + 1, y1 + yOffset, z1, direction, level, chainLength);
                }
            }
        }

        @Override
        public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
            if (!this.cannotGenerate(world, chunkBox)) {
                BlockState bricks = getBricks(world);
                BlockState brickStairs = getBrickStairs(world);

                BlockState cobblestone = getCobble(world);
                BlockState cobbleStairs = getCobbleStairs(world);

                BlockState planks = getPlanks(world);
                BlockState woodStairs = getWoodStairs(world);
                
                int x1 = 0;
                int y1 = 0;
                int z1 = 0;
                int x2 = 4;
                int y2 = 10;
                int z2 = 9;

                if (level < prevLevel) {
                    this.fillWithOutline(world, chunkBox, x1, y2 - 3, z1, x2, y2, z1, AIR, AIR, false);
                    this.fillFloor(world, chunkBox, x1 + 1, y2 - 4, z1, x2 - 1, y2 - 4, z1, cobblestone, planks);
                    this.addRail(world, random, chunkBox, x1 + 2, y2 - 3, z1, RAIL.getDefaultState().with(RailBlock.SHAPE, RailShape.NORTH_SOUTH), true);

                    this.generateSupport(world, random, chunkBox, x1, y2 - 4, z1);

                    for (int i = 0; i < 7; i++) {
                        this.fillWithOutline(world, chunkBox, x1, y2 - 3 - i, z1 + 1 + i, x2, y2 - i, z1 + 3 + i, AIR, AIR, false);

                        if (i > 0) {
                            this.addFloorBlock(world, chunkBox, x1, y2 - 3 - i, z1 + 1 + i, brickStairs.with(StairsBlock.FACING, Direction.SOUTH), woodStairs.with(StairsBlock.FACING, Direction.SOUTH));
                            this.addFloorBlock(world, chunkBox, x2, y2 - 3 - i, z1 + 1 + i, brickStairs.with(StairsBlock.FACING, Direction.SOUTH), woodStairs.with(StairsBlock.FACING, Direction.SOUTH));
                            this.addFloorBlock(world, chunkBox, x1 + 1, y2 - 3 - i, z1 + 1 + i, cobbleStairs.with(StairsBlock.FACING, Direction.SOUTH), woodStairs.with(StairsBlock.FACING, Direction.SOUTH));
                            this.addFloorBlock(world, chunkBox, x2 - 1, y2 - 3 - i, z1 + 1 + i, cobbleStairs.with(StairsBlock.FACING, Direction.SOUTH), woodStairs.with(StairsBlock.FACING, Direction.SOUTH));
                        }

                        this.fillFloor(world, chunkBox, x1 + 1, y2 - 4 - i, z1 + 1 + i, x2 - 1, y2 - 4 - i, z1 + 1 + i, cobblestone, planks);
                        this.addFloorBlock(world, chunkBox, x1, y2 - 4 - i, z1 + 1 + i, bricks, planks);
                        this.addFloorBlock(world, chunkBox, x2, y2 - 4 - i, z1 + 1 + i, bricks, planks);

                        if (i > 0) this.addRail(world, random, chunkBox, x1 + 2, y2 - 3 - i, z1 + 1 + i, RAIL.getDefaultState().with(RailBlock.SHAPE, RailShape.ASCENDING_SOUTH), false);
                    }

                    this.fillFloor(world, chunkBox, x1 + 1, y1, z2 - 1, x2 - 1, y1, z2, cobblestone, planks);

                    this.addFloorBlock(world, chunkBox, x1, y1, z2 - 1, bricks, planks);
                    this.addFloorBlock(world, chunkBox, x2, y1, z2 - 1, bricks, planks);

                    this.addRail(world, random, chunkBox, x1 + 2, y1 + 1, z2, RAIL.getDefaultState().with(RailBlock.SHAPE, RailShape.NORTH_SOUTH), true);

                    this.generateSupport(world, random, chunkBox, x1, y1, z2);
                }
                else {
                    this.fillWithOutline(world, chunkBox, x1, y1, z1, x2, y1 + 4, z1, AIR, AIR, false);
                    this.fillFloor(world, chunkBox, x1 + 1, y1, z1, x2 - 1, y1, z1, cobblestone, planks);

                    this.addRail(world, random, chunkBox, x1 + 2, y1 + 1, z1, RAIL.getDefaultState().with(RailBlock.SHAPE, RailShape.NORTH_SOUTH), true);

                    this.generateSupport(world, random, chunkBox, x1, y1, z1);

                    for (int i = 0; i < 7; i++) {
                        this.fillWithOutline(world, chunkBox, x1, y1 + i, z1 + 1 + i, x2, y1 + (i != 6 ? 5 : 4) + i, z1 + 1 + i, AIR, AIR, false);

                        if (i > 0) {
                            this.addFloorBlock(world, chunkBox, x1, y1 + i, z1 + 1 + i, brickStairs.with(StairsBlock.FACING, Direction.NORTH), woodStairs.with(StairsBlock.FACING, Direction.NORTH));
                            this.addFloorBlock(world, chunkBox, x2, y1 + i, z1 + 1 + i, brickStairs.with(StairsBlock.FACING, Direction.NORTH), woodStairs.with(StairsBlock.FACING, Direction.NORTH));
                            this.addFloorBlock(world, chunkBox, x1 + 1, y1 + i, z1 + 1 + i, cobbleStairs.with(StairsBlock.FACING, Direction.NORTH), woodStairs.with(StairsBlock.FACING, Direction.NORTH));
                            this.addFloorBlock(world, chunkBox, x2 - 1, y1 + i, z1 + 1 + i, cobbleStairs.with(StairsBlock.FACING, Direction.NORTH), woodStairs.with(StairsBlock.FACING, Direction.NORTH));
                        }

                        int yCheck = y1 - (i != 0 ? 1 : 0) + i;
                        this.fillFloor(world, chunkBox, x1 + 1, yCheck, z1 + 1 + i, x2 - 1, yCheck, z1 + 1 + i, cobblestone, planks);
                        this.addFloorBlock(world, chunkBox, x1, yCheck, z1 + 1 + i, bricks, planks);
                        this.addFloorBlock(world, chunkBox, x2, yCheck, z1 + 1 + i, bricks, planks);

                        if (i > 0) this.addRail(world, random, chunkBox, x1 + 2, yCheck, z1 + i, RAIL.getDefaultState().with(RailBlock.SHAPE, RailShape.ASCENDING_NORTH), false);
                    }

                    this.fillWithOutline(world, chunkBox, x1, y2 - 3, z2 - 1, x2, y2, z2, AIR, AIR, false);

                    this.fillFloor(world, chunkBox, x1, y2 - 4, z2 - 1, x2, y2 - 4, z2, cobblestone, planks);
                    this.addFloorBlock(world, chunkBox, x1, y2 - 4, z2 - 1, bricks, planks);
                    this.addFloorBlock(world, chunkBox, x2, y2 - 4, z2 - 1, bricks, planks);

                    this.generateSupport(world, random, chunkBox, x1, y2 - 4, z2);
                }
            }
        }
    }

    public static class Terminator extends Piece {
        public Terminator(int chainLength, Random random, BlockBox boundingBox, Direction orientation, int level) {
            super(UStructurePieces.MS_END, chainLength, boundingBox, level);
            setOrientation(orientation);
        }

        public Terminator(NbtCompound nbtCompound) {
            super(UStructurePieces.MS_END, nbtCompound);
        }

        public static Terminator create(StructurePiecesHolder holder, Random random, int x, int y, int z, Direction orientation, int level, int chainLength) {
            BlockBox blockBox = Terminator.getBoundingBox(holder, random, x, y, z, orientation);
            return new Terminator(chainLength, random, blockBox, orientation, level);
        }

        @Nullable
        public static BlockBox getBoundingBox(StructurePiecesHolder holder, Random random, int x, int y, int z, Direction orientation) {
            return BlockBox.rotated(x, y, z, 0, 0, 0, 5, 5, 1, orientation);
        }

        @Override
        public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
            if (!this.cannotGenerate(world, chunkBox)) {
                BlockState bricks = getBricks(world);
                BlockState cobblestone = getCobble(world);

                BlockState planks = getPlanks(world);
                BlockState fence = getFence(world);

                int x1 = 0;
                int y1 = 0;
                int z1 = 0;
                int x2 = 4;
                int y2 = 4;
                int z2 = 0;

                int airCount = 0;
                for (int i = 0; i < x2; i++) {
                    if (getBlockAt(world, x1 + i, y1 - 1, z1, chunkBox).isAir()) airCount++;
                }
                boolean isInAir = airCount > 3;


                this.fillWithOutline(world, chunkBox, x1, y1, z1, x2, y1, z2, isInAir ? planks : bricks, isInAir ? planks : bricks, false);
                this.generateSupport(world, random, chunkBox, x1, y1, z1);

                BlockState bars = isInAir ? fence.with(FenceBlock.EAST, true).with(FenceBlock.WEST, true) : IRON_BARS.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true);
                this.fillWithOutline(world, chunkBox, x1 + 1, y1 + 1, z1, x2 - 1, y2 - 1, z2, bars, bars, false);
            }
        }
    }

    public static class FancyTerminator extends Piece {
        public FancyTerminator(int chainLength, Random random, BlockBox boundingBox, Direction orientation, int level) {
            super(UStructurePieces.MS_FANCY_END, chainLength, boundingBox, level);
            setOrientation(orientation);
        }

        public FancyTerminator(NbtCompound nbtCompound) {
            super(UStructurePieces.MS_FANCY_END, nbtCompound);
        }

        @Nullable
        public static BlockBox getBoundingBox(StructurePiecesHolder holder, Random random, int x, int y, int z, Direction orientation) {
            BlockBox blockBox = BlockBox.rotated(x, y, z, 0, 0, 0, 5, 5, 4, orientation);
            return holder.getIntersecting(blockBox) == null ? blockBox : null;
        }

        @Override
        public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
            if (!this.cannotGenerate(world, chunkBox)) {
                BlockState bricks = getBricks(world);
                BlockState cobblestone = getCobble(world);

                BlockState planks = getPlanks(world);
                BlockState stairs = getWoodStairs(world);

                int x1 = 0;
                int y1 = 0;
                int z1 = 0;
                int x2 = 4;
                int y2 = 4;
                int z2 = 3;

                this.fillWithOutline(world, chunkBox, x1, y1 + 1, z1, x2, y2, z2, AIR, AIR, false);

                this.fillFloor(world, chunkBox, x1, y1, z1, x1, y1, z2, bricks, planks);
                this.fillFloor(world, chunkBox, x2, y1, z1, x2, y1, z2, bricks, planks);
                this.fillFloor(world, chunkBox, x1, y1, z2, x2, y1, z2, bricks, planks);

                this.fillFloor(world, chunkBox, x1 + 1, y1, z1, x2 - 1, y1, z2 - 1, cobblestone, planks);

                this.addRail(world, random, chunkBox, x1 + 2, y1 + 1, z1, RAIL.getDefaultState().with(RailBlock.SHAPE, RailShape.NORTH_SOUTH), true);
                this.addBlock(world, stairs.with(StairsBlock.FACING, Direction.NORTH), x1 + 2, y1 + 1, z1 + 1, chunkBox);

                this.generateSupport(world, random, chunkBox, x1, y1, z1 + 3);
            }
        }
    }

    private abstract static class Piece extends StructurePiece {
        public final int level;
        public static final float RAIL_CHANCE = .875f;
        public static final float CHEST_CHANCE = .005f;
        public static final float LANTERN_CHANCE = .2f;

        public Piece(StructurePieceType type, int length, BlockBox boundingBox, int level) {
            super(type, length, boundingBox);
            this.level = level;
        }

        public Piece(StructurePieceType type, NbtCompound nbtCompound) {
            super(type, nbtCompound);
            this.level = nbtCompound.getInt("level");
        }

        @Override
        protected void writeNbt(StructureContext context, NbtCompound nbt) {
            nbt.putInt("level", level);
        }

        protected BlockState getLog(StructureWorldAccess world) {
            return OAK_LOG.getDefaultState();
        }

        protected BlockState getPlanks(StructureWorldAccess world) {
            return OAK_PLANKS.getDefaultState();
        }

        protected BlockState getWoodStairs(StructureWorldAccess world) {
            return OAK_STAIRS.getDefaultState();
        }

        protected BlockState getFence(StructureWorldAccess world) {
            return OAK_FENCE.getDefaultState();
        }

        protected BlockState getCobbleStairs(StructureWorldAccess world) {
            return (boundingBox.getMinY() < 0 ? COBBLED_DEEPSLATE_STAIRS : COBBLESTONE_STAIRS).getDefaultState();
        }

        protected BlockState getBrickStairs(StructureWorldAccess world) {
            return (boundingBox.getMinY() < 0 ? DEEPSLATE_BRICK_STAIRS : STONE_BRICK_STAIRS).getDefaultState();
        }

        protected BlockState getCobble(StructureWorldAccess world) {
            return (boundingBox.getMinY() < 0 ? COBBLED_DEEPSLATE : COBBLESTONE).getDefaultState();
        }

        protected BlockState getBricks(StructureWorldAccess world) {
            return (boundingBox.getMinY() < 0 ? DEEPSLATE_BRICKS : STONE_BRICKS).getDefaultState();
        }

        protected void fillFloor(StructureWorldAccess world, BlockBox bounds, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, BlockState ground, BlockState floating) {
            for(int i = minY; i <= maxY; ++i) {
                for(int j = minX; j <= maxX; ++j) {
                    for(int k = minZ; k <= maxZ; ++k) {
                        this.addBlock(world, getBlockAt(world, j, i - 1, k, bounds).isAir() ? floating : ground, j, i, k, bounds);
                    }
                }
            }
        }

        protected void addFloorBlock(StructureWorldAccess world, BlockBox bounds, int x, int y, int z, BlockState ground, BlockState floating) {
            this.addBlock(world, getBlockAt(world, x, y - 1, z, bounds).isAir() ? floating : ground, x, y, z, bounds);
        }

        protected void fillWithChance(StructureWorldAccess world, BlockBox bounds, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, BlockState state, float chance) {
            Random random = world.getRandom();
            for(int i = minY; i <= maxY; ++i) {
                for(int j = minX; j <= maxX; ++j) {
                    for(int k = minZ; k <= maxZ; ++k) {
                        if (random.nextFloat() < chance) this.addBlock(world, state, j, i, k, bounds);
                    }
                }
            }
        }

        protected void addChestMinecart(StructureWorldAccess world, Random random, int x, int y, int z) {
            BlockPos blockPos = this.offsetPos(x, y, z);

            ChestMinecartEntity chestMinecartEntity = new ChestMinecartEntity(world.toServerWorld(), (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5);
            chestMinecartEntity.setLootTable(LootTables.ABANDONED_MINESHAFT_CHEST, random.nextLong());
            world.spawnEntity(chestMinecartEntity);
        }

        protected void addRail(StructureWorldAccess world, Random random, BlockBox bounds, int x, int y, int z, BlockState block, boolean canHaveChest) {
            if (random.nextFloat() < RAIL_CHANCE) {
                this.addBlock(world, block, x, y, z, bounds);
                if (canHaveChest && random.nextFloat() < CHEST_CHANCE) {
                    addChestMinecart(world, random, x, y, z);
                }
            }
        }

        protected void fillRails(StructureWorldAccess world, Random random, BlockBox bounds, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, RailShape shape, boolean canHaveChest) {
            BlockState state = RAIL.getDefaultState().with(RailBlock.SHAPE, shape);
            boolean hasChest = false;

            for(int i = minY; i <= maxY; ++i) {
                for(int j = minX; j <= maxX; ++j) {
                    for(int k = minZ; k <= maxZ; ++k) {
                        if (random.nextFloat() < RAIL_CHANCE) {
                            this.addBlock(world, state, j, i, k, bounds);
                            if (canHaveChest && !hasChest && random.nextFloat() < CHEST_CHANCE) {
                                hasChest = true;
                                addChestMinecart(world, random, random.nextBetween(minX, maxX), random.nextBetween(minY, maxY), random.nextBetween(minZ, maxZ));
                            }
                        }
                    }
                }
            }
        }

        protected void generateSupport(StructureWorldAccess world, Random random, BlockBox chunkBox, int x1, int y1, int z1) {
            BlockState log = getLog(world);
            BlockState fence = getFence(world);
            BlockState lantern = LANTERN.getDefaultState();

            this.fillWithOutline(world, chunkBox, x1, y1, z1, x1, y1 + 4, z1, log, AIR, false);
            this.fillWithOutline(world, chunkBox, x1 + 4, y1, z1, x1 + 4, y1 + 4, z1, log, AIR, false);

            this.fillDownwards(world, log, x1, y1 - 1, z1, chunkBox);
            this.fillDownwards(world, log, x1 + 4, y1 - 1, z1, chunkBox);

            this.fillWithOutline(world, chunkBox, x1, y1 + 4, z1, x1 + 4, y1 + 4, z1, log.with(PillarBlock.AXIS, Direction.Axis.X), AIR, false);

            this.addBlock(world, fence.with(FenceBlock.WEST, true), x1 + 1, y1 + 3, z1, chunkBox);
            this.addBlock(world, fence.with(FenceBlock.EAST, true), x1 + 3, y1 + 3, z1, chunkBox);

            if (random.nextFloat() < LANTERN_CHANCE) this.addBlock(world, lantern.with(LanternBlock.HANGING, true), x1 + 2, y1 + 3, z1, chunkBox);
        }

        protected void generateBoxSupport(StructureWorldAccess world, Random random, BlockBox chunkBox, int x1, int y1, int z1) {
            BlockState log = getLog(world);
            BlockState fence = getFence(world);
            BlockState lantern = LANTERN.getDefaultState();

            this.fillWithOutline(world, chunkBox, x1, y1, z1, x1, y1 + 4, z1, log, AIR, false);
            this.fillWithOutline(world, chunkBox, x1 + 4, y1, z1, x1 + 4, y1 + 4, z1, log, AIR, false);
            this.fillWithOutline(world, chunkBox, x1, y1, z1 + 4, x1, y1 + 4, z1 + 4, log, AIR, false);
            this.fillWithOutline(world, chunkBox, x1 + 4, y1, z1 + 4, x1 + 4, y1 + 4, z1 + 4, log, AIR, false);

            this.fillDownwards(world, log, x1, y1 - 1, z1, chunkBox);
            this.fillDownwards(world, log, x1 + 4, y1 - 1, z1, chunkBox);
            this.fillDownwards(world, log, x1, y1 - 1, z1 + 4, chunkBox);
            this.fillDownwards(world, log, x1 + 4, y1 - 1, z1 + 4, chunkBox);

            this.fillWithOutline(world, chunkBox, x1, y1 + 4, z1, x1 + 3, y1 + 4, z1, log.with(PillarBlock.AXIS, Direction.Axis.X), AIR, false);
            this.fillWithOutline(world, chunkBox, x1 + 1, y1 + 4, z1 + 4, x1 + 4, y1 + 4, z1 + 4, log.with(PillarBlock.AXIS, Direction.Axis.X), AIR, false);

            this.fillWithOutline(world, chunkBox, x1, y1 + 4, z1 + 1, x1, y1 + 4, z1 + 4, log.with(PillarBlock.AXIS, Direction.Axis.Z), AIR, false);
            this.fillWithOutline(world, chunkBox, x1 + 4, y1 + 4, z1, x1 + 4, y1 + 4, z1 + 3, log.with(PillarBlock.AXIS, Direction.Axis.Z), AIR, false);

            this.addBlock(world, fence.with(FenceBlock.WEST, true), x1 + 1, y1 + 3, z1, chunkBox);
            this.addBlock(world, fence.with(FenceBlock.EAST, true), x1 + 3, y1 + 3, z1, chunkBox);
            this.addBlock(world, fence.with(FenceBlock.WEST, true), x1 + 1, y1 + 3, z1 + 4, chunkBox);
            this.addBlock(world, fence.with(FenceBlock.EAST, true), x1 + 3, y1 + 3, z1 + 4, chunkBox);

            this.addBlock(world, fence.with(FenceBlock.SOUTH, true), x1, y1 + 3, z1 + 1, chunkBox);
            this.addBlock(world, fence.with(FenceBlock.NORTH, true), x1, y1 + 3, z1 + 3, chunkBox);
            this.addBlock(world, fence.with(FenceBlock.SOUTH, true), x1 + 4, y1 + 3, z1 + 1, chunkBox);
            this.addBlock(world, fence.with(FenceBlock.NORTH, true), x1 + 4, y1 + 3, z1 + 3, chunkBox);

            if (random.nextFloat() < LANTERN_CHANCE) this.addBlock(world, lantern.with(LanternBlock.HANGING, true), x1 + 2, y1 + 3, z1, chunkBox);
            if (random.nextFloat() < LANTERN_CHANCE) this.addBlock(world, lantern.with(LanternBlock.HANGING, true), x1 + 2, y1 + 3, z1 + 4, chunkBox);

            if (random.nextFloat() < LANTERN_CHANCE) this.addBlock(world, lantern.with(LanternBlock.HANGING, true), x1, y1 + 3, z1 + 2, chunkBox);
            if (random.nextFloat() < LANTERN_CHANCE) this.addBlock(world, lantern.with(LanternBlock.HANGING, true), x1 + 4, y1 + 3, z1 + 2, chunkBox);
        }

        protected boolean cannotGenerate(WorldAccess world, BlockBox box) {
            int i = Math.max(this.boundingBox.getMinX() - 1, box.getMinX());
            int j = Math.max(this.boundingBox.getMinY() - 1, box.getMinY());
            int k = Math.max(this.boundingBox.getMinZ() - 1, box.getMinZ());
            int l = Math.min(this.boundingBox.getMaxX() + 1, box.getMaxX());
            int m = Math.min(this.boundingBox.getMaxY() + 1, box.getMaxY());
            int n = Math.min(this.boundingBox.getMaxZ() + 1, box.getMaxZ());
            BlockPos.Mutable mutable = new BlockPos.Mutable((i + l) / 2, (j + m) / 2, (k + n) / 2);
            return world.getBiome(mutable).isIn(BiomeTags.MINESHAFT_BLOCKING);
        }
    }
}
