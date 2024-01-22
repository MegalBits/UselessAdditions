package net.megal.uselessadditions.client.trajectories;

import me.x150.renderer.render.Renderer3d;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.UAddClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.joml.Matrix4f;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static net.megal.uselessadditions.UAddClient.trajectories;

public class FireballHandler {
    public static void line(MatrixStack stack) {
        List<UUID> removals = new ArrayList<>();
        HashMap<UUID, UAddClient.Trajectory> trajectoryCopy = (HashMap<UUID, UAddClient.Trajectory>) trajectories.clone();
        int i = 0;
        for (UAddClient.Trajectory trajectory : trajectoryCopy.values()) {
            i++;

            UUID uuid = trajectory.entity.getUuid();
            if (!trajectory.entity.isRemoved()) {
                Vec3d start = trajectory.entity.getPos().add(0,trajectory.entity.getHeight()/2f,0);

                assert MinecraftClient.getInstance().cameraEntity != null;
                Vec3d camPos = MinecraftClient.getInstance().cameraEntity.getPos();

                if (trajectory.entity.shouldRender(camPos.x, camPos.y, camPos.z)) {
                    trajectories.get(uuid).updateTick(start);
                    Color color = new Color(139, 109, 11);
                    if (trajectory.entity instanceof DragonFireballEntity) color = new Color(220, 117, 250);
                    if (trajectory.entity instanceof WitherSkullEntity skull) color = skull.isCharged() ? new Color(77, 114, 160) : new Color(41, 41, 41);

                    if (i < 50) Renderer3d.renderLine(stack, color, start, trajectory.end);
                }
            }
            else {
                removals.add(uuid);
            }
        }
        for (UUID uuid : removals) trajectories.remove(uuid);
    }
}
