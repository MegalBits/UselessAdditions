package net.megal.uselessadditions.worldgen;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.megal.uselessadditions.UAdd;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.function.Predicate;

public class UStructures {
    public static final StructureType<UMineshaftStructure> MINESHAFT = register(new Identifier(UAdd.MOD_ID,"mineshaft"), UMineshaftStructure.CODEC);

    private static <T extends Structure> StructureType<T> register(Identifier id, Codec<T> codec) {
        return Registry.register(Registries.STRUCTURE_TYPE, id, () -> codec);
    }

    public static void loadStuff() {}
}
