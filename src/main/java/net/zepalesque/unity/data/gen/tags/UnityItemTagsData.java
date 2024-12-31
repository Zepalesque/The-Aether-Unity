package net.zepalesque.unity.data.gen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.UnityTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class UnityItemTagsData extends ItemTagsProvider {

    public UnityItemTagsData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper helper) {
        super(output, registries, blockTags, Unity.MODID, helper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {

        this.tag(UnityTags.Items.PACKED_AETHER_MUD_CRAFTING).add(
                Items.WHEAT
        );
        /*this.tag(UnityTags.Items.AETHER_GRAVEL).add(
        );*/
    }
}
