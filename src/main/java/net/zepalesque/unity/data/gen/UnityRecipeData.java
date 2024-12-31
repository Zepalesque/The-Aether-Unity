package net.zepalesque.unity.data.gen;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.UnityTags;
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, UnityBlocks.PACKED_AETHER_MUD, 1)
                .requires(UnityBlocks.AETHER_MUD).requires(UnityTags.Items.PACKED_AETHER_MUD_CRAFTING)
                .unlockedBy(getHasName(UnityBlocks.AETHER_MUD), has(UnityBlocks.AETHER_MUD))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AetherBlocks.MOSSY_HOLYSTONE, 1)
                .requires(UnityBlocks.FLUTEMOSS_BLOCK).requires(AetherBlocks.HOLYSTONE)
                .unlockedBy(getHasName(UnityBlocks.FLUTEMOSS_BLOCK), has(UnityBlocks.FLUTEMOSS_BLOCK))
                .save(output, "mossy_holystone_from_flutemoss");
        brick(output, RecipeCategory.BUILDING_BLOCKS, UnityBlocks.AETHER_MUD_BRICKS, UnityBlocks.PACKED_AETHER_MUD);
        stoneSet(output, RecipeCategory.BUILDING_BLOCKS,
                UnityBlocks.AETHER_MUD_BRICKS,
                UnityBlocks.AETHER_MUD_BRICK_WALL,
                UnityBlocks.AETHER_MUD_BRICK_STAIRS,
                UnityBlocks.AETHER_MUD_BRICK_SLAB
        );

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, UnityBlocks.COARSE_AETHER_DIRT, 4)
                .define('D', AetherBlocks.AETHER_DIRT)
                .define('G', UnityTags.Items.AETHER_GRAVEL)
                .pattern("DG")
                .pattern("GD")
                .unlockedBy(getHasName(AetherBlocks.AETHER_DIRT.get()), has(AetherBlocks.AETHER_DIRT.get()))
                .save(output);
    }
}
