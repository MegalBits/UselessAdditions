package net.megal.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.item.TooltipType;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.Item;

import java.util.Optional;

public interface UDrawContext {
    default void UAdd$setStoredItem(TooltipType tooltipType) {}
    default Optional<TooltipType> UAdd$getStoredItem() {
        return Optional.empty();
    }
}
