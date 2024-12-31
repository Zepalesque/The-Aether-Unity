package net.zepalesque.unity.data.prov;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;

import java.util.concurrent.CompletableFuture;

public abstract class UnityDataMapProvider extends DataMapProvider {

    protected UnityDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    public void addCompost(DataMapProvider.Builder<Compostable, Item> map, ItemLike item, float chance) {
        map.add(item.asItem().builtInRegistryHolder(), new Compostable(chance), false);
    }
}
