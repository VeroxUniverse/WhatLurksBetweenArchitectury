package net.veroxuniverse.what_lurks_between.client;

import dev.architectury.networking.NetworkManager;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.minecraft.client.renderer.RenderType;
import net.veroxuniverse.what_lurks_between.network.SanityNetworking;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;

public class WhatLurksBetweenClient {
    public static void initClient() {
        SanityHudRenderer.init();
        SanityVignetteRenderer.init();

        RenderTypeRegistry.register(RenderType.cutout(),
                ModBlocks.UNLIT_TORCH.get(),
                ModBlocks.UNLIT_WALL_TORCH.get(),
                ModBlocks.UNLIT_LANTERN.get()
        );

        NetworkManager.registerReceiver(NetworkManager.Side.S2C, SanityNetworking.TYPE, SanityNetworking.CODEC, (payload, context) -> {
            context.queue(() -> {
                ClientSanityData.setClientData(payload.value(), payload.isCultist());
            });
        });
    }
}