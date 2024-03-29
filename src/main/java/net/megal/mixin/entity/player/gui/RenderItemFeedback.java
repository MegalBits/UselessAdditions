package net.megal.mixin.entity.player.gui;

import net.megal.UAddClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class RenderItemFeedback {
    @Shadow @Final private MinecraftClient client;

    @Shadow public abstract TextRenderer getTextRenderer();

    @Shadow private int scaledWidth;

    @Shadow private int scaledHeight;

    @Inject(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;renderAutosaveIndicator(Lnet/minecraft/client/gui/DrawContext;)V",
                    shift = At.Shift.AFTER
            ),
            method = "render"
    )
    private void renderText(DrawContext context, float tickDelta, CallbackInfo ci) {
        if (UAddClient.textTimer > 0) {
            this.client.getProfiler().push("feedbackText");
            TextRenderer textRenderer = this.getTextRenderer();

            context.getMatrices().push();
            context.getMatrices().translate((float)(this.scaledWidth / 2), (float)(this.scaledHeight - 50), 10.0F);
            context.drawItem(UAddClient.feedbackStack, -8, -22);
            context.drawTextWithShadow(textRenderer, UAddClient.feedbackText, -textRenderer.getWidth(UAddClient.feedbackText) / 2, -4, UAddClient.textColor);
            context.getMatrices().pop();
            this.client.getProfiler().pop();
        }
    }

    @Inject(
            at = @At("HEAD"),
            method = "tick()V"
    )
    private void decrementTimer(CallbackInfo ci) {
        UAddClient.textTimer--;
    }
}
