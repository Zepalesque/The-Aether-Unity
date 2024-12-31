package net.zepalesque.unity.blockset.util;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.data.prov.UnityBlockStateProvider;
import net.zepalesque.unity.data.prov.UnityDataMapProvider;
import net.zepalesque.unity.data.prov.UnityItemModelProvider;
import net.zepalesque.unity.data.prov.UnityLanguageProvider;
import net.zepalesque.unity.data.prov.UnityRecipeProvider;
import net.zepalesque.unity.data.prov.loot.UnityBlockLootProvider;
import net.zepalesque.unity.data.prov.tags.UnityBlockTagsProvider;
import net.zepalesque.unity.data.prov.tags.UnityItemTagsProvider;
import net.zepalesque.zenith.api.blockset.BlockSet;

import java.util.function.Consumer;

public interface AetherGenerationHelper<
        StateProv extends UnityBlockStateProvider<StateProv>,
        IModelProv extends UnityItemModelProvider<IModelProv>,
        LangProv extends UnityLanguageProvider<LangProv>,
        RecipeProv extends UnityRecipeProvider<RecipeProv>,
        BTagsProv extends UnityBlockTagsProvider<BTagsProv>,
        ITagsProv extends UnityItemTagsProvider<ITagsProv>,
        BLootProv extends UnityBlockLootProvider<BLootProv>,
        DataMapProv extends UnityDataMapProvider<DataMapProv>> extends BlockSet {


    @Override
    default void blockData(BlockStateProvider provider) {
        this.<BlockStateProvider, StateProv>generate(this::blockData, provider);
    }

    void blockData(StateProv data);


    @Override
    default void itemData(ItemModelProvider provider) {
        this.<ItemModelProvider, IModelProv>generate(this::itemData, provider);

    }

    void itemData(IModelProv data);


    @Override
    default void langData(LanguageProvider provider) {
        this.<LanguageProvider, LangProv>generate(this::langData, provider);
    }


    void langData(LangProv data);

    @Override
    default void recipeData(RecipeProvider provider, RecipeOutput output) {
        this.<RecipeProvider, RecipeProv>generate(recipes -> this.recipeData(recipes, output), provider);
    }

    void recipeData(RecipeProv data, RecipeOutput output);


    @Override
    default void blockTagData(BlockTagsProvider provider) {
        this.<BlockTagsProvider, BTagsProv>generate(this::blockTagData, provider);
    }

    void blockTagData(BTagsProv data);

    @Override
    default void itemTagData(ItemTagsProvider provider) {
        this.<ItemTagsProvider, ITagsProv>generate(this::itemTagData, provider);

    }

    void itemTagData(ITagsProv data);

    @Override
    default void lootData(BlockLootSubProvider provider) {
        this.<BlockLootSubProvider, BLootProv>generate(this::lootData, provider);
    }

    void lootData(BLootProv data);

    @Override
    default void mapData(DataMapProvider provider) {
        this.<DataMapProvider, DataMapProv>generate(this::mapData, provider);
    }

    void mapData(DataMapProv data);


    default <P, G extends P> void generate(Consumer<G> consumer, P provider) {
        try {
            @SuppressWarnings("unchecked")
            var gen = (G) provider;
            consumer.accept(gen);
        } catch (ClassCastException e) {
            Unity.LOGGER.error("Failed to generate some data!", e);
        }
    }

}