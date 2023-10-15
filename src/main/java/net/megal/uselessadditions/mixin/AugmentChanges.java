package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.enchantment.AugmentEnchantment;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class AugmentChanges {

    @Overwrite
    public boolean hasGlint(ItemStack stack) {
        for (Enchantment enchantment : EnchantmentHelper.get(stack).keySet()) {
            if (!(enchantment instanceof AugmentEnchantment)) return true;
        }
        return false;
    }

    @Inject(at = @At("HEAD"),
            method = "inventoryTick(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;IZ)V",
            cancellable = false)
    private void repairItemTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (entity instanceof ServerPlayerEntity player && !selected) {
            int repairingLevel = EnchantmentHelper.getLevel(UEnchantments.REPAIRING, stack);
            if (EnchantmentHelper.getLevel(UEnchantments.REPAIRING, stack) > 0) {
                NbtCompound nbt = stack.getOrCreateNbt();
                int repTicks = 0;
                if (nbt.contains("repairticks")) {
                    repTicks = nbt.getInt("repairticks") + 1;
                    if (repTicks >= 600 / Math.max(repairingLevel, 1)) {
                        stack.damage(-1, Random.create(), player);
                        repTicks = 0;
                    }
                }
                nbt.putInt("repairticks", repTicks);

            }
        }
    }

}