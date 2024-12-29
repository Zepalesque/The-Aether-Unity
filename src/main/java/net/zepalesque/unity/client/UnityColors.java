package net.zepalesque.unity.client;

import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.block.natural.AetherShortGrassBlock;
import net.zepalesque.unity.block.state.UnityStates;
import net.zepalesque.unity.world.biome.tint.UnityBiomeTints;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Predicate;

public class UnityColors {

    public static final int AETHER_GRASS_COLOR = 0xADF9C4;

    public static ColorResolver AETHER_GRASS = (biome, x, z) -> UnityBiomeTints.AETHER_GRASS.get().getColor(biome);

    public static void blockColors(RegisterColorHandlersEvent.Block event) {
        Unity.LOGGER.debug("Beginning block color registration for the Aether: Unity");

        event.register((state, level, pos, index) -> getColor(state, level, pos, index, i -> i == 1, false), AetherBlocks.AETHER_GRASS_BLOCK.get());
        event.register((state, level, pos, index) -> getColor(state, level, pos, index, i -> i == 0, true), UnityBlocks.SHORT_AETHER_GRASS.get());
        event.register((state, level, pos, index) -> getColor(state, level, pos, index, i -> i == 1, true),
                AetherBlocks.WHITE_FLOWER.get(),
                AetherBlocks.POTTED_WHITE_FLOWER.get(),
                AetherBlocks.PURPLE_FLOWER.get(),
                AetherBlocks.POTTED_PURPLE_FLOWER.get()
        );
    }

    public static void itemColors(RegisterColorHandlersEvent.Item event) {
        Unity.LOGGER.debug("Beginning item color registration for the Aether: Unity");
        event.register((stack, tintIndex) -> tintIndex == 1 ? AETHER_GRASS_COLOR : 0xFFFFFF,
                AetherBlocks.AETHER_GRASS_BLOCK.get(),
                AetherBlocks.WHITE_FLOWER.get(),
                AetherBlocks.PURPLE_FLOWER.get()        );
        event.register((stack, tintIndex) -> tintIndex == 0 ? AETHER_GRASS_COLOR : 0xFFFFFF,
                UnityBlocks.SHORT_AETHER_GRASS.get(
        ));
    }

    public static void resolvers(RegisterColorHandlersEvent.ColorResolvers event) {
        event.register(AETHER_GRASS);
    }

    private static int getAverageColor(BlockAndTintGetter level, BlockPos blockPos, ColorResolver colorResolver) {
        if (level != null && blockPos != null) {
            try {
                return level.getBlockTint(blockPos, colorResolver);
            } catch (Exception e) {
                Unity.LOGGER.error("Failed to get Aether Grass color, this is not intended! Ignoring exception and using default color", e);
            }
        }
        return AETHER_GRASS_COLOR;
    }

    public static int getColor(BlockState state, @Nullable BlockAndTintGetter level, @Nullable BlockPos pos, int index, Predicate<Integer> indexGoal, boolean useBelowProperties) {
        if (indexGoal.test(index)) {
            if (level != null && pos != null) {

                for (AetherShortGrassBlock.TintOverride override : AetherShortGrassBlock.COLOR_OVERRIDES) {
                    Optional<Integer> optional = override.tint(state, level, pos, index, indexGoal, useBelowProperties);
                    if (optional.isPresent()) return optional.get();
                }

                return getAverageColor(level, pos, AETHER_GRASS);
            }
            return AETHER_GRASS_COLOR;
        }
        return 0xFFFFFF;
    }

    // Avoid constant Optional instantiation
    public static final Optional<Integer> NO_TINT = Optional.of(0xFFFFFF);

    /**
     * See {@link AetherShortGrassBlock#COLOR_OVERRIDES} and {@link UnityColors#getColor}
     */
    public static Optional<Integer> enchantedGrassOverrides(BlockState state, BlockAndTintGetter level, BlockPos pos, int index, Predicate<Integer> indexGoal, boolean useBelowProperties) {
        if (state.hasProperty(UnityStates.ENCHANTED) && state.getValue(UnityStates.ENCHANTED)) {
            return NO_TINT;
        }
        return Optional.empty();
    }

}
