package net.megal.item.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.UAdd;
import net.megal.item.TooltipType;
import net.megal.item.UItem;
import net.megal.item.UItems;
import net.megal.item.modifier.Modifiers;
import net.megal.shader.ShaderLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

@Environment(EnvType.CLIENT)
public class ItemInfo {
    public static HashMap<Item, TooltipType> tooltipTypeLookup = new HashMap<>();

    public static void addTooltipTypes() {
        Field[] fields = UItems.class.getDeclaredFields();
        for (Field f : fields) {
            Object object;
            try {
                object = f.get(UItems.class);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            if (object instanceof Item item && !((UItem)item).UAdd$getModifiers().isEmpty()) {
                List<String> modifiers = ((UItem)item).UAdd$getModifiers();
                TooltipType tooltipType = null;

                if (modifiers.contains(Modifiers.AUTO_SMELTING)) tooltipType = TooltipType.FIRE;
                else if (modifiers.contains(Modifiers.CRYSTALLIZED_XP)) tooltipType = TooltipType.CRYSTAL;

                if (tooltipType != null) tooltipTypeLookup.put(item, tooltipType);
            }
        }
    }

    public static void add(Item item, TooltipType type) {
        tooltipTypeLookup.put(item, type);
    }
}
