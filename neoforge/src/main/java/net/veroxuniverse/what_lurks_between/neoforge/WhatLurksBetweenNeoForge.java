package net.veroxuniverse.what_lurks_between.neoforge;

import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.registry.ModAttributes;

@Mod(WhatLurksBetween.MOD_ID)
public final class WhatLurksBetweenNeoForge {
    public WhatLurksBetweenNeoForge(IEventBus modEventBus) {
        ModAttributes.register();
        WhatLurksBetween.init();
    }

}
