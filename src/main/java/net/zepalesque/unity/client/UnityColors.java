package net.zepalesque.unity.client;

import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.block.natural.AetherShortGrassBlock;
import net.zepalesque.unity.block.state.UnityStates;
import net.zepalesque.unity.data.UnityTags;
import net.zepalesque.unity.world.biome.tint.UnityBiomeTints;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class UnityColors {

    public static final int AETHER_GRASS_COLOR = 0xFFADF9C4;

    public static final ColorResolver GRASS_COLORS = (biome, x, z) -> UnityBiomeTints.AETHER_GRASS.get().getColor(biome);
    
    
    public static final BlockColor OVERLAY_BASE = (state, level, pos, index) -> getColor(state, level, pos, index, i -> i == 1, false);
    public static final BlockColor FULL_INHERITING = (state, level, pos, index) -> getColor(state, level, pos, index, i -> i == 0, true);
    public static final BlockColor OVERLAY_INHERITING = (state, level, pos, index) -> getColor(state, level, pos, index, i -> i == 1, true);
    
    public static final ItemColor ITEM_OVERLAY_AETHER = (stack, tintIndex) -> tintIndex == 1 ? AETHER_GRASS_COLOR : 0xFFFFFFFF;
    public static final ItemColor ITEM_FULL_AETHER = (stack, tintIndex) -> tintIndex == 0 ? AETHER_GRASS_COLOR : 0xFFFFFFFF;
    
    public static void blockColors(RegisterColorHandlersEvent.Block event) {
        Unity.LOGGER.debug("Beginning block color registration for the Aether: Unity");

        event.register(OVERLAY_BASE, AetherBlocks.AETHER_GRASS_BLOCK.get()
        );
        event.register(FULL_INHERITING,
                UnityBlocks.SHORT_AETHER_GRASS.get(),
                UnityBlocks.SKYFERN.get()
        );
        event.register(OVERLAY_INHERITING,
                AetherBlocks.WHITE_FLOWER.get(),
                AetherBlocks.POTTED_WHITE_FLOWER.get(),
                AetherBlocks.PURPLE_FLOWER.get(),
                AetherBlocks.POTTED_PURPLE_FLOWER.get()
        );
    }

    public static void itemColors(RegisterColorHandlersEvent.Item event) {
        Unity.LOGGER.debug("Beginning item color registration for the Aether: Unity");
        event.register(ITEM_OVERLAY_AETHER,
                AetherBlocks.AETHER_GRASS_BLOCK.get(),
                AetherBlocks.WHITE_FLOWER.get(),
                AetherBlocks.PURPLE_FLOWER.get()
        );
        event.register(ITEM_FULL_AETHER,
                UnityBlocks.SHORT_AETHER_GRASS.get(),
                UnityBlocks.SKYFERN.get()
        );
    }

    public static void resolvers(RegisterColorHandlersEvent.ColorResolvers event) {
        event.register(GRASS_COLORS);
    }

    private static int getAverageColor(BlockAndTintGetter level, BlockPos blockPos, ColorResolver colorResolver) {
        if (level != null && blockPos != null) try {
            return level.getBlockTint(blockPos, colorResolver);
        } catch (Exception e) {
            Unity.LOGGER.error("Failed to get Aether Grass color, this is not intended! Ignoring exception and using default color", e);
        }
        return AETHER_GRASS_COLOR;
    }

    public static int getColor(BlockState state, @Nullable BlockAndTintGetter level, @Nullable BlockPos pos, int index, Predicate<Integer> indexGoal, boolean useBelowProperties) {
        if (indexGoal.test(index)) {
            if (level != null && pos != null) {

                for (AetherShortGrassBlock.TintOverride override : AetherShortGrassBlock.COLOR_OVERRIDES) {
                    @Nullable Integer i = override.tint(state, level, pos, index, indexGoal, useBelowProperties);
                    if (i != null) return i;
                }

                return getAverageColor(level, pos, GRASS_COLORS);
            }
            return AETHER_GRASS_COLOR;
        }
        return 0xFFFFFFFF;
    }


    /**
     * See {@link AetherShortGrassBlock#COLOR_OVERRIDES} and {@link UnityColors#getColor}
     */
    public static Integer unityColors(BlockState state, BlockAndTintGetter level, BlockPos pos, int index, Predicate<Integer> indexGoal, boolean useBelowProperties) {
        if (state.hasProperty(UnityStates.ENCHANTED) && state.getValue(UnityStates.ENCHANTED))
            return 0xFFFFFF;
        else if (level.getBlockState(pos.below()).is(UnityTags.Blocks.SHORT_AETHER_GRASS_DEFAULT_COLORING))
            return AETHER_GRASS_COLOR;
        return null;
    }
}
