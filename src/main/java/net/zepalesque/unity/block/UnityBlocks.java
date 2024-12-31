package net.zepalesque.unity.block;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.block.natural.AetherDoubleDropBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.MudBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.natural.AetherShortGrassBlock;
import net.zepalesque.unity.block.natural.DoubleDropsCarpet;
import net.zepalesque.unity.block.natural.DoubleDropsGrowthBlock;
import net.zepalesque.unity.block.natural.DoubleDropsMud;
import net.zepalesque.unity.block.natural.leaves.LeafPileBlock;
import net.zepalesque.unity.block.state.UnityBlockBuilders;
import net.zepalesque.unity.data.resource.registries.UnityFeatureConfig;
import net.zepalesque.unity.event.hook.ToolActionHooks;
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

    public static DeferredBlock<DoubleDropsMud> AETHER_MUD = register("aether_mud",
            () -> new DoubleDropsMud(Properties.ofFullCopy(Blocks.MUD).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)));

    public static DeferredBlock<AetherDoubleDropBlock> PACKED_AETHER_MUD = register("packed_aether_mud",
            () -> new AetherDoubleDropBlock(Properties.ofFullCopy(Blocks.PACKED_MUD).mapColor(MapColor.TERRACOTTA_CYAN)));

    public static DeferredBlock<Block> AETHER_MUD_BRICKS = register("aether_mud_bricks",
            () -> new Block(Properties.ofFullCopy(Blocks.PACKED_MUD).mapColor(MapColor.TERRACOTTA_CYAN)));
    public static final DeferredBlock<WallBlock> AETHER_MUD_BRICK_WALL = register("aether_mud_brick_wall", () -> new WallBlock(Block.Properties.ofFullCopy(AETHER_MUD_BRICKS.get()).forceSolidOn()));
    public static final DeferredBlock<StairBlock> AETHER_MUD_BRICK_STAIRS = register("aether_mud_brick_stairs",
            () -> new StairBlock(AETHER_MUD_BRICKS.get().defaultBlockState(), Block.Properties.ofFullCopy(AETHER_MUD_BRICKS.get())));
    public static final DeferredBlock<SlabBlock> AETHER_MUD_BRICK_SLAB = register("aether_mud_brick_slab",
            () -> new SlabBlock(Block.Properties.ofFullCopy(AETHER_MUD_BRICKS.get()).strength(0.5F, 6.0F)));

    public static DeferredBlock<AetherDoubleDropBlock> COARSE_AETHER_DIRT = register("coarse_aether_dirt",
            () -> new AetherDoubleDropBlock(Properties.ofFullCopy(AetherBlocks.AETHER_DIRT.get())));






    public static void registerFlammability() {
        FireAccessor accessor = (FireAccessor) Blocks.FIRE;
    }

    public static void registerToolConversions() {
        ToolActionHooks.FLATTENABLES.put(COARSE_AETHER_DIRT.get(), AetherBlocks.AETHER_DIRT_PATH.get());
        ToolActionHooks.TILLABLES.put(COARSE_AETHER_DIRT.get(), AetherBlocks.AETHER_DIRT.get());
    }
}
