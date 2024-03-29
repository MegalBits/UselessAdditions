package net.megal.item;

import net.megal.UAdd;
import net.megal.UAddClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WarpPearl extends Item {
    public WarpPearl(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity serverPlayer) {
            stack.damage(1, user, p -> p.sendToolBreakStatus(user.getActiveHand()));
            serverPlayer.getItemCooldownManager().set(stack.getItem(), 600);
            teleportToPos(serverPlayer, stack);
        }

        return stack;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (user.isSneaking()) {
            writePosition(world, user.getBlockPos(), stack);
            user.getItemCooldownManager().set(stack.getItem(), 20);
            user.setCurrentHand(hand);
            return TypedActionResult.success(stack);
        }
        else if (stack.getOrCreateNbt().contains("StoredPos") && stack.getOrCreateNbt().contains("StoredDimension")) {
            if (!readDimension(user, stack)) {
                if (world.isClient()) {
                    UAddClient.setFeedback(stack, Text.translatable("uselessadditions.item.warp_pearl.wrong_dimension"), 60, Colors.LIGHT_RED);
                }
                return TypedActionResult.fail(stack);
            }
            else if (!readPosition(stack).isWithinDistance(user.getBlockPos(), 2500)) {
                if (world.isClient()) {
                    UAddClient.setFeedback(stack, Text.translatable("uselessadditions.item.warp_pearl.too_far"), 60, Colors.LIGHT_RED);
                }
                return TypedActionResult.fail(stack);
            }
            else {
                user.setCurrentHand(hand);
                return TypedActionResult.consume(stack);
            }
        }
        return super.use(world, user, hand);
    }

    private boolean teleportToPos(ServerPlayerEntity player, ItemStack stack) {
        BlockPos pos = readPosition(stack);
        player.requestTeleport(pos.getX()+.5, pos.getY(), pos.getZ()+.5);
        return true;
    }

    private BlockPos readPosition(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        return (new BlockPos(NbtHelper.toBlockPos(nbt.getCompound("StoredPos"))));
    }

    private boolean readDimension(PlayerEntity player, ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        return player.getWorld().getRegistryKey().equals(RegistryKey.of(RegistryKeys.WORLD, new Identifier(nbt.getString("StoredDimension"))));
    }

    private void writePosition(World world, BlockPos pos, ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.put("StoredPos", NbtHelper.fromBlockPos(pos));
        nbt.putString("StoredDimension", world.getRegistryKey().getValue().toString());
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 300;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }
}
