package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.UAddClient;
import net.megal.uselessadditions.enchantment.*;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Arrays;
import java.util.List;

@Mixin(ItemStack.class)
public abstract class EnchantmentTooltips {
    private static final MutableText PREFIX = Text.literal("◆ ");
    private static final MutableText PREFIX_2 = Text.literal("◇ ");
    private static final MutableText TAB = Text.literal("       ");
    private static final String SPACE = " ";
    private static final String ADD = "+";
    private static final String SUBTRACT = "";
    private static final int STAT_COLOR = 0x384258;

    private static final List<Enchantment> doubleDesc = Arrays.asList(Enchantments.FIRE_PROTECTION, Enchantments.BLAST_PROTECTION, Enchantments.RESPIRATION);
    @Overwrite
    public static void appendEnchantments(List<Text> tooltip, NbtList enchantments) {
        for (int i = 0; i < enchantments.size(); ++i) {
            NbtCompound nbtCompound = enchantments.getCompound(i);
            Registries.ENCHANTMENT.getOrEmpty(EnchantmentHelper.getIdFromNbt(nbtCompound)).ifPresent(e -> {
                int level = EnchantmentHelper.getLevelFromNbt(nbtCompound);
                tooltip.add(e.getName(level));
                if (UAdd.expandDescriptions) {
                    tooltip.add(PREFIX.copy().append(Text.translatable(e.getTranslationKey() + ".desc")).formatted(Formatting.DARK_GRAY, Formatting.ITALIC));
                    if (doubleDesc.contains(e) || (e instanceof UEnchantment ue && ue.secondLineTooltip()))
                        tooltip.add(PREFIX_2.copy().append(Text.translatable(e.getTranslationKey() + ".desc2")).formatted(Formatting.DARK_GRAY, Formatting.ITALIC));

                    if (e instanceof AugmentEnchantment aug) {
                        if (aug instanceof RepairingEnchantment) {
                            tooltip.add(TAB.copy().append(Text.translatable("enchantment.tooltip.repair_frequency").append(Text.literal(SPACE + (30 / level) + "s"))).formatted(Formatting.ITALIC).styled(style -> style.withColor(STAT_COLOR)));
                        }
                        if (aug.getDamage(level) != 0) {
                            float f = aug.getDamage(level);
                            tooltip.add(TAB.copy().append(Text.translatable("enchantment.tooltip.damage").append(Text.literal(SPACE + (f >= 0 ? ADD : SUBTRACT) + f))).formatted(Formatting.ITALIC).styled(style -> style.withColor(STAT_COLOR)));
                        }
                        if (aug.getDurability(level) != 0) {
                            int ii = aug.getDurability(level);
                            tooltip.add(TAB.copy().append(Text.translatable("enchantment.tooltip.durability").append(Text.literal(SPACE + (ii >= 0 ? ADD : SUBTRACT) + ii))).formatted(Formatting.ITALIC).styled(style -> style.withColor(STAT_COLOR)));
                        }
                        if (aug.getStatusDuration(level) > 0) {
                            tooltip.add(TAB.copy().append(Text.translatable("enchantment.tooltip.duration").append(Text.literal(SPACE + aug.getStatusDuration(level) / 20f + "s"))).formatted(Formatting.ITALIC).styled(style -> style.withColor(STAT_COLOR)));
                            tooltip.add(TAB.copy().append(Text.translatable("enchantment.tooltip.amplifier").append(Text.literal(SPACE + (aug.getAmplifier(level) + 1)))).formatted(Formatting.ITALIC).styled(style -> style.withColor(STAT_COLOR)));
                        }
                    } else {
                        if (e instanceof DamageEnchantment dmg) {
                            float f = getDamage(level, dmg.typeIndex);
                            tooltip.add(TAB.copy().append(Text.translatable("enchantment.tooltip.damage").append(Text.literal(SPACE + (f >= 0 ? ADD : SUBTRACT) + f))).formatted(Formatting.ITALIC).styled(style -> style.withColor(STAT_COLOR)));
                        }

                        if (e instanceof FireAspectEnchantment || e instanceof FlameEnchantment || e instanceof AutoSmeltEnchantment) {
                            tooltip.add(TAB.copy().append(Text.translatable("enchantment.tooltip.duration").append(Text.literal(SPACE + (getFireTicks(level, (e instanceof FireAspectEnchantment ? 0 : e instanceof FlameEnchantment ? 1 : 2)) / 20) + "s"))).formatted(Formatting.ITALIC).styled(style -> style.withColor(STAT_COLOR)));
                        }
                        if (e instanceof EfficiencyEnchantment) {
                            float f = getEfficiency(level);
                            tooltip.add(TAB.copy().append(Text.translatable("enchantment.tooltip.efficiency").append(Text.literal(SPACE + (f >= 0 ? ADD : SUBTRACT) + f))).formatted(Formatting.ITALIC).styled(style -> style.withColor(STAT_COLOR)));
                        }
                        if (e instanceof FrostWalkerEnchantment) {
                            tooltip.add(TAB.copy().append(Text.translatable("enchantment.tooltip.radius").append(Text.literal(SPACE + frostRadius(level) + SPACE).append(Text.translatable("enchantment.tooltip.suffix.blocks")))).formatted(Formatting.ITALIC).styled(style -> style.withColor(STAT_COLOR)));
                        }
                        if (e instanceof KnockbackEnchantment) {
                            tooltip.add(TAB.copy().append(Text.translatable("enchantment.tooltip.knockback").append(Text.literal(SPACE + (level >= 0 ? ADD : SUBTRACT) + level))).formatted(Formatting.ITALIC).styled(style -> style.withColor(STAT_COLOR)));
                        }
                        if (e instanceof LureEnchantment) {
                            float f = lureDecrease(level);
                            tooltip.add(TAB.copy().append(Text.translatable("enchantment.tooltip.lure").append(Text.literal(SPACE + (f >= 0 ? SUBTRACT : ADD) + (f / 20)))).formatted(Formatting.ITALIC).styled(style -> style.withColor(STAT_COLOR)));
                        }
                        if (e instanceof PowerEnchantment) {
                            float f = getPower(level);
                            tooltip.add(TAB.copy().append(Text.translatable("enchantment.tooltip.damage").append(Text.literal(SPACE + (f >= 0 ? ADD : SUBTRACT) + f))).formatted(Formatting.ITALIC).styled(style -> style.withColor(STAT_COLOR)));
                        }
                        if (e instanceof ProtectionEnchantment prot) {
                            float f = getProtection(level, prot.protectionType);
                            tooltip.add(TAB.copy().append(Text.translatable("enchantment.tooltip.protection").append(Text.literal(SPACE + (f >= 0 ? ADD : SUBTRACT) + f))).formatted(Formatting.ITALIC).styled(style -> style.withColor(STAT_COLOR)));
                        }
                        //unfinished, more yet to come
                        // - depth strider
                        // - quick charge
                        // - respiration?
                        // - riptide
                        // - soul speed
                        // - sweeping
                        // - swift sneak
                        // - thorns
                    }
                }
            });
        }
    }
    @ModifyReturnValue(at = @At("RETURN"),
            method = "getTooltip(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/client/item/TooltipContext;)Ljava/util/List;")
    private List<Text> getTooltip(List<Text> l, PlayerEntity player, TooltipContext context) {
        if (!UAdd.expandDescriptions && ((ItemStack)(Object)this).hasEnchantments()) l.add(Text.translatable("enchantment.tooltip.description").append(UAddClient.EXPAND_TOOLTIP.getBoundKeyLocalizedText()).append(Text.translatable("enchantment.tooltip.description2")).formatted(Formatting.GRAY));
        return l;
    }
    private static float getPower(int level) {
        return level * 0.5f + 0.5f;
    }
    private static int lureDecrease(int level) {
        return level * 20 * 5;
    }

    private static int frostRadius(int level) {
        return Math.min(16, 2 + level);
    }

    private static float getEfficiency(int level) {
        return level * level + 1;
    }

    private static int getFireTicks(int level, int type) {
        if (type == 0) {//Fire aspect
            return level * 80;
        }
        if (type == 1) {//Flame
            return 100;
        }
        if (type == 2) {//Auto smelting
            return (level-1) * 20 + 20;
        }
        return 0;
    }

    private static float getDamage(int level, int type) {
        if (type == 0) {
            return 1.0f + (float)Math.max(0, level - 1) * 0.5f;
        }
        if (type == 1) {//EntityGroup.UNDEAD
            return (float)level * 2.5f;
        }
        if (type == 2) {//EntityGroup.ARTHROPOD
            return (float)level * 2.5f;
        }
        return 0f;
    }

    private static int getProtection(int level, ProtectionEnchantment.Type protectionType) {
        if (protectionType == ProtectionEnchantment.Type.ALL) {
            return level;
        }
        if (protectionType == ProtectionEnchantment.Type.FIRE) {
            return level * 2;
        }
        if (protectionType == ProtectionEnchantment.Type.FALL) {
            return level * 3;
        }
        if (protectionType == ProtectionEnchantment.Type.EXPLOSION) {
            return level * 2;
        }
        if (protectionType == ProtectionEnchantment.Type.PROJECTILE) {
            return level * 2;
        }
        return 0;
    }
}