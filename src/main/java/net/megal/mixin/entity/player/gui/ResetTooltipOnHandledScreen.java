package net.megal.mixin.entity.player.gui;

import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.gui.UDrawContext;
import net.megal.item.TooltipType;
import net.megal.item.client.ItemInfo;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(HandledScreen.class)
public abstract class ResetTooltipOnHandledScreen {
    @Inject(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTooltip(Lnet/minecraft/client/font/TextRenderer;Ljava/util/List;Ljava/util/Optional;II)V"
            ),
            method = "drawMouseoverTooltip"
    )
    private void resetDrawContextStoredItem(DrawContext context, int x, int y, CallbackInfo ci, @Local ItemStack stack) {
        ((UDrawContext)context).UAdd$setStoredItem(ItemInfo.tooltipTypeLookup.getOrDefault(stack.getItem(), TooltipType.DEFAULT));
    }
}
