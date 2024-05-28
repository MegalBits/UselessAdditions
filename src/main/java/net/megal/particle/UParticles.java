package net.megal.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.megal.UAdd;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class UParticles {
    public static SimpleParticleType HEALTH_INCREASE = register("health_increase", true);

    public static SimpleParticleType register(String id, boolean alwaysSpawn) {
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(UAdd.ID, id), FabricParticleTypes.simple(alwaysSpawn));
    }

    public static void loadStuff() {}
}
