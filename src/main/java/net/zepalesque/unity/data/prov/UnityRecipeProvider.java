package net.zepalesque.unity.data.prov;

import com.aetherteam.aether.data.providers.AetherRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.zepalesque.zenith.api.recipe.builder.NoneRecipeBuilder;

import java.util.concurrent.CompletableFuture;

public abstract class UnityRecipeProvider extends AetherRecipeProvider {

    public UnityRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String id) {
        super(output, lookupProvider, id);
    }


    public static void layerBlock(RecipeOutput output, ItemLike carpet, ItemLike material, int count) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, carpet, count)
                .define('#', material).pattern("##")
                .unlockedBy(getHasName(material), has(material))
                .save(output);
    }

    public static void campfire(RecipeOutput output, ItemLike result, ItemLike fuel) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .define('S', Tags.Items.RODS_WOODEN).define('F', fuel).define('L', ItemTags.LOGS)
                .pattern(" S ")
                .pattern("SFS")
                .pattern("LLL").unlockedBy(getHasName(fuel), has(fuel))
                .save(output);

    }

    public static void brick(RecipeOutput recipeOutput, RecipeCategory category, ItemLike brick, ItemLike base) {
        ShapedRecipeBuilder.shaped(category, brick, 4)
                .define('#', base)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(base), has(base))
                .save(recipeOutput);
    }
    public void craftStoneSet(RecipeOutput output, RecipeCategory category, ItemLike base, ItemLike wall, ItemLike stairs, ItemLike slab) {
        wall(output, category, wall, base);
        stairBuilder(stairs, Ingredient.of(base))
                .unlockedBy(getHasName(base), has(base))
                .save(output);
        slab(output, category, slab, base);
    }

    public void stonecutStoneSet(RecipeOutput output, RecipeCategory category, ItemLike base, ItemLike wall, ItemLike stairs, ItemLike slab) {
        stonecut(output, category, wall, base);
        stonecut(output, category, stairs, base);
        stonecut(output, category, slab, base);
    }

    public void stonecutAndCraftStoneSet(RecipeOutput output, RecipeCategory category, ItemLike base, ItemLike wall, ItemLike stairs, ItemLike slab) {
        craftStoneSet(output, category, base, wall, stairs, slab);
        stonecutStoneSet(output, category, base, wall, stairs, slab);
    }

    protected void stonecut(RecipeOutput output, RecipeCategory category, ItemLike item, ItemLike ingredient) {
        stonecut(output, category, item, ingredient, 1);
    }

    protected void stonecut(RecipeOutput output, RecipeCategory category, ItemLike item, ItemLike ingredient, int count) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), category, item, count).unlockedBy(getHasName(ingredient), has(ingredient)).save(output, name(getConversionRecipeName(item, ingredient) + "_stonecutting"));
    }


    protected void none(RecipeOutput output, ResourceLocation id) {
        NoneRecipeBuilder.of().save(output, id);
    }


    @Override
    public ResourceLocation name(String name) {
        return super.name(name);
    }


}
