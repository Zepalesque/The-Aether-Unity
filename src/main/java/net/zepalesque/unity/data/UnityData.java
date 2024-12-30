package net.zepalesque.unity.data;

import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.util.InclusiveRange;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.data.gen.UnityBlockStateData;
import net.zepalesque.unity.data.gen.UnityItemModelData;
import net.zepalesque.unity.data.gen.UnityLanguageData;
import net.zepalesque.unity.data.gen.UnityRecipeData;
import net.zepalesque.unity.data.gen.UnityRegistrySets;
import net.zepalesque.unity.data.gen.tags.UnityBlockTagsData;
import net.zepalesque.unity.data.gen.tags.UnityItemTagsData;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class UnityData {
    public static void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        PackOutput packOutput = generator.getPackOutput();

        // Client Data
        generator.addProvider(event.includeClient(), new UnityBlockStateData(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new UnityItemModelData(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new UnityLanguageData(packOutput));


        // Server Data
        DatapackBuiltinEntriesProvider registrySets = new UnityRegistrySets(packOutput, lookupProvider, Unity.MODID);
        // Use for structure and damage type data, plus any custom ones that need to access the condition registry
        CompletableFuture<HolderLookup.Provider> registryProvider = registrySets.getRegistryProvider();
        generator.addProvider(event.includeServer(), registrySets);
        generator.addProvider(event.includeServer(), new UnityRecipeData(packOutput, registryProvider));

        // Tags
        UnityBlockTagsData blockTags = new UnityBlockTagsData(packOutput, lookupProvider, fileHelper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new UnityItemTagsData(packOutput, lookupProvider, blockTags.contentsGetter(), fileHelper));

        // pack.mcmeta
        generator.addProvider(true, new PackMetadataGenerator(packOutput).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.translatable("pack.aether_unity.mod.description"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.SERVER_DATA),
                Optional.of(new InclusiveRange<>(0, Integer.MAX_VALUE)))));
    }
}