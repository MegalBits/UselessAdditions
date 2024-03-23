package net.megal.mixin.item.modifier;

import net.megal.item.modifier.Modifier;
import net.megal.item.modifier.Modifiers;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

@Mixin(EnchantmentScreenHandler.class)
public abstract class ApplyModifierEnchantingIncompatibilities {
    @ModifyVariable(
            at = @At("STORE"),
            method = "method_17410"
    )
    private List<EnchantmentLevelEntry> getEntries(List<EnchantmentLevelEntry> list, ItemStack stack, int id, PlayerEntity player, int i, ItemStack stack2, World world, BlockPos pos) {
        List<EnchantmentLevelEntry> stuffToRemove = new ArrayList<>();
        for (String modifierId : Modifiers.getModifiersFromStack(stack)) {
            Modifier modifier = Modifiers.getModifier(modifierId);

            if (!modifier.isCompatibleWith(list.get(0).enchantment)) return List.of();
            for (EnchantmentLevelEntry entry : list) {
                if (!modifier.isCompatibleWith(entry.enchantment)) {
                    stuffToRemove.add(entry);
                }
            }
        }

        list.removeAll(stuffToRemove);

        return list;
    }
}
