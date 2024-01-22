package net.megal.uselessadditions.item.base;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UHoeItem extends HoeItem {
    private final List<String> effects;
    public UHoeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings, @Nullable String ... effects) {
        super(material, attackDamage, attackSpeed, settings);
        this.effects = List.of(effects);
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
}
