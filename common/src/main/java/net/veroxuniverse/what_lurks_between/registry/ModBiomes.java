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

        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 100, 1, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 95, 1, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 100, 1, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 70, 1, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.BOGGED, 30, 1, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 10, 1, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 5, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 1, 1, 1));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FROG, 10, 2, 5));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 12, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 10, 2, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 10, 1, 3));

        spawnBuilder.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.BAT, 10, 8, 8));

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
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genSettings.build())
                .build();
    }
}