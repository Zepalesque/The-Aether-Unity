package net.zepalesque.unity.blockset.stone;

import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.blockset.stone.type.BaseStoneSet;
import net.zepalesque.unity.blockset.stone.type.BrickStoneSet;
import net.zepalesque.unity.item.UnityItems;
import net.zepalesque.zenith.api.blockset.AbstractStoneSet;
import net.zepalesque.zenith.api.blockset.BlockSet;

public class UnityStoneSets {

    public static final BaseStoneSet<?, ?, ?, ?, ?, ?, ?, ?> AETHER_MUD_BRICK = register(new BrickStoneSet<>("aether_mud_brick", UnityBlocks.BLOCKS, UnityItems.ITEMS,
            MapColor.DEEPSLATE, SoundType.NETHER_BRICKS, 1.0F, 6.0F, "construction/"))
            .withLore("Bricks made of Packed Aether Mud. These can be used as a nice building block!")
//            .tabAfter(AetherCreativeTabs.AETHER_BUILDING_BLOCKS, () -> UnityBlocks.PACKED_AETHER_MUD.get(), true, BlockSet.TabAdditionPhase.BEFORE)
            .withTag(BlockTags.MINEABLE_WITH_PICKAXE, true);



    public static <T extends AbstractStoneSet> T register(T set) {
        Unity.BLOCK_SETS.add(set);
        return set;
    }
}
