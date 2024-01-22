/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package net.megal.uselessadditions.screen;

import net.megal.uselessadditions.block.UBlocks;
import net.megal.uselessadditions.item.DamageableItem;
import net.megal.uselessadditions.recipe.EnhancementRecipe;
import net.megal.uselessadditions.recipe.URecipes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.ForgingSlotsManager;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

import static net.megal.uselessadditions.UAdd.HAMMERS;

public class EnhancementScreenHandler extends ForgingScreenHandler {
    private final World world;
    @Nullable
    private RecipeEntry<EnhancementRecipe> currentRecipe;
    private final List<RecipeEntry<EnhancementRecipe>> recipes;

    public EnhancementScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public EnhancementScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(UScreens.ENHANCEMENT_SCREEN_HANDLER, syncId, playerInventory, context);
        this.world = playerInventory.player.getWorld();
        this.recipes = this.world.getRecipeManager().listAllOfType(URecipes.ENHANCEMENT);
    }

    @Override
    protected ForgingSlotsManager getForgingSlotsManager() {
        return ForgingSlotsManager.create()
                .input(0, 8, 48, stack -> this.recipes.stream().anyMatch(recipe -> recipe.value().testTemplate(stack)))
                .input(1, 26, 48, stack -> this.recipes.stream().anyMatch(recipe -> recipe.value().testBase(stack)))
                .input(2, 44, 48, stack -> this.recipes.stream().anyMatch(recipe -> recipe.value().testAddition(stack)))
                .output(3, 98, 48)
                .build();
    }

    @Override
    protected boolean canUse(BlockState state) {
        return state.isOf(UBlocks.ENHANCEMENT_TABLE);
    }

    @Override
    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return this.currentRecipe != null && this.currentRecipe.value().matches(this.input, this.world);
    }

    @Override
    protected void onTakeOutput(PlayerEntity player, ItemStack stack) {
        stack.onCraftByPlayer(player.getWorld(), player, stack.getCount());
        this.output.unlockLastRecipe(player, this.getInputStacks());
        this.decrementStack(0);
        this.decrementStack(1);
        this.decrementStack(2);
        this.context.run((world, pos) -> world.syncWorldEvent(WorldEvents.SMITHING_TABLE_USED, pos, 0));
    }

    private List<ItemStack> getInputStacks() {
        return List.of(this.input.getStack(0), this.input.getStack(1), this.input.getStack(2));
    }

    private void decrementStack(int slot) {
        if (slot == 0) {
            ItemStack stack = input.getStack(slot);
            if((stack.getItem() instanceof DamageableItem || (stack.isIn(HAMMERS) && stack.isDamageable())) && stack.getDamage() <= stack.getMaxDamage() - 2) {
                stack.setDamage(stack.getDamage() + 1);
                return;
            }
        }
        ItemStack itemStack = this.input.getStack(slot);
        if (!itemStack.isEmpty()) {
            itemStack.decrement(1);
            this.input.setStack(slot, itemStack);
        }
    }

    @Override
    public void updateResult() {
        List<RecipeEntry<EnhancementRecipe>> list = this.world.getRecipeManager().getAllMatches(URecipes.ENHANCEMENT, this.input, this.world);
        if (list.isEmpty()) {
            this.output.setStack(0, ItemStack.EMPTY);
        } else {
            RecipeEntry<EnhancementRecipe> enhancementRecipe = list.get(0);
            ItemStack itemStack = enhancementRecipe.value().craft(this.input, this.world.getRegistryManager());
            if (itemStack.isItemEnabled(this.world.getEnabledFeatures())) {
                this.currentRecipe = enhancementRecipe;
                this.output.setLastRecipe(enhancementRecipe);
                this.output.setStack(0, itemStack);
            }
        }
    }

    @Override
    public int getSlotFor(ItemStack stack) {
        return this.recipes.stream().map(recipe -> EnhancementScreenHandler.getQuickMoveSlot(recipe.value(), stack)).filter(Optional::isPresent).findFirst().orElse(Optional.of(0)).get();
    }

    private static Optional<Integer> getQuickMoveSlot(EnhancementRecipe recipe, ItemStack stack) {
        if (recipe.testTemplate(stack)) {
            return Optional.of(0);
        }
        if (recipe.testBase(stack)) {
            return Optional.of(1);
        }
        if (recipe.testAddition(stack)) {
            return Optional.of(2);
        }
        return Optional.empty();
    }

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.inventory != this.output && super.canInsertIntoSlot(stack, slot);
    }

    @Override
    public boolean isValidIngredient(ItemStack stack) {
        return this.recipes.stream().map(recipe -> EnhancementScreenHandler.getQuickMoveSlot(recipe.value(), stack)).anyMatch(Optional::isPresent);
    }
}

