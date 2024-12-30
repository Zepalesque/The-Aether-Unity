package net.zepalesque.unity.data.gen.loot;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.util.Unit;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.prov.loot.UnityBlockLootProvider;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnityBlockLoot extends UnityBlockLootProvider {

    private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(AetherBlocks.TREASURE_CHEST.get()).map(ItemLike::asItem).collect(Collectors.toSet());

    public UnityBlockLoot(HolderLookup.Provider registries) {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        this.add(UnityBlocks.SHORT_AETHER_GRASS.get(), shears());

        this.add(UnityBlocks.GOLDEN_OAK_LEAF_PILE.get(), shears());
        this.add(UnityBlocks.SKYROOT_LEAF_PILE.get(), shears());

        this.add(UnityBlocks.AMBROSIUM_CAMPFIRE.get(), campfireFuelDrop(Items.CHARCOAL, AetherItems.AMBROSIUM_SHARD));

        this.dropSelf(UnityBlocks.FLUTEMOSS_BLOCK.get());
        this.dropSelf(UnityBlocks.FLUTEMOSS_CARPET.get());
    }

    @Override
    public Iterable<Block> getKnownBlocks() {
        return UnityBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
    }
}
