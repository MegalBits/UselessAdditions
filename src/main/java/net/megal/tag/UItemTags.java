package net.megal.tag;

import net.megal.UAdd;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class UItemTags {
    private static TagKey<Item> tag(String id) {
        return TagKey.of(RegistryKeys.ITEM, new Identifier(UAdd.ID, id));
    }
}
