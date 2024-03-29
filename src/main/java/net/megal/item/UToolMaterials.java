package net.megal.item;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum UToolMaterials implements ToolMaterial {
    AMETHYST(2, 832, 11.0F, 2.5F, 52, () -> Ingredient.ofItems(UItems.MAGIC_INGOT)),
    EMERALD(2, 625, 8.5F, 3.0F, 21, () -> Ingredient.ofItems(Items.EMERALD)),
    BLAZE_METAL(3, 1920, 8.5f, 3.5f, 15, () -> Ingredient.ofItems(UItems.BLAZE_METAL)),
    DRAGON_SCALE(4, 5760, 10f, 4.5f, 7, () -> Ingredient.ofItems(UItems.DRAGON_SCALE)),
    ANCIENT(4, 4608, 11f, 6f, 2, () -> Ingredient.ofItems(UItems.ANCIENT_ALLOY));
    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    UToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy(repairIngredient);
    }

    public int getDurability() {
        return this.itemDurability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public int getMiningLevel() {
        return this.miningLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
