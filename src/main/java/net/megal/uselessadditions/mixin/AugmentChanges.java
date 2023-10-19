package net.megal.uselessadditions.mixin;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.enchantment.AugmentEnchantment;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.trim.ArmorTrim;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
                if (nbt.contains("RepairTicks")) {
                    repTicks = nbt.getInt("RepairTicks") + 1;
                    if (repTicks >= 600 / Math.max(repairingLevel, 1)) {
                        stack.damage(-1, Random.create(), player);
                        repTicks = 0;
                    }
                }
                nbt.putInt("RepairTicks", repTicks);
            }
        }
    }



}