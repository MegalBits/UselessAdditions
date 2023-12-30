package net.megal.uselessadditions.item;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum UToolMaterials implements ToolMaterial {
    WOODEN_HAMMER(0, 16, 1.5f, 0.0f, 12, () -> Ingredient.fromTag(ItemTags.PLANKS)),
    IRON_HAMMER(2, 64, 4.0f, 1.5f, 13, () -> Ingredient.ofItems(Items.IRON_INGOT)),
    AMETHYST_HAMMER(2, 4096, 6.5f, 2.5f, 32, () -> Ingredient.ofItems(UItems.MAGIC_INGOT)),
    AMETHYST(2, 832, 11.0F, 2.5F, 52, () -> Ingredient.ofItems(UItems.MAGIC_INGOT)),
    AMETHYST_P5(AMETHYST.miningLevel, AMETHYST.itemDurability, AMETHYST.miningSpeed, AMETHYST.attackDamage-.5f, AMETHYST.enchantability, () -> AMETHYST.repairIngredient),
    EMERALD(3, 625, 8.5F, 3.0F, 21, () -> Ingredient.ofItems(Items.EMERALD)),
    BLAZE_METAL(4, 1920, 8.5f, 3.5f, 15, () -> Ingredient.ofItems(UItems.BLAZE_METAL)),
    BLAZE_METAL_P5(BLAZE_METAL.miningLevel, BLAZE_METAL.itemDurability, BLAZE_METAL.miningSpeed, BLAZE_METAL.attackDamage-.5f, BLAZE_METAL.enchantability, () -> BLAZE_METAL.repairIngredient),
    DRAGON_SCALE(4, 5760, 10f, 4.5f, 7, () -> Ingredient.ofItems(UItems.DRAGON_SCALE)),
    DRAGON_SCALE_P5(DRAGON_SCALE.miningLevel, DRAGON_SCALE.itemDurability, DRAGON_SCALE.miningSpeed, DRAGON_SCALE.attackDamage-.5f, DRAGON_SCALE.enchantability, () -> DRAGON_SCALE.repairIngredient);

    //Copied from ToolMaterials
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
        return (Ingredient)this.repairIngredient.get();
    }
}
