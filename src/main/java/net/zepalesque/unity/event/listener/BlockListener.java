package net.zepalesque.unity.event.listener;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;

@EventBusSubscriber(modid = Unity.MODID, bus = EventBusSubscriber.Bus.MOD)
public class BlockListener {
    @SubscribeEvent
    public static void registerBlockEntityBlocks(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.CAMPFIRE, UnityBlocks.AMBROSIUM_CAMPFIRE.get());
    }
}
