package net.veroxuniverse.what_lurks_between.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(WhatLurksBetween.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> MAIN_TAB = TABS.register("main_tab", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("itemGroup." + WhatLurksBetween.MOD_ID + ".main"))
            .icon(() -> new ItemStack(ModBlocks.UNLIT_LANTERN.get()))
            .displayItems((parameters, output) -> {
                output.accept(ModItems.UNLIT_TORCH.get());
                output.accept(ModBlocks.UNLIT_LANTERN.get());
            })
            .build()
    );

    public static void register() {
        TABS.register();
    }
}