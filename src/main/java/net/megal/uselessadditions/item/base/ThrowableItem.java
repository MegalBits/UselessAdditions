package net.megal.uselessadditions.item.base;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class ThrowableItem extends Item {
    public ThrowableItem(Settings settings) {
        super(settings);
    }

    public abstract ThrownItemEntity getThrownItemEntity(World world, PlayerEntity user);

    public abstract SoundEvent getSound();

    @Nullable
    public Item[] cooldownItems() {
        return null;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), getSound(), SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        Item[] cooldownItems = cooldownItems();
        if (cooldownItems != null) {
            for (Item item : cooldownItems) {
                user.getItemCooldownManager().set(item, 20);
            }
        }
        if (!world.isClient) {
            ThrownItemEntity thrownItem = getThrownItemEntity(world, user);
            thrownItem.setItem(itemStack);
            thrownItem.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(thrownItem);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }
}
