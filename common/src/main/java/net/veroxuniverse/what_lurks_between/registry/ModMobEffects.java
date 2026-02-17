package net.veroxuniverse.what_lurks_between.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(WhatLurksBetween.MOD_ID, Registries.MOB_EFFECT);

    public static final RegistrySupplier<MobEffect> SANITY_PROTECTION = MOB_EFFECTS.register(
            "sanity_protection",
            () -> new SanityProtectionEffect(MobEffectCategory.BENEFICIAL, 0xADD8E6)
    );

    public static void register() {
        MOB_EFFECTS.register();
    }

    private static class SanityProtectionEffect extends MobEffect {
        protected SanityProtectionEffect(MobEffectCategory category, int color) {
            super(category, color);
        }
    }
}