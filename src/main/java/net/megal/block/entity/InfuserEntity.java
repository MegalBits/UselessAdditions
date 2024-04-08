package net.megal.block.entity;

import net.megal.item.EssenceItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.RecipeUnlocker;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class InfuserEntity extends LockableContainerBlockEntity implements SidedInventory, RecipeUnlocker, RecipeInputProvider {
    private static final int[] TOP_SLOT = new int[]{0};
    private static final int[] SIDE_SLOTS = new int[]{1, 2, 3};
    private static final int[] BOTTOM_SLOT = new int[]{4};

    public InfuserEntity(BlockPos pos, BlockState state) {
        super(UBlockEntities.INFUSER, pos, state);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("uselessadditions.container.infuser");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        //Todo: add a screen for this
        return null;
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return switch (side) {
            case UP -> TOP_SLOT;
            case DOWN -> BOTTOM_SLOT;
            case NORTH, EAST, SOUTH, WEST -> SIDE_SLOTS;
        };
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return this.isValid(slot, stack);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStack(int slot) {
        return null;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return null;
    }

    @Override
    public ItemStack removeStack(int slot) {
        return null;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return false;
    }

    @Override
    public void provideRecipeInputs(RecipeMatcher finder) {

    }

    @Override
    public void setLastRecipe(@Nullable RecipeEntry<?> recipe) {

    }

    @Nullable
    @Override
    public RecipeEntry<?> getLastRecipe() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == 4) return false;
        if (slot == 0 && stack.getItem() instanceof EssenceItem) return true;
        return slot == 1 || slot == 2 || slot == 3;
    }
}
