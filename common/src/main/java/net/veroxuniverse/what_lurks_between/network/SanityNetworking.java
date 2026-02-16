package net.veroxuniverse.what_lurks_between.network;

import dev.architectury.networking.NetworkManager;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

public class SanityNetworking {
    public static final ResourceLocation SANITY_PACKET_ID = ResourceLocation.fromNamespaceAndPath(WhatLurksBetween.MOD_ID, "sanity_sync");

    public record SanitySyncPayload(float value) implements CustomPacketPayload {
        public static final Type<SanitySyncPayload> TYPE = new Type<>(SANITY_PACKET_ID);

        public static final StreamCodec<RegistryFriendlyByteBuf, SanitySyncPayload> CODEC = StreamCodec.composite(
                ByteBufCodecs.FLOAT, SanitySyncPayload::value,
                SanitySyncPayload::new
        );

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public static void init() {
        try {
            NetworkManager.registerS2CPayloadType(SanitySyncPayload.TYPE, SanitySyncPayload.CODEC);
        } catch (IllegalArgumentException e) {
            System.out.println("[WhatLurksBetween] Sanity Packet already known, skipping registration.");
        }
    }

    public static void syncToClient(net.minecraft.world.entity.player.Player player, float value) {
        if (player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
            init();
            NetworkManager.sendToPlayer(serverPlayer, new SanitySyncPayload(value));
        }
    }
}