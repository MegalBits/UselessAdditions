package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.item.UItems;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class ItemEntityCanMerge {
    @Shadow
    private int pickupDelay;
    @Inject(at = @At("HEAD"),
            method = "canMerge()Z",
            cancellable = true)
    private void canMerge(CallbackInfoReturnable<Boolean> cir) {
        ItemEntity item = ((ItemEntity)(Object)this);
        if (pickupDelay - 15 > 0) cir.setReturnValue(false);
    }
}