package net.megal.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.megal.UAdd;
import net.megal.entity.UEntities;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class UItems {
    public static final SpawnEggItem SKELETON_ROGUE_SPAWN_EGG = register("skeleton_rogue_spawn_egg", spawnEgg(UEntities.SKELETON_ROGUE, 12698049, 4802889));

    public static final Item RAW_COPPER_NUGGET = register("raw_copper_nugget", item());
    public static final Item RAW_IRON_NUGGET = register("raw_iron_nugget", item());
    public static final Item RAW_GOLD_NUGGET = register("raw_gold_nugget", item());
    public static final Item COPPER_NUGGET = register("copper_nugget", item());
    public static final Item AMETHYST_ALLOY_NUGGET = register("amethyst_alloy_nugget", item());
    public static final Item EMERALD_NUGGET = register("emerald_nugget", item());
    public static final Item LAPIS_LAZULI_NUGGET = register("lapis_lazuli_nugget", item());
    public static final Item DIAMOND_NUGGET = register("diamond_nugget", item());
    public static final Item NETHERITE_NUGGET = register("netherite_nugget", fitem());
    public static final Item AMETHYST_ALLOY = register("amethyst_alloy", item());
    public static final Item AMETHYST_CORE = register("amethyst_core", item());
    public static final SwordItem AMETHYST_SWORD = register("amethyst_sword", new SwordItem(UToolMaterials.AMETHYST, s_sword(UToolMaterials.AMETHYST)));
    public static final ShovelItem AMETHYST_SHOVEL = register("amethyst_shovel", new ShovelItem(UToolMaterials.AMETHYST, s_shovel(UToolMaterials.AMETHYST)));
    public static final PickaxeItem AMETHYST_PICKAXE = register("amethyst_pickaxe", new PickaxeItem(UToolMaterials.AMETHYST, s_pickaxe(UToolMaterials.AMETHYST)));
    public static final AxeItem AMETHYST_AXE = register("amethyst_axe", new AxeItem(UToolMaterials.AMETHYST, s_axe(UToolMaterials.AMETHYST, 5.5f)));
    public static final HoeItem AMETHYST_HOE = register("amethyst_hoe", new HoeItem(UToolMaterials.AMETHYST, s_hoe(UToolMaterials.AMETHYST, -.5f)));
    public static final HealthIncreaseItem AMETHYST_HEART = register("amethyst_heart", healthIncrease("amethyst", 2, 5));
    public static final HealthIncreaseItem PLATED_HEART = register("plated_heart", fhealthIncrease("plated", 4, 2));

    public static Item item() {
        return new Item(new Item.Settings());
    }

    public static Item fitem() {
        return new Item(new Item.Settings().fireproof());
    }

    public static SpawnEggItem spawnEgg(EntityType<? extends MobEntity> entity, int base, int detail) {
        return new SpawnEggItem(entity, base, detail, new Item.Settings());
    }

    public static HealthIncreaseItem healthIncrease(String id, int increase, int uses) {
        return new HealthIncreaseItem(new Item.Settings(), id, increase, uses);
    }

    public static HealthIncreaseItem fhealthIncrease(String id, int increase, int uses) {
        return new HealthIncreaseItem(new Item.Settings().fireproof(), id, increase, uses);
    }

    public static Item.Settings s_sword(ToolMaterial material, int damage, float speed) {
        return new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(material, damage, speed));
    }

    public static Item.Settings s_sword(ToolMaterial material) {
        return s_sword(material, 3, -2.4f);
    }

    public static Item.Settings s_shovel(ToolMaterial material, float damage, float speed) {
        return new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(material, damage, speed));
    }

    public static Item.Settings s_shovel(ToolMaterial material) {
        return s_shovel(material, 1.5f, -3.0f);
    }

    public static Item.Settings s_pickaxe(ToolMaterial material, float damage, float speed) {
        return new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(material, damage, speed));
    }

    public static Item.Settings s_pickaxe(ToolMaterial material) {
        return s_pickaxe(material, 1.0f, -2.8f);
    }

    public static Item.Settings s_axe(ToolMaterial material, float damage, float speed) {
        return new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(material, damage, speed));
    }

    public static Item.Settings s_axe(ToolMaterial material, float damage) {
        return s_axe(material, damage, -3.2f);
    }

    public static Item.Settings s_axe(ToolMaterial material) {
        return s_axe(material, 6.0f);
    }

    public static Item.Settings s_hoe(ToolMaterial material, float damage, float speed) {
        return new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(material, damage, speed));
    }

    public static Item.Settings s_hoe(ToolMaterial material, float speed) {
        return s_hoe(material, -material.getAttackDamage(), speed);
    }

    public static Item.Settings s_hoe(ToolMaterial material) {
        return s_hoe(material, 0);
    }

    public static BlockItem register(Block block) {
        return Registry.register(Registries.ITEM, Registries.BLOCK.getId(block), new BlockItem(block, new Item.Settings()));
    }

    public static <T extends Item> T register(String id, T item) {
        return Registry.register(Registries.ITEM, new Identifier(UAdd.ID, id), item);
    }

    public static void fillItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(UItemGroups.MAIN).register(entries -> {
            entries.add(SKELETON_ROGUE_SPAWN_EGG);

            entries.add(RAW_COPPER_NUGGET);
            entries.add(RAW_IRON_NUGGET);
            entries.add(RAW_GOLD_NUGGET);
            entries.add(COPPER_NUGGET);
            entries.add(AMETHYST_ALLOY_NUGGET);
            entries.add(EMERALD_NUGGET);
            entries.add(LAPIS_LAZULI_NUGGET);
            entries.add(DIAMOND_NUGGET);
            entries.add(NETHERITE_NUGGET);
            entries.add(AMETHYST_ALLOY);
            entries.add(AMETHYST_CORE);

            entries.add(AMETHYST_SWORD);
            entries.add(AMETHYST_SHOVEL);
            entries.add(AMETHYST_PICKAXE);
            entries.add(AMETHYST_AXE);
            entries.add(AMETHYST_HOE);

            entries.add(AMETHYST_HEART);
            entries.add(PLATED_HEART);
        });
    }
}
