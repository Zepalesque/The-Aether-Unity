package net.zepalesque.unity.data.gen;

import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.prov.UnityItemModelProvider;
import net.zepalesque.unity.item.UnityItems;

public class UnityItemModelData extends UnityItemModelProvider {

    public UnityItemModelData(PackOutput output, ExistingFileHelper helper) {
        super(output, Unity.MODID, helper);
    }

    @Override
    protected void registerModels() {
        this.itemBlockFlatCustomTexture(UnityBlocks.SHORT_AETHER_GRASS.get(), Unity.loc(BLOCK_FOLDER + "/natural/aether_medium_grass"));
        this.itemBlockFlat(UnityBlocks.SKYFERN.get(), "natural/");
        this.leafPile(UnityBlocks.GOLDEN_OAK_LEAF_PILE.get());
        this.leafPile(UnityBlocks.SKYROOT_LEAF_PILE.get());
        this.item(UnityBlocks.AMBROSIUM_CAMPFIRE.asItem(), "misc/");

        this.itemBlock(UnityBlocks.FLUTEMOSS_BLOCK.get());
        this.itemBlock(UnityBlocks.FLUTEMOSS_CARPET.get());

        this.itemBlock(UnityBlocks.AETHER_MUD.get());
        this.itemBlock(UnityBlocks.PACKED_AETHER_MUD.get());
        this.itemBlock(UnityBlocks.AETHER_MUD_BRICKS.get());
        this.itemWallBlock(UnityBlocks.AETHER_MUD_BRICK_WALL.get(), UnityBlocks.AETHER_MUD_BRICKS.get(), "construction/");
        this.itemBlock(UnityBlocks.AETHER_MUD_BRICK_STAIRS.get());
        this.itemBlock(UnityBlocks.AETHER_MUD_BRICK_SLAB.get());
        this.itemBlock(UnityBlocks.COARSE_AETHER_DIRT.get());
        this.itemBlock(UnityBlocks.VALKYRIE_CLAY.get());
        this.itemBlock(UnityBlocks.VALKYRIE_BRICKS.get());
        this.itemWallBlock(UnityBlocks.VALKYRIE_BRICK_WALL.get(), UnityBlocks.VALKYRIE_BRICKS.get(), "construction/");
        this.itemBlock(UnityBlocks.VALKYRIE_BRICK_STAIRS.get());
        this.itemBlock(UnityBlocks.VALKYRIE_BRICK_SLAB.get());
        this.itemBlock(UnityBlocks.VALKYRIE_TILES.get());
        this.itemWallBlock(UnityBlocks.VALKYRIE_TILE_WALL.get(), UnityBlocks.VALKYRIE_TILES.get(), "construction/");
        this.itemBlock(UnityBlocks.VALKYRIE_TILE_STAIRS.get());
        this.itemBlock(UnityBlocks.VALKYRIE_TILE_SLAB.get());

        this.item(UnityItems.VALKYRIE_CLAY_BALL.get(), "materials/");
        this.item(UnityItems.VALKYRIE_BRICK.get(), "materials/");
    }
    
    public static class Grass extends UnityItemModelProvider {
        public Grass(PackOutput output, String id, ExistingFileHelper helper) {
            super(output, id, helper);
        }
        
        @Override
        protected void registerModels() {
            this.itemBlockFlatTintOverlay(AetherBlocks.WHITE_FLOWER.get(), "natural/");
            this.itemBlockFlatTintOverlay(AetherBlocks.PURPLE_FLOWER.get(), "natural/");
        }
    }
}
