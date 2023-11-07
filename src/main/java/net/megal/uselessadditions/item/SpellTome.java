package net.megal.uselessadditions.item;

import net.megal.uselessadditions.magic.Spell;
import net.megal.uselessadditions.magic.USpells;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class SpellTome extends SpellBook {
    Random random = Random.create();
    List<Spell> availableSpells = List.of(
            USpells.BASIC_DAMAGE,
            USpells.HIGH_DAMAGE,
            USpells.BASIC_ARROW,
            USpells.FAST_ARROW
    );
    public SpellTome(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!user.getStackInHand(hand).hasNbt()) {
            user.setStackInHand(hand, this.getDefaultStack());
            return TypedActionResult.consume(user.getStackInHand(hand));
        }
        return super.use(world, user, hand);
    }

    @Override
    public ItemStack getDefaultStack() {
        List<Spell> spells = new ArrayList<>();
        for (int i = 0; i < random.nextBetween(2, 5); i++) {
            Spell spell = availableSpells.get(random.nextBetween(0, availableSpells.size()-1));
            spells.add(spell);
        }
        return UItems.createSpellBookWithSpells(spells);
    }
}
