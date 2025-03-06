package net.zepalesque.unity.data.resource.builders.base;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

public class BasePlacementBuilders {

    protected static void register(BootstrapContext<PlacedFeature> context,
                                   ResourceKey<PlacedFeature> key,
                                   Holder<ConfiguredFeature<?, ?>> configuration,
                                   List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
    
    protected static void register(BootstrapContext<PlacedFeature> context,
                                   ResourceKey<PlacedFeature> key,
                                   Holder<ConfiguredFeature<?, ?>> configuration,
                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }

    protected static String name(DeferredHolder<?, ?> reg) {
        return reg.getId().getPath();
    }
}
