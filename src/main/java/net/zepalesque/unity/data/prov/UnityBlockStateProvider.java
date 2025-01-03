package net.zepalesque.unity.data.prov;

import com.aetherteam.aether.data.providers.AetherBlockStateProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.natural.AetherShortGrassBlock;
import net.zepalesque.unity.block.natural.leaves.LeafPileBlock;
import net.zepalesque.unity.block.state.UnityStates;

public abstract class UnityBlockStateProvider extends AetherBlockStateProvider {

    public UnityBlockStateProvider(PackOutput output, String id, ExistingFileHelper helper) {
        super(output, id, helper);
    }

    public void tintableShortGrass(AetherShortGrassBlock block, String location) {
        this.getVariantBuilder(block).forAllStates((state) -> {
            boolean enchanted = state.getValue(UnityStates.ENCHANTED);
            if (enchanted) {
                return ConfiguredModel.builder()
                        .modelFile(models()
                                .cross(state.getValue(UnityStates.GRASS_SIZE).getSerializedName() + "_enchanted_grass",
                                        modLoc("block/natural/enchanted_" + state.getValue(UnityStates.GRASS_SIZE).getSerializedName()  + "_grass"))
                                .renderType("cutout")).build();
            }

            return ConfiguredModel.builder()
                    .modelFile(models()
                            .singleTexture(state.getValue(UnityStates.GRASS_SIZE).getSerializedName() + "_aether_grass",
                                    mcLoc("block/tinted_cross"), "cross",
                                    modLoc("block/natural/aether_" + state.getValue(UnityStates.GRASS_SIZE).getSerializedName() + "_grass"))
                            .renderType("cutout")).build();
        });
    }



    public static ResourceLocation extendStatic(ResourceLocation location, String suffix) {
        return ResourceLocation.fromNamespaceAndPath(location.getNamespace(), location.getPath() + suffix);
    }

    public static String nameStatic(Block block) {
        ResourceLocation location = BuiltInRegistries.BLOCK.getKey(block);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown block: " + block.toString());
        }
    }

    // Cross blocks
    public void crossGlowOverlay(Block block, String location) {
        BlockModelBuilder cross = models().withExistingParent(this.name(block), Unity.loc(ModelProvider.BLOCK_FOLDER + "/template/cross/cross_glowing_overlay"))
                .texture("cross", this.texture(this.name(block), location))
                .texture("overlay", this.texture(this.name(block) + "_glow", location)).renderType("cutout");
        this.crossBlock(block, cross);
    }

    public void crossTintedGlow(Block block, String location) {
        BlockModelBuilder cross = models().withExistingParent(this.name(block), Unity.loc(ModelProvider.BLOCK_FOLDER + "/template/cross/cross_tinted_glow"))
                .texture("cross", this.texture(this.name(block), location))
                .texture("overlay", this.texture(this.name(block) + "_glow", location)).renderType("cutout");
        this.crossBlock(block, cross);
    }

    public void crossTintedDualGloverlay(Block block, String location, boolean useGlowForParticle) {
        BlockModelBuilder cross = models().withExistingParent(this.name(block), Unity.loc(ModelProvider.BLOCK_FOLDER + "/template/cross/cross_tinted_gloverlay_" + (useGlowForParticle ? "glow" : "overlay")))
                .texture("cross", this.texture(this.name(block), location))
                .texture("glow", this.texture(this.name(block) + "_glow", location)).renderType("cutout")
                .texture("overlay", this.texture(this.name(block) + "_overlay", location)).renderType("cutout");
        this.crossBlock(block, cross);

    }
    public void crossTintedOverlay(Block block, String location) {
        BlockModelBuilder cross = models().withExistingParent(this.name(block), Unity.loc(ModelProvider.BLOCK_FOLDER + "/template/cross/cross_tinted_overlay"))
                .texture("cross", this.texture(this.name(block), location))
                .texture("overlay", this.texture(this.name(block) + "_overlay", location)).renderType("cutout");
        this.crossBlock(block, cross);
    }

    public void crossEnchantableOverlay(Block block, String location) {
        BlockModelBuilder cross = models().withExistingParent(this.name(block), Unity.loc(ModelProvider.BLOCK_FOLDER + "/template/cross/cross_tinted_overlay"))
                .texture("cross", this.texture(this.name(block), location))
                .texture("overlay", this.texture(this.name(block) + "_overlay", location)).renderType("cutout");
        BlockModelBuilder ench = models().cross("enchanted_" + this.name(block), this.texture("enchanted_" + this.name(block), location)).renderType("cutout");
        this.getVariantBuilder(block).forAllStates((state) ->  ConfiguredModel.builder().modelFile(state.getValue(UnityStates.ENCHANTED) ? ench : cross).build());
    }

    public void potAlt(Block block, Block flower, String location) {
        this.potPrefix(block, flower, location, "potted_");
    }

    public void potPrefix(Block block, Block flower, String location, String prefix) {
        ModelFile pot = this.models().withExistingParent(this.name(block), mcLoc("block/flower_pot_cross"))
                .texture("plant", this.modLoc("block/" + location + prefix + this.name(flower))).renderType("cutout");
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(pot));
    }

    public void tintedPotDualGloverlay(Block block, Block flower, String location) {
        ModelFile pot = this.models().withExistingParent(this.name(block), Unity.loc("block/template/pot/flower_pot_tinted_dual_gloverlay"))
                .texture("plant", this.modLoc("block/" + location + this.name(flower)))
                .texture("glow", this.modLoc("block/" + location + this.name(flower) + "_glow"))
                .texture("overlay", this.modLoc("block/" + location + this.name(flower) + "_overlay")).renderType("cutout");
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(pot));
    }
    public void tintedPotGlowOverlay(Block block, Block flower, String location) {
        ModelFile pot = this.models().withExistingParent(this.name(block), Unity.loc("block/template/pot/flower_pot_tinted_glowing_overlay"))
                .texture("plant", this.modLoc("block/" + location + this.name(flower)))
                .texture("overlay", this.modLoc("block/" + location + this.name(flower) + "_glow")).renderType("cutout");
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(pot));
    }
    public void tintedPotGlowOverlayAlt(Block block, Block flower, String location) {
        ModelFile pot = this.models().withExistingParent(this.name(block), Unity.loc("block/template/pot/flower_pot_tinted_glowing_overlay"))
                .texture("plant", this.modLoc("block/" + location + "potted_" + this.name(flower)))
                .texture("overlay", this.modLoc("block/" + location + "potted_" + this.name(flower) + "_glow")).renderType("cutout");
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(pot));
    }
    public void tintedPotOverlay(Block block, Block flower, String location) {
        ModelFile pot = this.models().withExistingParent(this.name(block), Unity.loc("block/template/pot/flower_pot_tinted_overlay"))
                .texture("plant", this.modLoc("block/" + location + this.name(flower)))
                .texture("overlay", this.modLoc("block/" + location + this.name(flower) + "_overlay")).renderType("cutout");
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(pot));
    }
    public void tintedPot(Block block, Block flower, String location) {
        ModelFile pot = this.models().withExistingParent(this.name(block), Unity.loc("block/template/pot/flower_pot_tinted"))
                .texture("plant", this.modLoc("block/" + location + this.name(flower))).renderType("cutout");
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(pot));
    }
    public void tintedPotOverlayAlt(Block block, Block flower, String location) {
        ModelFile pot = this.models().withExistingParent(this.name(block), Unity.loc("block/template/pot/flower_pot_tinted_overlay"))
                .texture("plant", this.modLoc("block/" + location + "potted_" + this.name(flower)))
                .texture("overlay", this.modLoc("block/" + location + "potted_" + this.name(flower) + "_overlay")).renderType("cutout");
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(pot));
    }

    // Other

    public void leafPile(Block block, Block baseBlock, String location) {
        this.getVariantBuilder(block).forAllStates(
                state -> ConfiguredModel.builder().modelFile(state.getValue(
                        LeafPileBlock.LAYERS) == 16 ? this.models().cubeAll(this.name(block) + "_full", this.texture(baseBlock, location)).renderType("cutout") :
                        this.models().singleTexture(this.name(block) + "_size_" + state.getValue(LeafPileBlock.LAYERS),
                                this.modLoc("block/template/layer/layer_size" + state.getValue(LeafPileBlock.LAYERS)),
                                "block", this.texture(baseBlock, location)).renderType("cutout")).build());
    }

    public void campfire(Block block, String location) {
        this.getVariantBuilder(block).forAllStatesExcept(
                state -> {
                    BlockModelBuilder on = this.models().withExistingParent(this.name(block), Unity.loc("block/template/campfire"))
                                    .texture("log", this.modLoc("block/" + location + this.name(block) + "_log"))
                                    .texture("fire", this.modLoc("block/" + location + this.name(block) + "_fire"))
                                    .texture("lit_log", this.modLoc("block/" + location + this.name(block) + "_log_lit")).renderType("cutout");
                    BlockModelBuilder off = this.models().singleTexture(this.name(block) + "_off", Unity.loc("block/template/campfire_off"), "log", this.modLoc("block/" + location + this.name(block) + "_log"));

                    Direction d = state.getValue(CampfireBlock.FACING);
                    int rotDeg = d.get2DDataValue() * 90;
                    boolean lit = state.getValue(CampfireBlock.LIT);

                    return ConfiguredModel.builder().modelFile(lit ? on : off).rotationY(rotDeg).build();
                    }, CampfireBlock.SIGNAL_FIRE, CampfireBlock.WATERLOGGED);
    }

    public void carpet(Block block, Block baseBlock, String location) {
        this.simpleBlock(block, this.models().singleTexture(this.name(block), mcLoc("block/carpet"), "wool", this.texture(this.name(baseBlock), location)));
    }

    // Does both at the same time :D
    public void mossSet(Block moss, Block carpet, String location) {
        this.block(moss, location);
        this.carpet(carpet, moss, location);
    }

    public ResourceLocation texture(Block block, String location) {
        return ResourceLocation.fromNamespaceAndPath(BuiltInRegistries.BLOCK.getKey(block).getNamespace(), "block/" + location + name(block));
    }

}
