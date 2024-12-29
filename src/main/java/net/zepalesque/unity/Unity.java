package net.zepalesque.unity;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;
import net.zepalesque.unity.attachment.UnityDataAttachments;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.client.UnityClient;
import net.zepalesque.unity.client.UnityColors;
import net.zepalesque.unity.config.UnityConfig;
import net.zepalesque.unity.config.UnityConfigHandler;
import net.zepalesque.unity.data.UnityData;
import net.zepalesque.unity.item.UnityItems;
import net.zepalesque.unity.tile.UnityTiles;
import net.zepalesque.unity.world.biome.tint.UnityBiomeTints;
import net.zepalesque.zenith.api.blockset.BlockSet;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collection;

@Mod(Unity.MODID)
public class Unity {
    public static final String MODID = "aether_unity";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final Collection<BlockSet> BLOCK_SETS = new ArrayList<>();

    public Unity(ModContainer mod, IEventBus bus, Dist dist) {
        bus.addListener(EventPriority.LOWEST, UnityData::dataSetup);
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::registerDataMaps);
        bus.addListener(this::packSetup);
        bus.addListener(this::registerPackets);
        if (dist == Dist.CLIENT) {
            bus.addListener(EventPriority.LOWEST, UnityColors::blockColors);
            bus.addListener(UnityColors::itemColors);
            bus.addListener(UnityColors::resolvers);
        }

        DeferredRegister<?>[] registers = {
                UnityBlocks.BLOCKS,
                UnityItems.ITEMS,
                UnityTiles.TILES,
                UnityBiomeTints.TINTS,
                UnityDataAttachments.ATTACHMENTS
        };

        for (DeferredRegister<?> register : registers) {
            register.register(bus);
        }

        UnityConfigHandler.setup(mod, bus);

        UnityConfig.SERVER.registerSerializer();
        UnityConfig.COMMON.registerSerializer();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            UnityBlocks.registerToolConversions();
            UnityBlocks.registerFlammability();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            UnityClient.registerTintOverrides();
        });
    }

    public void registerPackets(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(MODID).versioned("1.0.0").optional();
    }

    private void registerDataMaps(RegisterDataMapTypesEvent event) {
    }

    public  void packSetup(AddPackFindersEvent event) {
        // TODO
    }

    public static ResourceLocation loc(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }



}
