package net.megal.loot;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.megal.item.UItems;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;

public class ULootTableModifications {
    public static void modify() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            Set set = new Set(key, tableBuilder, source);

            addPool(set, LootTables.BASTION_TREASURE_CHEST, LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1, 1))
                    .with(item(UItems.PLATED_HEART))
                    .with(empty(4))
            );
        });
    }

    private static ItemEntry.Builder<?> item(ItemConvertible item) {
        return ItemEntry.builder(item);
    }

    private static ItemEntry.Builder<?> item(ItemConvertible item, int weight) {
        return ItemEntry.builder(item).weight(weight);
    }

    private static ItemEntry.Builder<?> empty(int weight) {
        return ItemEntry.builder(Items.AIR).weight(weight);
    }

    private static void addPool(Set set, RegistryKey<LootTable> key, LootPool.Builder builder) {
        if (set.source.isBuiltin() && set.key == key) {
            set.tableBuilder.pool(builder);
        }
    }

    private record Set(RegistryKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source) {}
}
