package net.megal.uselessadditions.item;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WarpPearl extends Item {
    public WarpPearl(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbt = stack.getOrCreateNbt();
        if (nbt.contains("StoredPos") && nbt.contains("StoredDimension")) tooltip.add(Text.translatable("item.uselessadditions.warp_pearl.teleport").append(Text.literal(nbt.get("StoredPos").asString()).formatted(Formatting.GREEN).append(Text.translatable("item.uselessadditions.warp_pearl.dimension").formatted(Formatting.GRAY).append(Text.literal(nbt.getString("StoredDimension")).formatted(Formatting.GREEN)))).formatted(Formatting.GRAY));
        else tooltip.add(Text.translatable("item.uselessadditions.warp_pearl.none").formatted(Formatting.GRAY));
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        Random random = Random.create();
        boolean doTeleportVisuals = !user.isSneaking() && stack.getOrCreateNbt().contains("StoredPos") && stack.getOrCreateNbt().contains("StoredDimension");
        int ticks = this.getMaxUseTime(stack) - remainingUseTicks;
        float f = getUseProgress(ticks);
        if (f >= 10.0f) {
            if (doTeleportVisuals) {
                for (int i = 0; i < 32; ++i) {
                    world.addParticle(ParticleTypes.PORTAL, user.getX()+random.nextDouble()-.5f, user.getY()+random.nextDouble()*2.0, user.getZ()+random.nextDouble()-.5f, 0f, 0f, 0f);
                }
            }
            if (user instanceof ServerPlayerEntity player) {
                boolean teleport = false;
                if (!player.isSneaking()) teleport = teleportToPos(world, player, stack);
                if (!teleport) return;
                if (!world.isClient) {
                    player.getItemCooldownManager().set(stack.getItem(), 600);
                    stack.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
                    world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS);
                }
            }
            if (doTeleportVisuals) {
                BlockPos particlePos = NbtHelper.toBlockPos(stack.getOrCreateNbt().getCompound("StoredPos"));
                for (int i = 0; i < 32; ++i) {
                    world.addParticle(ParticleTypes.PORTAL, particlePos.getX()+.5f, particlePos.getY() + random.nextDouble() * 2.0, particlePos.getZ()+.5f, random.nextGaussian(), 0.0, random.nextGaussian());
                }
            }
        }
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        Random random = Random.create();
        int ticks = this.getMaxUseTime(stack) - remainingUseTicks;
        float f = getUseProgress(ticks);
        if (f >= 10.0f) {
            world.addParticle(ParticleTypes.REVERSE_PORTAL, user.getX()+random.nextDouble()-.5f, user.getY()+random.nextDouble()*2.0, user.getZ()+random.nextDouble()-.5f, random.nextGaussian()/3f, 0f, random.nextGaussian()/3f);
        }
        else {
            world.addParticle(ParticleTypes.PORTAL, user.getX()+random.nextDouble()-.5f, user.getY()+random.nextDouble()*2.0, user.getZ()+random.nextDouble()-.5f, 0f, 0f, 0f);
        }
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
            user.setCurrentHand(hand);
            return TypedActionResult.consume(stack);
        }
        return super.use(world, user, hand);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    public static float getUseProgress(int useTicks) {
        float f = (float)useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        return f;
    }

    private boolean teleportToPos(World world, ServerPlayerEntity player, ItemStack stack) {
        @Nullable Pair<BlockPos, ServerWorld> position = readPosition(world.getServer(), stack);
        if (position == null) return false;
        BlockPos pos = position.getLeft();
        if (player.getServerWorld() != position.getRight()) player.teleport(position.getRight(), pos.getX()+.5, pos.getY(), pos.getZ()+.5, player.getYaw(), player.getPitch());
        else player.requestTeleport(pos.getX()+.5, pos.getY(), pos.getZ()+.5);
        return true;
    }

    private @Nullable Pair<BlockPos, ServerWorld> readPosition(MinecraftServer server, ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.contains("StoredPos") || nbt.get("StoredPos").getType() != NbtElement.COMPOUND_TYPE || !nbt.contains("StoredDimension")) return null;
        return new Pair<>(new BlockPos(NbtHelper.toBlockPos(nbt.getCompound("StoredPos"))), server.getWorld(RegistryKey.of(RegistryKeys.WORLD, new Identifier(nbt.getString("StoredDimension")))));
    }

    private void writePosition(World world, BlockPos pos, ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.put("StoredPos", NbtHelper.fromBlockPos(pos));
        nbt.putString("StoredDimension", world.getRegistryKey().getValue().toString());
    }
}