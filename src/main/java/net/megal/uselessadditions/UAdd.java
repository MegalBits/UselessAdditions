package net.megal.uselessadditions;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.megal.uselessadditions.block.UBlocks;
import net.megal.uselessadditions.effect.UStatusEffects;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.megal.uselessadditions.item.UGroups;
import net.megal.uselessadditions.item.UItems;
import net.megal.uselessadditions.recipe.URecipes;
import net.megal.uselessadditions.screen.UScreens;
import net.minecraft.block.Block;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class UAdd implements ModInitializer {
    public static final String MOD_ID = "uselessadditions";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final TagKey<Item> BUNDLES = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "bundles"));
    public static final TagKey<Item> HAMMERS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "smithing_hammers"));
    public static final TagKey<Item> NATURAL_MENDING = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "natural_mending"));
    public static final TagKey<Item> AUTO_SMELT = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "auto_smelt"));
    public static final TagKey<Item> MOB_EGGS = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "mob_eggs"));
    public static final TagKey<Item> MOB_SHARDS = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "mob_shards"));
    public static final TagKey<Item> SMALL_MOB_SHARDS = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "small_mob_shards"));
    public static final TagKey<Block> PICKAXE_SHOVEL_MINEABLE = TagKey.of(RegistryKeys.BLOCK, new Identifier("uselessadditions", "mineable/pickaxe_shovel"));
    public static final TagKey<Block> OBSIDIAN_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "obsidian_blocks"));
    public static final List<Item> naturalMendingItems = new ArrayList<>();
    public static final List<Item> autoSmeltItems = new ArrayList<>();
    public static boolean expandDescriptions = true;

    static {
        naturalMendingItems.add(UItems.ENCHANTED_HAMMER);
        naturalMendingItems.add(UItems.AMETHYST_SWORD);
        naturalMendingItems.add(UItems.AMETHYST_SHOVEL);
        naturalMendingItems.add(UItems.AMETHYST_PICKAXE);
        naturalMendingItems.add(UItems.AMETHYST_AXE);
        naturalMendingItems.add(UItems.AMETHYST_HOE);
        autoSmeltItems.add(UItems.BLAZE_METAL_SWORD);
        autoSmeltItems.add(UItems.BLAZE_METAL_SHOVEL);
        autoSmeltItems.add(UItems.BLAZE_METAL_PICKAXE);
        autoSmeltItems.add(UItems.BLAZE_METAL_AXE);
        autoSmeltItems.add(UItems.BLAZE_METAL_HOE);
    }

    private record VelocityCalculationValue(Vec3d pos, float movementTime, double velocity, float lastMove, float f) {}
    private static final HashMap<UUID, VelocityCalculationValue> playerLastPos = new HashMap<>();
    private static final HashMap<UUID, VelocityCalculationValue> mobLastPos = new HashMap<>();

    public static List<?> calculateVelocity(LivingEntity entity) {
        UUID uuid = entity.getUuid();
        Vec3d pos = entity.getPos();
        double vel = 0;
        float movementTime = 0;
        float lastMove = 0;
        if (entity instanceof PlayerEntity) {
            if (playerLastPos.containsKey(uuid)) {
                VelocityCalculationValue calc = playerLastPos.get(uuid);
                if (pos != playerLastPos.get(uuid).pos) {
                    vel = pos.distanceTo(calc.pos)*20;
                }
                movementTime = calc.movementTime;
                lastMove = calc.lastMove;
            }
            playerLastPos.put(uuid, new VelocityCalculationValue(pos, movementTime, vel, lastMove, 20f));
        }
        else {
            if (mobLastPos.containsKey(uuid)) {
                VelocityCalculationValue calc = mobLastPos.get(uuid);
                if (pos != mobLastPos.get(uuid).pos) {
                    vel = pos.distanceTo(calc.pos)*20;
                }
                movementTime = calc.movementTime;
                lastMove = calc.lastMove;
            }
            mobLastPos.put(uuid, new VelocityCalculationValue(pos, movementTime, vel, lastMove, 20f));
        }
        return List.of(vel, movementTime, lastMove);
    }

    public static int calcCooldown(ItemStack stack, int cooldown) {
        return (int)(cooldown * (1f - EnchantmentHelper.getLevel(UEnchantments.QUICKER_COOLDOWN, stack)*.05f));
    }

    private void addCompostable() {
        compostableItem(UItems.DIRT_PILE, ComposterRarities.EXTREMELY_LOW);
        compostableItem(UItems.PLANT_FIBRE, ComposterRarities.VERY_LOW);
        compostableItem(UItems.LESSER_GOLDEN_APPLE, ComposterRarities.HIGH);
        compostableItem(UItems.LESSER_GOLDEN_CARROT, ComposterRarities.HIGH);
        compostableItem(UItems.BUNDLED_FLOWERS, ComposterRarities.HIGH);
        compostableItem(UItems.ASH, ComposterRarities.HIGH);
    }
    private static void addTrades() {
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 36), new ItemStack(Items.LAVA_BUCKET, 1), 1, 1, 0f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 1), new ItemStack(Items.BONE_MEAL, 2), 16, 3, 0.07f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 5, factories -> {
            factories.add((entity, random) -> enchantedBookTrade(random.nextBetween(2,4), 15));
            factories.add((entity, random) -> extraLevelEnchantedBookTrade(15));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 18), new ItemStack(Items.LAVA_BUCKET, 1), 3, 15, 0.2f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 1), new ItemStack(Items.STONE_BRICKS, 4), 16, 5, 0.05f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 1), new ItemStack(Items.CRACKED_STONE_BRICKS, 4), 16, 5, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 1), new ItemStack(Items.SMOOTH_STONE, 4), 16, 10, 0.05f
            ));
        });
    }
    @Override
    public void onInitialize() {
        ServerPlayNetworking.registerGlobalReceiver(new Identifier(MOD_ID, "expanded_descriptions"), (server, player, handler, buf, responseSender) -> {
            boolean b = buf.readBoolean();
            server.execute(() -> {
                expandDescriptions = b;
            });
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (UUID uuid : playerLastPos.keySet()) {
                VelocityCalculationValue calc = playerLastPos.get(uuid);
                Vec3d pos = calc.pos;
                float movementTime = calc.movementTime;
                double velocity = calc.velocity;
                float lastMove = calc.lastMove;
                float f = calc.f;
                if (f-1 < 30) playerLastPos.put(uuid, new VelocityCalculationValue(pos, velocity > 0 ? movementTime+1 : 0, velocity, velocity == 0 ? lastMove+1 : 0, f-1));
                else playerLastPos.remove(uuid);
            }
            for (UUID uuid : mobLastPos.keySet()) {
                VelocityCalculationValue calc = mobLastPos.get(uuid);
                Vec3d pos = calc.pos;
                float movementTime = calc.movementTime;
                double velocity = calc.velocity;
                float lastMove = calc.lastMove;
                float f = calc.f;
                if (f-1 < 30) mobLastPos.put(uuid, new VelocityCalculationValue(pos, velocity > 0 ? movementTime+1 : 0, velocity, lastMove+1, f-1));
                else mobLastPos.remove(uuid);
            }
        });

        //Loads status effects
        UStatusEffects.effLoad();

        //Loads enchantments
        UEnchantments.enchLoad();

        //Loads blocks
        UBlocks.blockLoad();

        //Loads item tabs
        UGroups.groupLoad();

        //Adds items to their respective creative tabs
        UItems.itemTabs();

        //Makes items compostable
        addCompostable();

        //Adds custom trades
        addTrades();

        //Loads screens so that they work
        UScreens.loadStuff();

        //
        URecipes.loadStuff();

        //Loads in world gen stuff such as ores
        UWorldgen.wgenLoad();

        //Adds stuff to loot tables
        ULootTables.modifyLootTables();
    }
    private static TradeOffer enchantedBookTrade(int enchantCount, int xp) {
        Random random = Random.create();
        List<Enchantment> list = new ArrayList<>(Registries.ENCHANTMENT.stream().filter(Enchantment::isAvailableForEnchantedBookOffer).toList());
        List<Enchantment> itemEnchantments = new ArrayList<>();
        ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
        float cost = 0;
        for (int i = 0; i < enchantCount; i++) {
            int index = random.nextInt(list.size());
            Enchantment enchantment = list.get(index);
            boolean isCompat = false;
            int ii = 0;
            while (!isCompat && ii < 5) {
                isCompat = checkEnchantCompat(enchantment, itemEnchantments);
                ii++;
            }
            if (isCompat) {
                itemEnchantments.add(list.get(index));
                int lv = MathHelper.nextInt(random, enchantment.getMinLevel(), enchantment.getMaxLevel());
                stack.addEnchantment(enchantment, lv);
                int eCost = 2 + random.nextInt(5 + lv * 10) + 3 * lv;
                if (enchantment.isTreasure()) {
                    eCost *= 2;
                }
                cost += eCost;
            }
            list.remove(index);
        }
        cost *= Math.max(Math.max((1f-((enchantCount-1)/5f)), .5f),1);

        return new TradeOffer(new ItemStack(cost/9f > 7.5f ? Items.EMERALD_BLOCK : Items.EMERALD, cost/9f > 7.5f ? Math.max(Math.round(cost/9f), 1) : (int) cost), new ItemStack(Items.BOOK), stack, 6, xp, cost/9f > 7.5f ? 0.1f : 0.2f);
    }
    private static TradeOffer extraLevelEnchantedBookTrade(int xp) {
        Random random = Random.create();
        List<Enchantment> list = new ArrayList<>(Registries.ENCHANTMENT.stream().filter(Enchantment::isAvailableForEnchantedBookOffer).toList());
        ItemStack buyStack = new ItemStack(Items.ENCHANTED_BOOK);
        ItemStack sellStack = buyStack.copy();
        float cost = 0;
        int index = random.nextInt(list.size());
        Enchantment enchantment = list.get(index);
        int lv = enchantment.getMaxLevel();
        float multiplier = 1.5f;
        if (lv > 1) {
            buyStack.addEnchantment(enchantment, lv);
            sellStack.addEnchantment(enchantment, lv+1);
            multiplier = 3f;
        }
        else {
            buyStack = new ItemStack(Items.BOOK);
            sellStack.addEnchantment(enchantment, lv);
        }
        int eCost = 2 + random.nextInt(5 + lv * 10) + 3 * (lv+1);
        if (enchantment.isTreasure()) {
            eCost *= 2;
        }
        cost += eCost;
        cost *= multiplier;

        return new TradeOffer(new ItemStack(cost/9f > 7.5f ? Items.EMERALD_BLOCK : Items.EMERALD, cost/9f > 7.5f ? Math.max(Math.round(cost/9f), 1) : (int) cost), buyStack, sellStack, 2, xp, cost/9f > 7.5f ? 0.1f : 0.2f);
    }
    private static boolean checkEnchantCompat(Enchantment enchantment, List<Enchantment> list) {
        for (Enchantment other : list) {
            if (!enchantment.canCombine(other)) return false;
        }
        return true;
    }
    private void compostableItem(Item item, ComposterRarities rarity) {
        CompostingChanceRegistry.INSTANCE.add(item, rarity.getRarity());
    }
    private enum ComposterRarities {
        EXTREMELY_LOW(.1f),
        VERY_LOW(.3f),
        LOW(.5f),
        MED(.65f),
        HIGH(.85f),
        GUARUNTEED(1f);
        private final float rarity;
        private ComposterRarities(float rarity) {
            this.rarity = rarity;
        }
        public float getRarity() {return rarity;}
    }


}