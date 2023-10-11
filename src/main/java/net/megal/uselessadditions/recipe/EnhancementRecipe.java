/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package net.megal.uselessadditions.recipe;

import net.megal.uselessadditions.block.UBlocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;

public interface EnhancementRecipe extends Recipe<Inventory> {
    @Override
    default public RecipeType<?> getType() {
        return URecipes.ENHANCEMENT;
    }

    @Override
    default public boolean fits(int width, int height) {
        return width >= 3 && height >= 1;
    }

    @Override
    default public ItemStack createIcon() {
        return new ItemStack(UBlocks.ENHANCEMENT_TABLE);
    }

    public boolean testTemplate(ItemStack var1);

    public boolean testBase(ItemStack var1);

    public boolean testAddition(ItemStack var1);
}

