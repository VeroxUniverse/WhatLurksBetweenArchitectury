package net.veroxuniverse.what_lurks_between;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.veroxuniverse.what_lurks_between.config.SanityConfig;
import net.veroxuniverse.what_lurks_between.network.SanityNetworking;
import net.veroxuniverse.what_lurks_between.registry.*;
import net.veroxuniverse.what_lurks_between.sanity.SanityEventHandler;

public final class WhatLurksBetween {
    public static final String MOD_ID = "what_lurks_between";

    public static void init() {
        AutoConfig.register(SanityConfig.class, JanksonConfigSerializer::new);
        SanityConfig.INSTANCE = AutoConfig.getConfigHolder(SanityConfig.class).getConfig();
        ModMobEffects.register();
        ModAttributes.register();
        ModBlocks.register();
        ModItems.register();
        ModTabs.register();
        SanityNetworking.register();
        SanityEventHandler.init();
    }
}
