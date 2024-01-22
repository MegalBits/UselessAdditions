package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.effect.UStatusEffects;
import net.minecraft.block.Blocks;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.ShiftableStructurePiece;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.SwampHutGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.structure.SwampHutStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SwampHutGenerator.class)
public abstract class AddChestToWitchHut extends ShiftableStructurePiece {
    protected AddChestToWitchHut(StructurePieceType type, int x, int y, int z, int width, int height, int depth, Direction orientation) {
        super(type, x, y, z, width, height, depth, orientation);
    }

    @Inject(at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/structure/SwampHutGenerator;addBlock(Lnet/minecraft/world/StructureWorldAccess;Lnet/minecraft/block/BlockState;IIILnet/minecraft/util/math/BlockBox;)V",
                ordinal = 0
            ),
            method = "generate")
    private void applyCurse(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot, CallbackInfo ci) {
        this.addChest(world, chunkBox, random, 2, 2, 6, new Identifier(UAdd.MOD_ID, "chests/swamp_hut_chest"));
    }
}
