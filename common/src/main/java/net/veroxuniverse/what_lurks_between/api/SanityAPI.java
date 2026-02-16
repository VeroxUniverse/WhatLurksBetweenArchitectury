package net.veroxuniverse.what_lurks_between.api;

import net.minecraft.world.entity.player.Player;
import net.veroxuniverse.what_lurks_between.network.SanityNetworking;
import net.veroxuniverse.what_lurks_between.sanity.SanityData;
import net.veroxuniverse.what_lurks_between.sanity.SanitySavedData;

import java.util.Map;
import java.util.UUID;

public class SanityAPI {

    public static float getSanity(Player player) {
        if (player.getServer() == null) return 100f; // Client-Fallback

        Map<UUID, SanityData> storage = SanitySavedData.get(player.getServer()).getMap();
        return storage.getOrDefault(player.getUUID(), new SanityData(100f)).value();
    }

    public static void modifySanity(Player player, float amount) {
        if (player.level().isClientSide() || player.getServer() == null) return;

        SanitySavedData savedData = SanitySavedData.get(player.getServer());

        UUID uuid = player.getUUID();
        Map<UUID, SanityData> storage = savedData.getMap();
        SanityData oldData = storage.getOrDefault(uuid, new SanityData(100f));
        SanityData newData = oldData.add(amount);

        storage.put(uuid, newData);

        savedData.setDirty();

        SanityNetworking.syncToClient(player, newData.value());
    }
}