package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.enchantment.UEnchantment;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;

@Mixin(Item.class)
public abstract class ItemTicking {
    @Inject(at = @At("HEAD"),
            method = "inventoryTick(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;IZ)V")
    private void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        boolean isEquippedArmor = false;
        int i = -1;
        if (entity instanceof PlayerEntity player) {
            for (ItemStack itemStack : player.getArmorItems()) {
                i++;
                if (itemStack == stack) {
                    isEquippedArmor = true;
                    break;
                }
            }
        }

        if (entity instanceof LivingEntity livingEntity) {
            if (stack.hasEnchantments() && isEquippedArmor) {
                Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
                for (Enchantment ench : enchantments.keySet()) {
                    int level = enchantments.get(ench);
                    if (ench instanceof UEnchantment uEnch && uEnch.tickEffectCondition(livingEntity, stack, level)) {
                        uEnch.equippedTick(livingEntity, stack, level);
                    }
                }
            }
        }
        if (!selected && !isEquippedArmor && entity instanceof ServerPlayerEntity player) {
            int repairingLevel = EnchantmentHelper.getLevel(UEnchantments.REPAIRING, stack);
            if (EnchantmentHelper.getLevel(UEnchantments.REPAIRING, stack) > 0) {
                NbtCompound tickNbt = stack.getOrCreateSubNbt("Ticking");
                int repTicks = 0;
                if (tickNbt.contains("RepairTicks")) {
                    repTicks = tickNbt.getInt("RepairTicks") + 1;
                    if (repTicks >= 600 / Math.max(repairingLevel, 1)) {
                        stack.damage(-1, Random.create(), player);
                        repTicks = 0;
                    }
                }
                tickNbt.putInt("RepairTicks", repTicks);
            }
        }
    }
//    public void addAttribute(LivingEntity entity, AugmentEnchantment.EquippedAttributeModifier eam, String string, int level) {
//        AttributeContainer attributes = entity.getAttributes();
//        EntityAttributeInstance entityAttributeInstance = attributes.getCustomInstance(eam.attribute());
//        EntityAttributeModifier entityAttributeModifier = new EntityAttributeModifier(UUID.fromString(eam.uuid()), string, eam.amount(), eam.operation());
//        if (entityAttributeInstance == null) return;
//        entityAttributeInstance.removeModifier(entityAttributeModifier);
//        entityAttributeInstance.addPersistentModifier(new EntityAttributeModifier(entityAttributeModifier.getId(), string + " " + level, adjustModifierAmount(level, entityAttributeModifier), entityAttributeModifier.getOperation()));
//    }
//    public void removeAttribute(LivingEntity entity, AugmentEnchantment.EquippedAttributeModifier eam, String string) {
//        AttributeContainer attributes = entity.getAttributes();
//        EntityAttributeModifier entityAttributeModifier = new EntityAttributeModifier(UUID.fromString(eam.uuid()), string, eam.amount(), eam.operation());
//        EntityAttributeInstance entityAttributeInstance = attributes.getCustomInstance(eam.attribute());
//        if (entityAttributeInstance == null) return;
//        entityAttributeInstance.removeModifier(entityAttributeModifier);
//    }
//    private double adjustModifierAmount(int amplifier, EntityAttributeModifier modifier) {
//        return modifier.getValue() * (double)(amplifier + 1);
//    }
}