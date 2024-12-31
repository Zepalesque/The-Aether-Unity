package net.zepalesque.unity.block;

import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.natural.AetherShortGrassBlock;
import net.zepalesque.unity.block.natural.DoubleDropsCarpet;
import net.zepalesque.unity.block.natural.DoubleDropsGrowthBlock;
import net.zepalesque.unity.block.natural.leaves.LeafPileBlock;
import net.zepalesque.unity.block.state.UnityBlockBuilders;
import net.zepalesque.unity.data.resource.registries.UnityFeatureConfig;
import net.zepalesque.zenith.mixin.mixins.common.accessor.FireAccessor;

public class UnityBlocks extends UnityBlockBuilders {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Unity.MODID);

    public static DeferredBlock<AetherShortGrassBlock> SHORT_AETHER_GRASS = register("short_aether_grass",
            () -> new AetherShortGrassBlock(
                    Properties.ofFullCopy(Blocks.SHORT_GRASS)
                            .offsetType(OffsetType.XZ)
                            .hasPostProcess((state, level, pos) -> true)
            ));

    public static DeferredBlock<LeafPileBlock> SKYROOT_LEAF_PILE = register("skyroot_leaf_pile",
            () -> new LeafPileBlock(AetherBlocks.SKYROOT_LEAVES));

    public static DeferredBlock<LeafPileBlock> GOLDEN_OAK_LEAF_PILE = register("golden_oak_leaf_pile",
            () -> new LeafPileBlock(AetherBlocks.GOLDEN_OAK_LEAVES));

    public static DeferredBlock<CampfireBlock> AMBROSIUM_CAMPFIRE = register("ambrosium_campfire",
            () -> new CampfireBlock(false, 1, Properties.ofFullCopy(Blocks.CAMPFIRE)));

    public static DeferredBlock<DoubleDropsGrowthBlock> FLUTEMOSS_BLOCK = register("flutemoss_block",
            () -> new DoubleDropsGrowthBlock(Properties.ofFullCopy(Blocks.MOSS_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN), UnityFeatureConfig.FLUTEMOSS_BONEMEAL));
    public static DeferredBlock<DoubleDropsCarpet> FLUTEMOSS_CARPET = register("flutemoss_carpet",
            () -> new DoubleDropsCarpet(Properties.ofFullCopy(Blocks.MOSS_CARPET).mapColor(MapColor.COLOR_LIGHT_GREEN)));

    public static void registerFlammability() {
        FireAccessor accessor = (FireAccessor) Blocks.FIRE;
    }

    public static void registerToolConversions() {
    }
}
