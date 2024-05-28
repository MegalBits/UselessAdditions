package net.megal.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {
    @ModifyExpressionValue(
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=6.0"
            ),
            method = "canSprint"
    )
    private float canSprintAtOnePorkchopFull(float original) {
        return 8f;
    }
}
