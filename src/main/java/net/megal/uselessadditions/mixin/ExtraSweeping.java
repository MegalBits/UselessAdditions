package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.megal.uselessadditions.item.base.ScytheItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(PlayerEntity.class)
public class ExtraSweeping {
    @WrapOperation(method = "attack(Lnet/minecraft/entity/Entity;)V",
            constant = @Constant(classValue = SwordItem.class))
    private boolean sweepingConditions(Object obj, Operation<Boolean> original) {
        return original.call(obj) || obj instanceof ScytheItem;
    }
}
