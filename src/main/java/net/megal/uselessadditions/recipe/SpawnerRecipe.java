/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package net.megal.uselessadditions.recipe;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.block.UBlocks;
import net.megal.uselessadditions.item.SpawnEgg;
import net.megal.uselessadditions.item.UItems;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class SpawnerRecipe extends SpecialCraftingRecipe {
    private static final Ingredient SPAWNER = Ingredient.ofItems(UBlocks.EMPTY_SPAWNER.asItem());

    public SpawnerRecipe(CraftingRecipeCategory craftingRecipeCategory) {
        super(craftingRecipeCategory);
    }

    @Override
    public boolean matches(RecipeInputInventory recipeInputInventory, World world) {
        ItemStack egg = new ItemStack(UItems.ZOMBIE_SPAWN_EGG);
        for (int i = 0; i < recipeInputInventory.size(); ++i) {
            ItemStack stack = recipeInputInventory.getStack(i);
            if (i == 0 && stack.isIn(UAdd.MOB_EGGS)) egg = stack;
            if (i == 4 && !SPAWNER.test(stack)) return false;
            if (i != 4 && stack.getItem() != egg.getItem()) return false;
        }
        return true;
    }

    @Override
    public ItemStack craft(RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager) {
        ItemStack stack = new ItemStack(UBlocks.SURVIVAL_SPAWNER.asItem());
        NbtCompound nbt = stack.getOrCreateNbt();
        if (recipeInputInventory.getStack(0).getItem() instanceof SpawnEgg egg && egg.getEntityType() != null) {
            nbt.putString("EntityStored", Registries.ENTITY_TYPE.getId(egg.getEntityType()).toString());
        }
        return stack;
    }
    @Override
    public boolean fits(int width, int height) {
        return width == 3 && height == 3;
    }
    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return new ItemStack(UBlocks.SURVIVAL_SPAWNER.asItem());
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return URecipes.SPAWNER_RECIPE;
    }
}

