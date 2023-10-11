/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package net.megal.uselessadditions.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

import static net.megal.uselessadditions.UAdd.autoSmeltItems;
import static net.megal.uselessadditions.UAdd.naturalMendingItems;

public class EnhancementAugmentRecipe implements EnhancementRecipe {
    private final Identifier id;
    final Ingredient template;
    final Ingredient base;
    final Ingredient addition;
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
        return this.template.test(inventory.getStack(0)) && this.base.test(inventory.getStack(1)) && this.addition.test(inventory.getStack(2));
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        ItemStack stack = new ItemStack(inventory.getStack(1).getItem());
        NbtCompound nbtCompound = inventory.getStack(1).getNbt();
        if (nbtCompound != null) stack.setNbt(nbtCompound.copy());
        @Nullable Enchantment enchantment = Registries.ENCHANTMENT.get(modifier);
        if (modifier != null) stack.addEnchantment(enchantment, 1);

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
//        for (ItemStack stack : base.getMatchingStacks()) {
//            return stack;
//        }
        return new ItemStack(Items.BARRIER);
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


//        @Override
//        public /* synthetic */ Recipe read(Identifier id, PacketByteBuf buf) {
//            return this.read(id, buf);
//        }
//
//        @Override
//        public /* synthetic */ Recipe read(Identifier id, JsonObject json) {
//            return this.read(id, json);
//        }
    }
}

