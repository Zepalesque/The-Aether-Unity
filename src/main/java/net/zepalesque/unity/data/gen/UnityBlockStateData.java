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

    }
}
