package net.megal;

import net.fabricmc.api.ClientModInitializer;
import net.megal.block.UBlockRendering;
import net.megal.entity.UEntityRendering;
import net.megal.network.UClientNetworking;
import net.megal.particle.UClientParticles;

public class UAddClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        UEntityRendering.registerEntityRenderers();
        UBlockRendering.registerBlockLayers();
        UClientNetworking.registerReceivers();
        UClientParticles.registerParticles();
    }
}
