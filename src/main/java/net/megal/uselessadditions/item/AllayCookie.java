package net.megal.uselessadditions.item;

import net.megal.uselessadditions.UAdd;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class AllayCookie extends TooltipItem {
    public AllayCookie(Settings settings, Formatting formatting) {
        super(settings, formatting);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity player) {
            if (!world.isClient) {
                MinecraftServer server = world.getServer();
                ServerWorld spawnWorld = server.getWorld(player.getSpawnPointDimension());
                BlockPos spawnPoint = player.getSpawnPointPosition();
                if (spawnPoint == null) spawnPoint = world.getSpawnPos();
                if (spawnWorld == null) spawnWorld = (ServerWorld) world;
                player.teleport(spawnWorld, spawnPoint.getX() + 0.5d, spawnPoint.getY(), spawnPoint.getZ() + 0.5d, player.getYaw(), player.getPitch());
            }
            player.getItemCooldownManager().set(stack.getItem(), 300);
        }
        return super.finishUsing(stack, world, user);
    }
}
