package net.megal.tag;

import net.megal.UAdd;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class UBlockTags {
    private static TagKey<Block> tag(String id) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(UAdd.ID, id));
    }
}
