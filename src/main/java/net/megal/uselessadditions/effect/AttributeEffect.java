package net.megal.uselessadditions.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class AttributeEffect extends StatusEffect {
    protected AttributeEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
}
