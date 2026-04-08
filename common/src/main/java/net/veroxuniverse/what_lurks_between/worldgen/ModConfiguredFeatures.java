package net.veroxuniverse.what_lurks_between.worldgen;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.block.MireReedBlock;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;
import net.veroxuniverse.what_lurks_between.worldgen.tree.GhostWillowTreeFeature;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> GHOST_WILLOW_TREE = registerKey("ghost_willow_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_TENTACLE_GRASS_PATCH = registerKey("tall_tentacle_grass_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHISTLING_REEDS_PATCH = registerKey("whistling_reeds_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIRE_LAKE = registerKey("mire_lake");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WET_MUD_LAKE = registerKey("wet_mud_lake"); // NEU
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIRE_LILLY_PADS = registerKey("mire_lilly_pads");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIRE_SHORT_GRASS = registerKey("mire_short_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROOT_TENTACLES_PATCH = registerKey("root_tentacles_patch");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        GhostWillowTreeFeature.bootstrap(context);

        context.register(MIRE_SHORT_GRASS, new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        32, 7, 3,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.TENTACLE_GRASS.get())),
                                BlockPredicate.allOf(
                                        BlockPredicate.matchesBlocks(Blocks.AIR),
                                        BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), ModBlocks.MIRE_MOSS.get(), ModBlocks.MOSSY_MIRE_MUD.get())
                                )
                        )
                )));

        context.register(MIRE_LILLY_PADS, new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        20, 6, 2,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.LILY_PAD)),
                                BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.WATER)
                        )
                )));

        context.register(MIRE_LAKE, new ConfiguredFeature<>(Feature.LAKE,
                new LakeFeature.Configuration(
                        BlockStateProvider.simple(Blocks.WATER.defaultBlockState()),
                        BlockStateProvider.simple(ModBlocks.ROCKY_MIRE_MUD.get().defaultBlockState())
                )));

        context.register(WET_MUD_LAKE, new ConfiguredFeature<>(Feature.LAKE,
                new LakeFeature.Configuration(
                        BlockStateProvider.simple(ModBlocks.WET_MIRE_MUD.get().defaultBlockState()),
                        BlockStateProvider.simple(ModBlocks.ROCKY_MIRE_MUD.get().defaultBlockState())
                )));

        context.register(WHISTLING_REEDS_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        32,
                        6,
                        2,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                                                .add(ModBlocks.WHISTLING_REEDS.get().defaultBlockState().setValue(MireReedBlock.AGE, 1).setValue(MireReedBlock.WATERLOGGED, true), 1)
                                                .add(ModBlocks.WHISTLING_REEDS.get().defaultBlockState().setValue(MireReedBlock.AGE, 2).setValue(MireReedBlock.WATERLOGGED, true), 2)
                                                .add(ModBlocks.WHISTLING_REEDS.get().defaultBlockState().setValue(MireReedBlock.AGE, 4).setValue(MireReedBlock.WATERLOGGED, true), 1)
                                        )
                                ),
                                BlockPredicate.allOf(
                                        BlockPredicate.matchesBlocks(Blocks.WATER),
                                        BlockPredicate.matchesBlocks(Direction.UP.getNormal(), Blocks.AIR),
                                        BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), ModBlocks.MIRE_MUD.get(), ModBlocks.MOSSY_MIRE_MUD.get())
                                )
                        ))));

        context.register(TALL_TENTACLE_GRASS_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        8, 4, 2,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.TALL_TENTACLE_GRASS.get())),
                                BlockPredicate.matchesBlocks(Blocks.AIR)
                        ))));

        context.register(ROOT_TENTACLES_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        8, 4, 2,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ROOT_TENTACLES.get())),
                                BlockPredicate.matchesBlocks(Blocks.AIR)
                        ))));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(WhatLurksBetween.MOD_ID, name));
    }
}