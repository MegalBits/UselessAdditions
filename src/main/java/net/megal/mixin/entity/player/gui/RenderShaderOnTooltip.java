package net.megal.mixin.entity.player.gui;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.UAdd;
import net.megal.gui.UDrawContext;
import net.megal.item.TooltipType;
import net.minecraft.client.gl.GlUniform;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipBackgroundRenderer;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.ColorHelper;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
@Mixin(TooltipBackgroundRenderer.class)
public abstract class RenderShaderOnTooltip {
    @Shadow
    private static void renderVerticalLine(DrawContext context, int x, int y, int height, int z, int startColor, int endColor) {}

    @Shadow
    private static void renderHorizontalLine(DrawContext context, int x, int y, int width, int z, int color) {}

    @WrapWithCondition(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/tooltip/TooltipBackgroundRenderer;renderRectangle(Lnet/minecraft/client/gui/DrawContext;IIIIII)V"
            ),
            method = "render"
    )
    private static boolean dontRenderBackground(DrawContext context, int x, int y, int width, int height, int z, int color) {
        return ((UDrawContext)context).UAdd$getStoredItem().isEmpty();
    }

    @WrapWithCondition(
            at = {
                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/client/gui/tooltip/TooltipBackgroundRenderer;renderHorizontalLine(Lnet/minecraft/client/gui/DrawContext;IIIII)V"
                    ),
                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/client/gui/tooltip/TooltipBackgroundRenderer;renderVerticalLine(Lnet/minecraft/client/gui/DrawContext;IIIII)V"
                    )
            },
            method = "render"
    )
    private static boolean dontRenderLines(DrawContext context, int x, int y, int length, int z, int color) {
        return ((UDrawContext)context).UAdd$getStoredItem().isEmpty();
    }
    @ModifyExpressionValue(
            at = @At(value = "CONSTANT", args = "intValue=1347420415"),
            method = "render"
    )
    private static int changeStartColor(int original, DrawContext context, int x, int y, int width, int height, int z) {
        Optional<TooltipType> tooltipType = ((UDrawContext)context).UAdd$getStoredItem();
        return tooltipType.map(TooltipType::getBorderStart).orElse(original);
    }

    @ModifyExpressionValue(
            at = @At(value = "CONSTANT", args = "intValue=1344798847"),
            method = "render"
    )
    private static int changeEndColor(int original, DrawContext context, int x, int y, int width, int height, int z) {
        Optional<TooltipType> tooltipType = ((UDrawContext)context).UAdd$getStoredItem();
        return tooltipType.map(TooltipType::getBorderEnd).orElse(original);
    }

    @Inject(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/tooltip/TooltipBackgroundRenderer;renderRectangle(Lnet/minecraft/client/gui/DrawContext;IIIIII)V"
            ),
            method = "render"
    )
    private static void renderShader(DrawContext context, int x, int y, int width, int height, int z, CallbackInfo ci) {
        Optional<TooltipType> tooltipType = ((UDrawContext)context).UAdd$getStoredItem();
        if (tooltipType.isEmpty()) return;
        int xStart = x - 3;
        int yStart = y - 3;
        int xEnd = width + 3 + 3;
        int yEnd = height + 3 + 3;

        renderHorizontalLine(context, xStart, yStart - 1, xEnd, z, tooltipType.get().getBaseStart());
        renderHorizontalLine(context, xStart, yStart + yEnd, xEnd, z, tooltipType.get().getBaseEnd());
        renderVerticalLine(context, xStart - 1, yStart, yEnd, z, tooltipType.get().getBaseStart(), tooltipType.get().getBaseEnd());
        renderVerticalLine(context, xStart + xEnd, yStart, yEnd, z, tooltipType.get().getBaseStart(), tooltipType.get().getBaseEnd());

        xEnd += xStart;
        yEnd += yStart;

        ShaderProgram shader = tooltipType.get().program;
        Supplier<ShaderProgram> supplier = tooltipType.get().supplier;

        GlUniform quadSize = shader.getUniform("QuadSize");

        if (quadSize != null) {
            quadSize.set((float)xStart, (float)yStart, (float)xEnd, (float)yEnd);
        }

        MatrixStack matrixStack = context.getMatrices();
        Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        buffer.vertex(positionMatrix, xStart, yStart, z).color(1, 1, 1, 0.94f).next();
        buffer.vertex(positionMatrix, xStart, yEnd, z).color(1, 1, 1, 0.94f).next();
        buffer.vertex(positionMatrix, xEnd, yEnd, z).color(1, 1, 1, 0.94f).next();
        buffer.vertex(positionMatrix, xEnd, yStart, z).color(1, 1, 1, 0.94f).next();

        RenderSystem.setShader(supplier);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

        tessellator.draw();
    }
}
