package net.zepalesque.unity.data.gen;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.prov.UnityRecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class UnityRecipeData extends UnityRecipeProvider {

    public UnityRecipeData(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Unity.MODID);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {

        layerBlock(output, UnityBlocks.GOLDEN_OAK_LEAF_PILE, AetherBlocks.GOLDEN_OAK_LEAVES, 6);
        layerBlock(output, UnityBlocks.SKYROOT_LEAF_PILE, AetherBlocks.SKYROOT_LEAVES, 6);
        layerBlock(output, UnityBlocks.FLUTEMOSS_CARPET, UnityBlocks.FLUTEMOSS_BLOCK, 3);
        campfire(output, UnityBlocks.AMBROSIUM_CAMPFIRE, AetherItems.AMBROSIUM_SHARD);
    }
}
