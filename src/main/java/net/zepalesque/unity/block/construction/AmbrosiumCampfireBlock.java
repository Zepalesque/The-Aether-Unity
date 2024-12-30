//package net.zepalesque.unity.block.construction;
//
//import com.mojang.serialization.Codec;
//import com.mojang.serialization.MapCodec;
//import com.mojang.serialization.codecs.RecordCodecBuilder;
//
//import java.util.Optional;
//import java.util.function.Supplier;
//import javax.annotation.Nullable;
//
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.core.particles.ParticleOptions;
//import net.minecraft.core.particles.ParticleTypes;
//import net.minecraft.core.particles.SimpleParticleType;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.stats.Stats;
//import net.minecraft.tags.BlockTags;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.Containers;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.ItemInteractionResult;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.entity.projectile.Projectile;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.context.BlockPlaceContext;
//import net.minecraft.world.item.crafting.CampfireCookingRecipe;
//import net.minecraft.world.item.crafting.RecipeHolder;
//import net.minecraft.world.level.BlockGetter;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.LevelAccessor;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.CampfireBlock;
//import net.minecraft.world.level.block.SimpleWaterloggedBlock;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntityTicker;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraft.world.level.block.entity.CampfireBlockEntity;
//import net.minecraft.world.level.block.state.BlockBehaviour;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.block.state.StateDefinition;
//import net.minecraft.world.level.block.state.properties.BlockStateProperties;
//import net.minecraft.world.level.block.state.properties.BooleanProperty;
//import net.minecraft.world.level.block.state.properties.DirectionProperty;
//import net.minecraft.world.level.gameevent.GameEvent;
//import net.minecraft.world.level.material.FluidState;
//import net.minecraft.world.level.material.Fluids;
//import net.minecraft.world.level.pathfinder.PathComputationType;
//import net.minecraft.world.phys.BlockHitResult;
//import net.minecraft.world.phys.shapes.BooleanOp;
//import net.minecraft.world.phys.shapes.CollisionContext;
//import net.minecraft.world.phys.shapes.Shapes;
//import net.minecraft.world.phys.shapes.VoxelShape;
//
//public class AmbrosiumCampfireBlock extends CampfireBlock implements SimpleWaterloggedBlock {
//
//
//    public static final MapCodec<CampfireBlock> CODEC = RecordCodecBuilder.<AmbrosiumCampfireBlock>mapCodec(
//            p_308808_ -> p_308808_.group(
//                            Codec.intRange(0, 1000).fieldOf("fire_damage").forGetter(block -> block.fireDamageProtected),
//                            propertiesCodec()
//                    )
//                    .apply(p_308808_, AmbrosiumCampfireBlock::new)
//    ).xmap(block -> block, block -> (AmbrosiumCampfireBlock) block);
//    protected final int fireDamageProtected;
//
//
//    @Override
//    public MapCodec<CampfireBlock> codec() {
//        return CODEC;
//    }
//
//    public AmbrosiumCampfireBlock(int fireDamage, BlockBehaviour.Properties properties) {
//        super(false, fireDamage, properties);
//        this.fireDamageProtected = fireDamage;
//    }
//
//
//    public static boolean isLitCampfire(BlockState state) {
//        return state.hasProperty(LIT) && state.is(BlockTags.CAMPFIRES) && state.getValue(LIT);
//    }
//
//    @Override
//    protected FluidState getFluidState(BlockState state) {
//        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
//    }
//
//    /**
//     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed blockstate.
//     * @deprecated call via {@link net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#rotate} whenever possible. Implementing/overriding is fine.
//     */
//    @Override
//    protected BlockState rotate(BlockState state, Rotation rot) {
//        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
//    }
//
//    /**
//     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed blockstate.
//     * @deprecated call via {@link net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#mirror} whenever possible. Implementing/overriding is fine.
//     */
//    @Override
//    protected BlockState mirror(BlockState state, Mirror mirror) {
//        return state.rotate(mirror.getRotation(state.getValue(FACING)));
//    }
//
//    @Override
//    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
//        builder.add(LIT, SIGNAL_FIRE, WATERLOGGED, FACING);
//    }
//
//    @Override
//    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
//        return new CampfireBlockEntity(pos, state);
//    }
//
//    @Nullable
//    @Override
//    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
//        if (level.isClientSide) {
//            return state.getValue(LIT) ? createTickerHelper(blockEntityType, BlockEntityType.CAMPFIRE, CampfireBlockEntity::particleTick) : null;
//        } else {
//            return state.getValue(LIT)
//                    ? createTickerHelper(blockEntityType, BlockEntityType.CAMPFIRE, CampfireBlockEntity::cookTick)
//                    : createTickerHelper(blockEntityType, BlockEntityType.CAMPFIRE, CampfireBlockEntity::cooldownTick);
//        }
//    }
//
//    @Override
//    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
//        return false;
//    }
//
//    public static boolean canLight(BlockState state) {
//        return state.is(BlockTags.CAMPFIRES, p_51262_ -> p_51262_.hasProperty(WATERLOGGED) && p_51262_.hasProperty(LIT))
//                && !state.getValue(WATERLOGGED)
//                && !state.getValue(LIT);
//    }
//}
