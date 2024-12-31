package net.zepalesque.unity.data.prov.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zepalesque.zenith.api.blockset.BlockSet;
import net.zepalesque.zenith.api.blockset.BlockSetDatagen;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public abstract class UnityItemTagsProvider<P extends UnityItemTagsProvider<P>> extends ItemTagsProvider implements BlockSetDatagen<P> {

    public UnityItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagLookup<Block>> blockTags, String id, @Nullable ExistingFileHelper helper) {
        super(output, registries, blockTags, id, helper);
    }

    @Override
    public IntrinsicTagAppender<Item> tag(TagKey<Item> tag) {
        return super.tag(tag);
    }

    @Override
    public void copy(TagKey<Block> blockTag, TagKey<Item> itemTag) {
        super.copy(blockTag, itemTag);
    }

    @Override
    public <B extends BlockSet> void generateDataForBlockSet(B set) {
        set.itemTagData(this);
    }
}
