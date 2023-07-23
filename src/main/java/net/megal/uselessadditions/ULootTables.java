package net.megal.uselessadditions;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.megal.uselessadditions.item.UItems;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ULootTables {
    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && LootTables.NETHER_BRIDGE_CHEST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.4F))
                        .with(ItemEntry.builder(UItems.FORTRESS_NUGGET).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 4))));
                tableBuilder.pool(poolBuilder);
            }
        });
    }
}
