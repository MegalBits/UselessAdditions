package net.megal.uselessadditions.recipe;

import net.megal.uselessadditions.UAdd;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class URecipes {
    public static final RecipeType<EnhancementRecipe> ENHANCEMENT = registerType("enhancement");
    public static final RecipeSerializer<EnhancementAugmentRecipe> ENHANCMENT_AUGMENT_RECIPE = registerSerializer("enhancement_augment", new EnhancementAugmentRecipe.Serializer());
    public static final RecipeSerializer<SmithingNoNbtRecipe> SMITHING_NO_NBT_RECIPE = registerSerializer("smithing_no_nbt_transform", new SmithingNoNbtRecipe.Serializer());
    public static final RecipeSerializer<SpawnerRecipe> SPAWNER_RECIPE = registerSerializer("spawner_recipe", new SpecialRecipeSerializer<SpawnerRecipe>(SpawnerRecipe::new));
    public static <T extends Recipe<?>> RecipeType<T> registerType(final String id) {
        return Registry.register(Registries.RECIPE_TYPE, new Identifier(UAdd.MOD_ID, id), new RecipeType<T>(){
            @Override
            public String toString() {
                return id;
            }
        });
    }
    public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerSerializer(String id, S serializer) {
        return (S)Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(UAdd.MOD_ID, id), serializer);
    }
    public static void loadStuff() {}
}
