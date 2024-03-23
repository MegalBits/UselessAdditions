package net.megal.mixin.entity.player;

import net.minecraft.entity.player.HungerManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HungerManager.class)
public abstract class IncreaseMaxHunger {
    @Shadow private int foodLevel;
    @Shadow private int prevFoodLevel;

    @Unique
    private static final int maxFoodLevel = 40;

    @Unique
    private static final int healFoodLevel = 30;

    @Inject(
            at = @At("TAIL"),
            method = "<init>"
    )
    private void changeMaxValues(CallbackInfo ci) {
        foodLevel = maxFoodLevel;
        prevFoodLevel = maxFoodLevel;
    }

    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Math;min(II)I",
                    ordinal = 0
            ),
            method = "add",
            index = 1
    )
    private int limitToChangedMaxValue(int original) {
        return maxFoodLevel;
    }

    @ModifyConstant(
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;canFoodHeal()Z"),
                    to = @At(value = "INVOKE", target = "Ljava/lang/Math;min(FF)F")
            ),
            method = "update",
            constant = @Constant(intValue = 20)
    )
    private int applyIfOverChangedMaxValue(int constant) {
        return maxFoodLevel;
    }

    @ModifyConstant(
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;addExhaustion(F)V"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;canFoodHeal()Z", ordinal = 1)
            ),
            method = "update",
            constant = @Constant(intValue = 18)
    )
    private int healIfOverChangedHealValue(int constant) {
        return healFoodLevel;
    }

    @ModifyConstant(
            method = "isNotFull",
            constant = @Constant(intValue = 20)
    )
    private int increaseToChangedMaxValue(int constant) {
        return maxFoodLevel;
    }
}
