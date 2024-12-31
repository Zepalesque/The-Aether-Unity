package net.zepalesque.unity.data.gen;

import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.prov.UnityBlockStateProvider;

public class UnityBlockStateData extends UnityBlockStateProvider {

    public UnityBlockStateData(PackOutput output, ExistingFileHelper helper) {
        super(output, Unity.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.tintableShortGrass(UnityBlocks.SHORT_AETHER_GRASS.get(), "natural/");
        this.leafPile(UnityBlocks.GOLDEN_OAK_LEAF_PILE.get(), AetherBlocks.GOLDEN_OAK_LEAVES.get(), "natural/");
        this.leafPile(UnityBlocks.SKYROOT_LEAF_PILE.get(), AetherBlocks.SKYROOT_LEAVES.get(), "natural/");
        this.campfire(UnityBlocks.AMBROSIUM_CAMPFIRE.get(), "utility/");
        this.mossSet(UnityBlocks.FLUTEMOSS_BLOCK.get(), UnityBlocks.FLUTEMOSS_CARPET.get(), "natural/");
        this.block(UnityBlocks.AETHER_MUD.get(), "natural/");
        this.block(UnityBlocks.PACKED_AETHER_MUD.get(), "natural/");
        this.block(UnityBlocks.AETHER_MUD_BRICKS.get(), "construction/");
        this.wallBlock(UnityBlocks.AETHER_MUD_BRICK_WALL.get(), UnityBlocks.AETHER_MUD_BRICKS.get(), "construction/");
        this.stairs(UnityBlocks.AETHER_MUD_BRICK_STAIRS.get(), UnityBlocks.AETHER_MUD_BRICKS.get(), "construction/");
        this.slab(UnityBlocks.AETHER_MUD_BRICK_SLAB.get(), UnityBlocks.AETHER_MUD_BRICKS.get(), "construction/");
        this.block(UnityBlocks.COARSE_AETHER_DIRT.get(), "natural/");
    }
}
