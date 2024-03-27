package net.megal.mixin.entity.player.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.UAddClient;
import net.megal.gui.UDrawContext;
import net.megal.item.TooltipType;
import net.megal.item.client.ItemInfo;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Environment(EnvType.CLIENT)
@Mixin(DrawContext.class)
public abstract class UDrawContextMixin implements UDrawContext {
    @Unique
    @Nullable
    private TooltipType storedTooltipType;

    @Override
    public void UAdd$setStoredItem(TooltipType tooltipType) {
        storedTooltipType = tooltipType;
    }

    @Override
    public Optional<TooltipType> UAdd$getStoredItem() {
        return Optional.ofNullable(storedTooltipType);
    }

    @Inject(
            at = @At("HEAD"),
            method = "drawItemTooltip"
    )
    private void setStoredItem(TextRenderer textRenderer, ItemStack stack, int x, int y, CallbackInfo ci) {
        storedTooltipType = ItemInfo.tooltipTypeLookup.getOrDefault(stack.getItem(), TooltipType.DEFAULT);
    }
}
