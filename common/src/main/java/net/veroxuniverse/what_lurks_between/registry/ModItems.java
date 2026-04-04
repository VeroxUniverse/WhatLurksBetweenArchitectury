package net.veroxuniverse.what_lurks_between.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.item.WetMireMudBucketItem;
import net.veroxuniverse.what_lurks_between.item.armor.CultistRobeArmorItem;
import net.veroxuniverse.what_lurks_between.item.armor.DivingGearArmorItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(WhatLurksBetween.MOD_ID, Registries.ITEM);

    static Item.Properties CultistProperties = new Item.Properties().durability(490).stacksTo(1).rarity(Rarity.UNCOMMON).arch$tab(ModTabs.ITEMS_TAB);

    public static final RegistrySupplier<Item> WET_MIRE_MUD_BUCKET = ITEMS.register("wet_mire_mud_bucket",
            () -> new WetMireMudBucketItem(ModBlocks.WET_MIRE_MUD.get(),
                    new Item.Properties().stacksTo(1).arch$tab(ModTabs.ITEMS_TAB)));

    public static final RegistrySupplier<Item> VOID_PEARL = ITEMS.register("void_pearl",
            () -> new Item(new Item.Properties().arch$tab(ModTabs.ITEMS_TAB)));

    public static final RegistrySupplier<Item> CULTIST_ROBE_HELMET = ITEMS.register("cultist_robe_helmet",
            () -> new CultistRobeArmorItem(ModArmorMaterials.CULTIST_ROBE, ArmorItem.Type.HELMET, CultistProperties));
    public static final RegistrySupplier<Item> CULTIST_ROBE_CHESTPLATE = ITEMS.register("cultist_robe_chestplate",
            () -> new CultistRobeArmorItem(ModArmorMaterials.CULTIST_ROBE,ArmorItem.Type.CHESTPLATE, CultistProperties));
    public static final RegistrySupplier<Item> CULTIST_ROBE_LEGGINGS = ITEMS.register("cultist_robe_leggings",
            () -> new CultistRobeArmorItem(ModArmorMaterials.CULTIST_ROBE,ArmorItem.Type.LEGGINGS, CultistProperties));
    public static final RegistrySupplier<Item> CULTIST_ROBE_BOOTS = ITEMS.register("cultist_robe_boots",
            () -> new CultistRobeArmorItem(ModArmorMaterials.CULTIST_ROBE,ArmorItem.Type.BOOTS, CultistProperties));

    public static final RegistrySupplier<Item> DIVING_GEAR_HELMET = ITEMS.register("diving_gear_helmet",
            () -> new DivingGearArmorItem(ModArmorMaterials.DIVING_GEAR, ArmorItem.Type.HELMET, CultistProperties));
    public static final RegistrySupplier<Item> DIVING_GEAR_CHESTPLATE = ITEMS.register("diving_gear_chestplate",
            () -> new DivingGearArmorItem(ModArmorMaterials.DIVING_GEAR,ArmorItem.Type.CHESTPLATE, CultistProperties));
    public static final RegistrySupplier<Item> DIVING_GEAR_LEGGINGS = ITEMS.register("diving_gear_leggings",
            () -> new DivingGearArmorItem(ModArmorMaterials.DIVING_GEAR,ArmorItem.Type.LEGGINGS, CultistProperties));
    public static final RegistrySupplier<Item> DIVING_GEAR_BOOTS = ITEMS.register("diving_gear_boots",
            () -> new DivingGearArmorItem(ModArmorMaterials.DIVING_GEAR,ArmorItem.Type.BOOTS, CultistProperties));


    public static void register() {
        ITEMS.register();
    }
}