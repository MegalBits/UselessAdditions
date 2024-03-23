package net.megal.mixin.item.modifier;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.megal.item.UItem;
import net.megal.item.modifier.Modifiers;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.megal.item.modifier.Modifiers.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mixin(ItemStack.class)
public abstract class DisplayModifiers {
    @Unique
    private final int cxp_color = 0xcfa0f3;

    @Inject(
            at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"
            ),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;hasNbt()Z")
            ),
            method = "getTooltip(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/client/item/TooltipContext;)Ljava/util/List;"
    )
    private void appendBuiltinTooltips(@Nullable PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> cir, @Local(ordinal = 0) List<Text> list) {
        ItemStack stack = (ItemStack)(Object)this;
        List<String> modifiers = getModifiersFromStack(stack);

        list.addAll(getModifierText(stack, modifiers));

        if (modifiers.contains(CRYSTALLIZED_XP) && player != null) {
            DecimalFormat df = new DecimalFormat("#.##");
            int level = Math.min(player.experienceLevel, 100);

            list.add(Text.empty());
            list.add(
                    Text.literal(level + "/100 ")
                            .append(Text.translatable("uselessadditions.modifiers.crystallized_xp.providing"))
                            .append(Text.literal(":"))
                            .formatted(Formatting.GRAY)
            );
            list.add(
                    Text.literal(" " + df.format(cxp_damageAmount * level) + " ")
                            .append(Text.translatable("uselessadditions.modifiers.crystallized_xp.damage"))
                            .styled(style -> style.withColor(cxp_color))
            );
            if (stack.getItem() instanceof MiningToolItem) {
                list.add(
                        Text.literal(" " + df.format(cxp_miningAmount * level) + " ")
                                .append(Text.translatable("uselessadditions.modifiers.crystallized_xp.mining"))
                                .styled(style -> style.withColor(cxp_color))
                );
            }
        }
    }

    @Unique
    private static List<Text> getModifierText(ItemStack stack, List<String> modifiers) {
        List<Text> list = new ArrayList<>();

        if (stack.getItem().isFireproof()) {
            list.add(createModifierText(true, FIREPROOF));
        }

        List<String> builtinModifiers = ((UItem)stack.getItem()).UAdd$getModifiers();
        for (String id : builtinModifiers) {
            list.add(createModifierText(true, id));
        }

        for (String id : modifiers) {
            if (builtinModifiers.contains(id)) continue;
            list.add(createModifierText(false, id));
        }

        return list;
    }

    @Unique
    private static Text createModifierText(boolean isBuiltin, String id) {
        return Text.translatable(isBuiltin ? "uselessadditions.modifiers.prefixes.builtin" : "uselessadditions.modifiers.prefixes.modified")
                .formatted(isBuiltin ? Formatting.DARK_GRAY : Formatting.DARK_AQUA)
                .append(Text.literal(" ")
                        .append(Text.translatable("uselessadditions.modifiers." + id)
                                .formatted(Formatting.GRAY)));
    }

    @WrapOperation(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/attribute/EntityAttributeModifier;getId()Ljava/util/UUID;"
            ),
            method = "getTooltip"
    )
    private UUID mergeModifierUUIDs(EntityAttributeModifier instance, Operation<UUID> original) {
        if (instance.getId() == AttackDamageUUID) return Item.ATTACK_DAMAGE_MODIFIER_ID;
        if (instance.getId() == AttackSpeedUUID) return Item.ATTACK_SPEED_MODIFIER_ID;
        return original.call(instance);
    }

    @ModifyVariable(
            at = @At("STORE"),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/EntityAttributeModifier;getId()Ljava/util/UUID;"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getAttackDamage(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityGroup;)F")
            ),
            method = "getTooltip", ordinal = 0)
    private double modifyAttackDamage(double d, @Nullable PlayerEntity player, TooltipContext context) {
        ItemStack stack = (ItemStack)(Object)this;
        if (player != null && Modifiers.getModifiersFromStack(stack).contains(CRYSTALLIZED_XP)) d += Math.min(player.experienceLevel, 100) * cxp_damageAmount;
        return d;
    }

    /*
    Todo: Add pickaxe values such as mining speed and harvest level to tooltips

    @Inject(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;getAttributeModifiers(Lnet/minecraft/entity/EquipmentSlot;)Lcom/google/common/collect/Multimap;"
            ),
            method = "getTooltip"
    )
    private void clearAttributeModifiersList(@Nullable PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> cir) {
        attributeModifiers.clear();
    }

    @ModifyReceiver(
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z"
            ),
            slice = @Slice(
                    from = @At(value = "CONSTANT", args = "stringValue=attribute.modifier.take.")
            ),
            method = "getTooltip"
    )
    private List<Text> moveAttributeModifiers(List<Text> instance, Object e) {
        return attributeModifiers;
    }

    */
}
