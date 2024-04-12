package net.megal;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.megal.entity.RangedMob;
import net.megal.item.client.Predicates;
import net.megal.mixin.entity.projectile.SetStack;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;

public class UAddClient implements ClientModInitializer {
	public static ItemStack feedbackStack = ItemStack.EMPTY;
	public static Text feedbackText = Text.empty();
	public static float textTimer = -1;
	public static float textTimerMax = -1;
	public static int textColor = Colors.WHITE;

	public static void setFeedback(ItemStack stack, Text text, float time, int color) {
		feedbackStack = stack.copyWithCount(1);
		feedbackText = text;
		textTimer = time;
		textTimerMax = time;
		textColor = color;
	}

	@Override
	public void onInitializeClient() {
		Predicates.addBowPredicates();

		ClientPlayNetworking.registerGlobalReceiver(new Identifier(UAdd.ID, "sync_mob_projectile"), (client, handler, buf, responseSender) -> {
			Item item = Registries.ITEM.get(new Identifier(buf.readString()));
			int id = buf.readInt();

			client.execute(() -> {
				if (client.world != null) {
					AbstractSkeletonEntity skeleton = (AbstractSkeletonEntity) client.world.getEntityById(id);

					if (skeleton != null) ((RangedMob)skeleton).UAdd$setProjectile(item);
				}
			});
		});

		ClientPlayNetworking.registerGlobalReceiver(new Identifier(UAdd.ID, "sync_projectile_item"), (client, handler, buf, responseSender) -> {
			ItemStack stack = buf.readItemStack();
			int id = buf.readInt();

			client.execute(() -> {
				if (client.world != null) {
					PersistentProjectileEntity projectile = (PersistentProjectileEntity) client.world.getEntityById(id);

					if (projectile != null) ((SetStack)projectile).setStack(stack);
				}
			});
		});
	}
}