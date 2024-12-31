package net.zepalesque.unity.data.gen.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.UnityTags;
import net.zepalesque.unity.data.prov.tags.UnityBlockTagsProvider;
import net.zepalesque.zenith.api.blockset.BlockSet;
import net.zepalesque.zenith.api.blockset.BlockSetDatagen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class UnityBlockTagsData extends UnityBlockTagsProvider<UnityBlockTagsData> {

    public UnityBlockTagsData(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Unity.MODID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {

        this.doBlockSetGeneration();

        // Adds every single Unity block as a block that should be treaded as an Aether Block and get the tool debuff
        IntrinsicTagAppender<Block> tag = this.tag(AetherTags.Blocks.TREATED_AS_AETHER_BLOCK);
        for (DeferredHolder<Block, ? extends Block> block : UnityBlocks.BLOCKS.getEntries()) {
            tag.add(block.get());
        }

        this.tag(UnityTags.Blocks.SHORT_AETHER_GRASS_STATE_ENCHANTING).add(
                AetherBlocks.ENCHANTED_AETHER_GRASS_BLOCK.get()
        );
        this.tag(UnityTags.Blocks.SHORT_AETHER_GRASS_DEFAULT_COLORING).add(
                UnityBlocks.FLUTEMOSS_BLOCK.get()
        );

        this.tag(UnityTags.Blocks.AETHER_CARVER_REPLACEABLES).addTags(
                AetherTags.Blocks.HOLYSTONE, AetherTags.Blocks.AETHER_DIRT
        );

        this.tag(AetherTags.Blocks.AETHER_DIRT).add(
                UnityBlocks.FLUTEMOSS_BLOCK.get()
        );

        this.tag(BlockTags.REPLACEABLE).add(
                UnityBlocks.SHORT_AETHER_GRASS.get()
        );

        this.tag(BlockTags.MINEABLE_WITH_HOE).add(
                UnityBlocks.GOLDEN_OAK_LEAF_PILE.get(),
                UnityBlocks.SKYROOT_LEAF_PILE.get(),
                UnityBlocks.FLUTEMOSS_BLOCK.get(),
                UnityBlocks.FLUTEMOSS_CARPET.get()
        );

        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                UnityBlocks.AMBROSIUM_CAMPFIRE.get()
        );
        
        this.tag(BlockTags.CAMPFIRES).add(
                UnityBlocks.AMBROSIUM_CAMPFIRE.get()
        );
    }
}
