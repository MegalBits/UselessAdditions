package net.megal.uselessadditions.item;

import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SpecialEffects {
    public static int CXP_MINING_LEVEL_CAP = 2;
    public static float CXP_MINING_LEVEL_REQ = 30f;
    public static float CXP_DAMAGE = .075f;
    public static float CXP_EFFICIENCY = .15f;


    public static final HashMap<String, SpecialEffect> effects = new HashMap<>();
    public static final String AUTO_SMELT = registerEffect("auto_smelt", new SpecialEffect(UEnchantments.AUTO_SMELT, Enchantments.FIRE_ASPECT));
    public static final String CRYSTALLIZED_XP = registerEffect("crystallized_xp", new SpecialEffect(UEnchantments.REPAIRING, Enchantments.MENDING));
    public static final String TREE_FELLING = registerEffect("tree_felling", new SpecialEffect(List.of(Enchantments.EFFICIENCY), List.of()));
    public static final String IMPROVED_SWEEPING = registerEffect("improved_sweeping", new SpecialEffect());
    public static final String CACTUS_LINING = registerEffect("cactus_lining", new SpecialEffect(UEnchantments.CACTUS_LINING, DamageTypes.CACTUS, null));
    public static final String OBSIDIAN_PADDING = registerEffect("obsidian_padding", new SpecialEffect(UEnchantments.OBSIDIAN_PADDING, null, DamageTypeTags.IS_EXPLOSION));
    public static final String FIREPROOF = registerEffect("fireproof", new SpecialEffect());

    private static String registerEffect(String id, SpecialEffect effect) {
        effects.put(id, effect);
        return id;
    }

    public static class SpecialEffect {
        public final List<Enchantment> enchantments;
        public final List<String> effects;
        public final float miningSpeedMod;
        public final float miningSpeedMul;
        @Nullable
        public final RegistryKey<DamageType> damageTypeToPrevent;
        @Nullable
        public final TagKey<DamageType> damageTypesToPrevent;

        public SpecialEffect(List<Enchantment> enchantments, List<String> effects, float miningSpeedMod, float miningSpeedMul, @Nullable RegistryKey<DamageType> damageTypeToPrevent, @Nullable TagKey<DamageType> damageTypesToPrevent) {
            this.enchantments = enchantments;
            this.effects = effects;
            this.miningSpeedMod = miningSpeedMod;
            this.miningSpeedMul = miningSpeedMul;
            this.damageTypeToPrevent = damageTypeToPrevent;
            this.damageTypesToPrevent = damageTypesToPrevent;
        }

        public SpecialEffect(Enchantment enchantment, @Nullable RegistryKey<DamageType> damageTypeToPrevent, @Nullable TagKey<DamageType> damageTypesToPrevent) {
            this(List.of(enchantment), List.of(), 0, 1, damageTypeToPrevent, damageTypesToPrevent);
        }

        public SpecialEffect(List<Enchantment> enchantments, List<String> effects) {
            this(enchantments, effects, 0, 1, null, null);
        }

        public SpecialEffect(Enchantment ... enchantment) {
            this(Arrays.stream(enchantment).toList(), List.of());
        }

        public SpecialEffect(String ... effects) {
            this(List.of(), Arrays.stream(effects).toList());
        }

        public SpecialEffect() {
            this(List.of(), List.of());
        }
    }
}
