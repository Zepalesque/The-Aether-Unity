package net.zepalesque.unity.data.resource.builders.base;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.data.resources.AetherFeatureStates;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.UnityTags;
import net.zepalesque.zenith.api.block.predicate.NoisePredicate;
import net.zepalesque.zenith.api.world.feature.gen.BlockWithPredicateFeature;
import net.zepalesque.zenith.api.world.feature.gen.RuleBasedLakeFeature;
import net.zepalesque.zenith.core.registry.ZenithFeatures;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class BaseFeatureBuilders {

    // TODO: Replace all instances with FeatureUtils#register (if that will work)
    protected static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    public static String name(DeferredHolder<?, ?> reg) {
        return reg.getId().getPath();
    }

    public static BlockStateProvider prov(BlockState state) {
        return BlockStateProvider.simple(drops(state));
    }

    public static BlockStateProvider prov(Supplier<? extends Block> block) {
        return prov(block.get().defaultBlockState());
    }

    public static BlockState drops(BlockState state) {
        return state.hasProperty(AetherBlockStateProperties.DOUBLE_DROPS) ? state.setValue(AetherBlockStateProperties.DOUBLE_DROPS, true) : state;
    }

    public static BlockState drops(Supplier<? extends Block> block) {
        return drops(block.get().defaultBlockState());
    }

    public static final Vec3i OFFSET_ABOVE = new Vec3i(0, 1, 0);
    public static final Vec3i OFFSET_BELOW = new Vec3i(0, -1, 0);

    public static final Lazy<BlockPredicate> CANNOT_SPAWN_AETHER_GRASS = Lazy.of(() -> BlockPredicate.not(BlockPredicate.matchesTag(OFFSET_BELOW, UnityTags.Blocks.CANNOT_SPAWN_AETHER_GRASS)));

    public static RandomPatchConfiguration patch(int tries, int xz, int y, BlockStateProvider state) {
        return new RandomPatchConfiguration(tries, xz, y, PlacementUtils.onlyWhenEmpty(
                Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(state)));
    }

    public static RandomPatchConfiguration patch(int tries, int xz, int y, BlockStateProvider state, BlockPredicate predicate) {
        return new RandomPatchConfiguration(tries, xz, y, PlacementUtils.onlyWhenEmpty(
                ZenithFeatures.BLOCK_WITH_PREDICATE.get(), new BlockWithPredicateFeature.Config(state, predicate)));
    }

    public static RuleBasedLakeFeature.Config lake(TagKey<Block> grass, Supplier<? extends Block> fluid, HolderGetter<NormalNoise.NoiseParameters> params, RuleBasedBlockStateProvider.Rule... others) {
        double threshold;
        return new RuleBasedLakeFeature.Config(
            prov(fluid), Optional.of(new RuleBasedBlockStateProvider(
                BlockStateProvider.simple(AetherFeatureStates.AETHER_DIRT), Stream.concat(Stream.of(
                    new RuleBasedBlockStateProvider.Rule(
                        BlockPredicate.allOf(
                            BlockPredicate.matchesTag(grass),
                            BlockPredicate.not(BlockPredicate.solid(OFFSET_ABOVE)),
                            BlockPredicate.not(BlockPredicate.matchesBlocks(OFFSET_ABOVE, fluid.get()))
                        ), BlockStateProvider.simple(Blocks.AIR)
                    ),
                    
                    new RuleBasedBlockStateProvider.Rule(
                        BlockPredicate.anyOf(
                            BlockPredicate.matchesTag(OFFSET_ABOVE, AetherTags.Blocks.AETHER_DIRT),
                            BlockPredicate.matchesBlocks(OFFSET_ABOVE, AetherBlocks.AETHER_DIRT.get())
                        ), prov(AetherBlocks.AETHER_DIRT)
                    ),

                    new RuleBasedBlockStateProvider.Rule(
                        BlockPredicate.allOf(
                            new NoisePredicate(params.getOrThrow(Noises.SWAMP), 2743L, -0.3, threshold = 0.1),
                            BlockPredicate.matchesBlocks(OFFSET_ABOVE, Blocks.WATER)
                        ),
                        prov(UnityBlocks.AETHER_MUD)
                    ),

                    new RuleBasedBlockStateProvider.Rule(
                        BlockPredicate.allOf(
                            // Use same seed, mud will surround clay
                            BlockPredicate.matchesBlocks(OFFSET_ABOVE, fluid.get()),
                            new NoisePredicate(params.getOrThrow(Noises.SWAMP), 2743L, threshold, Double.MAX_VALUE)
                        ),
                        prov(UnityBlocks.VALKYRIE_CLAY)
                    ),
                    
                    new RuleBasedBlockStateProvider.Rule(
                        BlockPredicate.allOf(
                            BlockPredicate.matchesTag(grass),
                            BlockPredicate.matchesTag(OFFSET_ABOVE, BlockTags.AIR)
                        ), BlockStateProvider.simple(Blocks.AIR)
                    )
                ), Stream.of(others)).toList())));
    }
}
