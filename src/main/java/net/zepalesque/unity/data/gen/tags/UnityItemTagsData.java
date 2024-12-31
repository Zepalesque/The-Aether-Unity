package net.zepalesque.unity.data.gen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.data.prov.tags.UnityBlockTagsProvider;
import net.zepalesque.unity.data.prov.tags.UnityItemTagsProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class UnityItemTagsData extends UnityItemTagsProvider<UnityItemTagsData> {

    public UnityItemTagsData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper helper) {
        super(output, registries, blockTags, Unity.MODID, helper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {

    }
}
