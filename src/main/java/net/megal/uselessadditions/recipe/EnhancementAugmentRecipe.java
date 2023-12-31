/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package net.megal.uselessadditions.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import net.megal.uselessadditions.item.UItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.stream.Stream;

public class EnhancementAugmentRecipe implements EnhancementRecipe {
    private final Identifier id;
    public final Ingredient template;
    public final Ingredient base;
    public final Ingredient addition;
    final Identifier modifier;

    public EnhancementAugmentRecipe(Identifier id, Ingredient template, Ingredient base, Ingredient addition, Identifier modifier) {
        this.id = id;
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.modifier = modifier;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        ItemStack stack = inventory.getStack(1);

        return this.template.test(inventory.getStack(0)) && this.base.test(inventory.getStack(1)) && this.addition.test(inventory.getStack(2));
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        ItemStack stack = new ItemStack(inventory.getStack(1).getItem());
        NbtCompound nbtCompound = inventory.getStack(1).getNbt();
        if (nbtCompound != null) stack.setNbt(nbtCompound.copy());
        @Nullable Enchantment enchantment = Registries.ENCHANTMENT.get(modifier);
        if (enchantment != null) {
            int level = EnchantmentHelper.getLevel(enchantment, stack);
            Map<Enchantment, Integer> map = EnchantmentHelper.get(stack);
            if (level <= 0) stack.addEnchantment(enchantment, 1);
            else if (level < enchantment.getMaxLevel()) {
                map.replace(enchantment, EnchantmentHelper.getLevel(enchantment, stack) + 1);
                EnchantmentHelper.set(map, stack);
            }
        }
        return stack;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        ItemStack stack = new ItemStack(UItems.AUGMENT);
        @Nullable Enchantment enchantment = Registries.ENCHANTMENT.get(modifier);
        if (enchantment != null) stack.addEnchantment(enchantment, enchantment.getMaxLevel());
        //stack.setCustomName(Text.translatable("item.uselessadditions.augment_book").formatted(Formatting.WHITE));
        return stack;
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
        return URecipes.ENHANCMENT_AUGMENT_RECIPE;
    }

    @Override
    public boolean isEmpty() {
        return Stream.of(this.template, this.base, this.addition).anyMatch(Ingredient::isEmpty);
    }

    public static Identifier outputFromJson(JsonObject json) {
        Identifier id = EnhancementAugmentRecipe.getIdentifier(json);
        if (json.has("data")) {
            throw new JsonParseException("Disallowed data tag found");
        }
        int i = JsonHelper.getInt(json, "count", 1);
        if (i < 1) {
            throw new JsonSyntaxException("Invalid output count: " + i);
        }
        return id;
    }

    public static Identifier getIdentifier(JsonObject json) {
        String string = JsonHelper.getString(json, "enchantment");
        return new Identifier(string);
    }

    public static class Serializer
    implements RecipeSerializer<EnhancementAugmentRecipe> {

        @Override
        public EnhancementAugmentRecipe read(Identifier identifier, JsonObject jsonObject) {
            Ingredient ingredient = Ingredient.fromJson(JsonHelper.getElement(jsonObject, "template"));
            Ingredient ingredient2 = Ingredient.fromJson(JsonHelper.getElement(jsonObject, "base"));
            Ingredient ingredient3 = Ingredient.fromJson(JsonHelper.getElement(jsonObject, "addition"));
            Identifier modifier = EnhancementAugmentRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "modifier"));
            return new EnhancementAugmentRecipe(identifier, ingredient, ingredient2, ingredient3, modifier);
        }

        @Override
        public EnhancementAugmentRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient3 = Ingredient.fromPacket(packetByteBuf);
            Identifier modifier = packetByteBuf.readIdentifier();
            return new EnhancementAugmentRecipe(identifier, ingredient, ingredient2, ingredient3, modifier);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, EnhancementAugmentRecipe enhancementAugmentRecipe) {
            enhancementAugmentRecipe.template.write(packetByteBuf);
            enhancementAugmentRecipe.base.write(packetByteBuf);
            enhancementAugmentRecipe.addition.write(packetByteBuf);
            packetByteBuf.writeIdentifier(enhancementAugmentRecipe.modifier);
        }
    }
}

