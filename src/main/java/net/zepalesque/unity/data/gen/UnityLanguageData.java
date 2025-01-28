package net.zepalesque.unity.data.gen;

import net.minecraft.data.PackOutput;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.prov.UnityLanguageProvider;
import net.zepalesque.unity.item.UnityItems;

public class UnityLanguageData extends UnityLanguageProvider {

    public UnityLanguageData(PackOutput output) {
        super(output, Unity.MODID);
    }

    @Override
    protected void addTranslations() {
        addBlock(UnityBlocks.SHORT_AETHER_GRASS);
        addLore(UnityBlocks.SHORT_AETHER_GRASS, "Blades of the Aether's grass. It feels slightly cool to the touch.");

        addBlock(UnityBlocks.GOLDEN_OAK_LEAF_PILE);
        addLore(UnityBlocks.GOLDEN_OAK_LEAF_PILE, "A pile of Golden Oak Leaves. These can be stacked on top of eachother to make various sizes!");

        addBlock(UnityBlocks.SKYROOT_LEAF_PILE);
        addLore(UnityBlocks.SKYROOT_LEAF_PILE, "A pile of Skyroot Leaves. These can be stacked on top of eachother to make various sizes!");

        addBlock(UnityBlocks.AMBROSIUM_CAMPFIRE);
        addLore(UnityBlocks.AMBROSIUM_CAMPFIRE, "A campfire made of Ambrosium. You can use this to cook meat, or as a signal or light source!");

        addBlock(UnityBlocks.FLUTEMOSS_BLOCK);
        addLore(UnityBlocks.FLUTEMOSS_BLOCK, "The Aether's native moss species. This can be grown with Bone Meal!");
        addBlock(UnityBlocks.FLUTEMOSS_CARPET);
        addLore(UnityBlocks.FLUTEMOSS_CARPET, "A thin, blanket-like layer of Flutemoss. This is produced when growing Flutemoss with Bone Meal!");

        addBlock(UnityBlocks.AETHER_MUD);
        addLore(UnityBlocks.AETHER_MUD, "A squelchy soil comprised of Aether Dirt. These can be smelted into Packed Aether Mud, which can be used to make some nice decorative bricks!");
        addBlock(UnityBlocks.PACKED_AETHER_MUD);
        addLore(UnityBlocks.PACKED_AETHER_MUD, "The hardened variant of Aether Mud. These can be used as a building block, or they can be turned into decorative bricks!");

        addBlock(UnityBlocks.AETHER_MUD_BRICKS);
        addLore(UnityBlocks.AETHER_MUD_BRICKS, "Bricks made from Packed Aether Mud. These can be used as a building block!");
        addBlock(UnityBlocks.AETHER_MUD_BRICK_WALL);
        addBlock(UnityBlocks.AETHER_MUD_BRICK_STAIRS);
        addBlock(UnityBlocks.AETHER_MUD_BRICK_SLAB);
        addLore(UnityBlocks.AETHER_MUD_BRICK_WALL, "Crafted from Aether Mud Bricks. " + LoreDetails.WALL);
        addLore(UnityBlocks.AETHER_MUD_BRICK_STAIRS, "Crafted from Aether Mud Bricks. " + LoreDetails.STAIRS);
        addLore(UnityBlocks.AETHER_MUD_BRICK_SLAB, "Crafted from Aether Mud Bricks. " + LoreDetails.SLAB);

        addBlock(UnityBlocks.COARSE_AETHER_DIRT);
        addLore(UnityBlocks.COARSE_AETHER_DIRT, "The coarse variation of Aether Dirt.");


        addBlock(UnityBlocks.VALKYRIE_CLAY);
        addLore(UnityBlocks.VALKYRIE_CLAY, "The Aether's native clay. This is occasionally found in lakes.");

        addBlock(UnityBlocks.VALKYRIE_BRICKS);
        addLore(UnityBlocks.VALKYRIE_BRICKS, "The result from smelting Valkyrie Clay after crafting it together. These pristine bricks make for a nice building material that can crafted into several variants.");
        addBlock(UnityBlocks.VALKYRIE_BRICK_WALL);
        addBlock(UnityBlocks.VALKYRIE_BRICK_STAIRS);
        addBlock(UnityBlocks.VALKYRIE_BRICK_SLAB);
        addLore(UnityBlocks.VALKYRIE_BRICK_WALL, "Crafted from Valkyrie Bricks. " + LoreDetails.WALL);
        addLore(UnityBlocks.VALKYRIE_BRICK_STAIRS, "Crafted from Valkyrie Bricks. " + LoreDetails.STAIRS);
        addLore(UnityBlocks.VALKYRIE_BRICK_SLAB, "Crafted from Valkyrie Bricks. " + LoreDetails.SLAB);

        addBlock(UnityBlocks.VALKYRIE_TILES);
        addLore(UnityBlocks.VALKYRIE_TILES, "A variant of Valkyrie Bricks that works nicely as a shiny floor block.");
        addBlock(UnityBlocks.VALKYRIE_TILE_WALL);
        addBlock(UnityBlocks.VALKYRIE_TILE_STAIRS);
        addBlock(UnityBlocks.VALKYRIE_TILE_SLAB);
        addLore(UnityBlocks.VALKYRIE_TILE_WALL, "Crafted from Valkyrie Tiles. " + LoreDetails.WALL);
        addLore(UnityBlocks.VALKYRIE_TILE_STAIRS, "Crafted from Valkyrie Tiles. " + LoreDetails.STAIRS);
        addLore(UnityBlocks.VALKYRIE_TILE_SLAB, "Crafted from Valkyrie Tiles. " + LoreDetails.SLAB);

        addItem(UnityItems.VALKYRIE_CLAY_BALL);
        addLore(UnityItems.VALKYRIE_CLAY_BALL, "Obtained from mining Valkyrie Clay. This moldable lump of clay can either be smelted into a Valkyrie Brick or crafted back into a block.");
        addItem(UnityItems.VALKYRIE_BRICK);
        addLore(UnityItems.VALKYRIE_BRICK, "A shiny white brick that can be crafted into various different decoration blocks, such as Valkyrie Bricks.");

        addTooltip("shift_info", "Hold [%s] for more info...");
        addPackDescription("mod", "The Aether: Unity Resources");
        addPackTitle("asset_overrides", "The Aether: Unity Asset Overrides");
        addPackDescription("asset_overrides", "Configurable, see config/aether_unity/client.toml");

    }
}
