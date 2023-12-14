package net.megal.uselessadditions.item;

import net.minecraft.item.Item;

public class DescriptionItem extends Item {
    public DescriptionItem(Settings settings) {
        super(settings);
    }
    public int tooltipCount() {
        return 1;
    }
}
