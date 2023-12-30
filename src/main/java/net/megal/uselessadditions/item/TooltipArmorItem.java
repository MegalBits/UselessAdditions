package net.megal.uselessadditions.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class TooltipArmorItem extends ArmorItem {
    private final Formatting formatting;
    public TooltipArmorItem(ArmorMaterial material, Type type, Settings settings, Formatting formatting) {
        super(material, type, settings);
        this.formatting = formatting;
    }
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable(this.getTranslationKey()+".tooltip").formatted(formatting));
    }
}
