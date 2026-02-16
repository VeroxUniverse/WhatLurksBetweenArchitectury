package net.veroxuniverse.what_lurks_between.client;

import dev.architectury.event.events.client.ClientGuiEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

public class SanityHudRenderer {
    private static final ResourceLocation EYE_TEX = ResourceLocation.fromNamespaceAndPath(WhatLurksBetween.MOD_ID, "textures/gui/sanity_icons.png");

    public static void init() {
        ClientGuiEvent.RENDER_HUD.register((guiGraphics, tickDelta) -> {
            Minecraft mc = Minecraft.getInstance();

            if (mc.player == null || mc.options.hideGui || mc.player.isSpectator()) return;

            float sanity = ClientSanityData.getSanity();

            if (sanity >= 80) return;

            int x = guiGraphics.guiWidth() / 2 - 8;
            int y = guiGraphics.guiHeight() - 32 - 11;

            int u = (sanity < 20) ? 48 : (sanity < 40) ? 32 : (sanity < 60) ? 16 : 0;

            guiGraphics.blit(EYE_TEX, x, y, u, 0, 16, 16, 64, 16);
        });
    }
}