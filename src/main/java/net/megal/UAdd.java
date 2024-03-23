package net.megal;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.megal.item.UArrowItem;
import net.megal.item.UItemGroups;
import net.megal.item.UItems;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UAdd implements ModInitializer {
	public static final List<Item> arrowTypes = new ArrayList<>();

	public static final String ID = "uselessadditions";
    public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize() {
		UItems.itemTabs();

		getArrowTypes();

		UItemGroups.groupLoad();
	}

	private void getArrowTypes() {
		arrowTypes.add(Items.ARROW);
		arrowTypes.add(Items.SPECTRAL_ARROW);

		Field[] fields = UItems.class.getDeclaredFields();
		for (Field field : fields) {
			Object object;
			try {
				object = field.get(UItems.class);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}

			if (object instanceof UArrowItem item) arrowTypes.add(item);
		}
	}
}