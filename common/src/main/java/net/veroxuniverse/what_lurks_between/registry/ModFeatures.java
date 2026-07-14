package net.veroxuniverse.what_lurks_between.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.worldgen.feature.MireLakeFeature;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(WhatLurksBetween.MOD_ID, Registries.FEATURE);

    public static final RegistrySupplier<MireLakeFeature> MIRE_LAKE_FEATURE = FEATURES.register(
            "mire_lake_feature",
            () -> new MireLakeFeature(LakeFeature.Configuration.CODEC)
    );

    public static void register() {
        FEATURES.register();
    }
}