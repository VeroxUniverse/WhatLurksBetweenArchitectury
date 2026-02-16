package net.veroxuniverse.what_lurks_between.network;

import dev.architectury.networking.NetworkManager;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;

public class SanityNetworking {
    public static final ResourceLocation SANITY_PACKET_ID = ResourceLocation.fromNamespaceAndPath(WhatLurksBetween.MOD_ID, "sanity_sync");

    public static final CustomPacketPayload.Type<SanitySyncPayload> TYPE = new CustomPacketPayload.Type<>(SANITY_PACKET_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, SanitySyncPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, SanitySyncPayload::value,
            SanitySyncPayload::new
    );

    public record SanitySyncPayload(float value) implements CustomPacketPayload {
        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public static void syncToClient(Player player, float value) {
        if (player instanceof ServerPlayer serverPlayer) {
            NetworkManager.sendToPlayer(serverPlayer, new SanitySyncPayload(value));
        }
    }
}