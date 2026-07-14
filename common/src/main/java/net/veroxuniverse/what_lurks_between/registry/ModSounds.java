package net.veroxuniverse.what_lurks_between.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(WhatLurksBetween.MOD_ID, Registries.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> CREEPY_WHISPER = SOUNDS.register("creepy_whisper",
            () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(WhatLurksBetween.MOD_ID, "creepy_whisper")));

    public static void register() {
        SOUNDS.register();
    }
}