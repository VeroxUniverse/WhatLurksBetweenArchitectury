package net.veroxuniverse.what_lurks_between.worldgen;

import net.minecraft.resources.ResourceLocation;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

public class ModTerrablender {
    public static void register() {
        Regions.register(new ModMireRegion(ResourceLocation.fromNamespaceAndPath(WhatLurksBetween.MOD_ID, "mire_region"), 2));

        try {
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, WhatLurksBetween.MOD_ID, ModSurfaceRules.makeRules());
        } catch (Exception e) {
        }
    }
}