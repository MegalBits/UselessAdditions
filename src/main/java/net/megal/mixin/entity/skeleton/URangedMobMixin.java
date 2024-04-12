package net.megal.mixin.entity.skeleton;

import net.megal.entity.RangedMob;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public abstract class URangedMobMixin implements RangedMob {
    @Unique
    private Item projectileItem;

    @Override
    public void UAdd$setProjectile(Item item) {
        projectileItem = item;
    }

    @Override
    public Item UAdd$getProjectile() {
        return projectileItem;
    }

    @Inject(
            at = @At("TAIL"),
            method = "writeCustomDataToNbt"
    )
    private void writeProjectileItemToNbt(NbtCompound nbt, CallbackInfo ci) {
        if (projectileItem != null) nbt.putString("projectileItem", Registries.ITEM.getId(projectileItem).toString());
    }

    @Inject(
            at = @At("TAIL"),
            method = "readCustomDataFromNbt"
    )
    private void readProjectileItemFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("projectileItem")) projectileItem = Registries.ITEM.get(new Identifier(nbt.getString("projectileItem")));
    }
}
