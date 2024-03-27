package net.megal.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.item.v1.CustomDamageHandler;
import net.fabricmc.fabric.api.item.v1.EquipmentSlotProvider;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.util.Rarity;

public class UItemSettings extends FabricItemSettings {
    public boolean glint = false;
    public String[] modifiers;

    public UItemSettings glint() {
        glint = true;
        return this;
    }

    public UItemSettings modifiers(String... modifiers) {
        this.modifiers = modifiers;
        return this;
    }

    // Overrides of fabric methods

    @Override
    public UItemSettings equipmentSlot(EquipmentSlotProvider equipmentSlotProvider) {
        super.equipmentSlot(equipmentSlotProvider);
        return this;
    }

    @Override
    public UItemSettings customDamage(CustomDamageHandler handler) {
        super.customDamage(handler);
        return this;
    }

    // Overrides of vanilla methods

    @Override
    public UItemSettings food(FoodComponent foodComponent) {
        super.food(foodComponent);
        return this;
    }

    @Override
    public UItemSettings maxCount(int maxCount) {
        super.maxCount(maxCount);
        return this;
    }

    @Override
    public UItemSettings maxDamageIfAbsent(int maxDamage) {
        super.maxDamageIfAbsent(maxDamage);
        return this;
    }

    @Override
    public UItemSettings maxDamage(int maxDamage) {
        super.maxDamage(maxDamage);
        return this;
    }

    @Override
    public UItemSettings recipeRemainder(Item recipeRemainder) {
        super.recipeRemainder(recipeRemainder);
        return this;
    }

    @Override
    public UItemSettings rarity(Rarity rarity) {
        super.rarity(rarity);
        return this;
    }

    @Override
    public UItemSettings fireproof() {
        super.fireproof();
        return this;
    }

    @Override
    public UItemSettings requires(FeatureFlag... features) {
        super.requires(features);
        return this;
    }
}
