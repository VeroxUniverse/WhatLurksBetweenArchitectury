package net.veroxuniverse.what_lurks_between.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.FrogVariant;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

public class ModFrogVariants {
    public static final DeferredRegister<FrogVariant> FROG_VARIANTS =
            DeferredRegister.create(WhatLurksBetween.MOD_ID, Registries.FROG_VARIANT);

    public static final RegistrySupplier<FrogVariant> MIRE = FROG_VARIANTS.register("mire",
            () -> new FrogVariant(ResourceLocation.fromNamespaceAndPath(WhatLurksBetween.MOD_ID, "textures/entity/frog/mire_frog.png")));

    public static void register() {
        FROG_VARIANTS.register();
    }
}
