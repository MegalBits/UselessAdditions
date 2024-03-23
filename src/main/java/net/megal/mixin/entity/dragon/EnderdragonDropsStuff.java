package net.megal.mixin.entity.dragon;

import net.megal.item.UItems;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.EndPortalFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderDragonEntity.class)
public abstract class EnderdragonDropsStuff {
    @Shadow
    private BlockPos fightOrigin;

    @Inject(
            at = @At("RETURN"),
            method = "kill()V"
    )
    private void spawnIfKilledByCommands(CallbackInfo ci) {
        EnderDragonEntity dragon = ((EnderDragonEntity)(Object)this);
        if (!dragon.getWorld().isClient() && dragon.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            Random random = Random.create();
            World world = dragon.getWorld();
            BlockPos portalPos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPortalFeature.offsetOrigin(fightOrigin));
            BlockPos dragonPos = dragon.getBlockPos();
            for (int i = 0; i < random.nextBetween(6, 18); i++) {
                ItemEntity scale = new ItemEntity(world, dragonPos.getX(), dragonPos.getY(), dragonPos.getZ(), new ItemStack(UItems.DRAGON_SCALE), (double) random.nextBetween(-8,8)/10d, (double) random.nextBetween(4,7)/4d, (double) random.nextBetween(-8,8)/10d);
                scale.setPickupDelay(80);
                world.spawnEntity(scale);
            }

            /*
            for (int i = 0; i < random.nextBetween(32, 96); i++) {
                ItemEntity flesh = new ItemEntity(world, dragonPos.getX(), dragonPos.getY(), dragonPos.getZ(), new ItemStack(UItems.DRAGON_FLESH), (double) random.nextBetween(-8,8)/10d, (double) random.nextBetween(-2,9)/4d, (double) random.nextBetween(-8,8)/10d);
                flesh.setPickupDelay(80);
                world.spawnEntity(flesh);
            }

            ItemEntity heart = new ItemEntity(world, dragonPos.getX(), dragonPos.getY(), dragonPos.getZ(), new ItemStack(UItems.DRAGON_HEART), (double) random.nextBetween(-8,8)/10d, (double) random.nextBetween(4,7)/4d, (double) random.nextBetween(-8,8)/10d);
            heart.setPickupDelay(80);
            world.spawnEntity(heart);
            */
        }
    }
    @Inject(
            at = @At("RETURN"),
            method = "updatePostDeath()V"
    )
    private void spawnWhileAndAfterDying(CallbackInfo ci) {
        EnderDragonEntity dragon = ((EnderDragonEntity)(Object)this);
        if (!dragon.getWorld().isClient() && dragon.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            Random random = Random.create();
            World world = dragon.getWorld();
            BlockPos portalPos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPortalFeature.offsetOrigin(fightOrigin));
            BlockPos dragonPos = dragon.getBlockPos();
            if (random.nextBetween(0, 24) == 0) {
                ItemEntity scale = new ItemEntity(world, dragonPos.getX(), dragonPos.getY(), dragonPos.getZ(), new ItemStack(UItems.DRAGON_SCALE), (double) random.nextBetween(-8,8)/10d, (double) random.nextBetween(4,7)/4d, (double) random.nextBetween(-8,8)/10d);
                scale.setCovetedItem();
                scale.setPickupDelay(80);
                world.spawnEntity(scale);
            }

            if (dragon.ticksSinceDeath == 200) {
                for (int i = 0; i < random.nextBetween(8, 16); i++) {
                    ItemEntity scale = new ItemEntity(world, dragonPos.getX(), dragonPos.getY(), dragonPos.getZ(), new ItemStack(UItems.DRAGON_SCALE), (double) random.nextBetween(-8,8)/10d, (double) random.nextBetween(4,7)/4d, (double) random.nextBetween(-8,8)/10d);
                    scale.setCovetedItem();
                    scale.setPickupDelay(80);
                    world.spawnEntity(scale);
                }

                /*
                for (int i = 0; i < random.nextBetween(32, 96); i++) {
                    ItemEntity flesh = new ItemEntity(world, dragonPos.getX(), dragonPos.getY(), dragonPos.getZ(), new ItemStack(UItems.DRAGON_FLESH), (double) random.nextBetween(-8,8)/10d, (double) random.nextBetween(-2,9)/4d, (double) random.nextBetween(-8,8)/10d);
                    flesh.setPickupDelay(80);
                    world.spawnEntity(flesh);
                }

                ItemEntity heart = new ItemEntity(world, dragonPos.getX(), dragonPos.getY(), dragonPos.getZ(), new ItemStack(UItems.DRAGON_HEART), (double) random.nextBetween(-8,8)/10d, (double) random.nextBetween(4,7)/4d, (double) random.nextBetween(-8,8)/10d);
                heart.setCovetedItem();
                heart.setPickupDelay(80);
                world.spawnEntity(heart);

                */
            }
        }
    }
}