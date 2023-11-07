package net.megal.uselessadditions.magic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;

public class USpells {
    public static final Spell BASIC_ARROW = new Spell(new Spell.Instruction().setProjectile(1), translationKey("arrow"), null);
    public static final Spell FAST_ARROW = new Spell(new Spell.Instruction().setProjectile(1, 1, 8, 1), translationKey("fast_arrow"), null);
    public static final Spell BASIC_DAMAGE = new Spell(new Spell.Instruction().setDamage(1), translationKey("damage"), null);
    public static final Spell HIGH_DAMAGE = new Spell(new Spell.Instruction().setDamage(4), translationKey("high_damage"), null);

    private static String translationKey(String name) {
        return "spell.uselessadditions."+name;
    }
}
