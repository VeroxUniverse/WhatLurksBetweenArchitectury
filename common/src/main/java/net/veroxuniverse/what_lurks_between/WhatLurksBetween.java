package net.veroxuniverse.what_lurks_between;

import com.google.common.collect.ImmutableMap;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import mod.azure.azurelib.common.animation.cache.AzIdentityRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.veroxuniverse.veroxlib.sanity.SanityConditionManager;
import net.veroxuniverse.what_lurks_between.client.WhatLurksBetweenClient;
import net.veroxuniverse.what_lurks_between.config.ModConfig;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;
import net.veroxuniverse.what_lurks_between.registry.ModItems;
import net.veroxuniverse.what_lurks_between.registry.ModMobEffects;
import net.veroxuniverse.what_lurks_between.registry.ModTabs;
import net.veroxuniverse.what_lurks_between.util.sanity.BiomeSanityProtectionCondition;

import java.util.HashMap;
import java.util.Map;

public final class WhatLurksBetween {
    public static final String MOD_ID = "what_lurks_between";

    public static void init() {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        ModConfig.INSTANCE = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        ModMobEffects.register();
        //ModAttributes.register();
        ModBlocks.register();
        ModItems.register();
        ModTabs.register();
        SanityConditionManager.registerCondition(new BiomeSanityProtectionCondition());
    }

    public static void initAzIdentityRegistry() {
        AzIdentityRegistry.register(
                ModItems.CULTIST_ROBE_HELMET.get(),
                ModItems.CULTIST_ROBE_CHESTPLATE.get(),
                ModItems.CULTIST_ROBE_LEGGINGS.get(),
                ModItems.CULTIST_ROBE_BOOTS.get(),
                ModItems.DIVING_GEAR_HELMET.get(),
                ModItems.DIVING_GEAR_CHESTPLATE.get(),
                ModItems.DIVING_GEAR_LEGGINGS.get(),
                ModItems.DIVING_GEAR_BOOTS.get()
        );
    }

    public static ResourceLocation modResource(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public static void initClient() {
        WhatLurksBetweenClient.initClientAzRenders();
    }

}
