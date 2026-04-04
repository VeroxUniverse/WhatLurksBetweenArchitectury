package net.veroxuniverse.what_lurks_between.item.armor.renderer;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

public class CultistRobeRenderer extends AzArmorRenderer {

    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            WhatLurksBetween.MOD_ID,
            "geo/armor/cultist_robe.geo.json"
    );

    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            WhatLurksBetween.MOD_ID,
            "textures/models/cultist_robe.png"
    );

    public CultistRobeRenderer() {
        super(
                AzArmorRendererConfig.builder(GEO, TEX)
                        .build()
        );
    }
}
