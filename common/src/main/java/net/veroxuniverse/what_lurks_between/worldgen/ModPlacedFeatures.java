package net.veroxuniverse.what_lurks_between.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> GHOST_WILLOW_PLACED = registerKey("ghost_willow_placed");
    public static final ResourceKey<PlacedFeature> WHISTLING_REEDS_PLACED = registerKey("whistling_reeds_placed");
    public static final ResourceKey<PlacedFeature> TALL_TENTACLE_GRASS_PLACED = registerKey("tall_tentacle_grass_placed");
    public static final ResourceKey<PlacedFeature> MIRE_LAKE_PLACED = registerKey("mire_lake_placed");
    public static final ResourceKey<PlacedFeature> WET_MUD_LAKE_PLACED = registerKey("wet_mud_lake_placed"); // NEU
    public static final ResourceKey<PlacedFeature> MIRE_LILLY_PADS_PLACED = registerKey("mire_lilly_pads_placed");
    public static final ResourceKey<PlacedFeature> MIRE_SHORT_GRASS_PLACED = registerKey("mire_short_grass_placed");
    public static final ResourceKey<PlacedFeature> ROOT_TENTACLES_PLACED = registerKey("root_tentacles_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configured = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(MIRE_LILLY_PADS_PLACED, new PlacedFeature(configured.getOrThrow(ModConfiguredFeatures.MIRE_LILLY_PADS),
                List.of(
                        RarityFilter.onAverageOnceEvery(4),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )));

        context.register(MIRE_SHORT_GRASS_PLACED, new PlacedFeature(configured.getOrThrow(ModConfiguredFeatures.MIRE_SHORT_GRASS),
                List.of(
                        CountPlacement.of(5),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )));

        context.register(GHOST_WILLOW_PLACED, new PlacedFeature(configured.getOrThrow(ModConfiguredFeatures.GHOST_WILLOW_TREE),
                List.of(
                        PlacementUtils.countExtra(12, 0.5f, 5),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )));

        context.register(WHISTLING_REEDS_PLACED, new PlacedFeature(
                configured.getOrThrow(ModConfiguredFeatures.WHISTLING_REEDS_PATCH),
                List.of(
                        CountPlacement.of(8),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        BiomeFilter.biome()
                )));

        context.register(MIRE_LAKE_PLACED, new PlacedFeature(
                configured.getOrThrow(ModConfiguredFeatures.MIRE_LAKE),
                List.of(
                        RarityFilter.onAverageOnceEvery(15),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(60), VerticalAnchor.absolute(80)),
                        BiomeFilter.biome()
                )));

        context.register(WET_MUD_LAKE_PLACED, new PlacedFeature(
                configured.getOrThrow(ModConfiguredFeatures.WET_MUD_LAKE),
                List.of(
                        RarityFilter.onAverageOnceEvery(12),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(60), VerticalAnchor.absolute(80)),
                        BiomeFilter.biome()
                )));

        context.register(TALL_TENTACLE_GRASS_PLACED, new PlacedFeature(
                configured.getOrThrow(ModConfiguredFeatures.TALL_TENTACLE_GRASS_PATCH),
                List.of(
                        CountPlacement.of(4),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )));

        context.register(ROOT_TENTACLES_PLACED, new PlacedFeature(
                configured.getOrThrow(ModConfiguredFeatures.ROOT_TENTACLES_PATCH),
                List.of(
                        CountPlacement.of(3),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(WhatLurksBetween.MOD_ID, name));
    }
}