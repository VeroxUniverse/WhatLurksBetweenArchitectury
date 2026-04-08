package net.veroxuniverse.what_lurks_between.neoforge.datagen;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.veroxuniverse.what_lurks_between.worldgen.ModConfiguredFeatures;
import net.veroxuniverse.what_lurks_between.worldgen.ModPlacedFeatures;

public class ModWorldgenProvider {

    public static void bootstrapConfigured(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        ModConfiguredFeatures.bootstrap(context);
    }

    public static void bootstrapPlaced(BootstrapContext<PlacedFeature> context) {
        ModPlacedFeatures.bootstrap(context);
    }
}