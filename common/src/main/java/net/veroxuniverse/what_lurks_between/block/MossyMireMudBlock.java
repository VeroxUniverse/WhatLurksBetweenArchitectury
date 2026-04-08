package net.veroxuniverse.what_lurks_between.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;

public class MossyMireMudBlock extends Block {
    private static final VoxelShape COLLISION_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

    public MossyMireMudBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter reader, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return Shapes.block();
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    private static boolean canMossSurvive(ServerLevel level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);

        if (!level.getFluidState(abovePos).isEmpty() || aboveState.isSolidRender(level, abovePos)) {
            return false;
        }

        return level.getBrightness(net.minecraft.world.level.LightLayer.SKY, abovePos) >= 4 ||
                level.getBrightness(net.minecraft.world.level.LightLayer.BLOCK, abovePos) >= 4;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isClientSide) {
            if (!canMossSurvive(level, pos)) {
                level.setBlockAndUpdate(pos, ModBlocks.MIRE_MUD.get().defaultBlockState());
            }
        }
    }
}