package net.veroxuniverse.what_lurks_between.sanity;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SanitySavedData extends SavedData {
    private final Map<UUID, SanityData> playerSanity = new HashMap<>();

    public static final Factory<SanitySavedData> FACTORY = new Factory<>(
            SanitySavedData::new,
            SanitySavedData::load,
            null
    );

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        ListTag list = new ListTag();
        playerSanity.forEach((uuid, data) -> {
            CompoundTag entry = new CompoundTag();
            entry.putUUID("uuid", uuid);
            entry.putFloat("value", data.value());
            list.add(entry);
        });
        tag.put("player_sanity", list);
        System.out.println("[WhatLurksBetween] SAVING Sanity Data: " + playerSanity.size() + " entries.");
        return tag;
    }

    public static SanitySavedData load(CompoundTag tag, HolderLookup.Provider provider) {
        SanitySavedData data = new SanitySavedData();
        ListTag list = tag.getList("player_sanity", 10);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag entry = list.getCompound(i);
            data.playerSanity.put(entry.getUUID("uuid"), new SanityData(entry.getFloat("value")));
        }
        System.out.println("[WhatLurksBetween] LOADING Sanity Data: " + data.playerSanity.size() + " entries.");
        return data;
    }

    public static SanitySavedData get(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(FACTORY, "what_lurks_between_sanity");
    }

    public Map<UUID, SanityData> getMap() { return playerSanity; }
}