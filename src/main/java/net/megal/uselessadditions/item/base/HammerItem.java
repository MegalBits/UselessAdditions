package net.megal.uselessadditions.item.base;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.mixin.ItemRemainder;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HammerItem extends MiningToolItem {
    private final List<String> effects;

    public HammerItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings, @Nullable String ... effects) {
        super(attackDamage, attackSpeed, UItemHelper.modifyMaterial(material, 0, -1, 0), UAdd.PICKAXE_SHOVEL_MINEABLE, settings);
        ((ItemRemainder)this).setRecipeRemainder(this);
        this.effects = List.of(effects);
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        ItemStack alteredStack = new ItemStack(this, stack.getCount());
        alteredStack.setNbt(stack.getNbt());
        alteredStack.setDamage(stack.getDamage()+1);
        return !(alteredStack.getDamage() >= alteredStack.getMaxDamage()) ? alteredStack : ItemStack.EMPTY;
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
