package net.zepalesque.unity.data.gen;

import com.aetherteam.aether.block.AetherBlocks;
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

        leafPile(output, UnityBlocks.GOLDEN_OAK_LEAF_PILE.get(), AetherBlocks.GOLDEN_OAK_LEAVES.get());
        leafPile(output, UnityBlocks.SKYROOT_LEAF_PILE.get(), AetherBlocks.SKYROOT_LEAVES.get());

    }
}
