package net.zepalesque.unity.data.gen;

import net.minecraft.data.PackOutput;
import net.minecraft.util.Unit;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.prov.UnityItemModelProvider;

public class UnityItemModelData extends UnityItemModelProvider<UnityItemModelData> {

    public UnityItemModelData(PackOutput output, ExistingFileHelper helper) {
        super(output, Unity.MODID, helper);
    }

    @Override
    protected void registerModels() {
        this.doBlockSetGeneration();
        itemBlockFlatCustomTexture(UnityBlocks.SHORT_AETHER_GRASS.get(), "natural/aether_medium_grass");
        this.leafPile(UnityBlocks.GOLDEN_OAK_LEAF_PILE.get());
        this.leafPile(UnityBlocks.SKYROOT_LEAF_PILE.get());
        this.item(UnityBlocks.AMBROSIUM_CAMPFIRE.asItem(), "misc/");

        this.itemBlock(UnityBlocks.FLUTEMOSS_BLOCK.get());
        this.itemBlock(UnityBlocks.FLUTEMOSS_CARPET.get());
    }
}
