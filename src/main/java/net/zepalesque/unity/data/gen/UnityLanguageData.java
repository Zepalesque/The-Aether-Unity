package net.zepalesque.unity.data.gen;

import net.minecraft.data.PackOutput;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.prov.UnityLanguageProvider;

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
        addLore(UnityBlocks.AETHER_MUD_BRICKS, "The hardened variant of Aether Mud. These can be used as a building block, or they can be turned into decorative bricks!");
        addBlock(UnityBlocks.AETHER_MUD_BRICK_WALL);
        addBlock(UnityBlocks.AETHER_MUD_BRICK_STAIRS);
        addBlock(UnityBlocks.AETHER_MUD_BRICK_SLAB);
        addLore(UnityBlocks.AETHER_MUD_BRICK_WALL, "Crafted from Holystone Bricks. " + LoreDetails.WALL);
        addLore(UnityBlocks.AETHER_MUD_BRICK_STAIRS, "The hardened variant of Aether Mud. " + LoreDetails.STAIRS);
        addLore(UnityBlocks.AETHER_MUD_BRICK_SLAB, "The hardened variant of Aether Mud. " + LoreDetails.SLAB);

        addTooltip("shift_info", "Hold [%s] for more info...");
        addPackDescription("mod", "The Aether: Unity Resources");

    }
}
