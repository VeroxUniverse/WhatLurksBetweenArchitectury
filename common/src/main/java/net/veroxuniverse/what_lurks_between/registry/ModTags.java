package net.veroxuniverse.what_lurks_between.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

public class ModTags {

    public static final TagKey<Block> LIGHT_SOURCES = TagKey.create(
            Registries.BLOCK,
            ResourceLocation.fromNamespaceAndPath(WhatLurksBetween.MOD_ID, "light_sources")
    );

    public static final TagKey<Block> MIRE_PLANTABLE_ON = TagKey.create(
            Registries.BLOCK,
            ResourceLocation.fromNamespaceAndPath(WhatLurksBetween.MOD_ID, "mire_plantable_on")
    );
}