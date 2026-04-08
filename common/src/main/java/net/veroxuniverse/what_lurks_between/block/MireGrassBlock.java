package net.veroxuniverse.what_lurks_between.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;

public class MireGrassBlock extends GrassBlock {
    public MireGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockState tallState = ModBlocks.TALL_TENTACLE_GRASS.get().defaultBlockState();
        if (tallState.canSurvive(level, pos) && level.isEmptyBlock(pos.above())) {
            DoublePlantBlock.placeAt(level, tallState, pos, 2);
        }
    }
}