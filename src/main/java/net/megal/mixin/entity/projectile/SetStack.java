package net.megal.mixin.entity.projectile;

import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PersistentProjectileEntity.class)
public interface SetStack {
    @Accessor("stack")
    @Mutable
    void setStack(ItemStack stack);
}
