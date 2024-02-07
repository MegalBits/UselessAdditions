package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

@Mixin(EnchantmentScreenHandler.class)
public abstract class StopSpecialEffectConflicts extends ScreenHandler {
    //target = "Lnet/minecraft/screen/EnchantmentScreenHandler;generateEnchantments(Lnet/minecraft/item/ItemStack;II)Ljava/util/List;", shift = At.Shift.AFTER
    @ModifyVariable(at = @At("STORE"),
            method = "method_17410(Lnet/minecraft/item/ItemStack;ILnet/minecraft/entity/player/PlayerEntity;ILnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V")
    private List<EnchantmentLevelEntry> getEntries(List<EnchantmentLevelEntry> list, ItemStack stack, int id, PlayerEntity player, int i, ItemStack stack2, World world, BlockPos pos) {
        List<EnchantmentLevelEntry> stuffToRemove = new ArrayList<>();
        for (String s : UItemHelper.getEffects(stack)) {
            if (SpecialEffects.effects.get(s).enchantments.contains(list.get(0).enchantment)) return List.of();
            for (EnchantmentLevelEntry entry : list) {
                if (SpecialEffects.effects.get(s).enchantments.contains(entry.enchantment)) {
                    //if (entry == list.get(0)) continue;
                    stuffToRemove.add(entry);
                }
            }
        }

        list.removeAll(stuffToRemove);

        return list;
    }

    protected StopSpecialEffectConflicts(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }
}
