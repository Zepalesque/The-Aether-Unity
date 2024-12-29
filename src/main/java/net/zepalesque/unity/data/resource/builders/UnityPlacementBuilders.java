package net.zepalesque.unity.data.resource.builders;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.data.resource.builders.base.BasePlacementBuilders;

public class UnityPlacementBuilders extends BasePlacementBuilders {
    protected static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Unity.loc(name));
    }

    protected static ResourceKey<PlacedFeature> copyKey(ResourceKey<ConfiguredFeature<?, ?>> configFeat) {
        return createKey(configFeat.location().getPath());
    }
}
