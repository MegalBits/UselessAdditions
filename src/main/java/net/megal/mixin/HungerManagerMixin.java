package net.megal.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.player.HungerManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HungerManager.class)
public abstract class HungerManagerMixin {
    @Shadow
    private int foodLevel;
    @Shadow
    private int prevFoodLevel;
    @Shadow
    private float saturationLevel;

    @Unique
    private static final int maxFoodLevel = 80;
    @Unique
    private static final int healFoodLevel = 60;

    @Inject(
            at = @At("TAIL"),
            method = "<init>"
    )
    private void changeMaxValues(CallbackInfo ci) {
        foodLevel = maxFoodLevel;
        prevFoodLevel = maxFoodLevel;
        saturationLevel = maxFoodLevel / 5f;
    }

    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Math;min(II)I"
            ),
            method = "addInternal",
            index = 1
    )
    private int limitFoodToChangedMaxValue(int original) {
        return maxFoodLevel;
    }

    @ModifyExpressionValue(
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=20"
            ),
            method = "isNotFull"
    )
    private int notFullUntilChangedMaxValue(int original) {
        return maxFoodLevel;
    }

    @ModifyExpressionValue(
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=20"
            ),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;canFoodHeal()Z"),
                    to = @At(value = "INVOKE", target = "Ljava/lang/Math;min(FF)F")
            ),
            method = "update"
    )
    private int healWithSaturationAboveChangedMaxValue(int original) {
        return maxFoodLevel;
    }

    @ModifyExpressionValue(
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=18"
            ),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;addExhaustion(F)V"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;canFoodHeal()Z", ordinal = 1)
            ),
            method = "update"
    )
    private int healWhileAboveChangedHealValue(int original) {
        return healFoodLevel;
    }
}
