package net.megal.uselessadditions.item.base;

import net.megal.uselessadditions.TooltipStuff;
import net.megal.uselessadditions.UAdd;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class UItemHelper {
    public static ItemStack setEffects(ItemStack stack, List<String> effects) {
        if(effects.isEmpty()) return stack;

        NbtList nbtList = new NbtList();
        for (String s : effects) {
            nbtList.add(NbtString.of(s));
        }
        stack.getOrCreateNbt().put("SpecialEffects", nbtList);

        return stack;
    }

    public static List<String> getEffects(ItemStack stack) {
        List<String> effects = new ArrayList<>();
        if (!stack.getOrCreateNbt().contains("SpecialEffects")) return effects;
        NbtList nbtList = stack.getOrCreateNbt().getList("SpecialEffects", NbtElement.STRING_TYPE);
        for (NbtElement element : nbtList) {
            NbtString nbtString = (NbtString) element;
            effects.add(nbtString.asString());
        }
        return effects;
    }

    public static List<Text> effectsText(ItemStack stack, List<String> effects) {
        List<Text> list = new ArrayList<>();
        List<String> effects2 = getEffects(stack);
        for (String s : effects2.isEmpty() ? effects : effects2) {
            list.add(Text.translatable("specialeffect.builtin").formatted(Formatting.DARK_GRAY).append(Text.literal(TooltipStuff.SPACE).append(Text.translatable("specialeffect.uselessadditions."+s).formatted(Formatting.GRAY))));
        }
        return list;
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
}
