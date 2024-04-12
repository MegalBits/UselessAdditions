package net.megal.mixin.entity.skeleton;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.megal.UAdd;
import net.megal.entity.RangedMob;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HostileEntity.class)
public abstract class ReadRangedMobProjectile {
    @ModifyExpressionValue(
        at = @At(
                value = "FIELD",
                target = "Lnet/minecraft/item/Items;ARROW:Lnet/minecraft/item/Item;"
        ),
        method = "getProjectileType"
    )
    private Item changeProjectile(Item original) {
        HostileEntity hostileEntity = ((HostileEntity)(Object)this);
        if (((RangedMob)hostileEntity).UAdd$getProjectile() != null) {
            return ((RangedMob)hostileEntity).UAdd$getProjectile();
        }

        return original;
    }
}
