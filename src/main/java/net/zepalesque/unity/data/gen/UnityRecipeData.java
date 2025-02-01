package net.zepalesque.unity.data.gen;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.UnityTags;
import net.zepalesque.unity.data.prov.UnityRecipeProvider;
import net.zepalesque.unity.item.UnityItems;
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
        stonecutAndCraftStoneSet(output, RecipeCategory.BUILDING_BLOCKS,
                UnityBlocks.AETHER_MUD_BRICKS,
                UnityBlocks.AETHER_MUD_BRICK_WALL,
                UnityBlocks.AETHER_MUD_BRICK_STAIRS,
                UnityBlocks.AETHER_MUD_BRICK_SLAB
        );
        stonecutStoneSet(output, RecipeCategory.BUILDING_BLOCKS,
                UnityBlocks.PACKED_AETHER_MUD,
                UnityBlocks.AETHER_MUD_BRICK_WALL,
                UnityBlocks.AETHER_MUD_BRICK_STAIRS,
                UnityBlocks.AETHER_MUD_BRICK_SLAB
        );

        twoByTwoPacker(output, RecipeCategory.BUILDING_BLOCKS, UnityBlocks.VALKYRIE_CLAY.get(), UnityItems.VALKYRIE_CLAY_BALL.get());
        twoByTwoPacker(output, RecipeCategory.BUILDING_BLOCKS, UnityBlocks.VALKYRIE_BRICKS.get(), UnityItems.VALKYRIE_BRICK.get());
        smeltingOreRecipe(UnityItems.VALKYRIE_BRICK.get(), UnityItems.VALKYRIE_CLAY_BALL.get(), 0.3F).save(output);

        stonecutAndCraftStoneSet(output, RecipeCategory.BUILDING_BLOCKS,
                UnityBlocks.VALKYRIE_TILES,
                UnityBlocks.VALKYRIE_TILE_WALL,
                UnityBlocks.VALKYRIE_TILE_STAIRS,
                UnityBlocks.VALKYRIE_TILE_SLAB
        );

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, UnityBlocks.COARSE_AETHER_DIRT, 4)
                .define('D', AetherBlocks.AETHER_DIRT)
                .define('G', UnityTags.Items.AETHER_GRAVEL)
                .pattern("DG")
                .pattern("GD")
                .unlockedBy(getHasName(AetherBlocks.AETHER_DIRT.get()), has(AetherBlocks.AETHER_DIRT.get()))
                .save(output);


        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AetherBlocks.PILLAR.get(), 2)
                .define('V', UnityBlocks.VALKYRIE_BRICKS.get())
                .pattern("V")
                .pattern("V")
                .unlockedBy(getHasName(UnityBlocks.VALKYRIE_BRICKS.get()), has(UnityBlocks.VALKYRIE_BRICKS.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.FLOWER_POT)
                .define('V', UnityItems.VALKYRIE_BRICK.get())
                .pattern("V V")
                .pattern(" V ")
                .unlockedBy(getHasName(UnityItems.VALKYRIE_BRICK.get()), has(UnityItems.VALKYRIE_BRICK.get()))
                .save(output, name("flower_pot_from_valkyrie_bricks"));

        brick(output, RecipeCategory.BUILDING_BLOCKS, UnityBlocks.VALKYRIE_TILES, UnityBlocks.VALKYRIE_BRICKS);

        brick(output, RecipeCategory.BUILDING_BLOCKS, AetherBlocks.PILLAR_TOP, AetherBlocks.PILLAR);

    }


}
