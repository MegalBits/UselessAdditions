package net.megal.mixin.entity;

import net.minecraft.entity.attribute.EntityAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EntityAttributes.class)
public abstract class IncreaseMaxArmorValue {
    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/attribute/ClampedEntityAttribute;<init>(Ljava/lang/String;DDD)V",
                    ordinal = 0
            ),
            method = "<clinit>",
            index = 3
    )
    private static double setMaxValue(double max) {
        return 80;
    }
}
