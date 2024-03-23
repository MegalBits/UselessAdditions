package net.megal.mixin.item.modifier;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.megal.item.modifier.Modifiers;
import net.megal.inventory.DummyInventory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.Optional;

@Mixin(Block.class)
public abstract class SmeltDrops {
    @Unique
    private static final DummyInventory DUMMY = new DummyInventory();

    @ModifyReturnValue(
            at = @At("RETURN"),
            method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;"
    )
    private static List<ItemStack> smeltStacks(List<ItemStack> original, BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack stack) {
        if (!stack.isSuitableFor(state) || !Modifiers.getModifiersFromStack(stack).contains(Modifiers.AUTO_SMELTING)) return original;
        final List<ItemStack> smelted = original
                .stream()
                .map(drop -> getFurnaceOutput(drop, world))
                .flatMap(Optional::stream)
                .toList();
        if (!smelted.isEmpty()) return smelted;
        return original;
    }

    @Unique
    private static Optional<ItemStack> getFurnaceOutput(ItemStack stack, ServerWorld world) {
        DUMMY.setStack(0, stack);
        Optional<ItemStack> output = world
                .getRecipeManager()
                .listAllOfType(RecipeType.SMELTING).stream()
                .filter(recipe -> recipe.value().matches(DUMMY, world))
                .findFirst()
                .map(recipe -> recipe.value().getResult(world.getRegistryManager()).copy().getItem().getDefaultStack());
        output.ifPresent(itemStack -> itemStack.setCount(stack.getCount()));
        return output;
    }
}
