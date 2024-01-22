/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package net.megal.uselessadditions.recipe;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.UItems;
import net.megal.uselessadditions.item.base.UItemHelper;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class EnhancementAugmentRecipe implements EnhancementRecipe {

    public final Ingredient template;
    public final Ingredient base;
    public final Ingredient addition;
    public final int type;
    public final String effect;

    public EnhancementAugmentRecipe(Ingredient template, Ingredient base, Ingredient addition, int type, String effect) {
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.type = type;
        this.effect = effect;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        ItemStack stack = inventory.getStack(1);
        boolean isCompatible = !UItemHelper.getEffects(stack).contains(effect);
        List<Enchantment> enchantments = EnchantmentHelper.get(stack).keySet().stream().toList();
        List<Enchantment> incompatibleEnchantments = SpecialEffects.effects.get(effect).enchantments;
        List<String> incompatibleEffects = SpecialEffects.effects.get(effect).effects;
        if (incompatibleEffects.contains(effect)) {
            isCompatible = false;
        }
        for (Enchantment enchantment : enchantments) {
            if (incompatibleEnchantments.contains(enchantment)) {
                isCompatible = false;
                break;
            }
        }
        return isCompatible && this.template.test(inventory.getStack(0)) && this.base.test(inventory.getStack(1)) && this.addition.test(inventory.getStack(2));
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        ItemStack stack = inventory.getStack(1).copy();
        UItemHelper.addEffects(stack, List.of(effect));
        return stack;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        ItemStack stack = ItemStack.EMPTY;
        switch (type) {
            case 1:
                stack = UItems.MODIFIER.getDefaultStack();
                break;
            case 2:
                break;
            default:
                UAdd.LOGGER.error("Incorrect recipe type given for enhancement recipe, this will cause it to show up with a blank output in REI!");
                break;
        }
        if (!stack.isEmpty()) UItemHelper.addEffects(stack, List.of(effect));
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
    public RecipeSerializer<?> getSerializer() {
        return URecipes.ENHANCMENT_AUGMENT_RECIPE;
    }

    @Override
    public boolean isEmpty() {
        return Stream.of(this.template, this.base, this.addition).anyMatch(Ingredient::isEmpty);
    }
    
    public static class Serializer
    implements RecipeSerializer<EnhancementAugmentRecipe> {
        private static final Codec<EnhancementAugmentRecipe> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("template").forGetter(recipe -> recipe.template),
                                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("base").forGetter(recipe -> recipe.base),
                                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("addition").forGetter(recipe -> recipe.addition),
                                Codec.INT.fieldOf("output_type").forGetter(recipe -> recipe.type),
                                Codec.STRING.fieldOf("effect").forGetter(recipe -> recipe.effect)
                        )
                        .apply(instance, EnhancementAugmentRecipe::new)
        );

        @Override
        public Codec<EnhancementAugmentRecipe> codec() {
            return CODEC;
        }

        @Override
        public EnhancementAugmentRecipe read(PacketByteBuf packetByteBuf) {
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient3 = Ingredient.fromPacket(packetByteBuf);
            int type = packetByteBuf.readInt();
            String effect = packetByteBuf.readString();
            return new EnhancementAugmentRecipe(ingredient, ingredient2, ingredient3, type, effect);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, EnhancementAugmentRecipe enhancementAugmentRecipe) {
            enhancementAugmentRecipe.template.write(packetByteBuf);
            enhancementAugmentRecipe.base.write(packetByteBuf);
            enhancementAugmentRecipe.addition.write(packetByteBuf);
            packetByteBuf.writeInt(enhancementAugmentRecipe.type);
            packetByteBuf.writeString(enhancementAugmentRecipe.effect);
        }
    }
}

