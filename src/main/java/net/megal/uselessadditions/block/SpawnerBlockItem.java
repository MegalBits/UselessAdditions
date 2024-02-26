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
                tooltip.add(Text.empty());
                tooltip.add(Text.translatable("spawner.uselessadditions.desc").append(Text.translatable(e.getTranslationKey()).formatted(Formatting.BLUE)).formatted(Formatting.GRAY));
            }, () -> {});
        }
    }
}
