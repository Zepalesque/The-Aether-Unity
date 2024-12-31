package net.zepalesque.unity.data.prov;

import com.aetherteam.aether.data.providers.AetherRecipeProvider;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.zepalesque.zenith.api.blockset.BlockSet;
import net.zepalesque.zenith.api.blockset.BlockSetDatagen;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class UnityRecipeProvider<P extends UnityRecipeProvider<P>> extends AetherRecipeProvider implements BlockSetDatagen<P> {

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

    public static void woodFromLogs(RecipeOutput recipeOutput, ItemLike wood, ItemLike log) {
        RecipeProvider.woodFromLogs(recipeOutput, wood, log);
    }

    public static void planksFromLog(RecipeOutput recipeOutput, ItemLike planks, TagKey<Item> logs, int resultCount) {
        RecipeProvider.planksFromLog(recipeOutput, planks, logs, resultCount);
    }

    public ShapedRecipeBuilder fence(Supplier<? extends Block> fence, Supplier<? extends Block> material) {
        return super.fence(fence, material);
    }

    public ShapedRecipeBuilder fenceGate(Supplier<? extends Block> fenceGate, Supplier<? extends Block> material) {
        return super.fenceGate(fenceGate, material);
    }

    public static RecipeBuilder doorBuilder(ItemLike door, Ingredient material) {
        return RecipeProvider.doorBuilder(door, material);
    }

    public static String getHasName(ItemLike itemLike) {
        return RecipeProvider.getHasName(itemLike);
    }

    public static String getItemName(ItemLike itemLike) {
        return RecipeProvider.getItemName(itemLike);
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> has(ItemLike itemLike) {
        return RecipeProvider.has(itemLike);
    }

    public static RecipeBuilder trapdoorBuilder(ItemLike trapdoor, Ingredient material) {
        return RecipeProvider.trapdoorBuilder(trapdoor, material);
    }

    public static void pressurePlate(RecipeOutput output, ItemLike pressurePlate, ItemLike material) {
        RecipeProvider.pressurePlate(output, pressurePlate, material);
    }

    public static RecipeBuilder buttonBuilder(ItemLike button, Ingredient material) {
        return RecipeProvider.buttonBuilder(button, material);
    }

    public static void woodenBoat(RecipeOutput output, ItemLike boat, ItemLike material) {
        RecipeProvider.woodenBoat(output, boat, material);
    }

    public static void chestBoat(RecipeOutput output, ItemLike boat, ItemLike material) {
        RecipeProvider.chestBoat(output, boat, material);
    }

    public static void slab(RecipeOutput output, RecipeCategory category, ItemLike slab, ItemLike material) {
        RecipeProvider.slab(output, category, slab, material);
    }

    public RecipeBuilder stairs(Supplier<? extends Block> stairs, Supplier<? extends Block> material) {
        return super.stairs(stairs, material);
    }

    public static void wall(RecipeOutput output, RecipeCategory category, ItemLike wall, ItemLike material) {
        RecipeProvider.wall(output, category, wall, material);
    }


    @Override
    public void stonecuttingRecipe(RecipeOutput output, RecipeCategory category, ItemLike item, ItemLike ingredient) {
        super.stonecuttingRecipe(output, category, item, ingredient);
    }

    @Override
    public void stonecuttingRecipe(RecipeOutput output, RecipeCategory category, ItemLike item, ItemLike ingredient, int count) {
        super.stonecuttingRecipe(output, category, item, ingredient, count);
    }

    public static String getConversionRecipeName(ItemLike result, ItemLike ingredient) {
        return RecipeProvider.getConversionRecipeName(result, ingredient);
    }

    public static String getConversionRecipeNameSwitched(ItemLike result, ItemLike ingredient) {
        return getItemName(ingredient) + "_to_" + getItemName(result);
    }

    public SimpleCookingRecipeBuilder smeltingOreRecipe(ItemLike result, ItemLike ingredient, float experience) {
        return super.smeltingOreRecipe(result, ingredient, experience);
    }

    protected SimpleCookingRecipeBuilder smeltingOreRecipe(ItemLike result, ItemLike ingredient, float experience, int cookTime) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.MISC, result, experience, cookTime)
                .unlockedBy(getHasName(ingredient), has(ingredient));
    }

    protected SimpleCookingRecipeBuilder blastingOreRecipe(ItemLike result, ItemLike ingredient, float experience, int cookTime) {
        return SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), RecipeCategory.MISC, result, experience, cookTime)
                .unlockedBy(getHasName(ingredient), has(ingredient));
    }


    @Override
    public ResourceLocation name(String name) {
        return super.name(name);
    }

    @Override
    public void doBlockSetGeneration() {
        throw new UnsupportedOperationException("Please use UnityRecipeProvider.generateDataForBlockSet(B set, RecipeOutput output) !!!!");
    }

    @Override
    public <B extends BlockSet> void generateDataForBlockSet(B set) {
        throw new UnsupportedOperationException("Please use UnityRecipeProvider.generateDataForBlockSet(B set, RecipeOutput output) !!!!");
    }

    public void doBlockSetGeneration(RecipeOutput output) {
        this.getSets().forEach(registry -> registry.forEach(set -> generateDataForBlockSet(set, output)));
    }

    public <B extends BlockSet> void generateDataForBlockSet(B set, RecipeOutput output) {
        set.recipeData(this, output);
    }
}
