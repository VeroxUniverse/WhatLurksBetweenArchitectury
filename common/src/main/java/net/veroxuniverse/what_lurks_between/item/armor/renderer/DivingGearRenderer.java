package net.veroxuniverse.what_lurks_between.item.armor.renderer;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import mod.azure.azurelib.common.render.layer.AzAutoGlowingLayer;
import net.minecraft.resources.ResourceLocation;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

public class DivingGearRenderer extends AzArmorRenderer {

    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            WhatLurksBetween.MOD_ID,
            "geo/armor/diving_gear.geo.json"
    );

    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            WhatLurksBetween.MOD_ID,
            "textures/models/diving_gear.png"
    );

    public DivingGearRenderer() {
        super(
                AzArmorRendererConfig.builder(GEO, TEX)
                        .addRenderLayer(new AzAutoGlowingLayer<>())
                        .build()
        );
    }
}