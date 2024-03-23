package net.megal.mixin.entity.projectile;

import net.megal.UAdd;
import net.megal.item.HasProjectileStats;
import net.megal.item.UItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class ApplyProjectileEffects {
    @Shadow private double damage;

    @Inject(
            at = @At("TAIL"),
            method = "<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;)V"
    )
    private void applyEffects(EntityType<? extends PersistentProjectileEntity> type, World world, ItemStack stack, CallbackInfo ci) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity)(Object)this;

        if (stack.getItem() instanceof HasProjectileStats projectileStats) {
            damage += projectileStats.getDamage();
        }
        
        if (stack.isOf(UItems.BLAZE_METAL_ARROW)) projectile.setOnFireFor(100);
    }
}
