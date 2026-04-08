package net.veroxuniverse.what_lurks_between.registry;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower GHOST_WILLOW = new TreeGrower(
            "ghost_willow",
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE,
                    ResourceLocation.fromNamespaceAndPath(WhatLurksBetween.MOD_ID, "ghost_willow_tree"))),
            Optional.empty()
    );
}