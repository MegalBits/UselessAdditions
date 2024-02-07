package net.megal.uselessadditions.item.base;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.megal.uselessadditions.item.SpecialEffects;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class UBowItem extends BowItem {
    private final List<String> effects;
    public final float speed;
    public final float accuracy;
    public final float useTime;

    public UBowItem(ToolMaterial material, float speed, float accuracy, float useTime, Settings settings, @Nullable String ... effects) {
        super(settings.maxDamage(material.getDurability()));

        this.speed = speed;
        this.accuracy = accuracy;
        this.useTime = useTime;

        this.effects = List.of(effects);
    }

    public UBowItem(ToolMaterial material, Settings settings) {
        this(material, 3, 1, 1, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        List<Text> effectsText = UItemHelper.effectsText(stack, effects);
        if (!effectsText.isEmpty() && stack.getOrCreateNbt().isEmpty()) tooltip.addAll(effectsText);
    }

    @Override
    public ItemStack getDefaultStack() {
        return UItemHelper.setEffects(super.getDefaultStack(), effects);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        for (String s : effects) {
            if (!UItemHelper.getEffects(stack).contains(s)) UItemHelper.addEffects(stack, List.of(s));
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            Random random = world.getRandom();
            boolean bl = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemStack = playerEntity.getProjectileType(stack);
            if (!itemStack.isEmpty() || bl) {
                if (itemStack.isEmpty()) {
                    itemStack = new ItemStack(Items.ARROW);
                }

                float speedExtra = 0;
                float accuracyIncrease = 0;

                boolean hasCXP = UItemHelper.getEffects(stack).contains(SpecialEffects.CRYSTALLIZED_XP);

                if (UItemHelper.getEffects(stack).contains(SpecialEffects.CRYSTALLIZED_XP)) {
                    int level = Math.min(playerEntity.experienceLevel, 80);
                    speedExtra = level * SpecialEffects.CXP_ARROW_SPEED;
                    accuracyIncrease = Math.min(level * SpecialEffects.CXP_ACCURACY, accuracy);
                }


                int i = this.getMaxUseTime(stack) - remainingUseTicks;
                float f = getPullProgress(i, useTime, hasCXP, playerEntity.experienceLevel);
                if (!((double)f < 0.1)) {
                    boolean bl2 = bl && itemStack.isOf(Items.ARROW);
                    if (!world.isClient) {
                        ArrowItem arrowItem = (ArrowItem)(itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
                        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, itemStack, playerEntity);
                        persistentProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * (speed + speedExtra), accuracy - accuracyIncrease);
                        if (f == 1.0F) {
                            persistentProjectileEntity.setCritical(true);
                        }

                        int j = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                        if (j > 0) {
                            persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double)j * 0.5 + 0.5);
                        }

                        int k = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
                        if (k > 0) {
                            persistentProjectileEntity.setPunch(k);
                        }

                        if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                            persistentProjectileEntity.setOnFireFor(100);
                        }

                        applyArrowEffects(persistentProjectileEntity, stack, world, user);

                        stack.damage(1, (LivingEntity)playerEntity, ((p) -> {
                            p.sendToolBreakStatus(playerEntity.getActiveHand());
                        }));
                        if (bl2 || playerEntity.getAbilities().creativeMode && (itemStack.isOf(Items.SPECTRAL_ARROW) || itemStack.isOf(Items.TIPPED_ARROW))) {
                            persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                        }

                        if (UItemHelper.getEffects(stack).contains(SpecialEffects.DUAL_SHOT)) {
                            PersistentProjectileEntity extraProjectile = arrowItem.createArrow(world, itemStack, playerEntity);
                            UUID uuid = extraProjectile.getUuid();

                            extraProjectile.copyFrom(persistentProjectileEntity);
                            extraProjectile.setUuid(uuid);

                            extraProjectile.setVelocity(playerEntity, playerEntity.getPitch() + ((random.nextFloat() - .5f) * 3), playerEntity.getYaw() + ((random.nextFloat() - .5f) * 3), 0.0F, f * (speed + speedExtra) * .8f, (accuracy - accuracyIncrease) * 20);

                            extraProjectile.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;

                            world.spawnEntity(extraProjectile);
                        }

                        world.spawnEntity(persistentProjectileEntity);
                    }

                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!bl2 && !playerEntity.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                        if (itemStack.isEmpty()) {
                            playerEntity.getInventory().removeOne(itemStack);
                        }
                    }

                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
            }
        }
    }

    public static float getPullProgress(int useTicks, float useTime, boolean hasCXP, int xpLevel) {
        float timeDecrease = 0;
        if (hasCXP) timeDecrease = Math.min(xpLevel, 80) * SpecialEffects.CXP_CHARGE_SPEED;
        float f = (float)useTicks / ((useTime - timeDecrease) * 20);
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public static void applyArrowEffects(PersistentProjectileEntity projectile, ItemStack stack, World world, LivingEntity user) {
        if (EnchantmentHelper.getLevel(UEnchantments.AUTO_SMELT, stack) > 0 || UItemHelper.getEffects(stack).contains(SpecialEffects.AUTO_SMELT)) {
            projectile.setOnFireFor(100);
        }
        //return projectile;
    }
}
