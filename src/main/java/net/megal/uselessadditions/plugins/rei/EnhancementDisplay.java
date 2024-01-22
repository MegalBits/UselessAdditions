package net.megal.uselessadditions.plugins.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.megal.uselessadditions.recipe.EnhancementAugmentRecipe;
import net.megal.uselessadditions.recipe.EnhancementRecipe;
import net.megal.uselessadditions.recipe.SmithingNoNbtRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class EnhancementDisplay extends BasicDisplay {
    public EnhancementDisplay(RecipeEntry<EnhancementAugmentRecipe> recipe) {
        this(recipe.value(),
                recipe.id(),
                List.of(
                EntryIngredients.ofIngredient(recipe.value().template),
                EntryIngredients.ofIngredient(recipe.value().base),
                EntryIngredients.ofIngredient(recipe.value().addition)
            )
        );
    }
    public EnhancementDisplay(EnhancementRecipe recipe, @Nullable Identifier id, List<EntryIngredient> inputs) {
        this(
                inputs,
                List.of(EntryIngredients.of(recipe.getResult(BasicDisplay.registryAccess()))),
                Optional.ofNullable(id));
    }
    public EnhancementDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<Identifier> location) {
        super(inputs, outputs, location);
    }

//    @Override
//    public List<EntryIngredient> getOutputEntries() {
//        return inputs;
//    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return UAddREIClientPlugin.ENHANCEMENT;
    }
    public static Serializer<EnhancementDisplay> serializer() {
        return Serializer.ofSimple(EnhancementDisplay::new);
    }
}
