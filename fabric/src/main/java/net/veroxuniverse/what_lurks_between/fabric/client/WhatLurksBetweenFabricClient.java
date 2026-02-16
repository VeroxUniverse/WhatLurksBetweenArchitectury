package net.veroxuniverse.what_lurks_between.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.veroxuniverse.what_lurks_between.client.WhatLurksBetweenClient;

public final class WhatLurksBetweenFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        WhatLurksBetweenClient.initClient();
    }
}
