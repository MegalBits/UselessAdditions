package net.megal.uselessadditions.block;

import net.megal.uselessadditions.UAdd;
import net.minecraft.block.Block;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;

public class SpawnerBlockItem extends BlockItem {
    private final MutableText s = Text.literal("s");
    private final MutableText m = Text.literal("m");
    private final MutableText h = Text.literal("h");
    public final int STAT_COLOR = 0x384258;

    public SpawnerBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = new ItemStack(this);
        stack.getOrCreateNbt().putString("EntityStored","minecraft:pig");
        return stack;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        if (stack.getOrCreateNbt().contains("EntityStored")) {
            Registries.ENTITY_TYPE.getOrEmpty(new Identifier(stack.getOrCreateNbt().getString("EntityStored"))).ifPresentOrElse(e -> {
                tooltip.add(Text.translatable("spawner.uselessadditions.desc").append(Text.translatable(e.getTranslationKey()).formatted(Formatting.BLUE)).formatted(Formatting.GRAY));
            }, () -> {
            });
        }
        tooltip.add(Text.translatable("spawner.uselessadditions.stats").formatted(Formatting.GRAY));
        tooltip.add(rechargeText(SurvivalSpawner.EMERALD, SurvivalSpawner.EMERALD_TIME));
        tooltip.add(rechargeText(SurvivalSpawner.DIAMOND, SurvivalSpawner.DIAMOND_TIME));
        tooltip.add(rechargeText(SurvivalSpawner.NETHERITE, SurvivalSpawner.NETHERITE_TIME));
    }

    private MutableText rechargeText(Item item, int time) {
        time /= 20;
        MutableText timeType = s.copy();
        int displayTime = time;
        if (time >= 60) {
            displayTime /= 60;
            timeType = m.copy();
        }
        if (time/60 >= 60) {
            displayTime /= 60;
            timeType = h.copy();
        }
        return Text.literal(" ").append(
            Text.translatable("spawner.uselessadditions.recharge").append(
            Text.literal(String.valueOf(displayTime)).styled(style -> style.withColor(STAT_COLOR)).append(
            timeType.styled(style -> style.withColor(STAT_COLOR))).append(
            Text.translatable("spawner.uselessadditions.per").formatted(Formatting.DARK_GRAY).append(
            item.getName().copy().styled(style -> style.withColor(STAT_COLOR)))))).formatted(Formatting.DARK_GRAY);
    }
}
