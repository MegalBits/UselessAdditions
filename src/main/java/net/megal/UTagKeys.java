package net.megal;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class UTagKeys {
    public static final TagKey<Item> HAMMERS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "hammers"));
    public static final TagKey<Item> ROCKS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "rocks"));
    public static final TagKey<Block> HAMMER_MINABLE = TagKey.of(RegistryKeys.BLOCK, new Identifier("uselessadditions", "mineable/hammer"));
}
