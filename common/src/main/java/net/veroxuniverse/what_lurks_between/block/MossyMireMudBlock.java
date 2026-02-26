package net.veroxuniverse.what_lurks_between.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
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
        if (context instanceof EntityCollisionContext ecc && ecc.getEntity() instanceof LivingEntity living) {
            if (living.getItemBySlot(EquipmentSlot.FEET).is(Items.LEATHER_BOOTS)) {
                return Shapes.block();
            }
        }
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

    private static boolean canBeMossy(BlockState state, ServerLevel level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);

        return level.getMaxLocalRawBrightness(abovePos) >= 4 &&
                aboveState.getLightBlock(level, abovePos) < level.getMaxLightLevel();
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isClientSide) {
            if (!canBeMossy(state, level, pos)) {
                level.setBlockAndUpdate(pos, ModBlocks.MIRE_MUD.get().defaultBlockState());
            }
        }
    }
}