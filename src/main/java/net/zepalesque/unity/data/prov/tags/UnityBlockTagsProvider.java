package net.zepalesque.unity.data.prov.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zepalesque.zenith.api.blockset.BlockSet;
import net.zepalesque.zenith.api.blockset.BlockSetDatagen;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public abstract class UnityBlockTagsProvider<P extends UnityBlockTagsProvider<P>> extends BlockTagsProvider implements BlockSetDatagen<P> {

    public UnityBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    public IntrinsicTagAppender<Block> tag(TagKey<Block> tag) {
        return super.tag(tag);
    }

    @Override
    public <B extends BlockSet> void generateDataForBlockSet(B set) {
        set.blockTagData(this);
    }
}
