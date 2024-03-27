package net.megal.item.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.UAdd;
import net.megal.item.UBowItem;
import net.megal.item.UItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;

@Environment(EnvType.CLIENT)
public class Predicates {
    public static void addBowPredicates() {
        Field[] fields = UItems.class.getDeclaredFields();
        for (Field f : fields) {
            Object object;
            try {
                object = f.get(UItems.class);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            if (object instanceof BowItem item) bowPredicate(item);
        }

        ModelPredicateProviderRegistry.register(Items.BOW, new Identifier("arrow_type"), (stack, world, entity, seed) -> {
            if (entity != null && stack == entity.getActiveItem() && !entity.getProjectileType(stack).isEmpty()) {
                int i = UAdd.arrowTypes.indexOf(entity.getProjectileType(stack).getItem());
                if (i > 0) return i / 100f;
            }
            return 0;
        });
    }

    private static void bowPredicate(Item item) {
        float bowUseTime;
        if (item instanceof UBowItem bowItem) bowUseTime = bowItem.useTime;
        else {
            bowUseTime = 2;
        }

        ModelPredicateProviderRegistry.register(item, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity != null && stack == entity.getActiveItem()) return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / (bowUseTime * 20);
            return 0;
        });

        ModelPredicateProviderRegistry.register(item, new Identifier("pulling"), (stack, world, entity, seed) -> {
            if (entity != null && entity.isUsingItem() && entity.getActiveItem() == stack) return 1;
            return 0;
        });

        ModelPredicateProviderRegistry.register(item, new Identifier("arrow_type"), (stack, world, entity, seed) -> {
            if (entity != null && stack == entity.getActiveItem() && !entity.getProjectileType(stack).isEmpty()) {
                int i = UAdd.arrowTypes.indexOf(entity.getProjectileType(stack).getItem());
                if (i > 0) return i / 100f;
            }
            return 0;
        });
    }
}
