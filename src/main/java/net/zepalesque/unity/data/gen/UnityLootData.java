package net.zepalesque.unity.data.gen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.zepalesque.unity.data.gen.loot.UnityBlockLoot;
import net.zepalesque.unity.loot.UnityLoot;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UnityLootData {

    public static LootTableProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        return new LootTableProvider(output, UnityLoot.IMMUTABLE_LOOT_TABLES, List.of(
                new LootTableProvider.SubProviderEntry(UnityBlockLoot::new, LootContextParamSets.BLOCK)
        ), lookup);
    }
}
