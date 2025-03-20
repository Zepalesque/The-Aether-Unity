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

@SuppressWarnings({"unused", "UnusedReturnValue"})
public abstract class UnityItemModelProvider extends AetherItemModelProvider implements TextureExtensions {

    public UnityItemModelProvider(PackOutput output, String id, ExistingFileHelper helper) {
        super(output, id, helper);
    }

    public void itemBlockWithParent(Block block, Function<Block, ResourceLocation> existingParent) {
        this.withExistingParent(this.nameID(block), existingParent.apply(block));
    }

    public ItemModelBuilder itemBlockFlatCustomTexture(Block block, ResourceLocation path) {
        return withExistingParent(nameID(block), mcLoc("item/generated"))
            .texture("layer0", path);
    }

    public ItemModelBuilder itemBlockFlatOther(Block block, Block other, String location) {
        return withExistingParent(nameID(block), mcLoc("item/generated"))
            .texture("layer0", texture(other, location));
    }

    public ItemModelBuilder itemGlow(Item item, String location) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
        return withExistingParent(id.getPath(), mcLoc("item/generated"))
            .texture("layer0", id.withPath("item/" + location + id.getPath()))
            .texture("layer1", id.withPath("item/" + location + id.getPath() + "_glow"))
            .customLoader((builder,fileHelper) -> ItemLayerModelBuilder.begin(
                builder, fileHelper).emissive(15, 15, 1)
            ).end();
    }
    public ItemModelBuilder handheldGlow(Item item, String location) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
        return withExistingParent(id.getPath(), mcLoc("item/handheld"))
            .texture("layer0", id.withPath("item/" + location + id.getPath()))
            .texture("layer1", id.withPath("item/" + location + id.getPath() + "_glow"))
            .customLoader((builder,fileHelper) -> ItemLayerModelBuilder.begin(
                builder, fileHelper).emissive(15, 15, 1)
            ).end();
    }

    public void dartShooterGlow(Item item, String location) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
        this.withExistingParent(id.getPath(), this.mcLoc("item/handheld"))
            .texture("layer0", this.modLoc("item/" + location + id.getPath()))
            .texture("layer1", modLoc("item/" + location + id.getPath() + "_glow"))
            .customLoader((builder,fileHelper) -> ItemLayerModelBuilder.begin(
                builder, fileHelper).emissive(15, 15, 1)
            ).end()
            
            .transforms() // epico
            
            .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
            .rotation(0.0F, -90.0F, 45.0F)
            .translation(0.0F, 1.5F, -1.0F)
            .scale(0.85F, 0.85F, 0.85F)
            .end()
            
            .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
            .rotation(0.0F, 90.0F, -45.0F)
            .translation(0.0F, 1.5F, -1.0F)
            .scale(0.85F, 0.85F, 0.85F)
            .end()
            
            .end();
    }

    public ItemModelBuilder itemBlockFlatGlow(Block block, String location) {
        return withExistingParent(nameID(block), mcLoc("item/generated"))
            .texture("layer0", texture(block, location))
            .texture("layer1", texture(block, location, "_glow"))
            .customLoader((builder,fileHelper) -> ItemLayerModelBuilder.begin(
                builder, fileHelper).emissive(15, 15, 1)
            ).end();
    }
    public ItemModelBuilder itemBlockFlatTintOverlay(Block block, String location) {
        return withExistingParent(nameID(block), mcLoc("item/generated"))
            .texture("layer1", texture(block, location))
            .texture("layer0", texture(block, location, "_overlay"));
    }

    public ItemModelBuilder itemBlockFlatGlow(Block block, String location, String suffix) {
        return withExistingParent(nameID(block), mcLoc("item/generated"))
            .texture("layer0", texture(block, location) + suffix)
            .texture("layer1", texture(block, location, suffix + "_glow"))
            .customLoader((builder,fileHelper) -> ItemLayerModelBuilder.begin(
                builder, fileHelper).emissive(15, 15, 1)
            ).end();
    }
    public ItemModelBuilder itemBlockFlatGlowOther(Block block, Block other, String location) {
        return withExistingParent(nameID(block), mcLoc("item/generated"))
            .texture("layer0", texture(other, location))
            .texture("layer1", texture(other, location, "_glow"))
            .customLoader((builder,fileHelper) -> ItemLayerModelBuilder.begin(
                builder, fileHelper).emissive(15, 15, 1)).end();
    }
    public ItemModelBuilder itemBlockFlatTintGlowOverlay(Block block, String location) {
        return withExistingParent(nameID(block), mcLoc("item/generated"))
            .texture("layer1", texture(block, location))
            .texture("layer2", texture(block, location, "_glow"))
            .texture("layer0", texture(block, location, "_overlay"))
            .customLoader((builder,fileHelper) -> ItemLayerModelBuilder.begin(
                builder, fileHelper).emissive(15, 15, 2)
            ).end();
    }
    public ItemModelBuilder itemBlockFlatTintGlow(Block block, String location) {
        return withExistingParent(nameID(block), mcLoc("item/generated"))
            .texture("layer1", texture(block, location))
            .texture("layer0", texture(block, location, "_glow"))
            .customLoader((builder,fileHelper) -> ItemLayerModelBuilder.begin(
                builder, fileHelper).emissive(15, 15, 0)
            ).end();
    }

    public void itemBlockFlatPrefix(Block block, String location, String prefix) {
        this.withExistingParent(this.nameID(block), this.mcLoc("item/generated"))
            .texture("layer0", this.texture(prefix + this.name(block), location));
    }

    public void leafPile(Block block) {
        this.itemBlockWithParent(block, b -> modLoc(BLOCK_FOLDER + "/" + this.name(b) + "_size_1"));
    }
}
