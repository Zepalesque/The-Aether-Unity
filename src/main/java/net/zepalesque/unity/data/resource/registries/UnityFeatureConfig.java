package net.zepalesque.unity.data.resource.registries;

import com.aetherteam.aether.data.resources.registries.AetherConfiguredFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.resource.builders.UnityFeatureBuilders;

public class UnityFeatureConfig extends UnityFeatureBuilders {

    // Overrides
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRASS_BONEMEAL = createKey("aether_grass_bonemeal");

    // rip bootstap :pensive:
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configs = context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<DensityFunction> functions = context.lookup(Registries.DENSITY_FUNCTION);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);

        // Overrides
        register(context, AetherConfiguredFeatures.GRASS_PATCH_CONFIGURATION, Feature.RANDOM_PATCH, patch(48, 7, 3, prov(UnityBlocks.SHORT_AETHER_GRASS)/*, NOT_ON_COARSE_DIRT*/));
        register(context, AetherConfiguredFeatures.TALL_GRASS_PATCH_CONFIGURATION, Feature.NO_OP, new NoneFeatureConfiguration());

        register(context, GRASS_BONEMEAL, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(prov(UnityBlocks.SHORT_AETHER_GRASS)));
    }
}
