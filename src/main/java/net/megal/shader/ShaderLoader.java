package net.megal.shader;

import net.megal.UAdd;
import net.megal.UAddClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFactory;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.util.Optional;

import static net.megal.item.TooltipType.FIRE;

public class ShaderLoader {
    private static ShaderProgram tooltipProgram;
    private static ShaderProgram fireTooltipProgram;
    private static ShaderProgram crystalTooltipProgram;

    static {
        try {
            ResourceFactory factory = new ProxyResourceFactory(MinecraftClient.getInstance().getResourceManager());

            tooltipProgram = new ShaderProgram(factory, "tooltip", VertexFormats.POSITION_COLOR);
            fireTooltipProgram = new ShaderProgram(factory, "tooltip_fire", VertexFormats.POSITION_COLOR);
            crystalTooltipProgram = new ShaderProgram(factory, "tooltip_crystal", VertexFormats.POSITION_COLOR);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ShaderProgram getTooltipProgram() {
        return tooltipProgram;
    }
    public static ShaderProgram getFireTooltipProgram() {
        return fireTooltipProgram;
    }
    public static ShaderProgram getCrystalTooltipProgram() {
        return crystalTooltipProgram;
    }

    private static class ProxyResourceFactory implements ResourceFactory {
        private final ResourceManager manager;

        public ProxyResourceFactory(ResourceManager manager) {
            this.manager = manager;
        }

        @Override
        public Optional<Resource> getResource(Identifier id) {
            if (id.getPath().contains("/core/")) {
                return this.manager.getResource(new Identifier(UAdd.ID, id.getPath()));
            }

            return this.manager.getResource(id);
        }
    }
}
