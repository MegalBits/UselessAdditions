package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.UItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.phase.AbstractPhase;
import net.minecraft.entity.boss.dragon.phase.StrafePlayerPhase;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.EndPortalFeature;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StrafePlayerPhase.class)
public abstract class EnderdragonShotgun extends AbstractPhase {
    @Shadow
    @Nullable
    private LivingEntity target;

    public EnderdragonShotgun(EnderDragonEntity dragon) {
        super(dragon);
    }

    @Inject(at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/entity/projectile/DragonFireballEntity;refreshPositionAndAngles(DDDFF)V"
            ),
            method = "serverTick()V")
    private void shootShotgun(CallbackInfo ci) {
        World world = dragon.getWorld();
        Random random = world.getRandom();



        if (random.nextBetween(0,2) == 0) {
            Vec3d vec3d3 = this.dragon.getRotationVec(1.0F);
            double l = dragon.head.getX() - vec3d3.x;
            double m = dragon.head.getBodyY(0.5) + 0.5;
            double n = dragon.head.getZ() - vec3d3.z;
            double o = target.getX() - l;
            double p = target.getBodyY(0.5) - m;
            double q = target.getZ() - n;

            float mod = random.nextFloat() * random.nextBetween(20, 30);
            for (int i = 0; i < random.nextBetween(3,7); i++) {
                float xM = (random.nextFloat() - .5f) * mod;
                float yM = (random.nextFloat() - .5f) * (mod/2);
                float zM = (random.nextFloat() - .5f) * mod;
                if (!world.isClient()) {
                    DragonFireballEntity dragonFireballEntity = new DragonFireballEntity(world, dragon, o + xM, p + yM, q + zM);
                    dragonFireballEntity.refreshPositionAndAngles(l, m, n, 0.0F, 0.0F);
                    world.spawnEntity(dragonFireballEntity);
                }
            }

        }
    }
}