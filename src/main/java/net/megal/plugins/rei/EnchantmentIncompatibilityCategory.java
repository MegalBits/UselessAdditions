package net.megal.plugins.rei;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.*;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.CollectionUtils;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentIncompatibilityCategory implements DisplayCategory<EnchantmentIncompatibilityDisplay> {
    @Override
    public CategoryIdentifier<? extends EnchantmentIncompatibilityDisplay> getCategoryIdentifier() {
        return UAddReiClient.ENCHANTMENT_INCOMPATIBILITY;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("uselessadditions.rei.enchantment_incompatibility");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(Items.ENCHANTED_BOOK);
    }

    @Override
    public List<Widget> setupDisplay(EnchantmentIncompatibilityDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createSlot(new Point(bounds.x, bounds.getMinY() + 3)).entries(display.getOutputEntries().get(0)));

        Rectangle rectangle = new Rectangle(bounds.x + 18, bounds.y + 2, 130, 36);
        widgets.add(Widgets.createSlotBase(rectangle));
        widgets.add(new ScrollableSlotsWidget(rectangle, CollectionUtils.map(display.getInputEntries(), ingr -> Widgets.createSlot(new Point(0, 0)).disableBackground().entries(ingr))));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 36;
    }
}
