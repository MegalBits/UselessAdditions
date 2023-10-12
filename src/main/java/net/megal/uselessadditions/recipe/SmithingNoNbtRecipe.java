/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package net.megal.uselessadditions.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

import java.util.stream.Stream;

import static net.megal.uselessadditions.UAdd.autoSmeltItems;
import static net.megal.uselessadditions.UAdd.naturalMendingItems;

public class SmithingNoNbtRecipe implements SmithingRecipe {
    private final Identifier id;
    final Ingredient template;
    final Ingredient base;
    final Ingredient addition;
    final ItemStack result;

    public SmithingNoNbtRecipe(Identifier id, Ingredient template, Ingredient base, Ingredient addition, ItemStack result) {
        this.id = id;
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        return this.template.test(inventory.getStack(0)) && this.base.test(inventory.getStack(1)) && this.addition.test(inventory.getStack(2));
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        ItemStack stack = this.result.copy();
        if (naturalMendingItems.contains(stack.getItem())) {
            stack.addEnchantment(UEnchantments.NATURAL_MENDING, 1);
        }
        if (autoSmeltItems.contains(stack.getItem())) {
            stack.addEnchantment(UEnchantments.AUTO_SMELT, 1);
        }
        return stack;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return this.result;
    }

    @Override
    public boolean testTemplate(ItemStack stack) {
        return this.template.test(stack);
    }

    @Override
    public boolean testBase(ItemStack stack) {
        return this.base.test(stack);
    }

    @Override
    public boolean testAddition(ItemStack stack) {
        return this.addition.test(stack);
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializer.SMITHING_TRANSFORM;
    }

    @Override
    public boolean isEmpty() {
        return Stream.of(this.template, this.base, this.addition).anyMatch(Ingredient::isEmpty);
    }

    public static class Serializer
    implements RecipeSerializer<SmithingNoNbtRecipe> {
        @Override
        public SmithingNoNbtRecipe read(Identifier identifier, JsonObject jsonObject) {
            Ingredient ingredient = Ingredient.fromJson(JsonHelper.getElement(jsonObject, "template"));
            Ingredient ingredient2 = Ingredient.fromJson(JsonHelper.getElement(jsonObject, "base"));
            Ingredient ingredient3 = Ingredient.fromJson(JsonHelper.getElement(jsonObject, "addition"));
            ItemStack itemStack = ShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "result"));
            return new SmithingNoNbtRecipe(identifier, ingredient, ingredient2, ingredient3, itemStack);
        }

        @Override
        public SmithingNoNbtRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient3 = Ingredient.fromPacket(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItemStack();
            return new SmithingNoNbtRecipe(identifier, ingredient, ingredient2, ingredient3, itemStack);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, SmithingNoNbtRecipe smithingTransformRecipe) {
            smithingTransformRecipe.template.write(packetByteBuf);
            smithingTransformRecipe.base.write(packetByteBuf);
            smithingTransformRecipe.addition.write(packetByteBuf);
            packetByteBuf.writeItemStack(smithingTransformRecipe.result);
        }
    }
}

