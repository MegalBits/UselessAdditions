package net.megal.uselessadditions.block;

import net.fabricmc.fabric.api.tag.convention.v1.TagUtil;
import net.minecraft.block.Block;
import net.minecraft.data.server.tag.TagProvider;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class BlockEntry {
    @Nullable
    public final Block block;
    @Nullable
    public final TagKey<Block> blockTag;

    public BlockEntry(Block block) {
        this.block = block;
        this.blockTag = null;
    }

    public BlockEntry(TagKey<Block> blockTag) {
        this.blockTag = blockTag;
        this.block = null;
    }

    public boolean testBlock(Block block2) {
        return (block != null && block == block2) || (blockTag != null && block2.getDefaultState().isIn(blockTag));
    }
}
