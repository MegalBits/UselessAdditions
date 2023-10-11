package net.megal.uselessadditions.block;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class TooltipBlockItem extends BlockItem {
    private final Formatting formatting;
    public TooltipBlockItem(Block block, Settings settings, Formatting formatting) {
        super(block, settings);
        this.formatting = formatting;
    }
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable(this.getTranslationKey()+".tooltip").formatted(formatting));
    }
}
