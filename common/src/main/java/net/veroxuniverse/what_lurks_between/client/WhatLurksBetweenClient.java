package net.veroxuniverse.what_lurks_between.client;

import mod.azure.azurelib.common.render.armor.AzArmorRendererRegistry;
import net.minecraft.client.Camera;
import net.veroxuniverse.what_lurks_between.item.armor.renderer.CultistRobeRenderer;
import net.veroxuniverse.what_lurks_between.item.armor.renderer.DivingGearRenderer;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;
import net.veroxuniverse.what_lurks_between.registry.ModItems;

public class WhatLurksBetweenClient {

    public static boolean isCameraInMud(Camera camera) {
        if (camera.getEntity() == null) return false;
        return camera.getEntity().level().getBlockState(camera.getBlockPosition()).is(ModBlocks.WET_MIRE_MUD.get());
    }

    public static float getMudFogRed() { return 0.20F; }
    public static float getMudFogGreen() { return 0.12F; }
    public static float getMudFogBlue() { return 0.02F; }

    public static void initClientAzRenders() {
        AzArmorRendererRegistry.register(
                CultistRobeRenderer::new,
                ModItems.CULTIST_ROBE_HELMET.get(),
                ModItems.CULTIST_ROBE_CHESTPLATE.get(),
                ModItems.CULTIST_ROBE_LEGGINGS.get(),
                ModItems.CULTIST_ROBE_BOOTS.get()
        );

        AzArmorRendererRegistry.register(
                DivingGearRenderer::new,
                ModItems.DIVING_GEAR_HELMET.get(),
                ModItems.DIVING_GEAR_CHESTPLATE.get(),
                ModItems.DIVING_GEAR_LEGGINGS.get(),
                ModItems.DIVING_GEAR_BOOTS.get()
        );

    }
}