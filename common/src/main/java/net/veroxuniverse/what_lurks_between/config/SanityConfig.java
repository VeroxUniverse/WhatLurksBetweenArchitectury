package net.veroxuniverse.what_lurks_between.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

@Config(name = WhatLurksBetween.MOD_ID)
public class SanityConfig implements ConfigData {

    @ConfigEntry.Gui.Excluded
    public static SanityConfig INSTANCE;

    @ConfigEntry.Category("light_logic")
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 15)
    public int darknessThreshold = 6;

    @ConfigEntry.Category("light_logic")
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 15)
    public int brightnessThreshold = 10;

    @ConfigEntry.Category("light_logic")
    @ConfigEntry.Gui.Tooltip
    public float sanityReduction = -0.5f;

    @ConfigEntry.Category("light_logic")
    @ConfigEntry.Gui.Tooltip
    public float sanityGain = 0.2f;

    @ConfigEntry.Category("sleep")
    @ConfigEntry.Gui.Tooltip
    public boolean sleepResetsCompletely = false;

    @ConfigEntry.Category("sleep")
    @ConfigEntry.Gui.Tooltip
    public float sanityGainFromSleep = 30.0f;

    public static void init() {
        AutoConfig.register(SanityConfig.class, GsonConfigSerializer::new);
        INSTANCE = AutoConfig.getConfigHolder(SanityConfig.class).getConfig();
    }

    public static void save() {
        AutoConfig.getConfigHolder(SanityConfig.class).save();
    }
}