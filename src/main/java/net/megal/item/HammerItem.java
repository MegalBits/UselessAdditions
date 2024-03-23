package net.megal.item;

import net.megal.UTagKeys;
import net.minecraft.block.Block;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.TagKey;

public class HammerItem extends MiningToolItem {
    public HammerItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(attackDamage, attackSpeed, material, UTagKeys.HAMMER_MINABLE, settings);
    }
}
