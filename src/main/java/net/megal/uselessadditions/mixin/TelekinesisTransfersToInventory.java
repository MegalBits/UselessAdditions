package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.enchantment.UEnchantment;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(Block.class)
public class TelekinesisTransfersToInventory {
    @ModifyArg(at = @At(
            value = "INVOKE",
            target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V"
    ),
            method = "dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V",
            index = 0)
    private static Consumer<ItemStack> giveAndCancelDrop(Consumer<ItemStack> consumer, @Local(argsOnly = true) World world, @Local(argsOnly = true) BlockState state, @Local(argsOnly = true) Entity entity, @Local(argsOnly = true) ItemStack stack) {
        if (EnchantmentHelper.getLevel(UEnchantments.TELEKINESIS, stack) > 0 && stack.isSuitableFor(state)) {
            return (item) -> world.spawnEntity(new ItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), item));
        }
        return consumer;
    }
}
