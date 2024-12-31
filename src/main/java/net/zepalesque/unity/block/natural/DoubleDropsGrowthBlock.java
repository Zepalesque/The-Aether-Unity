package net.zepalesque.unity.block.natural;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.zepalesque.zenith.core.block.type.SpreadingPatchBlock;

public class DoubleDropsGrowthBlock extends SpreadingPatchBlock {
    public DoubleDropsGrowthBlock(Properties properties, ResourceKey<ConfiguredFeature<?, ?>> featureKey) {
        super(properties, featureKey);
        this.registerDefaultState(this.defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AetherBlockStateProperties.DOUBLE_DROPS);
    }
}
