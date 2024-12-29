package net.zepalesque.unity.data.prov;

import com.aetherteam.aether.data.providers.AetherRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public abstract class UnityRecipeProvider extends AetherRecipeProvider {

    public UnityRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String id) {
        super(output, lookupProvider, id);
    }


    protected static void leafPile(RecipeOutput recipeOutput, ItemLike carpet, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, carpet, 6)
                .define('#', material).pattern("##")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }


    @Override
    public ResourceLocation name(String name) {
        return super.name(name);
    }


}
