/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package net.megal.uselessadditions.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.mojang.datafixers.util.Function4;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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

public class SmithingNoNbtRecipe implements SmithingRecipe {
    public final Ingredient template;
    public final Ingredient base;
    public final Ingredient addition;
    final ItemStack result;

    public SmithingNoNbtRecipe(Ingredient template, Ingredient base, Ingredient addition, ItemStack result) {
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
        return this.result.copy();
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
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
    public RecipeSerializer<?> getSerializer() {
        return URecipes.SMITHING_NO_NBT_RECIPE;
    }

    @Override
    public boolean isEmpty() {
        return Stream.of(this.template, this.base, this.addition).anyMatch(Ingredient::isEmpty);
    }

    public static class Serializer
    implements RecipeSerializer<SmithingNoNbtRecipe> {
        private static final Codec<SmithingNoNbtRecipe> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Ingredient.ALLOW_EMPTY_CODEC.fieldOf("template").forGetter(recipe -> recipe.template),
                        Ingredient.ALLOW_EMPTY_CODEC.fieldOf("base").forGetter(recipe -> recipe.base),
                        Ingredient.ALLOW_EMPTY_CODEC.fieldOf("addition").forGetter(recipe -> recipe.addition),
                        ItemStack.RECIPE_RESULT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
                )
                .apply(instance, SmithingNoNbtRecipe::new)
        );

        @Override
        public Codec<SmithingNoNbtRecipe> codec() {
            return CODEC;
        }

        @Override
        public SmithingNoNbtRecipe read(PacketByteBuf packetByteBuf) {
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient3 = Ingredient.fromPacket(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItemStack();
            return new SmithingNoNbtRecipe(ingredient, ingredient2, ingredient3, itemStack);
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

