package net.megal.uselessadditions.entity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.megal.uselessadditions.UAdd;
import net.minecraft.entity.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;

public class UEntities {

    public static final EntityType<FrigidZombieEntity> FRIGID_ZOMBIE = registerEntity(new Identifier(UAdd.MOD_ID, "frigid_zombie"), FabricEntityTypeBuilder.createMob()
            .entityFactory(FrigidZombieEntity::new)
            .spawnGroup(SpawnGroup.MONSTER)
            .spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FrigidZombieEntity::canSpawn)
            .dimensions(EntityDimensions.fixed(0.6f, 1.95f))
            .build());

    public static <T extends Entity> EntityType<T> registerEntity(Identifier id, EntityType<T> entityType) {
        return Registry.register(Registries.ENTITY_TYPE, id, entityType);
    }

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(FRIGID_ZOMBIE, FrigidZombieEntity.createZombieAttributes());
    }

    public static void registerSpawning() {
        BiomeModifications.addSpawn(
                BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, new Identifier(UAdd.MOD_ID, "snow_spawns"))),
                SpawnGroup.MONSTER,
                FRIGID_ZOMBIE,
                55,
                1,
                2);
    }
}
