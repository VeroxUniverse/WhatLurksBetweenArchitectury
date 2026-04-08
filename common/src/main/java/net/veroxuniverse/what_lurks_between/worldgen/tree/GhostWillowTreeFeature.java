package net.veroxuniverse.what_lurks_between.worldgen.tree;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacement;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.UpwardsBranchingTrunkPlacer;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;
import net.veroxuniverse.what_lurks_between.worldgen.ModConfiguredFeatures;

import java.util.Optional;

public class GhostWillowTreeFeature {
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        var blocks = context.lookup(Registries.BLOCK);

        context.register(ModConfiguredFeatures.GHOST_WILLOW_TREE, new ConfiguredFeature<>(Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.GHOST_WILLOW_LOG.get()),
                        new UpwardsBranchingTrunkPlacer(
                                4, 2, 5,
                                UniformInt.of(1, 3), 0.5F,
                                UniformInt.of(0, 1),
                                blocks.getOrThrow(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),

                        new WeightedStateProvider(
                                SimpleWeightedRandomList.<BlockState>builder()
                                        .add(ModBlocks.GHOST_WILLOW_LEAVES.get().defaultBlockState(), 99)
                                        .add(ModBlocks.BLEEDING_GHOST_WILLOW_LEAVES.get().defaultBlockState(), 1)
                        ),

                        new RandomSpreadFoliagePlacer(
                                ConstantInt.of(2),
                                ConstantInt.of(0),
                                ConstantInt.of(2),
                                25),
                        Optional.of(new MangroveRootPlacer(
                                UniformInt.of(1, 3),
                                BlockStateProvider.simple(ModBlocks.GHOST_WILLOW_ROOTS.get()),
                                Optional.empty(),
                                new MangroveRootPlacement(
                                        blocks.getOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH),
                                        HolderSet.direct(
                                                ModBlocks.MIRE_MUD.get().builtInRegistryHolder(),
                                                net.minecraft.world.level.block.Blocks.WATER.builtInRegistryHolder()
                                        ),
                                        BlockStateProvider.simple(ModBlocks.MIRE_MUD.get()),
                                        8, 12, 0.1F)
                        )),
                        new TwoLayersFeatureSize(2, 0, 2)
                ).ignoreVines().build()
        ));
    }
}