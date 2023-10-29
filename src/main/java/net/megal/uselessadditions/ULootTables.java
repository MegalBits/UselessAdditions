package net.megal.uselessadditions;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.megal.uselessadditions.enchantment.UEnchantment;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.megal.uselessadditions.item.UItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.*;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;

public class ULootTables {
    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            //Global
            globalItemLootBuilder(id, UItems.EXOTIC_DUST, tableBuilder, source, 0.005F, 1, 1);
            globalItemLootBuilder(id, UItems.EXOTIC_INGOT, tableBuilder, source, 0.00025F, 1, 1);
            globalItemLootBuilder(id, UItems.MAGIC_BOOK, tableBuilder, source, 0.0001F, 1, 1);

            //Specifics

            //  Chests
            lootBuilder(id, LootTables.ABANDONED_MINESHAFT_CHEST, tableBuilder, source, LootPool.builder()
                    .with(ItemEntry.builder(UItems.RUBY).weight(2))
                    .with(ItemEntry.builder(UItems.WEATHERED_SCRAP).weight(1))
                    .with(ItemEntry.builder(Items.AIR).weight(8))
            );
            lootBuilder(id, LootTables.ANCIENT_CITY_CHEST, tableBuilder, source, LootPool.builder()
                    .with(ItemEntry.builder(UItems.ANCIENT_SHARD).weight(1).apply(uniformNumberBuilder(1,3)))
                    .with(ItemEntry.builder(Items.AIR).weight(4))
            );
            lootBuilder(id, LootTables.BASTION_BRIDGE_CHEST, tableBuilder, source, LootPool.builder()
                    .with(ItemEntry.builder(UItems.NETHER_UPGRADE_FRAGMENT).weight(1).apply(uniformNumberBuilder(1,3)))
                    .with(ItemEntry.builder(Items.AIR).weight(5))
            );
            lootBuilder(id, LootTables.BASTION_HOGLIN_STABLE_CHEST, tableBuilder, source, LootPool.builder()
                    .with(ItemEntry.builder(UItems.NETHER_UPGRADE_FRAGMENT).weight(1).apply(uniformNumberBuilder(1,3)))
                    .with(ItemEntry.builder(Items.AIR).weight(5))
            );
            lootBuilder(id, LootTables.BASTION_OTHER_CHEST, tableBuilder, source, LootPool.builder()
                    .with(ItemEntry.builder(UItems.NETHER_UPGRADE_FRAGMENT).weight(1).apply(uniformNumberBuilder(1,3)))
                    .with(ItemEntry.builder(Items.AIR).weight(5))
            );
            lootBuilder(id, LootTables.BASTION_TREASURE_CHEST, tableBuilder, source, LootPool.builder()
                    .with(ItemEntry.builder(UItems.NETHER_UPGRADE_FRAGMENT).apply(uniformNumberBuilder(2,6)))
            );
            lootBuilder(id, LootTables.BURIED_TREASURE_CHEST, tableBuilder, source, LootPool.builder()
                    .with(ItemEntry.builder(UItems.WEATHERED_SCRAP).weight(1).apply(uniformNumberBuilder(1,2)))
                    .with(ItemEntry.builder(Items.AIR).weight(3))
            );
            lootBuilder(id, LootTables.END_CITY_TREASURE_CHEST, tableBuilder, source, LootPool.builder()
                    .with(ItemEntry.builder(UItems.END_UPGRADE_FRAGMENT).weight(5).apply(uniformNumberBuilder(1,2)))
                    .with(ItemEntry.builder(UItems.END_UPGRADE).weight(1))
                    .with(ItemEntry.builder(Items.AIR).weight(14))
            );
            lootBuilder(id, LootTables.END_CITY_TREASURE_CHEST, tableBuilder, source, LootPool.builder()
                    .with(ItemEntry.builder(UItems.DRAGON_SCALE).weight(1))
                    .with(ItemEntry.builder(Items.AIR).weight(19))
            );
            lootBuilder(id, LootTables.DESERT_PYRAMID_CHEST, tableBuilder, source, LootPool.builder()
                    .with(ItemEntry.builder(UItems.RUBY).weight(1))
                    .with(ItemEntry.builder(Items.AIR).weight(3))
            );
            lootBuilder(id, LootTables.JUNGLE_TEMPLE_CHEST, tableBuilder, source, LootPool.builder()
                    .with(ItemEntry.builder(UItems.WEATHERED_SCRAP).weight(1))
                    .with(ItemEntry.builder(Items.AIR).weight(2))
            );
            lootBuilder(id, LootTables.NETHER_BRIDGE_CHEST, tableBuilder, source, LootPool.builder()
                    .with(ItemEntry.builder(UItems.FORTRESS_NUGGET).weight(1).apply(uniformNumberBuilder(1,3)))
                    .with(ItemEntry.builder(Items.AIR).weight(4))
            );
            lootBuilder(id, LootTables.NETHER_BRIDGE_CHEST, tableBuilder, source, LootPool.builder()
                    .with(ItemEntry.builder(UItems.NETHER_UPGRADE_FRAGMENT).weight(1).apply(uniformNumberBuilder(1,2)))
                    .with(ItemEntry.builder(Items.AIR).weight(7))
            );
            lootBuilder(id, LootTables.SIMPLE_DUNGEON_CHEST, tableBuilder, source, LootPool.builder()
                    .with(ItemEntry.builder(UItems.RUBY).weight(1))
                    .with(ItemEntry.builder(Items.AIR).weight(7))
            );
            //  Mobs
            shardLootBuilder(id, EntityType.ALLAY, UItems.SMALL_ALLAY_SHARD, tableBuilder, source,
                    2);
            rareItemLootBuilder(id, EntityType.ALLAY, UItems.ALLAY_COOKIE, tableBuilder, source,
                    0.085F);
            shardLootBuilder(id, EntityType.AXOLOTL, UItems.SMALL_AXOLOTL_SHARD, tableBuilder, source,
                    2);
            entityLootBuilder(id, EntityType.AXOLOTL, tableBuilder, source, LootPool.builder()
                    .with(ItemEntry.builder(UItems.RAW_AXOLOTL).apply(uniformNumberBuilder(0,2)).apply(lootingFunction(0,1))
                    ));
            rareItemLootBuilder(id, EntityType.AXOLOTL, UItems.AXOLOTL_TAIL, tableBuilder, source,
                    0.05F);
            shardLootBuilder(id, EntityType.BAT, UItems.SMALL_BAT_SHARD, tableBuilder, source,
                    2);
            entityLootBuilder(id, EntityType.BAT, tableBuilder, source, LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(0.86F))
                    .with(ItemEntry.builder(UItems.BAT_WING).apply(uniformNumberBuilder(1,2))
                    ));
            shardLootBuilder(id, EntityType.BEE, UItems.SMALL_BEE_SHARD, tableBuilder, source,
                    2);
            rareItemLootBuilder(id, EntityType.BEE, UItems.BEE_STINGER, tableBuilder, source,
                    0.02F);
            shardLootBuilder(id, EntityType.BLAZE, UItems.SMALL_BLAZE_SHARD, tableBuilder, source,
                    2);
            rareItemLootBuilder(id, EntityType.BLAZE, UItems.BLAZE_METAL, tableBuilder, source,
                    0.04F);
            entityLootBuilder(id, EntityType.BLAZE, tableBuilder, source, LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(0.25F))
                    .conditionally(KilledByPlayerLootCondition.builder())
                    .with(ItemEntry.builder(UItems.ASH)
                    ));
            shardLootBuilder(id, EntityType.CAT, UItems.SMALL_CAT_SHARD, tableBuilder, source,
                    2);
            rareItemLootBuilder(id, EntityType.CAT, UItems.TOY_FISH, tableBuilder, source,
                    0.02F);
            shardLootBuilder(id, EntityType.CAVE_SPIDER, UItems.SMALL_CAVE_SPIDER_SHARD, tableBuilder, source,
                    2);
            rareItemLootBuilder(id, EntityType.CAVE_SPIDER, UItems.POSION_GLAND, tableBuilder, source,
                    0.01F);
            shardLootBuilder(id, EntityType.CHICKEN, UItems.SMALL_CHICKEN_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.COD, UItems.SMALL_COD_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.COW, UItems.SMALL_COW_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.CREEPER, UItems.SMALL_CREEPER_SHARD, tableBuilder, source,
                    2);
            entityLootBuilder(id, EntityType.CREEPER, tableBuilder, source, LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(0.10F))
                    .with(ItemEntry.builder(UItems.ASH)
                    ));
            shardLootBuilder(id, EntityType.DOLPHIN, UItems.SMALL_DOLPHIN_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.DONKEY, UItems.SMALL_DONKEY_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.DROWNED, UItems.SMALL_ZOMBIE_SHARD, tableBuilder, source,
                    2);
            rareItemLootBuilder(id, EntityType.DROWNED, UItems.ESSENCE_OF_UNDEAD, tableBuilder, source,
                    0.005f);
            shardLootBuilder(id, EntityType.ENDERMAN, UItems.SMALL_ENDERMAN_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.ENDERMITE, UItems.SMALL_ENDERMITE_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.ELDER_GUARDIAN, UItems.SMALL_GUARDIAN_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.EVOKER, UItems.SMALL_ILLAGER_SHARD, tableBuilder, source,
                    2);
            rareItemLootBuilder(id, EntityType.EVOKER, UItems.MAGIC_BOOK, tableBuilder, source,
                    0.04F);
            rareItemLootBuilder(id, EntityType.EVOKER, UItems.EMPOWERED_EMERALD, tableBuilder, source,
                    0.05125F);
            shardLootBuilder(id, EntityType.FOX, UItems.SMALL_FOX_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.FROG, UItems.SMALL_FROG_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.GHAST, UItems.SMALL_GHAST_SHARD, tableBuilder, source,
                    2);
            entityLootBuilder(id, EntityType.GHAST, tableBuilder, source, LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(0.65F))
                    .with(ItemEntry.builder(UItems.ASH)
                    ));
            shardLootBuilder(id, EntityType.GLOW_SQUID, UItems.SMALL_GLOW_SQUID_SHARD, tableBuilder, source,
                    2);
            rareItemLootBuilder(id, EntityType.GLOW_SQUID, UItems.BIG_GLOW_INK_SAC, tableBuilder, source,
                    0.01F);
            shardLootBuilder(id, EntityType.GOAT, UItems.SMALL_GOAT_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.GUARDIAN, UItems.SMALL_GUARDIAN_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.HOGLIN, UItems.SMALL_HOGLIN_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.HORSE, UItems.SMALL_HORSE_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.HUSK, UItems.SMALL_ZOMBIE_SHARD, tableBuilder, source,
                    2);
            rareItemLootBuilder(id, EntityType.HUSK, UItems.ARID_CLOTH, tableBuilder, source,
                    0.02F);
            rareItemLootBuilder(id, EntityType.HUSK, UItems.ESSENCE_OF_UNDEAD, tableBuilder, source,
                    0.005f);
            shardLootBuilder(id, EntityType.IRON_GOLEM, UItems.SMALL_IRON_GOLEM_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.LLAMA, UItems.SMALL_LLAMA_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.MAGMA_CUBE, UItems.SMALL_MAGMA_CUBE_SHARD, tableBuilder, source,
                    2);
            entityLootBuilder(id, EntityType.MAGMA_CUBE, tableBuilder, source, LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(0.25F))
                    .with(ItemEntry.builder(UItems.ASH)
                    ));
            shardLootBuilder(id, EntityType.OCELOT, UItems.SMALL_OCELOT_SHARD, tableBuilder, source,
                    2);
            rareItemLootBuilder(id, EntityType.OCELOT, UItems.TOY_FISH, tableBuilder, source,
                    0.02F);
            shardLootBuilder(id, EntityType.PANDA, UItems.SMALL_PARROT_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.PARROT, UItems.SMALL_PARROT_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.PHANTOM, UItems.SMALL_PHANTOM_SHARD, tableBuilder, source,
                    2);
            rareItemLootBuilder(id, EntityType.PHANTOM, UItems.PHANTOM_HEART, tableBuilder, source,
                    0.035F);
            rareItemLootBuilder(id, EntityType.PHANTOM, UItems.ESSENCE_OF_UNDEAD, tableBuilder, source,
                    0.005f);
            shardLootBuilder(id, EntityType.PIG, UItems.SMALL_PIG_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.PIGLIN, UItems.SMALL_PIGLIN_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.PIGLIN_BRUTE, UItems.SMALL_PIGLIN_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.PILLAGER, UItems.SMALL_ILLAGER_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.POLAR_BEAR, UItems.SMALL_POLAR_BEAR_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.PUFFERFISH, UItems.SMALL_PUFFERFISH_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.RABBIT, UItems.SMALL_RABBIT_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.RAVAGER, UItems.SMALL_RAVAGER_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.SALMON, UItems.SMALL_SALMON_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.SHEEP, UItems.SMALL_SHEEP_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.SHULKER, UItems.SMALL_SHULKER_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.SILVERFISH, UItems.SMALL_SILVERFISH_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.SKELETON, UItems.SMALL_SKELETON_SHARD, tableBuilder, source,
                    2);
            rareItemLootBuilder(id, EntityType.SKELETON, UItems.ESSENCE_OF_UNDEAD, tableBuilder, source,
                    0.005f);
            shardLootBuilder(id, EntityType.SLIME, UItems.SMALL_SLIME_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.SNOW_GOLEM, UItems.SMALL_SNOW_GOLEM_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.SPIDER, UItems.SMALL_SPIDER_SHARD, tableBuilder, source,
                    2);
            rareItemLootBuilder(id, EntityType.SPIDER, UItems.POSION_GLAND, tableBuilder, source,
                    0.01F);
            shardLootBuilder(id, EntityType.SQUID, UItems.SMALL_SQUID_SHARD, tableBuilder, source,
                    2);
            rareItemLootBuilder(id, EntityType.SQUID, UItems.BIG_INK_SAC, tableBuilder, source,
                    0.01F);
            shardLootBuilder(id, EntityType.STRAY, UItems.SMALL_SKELETON_SHARD, tableBuilder, source,
                    2);
            rareItemLootBuilder(id, EntityType.STRAY, UItems.FRIGID_CLOTH, tableBuilder, source,
                    0.02F);
            rareItemLootBuilder(id, EntityType.STRAY, UItems.ESSENCE_OF_UNDEAD, tableBuilder, source,
                    0.005f);
            shardLootBuilder(id, EntityType.STRIDER, UItems.SMALL_STRIDER_SHARD, tableBuilder, source,
                    2);
            entityLootBuilder(id, EntityType.STRIDER, tableBuilder, source, LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(0.2F))
                    .with(ItemEntry.builder(UItems.ASH)
                    ));
            shardLootBuilder(id, EntityType.TROPICAL_FISH, UItems.SMALL_TROPICAL_FISH_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.TURTLE, UItems.SMALL_TURTLE_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.VEX, UItems.SMALL_VEX_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.VILLAGER, UItems.SMALL_VILLAGER_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.VINDICATOR, UItems.SMALL_ILLAGER_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.WARDEN, UItems.SMALL_WARDEN_SHARD, tableBuilder, source,
                    2);
            entityLootBuilder(id, EntityType.WARDEN, tableBuilder, source, LootPool.builder()
                    .conditionally(KilledByPlayerLootCondition.builder())
                    .with(ItemEntry.builder(UItems.ANCIENT_SHARD).apply(uniformNumberBuilder(4,16))
                    ));
            shardLootBuilder(id, EntityType.WITHER_SKELETON, UItems.SMALL_WITHER_SKELETON_SHARD, tableBuilder, source,
                    2);
            entityLootBuilder(id, EntityType.WITHER_SKELETON, tableBuilder, source, LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(0.85F))
                    .with(ItemEntry.builder(UItems.ASH)
                    ));
            rareItemLootBuilder(id, EntityType.WITHER_SKELETON, Items.WITHER_ROSE, tableBuilder, source,
                    0.00225F);
            rareItemLootBuilder(id, EntityType.WITHER_SKELETON, UItems.ESSENCE_OF_UNDEAD, tableBuilder, source,
                    0.005f);
            shardLootBuilder(id, EntityType.WOLF, UItems.SMALL_WOLF_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.ZOGLIN, UItems.SMALL_HOGLIN_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.ZOGLIN, UItems.SMALL_ZOMBIE_SHARD, tableBuilder, source,
                    1);
            rareItemLootBuilder(id, EntityType.ZOGLIN, UItems.ESSENCE_OF_UNDEAD, tableBuilder, source,
                    0.0025f);
            shardLootBuilder(id, EntityType.ZOMBIE, UItems.SMALL_ZOMBIE_SHARD, tableBuilder, source,
                    2);
            rareItemLootBuilder(id, EntityType.ZOMBIE, UItems.WEATHERED_SCRAP, tableBuilder, source,
                    0.03F);
            rareItemLootBuilder(id, EntityType.ZOMBIE, UItems.ESSENCE_OF_UNDEAD, tableBuilder, source,
                    0.005f);
            shardLootBuilder(id, EntityType.ZOMBIE_VILLAGER, UItems.SMALL_VILLAGER_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.ZOMBIE_VILLAGER, UItems.SMALL_ZOMBIE_SHARD, tableBuilder, source,
                    1);
            rareItemLootBuilder(id, EntityType.ZOMBIE_VILLAGER, UItems.WEATHERED_SCRAP, tableBuilder, source,
                    0.0375F);
            rareItemLootBuilder(id, EntityType.ZOMBIE_VILLAGER, UItems.ESSENCE_OF_UNDEAD, tableBuilder, source,
                    0.005f);
            shardLootBuilder(id, EntityType.ZOMBIFIED_PIGLIN, UItems.SMALL_PIGLIN_SHARD, tableBuilder, source,
                    2);
            shardLootBuilder(id, EntityType.ZOMBIFIED_PIGLIN, UItems.SMALL_ZOMBIE_SHARD, tableBuilder, source,
                    1);
            rareItemLootBuilder(id, EntityType.ZOMBIFIED_PIGLIN, UItems.ESSENCE_OF_UNDEAD, tableBuilder, source,
                    0.005f);
        });
    }
    private static LootingEnchantLootFunction.Builder lootingFunction(int min, int max) {
        return LootingEnchantLootFunction.builder(uniformNumber(min,max));
    }
    private static ItemPredicate.Builder itemWithEnchantment(Enchantment enchantment) {
        return ItemPredicate.Builder.create()
                .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1)));
    }
    private static ConditionalLootFunction.Builder<?> uniformNumberBuilder(int min, int max) {
        return SetCountLootFunction.builder(uniformNumber(min, max));
    }
    private static UniformLootNumberProvider uniformNumber(int min, int max) {
        return UniformLootNumberProvider.create(min, max);
    }
    private static ConstantLootNumberProvider constantNumber(int value) {
        return ConstantLootNumberProvider.create(value);
    }
    private static void rareItemLootBuilder(Identifier id, EntityType<?> entity, Item item, LootTable.Builder tableBuilder, LootTableSource source, float chance) {
        entityLootBuilder(id, entity, tableBuilder, source, LootPool.builder()
                .conditionally(RandomChanceLootCondition.builder(chance))
                .conditionally(KilledByPlayerLootCondition.builder())
                .with(ItemEntry.builder(item))
        );
    }
    private static void shardLootBuilder(Identifier id, EntityType<?> entity, Item item, LootTable.Builder tableBuilder, LootTableSource source, int max) {
        entityLootBuilder(id, entity, tableBuilder, source, LootPool.builder()
                .conditionally(KilledByPlayerLootCondition.builder())
                .with(ItemEntry.builder(item)
                        .apply(uniformNumberBuilder(0,max))
                        .apply(ApplyBonusLootFunction.uniformBonusCount(UEnchantments.SHATTERING, 2))
                )
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
