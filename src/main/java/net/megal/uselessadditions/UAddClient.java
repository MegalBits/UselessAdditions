package net.megal.uselessadditions;

import com.mojang.blaze3d.systems.RenderSystem;
import me.x150.renderer.event.RenderEvents;
import me.x150.renderer.render.Renderer3d;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.megal.uselessadditions.client.block.UBlockRendering;
import net.megal.uselessadditions.client.entity.UEntityRendering;
import net.megal.uselessadditions.client.item.UPredicates;
import net.megal.uselessadditions.client.screen.UScreenRendering;
import net.megal.uselessadditions.client.trajectories.FireballHandler;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.RaycastContext;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class UAddClient implements ClientModInitializer {
	public static HashMap<UUID, Trajectory> trajectories = new HashMap<>();
	public static class Trajectory {
		public final Entity entity;
		public final Vec3d move;
		public Vec3d end;
		public int tick = 0;

		public Trajectory(Entity entity, Vec3d move) {
			this.entity = entity;
			this.move = move;
			this.end = entity.getPos();
		}

		public void updateTick(Vec3d start) {
			tick -= 1;
			if (tick <= 0) {
				tick = 60;
				end = start.add(move.multiply(128));
				HitResult result = entity.getWorld().raycast(
						new RaycastContext(
								start,
								start.add(move.multiply(128)),
								RaycastContext.ShapeType.COLLIDER,
								RaycastContext.FluidHandling.NONE,
								entity
						));
				if (result.getType() != HitResult.Type.MISS) {
					end = result.getPos();
				}
			}
		}
	}

	public static void addTrajectory(ExplosiveProjectileEntity entity) {
		Vec3d move = new Vec3d(entity.powerX, entity.powerY, entity.powerZ).multiply(10);

		trajectories.put(entity.getUuid(), new Trajectory(entity, move));
	}

	@Override
	public void onInitializeClient() {
		RenderEvents.WORLD.register(FireballHandler::line);

		UBlockRendering.registerBlockRenderers();

		UEntityRendering.registerEntityRenderers();

		UScreenRendering.registerScreens();

		UPredicates.registerPredicates();
	}
}