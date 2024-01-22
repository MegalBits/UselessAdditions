package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.megal.uselessadditions.block.UBlocks;
import net.megal.uselessadditions.item.base.ScytheItem;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(TripwireHookBlock.class)
public class TripwireHookAcceptsPlantFibre {
    @WrapOperation(at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 1),
                method = "update")
    private static boolean isValidWire(BlockState instance, Block block, Operation<Boolean> original) {
        return original.call(instance, block) || instance.isOf(UBlocks.PLANT_FIBRE_TRIPWIRE);
    }
}
