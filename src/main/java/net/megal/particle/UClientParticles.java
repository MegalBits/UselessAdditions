package net.megal.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class UClientParticles {
    public static void registerParticles() {
        ParticleFactoryRegistry.getInstance().register(UParticles.HEALTH_INCREASE, HealthIncreaseParticle.Factory::new);
    }
}
