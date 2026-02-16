package net.veroxuniverse.what_lurks_between.fabric;

import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.fabricmc.api.ModInitializer;

public final class WhatLurksBetweenFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        WhatLurksBetween.init();
    }
}
