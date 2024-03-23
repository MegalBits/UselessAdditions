package net.megal;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.megal.item.client.Predicates;
import net.megal.mixin.entity.projectile.SetStack;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;

public class UAddClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Predicates.addBowPredicates();

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