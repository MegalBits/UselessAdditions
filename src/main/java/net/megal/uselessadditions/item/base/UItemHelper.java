package net.megal.uselessadditions.item.base;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.TwoHanded;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UItemHelper {
    public static ItemStack setEffects(ItemStack stack, List<String> effects) {
        if (stack.isEmpty() || effects.isEmpty()) return stack;

        NbtList nbtList = new NbtList();
        for (String s : effects) {
            nbtList.add(NbtString.of(s));
        }
        stack.getOrCreateNbt().put("SpecialEffects", nbtList);

        return stack;
    }

    public static ItemStack addEffects(ItemStack stack, List<String> effects) {
        if (stack.isEmpty() || effects.isEmpty()) return stack;
        if (!stack.hasNbt() || !stack.getOrCreateNbt().contains("SpecialEffects")) return setEffects(stack, effects);

        NbtList nbtList = stack.getOrCreateNbt().getList("SpecialEffects", NbtElement.STRING_TYPE);
        for (String s : effects) {
            if (nbtList.contains(NbtString.of(s))) continue;
            nbtList.add(NbtString.of(s));
        }
        stack.getOrCreateNbt().put("SpecialEffects", nbtList);

        return stack;
    }

    public static List<String> getEffects(ItemStack stack) {
        List<String> effects = new ArrayList<>();
        if (stack.isEmpty() || !stack.hasNbt() || !stack.getOrCreateNbt().contains("SpecialEffects")) return effects;
        NbtList nbtList = stack.getOrCreateNbt().getList("SpecialEffects", NbtElement.STRING_TYPE);
        for (NbtElement element : nbtList) {
            NbtString nbtString = (NbtString) element;
            effects.add(nbtString.asString());
        }
        return effects;
    }

    public static List<Text> effectsText(ItemStack stack, List<String> effects) {
        Item item = stack.getItem();
        List<Text> list = new ArrayList<>();
        List<Text> list2 = new ArrayList<>();
        List<String> effects2 = getEffects(stack);
        List<String> defaultEffects = getEffects(stack.getItem().getDefaultStack());

        if (item instanceof TwoHanded) {
            list.add(createEffectText(true, "two_handed"));
        }
        if (item == Items.TURTLE_HELMET) {
            list.add(createEffectText(true, "water_breathing"));
        }
        if (item instanceof ScytheItem) {
            list.add(createEffectText(true, SpecialEffects.IMPROVED_SWEEPING));
        }
        if (item.isFireproof()) {
            list.add(createEffectText(true, SpecialEffects.FIREPROOF));
        }
        for (String s : effects2.isEmpty() ? effects : effects2) {
            if (s.equals(SpecialEffects.FIREPROOF) && stack.getItem().isFireproof()) continue;
            if (s.equals(SpecialEffects.IMPROVED_SWEEPING) && stack.getItem() instanceof ScytheItem) continue;

            boolean isBuiltin = defaultEffects.contains(s);

            if (isBuiltin) list.add(createEffectText(isBuiltin, s));
            else list2.add(createEffectText(isBuiltin, s));
        }
        list.addAll(list2);
        return list;
    }

    private static MutableText createEffectText(boolean isBuiltin, String s) {
        return Text.translatable(isBuiltin ? "specialeffect.builtin" : "specialeffect.modified").formatted(Formatting.DARK_GRAY).append(Text.literal(" ").append(Text.translatable("specialeffect.uselessadditions."+s).formatted(Formatting.GRAY)));
    }

    public static ItemStack unstackItems(ItemStack stack, World world, PlayerEntity player) {
        if(stack.getCount() > stack.getMaxCount()) {
            for (int i = 0; i < stack.getCount()-1; i++) {
                if (!world.isClient) {
                    world.spawnEntity(new ItemEntity(world, player.getX(), player.getY(), player.getZ(), stack));
                }
            }
            stack.setCount(1);
        }
        return stack;
    }

    public static ToolMaterial modifyMaterial(ToolMaterial material, int durability, float miningSpeed, float attackDamage, int miningLevel, int enchantability, @Nullable Ingredient ingredient) {
        return new ToolMaterial() {
            @Override
            public int getDurability() {
                return material.getDurability() + durability;
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return material.getMiningSpeedMultiplier() + miningSpeed;
            }

            @Override
            public float getAttackDamage() {
                return material.getAttackDamage() + attackDamage;
            }

            @Override
            public int getMiningLevel() {
                return material.getMiningLevel() + miningLevel;
            }

            @Override
            public int getEnchantability() {
                return material.getEnchantability() + enchantability;
            }

            @Override
            public Ingredient getRepairIngredient() {
                if (ingredient != null) return ingredient;
                return material.getRepairIngredient();
            }
        };
    }

    public static ToolMaterial modifyMaterial(ToolMaterial material, int durability, float miningSpeed, float attackDamage) {
        return modifyMaterial(material, durability, miningSpeed, attackDamage, 0, 0, null);
    }
}
