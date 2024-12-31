package net.zepalesque.unity.event.hook;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.UnityTags;

import java.util.HashMap;
import java.util.Map;

public class BlockHooks {
    public static boolean tryConvertMud(ItemStack stack, Level level, BlockPos pos, Player player, BlockState state, Direction face, InteractionHand hand) {
        if (stack.getItem() instanceof PotionItem potion) {
            PotionContents potioncontents = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
            if (face != Direction.DOWN && state.is(UnityTags.Blocks.CONVERTABLE_TO_AETHER_MUD) && potioncontents.is(Potions.WATER)) {
                level.playSound(null, pos, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS, 1.0F, 1.0F);
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                if (!level.isClientSide) {
                    ServerLevel serverlevel = (ServerLevel) level;

                    for (int i = 0; i < 5; i++) {
                        serverlevel.sendParticles(
                                ParticleTypes.SPLASH,
                                (double) pos.getX() + level.random.nextDouble(),
                                pos.getY() + 1,
                                (double) pos.getZ() + level.random.nextDouble(),
                                1,
                                0.0,
                                0.0,
                                0.0,
                                1.0
                        );
                    }
                }

                level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                level.setBlockAndUpdate(pos, UnityBlocks.AETHER_MUD.get().defaultBlockState());
                return true;
            }
        }
        return false;
    }

    public static class ToolConversions {

        public static final Map<Block, Block> STRIPPABLES = new HashMap<>();

        public static final Map<Block, Block> FLATTENABLES = new HashMap<>();

        public static final Map<Block, Block> TILLABLES = new HashMap<>();


        public static BlockState setupToolActions(LevelAccessor accessor, BlockPos pos, BlockState old, ItemAbility action) {
            Block oldBlock = old.getBlock();
            if (action == ItemAbilities.AXE_STRIP) {
                if (STRIPPABLES.containsKey(oldBlock)) {
                    return STRIPPABLES.get(oldBlock).withPropertiesOf(old);
                }
            } else if (action == ItemAbilities.SHOVEL_FLATTEN) {
                if (FLATTENABLES.containsKey(oldBlock)) {
                    return FLATTENABLES.get(oldBlock).withPropertiesOf(old);
                }
            } else if (action == ItemAbilities.HOE_TILL) {
                if (accessor.getBlockState(pos.above()).isAir()) {
                    if (TILLABLES.containsKey(oldBlock)) {
                        return TILLABLES.get(oldBlock).withPropertiesOf(old);
                    }
                }
            }
            return old;
        }
    }
}
