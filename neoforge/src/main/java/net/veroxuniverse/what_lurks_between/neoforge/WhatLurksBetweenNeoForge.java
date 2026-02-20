package net.veroxuniverse.what_lurks_between.neoforge;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.client.WhatLurksBetweenClient;
import net.veroxuniverse.what_lurks_between.config.SanityConfig;
import net.veroxuniverse.what_lurks_between.registry.ModMobEffects;

@Mod(WhatLurksBetween.MOD_ID)
public final class WhatLurksBetweenNeoForge {
    public WhatLurksBetweenNeoForge(IEventBus modEventBus) {
        WhatLurksBetween.init();
    }

}
