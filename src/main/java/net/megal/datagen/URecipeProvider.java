package net.megal.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.megal.item.UItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class URecipeProvider extends FabricRecipeProvider {
    private RecipeExporter recipeExporter;

    public URecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        this.recipeExporter = recipeExporter;

        registerPackingUnpacking3(UItems.RAW_COPPER_NUGGET, Items.RAW_COPPER);
        registerPackingUnpacking3(UItems.RAW_IRON_NUGGET, Items.RAW_IRON);
        registerPackingUnpacking3(UItems.RAW_GOLD_NUGGET, Items.RAW_GOLD);
        registerPackingUnpacking3(UItems.COPPER_NUGGET, Items.COPPER_INGOT);
        registerPackingUnpacking3(UItems.AMETHYST_ALLOY_NUGGET, UItems.AMETHYST_ALLOY);
        registerPackingUnpacking3(UItems.EMERALD_NUGGET, Items.EMERALD);
        registerPackingUnpacking3(UItems.LAPIS_LAZULI_NUGGET, Items.LAPIS_LAZULI);
        registerPackingUnpacking3(UItems.DIAMOND_NUGGET, Items.DIAMOND);
        registerPackingUnpacking3(UItems.NETHERITE_NUGGET, Items.NETHERITE_INGOT);

        registerBlastingAndSmelting(UItems.COPPER_NUGGET, ui(UItems.RAW_COPPER_NUGGET), 0.075f, 30);
        registerBlastingAndSmelting(Items.IRON_NUGGET, ui(UItems.RAW_IRON_NUGGET), 0.075f, 30);
        registerBlastingAndSmelting(Items.GOLD_NUGGET, ui(UItems.RAW_GOLD_NUGGET), 0.075f, 30);

        UnbuiltIngredient amethyst = ui(Items.AMETHYST_SHARD);
        registerShapelessRecipe(UItems.AMETHYST_ALLOY, array(
                amethyst,
                amethyst,
                amethyst,
                ui(Items.GOLD_INGOT),
                amethyst,
                amethyst,
                ui(Items.GOLD_NUGGET),
                ui(Items.GOLD_NUGGET)
        ), 1, RecipeCategory.MISC);
        UnbuiltIngredient amethyst_alloy = ui(UItems.AMETHYST_ALLOY);
        registerShapedRecipe(UItems.AMETHYST_CORE, array(amethyst_alloy, amethyst, ui(Items.DIAMOND)), 1, new DefinablePattern("010","120","001"), RecipeCategory.MISC);

        UnbuiltIngredient stick = ui(Items.STICK);
        registerToolset(UItems.AMETHYST_SWORD, UItems.AMETHYST_SHOVEL, UItems.AMETHYST_PICKAXE, UItems.AMETHYST_AXE, UItems.AMETHYST_HOE, stick, amethyst_alloy);

        registerShapedRecipe(UItems.AMETHYST_HEART, array(amethyst_alloy, ui(UItems.AMETHYST_ALLOY_NUGGET), ui(UItems.AMETHYST_CORE)), 1, new DefinablePattern("010","020"," 0 "), RecipeCategory.MISC);
    }
    
    private void registerPackingUnpacking3(Item smallItem, Item bigItem) {
        registerShapedRecipe(bigItem, array(ui(smallItem)), 1, PredefinedPattern.x3, RecipeCategory.MISC, "packing");
        registerShapelessRecipe(smallItem, array(ui(bigItem)), 9, RecipeCategory.MISC, "unpacking");
    }

    private void registerPackingUnpacking2(Item smallItem, Item bigItem) {
        registerShapedRecipe(bigItem, array(ui(smallItem)), 1, PredefinedPattern.x2, RecipeCategory.MISC, "packing");
        registerShapelessRecipe(smallItem, array(ui(bigItem)), 4, RecipeCategory.MISC, "unpacking");
    }

    private void registerToolset(Item sword, Item shovel, Item pickaxe, Item axe, Item hoe, UnbuiltIngredient stick, UnbuiltIngredient material) {
        registerShapedRecipe(sword, array(stick, material), 1, PredefinedPattern.SWORD, RecipeCategory.COMBAT);
        registerShapedRecipe(shovel, array(stick, material), 1, PredefinedPattern.SHOVEL, RecipeCategory.TOOLS);
        registerShapedRecipe(pickaxe, array(stick, material), 1, PredefinedPattern.PICKAXE, RecipeCategory.TOOLS);
        registerShapedRecipe(axe, array(stick, material), 1, PredefinedPattern.AXE, RecipeCategory.TOOLS);
        registerShapedRecipe(hoe, array(stick, material), 1, PredefinedPattern.HOE, RecipeCategory.TOOLS);
    }

    private void registerBlastingAndSmelting(Item smeltedItem, UnbuiltIngredient input, float xp, int ticks) {
        registerSmeltingRecipe(smeltedItem, input, xp, ticks, RecipeCategory.MISC, "");
        registerBlastingRecipe(smeltedItem, input, xp, ticks/2, RecipeCategory.MISC, "");
    }

    private void registerSmokingAndSmelting(Item smeltedItem, UnbuiltIngredient input, float xp, int ticks) {
        registerSmeltingRecipe(smeltedItem, input, xp, ticks, RecipeCategory.FOOD, "");
        registerSmokingRecipe(smeltedItem, input, xp, ticks/2, "");
    }

    private void registerShapedRecipe(Item craftedItem, UnbuiltIngredient[] ingredients, int amount, Pattern pattern, RecipeCategory recipeCategory, String suffix) {
        ShapedRecipeJsonBuilder recipe = ShapedRecipeJsonBuilder.create(recipeCategory, craftedItem, amount);
        Arrays.stream(pattern.getPattern()).forEach(recipe::pattern);

        int i = 0;
        for (UnbuiltIngredient ingredient : ingredients) {
            recipe.input((char)(i+'0'), ingredient.build());
            craftingCriterionFromUnbuiltIngredient(recipe, ingredient);
            i++;
        }

        if (suffix.isEmpty()) recipe.offerTo(recipeExporter);
        else recipe.offerTo(recipeExporter, getItemPath(craftedItem) + "_" + suffix);
    }

    private void registerShapedRecipe(Item craftedItem, UnbuiltIngredient[] ingredients, int amount, Pattern pattern, RecipeCategory recipeCategory) {
        registerShapedRecipe(craftedItem, ingredients, amount, pattern, recipeCategory, "");
    }

    private void registerShapelessRecipe(Item craftedItem, UnbuiltIngredient[] ingredients, int amount, RecipeCategory recipeCategory, String suffix) {
        ShapelessRecipeJsonBuilder recipe = ShapelessRecipeJsonBuilder.create(recipeCategory, craftedItem, amount);
        for (UnbuiltIngredient ingredient : ingredients) {
            recipe.input(ingredient.build());
            craftingCriterionFromUnbuiltIngredient(recipe, ingredient);
        }

        if (suffix.isEmpty()) recipe.offerTo(recipeExporter);
        else recipe.offerTo(recipeExporter, getItemPath(craftedItem) + "_" + suffix);
    }

    private void registerShapelessRecipe(Item craftedItem, UnbuiltIngredient[] ingredients, int amount, RecipeCategory recipeCategory) {
        registerShapelessRecipe(craftedItem, ingredients, amount, recipeCategory, "");
    }

    private void registerSmeltingRecipe(Item craftedItem, UnbuiltIngredient ingredient, float xp, int ticks, RecipeCategory recipeCategory, String suffix) {
        CookingRecipeJsonBuilder recipe = CookingRecipeJsonBuilder.createSmelting(ingredient.build(), recipeCategory, craftedItem, xp, ticks);
        cookingCriterionFromUnbuiltIngredient(recipe, ingredient);

        if (suffix.isEmpty()) recipe.offerTo(recipeExporter, getItemPath(craftedItem) + "_from_smoking");
        else recipe.offerTo(recipeExporter, getItemPath(craftedItem) + "_from_smoking_" + suffix);
    }

    private void registerBlastingRecipe(Item craftedItem, UnbuiltIngredient ingredient, float xp, int ticks, RecipeCategory recipeCategory, String suffix) {
        CookingRecipeJsonBuilder recipe = CookingRecipeJsonBuilder.createBlasting(ingredient.build(), recipeCategory, craftedItem, xp, ticks);
        cookingCriterionFromUnbuiltIngredient(recipe, ingredient);

        if (suffix.isEmpty()) recipe.offerTo(recipeExporter, getItemPath(craftedItem) + "_from_blasting");
        else recipe.offerTo(recipeExporter, getItemPath(craftedItem) + "_from_blasting_" + suffix);
    }

    private void registerSmokingRecipe(Item craftedItem, UnbuiltIngredient ingredient, float xp, int ticks, String suffix) {
        CookingRecipeJsonBuilder recipe = CookingRecipeJsonBuilder.createBlasting(ingredient.build(), RecipeCategory.FOOD, craftedItem, xp, ticks);
        cookingCriterionFromUnbuiltIngredient(recipe, ingredient);

        if (suffix.isEmpty()) recipe.offerTo(recipeExporter);
        else recipe.offerTo(recipeExporter, getItemPath(craftedItem) + "_" + suffix);
    }

    private void registerSmithingRecipe(Item craftedItem, UnbuiltIngredient template, UnbuiltIngredient base, UnbuiltIngredient addition, RecipeCategory recipeCategory, String suffix) {
        SmithingTransformRecipeJsonBuilder recipe = SmithingTransformRecipeJsonBuilder.create(template.build(), base.build(), addition.build(), recipeCategory, craftedItem);
        smithingCriterionFromUnbuiltIngredient(recipe, template);
        smithingCriterionFromUnbuiltIngredient(recipe, base);
        smithingCriterionFromUnbuiltIngredient(recipe, addition);

        if (suffix.isEmpty()) recipe.offerTo(recipeExporter, getItemPath(craftedItem) + "_smithing");
        else recipe.offerTo(recipeExporter, getItemPath(craftedItem) + "_smithing_" + suffix);
    }

    private void craftingCriterionFromUnbuiltIngredient(CraftingRecipeJsonBuilder recipe, UnbuiltIngredient ingredient) {
        if (ingredient.isTag()) {
            recipe.criterion("has_" + ingredient.tag.id().getPath(), conditionsFromTag(ingredient.tag));
        }
        else {
            Arrays.stream(ingredient.items).forEach(item -> {
                recipe.criterion(hasItem(item), conditionsFromItem(item));
            });
        }
    }

    private void cookingCriterionFromUnbuiltIngredient(CookingRecipeJsonBuilder recipe, UnbuiltIngredient ingredient) {
        if (ingredient.isTag()) {
            recipe.criterion("has_" + ingredient.tag.id().getPath(), conditionsFromTag(ingredient.tag));
        }
        else {
            Arrays.stream(ingredient.items).forEach(item -> {
                recipe.criterion(hasItem(item), conditionsFromItem(item));
            });
        }
    }

    private void smithingCriterionFromUnbuiltIngredient(SmithingTransformRecipeJsonBuilder recipe, UnbuiltIngredient ingredient) {
        if (ingredient.isTag()) {
            recipe.criterion("has_" + ingredient.tag.id().getPath(), conditionsFromTag(ingredient.tag));
        }
        else {
            Arrays.stream(ingredient.items).forEach(item -> {
                recipe.criterion(hasItem(item), conditionsFromItem(item));
            });
        }
    }

    private static class UnbuiltIngredient {
        @Nullable
        public Item[] items;
        @Nullable
        public final TagKey<Item> tag;

        private UnbuiltIngredient(@Nullable Item[] items, @Nullable TagKey<Item> tag) {
            this.items = items;
            this.tag = tag;
        }

        public UnbuiltIngredient(@NotNull Item... items) {
            this(items, null);
        }

        public UnbuiltIngredient(@NotNull TagKey<Item> tag) {
            this(null, tag);
        }

        public boolean isTag() {
            return tag != null;
        }

        public Ingredient build() {
            return isTag() ? Ingredient.fromTag(tag) : Ingredient.ofItems(items);
        }
    }

    private static UnbuiltIngredient ui(Item... items) {
        return new UnbuiltIngredient(items);
    }
    private static UnbuiltIngredient ui(TagKey<Item> tag) {
        return new UnbuiltIngredient(tag);
    }

    private static UnbuiltIngredient[] array(UnbuiltIngredient... ingredients) {
        return ingredients;
    }
    
    private interface Pattern {
        String[] getPattern();
    }

    public static class DefinablePattern implements Pattern {
        private final String[] pattern;

        public DefinablePattern(String... pattern) {
            this.pattern = pattern;
        }

        @Override
        public String[] getPattern() {
            return pattern;
        }
    }

    public enum PredefinedPattern implements Pattern {
        x2("00","00"),
        x3("000","000","000"),
        SLAB("000"),
        STAIRS("0  ","00 ","000"),
        FENCE("010","010"),
        WALL("000","000"),
        DOOR("00","00","00"),
        SWORD("1","1","0"),
        SHOVEL("1","0","0"),
        PICKAXE("111"," 0 "," 0 "),
        AXE("11","10"," 0"),
        HOE("11"," 0"," 0");

        private final String[] pattern;

        PredefinedPattern(String... pattern) {
            this.pattern = pattern;
        }

        @Override
        public String[] getPattern() {
            return pattern;
        }
    }
}
