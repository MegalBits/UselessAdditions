package net.megal.item;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class UHoeItem extends HoeItem {
    public UHoeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(setZeroDamage(material), 0, attackSpeed, settings);
    }

    private static ToolMaterial setZeroDamage(ToolMaterial material) {
        return new ToolMaterial() {
            @Override
            public int getDurability() {
                return material.getDurability();
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return material.getMiningSpeedMultiplier();
            }

            @Override
            public float getAttackDamage() {
                return 0;
            }

            @Override
            public int getMiningLevel() {
                return material.getMiningLevel();
            }

            @Override
            public int getEnchantability() {
                return material.getEnchantability();
            }

            @Override
            public Ingredient getRepairIngredient() {
                return material.getRepairIngredient();
            }
        };
    }
}
