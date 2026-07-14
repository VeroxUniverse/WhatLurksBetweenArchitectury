package net.veroxuniverse.what_lurks_between.registry;

import net.veroxuniverse.veroxlib.sanity.SanityEffectManager;
import net.veroxuniverse.what_lurks_between.sanity.CreepyWhisperEffect;

public class ModSanityEffects {

    public static void register() {
        SanityEffectManager.register(new CreepyWhisperEffect());
    }
}