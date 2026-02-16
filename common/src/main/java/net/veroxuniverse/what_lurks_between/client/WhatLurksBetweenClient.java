package net.veroxuniverse.what_lurks_between.client;

import dev.architectury.networking.NetworkManager;
import net.veroxuniverse.what_lurks_between.network.SanityNetworking;

public class WhatLurksBetweenClient {

    public static void initClient() {
        SanityHudRenderer.init();

        NetworkManager.registerReceiver(NetworkManager.Side.S2C, SanityNetworking.TYPE, SanityNetworking.CODEC, (payload, context) -> {
            context.queue(() -> {
                ClientSanityData.setClientSanity(payload.value());
            });
        });

    }
}