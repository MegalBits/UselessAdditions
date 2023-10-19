package net.megal.uselessadditions.plugins.rei;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.block.Blocks;
import net.minecraft.text.Text;

import java.util.List;

public class SmithingNoNbtCategory implements DisplayCategory<SmithingNoNbtDisplay> {
    @Override
    public CategoryIdentifier<? extends SmithingNoNbtDisplay> getCategoryIdentifier() {
        return UAddREIClientPlugin.SMITHING_NO_NBT;
    }
    @Override
    public Text getTitle() {
        return Text.translatable("category.uselessadditions.no_nbt_smithing");
    }
    @Override
    public Renderer getIcon() {
        return EntryStacks.of(Blocks.SMITHING_TABLE);
    }
    @Override
    public List<Widget> setupDisplay(SmithingNoNbtDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 31, bounds.getCenterY() - 13);
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 27 + 5, startPoint.y + 4)));
        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 61 + 5, startPoint.y + 5)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 4 - 18 * 2 + 5, startPoint.y + 5)).entries(display.getInputEntries().get(0)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 4 - 18 + 5, startPoint.y + 5)).entries(display.getInputEntries().get(1)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 4 + 5, startPoint.y + 5)).entries(display.getInputEntries().get(2)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 61 + 5, startPoint.y + 5)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
        return widgets;
    }
    @Override
    public int getDisplayHeight() {
        return 36;
    }
}
