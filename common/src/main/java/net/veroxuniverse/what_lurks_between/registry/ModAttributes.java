package net.veroxuniverse.what_lurks_between.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(WhatLurksBetween.MOD_ID, Registries.ATTRIBUTE);

    public static final RegistrySupplier<Attribute> SANITY_RESISTANCE = ATTRIBUTES.register(
            "sanity_resistance",
            () -> new RangedAttribute("attribute.what_lurks_between.sanity_resistance", 0.0D, 0.0D, 1.0D).setSyncable(true)
    );

    public static void register() {
        ATTRIBUTES.register();
    }
}