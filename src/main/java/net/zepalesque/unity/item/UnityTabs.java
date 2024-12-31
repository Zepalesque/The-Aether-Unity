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
        if (tab == AetherCreativeTabs.AETHER_NATURAL_BLOCKS.get()) {

            TabUtil.putAfter(event, AetherBlocks.AETHER_GRASS_BLOCK,
                    UnityBlocks.SHORT_AETHER_GRASS
            );

            TabUtil.putBefore(event,AetherBlocks.HOLYSTONE,
                    UnityBlocks.FLUTEMOSS_CARPET,
                    UnityBlocks.FLUTEMOSS_BLOCK
            );

            TabUtil.putAfter(event,AetherBlocks.SKYROOT_LEAVES,
                    UnityBlocks.SKYROOT_LEAF_PILE
            );

            TabUtil.putAfter(event, AetherBlocks.GOLDEN_OAK_LEAVES, UnityBlocks.GOLDEN_OAK_LEAF_PILE);
        }

        if (tab == AetherCreativeTabs.AETHER_FUNCTIONAL_BLOCKS.get()) {
            TabUtil.putAfter(event, AetherBlocks.SUN_ALTAR, UnityBlocks.AMBROSIUM_CAMPFIRE);
        }
    }
}
