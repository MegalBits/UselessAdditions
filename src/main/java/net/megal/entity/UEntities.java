package net.megal.entity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.megal.UAdd;
import net.minecraft.entity.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;

public class UEntities {

    public static final EntityType<PullingFishingBobberEntity> PULLING_FISHING_BOBBER = register("pulling_fishing_bobber", EntityType.Builder.<PullingFishingBobberEntity>create(PullingFishingBobberEntity::new, SpawnGroup.MISC)
            .disableSaving()
            .disableSummon()
            .dimensions(0.25F, 0.25F)
            .maxTrackingRange(4)
            .trackingTickInterval(5)
            .build()
    );

    public static final EntityType<SkeletonRogueEntity> SKELETON_ROGUE = register("skeleton_rogue", FabricEntityType.Builder.createMob(SkeletonRogueEntity::new, SpawnGroup.MONSTER, mob -> mob
            .defaultAttributes(SkeletonRogueEntity::createSkeletonRogueAttributes)
            .spawnRestriction(SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SkeletonRogueEntity::canSpawnInDark))
            .dimensions(0.6F, 1.99F)
            .eyeHeight(1.74F)
            .vehicleAttachment(-0.7F)
            .maxTrackingRange(12)
            .build()
    );

    public static <T extends Entity> EntityType<T> register(String id, EntityType<T> entityType) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(UAdd.ID, id), entityType);
    }


    public static void registerSpawning() {
        BiomeModifications.addSpawn(
                BiomeSelectors.foundInOverworld(),
                SpawnGroup.MONSTER,
                SKELETON_ROGUE,
                60,
                2,
                4);
    }
}
