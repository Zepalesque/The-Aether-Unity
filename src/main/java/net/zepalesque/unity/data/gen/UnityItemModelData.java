package net.zepalesque.unity.data.gen;

import net.minecraft.data.PackOutput;
import net.minecraft.util.Unit;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.prov.UnityItemModelProvider;

public class UnityItemModelData extends UnityItemModelProvider {

    public UnityItemModelData(PackOutput output, ExistingFileHelper helper) {
        super(output, Unity.MODID, helper);
    }

    @Override
    protected void registerModels() {
        itemBlockFlatCustomTexture(UnityBlocks.SHORT_AETHER_GRASS.get(), "natural/aether_medium_grass");
        this.leafPile(UnityBlocks.GOLDEN_OAK_LEAF_PILE.get());
        this.leafPile(UnityBlocks.SKYROOT_LEAF_PILE.get());
        this.item(UnityBlocks.AMBROSIUM_CAMPFIRE.asItem(), "misc/");

        this.itemBlock(UnityBlocks.FLUTEMOSS_BLOCK.get());
        this.itemBlock(UnityBlocks.FLUTEMOSS_CARPET.get());

        this.itemBlock(UnityBlocks.AETHER_MUD.get());
        this.itemBlock(UnityBlocks.PACKED_AETHER_MUD.get());
        this.itemBlock(UnityBlocks.AETHER_MUD_BRICKS.get());
        this.itemWallBlock(UnityBlocks.AETHER_MUD_BRICK_WALL.get(), UnityBlocks.AETHER_MUD_BRICKS.get(), "construction/");
        this.itemBlock(UnityBlocks.AETHER_MUD_BRICK_STAIRS.get());
        this.itemBlock(UnityBlocks.AETHER_MUD_BRICK_SLAB.get());
        this.itemBlock(UnityBlocks.COARSE_AETHER_DIRT.get());
    }
}
