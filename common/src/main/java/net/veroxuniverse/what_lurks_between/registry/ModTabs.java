package net.veroxuniverse.what_lurks_between.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(WhatLurksBetween.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> ITEMS_TAB = TABS.register(
            "items_tab",
            () -> CreativeTabRegistry.create(
                    Component.translatable("itemGroup." + WhatLurksBetween.MOD_ID + ".items"),
                    () -> new ItemStack(ModItems.UNLIT_TORCH.get())
            )
    );

    public static final RegistrySupplier<CreativeModeTab> BLOCKS_TAB = TABS.register(
            "blocks_tab",
            () -> CreativeTabRegistry.create(
                    Component.translatable("itemGroup." + WhatLurksBetween.MOD_ID + ".blocks"),
                    () -> new ItemStack(ModBlocks.MIRE_ROCK.get())
            )
    );

    public static final RegistrySupplier<CreativeModeTab> MISC_TAB = TABS.register(
            "misc_tab",
            () -> CreativeTabRegistry.create(
                    Component.translatable("itemGroup." + WhatLurksBetween.MOD_ID + ".misc"),
                    () -> new ItemStack(Items.ECHO_SHARD)
            )
    );

    public static void register() {
        TABS.register();
    }
}