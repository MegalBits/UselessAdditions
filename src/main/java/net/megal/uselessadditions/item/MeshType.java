package net.megal.uselessadditions.item;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.block.BlockEntry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public enum MeshType implements StringIdentifiable {
    NONE("empty", 0, Map.of()),
    PLANT("plant", 64, Map.of(
            new BlockEntry(Blocks.GRASS_BLOCK), new Identifier(UAdd.MOD_ID, "sieve/plant/dirt"),
            new BlockEntry(Blocks.DIRT_PATH), new Identifier(UAdd.MOD_ID, "sieve/plant/dirt"),
            new BlockEntry(Blocks.DIRT), new Identifier(UAdd.MOD_ID, "sieve/plant/dirt"),
            new BlockEntry(Blocks.COARSE_DIRT), new Identifier(UAdd.MOD_ID, "sieve/plant/dirt"),
            new BlockEntry(Blocks.ROOTED_DIRT), new Identifier(UAdd.MOD_ID, "sieve/plant/dirt"),

            new BlockEntry(Blocks.SAND), new Identifier(UAdd.MOD_ID, "sieve/plant/sand"),
            new BlockEntry(Blocks.RED_SAND), new Identifier(UAdd.MOD_ID, "sieve/plant/red_sand"),

            new BlockEntry(Blocks.GRAVEL), new Identifier(UAdd.MOD_ID, "sieve/plant/gravel")
    )),
    STRING("string", 50, Map.of(
            new BlockEntry(Blocks.GRASS_BLOCK), new Identifier(UAdd.MOD_ID, "sieve/string/dirt"),
            new BlockEntry(Blocks.DIRT_PATH), new Identifier(UAdd.MOD_ID, "sieve/string/dirt"),
            new BlockEntry(Blocks.DIRT), new Identifier(UAdd.MOD_ID, "sieve/string/dirt"),
            new BlockEntry(Blocks.COARSE_DIRT), new Identifier(UAdd.MOD_ID, "sieve/string/dirt"),
            new BlockEntry(Blocks.ROOTED_DIRT), new Identifier(UAdd.MOD_ID, "sieve/string/dirt"),

            new BlockEntry(Blocks.SAND), new Identifier(UAdd.MOD_ID, "sieve/string/sand"),
            new BlockEntry(Blocks.RED_SAND), new Identifier(UAdd.MOD_ID, "sieve/string/red_sand"),

            new BlockEntry(Blocks.GRAVEL), new Identifier(UAdd.MOD_ID, "sieve/string/gravel")
    )),
    FLINT("flint", 46, Map.of(
            new BlockEntry(Blocks.GRASS_BLOCK), new Identifier(UAdd.MOD_ID, "sieve/flint/dirt"),
            new BlockEntry(Blocks.DIRT_PATH), new Identifier(UAdd.MOD_ID, "sieve/flint/dirt"),
            new BlockEntry(Blocks.DIRT), new Identifier(UAdd.MOD_ID, "sieve/flint/dirt"),
            new BlockEntry(Blocks.COARSE_DIRT), new Identifier(UAdd.MOD_ID, "sieve/flint/dirt"),
            new BlockEntry(Blocks.ROOTED_DIRT), new Identifier(UAdd.MOD_ID, "sieve/flint/dirt"),

            new BlockEntry(Blocks.SAND), new Identifier(UAdd.MOD_ID, "sieve/flint/sand"),
            new BlockEntry(Blocks.RED_SAND), new Identifier(UAdd.MOD_ID, "sieve/flint/red_sand"),

            new BlockEntry(Blocks.GRAVEL), new Identifier(UAdd.MOD_ID, "sieve/flint/gravel")
    )),
    IRON("iron", 34, Map.of(
            new BlockEntry(Blocks.GRASS_BLOCK), new Identifier(UAdd.MOD_ID, "sieve/iron/dirt"),
            new BlockEntry(Blocks.DIRT_PATH), new Identifier(UAdd.MOD_ID, "sieve/iron/dirt"),
            new BlockEntry(Blocks.DIRT), new Identifier(UAdd.MOD_ID, "sieve/iron/dirt"),
            new BlockEntry(Blocks.COARSE_DIRT), new Identifier(UAdd.MOD_ID, "sieve/iron/dirt"),
            new BlockEntry(Blocks.ROOTED_DIRT), new Identifier(UAdd.MOD_ID, "sieve/iron/dirt"),

            new BlockEntry(Blocks.SAND), new Identifier(UAdd.MOD_ID, "sieve/iron/sand"),
            new BlockEntry(Blocks.RED_SAND), new Identifier(UAdd.MOD_ID, "sieve/iron/red_sand"),

            new BlockEntry(Blocks.GRAVEL), new Identifier(UAdd.MOD_ID, "sieve/iron/gravel")
    )),
    GOLD("gold", 20, IRON.entries),
    DIAMOND("diamond", 28, Map.of(
            new BlockEntry(Blocks.GRASS_BLOCK), new Identifier(UAdd.MOD_ID, "sieve/diamond/dirt"),
            new BlockEntry(Blocks.DIRT_PATH), new Identifier(UAdd.MOD_ID, "sieve/diamond/dirt"),
            new BlockEntry(Blocks.DIRT), new Identifier(UAdd.MOD_ID, "sieve/diamond/dirt"),
            new BlockEntry(Blocks.COARSE_DIRT), new Identifier(UAdd.MOD_ID, "sieve/diamond/dirt"),
            new BlockEntry(Blocks.ROOTED_DIRT), new Identifier(UAdd.MOD_ID, "sieve/diamond/dirt"),

            new BlockEntry(Blocks.SAND), new Identifier(UAdd.MOD_ID, "sieve/diamond/sand"),
            new BlockEntry(Blocks.RED_SAND), new Identifier(UAdd.MOD_ID, "sieve/diamond/red_sand"),

            new BlockEntry(Blocks.GRAVEL), new Identifier(UAdd.MOD_ID, "sieve/diamond/gravel")
    )),
    NETHERITE("netherite", 18, Map.of(
            new BlockEntry(Blocks.GRASS_BLOCK), new Identifier(UAdd.MOD_ID, "sieve/netherite/dirt"),
            new BlockEntry(Blocks.DIRT_PATH), new Identifier(UAdd.MOD_ID, "sieve/netherite/dirt"),
            new BlockEntry(Blocks.DIRT), new Identifier(UAdd.MOD_ID, "sieve/netherite/dirt"),
            new BlockEntry(Blocks.COARSE_DIRT), new Identifier(UAdd.MOD_ID, "sieve/netherite/dirt"),
            new BlockEntry(Blocks.ROOTED_DIRT), new Identifier(UAdd.MOD_ID, "sieve/netherite/dirt"),

            new BlockEntry(Blocks.SAND), new Identifier(UAdd.MOD_ID, "sieve/netherite/sand"),
            new BlockEntry(Blocks.RED_SAND), new Identifier(UAdd.MOD_ID, "sieve/netherite/red_sand"),

            new BlockEntry(Blocks.GRAVEL), new Identifier(UAdd.MOD_ID, "sieve/netherite/gravel")
    )),
    DRAGON("dragon", 24, Map.of(
            new BlockEntry(Blocks.GRASS_BLOCK), new Identifier(UAdd.MOD_ID, "sieve/dragon/dirt"),
            new BlockEntry(Blocks.DIRT_PATH), new Identifier(UAdd.MOD_ID, "sieve/dragon/dirt"),
            new BlockEntry(Blocks.DIRT), new Identifier(UAdd.MOD_ID, "sieve/dragon/dirt"),
            new BlockEntry(Blocks.COARSE_DIRT), new Identifier(UAdd.MOD_ID, "sieve/dragon/dirt"),
            new BlockEntry(Blocks.ROOTED_DIRT), new Identifier(UAdd.MOD_ID, "sieve/dragon/dirt"),

            new BlockEntry(Blocks.SAND), new Identifier(UAdd.MOD_ID, "sieve/dragon/sand"),
            new BlockEntry(Blocks.RED_SAND), new Identifier(UAdd.MOD_ID, "sieve/dragon/red_sand"),

            new BlockEntry(Blocks.GRAVEL), new Identifier(UAdd.MOD_ID, "sieve/dragon/gravel")
    ));


    public final String type;
    public final int uses;
    public final Map<BlockEntry, Identifier> entries;
    MeshType(String type, int uses, Map<BlockEntry, Identifier> entries) {
        this.type = type;
        this.uses = uses;
        this.entries = entries;
    }

    public boolean canAccept(Block block) {
        for (BlockEntry entry : entries.keySet()) {
            if (entry.testBlock(block)) return true;
        }
        return false;
    }

    public boolean canAccept(Item item) {
        if (item instanceof BlockItem blockItem) {
            for (BlockEntry entry : entries.keySet()) {
                if (entry.testBlock(blockItem.getBlock())) return true;
            }
        }
        return false;
    }

    @Override
    public String asString() {
        return type;
    }
}
