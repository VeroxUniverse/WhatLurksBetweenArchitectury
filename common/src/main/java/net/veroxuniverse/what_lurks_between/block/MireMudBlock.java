package net.veroxuniverse.what_lurks_between.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;

public class MireMudBlock extends Block {

    private static final VoxelShape COLLISION_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

    public MireMudBlock(Properties properties) {
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

    private static boolean canReceiveMoss(ServerLevel level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        int skyLight = level.getBrightness(LightLayer.SKY, abovePos);

        return level.getFluidState(abovePos).isEmpty() &&
                skyLight >= 9 &&
                !level.getBlockState(abovePos).isSolid();
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isClientSide && canReceiveMoss(level, pos)) {
            for (int i = 0; i < 4; ++i) {
                BlockPos neighborPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                BlockState sourceState = level.getBlockState(neighborPos);

                if (sourceState.is(ModBlocks.MIRE_MOSS.get()) || sourceState.is(ModBlocks.MOSSY_MIRE_MUD.get())) {
                    level.setBlockAndUpdate(pos, ModBlocks.MOSSY_MIRE_MUD.get().defaultBlockState());
                    return;
                }
            }
        }
    }
}