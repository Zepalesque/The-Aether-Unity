package net.zepalesque.unity.data;

import com.aetherteam.aether.data.generators.AetherRegistrySets;
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
import net.zepalesque.unity.data.gen.UnityLangOverridesData;
import net.zepalesque.unity.data.gen.UnityLanguageData;
import net.zepalesque.unity.data.gen.UnityLootData;
import net.zepalesque.unity.data.gen.UnityMapData;
import net.zepalesque.unity.data.gen.UnityRecipeData;
import net.zepalesque.unity.data.gen.UnityRegistrySets;
import net.zepalesque.unity.data.gen.tags.UnityBlockTagsData;
import net.zepalesque.unity.data.gen.tags.UnityItemTagsData;

import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class UnityData extends UnityDataBuilders {
    public static void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existing = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookups = event.getLookupProvider();
        PackOutput output = generator.getPackOutput();

        // Client Data
        generator.addProvider(event.includeClient(), new UnityBlockStateData(output, existing));
        generator.addProvider(event.includeClient(), new UnityItemModelData(output, existing));
        generator.addProvider(event.includeClient(), new UnityLanguageData(output));

        AetherRegistrySets patch = new AetherRegistrySets(output, lookups);
        lookups = patch.getRegistryProvider();

        // Server Data
        DatapackBuiltinEntriesProvider registrySets = new UnityRegistrySets(output, lookups);
        generator.addProvider(event.includeServer(), registrySets);
        lookups = registrySets.getRegistryProvider();
        generator.addProvider(event.includeServer(), new UnityRecipeData(output, lookups));
        generator.addProvider(event.includeServer(), UnityLootData.create(output, lookups));
        generator.addProvider(event.includeServer(), new UnityMapData(output, lookups));

        // Tags
        UnityBlockTagsData blockTags = new UnityBlockTagsData(output, lookups, existing);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(),
            new UnityItemTagsData(output, lookups, blockTags.contentsGetter(), existing)
        );

        // pack.mcmeta
        generator.addProvider(true, new PackMetadataGenerator(output)
            .add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.translatable("pack.aether_unity.mod.description"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.SERVER_DATA),
                Optional.of(new InclusiveRange<>(0, Integer.MAX_VALUE)))
            )
        );
        
        createPack(event, PackType.CLIENT_RESOURCES, output, "tintable_grass", Unity.MODID,
            (out, modid) -> new UnityBlockStateData.Grass(out, modid, existing),
            (out, modid) -> new UnityItemModelData.Grass(out, modid, existing)
        );
        createPack(event, PackType.CLIENT_RESOURCES, output, "lang_overrides", Unity.MODID, UnityLangOverridesData::new);
    }
}