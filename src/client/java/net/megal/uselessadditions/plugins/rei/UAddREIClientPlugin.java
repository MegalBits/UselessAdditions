package net.megal.uselessadditions.plugins.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.block.UBlocks;
import net.megal.uselessadditions.item.UItems;
import net.megal.uselessadditions.recipe.EnhancementAugmentRecipe;
import net.megal.uselessadditions.recipe.SmithingNoNbtRecipe;
import net.megal.uselessadditions.recipe.URecipes;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeType;

public class UAddREIClientPlugin implements REIClientPlugin {
    public static final CategoryIdentifier<SmithingNoNbtDisplay> SMITHING_NO_NBT = CategoryIdentifier.of(UAdd.MOD_ID, "smithing_no_nbt");
    public static final CategoryIdentifier<EnhancementDisplay> ENHANCEMENT = CategoryIdentifier.of(UAdd.MOD_ID, "enhancement");
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new SmithingNoNbtCategory());
        registry.addWorkstations(SMITHING_NO_NBT, EntryStacks.of(Items.SMITHING_TABLE));

        registry.add(new EnhancementCategory());
        registry.addWorkstations(ENHANCEMENT, EntryStacks.of(UBlocks.ENHANCEMENT_TABLE));
    }
    //DefaultClientPlugin
    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(SmithingNoNbtRecipe.class, RecipeType.SMITHING, SmithingNoNbtDisplay::new);
        registry.registerFiller(EnhancementAugmentRecipe.class, EnhancementDisplay::new);
    }
}
