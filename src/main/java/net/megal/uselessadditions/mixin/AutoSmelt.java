package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.DummyInventory;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Mixin(Block.class)
public abstract class AutoSmelt {
    private static final DummyInventory DUMMY = new DummyInventory();
    @Inject(at = @At("RETURN"),
            method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;",
            cancellable = true)
    private static void smeltStacks(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack stack, CallbackInfoReturnable<List<ItemStack>> cir) {
        if (!stack.isSuitableFor(state) || !(EnchantmentHelper.getLevel(UEnchantments.AUTO_SMELT, stack) > 0 || UItemHelper.getEffects(stack).contains(SpecialEffects.AUTO_SMELT))) {
            return;
        }
        final List<ItemStack> drops = cir.getReturnValue();
        final List<ItemStack> smelted = drops
                .stream()
                .map(drop -> getFurnaceOutput(drop, world))
                .flatMap(Optional::stream)
                .toList();
        if(!smelted.isEmpty()) cir.setReturnValue(smelted);
    }
    private static Optional<ItemStack> getFurnaceOutput(ItemStack stack, ServerWorld world) {
        DUMMY.setStack(0, stack);
        return world
                .getRecipeManager()
                .listAllOfType(RecipeType.SMELTING).stream()
                .filter(recipe -> recipe.matches(DUMMY, world))
                .findFirst()
                .map(recipe -> recipe.getOutput(world.getRegistryManager()).copy());
    }
}
