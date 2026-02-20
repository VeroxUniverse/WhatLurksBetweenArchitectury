package net.veroxuniverse.what_lurks_between.neoforge.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, WhatLurksBetween.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("unlit_torch", mcLoc("item/generated"))
                .texture("layer0", modLoc("block/unlit_torch"));

        withExistingParent("unlit_lantern", mcLoc("item/generated"))
                .texture("layer0", modLoc("item/unlit_lantern"));
    }
}