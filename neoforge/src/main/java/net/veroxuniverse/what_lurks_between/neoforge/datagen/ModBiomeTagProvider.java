package net.veroxuniverse.what_lurks_between.neoforge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.registry.ModBiomes;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagProvider extends BiomeTagsProvider {

    private static final TagKey<Biome> IS_HORROR_BIOME = TagKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath("veroxlib", "is_horror_biome"));

    public ModBiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, WhatLurksBetween.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BiomeTags.HAS_SWAMP_HUT).addOptional(ModBiomes.WHISPERING_MIRE.location());
        tag(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS).addOptional(ModBiomes.WHISPERING_MIRE.location());

        tag(TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(WhatLurksBetween.MOD_ID, "spawns_mire_variant_frogs")))
                .addOptional(ModBiomes.WHISPERING_MIRE.location());

        tag(IS_HORROR_BIOME).addOptional(ModBiomes.WHISPERING_MIRE.location());
    }
}