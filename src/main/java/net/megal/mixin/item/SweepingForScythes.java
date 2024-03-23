package net.megal.mixin.item;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.megal.item.ScytheItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(PlayerEntity.class)
public abstract class SweepingForScythes {
    @WrapOperation(
            method = "attack",
            constant = @Constant(classValue = SwordItem.class)
    )
    private boolean sweepingConditions(Object obj, Operation<Boolean> original) {
        return original.call(obj) || obj instanceof ScytheItem;
    }
}
