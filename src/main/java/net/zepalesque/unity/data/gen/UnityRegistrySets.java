package net.zepalesque.unity.data.gen;

import com.aetherteam.aether.Aether;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.zepalesque.unity.data.resource.registries.UnityFeatureConfig;
import net.zepalesque.unity.data.resource.registries.UnityPlacements;
import org.apache.commons.compress.utils.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class UnityRegistrySets extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, UnityFeatureConfig::bootstrap)
            .add(Registries.PLACED_FEATURE, UnityPlacements::bootstrap);

    public UnityRegistrySets(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, String modid, String... otherIds) {
        super(output, registries, BUILDER, buildModidList(modid, otherIds));
    }
    public static Set<String> buildModidList(String modid, String... otherIds) {
        List<String> list = Lists.newArrayList();
        list.add(Aether.MODID);
        list.add(modid);
        list.addAll(Arrays.stream(otherIds).toList());
        return Set.copyOf(list);
    }
}