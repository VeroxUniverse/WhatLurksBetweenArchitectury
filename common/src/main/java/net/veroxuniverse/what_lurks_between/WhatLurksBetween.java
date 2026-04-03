package net.veroxuniverse.what_lurks_between;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.veroxuniverse.what_lurks_between.config.ModConfig;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;
import net.veroxuniverse.what_lurks_between.registry.ModItems;
import net.veroxuniverse.what_lurks_between.registry.ModMobEffects;
import net.veroxuniverse.what_lurks_between.registry.ModTabs;

public final class WhatLurksBetween {
    public static final String MOD_ID = "what_lurks_between";

    public static void init() {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        ModConfig.INSTANCE = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        ModMobEffects.register();
        //ModAttributes.register();
        ModBlocks.register();
        ModItems.register();
        ModTabs.register();
    }
}
