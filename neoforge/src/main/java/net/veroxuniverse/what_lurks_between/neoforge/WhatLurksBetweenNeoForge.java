package net.veroxuniverse.what_lurks_between.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.client.WhatLurksBetweenClient;

@Mod(WhatLurksBetween.MOD_ID)
public final class WhatLurksBetweenNeoForge {
    public WhatLurksBetweenNeoForge(IEventBus modEventBus) {
        WhatLurksBetween.init();
    }

}
