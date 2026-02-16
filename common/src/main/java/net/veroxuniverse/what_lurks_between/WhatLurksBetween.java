package net.veroxuniverse.what_lurks_between;

import net.veroxuniverse.what_lurks_between.network.SanityNetworking;
import net.veroxuniverse.what_lurks_between.sanity.SanityEventHandler;

public final class WhatLurksBetween {
    public static final String MOD_ID = "what_lurks_between";

    public static void init() {
        SanityNetworking.init();
        SanityEventHandler.init();
    }
}
