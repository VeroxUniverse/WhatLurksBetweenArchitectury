package net.veroxuniverse.what_lurks_between.neoforge.client;

import me.shedaniel.autoconfig.AutoConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.client.WhatLurksBetweenClient;
import net.veroxuniverse.what_lurks_between.config.ModConfig;

@EventBusSubscriber(modid = WhatLurksBetween.MOD_ID, value = Dist.CLIENT)
public class WhatLurksBetweenNeoForgeClient {

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        WhatLurksBetweenClient.initClient();

        ModLoadingContext.get()
                .registerExtensionPoint(
                        IConfigScreenFactory.class,
                        () -> (modContainer, screen) -> AutoConfig.getConfigScreen(ModConfig.class, screen).get()
                );
    }
}