package net.megal.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.shader.ShaderLoader;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public enum TooltipType {
    DEFAULT(ShaderLoader.getTooltipProgram(), ShaderLoader::getTooltipProgram,
            new Color(0.068f, 0.0f, 0.068f), new Color(0.06f, 0.0f, 0.06f),
            new Color(0.3137f, 0.0f, 1.0f), new Color(0.1568f, 0.0f, 0.5f)),
    FIRE(ShaderLoader.getFireTooltipProgram(), ShaderLoader::getFireTooltipProgram,
            new Color(0.5f, 0.375f, 0.0f), new Color(0.5f, 0.125f, 0.0f),
            new Color(1.0f, 0.825f, 0.0f), new Color(1.0f, 0.325f, 0.0f)),
    CRYSTAL(ShaderLoader.getCrystalTooltipProgram(), ShaderLoader::getCrystalTooltipProgram,
            new Color(0.406f, 0.3135f, 0.4765f), new Color(0.2175f, 0.155f, 0.335f),
            new Color(0.9f, 0.825f, 0.925f), new Color(0.45f, 0.325f, 0.685f));

    public final ShaderProgram program;
    public final Supplier<ShaderProgram> supplier;
    private final Color baseStartColor;
    private final Color baseEndColor;
    private final Color borderStartColor;
    private final Color borderEndColor;

    public int getBaseStart() {
        return baseStartColor.withAlpha(240).get();
    }
    public int getBaseEnd() {
        return baseEndColor.withAlpha(240).get();
    }
    public int getBorderStart() {
        return borderStartColor.withAlpha(120).get();
    }
    public int getBorderEnd() {
        return borderEndColor.withAlpha(120).get();
    }

    TooltipType(ShaderProgram program, Supplier<ShaderProgram> supplier, Color baseStartColor, Color baseEndColor, Color borderStartColor, Color borderEndColor) {
        this.program = program;
        this.supplier = supplier;
        this.baseStartColor = baseStartColor;
        this.baseEndColor = baseEndColor;
        this.borderStartColor = borderStartColor;
        this.borderEndColor = borderEndColor;
    }

    TooltipType(ShaderProgram program, Supplier<ShaderProgram> supplier, Color startColor, Color endColor) {
        this(program, supplier, startColor, endColor, startColor.multiply(2), endColor.multiply(2));
    }

    private static class Color {
        private final int r;
        private final int g;
        private final int b;
        private final int a;

        public Color(int r, int g, int b, int a) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.a = a;
        }

        public Color(int r, int g, int b) {
            this(r, g, b, 255);
        }

        public Color(float r, float g, float b, float a) {
            this(Math.round(r * 255), Math.round(g * 255), Math.round(b * 255), Math.round(a * 255));
        }

        public Color(float r, float g, float b) {
            this(r, g, b, 1f);
        }

        public Color withAlpha(int value) {
            return new Color(r, g, b, value);
        }

        public Color withAlpha(float value) {
            return withAlpha(Math.round(value * 255));
        }

        public Color multiply(float value) {
            return new Color(
                    (int)MathHelper.clamp(r * value, 0, 255),
                    (int)MathHelper.clamp(g * value, 0, 255),
                    (int)MathHelper.clamp(b * value, 0, 255),
                    a
            );
        }

        public int get() {
            return ColorHelper.Argb.getArgb(a, r, g, b);
        }

        public String toString() {
            return "r:" + r + " g:" + g + " b:" + b + " a:" + a;
        }
    }
}
