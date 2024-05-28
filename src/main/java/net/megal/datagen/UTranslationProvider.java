package net.megal.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.loader.impl.util.StringUtil;
import net.megal.block.UBlocks;
import net.megal.entity.UEntities;
import net.megal.item.UItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class UTranslationProvider extends FabricLanguageProvider {
    private TranslationBuilder translationBuilder;

    public UTranslationProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        this.translationBuilder = translationBuilder;
        autoRegister(UEntities.SKELETON_ROGUE);

        autoRegister(UItems.SKELETON_ROGUE_SPAWN_EGG);

        autoRegister(UItems.RAW_COPPER_NUGGET);
        autoRegister(UItems.RAW_IRON_NUGGET);
        autoRegister(UItems.RAW_GOLD_NUGGET);
        autoRegister(UItems.COPPER_NUGGET);
        autoRegister(UItems.AMETHYST_ALLOY_NUGGET);
        autoRegister(UItems.EMERALD_NUGGET);
        autoRegister(UItems.LAPIS_LAZULI_NUGGET);
        autoRegister(UItems.DIAMOND_NUGGET);
        autoRegister(UItems.NETHERITE_NUGGET);
        autoRegister(UItems.AMETHYST_ALLOY);
        autoRegister(UItems.AMETHYST_CORE);

        autoRegister(UItems.AMETHYST_SWORD);
        autoRegister(UItems.AMETHYST_SHOVEL);
        autoRegister(UItems.AMETHYST_PICKAXE);
        autoRegister(UItems.AMETHYST_AXE);
        autoRegister(UItems.AMETHYST_HOE);

        autoRegister(UItems.AMETHYST_HEART);
        autoRegister(UItems.PLATED_HEART);

        includeManualFile();
    }

    private void autoRegister(Item item) {
        translationBuilder.add(item, identifierToName(Registries.ITEM.getId(item)));
    }

    private void autoRegister(Block block) {
        translationBuilder.add(block, identifierToName(Registries.BLOCK.getId(block)));
    }

    private void autoRegister(EntityType<?> entity) {
        translationBuilder.add(entity, identifierToName(Registries.ENTITY_TYPE.getId(entity)));
    }

    private String identifierToName(Identifier identifier) {
        StringBuilder nameBuilder = new StringBuilder();
        Arrays.stream(identifier.getPath().replace("_", "& ").split("&")).forEach(word -> nameBuilder.append(StringUtil.capitalize(word)));
        return nameBuilder.toString();
    }

    private void includeManualFile() {
        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/uselessadditions/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        }
        catch (Exception e) {
            throw new RuntimeException("Couldn't find existing lang file :P", e);
        }
    }
}
