package net.zepalesque.unity.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.UnityTags;

public class UnityDispenserBehaviors {

    public static void bootstrap() {
        DispenserBlock.registerBehavior(
                Items.POTION,
                new DefaultDispenseItemBehavior() {
                    private final DefaultDispenseItemBehavior defaultBehavior = new DefaultDispenseItemBehavior();

                    @Override
                    public ItemStack execute(BlockSource source, ItemStack stack) {
                        PotionContents potioncontents = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
                        if (!potioncontents.is(Potions.WATER)) {
                            return this.defaultBehavior.dispense(source, stack);
                        } else {
                            ServerLevel level = source.level();
                            BlockPos pos = source.pos();
                            BlockPos relative = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
                            if (!level.getBlockState(relative).is(UnityTags.Blocks.CONVERTABLE_TO_AETHER_MUD)) {
                                return this.defaultBehavior.dispense(source, stack);
                            } else {
                                if (!level.isClientSide) {
                                    for (int i = 0; i < 5; i++) {
                                        level.sendParticles(
                                                ParticleTypes.SPLASH,
                                                (double)pos.getX() + level.random.nextDouble(),
                                                pos.getY() + 1,
                                                (double)pos.getZ() + level.random.nextDouble(),
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
                                level.setBlockAndUpdate(relative, UnityBlocks.AETHER_MUD.get().defaultBlockState());
                                return this.consumeWithRemainder(source, stack, new ItemStack(Items.GLASS_BOTTLE));
                            }
                        }
                    }
                }
        );
    }
}
