package net.zepalesque.unity.data.prov;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.zepalesque.zenith.api.blockset.BlockSet;
import net.zepalesque.zenith.api.blockset.BlockSetDatagen;

import java.util.concurrent.CompletableFuture;

public abstract class UnityDataMapProvider<P extends UnityDataMapProvider<P>> extends DataMapProvider implements BlockSetDatagen<P> {

    protected UnityDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    public void addCompost(DataMapProvider.Builder<Compostable, Item> map, ItemLike item, float chance) {
        map.add(item.asItem().builtInRegistryHolder(), new Compostable(chance), false);
    }

    @Override
    public <B extends BlockSet> void generateDataForBlockSet(B set) {
        set.mapData(this);
    }
}
