package net.megal.uselessadditions;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.megal.uselessadditions.item.UItems;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionTypes;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProviderType;
import net.minecraft.loot.provider.number.LootNumberProviderTypes;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ULootTables {
    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            //Global
            globalItemLootBuilder(id, UItems.EXOTIC_DUST, tableBuilder, source, 0.005F, 1, 1);
            globalItemLootBuilder(id, UItems.EXOTIC_INGOT, tableBuilder, source, 0.00025F, 1, 1);
            globalItemLootBuilder(id, UItems.MAGIC_BOOK, tableBuilder, source, 0.0001F, 1, 1);

            //Specifics
            lootBuilder(id, LootTables.NETHER_BRIDGE_CHEST, tableBuilder, source, LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(0.4F))
                    .with(ItemEntry.builder(UItems.FORTRESS_NUGGET).apply(uniformNumberBuilder(1,4)))
            );
            shardLootBuilder(id, EntityType.ALLAY, UItems.SMALL_ALLAY_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            entityLootBuilder(id, EntityType.ALLAY, tableBuilder, source, LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(0.07F))
                    .with(ItemEntry.builder(UItems.ALLAY_COOKIE)
            ));
            shardLootBuilder(id, EntityType.AXOLOTL, UItems.SMALL_AXOLOTL_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            entityLootBuilder(id, EntityType.AXOLOTL, tableBuilder, source, LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(0.9F))
                    .with(ItemEntry.builder(UItems.RAW_AXOLOTL).apply(uniformNumberBuilder(1,2)).apply(lootingFunction(0,1))
                    ));
            shardLootBuilder(id, EntityType.BAT, UItems.SMALL_BAT_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            entityLootBuilder(id, EntityType.BAT, tableBuilder, source, LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(0.86F))
                    .with(ItemEntry.builder(UItems.BAT_WING).apply(uniformNumberBuilder(1,2))
                    ));
            shardLootBuilder(id, EntityType.BEE, UItems.SMALL_BEE_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            entityLootBuilder(id, EntityType.BEE, tableBuilder, source, LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(0.02F))
                    .with(ItemEntry.builder(UItems.BEE_STINGER)
                    ));
            shardLootBuilder(id, EntityType.BLAZE, UItems.SMALL_BLAZE_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            entityLootBuilder(id, EntityType.BLAZE, tableBuilder, source, LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(0.04F))
                    .with(ItemEntry.builder(UItems.BLAZE_METAL)
                    ));
            shardLootBuilder(id, EntityType.CAT, UItems.SMALL_CAT_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.CAVE_SPIDER, UItems.SMALL_CAVE_SPIDER_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.CHICKEN, UItems.SMALL_CHICKEN_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.COD, UItems.SMALL_COD_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.COW, UItems.SMALL_COW_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.CREEPER, UItems.SMALL_CREEPER_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.DOLPHIN, UItems.SMALL_DOLPHIN_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.DONKEY, UItems.SMALL_DONKEY_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.DROWNED, UItems.SMALL_ZOMBIE_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.ENDERMAN, UItems.SMALL_ENDERMAN_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.ENDERMITE, UItems.SMALL_ENDERMITE_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.ELDER_GUARDIAN, UItems.SMALL_GUARDIAN_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.EVOKER, UItems.SMALL_ILLAGER_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            entityLootBuilder(id, EntityType.EVOKER, tableBuilder, source, LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(0.05F))
                    .with(ItemEntry.builder(UItems.MAGIC_BOOK))
                    );
            shardLootBuilder(id, EntityType.FOX, UItems.SMALL_FOX_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.FROG, UItems.SMALL_FROG_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.GHAST, UItems.SMALL_GHAST_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.GLOW_SQUID, UItems.SMALL_GLOW_SQUID_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.GOAT, UItems.SMALL_GOAT_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.GUARDIAN, UItems.SMALL_GUARDIAN_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.HOGLIN, UItems.SMALL_HOGLIN_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.HORSE, UItems.SMALL_HORSE_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.HUSK, UItems.SMALL_ZOMBIE_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.IRON_GOLEM, UItems.SMALL_IRON_GOLEM_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.LLAMA, UItems.SMALL_LLAMA_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.MAGMA_CUBE, UItems.SMALL_MAGMA_CUBE_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.OCELOT, UItems.SMALL_OCELOT_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.PANDA, UItems.SMALL_PARROT_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.PARROT, UItems.SMALL_PARROT_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.PHANTOM, UItems.SMALL_PHANTOM_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.PIG, UItems.SMALL_PIG_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.PIGLIN, UItems.SMALL_PIGLIN_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.PIGLIN_BRUTE, UItems.SMALL_PIGLIN_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.PILLAGER, UItems.SMALL_ILLAGER_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.POLAR_BEAR, UItems.SMALL_POLAR_BEAR_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.PUFFERFISH, UItems.SMALL_PUFFERFISH_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.RABBIT, UItems.SMALL_RABBIT_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.RAVAGER, UItems.SMALL_RAVAGER_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.SALMON, UItems.SMALL_SALMON_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.SHEEP, UItems.SMALL_SHEEP_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.SHULKER, UItems.SMALL_SHULKER_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.SILVERFISH, UItems.SMALL_SILVERFISH_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.SKELETON, UItems.SMALL_SKELETON_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.SLIME, UItems.SMALL_SLIME_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.SNOW_GOLEM, UItems.SMALL_SNOW_GOLEM_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.SPIDER, UItems.SMALL_SPIDER_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.SQUID, UItems.SMALL_SQUID_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.STRAY, UItems.SMALL_SKELETON_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.STRIDER, UItems.SMALL_STRIDER_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.TROPICAL_FISH, UItems.SMALL_TROPICAL_FISH_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.TURTLE, UItems.SMALL_TURTLE_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.VEX, UItems.SMALL_VEX_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.VILLAGER, UItems.SMALL_VILLAGER_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.VINDICATOR, UItems.SMALL_ILLAGER_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.WARDEN, UItems.SMALL_WARDEN_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.WITHER_SKELETON, UItems.SMALL_WITHER_SKELETON_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.WOLF, UItems.SMALL_WOLF_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.ZOGLIN, UItems.SMALL_HOGLIN_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.ZOGLIN, UItems.SMALL_ZOMBIE_SHARD, tableBuilder, source,
                    0.1f, 1, 1);
            shardLootBuilder(id, EntityType.ZOMBIE, UItems.SMALL_ZOMBIE_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.ZOMBIE_VILLAGER, UItems.SMALL_VILLAGER_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.ZOMBIE_VILLAGER, UItems.SMALL_ZOMBIE_SHARD, tableBuilder, source,
                    0.1f, 1, 1);
            shardLootBuilder(id, EntityType.ZOMBIFIED_PIGLIN, UItems.SMALL_PIGLIN_SHARD, tableBuilder, source,
                    0.2f, 1, 2);
            shardLootBuilder(id, EntityType.ZOMBIFIED_PIGLIN, UItems.SMALL_ZOMBIE_SHARD, tableBuilder, source,
                    0.1f, 1, 1);
        });
    }
    private static LootingEnchantLootFunction.Builder lootingFunction(int min, int max) {
        return LootingEnchantLootFunction.builder(uniformNumber(min,max));
    }
    private static ConditionalLootFunction.Builder<?> uniformNumberBuilder(int min, int max) {
        return SetCountLootFunction.builder(uniformNumber(min, max));
    }
    private static UniformLootNumberProvider uniformNumber(int min, int max) {
        return UniformLootNumberProvider.create(min, max);
    }
    private static void shardLootBuilder(Identifier id, EntityType<?> entity, Item item, LootTable.Builder tableBuilder, LootTableSource source, float chance, int min, int max) {
        entityLootBuilder(id, entity, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
    }
    private static void globalItemLootBuilder(Identifier id, Item item, LootTable.Builder tableBuilder, LootTableSource source, float chance, int min, int max) {
        lootBuilder(id, LootTables.ABANDONED_MINESHAFT_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.ANCIENT_CITY_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.ANCIENT_CITY_ICE_BOX_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.BASTION_BRIDGE_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.BASTION_HOGLIN_STABLE_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.BASTION_OTHER_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.BASTION_TREASURE_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.BURIED_TREASURE_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.DESERT_PYRAMID_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.END_CITY_TREASURE_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.IGLOO_CHEST_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.JUNGLE_TEMPLE_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.PILLAGER_OUTPOST_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.RUINED_PORTAL_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.SHIPWRECK_MAP_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.SHIPWRECK_SUPPLY_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.SHIPWRECK_TREASURE_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.SIMPLE_DUNGEON_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.STRONGHOLD_CORRIDOR_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.STRONGHOLD_CROSSING_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.STRONGHOLD_LIBRARY_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.UNDERWATER_RUIN_BIG_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.UNDERWATER_RUIN_SMALL_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.VILLAGE_ARMORER_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.VILLAGE_BUTCHER_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.VILLAGE_CARTOGRAPHER_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.VILLAGE_DESERT_HOUSE_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.VILLAGE_FISHER_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.VILLAGE_FLETCHER_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.VILLAGE_MASON_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.VILLAGE_PLAINS_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.VILLAGE_SAVANNA_HOUSE_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.VILLAGE_SHEPARD_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.VILLAGE_SNOWY_HOUSE_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.VILLAGE_TAIGA_HOUSE_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.VILLAGE_TANNERY_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.VILLAGE_TEMPLE_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.VILLAGE_TOOLSMITH_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
        lootBuilder(id, LootTables.VILLAGE_WEAPONSMITH_CHEST, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .with(ItemEntry.builder(item).apply(uniformNumberBuilder(min,max)))
        );
    }
    private static void lootBuilder(Identifier id, Identifier otherId, LootTable.Builder tableBuilder, LootTableSource source, LootPool.Builder builder) {
        if (source.isBuiltin() && otherId.equals(id)) {
            tableBuilder.pool(builder);
        }
    }
    private static void entityLootBuilder(Identifier id, EntityType<?> entity, LootTable.Builder tableBuilder, LootTableSource source, LootPool.Builder builder) {
        if (source.isBuiltin() && entity.getLootTableId().equals(id)) {
            tableBuilder.pool(builder);
        }
    }
}
