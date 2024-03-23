package net.megal.item.modifier;

import net.megal.item.UItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Modifiers {
    public static final UUID AttackDamageUUID = UUID.fromString("dffcbc0c-29e3-442c-b064-7b8066a7ec9e");
    public static final UUID AttackSpeedUUID = UUID.fromString("47c2c517-9be8-4bb3-ba7e-3cc7e9f6c80b");
    public static final float cxp_damageAmount = 0.04f;
    public static final float cxp_miningAmount = 0.1f;
    private static final HashMap<String, Modifier> modifiers = new HashMap<>();

    public static final String FIREPROOF = createModifier("fireproof", new Modifier());
    public static final String AUTO_SMELTING = createModifier("auto_smelting", new Modifier(Enchantments.FIRE_ASPECT, Enchantments.FLAME));
    public static final String CRYSTALLIZED_XP = createModifier("crystallized_xp", new Modifier(Enchantments.MENDING));
    public static final String IMPROVED_SWEEPING = createModifier("improved_sweeping", new Modifier());

    private static String createModifier(String id, Modifier modifier) {
        modifiers.put(id, modifier);
        return id;
    }

    public static Modifier getModifier(String id) {
        return modifiers.get(id);
    }

    public static List<String> getModifiersFromStack(ItemStack stack) {
        if (!stack.hasNbt()) return List.of();

        return getModifiersFromNbt(stack.getOrCreateNbt());
    }

    public static List<String> getModifiersFromNbt(NbtCompound nbt) {
        List<String> list = new ArrayList<>();
        if (!nbt.contains("Modifiers")) return list;

        NbtList modifierList = nbt.getList("Modifiers", NbtElement.COMPOUND_TYPE);

        for (NbtElement element : modifierList) {
            NbtCompound modifierNbt = (NbtCompound) element;
            String id = modifierNbt.getString("id");

            list.add(id);
        }
        return list;
    }

    public static void setModifiers(ItemStack stack, String... modifiers) {
        if (modifiers.length == 0) return;

        NbtCompound nbt = stack.getOrCreateNbt();
        NbtList modifierList = new NbtList();

        for (String modifierId : modifiers) {
            Modifier modifier = getModifier(modifierId);
            if (!EnchantmentHelper.get(stack).keySet().stream().allMatch(modifier::isCompatibleWith)) continue;

            NbtCompound modifierNbt = new NbtCompound();
            modifierNbt.putString("id", modifierId);

            modifierList.add(modifierNbt);
        }

        if (!modifierList.isEmpty()) nbt.put("Modifiers", modifierList);
    }

    public static void addModifiers(ItemStack stack, String... modifiers) {
        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.contains("Modifiers", NbtElement.LIST_TYPE)) {
            setModifiers(stack, modifiers);
            return;
        }

        NbtList modifierList = nbt.getList("Modifiers", NbtElement.COMPOUND_TYPE);

        for (String modifierId : modifiers) {
            Modifier modifier = getModifier(modifierId);
            if (!EnchantmentHelper.get(stack).keySet().stream().allMatch(modifier::isCompatibleWith)) continue;

            NbtCompound modifierNbt = new NbtCompound();
            modifierNbt.putString("id", modifierId);

            modifierList.add(modifierNbt);
        }

        nbt.put("Modifiers", modifierList);
    }

    @Nullable
    public static NbtCompound getModifierCompound(NbtCompound nbt, String modifier) {
        if (!nbt.contains("Modifiers", NbtElement.LIST_TYPE)) return null;

        NbtList modifierList = nbt.getList("Modifiers", NbtElement.COMPOUND_TYPE);

        for (NbtElement modifierElement : modifierList) {
            NbtCompound modifierNbt = (NbtCompound) modifierElement;

            if (modifierNbt.getString("id").equals(modifier)) return modifierNbt;
        }

        return null;
    }
}
