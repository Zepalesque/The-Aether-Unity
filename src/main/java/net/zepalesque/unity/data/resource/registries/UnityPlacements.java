package net.zepalesque.unity.data.resource.registries;

import com.aetherteam.aether.data.resources.registries.AetherPlacedFeatures;
import com.aetherteam.aether.world.placementmodifier.DungeonBlacklistFilter;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.zepalesque.unity.data.resource.builders.UnityPlacementBuilders;

public class UnityPlacements extends UnityPlacementBuilders {

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configs = context.lookup(Registries.CONFIGURED_FEATURE);
        DungeonBlacklistFilter blacklist = new DungeonBlacklistFilter();
        NoiseThresholdCountPlacement threshold = NoiseThresholdCountPlacement.of(-0.8D, 5, 10);

        // Overrides
        register(context, AetherPlacedFeatures.AETHER_GRASS_BONEMEAL, configs.getOrThrow(UnityFeatureConfig.GRASS_BONEMEAL), PlacementUtils.isEmpty());

    }

}
