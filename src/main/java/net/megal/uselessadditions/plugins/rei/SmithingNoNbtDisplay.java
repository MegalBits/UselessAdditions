package net.megal.uselessadditions.plugins.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.megal.uselessadditions.recipe.SmithingNoNbtRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class SmithingNoNbtDisplay extends BasicDisplay {
    public SmithingNoNbtDisplay(RecipeEntry<SmithingNoNbtRecipe> recipe) {
        this(recipe.value(),
                recipe.id(),
                List.of(
                EntryIngredients.ofIngredient(recipe.value().template),
                EntryIngredients.ofIngredient(recipe.value().base),
                EntryIngredients.ofIngredient(recipe.value().addition)
            )
        );
    }
    public SmithingNoNbtDisplay(SmithingRecipe recipe, @Nullable Identifier id, List<EntryIngredient> inputs) {
        this(
                inputs,
                List.of(EntryIngredients.of(recipe.getResult(BasicDisplay.registryAccess()))),
                Optional.ofNullable(id)
        );
    }
    public SmithingNoNbtDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<Identifier> location) {
        super(inputs, outputs, location);
    }
    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return UAddREIClientPlugin.SMITHING_NO_NBT;
    }
    public static Serializer<SmithingNoNbtDisplay> serializer() {
        return Serializer.ofSimple(SmithingNoNbtDisplay::new);
    }
}
