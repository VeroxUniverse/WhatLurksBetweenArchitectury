package net.veroxuniverse.what_lurks_between.neoforge;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

@EventBusSubscriber(modid = WhatLurksBetween.MOD_ID)
public class ModAttributesNeoForge {

    @SubscribeEvent
    public static void onEntityAttributeModification(EntityAttributeModificationEvent event) {
        Holder<Attribute> sanityResistance = BuiltInRegistries.ATTRIBUTE
                .getHolderOrThrow(ResourceKey.create(
                        Registries.ATTRIBUTE,
                        ResourceLocation.fromNamespaceAndPath(WhatLurksBetween.MOD_ID, "sanity_resistance")
                ));

        event.add(EntityType.PLAYER, sanityResistance);
    }
}