package net.zepalesque.unity.data.gen.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.UnityTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class UnityBlockTagsData extends BlockTagsProvider {

    public UnityBlockTagsData(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Unity.MODID, existingFileHelper);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTags(HolderLookup.Provider provider) {
        // Adds every single Unity block as a block that should be treaded as an Aether Block and get the tool debuff
        IntrinsicTagAppender<Block> tag = this.tag(AetherTags.Blocks.TREATED_AS_AETHER_BLOCK);
        for (DeferredHolder<Block, ? extends Block> block : UnityBlocks.BLOCKS.getEntries()) {
            tag.add(block.get());
        }
        this.tag(UnityTags.Blocks.ENCHANTED_GRASS_BLOCKS).add(AetherBlocks.ENCHANTED_AETHER_GRASS_BLOCK.get());

        this.tag(BlockTags.REPLACEABLE).add(
                UnityBlocks.SHORT_AETHER_GRASS.get()
        );

        this.tag(BlockTags.MINEABLE_WITH_HOE).add(
                UnityBlocks.GOLDEN_OAK_LEAF_PILE.get(),
                UnityBlocks.SKYROOT_LEAF_PILE.get()
        );

        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                UnityBlocks.AMBROSIUM_CAMPFIRE.get()
        );
        
        this.tag(BlockTags.CAMPFIRES).add(
                UnityBlocks.AMBROSIUM_CAMPFIRE.get()
        );
    }
}
