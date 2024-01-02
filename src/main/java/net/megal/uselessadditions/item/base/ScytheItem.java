package net.megal.uselessadditions.item.base;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ScytheItem extends MiningToolItem implements Vanishable {
    private final List<String> effects;
    public ScytheItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings, @Nullable String ... effects) {
        super(attackDamage, attackSpeed, material, BlockTags.HOE_MINEABLE, settings);
        this.effects = List.of(effects);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        List<Text> effectsText = UItemHelper.effectsText(stack, effects);
        if (!effectsText.isEmpty()) tooltip.addAll(effectsText);
    }

    @Override
    public ItemStack getDefaultStack() {
        return UItemHelper.setEffects(super.getDefaultStack(), effects);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (UItemHelper.getEffects(stack) != effects) UItemHelper.setEffects(stack, effects);
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
