package net.megal.item;

import net.megal.particle.UParticles;
import net.megal.player.PlayerNbt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipType;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;

public class HealthIncreaseItem extends Item {
    public static final HashMap<String, HealthIncreaseItem> healthIncreaseItems = new HashMap<>();

    public final String id;
    public final int increase;
    public final int maxUses;

    public HealthIncreaseItem(Settings settings, String id, int increase, int maxUses) {
        super(settings);
        this.id = id;
        this.increase = increase;
        this.maxUses = maxUses;

        healthIncreaseItems.put(id, this);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (((PlayerNbt)user).UAdd$healthIncrease(this)) {
            ItemStack stack = user.getStackInHand(hand);

            stack.decrementUnlessCreative(1, user);

            ItemCooldownManager cooldownManager = user.getItemCooldownManager();
            for (HealthIncreaseItem item : healthIncreaseItems.values()) {
                cooldownManager.set(item, 80);
            }

            Random random = world.getRandom();
            for (int i = 0; i < 10 + random.nextInt(5); i++) {
                world.addParticle(UParticles.HEALTH_INCREASE, user.getX() + random.nextDouble() * 0.7d - 0.35d, user.getY() + random.nextDouble() * user.getHeight(), user.getZ() + random.nextDouble() * 0.7d - 0.35d, 0, 0, 0);
            }

            return TypedActionResult.success(stack);
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        int uses = getUses();
        tooltip.add(Text.translatable("item.uselessadditions.health_increase.increase").append(Text.literal(" " + increase)).formatted(Formatting.GRAY));
        tooltip.add(Text.literal(uses + " / " + maxUses).formatted(uses == maxUses ? Formatting.GOLD : Formatting.GREEN));
    }

    private int getUses() {
        return MinecraftClient.getInstance().player != null ? ((PlayerNbt)MinecraftClient.getInstance().player).UAdd$getHealthIncreases().getOrDefault(id, 0) : 0;
    }
}
