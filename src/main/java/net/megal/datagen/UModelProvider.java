package net.megal.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.megal.UAdd;
import net.megal.block.UBlocks;
import net.megal.item.UItems;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class UModelProvider extends FabricModelProvider {
    private static final Model SPAWN_EGG = getVanillaItemModel("template_spawn_egg");
    private ItemModelGenerator itemModelGenerator;
    private BlockStateModelGenerator blockStateModelGenerator;

    public UModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        this.blockStateModelGenerator = blockStateModelGenerator;
    }

    private void registerLadder(Block block) {
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(block);
        blockStateModelGenerator.registerItemModel(block);
        getBlockModel("template_ladder", TextureKey.TEXTURE).upload(block, TextureMap.texture(block), blockStateModelGenerator.modelCollector);
    }

    private void registerPillarTop(Block block) {
        Identifier identifier = Models.CUBE_COLUMN.upload(block, new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(block, "_side")).put(TextureKey.END, TextureMap.getSubId(block, "_top")), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, identifier));
    }

    private void registerPillarTopBottom(Block block) {
        Identifier identifier = Models.CUBE_BOTTOM_TOP.upload(block, new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(block, "_side")).put(TextureKey.TOP, TextureMap.getSubId(block, "_top")).put(TextureKey.BOTTOM, TextureMap.getSubId(block, "_bottom")), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, identifier));
    }

    private void registerSideTop(Block side, Block top) {
        Identifier identifier = Models.CUBE_COLUMN.upload(side, new TextureMap().put(TextureKey.SIDE, TextureMap.getId(side)).put(TextureKey.END, TextureMap.getId(top)), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(side, identifier));
    }

    private static Model getBlockModel(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier(UAdd.ID, "block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private static Model getVanillaBlockModel(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier("minecraft", "block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        this.itemModelGenerator = itemModelGenerator;

        registerSpawnEggModel(UItems.SKELETON_ROGUE_SPAWN_EGG);

        registerGeneratedModel(UItems.RAW_COPPER_NUGGET);
        registerGeneratedModel(UItems.RAW_IRON_NUGGET);
        registerGeneratedModel(UItems.RAW_GOLD_NUGGET);
        registerGeneratedModel(UItems.COPPER_NUGGET);
        registerGeneratedModel(UItems.AMETHYST_ALLOY_NUGGET);
        registerGeneratedModel(UItems.EMERALD_NUGGET);
        registerGeneratedModel(UItems.LAPIS_LAZULI_NUGGET);
        registerGeneratedModel(UItems.DIAMOND_NUGGET);
        registerGeneratedModel(UItems.NETHERITE_NUGGET);
        registerGeneratedModel(UItems.AMETHYST_ALLOY);
        registerGeneratedModel(UItems.AMETHYST_CORE);

        registerToolset(UItems.AMETHYST_SWORD, UItems.AMETHYST_SHOVEL, UItems.AMETHYST_PICKAXE, UItems.AMETHYST_AXE, UItems.AMETHYST_HOE);

        registerGeneratedModel(UItems.AMETHYST_HEART);
        registerGeneratedModel(UItems.PLATED_HEART);
    }

    private void registerModel(Model model, Item item) {
        itemModelGenerator.register(item, model);
    }

    private void registerToolset(Item sword, Item shovel, Item pickaxe, Item axe, Item hoe) {
        registerHandheldModel(sword);
        registerHandheldModel(shovel);
        registerHandheldModel(pickaxe);
        registerHandheldModel(axe);
        registerHandheldModel(hoe);
    }

    private void registerHandheldModel(Item item) {
        registerModel(Models.HANDHELD, item);
    }

    private void registerRodModel(Item item) {
        registerModel(Models.HANDHELD_ROD, item);
    }

    private void registerGeneratedModel(Item item) {
        registerModel(Models.GENERATED, item);
    }

    private void registerSpawnEggModel(Item item) {
        registerModel(SPAWN_EGG, item);
    }

    private static Model getItemModel(String id, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier(UAdd.ID, "item/" + id)), Optional.empty(), requiredTextureKeys);
    }

    private static Model getVanillaItemModel(String id, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier("minecraft", "item/" + id)), Optional.empty(), requiredTextureKeys);
    }
}
