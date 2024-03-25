package net.megal.mixin.entity.player.gui;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.UAdd;
import net.megal.shader.ShaderLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.GlUniform;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipBackgroundRenderer;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(TooltipBackgroundRenderer.class)
public abstract class RenderShaderOnTooltip {
    @WrapWithCondition(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/tooltip/TooltipBackgroundRenderer;renderRectangle(Lnet/minecraft/client/gui/DrawContext;IIIIII)V"
            ),
            method = "render"
    )
    private static boolean dontRenderBackground(DrawContext context, int x, int y, int width, int height, int z, int color) {
        return false;
    }

    @Inject(
            at = @At("TAIL"),
            method = "render"
    )
    private static void renderShader(DrawContext context, int x, int y, int width, int height, int z, CallbackInfo ci) {
        int xStart = x - 3;
        int yStart = y - 3;
        int xEnd = xStart + width + 3 + 3;
        int yEnd = yStart + height + 3 + 3;

        ShaderProgram shader = ShaderLoader.getTooltipProgram();
        GlUniform quadSize = shader.getUniform("QuadSize");

        if (quadSize != null) {
            quadSize.set((float)xStart, (float)yStart, (float)xEnd, (float)yEnd);
        }

        MatrixStack matrixStack = context.getMatrices();
        Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        buffer.vertex(positionMatrix, xStart, yStart, z).color(1, 0, 0, 1).next();
        buffer.vertex(positionMatrix, xStart, yEnd, z).color(0, 1, 0, 1).next();
        buffer.vertex(positionMatrix, xEnd, yEnd, z).color(0, 0, 1, 1).next();
        buffer.vertex(positionMatrix, xEnd, yStart, z).color(1, 1, 0, 1).next();

        RenderSystem.setShader(ShaderLoader::getTooltipProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

        tessellator.draw();
    }
}
