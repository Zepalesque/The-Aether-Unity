package net.zepalesque.unity.data.resource.registries;

import com.aetherteam.aether.data.resources.registries.AetherConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.UnityTags;
import net.zepalesque.unity.data.resource.builders.UnityFeatureBuilders;

import java.util.List;

public class UnityFeatureConfig extends UnityFeatureBuilders {

    public static final ResourceKey<ConfiguredFeature<?, ?>> FLUTEMOSS_VEGETATION = createKey("flutemoss_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLUTEMOSS_BONEMEAL = createKey("flutemoss_bonemeal");

    // Overrides
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRASS_BONEMEAL = createKey("aether_grass_bonemeal");

    // rip bootstap :pensive:
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configs = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, FLUTEMOSS_VEGETATION, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder()
                                .add(drops(UnityBlocks.SHORT_AETHER_GRASS), 150)
                        // TODO: special blockstate provider with registerable modifiers for addons utilizing this one
                )));

        register(context, FLUTEMOSS_BONEMEAL, Feature.VEGETATION_PATCH,
                new VegetationPatchConfiguration(UnityTags.Blocks.AETHER_CARVER_REPLACEABLES,
                        prov(UnityBlocks.FLUTEMOSS_BLOCK),
                        Holder.direct(new PlacedFeature(configs.getOrThrow(FLUTEMOSS_VEGETATION),
                                List.of())),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.0F,
                        2,
                        0.8F,
                        UniformInt.of(1, 2),
                        0.75F));

        // Overrides
        register(context, AetherConfiguredFeatures.GRASS_PATCH_CONFIGURATION, Feature.RANDOM_PATCH, patch(48, 7, 3, prov(UnityBlocks.SHORT_AETHER_GRASS)/*, NOT_ON_COARSE_DIRT*/));
        register(context, AetherConfiguredFeatures.TALL_GRASS_PATCH_CONFIGURATION, Feature.NO_OP, new NoneFeatureConfiguration());

        register(context, GRASS_BONEMEAL, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(prov(UnityBlocks.SHORT_AETHER_GRASS)));
    }
}
