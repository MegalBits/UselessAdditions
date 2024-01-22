package net.megal.uselessadditions.plugins.rei;

import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import it.unimi.dsi.fastutil.objects.ReferenceSet;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.entry.EntryRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.displays.crafting.DefaultCustomDisplay;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.block.UBlocks;
import net.megal.uselessadditions.item.*;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.megal.uselessadditions.recipe.EnhancementAugmentRecipe;
import net.megal.uselessadditions.recipe.SmithingNoNbtRecipe;
import net.megal.uselessadditions.recipe.URecipes;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.*;

public class UAddREIClientPlugin implements REIClientPlugin {

    public static final CategoryIdentifier<SmithingNoNbtDisplay> SMITHING_NO_NBT = CategoryIdentifier.of(UAdd.MOD_ID, "smithing_no_nbt");
    public static final CategoryIdentifier<EnhancementDisplay> ENHANCEMENT = CategoryIdentifier.of(UAdd.MOD_ID, "enhancement");

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new SmithingNoNbtCategory());
        registry.addWorkstations(SMITHING_NO_NBT, EntryStacks.of(Items.SMITHING_TABLE));

        registry.add(new EnhancementCategory());
        registry.addWorkstations(ENHANCEMENT, EntryStacks.of(UBlocks.ENHANCEMENT_TABLE));
    }
    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(SmithingNoNbtRecipe.class, RecipeType.SMITHING, SmithingNoNbtDisplay::new);
        registry.registerRecipeFiller(EnhancementAugmentRecipe.class, URecipes.ENHANCEMENT, EnhancementDisplay::new);

        //EntryIngredient mobEggs = EntryIngredients.ofItemTag(UAdd.MOB_EGGS);
        ReferenceSet<EntityType<?>> registeredEntities = new ReferenceOpenHashSet<>();
        EntryRegistry.getInstance().getEntryStacks().filter(entry -> entry.getValueType() == ItemStack.class && entry.<ItemStack>castValue().getItem() instanceof SpawnEgg).forEach(entry -> {
            ItemStack stack = (ItemStack) entry.getValue();

            if (stack.getItem() instanceof SpawnEgg egg && registeredEntities.add(egg.getEntityType())) {
                List<EntryIngredient> input = new ArrayList<>();
                for (int i = 0; i < 4; i++)
                    input.add(EntryIngredients.of(stack.getItem()));
                input.add(EntryIngredients.of(UBlocks.EMPTY_SPAWNER.asItem()));
                for (int i = 0; i < 4; i++)
                    input.add(EntryIngredients.of(stack.getItem()));
                ItemStack outputStack = new ItemStack(UBlocks.SURVIVAL_SPAWNER.asItem());
                outputStack.getOrCreateNbt().putString("EntityStored", Registries.ENTITY_TYPE.getId(egg.getEntityType()).toString());
                EntryIngredient output = EntryIngredients.of(outputStack);
                registry.add(new DefaultCustomDisplay(null, input, Collections.singletonList(output)));
            }
        });
    }

    /*
    @Override
    public void registerEntries(EntryRegistry registry) {
        for (EntityType entity : Registries.ENTITY_TYPE) {
            if (entity == EntityType.PLAYER) continue;
            registry.addEntry();
        }
    }
    */
}
