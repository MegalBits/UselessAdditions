package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.block.SlimeCake;
import net.megal.uselessadditions.effect.UStatusEffects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlimeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.structure.ShiftableStructurePiece;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.SwampHutGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.BlockView;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BounceIfSlimed {
    @Inject(at = @At("HEAD"),
            method = "onEntityLand",
            cancellable = true)
    public void onEntityLand(BlockView world, Entity entity, CallbackInfo ci) {
        if (!entity.bypassesLandingEffects() && entity instanceof LivingEntity livingEntity && livingEntity.hasStatusEffect(UStatusEffects.SLIMED)) {
            Vec3d vec3d = entity.getVelocity();
            if (vec3d.y < -0.1) {
                double d = entity instanceof LivingEntity ? 1.0d : 0.8d;
                BlockSoundGroup sound = BlockSoundGroup.SLIME;
                entity.setVelocity(vec3d.x, -vec3d.y * d, vec3d.z);
                entity.playSound(sound.getStepSound(), sound.getVolume() * 0.15f, sound.getPitch());
            }
            else {
                entity.setVelocity(entity.getVelocity().multiply(1.0d, 0.0d, 1.0d));
            }
            ci.cancel();
        }
    }
}
