package net.megal.uselessadditions;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.megal.uselessadditions.block.UBlocks;
import net.megal.uselessadditions.effect.UStatusEffects;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.megal.uselessadditions.entity.UEntities;
import net.megal.uselessadditions.item.UGroups;
import net.megal.uselessadditions.item.UItems;
import net.megal.uselessadditions.recipe.URecipes;
import net.megal.uselessadditions.screen.UScreens;
import net.megal.uselessadditions.worldgen.UFeatures;
import net.megal.uselessadditions.worldgen.UStructurePieces;
import net.megal.uselessadditions.worldgen.UStructures;
import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class UAdd implements ModInitializer {
    public static final String MOD_ID = "uselessadditions";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final TagKey<Item> BUNDLES = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "bundles"));
    public static final TagKey<Item> HAMMERS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "smithing_hammers"));
    public static final TagKey<Item> MOB_EGGS = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "mob_eggs"));
    public static final TagKey<Block> PICKAXE_SHOVEL_MINEABLE = TagKey.of(RegistryKeys.BLOCK, new Identifier("uselessadditions", "mineable/pickaxe_shovel"));
    public static final TagKey<Block> VEIN_MINABLE = TagKey.of(RegistryKeys.BLOCK, new Identifier(MOD_ID, "vein_minable"));

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

    private void addCompostable() {
        compostableItem(UItems.DIRT_PILE, ComposterRarities.EXTREMELY_LOW);
        compostableItem(UItems.PLANT_FIBRE, ComposterRarities.VERY_LOW);
        compostableItem(UItems.BUNDLED_FLOWERS, ComposterRarities.HIGH);
        compostableItem(UItems.ASH, ComposterRarities.HIGH);
    }

    private void addDispenserBehaviour() {
        DispenserBlock.registerBehavior(Items.ARROW, new ProjectileDispenserBehavior() {
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                ArrowEntity arrowEntity = new ArrowEntity(world, position.getX(), position.getY(), position.getZ(), stack.copyWithCount(1));
                arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                return arrowEntity;
            }
        });
    }

    private static void addTrades() {
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 36), new ItemStack(Items.LAVA_BUCKET, 1), 1, 1, 0f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 1), new ItemStack(Items.BONE_MEAL, 2), 16, 1, 0.07f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 4, factories -> {
            factories.add(((entity, random) -> new TradeOffers.SellItemFactory(UItems.CHOCOLATE_CAKE, 1, 1, 12, 15).create(entity, random)));
            factories.add(((entity, random) -> new TradeOffers.SellItemFactory(UItems.RAINBOW_CAKE, 5, 1, 12, 20).create(entity, random)));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 1, factories -> {
            factories.add((entity, random) -> new TradeOffers.EnchantBookFactory(5).create(entity, random));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 5, factories -> {
            factories.add((entity, random) -> new TradeOffers.EnchantBookFactory(5).create(entity, random));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 24), new ItemStack(Items.LAVA_BUCKET, 1), 3, 15, 0.2f
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

    private static void addServerEvents() {
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
    }

    @Override
    public void onInitialize() {
        //"config/uadd/common.toml"

        //Loads server events
        addServerEvents();

        //Loads entity attributes
        UEntities.registerAttributes();

        //Loads entity spawn settings
        UEntities.registerSpawning();

        //Loads status effects
        UStatusEffects.effLoad();

        //Loads enchantments
        UEnchantments.enchLoad();

        //Loads item tabs
        UGroups.groupLoad();

        //Adds items to their respective creative tabs
        UItems.itemTabs();

        //Loads blocks
        UBlocks.blockLoad();

        //Makes items compostable
        addCompostable();

        //Adds custom trades
        addTrades();

        //Loads screens so that they work
        UScreens.loadStuff();

        //Loads recipes
        URecipes.loadStuff();

        //Loads structure pieces
        UStructurePieces.loadStuff();

        //Loads in structures
        UStructures.loadStuff();

        //Loads in features like ores
        UFeatures.loadStuff();

        //Adds stuff to loot tables
        ULootTables.modifyLootTables();
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