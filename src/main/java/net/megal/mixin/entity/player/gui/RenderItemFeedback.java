package net.megal.mixin.entity.player.gui;

import net.megal.UAddClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.OrderedText;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
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

            float xMod = 0;
            if (UAddClient.textTimer > UAddClient.textTimerMax - 6) xMod = MathHelper.sin((UAddClient.textTimerMax - (UAddClient.textTimer - tickDelta)) * 2) / 3;

            context.getMatrices().push();
            context.getMatrices().translate((float)(this.scaledWidth / 2), (float)(this.scaledHeight - 50), 10.0F);
            context.drawItem(UAddClient.feedbackStack, -8, -22);
            drawTextWithFloatPos(context, textRenderer, UAddClient.feedbackText.asOrderedText(), -textRenderer.getWidth(UAddClient.feedbackText) / 2f + xMod, -4, UAddClient.textColor);
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

    @Unique
    private void drawTextWithFloatPos(DrawContext context, TextRenderer textRenderer, OrderedText text, float x, float y, int color) {
        textRenderer.draw(text, x, y, color, true, context.getMatrices().peek().getPositionMatrix(), context.getVertexConsumers(), TextRenderer.TextLayerType.NORMAL, 0, 15728880);
        context.tryDraw();
    }
}
