package net.zepalesque.unity.data.prov;

import com.aetherteam.aether.data.providers.AetherItemModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.loaders.ItemLayerModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public abstract class UnityItemModelProvider extends AetherItemModelProvider {

    public UnityItemModelProvider(PackOutput output, String id, ExistingFileHelper helper) {
        super(output, id, helper);
    }

    public void itemBlockWithParent(Block block, Function<Block, ResourceLocation> existingParent) {
        this.withExistingParent(this.blockName(block), existingParent.apply(block));
    }

    public ItemModelBuilder itemBlockFlatCustomTexture(Block block, String locationPlusName) {

        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", texture(locationPlusName));
    }

    public ItemModelBuilder itemBlockFlatOther(Block block, Block other, String location) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", texture(blockName(other), location));
    }


    public ItemModelBuilder itemGlow(Item item, String location) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
        return withExistingParent(id.getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + location + id.getPath())).texture("layer1", modLoc("item/" + location + id.getPath() + "_glow")).customLoader((itemModelBuilder,existingFileHelper) ->
                        ItemLayerModelBuilder.begin(itemModelBuilder, existingFileHelper).emissive(15, 15, 1)).end();
    }
    public ItemModelBuilder handheldGlow(Item item, String location) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
        return withExistingParent(id.getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + location + id.getPath())).texture("layer1", modLoc("item/" + location + id.getPath() + "_glow")).customLoader((itemModelBuilder,existingFileHelper) ->
                        ItemLayerModelBuilder.begin(itemModelBuilder, existingFileHelper).emissive(15, 15, 1)).end();
    }

    public void dartShooterGlow(Item item, String location) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
        this.withExistingParent(id.getPath(), this.mcLoc("item/handheld"))
                .texture("layer0", this.modLoc("item/" + location + id.getPath()))
                .texture("layer1", modLoc("item/" + location + id.getPath() + "_glow")).customLoader((itemModelBuilder,existingFileHelper) ->
                        ItemLayerModelBuilder.begin(itemModelBuilder, existingFileHelper).emissive(15, 15, 1)).end()
                .transforms()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).rotation(0.0F, -90.0F, 45.0F).translation(0.0F, 1.5F, -1.0F).scale(0.85F, 0.85F, 0.85F).end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).rotation(0.0F, 90.0F, -45.0F).translation(0.0F, 1.5F, -1.0F).scale(0.85F, 0.85F, 0.85F).end()
                .end();
    }

    public ItemModelBuilder itemBlockFlatGlow(Block block, String location) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", texture(blockName(block), location))
                .texture("layer1", texture(blockName(block) + "_glow", location)).customLoader((itemModelBuilder,existingFileHelper) ->
                        ItemLayerModelBuilder.begin(itemModelBuilder, existingFileHelper).emissive(15, 15, 1)).end();
    }
    public ItemModelBuilder itemBlockFlatTintOverlay(Block block, String location) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer1", texture(blockName(block), location))
                .texture("layer0", texture(blockName(block) + "_overlay", location));
    }

    public ItemModelBuilder itemBlockFlatGlow(Block block, String location, String suffix) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", texture(blockName(block), location) + suffix)
                .texture("layer1", texture(blockName(block) + suffix + "_glow", location)).customLoader((itemModelBuilder,existingFileHelper) ->
                        ItemLayerModelBuilder.begin(itemModelBuilder, existingFileHelper).emissive(15, 15, 1)).end();
    }
    public ItemModelBuilder itemBlockFlatGlowOther(Block block, Block other, String location) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", texture(blockName(other), location))
                .texture("layer1", texture(blockName(other) + "_glow", location)).customLoader((itemModelBuilder,existingFileHelper) ->
                        ItemLayerModelBuilder.begin(itemModelBuilder, existingFileHelper).emissive(15, 15, 1)).end();
    }
    public ItemModelBuilder itemBlockFlatTintGlowOverlay(Block block, String location) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer1", texture(blockName(block), location))
                .texture("layer2", texture(blockName(block) + "_glow", location)).customLoader((itemModelBuilder,existingFileHelper) ->
                        ItemLayerModelBuilder.begin(itemModelBuilder, existingFileHelper).emissive(15, 15, 2)).end()
                .texture("layer0", texture(blockName(block) + "_overlay", location));
    }
    public ItemModelBuilder itemBlockFlatTintGlow(Block block, String location) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer1", texture(blockName(block), location))
                .texture("layer0", texture(blockName(block) + "_glow", location)).customLoader((itemModelBuilder,existingFileHelper) ->
                        ItemLayerModelBuilder.begin(itemModelBuilder, existingFileHelper).emissive(15, 15, 0)).end();
    }

    public void itemBlockFlatPrefix(Block block, String location, String prefix) {
        this.withExistingParent(this.blockName(block), this.mcLoc("item/generated"))
                .texture("layer0", this.texture(prefix + this.blockName(block), location));
    }

    public void leafPile(Block block) {
        this.itemBlockWithParent(block, b -> modLoc(BLOCK_FOLDER + "/" + this.blockName(b) + "_size_1"));

    }
}
