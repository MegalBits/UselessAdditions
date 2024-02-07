package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.megal.uselessadditions.item.base.UBowItem;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.text.DecimalFormat;
import java.util.List;

import static net.megal.uselessadditions.item.SpecialEffects.*;

@Mixin(ItemStack.class)
public abstract class BuiltinEffectsText {
    @Inject(at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"
            ),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;hasNbt()Z")
            ),
            method = "getTooltip(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/client/item/TooltipContext;)Ljava/util/List;")
    private void appendBuiltinTooltips(@Nullable PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> cir, @Local(ordinal = 0) List<Text> list) {
        ItemStack stack = (ItemStack)(Object)this;
        List<Text> effectsText = UItemHelper.effectsText(stack, List.of());
        if (!effectsText.isEmpty()) list.addAll(effectsText);
        if (UItemHelper.getEffects(stack).contains(CRYSTALLIZED_XP) && player != null) {
            DecimalFormat df = new DecimalFormat("#.##");
            list.add(Text.empty());
            MutableText levelProviding = Text.literal(String.valueOf(Math.min(player.experienceLevel, 80))).append(Text.literal(" ")).append(Text.translatable("specialeffect.uselessadditions.crystallized_xp.description")).formatted(Formatting.GRAY);
            if (player.experienceLevel >= 80) levelProviding = Text.translatable("specialeffect.uselessadditions.crystallized_xp.description.max").formatted(Formatting.DARK_GRAY).append(Text.literal(" " + Math.min(player.experienceLevel, 80)).append(Text.literal(" ")).append(Text.translatable("specialeffect.uselessadditions.crystallized_xp.description")).formatted(Formatting.GRAY));
            list.add(levelProviding);
            if (stack.getItem() instanceof UBowItem bowItem) {
                list.add(Text.literal(" ").append(Text.literal(df.format((Math.min(player.experienceLevel, 80) * CXP_ARROW_SPEED) / bowItem.speed * 100))).append(Text.literal("% ")).append(Text.translatable("stats.uselessadditions.projectile_speed")).formatted(Formatting.DARK_GRAY));
                list.add(Text.literal(" ").append(Text.literal(df.format((Math.min(player.experienceLevel, 80) * CXP_ACCURACY) / bowItem.accuracy * 100))).append(Text.literal("% ")).append(Text.translatable("stats.uselessadditions.accuracy")).formatted(Formatting.DARK_GRAY));
                list.add(Text.literal(" ").append(Text.literal(df.format((Math.min(player.experienceLevel, 80) * CXP_CHARGE_SPEED) / bowItem.useTime * 100))).append(Text.literal("% ")).append(Text.translatable("stats.uselessadditions.charge_time")).formatted(Formatting.DARK_GRAY));
            }
            else {
                list.add(Text.literal(" ").append(Text.literal(df.format(Math.min(player.experienceLevel, 80) * CXP_DAMAGE))).append(Text.literal(" ")).append(Text.translatable("stats.uselessadditions.damage")).formatted(Formatting.DARK_GRAY));
                list.add(Text.literal(" ").append(Text.literal(df.format(Math.min(player.experienceLevel, 80) * CXP_EFFICIENCY))).append(Text.literal(" ")).append(Text.translatable("stats.uselessadditions.efficiency")).formatted(Formatting.DARK_GRAY));
                int i = (int) Math.min(Math.floor(player.experienceLevel / CXP_MINING_LEVEL_REQ), CXP_MINING_LEVEL_CAP);
                MutableText miningLevel = Text.literal(" ").append(Text.literal(String.valueOf(i))).append(Text.literal(" ")).append(Text.translatable("stats.uselessadditions.mining_level"));
                if (i != 1) miningLevel.append(Text.literal("s"));
                miningLevel.formatted(Formatting.DARK_GRAY);
                list.add(miningLevel);
            }
        }
    }

    @ModifyVariable(at = @At("STORE"),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/EntityAttributeModifier;getId()Ljava/util/UUID;"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getAttackDamage(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityGroup;)F")
            ),
            method = "getTooltip(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/client/item/TooltipContext;)Ljava/util/List;", ordinal = 0)
    private double modifyAttackDamage(double d, @Nullable PlayerEntity player, TooltipContext context) {
        ItemStack stack = (ItemStack)(Object)this;
        if (player != null && UItemHelper.getEffects(stack).contains(CRYSTALLIZED_XP)) d += Math.min(player.experienceLevel, 80) * CXP_DAMAGE;
        return d;
    }
}
