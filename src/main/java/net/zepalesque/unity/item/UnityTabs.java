package net.zepalesque.unity.item;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.zenith.api.item.TabUtil;

@EventBusSubscriber(modid = Unity.MODID, bus = Bus.MOD)
public class UnityTabs {
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void buildCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
        CreativeModeTab tab = event.getTab();
        if (TabUtil.isForTab(event, AetherCreativeTabs.AETHER_NATURAL_BLOCKS)) {

            TabUtil.putAfter(event, AetherBlocks.AETHER_GRASS_BLOCK,
                    UnityBlocks.SHORT_AETHER_GRASS
            );

            TabUtil.putAfter(event, AetherBlocks.AETHER_DIRT,
                    UnityBlocks.COARSE_AETHER_DIRT
            );

            TabUtil.putAfter(event, AetherBlocks.AETHER_FARMLAND,
                    UnityBlocks.AETHER_MUD
            );

            TabUtil.putBefore(event,AetherBlocks.HOLYSTONE,
                    UnityBlocks.FLUTEMOSS_CARPET,
                    UnityBlocks.FLUTEMOSS_BLOCK
            );

            TabUtil.putAfter(event,AetherBlocks.SKYROOT_LEAVES,
                    UnityBlocks.SKYROOT_LEAF_PILE
            );

            TabUtil.putAfter(event, AetherBlocks.GOLDEN_OAK_LEAVES, UnityBlocks.GOLDEN_OAK_LEAF_PILE);
        } else if (TabUtil.isForTab(event, AetherCreativeTabs.AETHER_FUNCTIONAL_BLOCKS)) {
            TabUtil.putAfter(event, AetherBlocks.SUN_ALTAR, UnityBlocks.AMBROSIUM_CAMPFIRE);
        } else if (TabUtil.isForTab(event, AetherCreativeTabs.AETHER_BUILDING_BLOCKS)) {
            TabUtil.putBefore(event, AetherBlocks.ICESTONE, UnityBlocks.AETHER_MUD_BRICK_WALL, UnityBlocks.AETHER_MUD_BRICK_SLAB, UnityBlocks.AETHER_MUD_BRICK_STAIRS, UnityBlocks.AETHER_MUD_BRICKS, UnityBlocks.PACKED_AETHER_MUD);
        }
    }
}
