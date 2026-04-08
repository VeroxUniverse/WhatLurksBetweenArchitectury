package net.veroxuniverse.what_lurks_between.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;

public final class WhatLurksBetweenFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        WhatLurksBetween.init();

        StrippableBlockRegistry.register(ModBlocks.GHOST_WILLOW_LOG.get(), ModBlocks.STRIPPED_GHOST_WILLOW_LOG.get());
        StrippableBlockRegistry.register(ModBlocks.GHOST_WILLOW_WOOD.get(), ModBlocks.STRIPPED_GHOST_WILLOW_WOOD.get());

        var registry = FlammableBlockRegistry.getDefaultInstance();

        registerFabricFlammable(ModBlocks.GHOST_WILLOW);
        registry.add(ModBlocks.GHOST_WILLOW_LOG.get(), 5, 5);
        registry.add(ModBlocks.STRIPPED_GHOST_WILLOW_LOG.get(), 5, 5);
        registry.add(ModBlocks.GHOST_WILLOW_WOOD.get(), 5, 5);
        registry.add(ModBlocks.STRIPPED_GHOST_WILLOW_WOOD.get(), 5, 5);
        registry.add(ModBlocks.GHOST_WILLOW_PLANKS.get(), 5, 20);
        registry.add(ModBlocks.GHOST_WILLOW_LEAVES.get(), 30, 60);
        registry.add(ModBlocks.GHOST_WILLOW_DOOR.get(), 5, 20);
        registry.add(ModBlocks.GHOST_WILLOW_TRAPDOOR.get(), 5, 20);
    }

    private void registerFabricFlammable(ModBlocks.WoodSet set) {
        var registry = FlammableBlockRegistry.getDefaultInstance();
        registry.add(set.planks().get(), 5, 20);
        registry.add(set.stairs().get(), 5, 20);
        registry.add(set.slab().get(), 5, 20);
        registry.add(set.fence().get(), 5, 20);
        registry.add(set.fenceGate().get(), 5, 20);
    }
}
