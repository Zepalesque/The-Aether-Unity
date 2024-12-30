package net.zepalesque.unity.data.prov.loot;

import com.aetherteam.aether.data.providers.AetherBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;
import java.util.function.Function;

// Many of these are just public overrides with no differences, as this is used by the BlockSets
public abstract class UnityBlockLootProvider extends AetherBlockLootSubProvider {

    public UnityBlockLootProvider(Set<Item> items, FeatureFlagSet flags, HolderLookup.Provider registries) {
        super(items, flags, registries);
    }


    public Function<Block, LootTable.Builder> campfire(ItemLike drop) {
        return block -> this.createSilkTouchDispatchTable(
                block, this.applyExplosionCondition(
                        block, LootItem.lootTableItem(drop).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                )
        );
    }


    // Only drops with shears
    public Function<Block, LootTable.Builder> shears() {
        return shearsOr(Blocks.AIR);
    }

    // Drops another without shears
    public Function<Block, LootTable.Builder> shearsOr(ItemLike drop, float chance, float min, float max) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return (block) -> createSilkTouchOrShearsDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(drop).when(LootItemRandomChanceCondition.randomChance(chance)).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    public Function<Block, LootTable.Builder> shearsOr(ItemLike drop, float chance) {
        return shearsOr(drop, chance, 1.0F, 1.0F);
    }

    public Function<Block, LootTable.Builder> shearsOr(ItemLike drop) {
        return shearsOr(drop, 0.25F);
    }

}
