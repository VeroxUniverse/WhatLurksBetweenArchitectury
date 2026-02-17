package net.veroxuniverse.what_lurks_between;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.veroxuniverse.what_lurks_between.config.SanityConfig;
import net.veroxuniverse.what_lurks_between.network.SanityNetworking;
import net.veroxuniverse.what_lurks_between.registry.ModAttributes;
import net.veroxuniverse.what_lurks_between.sanity.SanityEventHandler;

public final class WhatLurksBetween {
    public static final String MOD_ID = "what_lurks_between";

    public static void init() {
        SanityConfig.init();
        ModAttributes.register();
        SanityNetworking.register();

        EntityAttributeRegistry.register(
                () -> EntityType.PLAYER,
                () -> Player.createAttributes().add(ModAttributes.SANITY_RESISTANCE)
        );

        SanityEventHandler.init();
    }
}
