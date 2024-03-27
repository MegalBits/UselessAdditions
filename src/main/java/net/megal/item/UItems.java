package net.megal.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.megal.UAdd;
import net.megal.annotation.*;
import net.megal.annotation.ItemModel.ModelType;
import net.megal.annotation.Recipe.RecipeType;
import net.megal.item.modifier.Modifiers;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.Arrays;

public class UItems {
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:allay_shard_fragment"))
    public static final Item ALLAY_SHARD = registerItem("allay_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:axolotl_shard_fragment"))
    public static final Item AXOLOTL_SHARD = registerItem("axolotl_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:bat_shard_fragment"))
    public static final Item BAT_SHARD = registerItem("bat_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:bee_shard_fragment"))
    public static final Item BEE_SHARD = registerItem("bee_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:blaze_shard_fragment"))
    public static final Item BLAZE_SHARD = registerItem("blaze_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:cat_shard_fragment"))
    public static final Item CAT_SHARD = registerItem("cat_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:cave_spider_shard_fragment"))
    public static final Item CAVE_SPIDER_SHARD = registerItem("cave_spider_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:chicken_shard_fragment"))
    public static final Item CHICKEN_SHARD = registerItem("chicken_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:cod_shard_fragment"))
    public static final Item COD_SHARD = registerItem("cod_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:cow_shard_fragment"))
    public static final Item COW_SHARD = registerItem("cow_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:creeper_shard_fragment"))
    public static final Item CREEPER_SHARD = registerItem("creeper_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:dolphin_shard_fragment"))
    public static final Item DOLPHIN_SHARD = registerItem("dolphin_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:donkey_shard_fragment"))
    public static final Item DONKEY_SHARD = registerItem("donkey_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:enderman_shard_fragment"))
    public static final Item ENDERMAN_SHARD = registerItem("enderman_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:endermite_shard_fragment"))
    public static final Item ENDERMITE_SHARD = registerItem("endermite_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:evoker_shard_fragment"))
    public static final Item EVOKER_SHARD = registerItem("evoker_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:fox_shard_fragment"))
    public static final Item FOX_SHARD = registerItem("fox_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:frog_shard_fragment"))
    public static final Item FROG_SHARD = registerItem("frog_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:ghast_shard_fragment"))
    public static final Item GHAST_SHARD = registerItem("ghast_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:glow_squid_shard_fragment"))
    public static final Item GLOW_SQUID_SHARD = registerItem("glow_squid_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:goat_shard_fragment"))
    public static final Item GOAT_SHARD = registerItem("goat_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:guardian_shard_fragment"))
    public static final Item GUARDIAN_SHARD = registerItem("guardian_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:hoglin_shard_fragment"))
    public static final Item HOGLIN_SHARD = registerItem("hoglin_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:horse_shard_fragment"))
    public static final Item HORSE_SHARD = registerItem("horse_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:iron_golem_shard_fragment"))
    public static final Item IRON_GOLEM_SHARD = registerItem("iron_golem_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:llama_shard_fragment"))
    public static final Item LLAMA_SHARD = registerItem("llama_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:magma_cube_shard_fragment"))
    public static final Item MAGMA_CUBE_SHARD = registerItem("magma_cube_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:ocelot_shard_fragment"))
    public static final Item OCELOT_SHARD = registerItem("ocelot_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:panda_shard_fragment"))
    public static final Item PANDA_SHARD = registerItem("panda_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:parrot_shard_fragment"))
    public static final Item PARROT_SHARD = registerItem("parrot_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:phantom_shard_fragment"))
    public static final Item PHANTOM_SHARD = registerItem("phantom_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:pig_shard_fragment"))
    public static final Item PIG_SHARD = registerItem("pig_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:piglin_shard_fragment"))
    public static final Item PIGLIN_SHARD = registerItem("piglin_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:pillager_shard_fragment"))
    public static final Item PILLAGER_SHARD = registerItem("pillager_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:polar_bear_shard_fragment"))
    public static final Item POLAR_BEAR_SHARD = registerItem("polar_bear_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:pufferfish_shard_fragment"))
    public static final Item PUFFERFISH_SHARD = registerItem("pufferfish_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:rabbit_shard_fragment"))
    public static final Item RABBIT_SHARD = registerItem("rabbit_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:ravager_shard_fragment"))
    public static final Item RAVAGER_SHARD = registerItem("ravager_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:salmon_shard_fragment"))
    public static final Item SALMON_SHARD = registerItem("salmon_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:sheep_shard_fragment"))
    public static final Item SHEEP_SHARD = registerItem("sheep_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:shulker_shard_fragment"))
    public static final Item SHULKER_SHARD = registerItem("shulker_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:silverfish_shard_fragment"))
    public static final Item SILVERFISH_SHARD = registerItem("silverfish_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:skeleton_shard_fragment"))
    public static final Item SKELETON_SHARD = registerItem("skeleton_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:slime_shard_fragment"))
    public static final Item SLIME_SHARD = registerItem("slime_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:snow_golem_shard_fragment"))
    public static final Item SNOW_GOLEM_SHARD = registerItem("snow_golem_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:spider_shard_fragment"))
    public static final Item SPIDER_SHARD = registerItem("spider_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:squid_shard_fragment"))
    public static final Item SQUID_SHARD = registerItem("squid_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:strider_shard_fragment"))
    public static final Item STRIDER_SHARD = registerItem("strider_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:tropical_fish_shard_fragment"))
    public static final Item TROPICAL_FISH_SHARD = registerItem("tropical_fish_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:turtle_shard_fragment"))
    public static final Item TURTLE_SHARD = registerItem("turtle_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:vex_shard_fragment"))
    public static final Item VEX_SHARD = registerItem("vex_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:villager_shard_fragment"))
    public static final Item VILLAGER_SHARD = registerItem("villager_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:vindicator_shard_fragment"))
    public static final Item VINDICATOR_SHARD = registerItem("vindicator_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:warden_shard_fragment"))
    public static final Item WARDEN_SHARD = registerItem("warden_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:witch_shard_fragment"))
    public static final Item WITCH_SHARD = registerItem("witch_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:wither_skeleton_shard_fragment"))
    public static final Item WITHER_SKELETON_SHARD = registerItem("wither_skeleton_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:wolf_shard_fragment"))
    public static final Item WOLF_SHARD = registerItem("wolf_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:zombie_shard_fragment"))
    public static final Item ZOMBIE_SHARD = registerItem("zombie_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:allay_shard"))
    public static final Item ALLAY_SHARD_FRAGMENT = registerItem("allay_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:axolotl_shard"))
    public static final Item AXOLOTL_SHARD_FRAGMENT = registerItem("axolotl_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:bat_shard"))
    public static final Item BAT_SHARD_FRAGMENT = registerItem("bat_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:bee_shard"))
    public static final Item BEE_SHARD_FRAGMENT = registerItem("bee_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:blaze_shard"))
    public static final Item BLAZE_SHARD_FRAGMENT = registerItem("blaze_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:cat_shard"))
    public static final Item CAT_SHARD_FRAGMENT = registerItem("cat_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:cave_spider_shard"))
    public static final Item CAVE_SPIDER_SHARD_FRAGMENT = registerItem("cave_spider_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:chicken_shard"))
    public static final Item CHICKEN_SHARD_FRAGMENT = registerItem("chicken_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:cod_shard"))
    public static final Item COD_SHARD_FRAGMENT = registerItem("cod_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:cow_shard"))
    public static final Item COW_SHARD_FRAGMENT = registerItem("cow_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:creeper_shard"))
    public static final Item CREEPER_SHARD_FRAGMENT = registerItem("creeper_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:dolphin_shard"))
    public static final Item DOLPHIN_SHARD_FRAGMENT = registerItem("dolphin_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:donkey_shard"))
    public static final Item DONKEY_SHARD_FRAGMENT = registerItem("donkey_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:enderman_shard"))
    public static final Item ENDERMAN_SHARD_FRAGMENT = registerItem("enderman_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:endermite_shard"))
    public static final Item ENDERMITE_SHARD_FRAGMENT = registerItem("endermite_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:evoker_shard"))
    public static final Item EVOKER_SHARD_FRAGMENT = registerItem("evoker_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:fox_shard"))
    public static final Item FOX_SHARD_FRAGMENT = registerItem("fox_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:frog_shard"))
    public static final Item FROG_SHARD_FRAGMENT = registerItem("frog_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:ghast_shard"))
    public static final Item GHAST_SHARD_FRAGMENT = registerItem("ghast_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:glow_squid_shard"))
    public static final Item GLOW_SQUID_SHARD_FRAGMENT = registerItem("glow_squid_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:goat_shard"))
    public static final Item GOAT_SHARD_FRAGMENT = registerItem("goat_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:guardian_shard"))
    public static final Item GUARDIAN_SHARD_FRAGMENT = registerItem("guardian_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:hoglin_shard"))
    public static final Item HOGLIN_SHARD_FRAGMENT = registerItem("hoglin_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:horse_shard"))
    public static final Item HORSE_SHARD_FRAGMENT = registerItem("horse_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:iron_golem_shard"))
    public static final Item IRON_GOLEM_SHARD_FRAGMENT = registerItem("iron_golem_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:llama_shard"))
    public static final Item LLAMA_SHARD_FRAGMENT = registerItem("llama_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:magma_cube_shard"))
    public static final Item MAGMA_CUBE_SHARD_FRAGMENT = registerItem("magma_cube_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:ocelot_shard"))
    public static final Item OCELOT_SHARD_FRAGMENT = registerItem("ocelot_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:panda_shard"))
    public static final Item PANDA_SHARD_FRAGMENT = registerItem("panda_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:parrot_shard"))
    public static final Item PARROT_SHARD_FRAGMENT = registerItem("parrot_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:phantom_shard"))
    public static final Item PHANTOM_SHARD_FRAGMENT = registerItem("phantom_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:pig_shard"))
    public static final Item PIG_SHARD_FRAGMENT = registerItem("pig_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:piglin_shard"))
    public static final Item PIGLIN_SHARD_FRAGMENT = registerItem("piglin_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:pillager_shard"))
    public static final Item PILLAGER_SHARD_FRAGMENT = registerItem("pillager_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:polar_bear_shard"))
    public static final Item POLAR_BEAR_SHARD_FRAGMENT = registerItem("polar_bear_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:pufferfish_shard"))
    public static final Item PUFFERFISH_SHARD_FRAGMENT = registerItem("pufferfish_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:rabbit_shard"))
    public static final Item RABBIT_SHARD_FRAGMENT = registerItem("rabbit_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:ravager_shard"))
    public static final Item RAVAGER_SHARD_FRAGMENT = registerItem("ravager_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:salmon_shard"))
    public static final Item SALMON_SHARD_FRAGMENT = registerItem("salmon_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:sheep_shard"))
    public static final Item SHEEP_SHARD_FRAGMENT = registerItem("sheep_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:shulker_shard"))
    public static final Item SHULKER_SHARD_FRAGMENT = registerItem("shulker_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:silverfish_shard"))
    public static final Item SILVERFISH_SHARD_FRAGMENT = registerItem("silverfish_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:skeleton_shard"))
    public static final Item SKELETON_SHARD_FRAGMENT = registerItem("skeleton_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:slime_shard"))
    public static final Item SLIME_SHARD_FRAGMENT = registerItem("slime_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:snow_golem_shard"))
    public static final Item SNOW_GOLEM_SHARD_FRAGMENT = registerItem("snow_golem_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:spider_shard"))
    public static final Item SPIDER_SHARD_FRAGMENT = registerItem("spider_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:squid_shard"))
    public static final Item SQUID_SHARD_FRAGMENT = registerItem("squid_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:strider_shard"))
    public static final Item STRIDER_SHARD_FRAGMENT = registerItem("strider_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:tropical_fish_shard"))
    public static final Item TROPICAL_FISH_SHARD_FRAGMENT = registerItem("tropical_fish_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:turtle_shard"))
    public static final Item TURTLE_SHARD_FRAGMENT = registerItem("turtle_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:vex_shard"))
    public static final Item VEX_SHARD_FRAGMENT = registerItem("vex_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:villager_shard"))
    public static final Item VILLAGER_SHARD_FRAGMENT = registerItem("villager_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:vindicator_shard"))
    public static final Item VINDICATOR_SHARD_FRAGMENT = registerItem("vindicator_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:warden_shard"))
    public static final Item WARDEN_SHARD_FRAGMENT = registerItem("warden_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:witch_shard"))
    public static final Item WITCH_SHARD_FRAGMENT = registerItem("witch_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:wither_skeleton_shard"))
    public static final Item WITHER_SKELETON_SHARD_FRAGMENT = registerItem("wither_skeleton_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:wolf_shard"))
    public static final Item WOLF_SHARD_FRAGMENT = registerItem("wolf_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:zombie_shard"))
    public static final Item ZOMBIE_SHARD_FRAGMENT = registerItem("zombie_shard_fragment", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg ALLAY_SPAWN_EGG = registerItem("allay_spawn_egg", createSpawnEgg(EntityType.ALLAY));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg AXOLOTL_SPAWN_EGG = registerItem("axolotl_spawn_egg", createSpawnEgg(EntityType.AXOLOTL));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg BAT_SPAWN_EGG = registerItem("bat_spawn_egg", createSpawnEgg(EntityType.BAT));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg BEE_SPAWN_EGG = registerItem("bee_spawn_egg", createSpawnEgg(EntityType.BEE));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg BLAZE_SPAWN_EGG = registerItem("blaze_spawn_egg", createSpawnEgg(EntityType.BLAZE));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg CAT_SPAWN_EGG = registerItem("cat_spawn_egg", createSpawnEgg(EntityType.CAT));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg CAVE_SPIDER_SPAWN_EGG = registerItem("cave_spider_spawn_egg", createSpawnEgg(EntityType.CAVE_SPIDER));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg CHICKEN_SPAWN_EGG = registerItem("chicken_spawn_egg", createSpawnEgg(EntityType.CHICKEN));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg COD_SPAWN_EGG = registerItem("cod_spawn_egg", createSpawnEgg(EntityType.COD));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg COW_SPAWN_EGG = registerItem("cow_spawn_egg", createSpawnEgg(EntityType.COW));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg CREEPER_SPAWN_EGG = registerItem("creeper_spawn_egg", createSpawnEgg(EntityType.CREEPER));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg DROWNED_SPAWN_EGG = registerItem("drowned_spawn_egg", createSpawnEgg(EntityType.DROWNED));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg DOLPHIN_SPAWN_EGG = registerItem("dolphin_spawn_egg", createSpawnEgg(EntityType.DOLPHIN));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg DONKEY_SPAWN_EGG = registerItem("donkey_spawn_egg", createSpawnEgg(EntityType.DONKEY));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg ELDER_GUARDIAN_SPAWN_EGG = registerItem("elder_guardian_spawn_egg", createSpawnEgg(EntityType.ELDER_GUARDIAN));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg ENDERMAN_SPAWN_EGG = registerItem("enderman_spawn_egg", createSpawnEgg(EntityType.ENDERMAN));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg ENDERMITE_SPAWN_EGG = registerItem("endermite_spawn_egg", createSpawnEgg(EntityType.ENDERMITE));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg EVOKER_SPAWN_EGG = registerItem("evoker_spawn_egg", createSpawnEgg(EntityType.EVOKER));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg FOX_SPAWN_EGG = registerItem("fox_spawn_egg", createSpawnEgg(EntityType.FOX));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg FROG_SPAWN_EGG = registerItem("frog_spawn_egg", createSpawnEgg(EntityType.FROG));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg GHAST_SPAWN_EGG = registerItem("ghast_spawn_egg", createSpawnEgg(EntityType.GHAST));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg GLOW_SQUID_SPAWN_EGG = registerItem("glow_squid_spawn_egg", createSpawnEgg(EntityType.GLOW_SQUID));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg GOAT_SPAWN_EGG = registerItem("goat_spawn_egg", createSpawnEgg(EntityType.GOAT));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg GUARDIAN_SPAWN_EGG = registerItem("guardian_spawn_egg", createSpawnEgg(EntityType.GUARDIAN));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg HOGLIN_SPAWN_EGG = registerItem("hoglin_spawn_egg", createSpawnEgg(EntityType.HOGLIN));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg HORSE_SPAWN_EGG = registerItem("horse_spawn_egg", createSpawnEgg(EntityType.HORSE));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg HUSK_SPAWN_EGG = registerItem("husk_spawn_egg", createSpawnEgg(EntityType.HUSK));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg IRON_GOLEM_SPAWN_EGG = registerItem("iron_golem_spawn_egg", createSpawnEgg(EntityType.IRON_GOLEM));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg LLAMA_SPAWN_EGG = registerItem("llama_spawn_egg", createSpawnEgg(EntityType.LLAMA));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg MAGMA_CUBE_SPAWN_EGG = registerItem("magma_cube_spawn_egg", createSpawnEgg(EntityType.MAGMA_CUBE));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg OCELOT_SPAWN_EGG = registerItem("ocelot_spawn_egg", createSpawnEgg(EntityType.OCELOT));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg PANDA_SPAWN_EGG = registerItem("panda_spawn_egg", createSpawnEgg(EntityType.PANDA));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg PARROT_SPAWN_EGG = registerItem("parrot_spawn_egg", createSpawnEgg(EntityType.PARROT));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg PHANTOM_SPAWN_EGG = registerItem("phantom_spawn_egg", createSpawnEgg(EntityType.PHANTOM));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg PIG_SPAWN_EGG = registerItem("pig_spawn_egg", createSpawnEgg(EntityType.PIG));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg PIGLIN_SPAWN_EGG = registerItem("piglin_spawn_egg", createSpawnEgg(EntityType.PIGLIN));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg PIGLIN_BRUTE_SPAWN_EGG = registerItem("piglin_brute_spawn_egg", createSpawnEgg(EntityType.PIGLIN_BRUTE));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg PILLAGER_SPAWN_EGG = registerItem("pillager_spawn_egg", createSpawnEgg(EntityType.PILLAGER));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg POLAR_BEAR_SPAWN_EGG = registerItem("polar_bear_spawn_egg", createSpawnEgg(EntityType.POLAR_BEAR));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg PUFFERFISH_SPAWN_EGG = registerItem("pufferfish_spawn_egg", createSpawnEgg(EntityType.PUFFERFISH));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg RABBIT_SPAWN_EGG = registerItem("rabbit_spawn_egg", createSpawnEgg(EntityType.RABBIT));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg RAVAGER_SPAWN_EGG = registerItem("ravager_spawn_egg", createSpawnEgg(EntityType.RAVAGER));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg SALMON_SPAWN_EGG = registerItem("salmon_spawn_egg", createSpawnEgg(EntityType.SALMON));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg SHEEP_SPAWN_EGG = registerItem("sheep_spawn_egg", createSpawnEgg(EntityType.SHEEP));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg SHULKER_SPAWN_EGG = registerItem("shulker_spawn_egg", createSpawnEgg(EntityType.SHULKER));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg SILVERFISH_SPAWN_EGG = registerItem("silverfish_spawn_egg", createSpawnEgg(EntityType.SILVERFISH));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg SKELETON_SPAWN_EGG = registerItem("skeleton_spawn_egg", createSpawnEgg(EntityType.SKELETON));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg SLIME_SPAWN_EGG = registerItem("slime_spawn_egg", createSpawnEgg(EntityType.SLIME));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg SNOW_GOLEM_SPAWN_EGG = registerItem("snow_golem_spawn_egg", createSpawnEgg(EntityType.SNOW_GOLEM));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg SPIDER_SPAWN_EGG = registerItem("spider_spawn_egg", createSpawnEgg(EntityType.SPIDER));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg SQUID_SPAWN_EGG = registerItem("squid_spawn_egg", createSpawnEgg(EntityType.SQUID));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg STRAY_SPAWN_EGG = registerItem("stray_spawn_egg", createSpawnEgg(EntityType.STRAY));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg STRIDER_SPAWN_EGG = registerItem("strider_spawn_egg", createSpawnEgg(EntityType.STRIDER));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg TROPICAL_FISH_SPAWN_EGG = registerItem("tropical_fish_spawn_egg", createSpawnEgg(EntityType.TROPICAL_FISH));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg TURTLE_SPAWN_EGG = registerItem("turtle_spawn_egg", createSpawnEgg(EntityType.TURTLE));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg VEX_SPAWN_EGG = registerItem("vex_spawn_egg", createSpawnEgg(EntityType.VEX));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg VILLAGER_SPAWN_EGG = registerItem("villager_spawn_egg", createSpawnEgg(EntityType.VILLAGER));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg VINDICATOR_SPAWN_EGG = registerItem("vindicator_spawn_egg", createSpawnEgg(EntityType.VINDICATOR));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg WARDEN_SPAWN_EGG = registerItem("warden_spawn_egg", createSpawnEgg(EntityType.WARDEN));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg WITCH_SPAWN_EGG = registerItem("witch_spawn_egg", createSpawnEgg(EntityType.WITCH));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg WITHER_SKELETON_SPAWN_EGG = registerItem("wither_skeleton_spawn_egg", createSpawnEgg(EntityType.WITHER_SKELETON));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg WOLF_SPAWN_EGG = registerItem("wolf_spawn_egg", createSpawnEgg(EntityType.WOLF));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg ZOGLIN_SPAWN_EGG = registerItem("zoglin_spawn_egg", createSpawnEgg(EntityType.ZOGLIN));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg ZOMBIE_SPAWN_EGG = registerItem("zombie_spawn_egg", createSpawnEgg(EntityType.ZOMBIE));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg ZOMBIE_VILLAGER_SPAWN_EGG = registerItem("zombie_villager_spawn_egg", createSpawnEgg(EntityType.ZOMBIE_VILLAGER));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final USpawnEgg ZOMBIFIED_PIGLIN_SPAWN_EGG = registerItem("zombified_piglin_spawn_egg", createSpawnEgg(EntityType.ZOMBIFIED_PIGLIN));
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING_2X2, ingredients = @SimpleIngredient("minecraft:cobblestone"))
    @Recipe(type = RecipeType.UNPACKING_4, ingredients = @SimpleIngredient("minecraft:cobblestone"), swapInputOutput = true)
    public static final Item ROCK = registerItem("rock", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING_2X2, ingredients = @SimpleIngredient("minecraft:cobbled_deepslate"))
    @Recipe(type = RecipeType.UNPACKING_4, ingredients = @SimpleIngredient("minecraft:cobbled_deepslate"), swapInputOutput = true)
    public static final Item DEEPSLATE_ROCK = registerItem("deepslate_rock", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING_2X2, ingredients = @SimpleIngredient("minecraft:ender_pearl"))
    @Recipe(type = RecipeType.UNPACKING_4, ingredients = @SimpleIngredient("minecraft:ender_pearl"), swapInputOutput = true)
    public static final Item ENDER_PEARL_SHARD = registerItem("ender_pearl_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final Item ANCIENT_SHARD = registerItem("ancient_shard", createFireproofItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:exotic_ingot"))
    public static final Item EXOTIC_DUST = registerItem("exotic_dust", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("minecraft:raw_copper"))
    @Recipe(type = RecipeType.SMELTING, ticks = 100, xp = 0.1f, ingredients = @SimpleIngredient("uselessadditions:copper_nugget"), swapInputOutput = true)
    @Recipe(type = RecipeType.BLASTING, ticks = 50, xp = 0.1f, ingredients = @SimpleIngredient("uselessadditions:copper_nugget"), swapInputOutput = true)
    public static final Item RAW_COPPER_NUGGET = registerItem("raw_copper_nugget", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("minecraft:raw_iron"))
    @Recipe(type = RecipeType.SMELTING, ticks = 100, xp = 0.1f, ingredients = @SimpleIngredient("minecraft:iron_nugget"), swapInputOutput = true)
    @Recipe(type = RecipeType.BLASTING, ticks = 50, xp = 0.1f, ingredients = @SimpleIngredient("minecraft:iron_nugget"), swapInputOutput = true)
    public static final Item RAW_IRON_NUGGET = registerItem("raw_iron_nugget", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("minecraft:raw_gold"))
    @Recipe(type = RecipeType.SMELTING, ticks = 100, xp = 0.1f, ingredients = @SimpleIngredient("minecraft:gold_nugget"), swapInputOutput = true)
    @Recipe(type = RecipeType.BLASTING, ticks = 50, xp = 0.1f, ingredients = @SimpleIngredient("minecraft:gold_nugget"), swapInputOutput = true)
    public static final Item RAW_GOLD_NUGGET = registerItem("raw_gold_nugget", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("minecraft:copper_ingot"))
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("minecraft:copper_ingot"), swapInputOutput = true)
    public static final Item COPPER_NUGGET = registerItem("copper_nugget", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("minecraft:emerald"))
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("minecraft:emerald"), swapInputOutput = true)
    public static final Item EMERALD_NUGGET = registerItem("emerald_nugget", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("minecraft:diamond"))
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("minecraft:diamond"), swapInputOutput = true)
    public static final Item DIAMOND_NUGGET = registerItem("diamond_nugget", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("minecraft:netherite_ingot"))
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("minecraft:netherite_ingot"), swapInputOutput = true)
    public static final Item NETHERITE_NUGGET = registerItem("netherite_nugget", createFireproofItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:ancient_alloy"))
    public static final Item ANCIENT_ALLOY_NUGGET = registerItem("ancient_alloy_nugget", createFireproofItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final Item MAGIC_INGOT = registerItem("magic_ingot", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final Item ENCHANTED_MAGIC_INGOT = registerItem("enchanted_magic_ingot", new Item(new UItemSettings().rarity(Rarity.RARE).glint()));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final Item BLAZE_METAL = registerItem("blaze_metal", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:ancient_alloy_nugget"))
    public static final Item ANCIENT_ALLOY = registerItem("ancient_alloy", createFireproofItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("uselessadditions:exotic_dust"))
    public static final Item EXOTIC_INGOT = registerItem("exotic_ingot", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final Item RUBY = registerItem("ruby", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final Item DRAGON_SCALE = registerItem("dragon_scale", new Item(new FabricItemSettings().fireproof().rarity(Rarity.UNCOMMON)));
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.SMITHING, ingredients = {@SimpleIngredient("#c:hammers"), @SimpleIngredient("minecraft:stick"), @SimpleIngredient("uselessadditions:netherite_nugget")})
    public static final Item NETHERITE_STICK = registerItem(("netherite_stick"), createFireproofItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.SMITHING, ingredients = {@SimpleIngredient("#c:hammers"), @SimpleIngredient("minecraft:string"), @SimpleIngredient("uselessadditions:netherite_nugget")})
    public static final Item NETHERITE_STRING = registerItem(("netherite_string"), createFireproofItem());
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.HAMMER, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("#minecraft:planks")})
    public static final HammerItem WOODEN_HAMMER = registerItem("wooden_hammer", new HammerItem(ToolMaterials.WOOD, 3, -3.2F, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD2X)
    @Name
    @Recipe(type = RecipeType.SCYTHE, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("#minecraft:planks"), @SimpleIngredient("minecraft:stick")})
    public static final ScytheItem WOODEN_SCYTHE = registerItem("wooden_scythe", new ScytheItem(ToolMaterials.WOOD, 4.5F, -2.8F, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.HAMMER, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("#minecraft:stone_crafting_materials")})
    public static final HammerItem STONE_HAMMER = registerItem("stone_hammer", new HammerItem(ToolMaterials.STONE, 3, -3.2F, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD2X)
    @Name
    @Recipe(type = RecipeType.SCYTHE, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("#minecraft:stone_crafting_materials"), @SimpleIngredient("#c:rocks")})
    public static final ScytheItem STONE_SCYTHE = registerItem("stone_scythe", new ScytheItem(ToolMaterials.STONE, 4.5F, -2.8F, new FabricItemSettings()));
    @ItemModel(ModelType.BOW)
    @Name
    @Recipe(type = RecipeType.BOW, ingredients = {@SimpleIngredient("minecraft:string"), @SimpleIngredient("#minecraft:stone_crafting_materials"), @SimpleIngredient("#c:rocks")})
    public static final UBowItem STONE_BOW = registerItem("stone_bow", new UBowItem(ToolMaterials.STONE, 1.25f, 1.8f, 1.8f, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.HAMMER, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:iron_ingot")})
    public static final HammerItem IRON_HAMMER = registerItem("iron_hammer", new HammerItem(ToolMaterials.IRON, 3, -3.2F, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD2X)
    @Name
    @Recipe(type = RecipeType.SCYTHE, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:iron_ingot"), @SimpleIngredient("minecraft:iron_nugget")})
    public static final ScytheItem IRON_SCYTHE = registerItem("iron_scythe", new ScytheItem(ToolMaterials.IRON, 4.5F, -2.8F, new FabricItemSettings()));
    @ItemModel(ModelType.BOW)
    @Name
    @Recipe(type = RecipeType.BOW, ingredients = {@SimpleIngredient("minecraft:string"), @SimpleIngredient("minecraft:iron_ingot"), @SimpleIngredient("minecraft:iron_nugget")})
    public static final UBowItem IRON_BOW = registerItem("iron_bow", new UBowItem(ToolMaterials.IRON, 1.5f, 1.6f, 1.6f, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.HAMMER, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:gold_ingot")})
    public static final HammerItem GOLDEN_HAMMER = registerItem("golden_hammer", new HammerItem(ToolMaterials.GOLD, 3, -3.2F, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD2X)
    @Name
    @Recipe(type = RecipeType.SCYTHE, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:gold_ingot"), @SimpleIngredient("minecraft:gold_nugget")})
    public static final ScytheItem GOLDEN_SCYTHE = registerItem("golden_scythe", new ScytheItem(ToolMaterials.GOLD, 4.5F, -2.8F, new FabricItemSettings()));
    @ItemModel(ModelType.BOW)
    @Name
    @Recipe(type = RecipeType.BOW, ingredients = {@SimpleIngredient("minecraft:string"), @SimpleIngredient("minecraft:gold_ingot"), @SimpleIngredient("minecraft:gold_nugget")})
    public static final UBowItem GOLDEN_BOW = registerItem("golden_bow", new UBowItem(ToolMaterials.GOLD, 1f, 2f, 2f, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.HAMMER, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:diamond")})
    public static final HammerItem DIAMOND_HAMMER = registerItem("diamond_hammer", new HammerItem(ToolMaterials.DIAMOND, 3, -3.2F, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD2X)
    @Name
    @Recipe(type = RecipeType.SCYTHE, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:diamond"), @SimpleIngredient("uselessadditions:diamond_nugget")})
    public static final ScytheItem DIAMOND_SCYTHE = registerItem("diamond_scythe", new ScytheItem(ToolMaterials.DIAMOND, 4.5F, -2.8F, new FabricItemSettings()));
    @ItemModel(ModelType.BOW)
    @Name
    @Recipe(type = RecipeType.BOW, ingredients = {@SimpleIngredient("minecraft:string"), @SimpleIngredient("minecraft:diamond"), @SimpleIngredient("uselessadditions:diamond_nugget")})
    public static final UBowItem DIAMOND_BOW = registerItem("diamond_bow", new UBowItem(ToolMaterials.DIAMOND, 1.75f, 1.4f, 1.4f, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.SMITHING, ingredients = {@SimpleIngredient("minecraft:netherite_upgrade_smithing_template"), @SimpleIngredient("uselessadditions:diamond_hammer"), @SimpleIngredient("minecraft:netherite_ingot")})
    public static final HammerItem NETHERITE_HAMMER = registerItem("netherite_hammer", new HammerItem(ToolMaterials.NETHERITE, 3, -3.2F, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.HANDHELD2X)
    @Name
    @Recipe(type = RecipeType.SMITHING, ingredients = {@SimpleIngredient("minecraft:netherite_upgrade_smithing_template"), @SimpleIngredient("uselessadditions:diamond_scythe"), @SimpleIngredient("minecraft:netherite_ingot")})
    public static final ScytheItem NETHERITE_SCYTHE = registerItem("netherite_scythe", new ScytheItem(ToolMaterials.NETHERITE, 4.5F, -2.8F, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.BOW)
    @Name
    @Recipe(type = RecipeType.SMITHING, ingredients = {@SimpleIngredient("minecraft:netherite_upgrade_smithing_template"), @SimpleIngredient("uselessadditions:diamond_bow"), @SimpleIngredient("minecraft:netherite_ingot")})
    public static final UBowItem NETHERITE_BOW = registerItem("netherite_bow", new UBowItem(ToolMaterials.NETHERITE, 2f, 1.2f, 1.2f, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.SWORD, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("uselessadditions:magic_ingot")})
    public static final SwordItem AMETHYST_SWORD = registerItem("amethyst_sword", new SwordItem(UToolMaterials.AMETHYST, 3, -2.4F, new UItemSettings().modifiers(Modifiers.CRYSTALLIZED_XP)));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.SHOVEL, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("uselessadditions:magic_ingot")})
    public static final ShovelItem AMETHYST_SHOVEL = registerItem("amethyst_shovel", new ShovelItem(UToolMaterials.AMETHYST, 1.5F, -3.0F, new UItemSettings().modifiers(Modifiers.CRYSTALLIZED_XP)));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.PICKAXE, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("uselessadditions:magic_ingot")})
    public static final PickaxeItem AMETHYST_PICKAXE = registerItem("amethyst_pickaxe", new PickaxeItem(UToolMaterials.AMETHYST, 1, -2.8F, new UItemSettings().modifiers(Modifiers.CRYSTALLIZED_XP)));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.AXE, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("uselessadditions:magic_ingot")})
    public static final AxeItem AMETHYST_AXE = registerItem("amethyst_axe", new AxeItem(UToolMaterials.AMETHYST, 5.0F, -3F, new UItemSettings().modifiers(Modifiers.CRYSTALLIZED_XP)));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.HOE, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("uselessadditions:magic_ingot")})
    public static final UHoeItem AMETHYST_HOE = registerItem("amethyst_hoe", new UHoeItem(UToolMaterials.AMETHYST, 0, 0.0F, new UItemSettings().modifiers(Modifiers.CRYSTALLIZED_XP)));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.HAMMER, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("uselessadditions:magic_ingot")})
    public static final HammerItem AMETHYST_HAMMER = registerItem("amethyst_hammer", new HammerItem(UToolMaterials.AMETHYST, 3, -3.2F, new UItemSettings().modifiers(Modifiers.CRYSTALLIZED_XP)));
    @ItemModel(ModelType.HANDHELD2X)
    @Name
    @Recipe(type = RecipeType.SCYTHE, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("uselessadditions:magic_ingot"), @SimpleIngredient("minecraft:amethyst_shard")})
    //Todo: magic nugget
    public static final ScytheItem AMETHYST_SCYTHE = registerItem("amethyst_scythe", new ScytheItem(UToolMaterials.AMETHYST, 4.5F, -2.8F, new UItemSettings().modifiers(Modifiers.CRYSTALLIZED_XP)));
    @ItemModel(ModelType.BOW)
    @Name
    @Recipe(type = RecipeType.BOW, ingredients = {@SimpleIngredient("minecraft:string"), @SimpleIngredient("uselessadditions:magic_ingot"), @SimpleIngredient("minecraft:amethyst_shard")})
    public static final UBowItem AMETHYST_BOW = registerItem("amethyst_bow", new UBowItem(UToolMaterials.AMETHYST, 1.625f, 1.5f, 1.5f, new UItemSettings().modifiers(Modifiers.CRYSTALLIZED_XP)));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.SWORD, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:emerald")})
    public static final SwordItem EMERALD_SWORD = registerItem("emerald_sword", new SwordItem(UToolMaterials.EMERALD, 3, -2.4F, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.SHOVEL, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:emerald")})
    public static final ShovelItem EMERALD_SHOVEL = registerItem("emerald_shovel", new ShovelItem(UToolMaterials.EMERALD, 1.5F, -3.0F, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.PICKAXE, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:emerald")})
    public static final PickaxeItem EMERALD_PICKAXE = registerItem("emerald_pickaxe", new PickaxeItem(UToolMaterials.EMERALD, 1, -2.8F, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.AXE, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:emerald")})
    public static final AxeItem EMERALD_AXE = registerItem("emerald_axe", new AxeItem(UToolMaterials.EMERALD, 5.0F, -3.0F, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.HOE, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:emerald")})
    public static final UHoeItem EMERALD_HOE = registerItem("emerald_hoe", new UHoeItem(UToolMaterials.EMERALD, 0, 0.0F, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.HAMMER, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:emerald")})
    public static final HammerItem EMERALD_HAMMER = registerItem("emerald_hammer", new HammerItem(UToolMaterials.EMERALD, 3, -3.2F, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD2X)
    @Name
    @Recipe(type = RecipeType.SCYTHE, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:emerald"), @SimpleIngredient("uselessadditions:emerald_nugget")})
    public static final ScytheItem EMERALD_SCYTHE = registerItem("emerald_scythe", new ScytheItem(UToolMaterials.EMERALD, 4.5F, -2.8F, new FabricItemSettings()));
    @ItemModel(ModelType.BOW)
    @Name
    @Recipe(type = RecipeType.BOW, ingredients = {@SimpleIngredient("minecraft:string"), @SimpleIngredient("minecraft:emerald"), @SimpleIngredient("uselessadditions:emerald_nugget")})
    public static final UBowItem EMERALD_BOW = registerItem("emerald_bow", new UBowItem(UToolMaterials.EMERALD, 1.75f, 1.4f, 1.4f, new FabricItemSettings()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.SWORD, ingredients = {@SimpleIngredient("uselessadditions:netherite_stick"), @SimpleIngredient("uselessadditions:blaze_metal")})
    public static final SwordItem BLAZE_METAL_SWORD = registerItem("blaze_metal_sword", new SwordItem(UToolMaterials.BLAZE_METAL, 3, -2.4F, new UItemSettings().modifiers(Modifiers.AUTO_SMELTING)));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.SHOVEL, ingredients = {@SimpleIngredient("uselessadditions:netherite_stick"), @SimpleIngredient("uselessadditions:blaze_metal")})
    public static final ShovelItem BLAZE_METAL_SHOVEL = registerItem("blaze_metal_shovel", new ShovelItem(UToolMaterials.BLAZE_METAL, 1.5F, -3.0F, new UItemSettings().modifiers(Modifiers.AUTO_SMELTING)));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.PICKAXE, ingredients = {@SimpleIngredient("uselessadditions:netherite_stick"), @SimpleIngredient("uselessadditions:blaze_metal")})
    public static final PickaxeItem BLAZE_METAL_PICKAXE = registerItem("blaze_metal_pickaxe", new PickaxeItem(UToolMaterials.BLAZE_METAL, 1, -2.8F, new UItemSettings().modifiers(Modifiers.AUTO_SMELTING)));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.AXE, ingredients = {@SimpleIngredient("uselessadditions:netherite_stick"), @SimpleIngredient("uselessadditions:blaze_metal")})
    public static final AxeItem BLAZE_METAL_AXE = registerItem("blaze_metal_axe", new AxeItem(UToolMaterials.BLAZE_METAL, 5.0F, -3.0F, new UItemSettings().modifiers(Modifiers.AUTO_SMELTING)));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.HOE, ingredients = {@SimpleIngredient("uselessadditions:netherite_stick"), @SimpleIngredient("uselessadditions:blaze_metal")})
    public static final UHoeItem BLAZE_METAL_HOE = registerItem("blaze_metal_hoe", new UHoeItem(UToolMaterials.BLAZE_METAL, 0, 0.0F, new UItemSettings().modifiers(Modifiers.AUTO_SMELTING)));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.HAMMER, ingredients = {@SimpleIngredient("uselessadditions:netherite_stick"), @SimpleIngredient("uselessadditions:blaze_metal")})
    public static final HammerItem BLAZE_METAL_HAMMER = registerItem("blaze_metal_hammer", new HammerItem(UToolMaterials.BLAZE_METAL, 3, -3.2F, new UItemSettings().modifiers(Modifiers.AUTO_SMELTING)));
    @ItemModel(ModelType.HANDHELD2X)
    @Name
    @Recipe(type = RecipeType.SCYTHE, ingredients = {@SimpleIngredient("uselessadditions:netherite_stick"), @SimpleIngredient("uselessadditions:blaze_metal"), @SimpleIngredient("minecraft:blaze_powder")})
    //Todo: blaze metal nugget
    public static final ScytheItem BLAZE_METAL_SCYTHE = registerItem("blaze_metal_scythe", new ScytheItem(UToolMaterials.BLAZE_METAL, 4.5F, -2.8F, new UItemSettings().modifiers(Modifiers.AUTO_SMELTING)));
    @ItemModel(ModelType.BOW)
    @Name
    @Recipe(type = RecipeType.BOW, ingredients = {@SimpleIngredient("uselessadditions:netherite_string"), @SimpleIngredient("uselessadditions:blaze_metal"), @SimpleIngredient("minecraft:blaze_powder")})
    public static final UBowItem BLAZE_METAL_BOW = registerItem("blaze_metal_bow", new UBowItem(UToolMaterials.BLAZE_METAL, 1.875f, 1.3f, 1.2f, new UItemSettings().modifiers(Modifiers.AUTO_SMELTING)));
    @ItemModel(ModelType.HANDHELD)
    @Name
    public static final SwordItem DRAGON_SCALE_SWORD = registerItem("dragon_scale_sword", new SwordItem(UToolMaterials.DRAGON_SCALE, 3, -2.4F, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    public static final ShovelItem DRAGON_SCALE_SHOVEL = registerItem("dragon_scale_shovel", new ShovelItem(UToolMaterials.DRAGON_SCALE, 1.5F, -3.0F, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    public static final PickaxeItem DRAGON_SCALE_PICKAXE = registerItem("dragon_scale_pickaxe", new PickaxeItem(UToolMaterials.DRAGON_SCALE, 1, -2.8F, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    public static final AxeItem DRAGON_SCALE_AXE = registerItem("dragon_scale_axe", new AxeItem(UToolMaterials.DRAGON_SCALE, 5.0F, -3.0F, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    public static final UHoeItem DRAGON_SCALE_HOE = registerItem("dragon_scale_hoe", new UHoeItem(UToolMaterials.DRAGON_SCALE, 0, 0.0F, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    public static final HammerItem DRAGON_SCALE_HAMMER = registerItem("dragon_scale_hammer", new HammerItem(UToolMaterials.DRAGON_SCALE, 4, -3.2F, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.HANDHELD2X)
    @Name
    public static final ScytheItem DRAGON_SCALE_SCYTHE = registerItem("dragon_scale_scythe", new ScytheItem(UToolMaterials.DRAGON_SCALE, 4.5F, -2.8F, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.BOW)
    @Name
    public static final UBowItem DRAGON_SCALE_BOW = registerItem("dragon_scale_bow", new UBowItem(UToolMaterials.DRAGON_SCALE, 2.125f, 1.1f, 1.1f, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.SWORD, ingredients = {@SimpleIngredient("uselessadditions:netherite_stick"), @SimpleIngredient("uselessadditions:ancient_alloy")})
    public static final SwordItem ANCIENT_SWORD = registerItem("ancient_sword", new SwordItem(UToolMaterials.ANCIENT, 3, -2.4F, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.SHOVEL, ingredients = {@SimpleIngredient("uselessadditions:netherite_stick"), @SimpleIngredient("uselessadditions:ancient_alloy")})
    public static final ShovelItem ANCIENT_SHOVEL = registerItem("ancient_shovel", new ShovelItem(UToolMaterials.ANCIENT, 1.5F, -3.0F, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.PICKAXE, ingredients = {@SimpleIngredient("uselessadditions:netherite_stick"), @SimpleIngredient("uselessadditions:ancient_alloy")})
    public static final PickaxeItem ANCIENT_PICKAXE = registerItem("ancient_pickaxe", new PickaxeItem(UToolMaterials.ANCIENT, 1, -2.8F, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.AXE, ingredients = {@SimpleIngredient("uselessadditions:netherite_stick"), @SimpleIngredient("uselessadditions:ancient_alloy")})
    public static final AxeItem ANCIENT_AXE = registerItem("ancient_axe", new AxeItem(UToolMaterials.ANCIENT, 5.0F, -3.0F, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.HOE, ingredients = {@SimpleIngredient("uselessadditions:netherite_stick"), @SimpleIngredient("uselessadditions:ancient_alloy")})
    public static final UHoeItem ANCIENT_HOE = registerItem("ancient_hoe", new UHoeItem(UToolMaterials.ANCIENT, 0, 0.0F, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.HANDHELD)
    @Name
    @Recipe(type = RecipeType.HAMMER, ingredients = {@SimpleIngredient("uselessadditions:netherite_stick"), @SimpleIngredient("uselessadditions:ancient_alloy")})
    public static final HammerItem ANCIENT_HAMMER = registerItem("ancient_hammer", new HammerItem(UToolMaterials.ANCIENT, 4, -3.2F, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.HANDHELD2X)
    @Name
    @Recipe(type = RecipeType.SCYTHE, ingredients = {@SimpleIngredient("uselessadditions:netherite_stick"), @SimpleIngredient("uselessadditions:ancient_alloy"), @SimpleIngredient("uselessadditions:ancient_alloy_nugget")})
    public static final ScytheItem ANCIENT_SCYTHE = registerItem("ancient_scythe", new ScytheItem(UToolMaterials.ANCIENT, 4.5F, -2.8F, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.BOW)
    @Name
    @Recipe(type = RecipeType.BOW, ingredients = {@SimpleIngredient("uselessadditions:netherite_string"), @SimpleIngredient("uselessadditions:ancient_alloy"), @SimpleIngredient("uselessadditions:ancient_alloy_nugget")})
    public static final UBowItem ANCIENT_BOW = registerItem("ancient_bow", new UBowItem(UToolMaterials.ANCIENT, 2.5f, 0.8f, 0.8f, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.ARROW, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:iron_ingot"), @SimpleIngredient("minecraft:feather")})
    public static final UArrowItem IRON_ARROW = registerItem("iron_arrow", new UArrowItem(.75f, 1.15f, new FabricItemSettings()));
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.ARROW, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:gold_ingot"), @SimpleIngredient("minecraft:feather")})
    public static final UArrowItem GOLD_ARROW = registerItem("gold_arrow", new UArrowItem(0, 1.25f, new FabricItemSettings()));
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.ARROW, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:diamond"), @SimpleIngredient("minecraft:feather")})
    public static final UArrowItem DIAMOND_ARROW = registerItem("diamond_arrow", new UArrowItem(1.5f, 1.3f, new FabricItemSettings()));
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.SMITHING, ingredients = {@SimpleIngredient("#c:hammers"), @SimpleIngredient("uselessadditions:diamond_arrow"), @SimpleIngredient("uselessadditions:netherite_nugget")})
    public static final UArrowItem NETHERITE_ARROW = registerItem("netherite_arrow", new UArrowItem(2.25f, 1.45f, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.ARROW, ingredients = {@SimpleIngredient("minecraft:stick"), @SimpleIngredient("minecraft:emerald"), @SimpleIngredient("minecraft:feather")})
    public static final UArrowItem EMERALD_ARROW = registerItem("emerald_arrow", new UArrowItem(1.35f, 1.2f, new FabricItemSettings()));
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.ARROW, ingredients = {@SimpleIngredient("uselessadditions:netherite_stick"), @SimpleIngredient("uselessadditions:blaze_metal"), @SimpleIngredient("minecraft:feather")})
    public static final UArrowItem BLAZE_METAL_ARROW = registerItem("blaze_metal_arrow", new UArrowItem(1.875f, 1.375f, new FabricItemSettings()));
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final UArrowItem DRAGON_SCALE_ARROW = registerItem("dragon_scale_arrow", new UArrowItem(3f, 1.525f, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.ARMOR)
    @Name
    public static final ArmorItem DRAGON_SCALE_HELMET = registerItem("dragon_scale_helmet", new ArmorItem(UArmorMaterials.DRAGON_SCALE, ArmorItem.Type.HELMET, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.ARMOR)
    @Name
    public static final ArmorItem DRAGON_SCALE_CHESTPLATE = registerItem("dragon_scale_chestplate", new ArmorItem(UArmorMaterials.DRAGON_SCALE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.ARMOR)
    @Name
    public static final ArmorItem DRAGON_SCALE_LEGGINGS = registerItem("dragon_scale_leggings", new ArmorItem(UArmorMaterials.DRAGON_SCALE, ArmorItem.Type.LEGGINGS, new FabricItemSettings().fireproof()));
    @ItemModel(ModelType.ARMOR)
    @Name
    public static final ArmorItem DRAGON_SCALE_BOOTS = registerItem("dragon_scale_boots", new ArmorItem(UArmorMaterials.DRAGON_SCALE, ArmorItem.Type.BOOTS, new FabricItemSettings().fireproof()));


    public static Item createItem() {
        return new Item(new FabricItemSettings());
    }
    public static Item createFireproofItem() {
        return new Item(new FabricItemSettings().fireproof());
    }

    public static USpawnEgg createSpawnEgg(EntityType<? extends MobEntity> entityType) {
        return new USpawnEgg(new FabricItemSettings(), entityType);
    }

    public static <T extends Item> T registerItem(String name, T item) {
        return Registry.register(Registries.ITEM, new Identifier(UAdd.ID, name), item);
    }

    public static void itemTabs() {
        ItemGroupEvents.modifyEntriesEvent(UItemGroups.MAIN).register(entries -> {
            entries.add(ROCK);
            entries.add(DEEPSLATE_ROCK);

            entries.add(ENDER_PEARL_SHARD);
            entries.add(ANCIENT_SHARD);

            entries.add(EXOTIC_DUST);
            entries.add(RAW_COPPER_NUGGET);
            entries.add(RAW_IRON_NUGGET);
            entries.add(RAW_GOLD_NUGGET);
            entries.add(COPPER_NUGGET);
            entries.add(EMERALD_NUGGET);
            entries.add(DIAMOND_NUGGET);
            entries.add(NETHERITE_NUGGET);
            entries.add(ANCIENT_ALLOY_NUGGET);
            entries.add(MAGIC_INGOT);
            entries.add(ENCHANTED_MAGIC_INGOT);
            entries.add(BLAZE_METAL);
            entries.add(ANCIENT_ALLOY);
            entries.add(EXOTIC_INGOT);
            entries.add(RUBY);
            entries.add(DRAGON_SCALE);

            entries.add(NETHERITE_STICK);
            entries.add(NETHERITE_STRING);

            entries.add(WOODEN_HAMMER);
            entries.add(WOODEN_SCYTHE);
            entries.add(STONE_HAMMER);
            entries.add(STONE_SCYTHE);
            entries.add(STONE_BOW);
            entries.add(IRON_HAMMER);
            entries.add(IRON_SCYTHE);
            entries.add(IRON_BOW);
            entries.add(GOLDEN_HAMMER);
            entries.add(GOLDEN_SCYTHE);
            entries.add(GOLDEN_BOW);
            entries.add(DIAMOND_HAMMER);
            entries.add(DIAMOND_SCYTHE);
            entries.add(DIAMOND_BOW);
            entries.add(NETHERITE_HAMMER);
            entries.add(NETHERITE_SCYTHE);
            entries.add(NETHERITE_BOW);
            entries.add(AMETHYST_SWORD);
            entries.add(AMETHYST_SHOVEL);
            entries.add(AMETHYST_PICKAXE);
            entries.add(AMETHYST_AXE);
            entries.add(AMETHYST_HOE);
            entries.add(AMETHYST_HAMMER);
            entries.add(AMETHYST_SCYTHE);
            entries.add(AMETHYST_BOW);
            entries.add(EMERALD_SWORD);
            entries.add(EMERALD_SHOVEL);
            entries.add(EMERALD_PICKAXE);
            entries.add(EMERALD_AXE);
            entries.add(EMERALD_HOE);
            entries.add(EMERALD_HAMMER);
            entries.add(EMERALD_SCYTHE);
            entries.add(EMERALD_BOW);
            entries.add(BLAZE_METAL_SWORD);
            entries.add(BLAZE_METAL_SHOVEL);
            entries.add(BLAZE_METAL_PICKAXE);
            entries.add(BLAZE_METAL_AXE);
            entries.add(BLAZE_METAL_HOE);
            entries.add(BLAZE_METAL_HAMMER);
            entries.add(BLAZE_METAL_SCYTHE);
            entries.add(BLAZE_METAL_BOW);
            entries.add(DRAGON_SCALE_SWORD);
            entries.add(DRAGON_SCALE_SHOVEL);
            entries.add(DRAGON_SCALE_PICKAXE);
            entries.add(DRAGON_SCALE_AXE);
            entries.add(DRAGON_SCALE_HOE);
            entries.add(DRAGON_SCALE_HAMMER);
            entries.add(DRAGON_SCALE_SCYTHE);
            entries.add(DRAGON_SCALE_BOW);
            entries.add(ANCIENT_SWORD);
            entries.add(ANCIENT_SHOVEL);
            entries.add(ANCIENT_PICKAXE);
            entries.add(ANCIENT_AXE);
            entries.add(ANCIENT_HOE);
            entries.add(ANCIENT_HAMMER);
            entries.add(ANCIENT_SCYTHE);
            entries.add(ANCIENT_BOW);
            entries.add(IRON_ARROW);
            entries.add(GOLD_ARROW);
            entries.add(DIAMOND_ARROW);
            entries.add(NETHERITE_ARROW);
            entries.add(EMERALD_ARROW);
            entries.add(BLAZE_METAL_ARROW);
            entries.add(DRAGON_SCALE_ARROW);
            entries.add(DRAGON_SCALE_HELMET);
            entries.add(DRAGON_SCALE_CHESTPLATE);
            entries.add(DRAGON_SCALE_LEGGINGS);
            entries.add(DRAGON_SCALE_BOOTS);
        });
        ItemGroupEvents.modifyEntriesEvent(UItemGroups.SHARDS).register(entries -> {
            entries.add(ALLAY_SHARD);
            entries.add(AXOLOTL_SHARD);
            entries.add(BAT_SHARD);
            entries.add(BEE_SHARD);
            entries.add(BLAZE_SHARD);
            entries.add(CAT_SHARD);
            entries.add(CAVE_SPIDER_SHARD);
            entries.add(CHICKEN_SHARD);
            entries.add(COD_SHARD);
            entries.add(COW_SHARD);
            entries.add(CREEPER_SHARD);
            entries.add(DOLPHIN_SHARD);
            entries.add(DONKEY_SHARD);
            entries.add(ENDERMAN_SHARD);
            entries.add(ENDERMITE_SHARD);
            entries.add(EVOKER_SHARD);
            entries.add(FOX_SHARD);
            entries.add(FROG_SHARD);
            entries.add(GHAST_SHARD);
            entries.add(GLOW_SQUID_SHARD);
            entries.add(GOAT_SHARD);
            entries.add(GUARDIAN_SHARD);
            entries.add(HOGLIN_SHARD);
            entries.add(HORSE_SHARD);
            entries.add(IRON_GOLEM_SHARD);
            entries.add(LLAMA_SHARD);
            entries.add(MAGMA_CUBE_SHARD);
            entries.add(OCELOT_SHARD);
            entries.add(PANDA_SHARD);
            entries.add(PARROT_SHARD);
            entries.add(PHANTOM_SHARD);
            entries.add(PIG_SHARD);
            entries.add(PIGLIN_SHARD);
            entries.add(PILLAGER_SHARD);
            entries.add(POLAR_BEAR_SHARD);
            entries.add(PUFFERFISH_SHARD);
            entries.add(RABBIT_SHARD);
            entries.add(RAVAGER_SHARD);
            entries.add(SALMON_SHARD);
            entries.add(SHEEP_SHARD);
            entries.add(SHULKER_SHARD);
            entries.add(SILVERFISH_SHARD);
            entries.add(SKELETON_SHARD);
            entries.add(SLIME_SHARD);
            entries.add(SNOW_GOLEM_SHARD);
            entries.add(SPIDER_SHARD);
            entries.add(SQUID_SHARD);
            entries.add(STRIDER_SHARD);
            entries.add(TROPICAL_FISH_SHARD);
            entries.add(TURTLE_SHARD);
            entries.add(VEX_SHARD);
            entries.add(VILLAGER_SHARD);
            entries.add(VINDICATOR_SHARD);
            entries.add(WARDEN_SHARD);
            entries.add(WITCH_SHARD);
            entries.add(WITHER_SKELETON_SHARD);
            entries.add(WOLF_SHARD);
            entries.add(ZOMBIE_SHARD);

            entries.add(ALLAY_SHARD_FRAGMENT);
            entries.add(AXOLOTL_SHARD_FRAGMENT);
            entries.add(BAT_SHARD_FRAGMENT);
            entries.add(BEE_SHARD_FRAGMENT);
            entries.add(BLAZE_SHARD_FRAGMENT);
            entries.add(CAT_SHARD_FRAGMENT);
            entries.add(CAVE_SPIDER_SHARD_FRAGMENT);
            entries.add(CHICKEN_SHARD_FRAGMENT);
            entries.add(COD_SHARD_FRAGMENT);
            entries.add(COW_SHARD_FRAGMENT);
            entries.add(CREEPER_SHARD_FRAGMENT);
            entries.add(DOLPHIN_SHARD_FRAGMENT);
            entries.add(DONKEY_SHARD_FRAGMENT);
            entries.add(ENDERMAN_SHARD_FRAGMENT);
            entries.add(ENDERMITE_SHARD_FRAGMENT);
            entries.add(EVOKER_SHARD_FRAGMENT);
            entries.add(FOX_SHARD_FRAGMENT);
            entries.add(FROG_SHARD_FRAGMENT);
            entries.add(GHAST_SHARD_FRAGMENT);
            entries.add(GLOW_SQUID_SHARD_FRAGMENT);
            entries.add(GOAT_SHARD_FRAGMENT);
            entries.add(GUARDIAN_SHARD_FRAGMENT);
            entries.add(HOGLIN_SHARD_FRAGMENT);
            entries.add(HORSE_SHARD_FRAGMENT);
            entries.add(IRON_GOLEM_SHARD_FRAGMENT);
            entries.add(LLAMA_SHARD_FRAGMENT);
            entries.add(MAGMA_CUBE_SHARD_FRAGMENT);
            entries.add(OCELOT_SHARD_FRAGMENT);
            entries.add(PANDA_SHARD_FRAGMENT);
            entries.add(PARROT_SHARD_FRAGMENT);
            entries.add(PHANTOM_SHARD_FRAGMENT);
            entries.add(PIG_SHARD_FRAGMENT);
            entries.add(PIGLIN_SHARD_FRAGMENT);
            entries.add(PILLAGER_SHARD_FRAGMENT);
            entries.add(POLAR_BEAR_SHARD_FRAGMENT);
            entries.add(PUFFERFISH_SHARD_FRAGMENT);
            entries.add(RABBIT_SHARD_FRAGMENT);
            entries.add(RAVAGER_SHARD_FRAGMENT);
            entries.add(SALMON_SHARD_FRAGMENT);
            entries.add(SHEEP_SHARD_FRAGMENT);
            entries.add(SHULKER_SHARD_FRAGMENT);
            entries.add(SILVERFISH_SHARD_FRAGMENT);
            entries.add(SKELETON_SHARD_FRAGMENT);
            entries.add(SLIME_SHARD_FRAGMENT);
            entries.add(SNOW_GOLEM_SHARD_FRAGMENT);
            entries.add(SPIDER_SHARD_FRAGMENT);
            entries.add(SQUID_SHARD_FRAGMENT);
            entries.add(STRIDER_SHARD_FRAGMENT);
            entries.add(TROPICAL_FISH_SHARD_FRAGMENT);
            entries.add(TURTLE_SHARD_FRAGMENT);
            entries.add(VEX_SHARD_FRAGMENT);
            entries.add(VILLAGER_SHARD_FRAGMENT);
            entries.add(VINDICATOR_SHARD_FRAGMENT);
            entries.add(WARDEN_SHARD_FRAGMENT);
            entries.add(WITCH_SHARD_FRAGMENT);
            entries.add(WITHER_SKELETON_SHARD_FRAGMENT);
            entries.add(WOLF_SHARD_FRAGMENT);
            entries.add(ZOMBIE_SHARD_FRAGMENT);

            entries.add(ALLAY_SPAWN_EGG);
            entries.add(AXOLOTL_SPAWN_EGG);
            entries.add(BAT_SPAWN_EGG);
            entries.add(BEE_SPAWN_EGG);
            entries.add(BLAZE_SPAWN_EGG);
            entries.add(CAT_SPAWN_EGG);
            entries.add(CAVE_SPIDER_SPAWN_EGG);
            entries.add(CHICKEN_SPAWN_EGG);
            entries.add(COD_SPAWN_EGG);
            entries.add(COW_SPAWN_EGG);
            entries.add(CREEPER_SPAWN_EGG);
            entries.add(DOLPHIN_SPAWN_EGG);
            entries.add(DONKEY_SPAWN_EGG);
            entries.add(DROWNED_SPAWN_EGG);
            entries.add(ENDERMAN_SPAWN_EGG);
            entries.add(ENDERMITE_SPAWN_EGG);
            entries.add(ELDER_GUARDIAN_SPAWN_EGG);
            entries.add(EVOKER_SPAWN_EGG);
            entries.add(FOX_SPAWN_EGG);
            entries.add(FROG_SPAWN_EGG);
            entries.add(GHAST_SPAWN_EGG);
            entries.add(GLOW_SQUID_SPAWN_EGG);
            entries.add(GOAT_SPAWN_EGG);
            entries.add(GUARDIAN_SPAWN_EGG);
            entries.add(HOGLIN_SPAWN_EGG);
            entries.add(HORSE_SPAWN_EGG);
            entries.add(HUSK_SPAWN_EGG);
            entries.add(IRON_GOLEM_SPAWN_EGG);
            entries.add(LLAMA_SPAWN_EGG);
            entries.add(MAGMA_CUBE_SPAWN_EGG);
            entries.add(OCELOT_SPAWN_EGG);
            entries.add(PANDA_SPAWN_EGG);
            entries.add(PARROT_SPAWN_EGG);
            entries.add(PHANTOM_SPAWN_EGG);
            entries.add(PIG_SPAWN_EGG);
            entries.add(PIGLIN_SPAWN_EGG);
            entries.add(PIGLIN_BRUTE_SPAWN_EGG);
            entries.add(PILLAGER_SPAWN_EGG);
            entries.add(POLAR_BEAR_SPAWN_EGG);
            entries.add(PUFFERFISH_SPAWN_EGG);
            entries.add(RABBIT_SPAWN_EGG);
            entries.add(RAVAGER_SPAWN_EGG);
            entries.add(SALMON_SPAWN_EGG);
            entries.add(SHEEP_SPAWN_EGG);
            entries.add(SHULKER_SPAWN_EGG);
            entries.add(SILVERFISH_SPAWN_EGG);
            entries.add(SKELETON_SPAWN_EGG);
            entries.add(SLIME_SPAWN_EGG);
            entries.add(SNOW_GOLEM_SPAWN_EGG);
            entries.add(SPIDER_SPAWN_EGG);
            entries.add(SQUID_SPAWN_EGG);
            entries.add(STRAY_SPAWN_EGG);
            entries.add(STRIDER_SPAWN_EGG);
            entries.add(TROPICAL_FISH_SPAWN_EGG);
            entries.add(TURTLE_SPAWN_EGG);
            entries.add(VEX_SPAWN_EGG);
            entries.add(VILLAGER_SPAWN_EGG);
            entries.add(VINDICATOR_SPAWN_EGG);
            entries.add(WARDEN_SPAWN_EGG);
            entries.add(WITCH_SPAWN_EGG);
            entries.add(WITHER_SKELETON_SPAWN_EGG);
            entries.add(WOLF_SPAWN_EGG);
            entries.add(ZOGLIN_SPAWN_EGG);
            entries.add(ZOMBIE_SPAWN_EGG);
            entries.add(ZOMBIE_VILLAGER_SPAWN_EGG);
            entries.add(ZOMBIFIED_PIGLIN_SPAWN_EGG);
        });
    }
}
