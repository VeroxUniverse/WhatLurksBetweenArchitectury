package net.veroxuniverse.what_lurks_between.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;
import net.veroxuniverse.what_lurks_between.registry.ModItems;
import org.jetbrains.annotations.Nullable;

public class MireReedBlock extends CropBlock implements SimpleWaterloggedBlock {
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public MireReedBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(getAgeProperty(), 0)
                .setValue(HALF, DoubleBlockHalf.LOWER)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.WHISTLING_REEDS_SEEDS.get();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, HALF, WATERLOGGED);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            return !this.isMaxAge(state);
        }

        return false;
    }

    @Override
    public boolean isBonemealSuccess(net.minecraft.world.level.Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            int currentAge = this.getAge(state);
            int maxAge = this.getMaxAge();
            int nextAge = Math.min(maxAge, currentAge + random.nextInt(3) + 2);

            this.applyGrowth(level, pos, state, nextAge);
        } else {
            BlockPos belowPos = pos.below();
            BlockState belowState = level.getBlockState(belowPos);
            if (belowState.is(this) && belowState.getValue(HALF) == DoubleBlockHalf.LOWER) {
                this.performBonemeal(level, random, belowPos, belowState);
            }
        }
    }

    private void applyGrowth(ServerLevel level, BlockPos pos, BlockState state, int nextAge) {
        level.setBlock(pos, this.getStateForAge(nextAge).setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 2);

        if (nextAge >= 5) {
            BlockPos abovePos = pos.above();
            if (level.isEmptyBlock(abovePos) || level.getFluidState(abovePos).is(Fluids.WATER)) {
                boolean isWaterAbove = level.getFluidState(abovePos).is(Fluids.WATER);
                level.setBlock(abovePos, this.getStateForAge(nextAge).setValue(HALF, DoubleBlockHalf.UPPER).setValue(WATERLOGGED, isWaterAbove), 2);
            }
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(pos);

        if (fluidstate.is(Fluids.WATER)) {
            BlockState superState = super.getStateForPlacement(context);
            if (superState != null && pos.getY() < context.getLevel().getMaxBuildHeight() - 1) {
                return superState.setValue(WATERLOGGED, true).setValue(HALF, DoubleBlockHalf.LOWER);
            }
        }

        return null;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            int age = this.getAge(state);
            if (age < this.getMaxAge()) {
                float growthSpeed = 2.0F;
                if (random.nextInt((int)(25.0F / growthSpeed) + 1) == 0) {
                    this.applyGrowth(level, pos, state, age + 1);
                }
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            BlockState below = level.getBlockState(pos.below());
            return below.is(this) && below.getValue(HALF) == DoubleBlockHalf.LOWER;
        }

        BlockPos belowPos = pos.below();
        boolean standsOnValidBlock = this.mayPlaceOn(level.getBlockState(belowPos), level, belowPos);

        boolean isInWater = level.getFluidState(pos).is(Fluids.WATER);

        return standsOnValidBlock && isInWater;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        DoubleBlockHalf half = state.getValue(HALF);
        if (direction.getAxis() == Direction.Axis.Y && (half == DoubleBlockHalf.LOWER == (direction == Direction.UP))) {
            if (!neighborState.is(this) || neighborState.getValue(HALF) == half) {
                return state.getValue(WATERLOGGED) ? Fluids.WATER.defaultFluidState().createLegacyBlock() : Blocks.AIR.defaultBlockState();
            }
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(ModBlocks.MIRE_MUD.get()) || state.is(ModBlocks.MOSSY_MIRE_MUD.get()) || state.is(Blocks.DIRT) || state.is(Blocks.GRASS_BLOCK);
    }
}