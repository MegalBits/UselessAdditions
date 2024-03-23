package net.megal.annotation;

import net.minecraft.recipe.book.RecipeCategory;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Recipes.class)
public @interface Recipe {
    RecipeCategory category() default RecipeCategory.MISC;
    RecipeType type();
    SimpleIngredient[] ingredients();
    int ticks() default 200;
    float xp() default 1f;
    boolean swapInputOutput() default false;

    public enum RecipeType {
        PACKING,
        PACKING_2X2,
        UNPACKING,
        UNPACKING_4,
        SWORD,
        SHOVEL,
        PICKAXE,
        AXE,
        HOE,
        SCYTHE,
        HAMMER,
        BOW,
        ARROW,
        SMELTING,
        SMOKING,
        BLASTING,
        SMITHING
    }
}
