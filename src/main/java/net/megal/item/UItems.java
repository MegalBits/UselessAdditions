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

public class UItems {
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING_2X2, ingredients = @SimpleIngredient("minecraft:cobblestone"), swapInputOutput = true)
    @Recipe(type = RecipeType.UNPACKING_4, ingredients = @SimpleIngredient("minecraft:cobblestone"))
    public static final Item ROCK = registerItem("rock", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING_2X2, ingredients = @SimpleIngredient("minecraft:cobbled_deepslate"), swapInputOutput = true)
    @Recipe(type = RecipeType.UNPACKING_4, ingredients = @SimpleIngredient("minecraft:cobbled_deepslate"))
    public static final Item DEEPSLATE_ROCK = registerItem("deepslate_rock", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING_2X2, ingredients = @SimpleIngredient("minecraft:ender_pearl"), swapInputOutput = true)
    @Recipe(type = RecipeType.UNPACKING_4, ingredients = @SimpleIngredient("minecraft:ender_pearl"))
    public static final Item ENDER_PEARL_SHARD = registerItem("ender_pearl_shard", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final Item ANCIENT_SHARD = registerItem("ancient_shard", createFireproofItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:exotic_ingot"), swapInputOutput = true)
    public static final Item EXOTIC_DUST = registerItem("exotic_dust", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("minecraft:raw_copper"), swapInputOutput = true)
    @Recipe(type = RecipeType.SMELTING, ticks = 100, xp = 0.1f, ingredients = @SimpleIngredient("uselessadditions:copper_nugget"), swapInputOutput = true)
    @Recipe(type = RecipeType.BLASTING, ticks = 50, xp = 0.1f, ingredients = @SimpleIngredient("uselessadditions:copper_nugget"), swapInputOutput = true)
    public static final Item RAW_COPPER_NUGGET = registerItem("raw_copper_nugget", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("minecraft:raw_iron"), swapInputOutput = true)
    @Recipe(type = RecipeType.SMELTING, ticks = 100, xp = 0.1f, ingredients = @SimpleIngredient("minecraft:iron_nugget"), swapInputOutput = true)
    @Recipe(type = RecipeType.BLASTING, ticks = 50, xp = 0.1f, ingredients = @SimpleIngredient("minecraft:iron_nugget"), swapInputOutput = true)
    public static final Item RAW_IRON_NUGGET = registerItem("raw_iron_nugget", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("minecraft:raw_gold"), swapInputOutput = true)
    @Recipe(type = RecipeType.SMELTING, ticks = 100, xp = 0.1f, ingredients = @SimpleIngredient("minecraft:gold_nugget"), swapInputOutput = true)
    @Recipe(type = RecipeType.BLASTING, ticks = 50, xp = 0.1f, ingredients = @SimpleIngredient("minecraft:gold_nugget"), swapInputOutput = true)
    public static final Item RAW_GOLD_NUGGET = registerItem("raw_gold_nugget", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("minecraft:copper_ingot"), swapInputOutput = true)
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("minecraft:copper_ingot"))
    public static final Item COPPER_NUGGET = registerItem("copper_nugget", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("minecraft:emerald"), swapInputOutput = true)
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("minecraft:emerald"))
    public static final Item EMERALD_NUGGET = registerItem("emerald_nugget", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("minecraft:diamond"), swapInputOutput = true)
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("minecraft:diamond"))
    public static final Item DIAMOND_NUGGET = registerItem("diamond_nugget", createItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("minecraft:netherite_ingot"), swapInputOutput = true)
    @Recipe(type = RecipeType.UNPACKING, ingredients = @SimpleIngredient("minecraft:netherite_ingot"))
    public static final Item NETHERITE_NUGGET = registerItem("netherite_nugget", createFireproofItem());
    @ItemModel(ModelType.GENERATED)
    @Name
    @Recipe(type = RecipeType.PACKING, ingredients = @SimpleIngredient("uselessadditions:ancient_alloy"), swapInputOutput = true)
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
    @ItemModel(ModelType.GENERATED)
    @Name
    public static final WarpPearl WARP_PEARL = registerItem("warp_pearl", new WarpPearl(new FabricItemSettings().maxCount(1).maxDamage(315)));

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
            entries.add(WARP_PEARL);
        });

    }
}
