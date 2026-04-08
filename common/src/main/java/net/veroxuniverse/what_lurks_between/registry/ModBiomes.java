package net.veroxuniverse.what_lurks_between.registry;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.worldgen.ModPlacedFeatures;

public class ModBiomes {
    public static final ResourceKey<Biome> WHISPERING_MIRE = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(WhatLurksBetween.MOD_ID, "whispering_mire"));

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(WHISPERING_MIRE, whisperingMire(context));
    }

    public static Biome whisperingMire(BootstrapContext<Biome> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var carvers = context.lookup(Registries.CONFIGURED_CARVER);

        BiomeGenerationSettings.Builder genSettings = new BiomeGenerationSettings.Builder(placedFeatures, carvers);

        genSettings.addFeature(GenerationStep.Decoration.LAKES, placedFeatures.getOrThrow(ModPlacedFeatures.MIRE_LAKE_PLACED));
        genSettings.addFeature(GenerationStep.Decoration.LAKES, placedFeatures.getOrThrow(ModPlacedFeatures.WET_MUD_LAKE_PLACED));

        genSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.GHOST_WILLOW_PLACED));
        genSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.MIRE_LILLY_PADS_PLACED));
        genSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.MIRE_SHORT_GRASS_PLACED));
        genSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.WHISTLING_REEDS_PLACED));
        genSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.TALL_TENTACLE_GRASS_PLACED));
        genSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.ROOT_TENTACLES_PLACED));

        genSettings.addCarver(GenerationStep.Carving.AIR, carvers.getOrThrow(net.minecraft.data.worldgen.Carvers.CAVE));

        MobSpawnSettings spawnSettings = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FROG, 20, 3, 5))
                .addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.BAT, 10, 8, 8))
                .build();

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.5F)
                .downfall(0.8F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d472d)
                        .waterFogColor(0x1a1f12)
                        .fogColor(0x2b2d2f)
                        .skyColor(0x212121)
                        .grassColorOverride(0x444433)
                        .foliageColorOverride(0x444433)
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.015F))
                        .build())
                .mobSpawnSettings(spawnSettings)
                .generationSettings(genSettings.build())
                .build();
    }
}