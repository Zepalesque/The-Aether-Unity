package net.zepalesque.unity.block.natural;

import com.aetherteam.aether.block.natural.AetherBushBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.zepalesque.unity.block.state.UnityStates;
import net.zepalesque.unity.block.state.enums.GrassSize;
import net.zepalesque.unity.client.UnityColors;
import net.zepalesque.unity.data.UnityTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class AetherShortGrassBlock extends AetherBushBlock {

    /**
     * See {@link UnityColors#enchantedGrassOverrides(BlockState, BlockAndTintGetter, BlockPos, int, Predicate, boolean)}
     */
    public static final Collection<TintOverride> COLOR_OVERRIDES = new ArrayList<>();


    public static final List<VoxelShape> SHAPES = List.of(
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 7.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D)
    );
    protected static VoxelShape COLLISION_SHAPE = Shapes.empty();

    public AetherShortGrassBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(UnityStates.ENCHANTED, false).setValue(UnityStates.GRASS_SIZE, GrassSize.MEDIUM));
    }
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        GrassSize size = pState.getValue(UnityStates.GRASS_SIZE);
        return SHAPES.get(size.ordinal());
    }

    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return super.canBeReplaced(pState, pUseContext) &&
                !pUseContext.getItemInHand().is(UnityTags.Items.AETHER_GRASS_NONREPLACING);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(UnityStates.ENCHANTED);
        builder.add(UnityStates.GRASS_SIZE);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return setValues(context.getLevel(), context.getClickedPos(), super.getStateForPlacement(context));
    }


    public BlockState setValues(Level level, BlockPos pos, BlockState state) {
        if (state != null) {
            long r = Mth.getSeed(pos);
            RandomSource rand = new XoroshiroRandomSource(r);
            int i = rand.nextInt(3);
            GrassSize size = GrassSize.values()[i];
            BlockState b = state.setValue(UnityStates.GRASS_SIZE, size);
            BlockPos below = pos.below();
            if (level.getBlockState(below).is(UnityTags.Blocks.ENCHANTED_GRASS_BLOCKS)) {
                return b.setValue(UnityStates.ENCHANTED, true);
            }
            return b;
        }
        return state;
    }


    @Override
    @NotNull
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        BlockState b = super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        if (b.hasProperty(UnityStates.GRASS_SIZE)) {
            long r = Mth.getSeed(currentPos);
            RandomSource rand = new XoroshiroRandomSource(r);
            int i = rand.nextInt(3);
            GrassSize size = GrassSize.values()[i];
            b = b.setValue(UnityStates.GRASS_SIZE, size);
        }
        if (b.hasProperty(UnityStates.ENCHANTED) && facing == Direction.DOWN) {
            if (level.getBlockState(facingPos).is(UnityTags.Blocks.ENCHANTED_GRASS_BLOCKS)) {
                return b.setValue(UnityStates.ENCHANTED, true);
            }
            return b.setValue(UnityStates.ENCHANTED, false);
        }
        return b;
    }


    public interface TintOverride {
        Optional<Integer> tint(BlockState state, @javax.annotation.Nullable BlockAndTintGetter level, @javax.annotation.Nullable BlockPos pos, int index, Predicate<Integer> indexGoal, boolean useBelowProperties);
    }
}
