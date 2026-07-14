package net.veroxuniverse.what_lurks_between.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class VentBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public VentBlock(BlockBehaviour.Properties properties) {
        super(properties.randomTicks());
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(ACTIVE, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.block();
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos neighborPos, boolean isMoving) {
        if (!level.isClientSide) {
            boolean hasSignal = level.hasNeighborSignal(pos);
            if (hasSignal && !state.getValue(ACTIVE)) {
                triggerEruption((ServerLevel) level, pos, state);
            }
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(WATERLOGGED) && !state.getValue(ACTIVE)) {
            triggerEruption(level, pos, state);
        }
    }

    private void triggerEruption(ServerLevel level, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(ACTIVE, true), 3);

        if (state.getValue(WATERLOGGED)) {
            level.playSound(null, pos, SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS, 0.5F, 0.4F);
            level.playSound(null, pos, SoundEvents.BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, SoundSource.BLOCKS, 0.8F, 0.7F);
        } else {
            level.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 0.8F, 0.5F);
            level.playSound(null, pos, SoundEvents.ENDER_DRAGON_SHOOT, SoundSource.BLOCKS, 0.5F, 0.6F);
        }

        triggerEruptionPush(level, pos);
        level.scheduleTick(pos, this, 40);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(ACTIVE)) {
            level.setBlock(pos, state.setValue(ACTIVE, false), 3);
        }
    }

    private void triggerEruptionPush(Level level, BlockPos pos) {
        AABB pushZone = new AABB(
                pos.getX() + 0.0D, pos.getY() + 1.01D, pos.getZ() + 0.0D,
                pos.getX() + 1.0D, pos.getY() + 6.0D,  pos.getZ() + 1.0D
        );

        List<Entity> entities = level.getEntities(null, pushZone);
        for (Entity entity : entities) {
            Vec3 motion = entity.getDeltaMovement();
            entity.setDeltaMovement(motion.x, 0.9D, motion.z);
            entity.hurtMarked = true;
        }
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (state.getValue(ACTIVE)) {
            if (entity.getX() >= pos.getX() && entity.getX() <= pos.getX() + 1.0D &&
                    entity.getZ() >= pos.getZ() && entity.getZ() <= pos.getZ() + 1.0D) {

                Vec3 motion = entity.getDeltaMovement();
                entity.setDeltaMovement(motion.x, 0.9D, motion.z);
                entity.hurtMarked = true;
            }
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(ACTIVE)) {
            double x = pos.getX() + 0.5D;
            double y = pos.getY() + (14.0D / 16.0D);
            double z = pos.getZ() + 0.5D;

            boolean isWater = state.getValue(WATERLOGGED);

            for (int i = 0; i < 15; i++) {
                double speedY = 0.8D + random.nextDouble() * 0.4D;

                if (isWater) {
                    level.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, x, y, z, 0.0D, speedY, 0.0D);
                    if (random.nextBoolean()) {
                        level.addParticle(ParticleTypes.POOF, x, y + 0.05D, z, 0.0D, 0.2D, 0.0D);
                    }
                } else {
                    level.addParticle(ParticleTypes.WHITE_SMOKE, x, y, z, 0.0D, speedY * 0.5D, 0.0D);
                    if (random.nextBoolean()) {
                        level.addParticle(ParticleTypes.POOF, x, y, z, 0.0D, speedY, 0.0D);
                    }
                }
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState()
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER)
                .setValue(ACTIVE, false);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, ACTIVE);
    }
}