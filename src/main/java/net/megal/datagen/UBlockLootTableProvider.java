package net.megal.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.megal.block.UBlocks;
import net.megal.item.UItems;
import net.megal.item.UToolMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class UBlockLootTableProvider extends FabricBlockLootTableProvider {
    public UBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

    }

    public LootTable.Builder withSilkTouchOrShears(ItemConvertible drop) {
        return LootTable.builder()
                .pool(LootPool.builder()
                        .conditionally(WITH_SILK_TOUCH_OR_SHEARS)
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(ItemEntry.builder(drop)));
    }

    public LootTable.Builder groupOreDrops(Block block, ItemConvertible drop, float min, float max) {
        return dropsWithSilkTouch(
                block,
                this.applyExplosionDecay(
                        block,
                        ItemEntry.builder(drop)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max)))
                                .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE))
                )
        );
    }

    public void addGravel(Block gravel) {
        addDrop(
                gravel,
                block -> dropsWithSilkTouch(
                        block,
                        this.addSurvivesExplosionCondition(
                                block,
                                ItemEntry.builder(Items.FLINT)
                                        .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.1f, 0.14285715f, 0.25f, 1.0f))
                                        .alternatively(ItemEntry.builder(block))
                        )
                )
        );
    }
}
