package net.zepalesque.unity.data.resource.builders.base;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.data.resources.AetherFeatureStates;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
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
import net.zepalesque.zenith.api.block.predicate.NoisePredicate;
import net.zepalesque.zenith.api.world.feature.gen.BlockWithPredicateFeature;
import net.zepalesque.zenith.api.world.feature.gen.RuleBasedLakeFeature;
import net.zepalesque.zenith.core.registry.ZenithFeatures;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

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

     public static final Lazy<BlockPredicate> NOT_ON_COARSE_DIRT = Lazy.of(() -> BlockPredicate.not(BlockPredicate.matchesBlocks(new Vec3i(0, -1, 0), UnityBlocks.COARSE_AETHER_DIRT.get())));

    public static RandomPatchConfiguration patch(int tries, int xz, int y, BlockStateProvider state) {
        return new RandomPatchConfiguration(tries, xz, y, PlacementUtils.onlyWhenEmpty(
                Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(state)));
    }

    public static RandomPatchConfiguration patch(int tries, int xz, int y, BlockStateProvider state, BlockPredicate predicate) {
        return new RandomPatchConfiguration(tries, xz, y, PlacementUtils.onlyWhenEmpty(
                ZenithFeatures.BLOCK_WITH_PREDICATE.get(), new BlockWithPredicateFeature.Config(state, predicate)));
    }

    public static RuleBasedLakeFeature.Config lakeWithGrassBlock(Supplier<? extends Block> grass, HolderGetter<NormalNoise.NoiseParameters> params, RuleBasedBlockStateProvider.Rule... others) {
        Block b = grass.get();
        double mudClayThreshold;
        return new RuleBasedLakeFeature.Config(
                BlockStateProvider.simple(Blocks.WATER), Optional.of(
                        new RuleBasedBlockStateProvider(BlockStateProvider.simple(AetherFeatureStates.AETHER_DIRT), Stream.concat(Stream.of(
                                new RuleBasedBlockStateProvider.Rule(
                                        BlockPredicate.allOf(
                                                BlockPredicate.matchesBlocks(b),
                                                BlockPredicate.not(BlockPredicate.solid(new Vec3i(0, 1, 0))),
                                                BlockPredicate.not(BlockPredicate.matchesBlocks(new Vec3i(0, 1, 0), Blocks.WATER))
                                        ), prov(grass)
                                ),
                                new RuleBasedBlockStateProvider.Rule(
                                        BlockPredicate.anyOf(
                                                BlockPredicate.matchesBlocks(new Vec3i(0, 1, 0), b),
                                                BlockPredicate.matchesBlocks(new Vec3i(0, 1, 0), AetherBlocks.AETHER_DIRT.get())
                                        ), prov(AetherBlocks.AETHER_DIRT)
                                ),

                                new RuleBasedBlockStateProvider.Rule(
                                        BlockPredicate.allOf(
                                                new NoisePredicate(params.getOrThrow(Noises.SWAMP), 2743L, 0.0, mudClayThreshold = 0.2),
                                                BlockPredicate.matchesBlocks(new Vec3i(0, 1, 0), Blocks.WATER)
                                                ),
                                        prov(UnityBlocks.AETHER_MUD)
                                ),

                                new RuleBasedBlockStateProvider.Rule(
                                        BlockPredicate.allOf(
                                                BlockPredicate.matchesBlocks(new Vec3i(0, 1, 0), Blocks.WATER),
                                                new NoisePredicate(params.getOrThrow(Noises.SWAMP), 2743L, mudClayThreshold, Double.MAX_VALUE)
                                        ),
                                        prov(UnityBlocks.VALKYRIE_CLAY)
                                ),
                                new RuleBasedBlockStateProvider.Rule(
                                        BlockPredicate.allOf(
                                                BlockPredicate.matchesBlocks(b),
                                                BlockPredicate.matchesTag(new Vec3i(0, 1, 0), BlockTags.AIR)
                                        ), prov(grass)
                                )
                        ), Stream.of(others)).toList())));
    }
}
