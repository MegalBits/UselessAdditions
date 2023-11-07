package net.megal.uselessadditions.item;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.magic.Spell;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpellBook extends Item {
    public SpellBook(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbt = stack.getOrCreateNbt();
        if (nbt.contains("Spells")) {
            MutableText spellOrder = Text.empty();
            NbtList nbtList = nbt.getList("Spells", NbtElement.COMPOUND_TYPE);
            for (NbtElement spell : nbtList) {
                NbtCompound spellCompound = (NbtCompound) spell;
                if (!spellOrder.equals(Text.empty())) spellOrder.append(Text.literal(" -> "));
                if (spellCompound.contains("TranslationKey")) spellOrder.append(Text.translatable(spellCompound.getString("TranslationKey")));
                else spellOrder.append(Text.literal(spellCompound.getString("Name")));
            }
            spellOrder.formatted(Formatting.DARK_PURPLE, Formatting.ITALIC);
            if (!spellOrder.equals(Text.empty())) tooltip.add(spellOrder);
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        NbtCompound nbt = stack.getOrCreateNbt();
        if (nbt.contains("Spells")) {
            NbtList nbtList = nbt.getList("Spells", NbtElement.COMPOUND_TYPE);
            for (NbtElement spell : nbtList) {
                NbtCompound spellCompound = (NbtCompound) spell;
                NbtList instructions = spellCompound.getList("Instructions", NbtElement.COMPOUND_TYPE);
                for (NbtElement instruction : instructions) {
                    NbtCompound instructionCompound = (NbtCompound) instruction;
                    new Spell(Spell.Instruction.readFromNbt(instructionCompound), null, null).runRightClickInstructions(user, null, user.getWorld());
                }
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        NbtCompound nbt = stack.getOrCreateNbt();
        if (nbt.contains("Spells")) {
            NbtList nbtList = nbt.getList("Spells", NbtElement.COMPOUND_TYPE);
            for (NbtElement spell : nbtList) {
                NbtCompound spellCompound = (NbtCompound) spell;
                NbtList instructions = spellCompound.getList("Instructions", NbtElement.COMPOUND_TYPE);
                for (NbtElement instruction : instructions) {
                    NbtCompound instructionCompound = (NbtCompound) instruction;
                    new Spell(Spell.Instruction.readFromNbt(instructionCompound), null, null).runRightClickInstructions(user, entity, user.getWorld());
                }
            }
        }
        return super.useOnEntity(stack, user, entity, hand);
    }
}
