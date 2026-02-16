package net.veroxuniverse.what_lurks_between.sanity;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.veroxuniverse.what_lurks_between.api.SanityAPI;
import net.veroxuniverse.what_lurks_between.network.SanityNetworking;

public class SanityEventHandler {

    public static void init() {
        TickEvent.PLAYER_POST.register(SanityEventHandler::onPlayerTick);

        LifecycleEvent.SERVER_STARTED.register(server -> {
            SanitySavedData.get(server);
            System.out.println("[WhatLurksBetween] Sanity data loaded on server start.");
        });

        LifecycleEvent.SERVER_LEVEL_SAVE.register(level -> {
            if (level.getServer() != null) {
                SanitySavedData.get(level.getServer()).setDirty();
                System.out.println("[WhatLurksBetween] Sanity data marked dirty for saving.");
            }
        });

        dev.architectury.event.events.common.PlayerEvent.PLAYER_JOIN.register(player -> {
            if (player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                float savedValue = SanityAPI.getSanity(serverPlayer);
                System.out.println("[WhatLurksBetween] Player joined. Syncing saved sanity: " + savedValue);
                SanityNetworking.syncToClient(serverPlayer, savedValue);
            }
        });
    }

    private static final String[] SLEEP_MESSAGE_KEYS = {
            "message.what_lurks_between.sleep_1",
            "message.what_lurks_between.sleep_2",
            "message.what_lurks_between.sleep_3",
            "message.what_lurks_between.sleep_4",
            "message.what_lurks_between.sleep_5"
    };

    private static void onPlayerTick(Player player) {
        if (player.level().isClientSide) return;

        if (player.isSleeping() && player.getSleepTimer() >= 99) {
            float current = SanityAPI.getSanity(player);
            if (current < 100f) {
                SanityAPI.modifySanity(player, 100f - current);

                String randomKey = SLEEP_MESSAGE_KEYS[player.getRandom().nextInt(SLEEP_MESSAGE_KEYS.length)];

                player.displayClientMessage(
                        Component.translatable(randomKey).withStyle(net.minecraft.ChatFormatting.GREEN),
                        true
                );
            }
        }

        if (player.tickCount % 20 == 0) {
            handleSanityLogic(player);
            SanityEffectManager.tick(player);
        }
    }

    private static void handleSanityLogic(Player player) {
        int light = player.level().getMaxLocalRawBrightness(player.blockPosition());

        if (light < 4) {
            SanityAPI.modifySanity(player, -0.5f);
        } else if (light > 12) {
            SanityAPI.modifySanity(player, 0.2f);
        }

        // Debug Actionbar
        player.displayClientMessage(
                net.minecraft.network.chat.Component.literal(
                        "§eSanity: §f" + String.format("%.1f", SanityAPI.getSanity(player)) +
                                " §8| §bLight: §f" + light
                ),
                true
        );
    }
}