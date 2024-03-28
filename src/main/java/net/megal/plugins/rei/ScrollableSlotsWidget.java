package net.megal.plugins.rei;

import com.google.common.collect.Lists;
import me.shedaniel.clothconfig2.ClothConfigInitializer;
import me.shedaniel.clothconfig2.api.scroll.ScrollingContainer;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.REIRuntime;
import me.shedaniel.rei.api.client.gui.widgets.CloseableScissors;
import me.shedaniel.rei.api.client.gui.widgets.Slot;
import me.shedaniel.rei.api.client.gui.widgets.WidgetWithBounds;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.util.math.MathHelper;

import java.util.List;
import java.util.Objects;

public class ScrollableSlotsWidget extends WidgetWithBounds {
    private Rectangle bounds;
    private List<Slot> widgets;
    private final ScrollingContainer scrolling = new ScrollingContainer() {
        @Override
        public Rectangle getBounds() {
            Rectangle bounds = ScrollableSlotsWidget.this.getBounds();
            return new Rectangle(bounds.x + 1, bounds.y + 1, bounds.width - 2, bounds.height - 2);
        }

        @Override
        public int getMaxScrollHeight() {
            return MathHelper.ceil(widgets.size() / 8f) * 18;
        }
    };

    public ScrollableSlotsWidget(Rectangle bounds, List<Slot> widgets) {
        this.bounds = Objects.requireNonNull(bounds);
        this.widgets = Lists.newArrayList(widgets);
    }

    @Override
    public boolean mouseScrolled(double double_1, double double_2, double amountX, double amountY) {
        if (containsMouse(double_1, double_2) && amountY != 0) {
            scrolling.offset(ClothConfigInitializer.getScrollStep() * -amountY, true);
            return true;
        }
        return false;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (scrolling.updateDraggingState(mouseX, mouseY, button))
            return true;
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (scrolling.mouseDragged(mouseX, mouseY, button, deltaX, deltaY))
            return true;
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public void render(DrawContext graphics, int mouseX, int mouseY, float delta) {
        scrolling.updatePosition(delta);
        Rectangle innerBounds = scrolling.getScissorBounds();
        try (CloseableScissors scissors = scissor(graphics, innerBounds)) {
            for (int y = 0; y < MathHelper.ceil(widgets.size() / 8f); y++) {
                for (int x = 0; x < 7; x++) {
                    int index = y * 8 + x;
                    if (widgets.size() <= index)
                        break;
                    Slot widget = widgets.get(index);
                    widget.getBounds().setLocation(bounds.x + 1 + x * 18, bounds.y + 1 + y * 18 - scrolling.scrollAmountInt());
                    widget.render(graphics, mouseX, mouseY, delta);
                }
            }
        }
        try (CloseableScissors scissors = scissor(graphics, scrolling.getBounds())) {
            scrolling.renderScrollBar(graphics, 0xff000000, 1, REIRuntime.getInstance().isDarkThemeEnabled() ? 0.8f : 1f);
        }
    }

    @Override
    public List<? extends Element> children() {
        return widgets;
    }
}