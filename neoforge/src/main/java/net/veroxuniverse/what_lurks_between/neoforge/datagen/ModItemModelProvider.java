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
        simpleItem("void_pearl");
        simpleItem("wet_mire_mud_bucket");

        simpleItem("cultist_robe_helmet");
        simpleItem("cultist_robe_chestplate");
        simpleItem("cultist_robe_leggings");
        simpleItem("cultist_robe_boots");

        simpleItem("diving_gear_helmet");
        simpleItem("diving_gear_chestplate");
        simpleItem("diving_gear_leggings");
        simpleItem("diving_gear_boots");

    }

    private void simpleItem(String name) {
        withExistingParent(name, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + name));
    }

    private void simpleBlockItem(String name, String texturePath) {
        withExistingParent(name, mcLoc("item/generated"))
                .texture("layer0", modLoc(texturePath));
    }
}