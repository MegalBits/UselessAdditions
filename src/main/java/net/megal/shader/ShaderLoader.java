package net.megal.shader;

import net.megal.UAdd;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFactory;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.util.Optional;

public class ShaderLoader {
    private static ShaderProgram tooltipProgram;

    static {
        try {
            ResourceFactory factory = new ProxyResourceFactory(MinecraftClient.getInstance().getResourceManager());

            tooltipProgram = new ShaderProgram(factory, "tooltip", VertexFormats.POSITION_COLOR);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ShaderProgram getTooltipProgram() {
        return tooltipProgram;
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
