package net.veroxuniverse.what_lurks_between.neoforge.client;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.registry.ModBiomes;

@EventBusSubscriber(modid = WhatLurksBetween.MOD_ID, value = Dist.CLIENT)
public class ClientFogHandler {

    @SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event) {
        Entity entity = event.getCamera().getEntity();
        Level level = entity.level();

        Holder<Biome> biome = level.getBiome(entity.blockPosition());
        if (biome.is(ModBiomes.WHISPERING_MIRE)) {

            boolean isNight = level.getDayTime() % 24000 > 13000;

            if (isNight) {
                event.setNearPlaneDistance(0.0F);
                event.setFarPlaneDistance(5.0F);
                event.setCanceled(true);
            } else {
                event.setNearPlaneDistance(2.0F);
                event.setFarPlaneDistance(15.0F);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onFogColor(ViewportEvent.ComputeFogColor event) {
        Entity entity = event.getCamera().getEntity();
        if (entity.level().getBiome(entity.blockPosition()).is(ModBiomes.WHISPERING_MIRE)) {
            event.setRed(0.1F);
            event.setGreen(0.12F);
            event.setBlue(0.1F);
        }
    }
}
