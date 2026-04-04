package net.veroxuniverse.what_lurks_between.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

public final class WhatLurksBetweenFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        WhatLurksBetween.initClient();
    }
}
