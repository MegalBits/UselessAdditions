package net.megal.uselessadditions.item;

import net.megal.uselessadditions.entity.SlimePearlEntity;
import net.megal.uselessadditions.item.base.ThrowableItem;
import net.minecraft.client.render.entity.BeeEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SlimePearl extends ThrowableItem {
    public SlimePearl(Settings settings) {
        super(settings);
    }

    @Override
    public ThrownItemEntity getThrownItemEntity(World world, PlayerEntity user) {
        return new SlimePearlEntity(world, user);
    }

    @Override
    public SoundEvent getSound() {
        return SoundEvents.ENTITY_ENDER_PEARL_THROW;
    }

    @Override
    @Nullable
    public Item[] cooldownItems() {
        return new Item[] {this, Items.ENDER_PEARL};
    }
}
