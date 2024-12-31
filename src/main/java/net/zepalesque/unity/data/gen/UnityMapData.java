package net.zepalesque.unity.data.gen;

import com.aetherteam.aether.data.resources.registries.AetherBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.prov.UnityDataMapProvider;
import net.zepalesque.unity.world.biome.tint.UnityBiomeTints;

import java.util.concurrent.CompletableFuture;

public class UnityMapData extends UnityDataMapProvider {

    public UnityMapData(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        var biomeTints = this.builder(UnityBiomeTints.AETHER_GRASS.get().getDataMap());
        biomeTints.add(AetherBiomes.SKYROOT_FOREST, 0xA2F2BC, false);
        biomeTints.add(AetherBiomes.SKYROOT_WOODLAND, 0x96E8B0, false);
        biomeTints.add(AetherBiomes.SKYROOT_MEADOW, 0xBAFFCB, false);

        var compostables = this.builder(NeoForgeDataMaps.COMPOSTABLES);
        this.addCompost(compostables, UnityBlocks.SHORT_AETHER_GRASS, 0.3F);
    }
}
