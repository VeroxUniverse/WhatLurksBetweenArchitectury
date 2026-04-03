package net.veroxuniverse.what_lurks_between.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

@Config(name = WhatLurksBetween.MOD_ID)
public class ModConfig implements ConfigData {

    @ConfigEntry.Gui.Excluded
    public static ModConfig INSTANCE;

}
