package net.veroxuniverse.what_lurks_between.client;

import net.minecraft.client.Camera;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;

public class WhatLurksBetweenClient {

    public static boolean isCameraInMud(Camera camera) {
        if (camera.getEntity() == null) return false;
        return camera.getEntity().level().getBlockState(camera.getBlockPosition()).is(ModBlocks.WET_MIRE_MUD.get());
    }

    public static float getMudFogRed() { return 0.20F; }
    public static float getMudFogGreen() { return 0.12F; }
    public static float getMudFogBlue() { return 0.02F; }

    public static void initClient() {

    }
}