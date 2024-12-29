package net.zepalesque.unity.data.resource.builders;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.data.resource.builders.base.BaseFeatureBuilders;

public class UnityFeatureBuilders extends BaseFeatureBuilders {
    protected static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Unity.loc(name));
    }
}
