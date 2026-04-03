package net.veroxuniverse.what_lurks_between.registry;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(WhatLurksBetween.MOD_ID, Registries.MOB_EFFECT);



    private ModMobEffects() {}

    public static void register() {
        MOB_EFFECTS.register();
    }
}