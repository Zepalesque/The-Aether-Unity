package net.zepalesque.unity.data.prov;

import com.aetherteam.aether.data.providers.AetherRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public abstract class UnityRecipeProvider extends AetherRecipeProvider {

    public UnityRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String id) {
        super(output, lookupProvider, id);
    }


    protected static void layerBlock(RecipeOutput output, ItemLike carpet, ItemLike material, int count) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, carpet, count)
                .define('#', material).pattern("##")
                .unlockedBy(getHasName(material), has(material))
                .save(output);
    }

    protected static void campfire(RecipeOutput output, ItemLike result, ItemLike fuel) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .define('S', Tags.Items.RODS_WOODEN).define('F', fuel).define('L', ItemTags.LOGS)
                .pattern(" S ")
                .pattern("SFS")
                .pattern("LLL").unlockedBy(getHasName(fuel), has(fuel))
                .save(output);

    }


    @Override
    public ResourceLocation name(String name) {
        return super.name(name);
    }


}
