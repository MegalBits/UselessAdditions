package net.megal.uselessadditions.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record SlimePileConfig(Identifier blockId) implements FeatureConfig {
    public static final Codec<SlimePileConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Identifier.CODEC.fieldOf("block").forGetter(SlimePileConfig::blockId)
                    ).apply(instance, SlimePileConfig::new));
}
