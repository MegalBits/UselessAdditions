package net.megal.uselessadditions.item;

import net.megal.uselessadditions.item.base.UAllItems;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TooltipItem extends Item {
    private final Formatting formatting;
    public TooltipItem(Settings settings, Formatting formatting) {
        super(settings);
        this.formatting = formatting;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable(this.getTranslationKey()+".tooltip").formatted(formatting));
    }
}
