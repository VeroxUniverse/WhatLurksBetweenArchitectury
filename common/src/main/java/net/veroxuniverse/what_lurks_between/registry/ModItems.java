package net.veroxuniverse.what_lurks_between.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.item.WetMireMudBucketItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(WhatLurksBetween.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> WET_MIRE_MUD_BUCKET = ITEMS.register("wet_mire_mud_bucket",
            () -> new WetMireMudBucketItem(ModBlocks.WET_MIRE_MUD.get(),
                    new Item.Properties().stacksTo(1).arch$tab(ModTabs.ITEMS_TAB)));

    public static final RegistrySupplier<Item> VOID_PEARL = ITEMS.register("void_pearl",
            () -> new Item(new Item.Properties().arch$tab(ModTabs.ITEMS_TAB)));

    public static void register() {
        ITEMS.register();
    }
}