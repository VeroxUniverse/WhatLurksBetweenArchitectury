package net.veroxuniverse.what_lurks_between.neoforge.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;
import net.veroxuniverse.what_lurks_between.registry.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, WhatLurksBetween.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.VOID_PEARL);
        simpleItem(ModItems.WET_MIRE_MUD_BUCKET);

        simpleItem(ModItems.CULTIST_ROBE_HELMET);
        simpleItem(ModItems.CULTIST_ROBE_CHESTPLATE);
        simpleItem(ModItems.CULTIST_ROBE_LEGGINGS);
        simpleItem(ModItems.CULTIST_ROBE_BOOTS);

        simpleItem(ModItems.DIVING_GEAR_HELMET);
        simpleItem(ModItems.DIVING_GEAR_CHESTPLATE);
        simpleItem(ModItems.DIVING_GEAR_LEGGINGS);
        simpleItem(ModItems.DIVING_GEAR_BOOTS);

        simpleItem(ModItems.WILLOW_TEAR);
        simpleItem(ModItems.PALE_FIREFLIES_BOTTLE);

        tallPlantItem(ModBlocks.TALL_TENTACLE_GRASS.get().asItem());
        tallPlantItem(ModBlocks.ROOT_TENTACLES.get().asItem());
        blockTextureItem(ModBlocks.TENTACLE_GRASS.get().asItem());
        blockTextureItem(ModBlocks.WHISTLING_REEDS.get().asItem());

        fenceItem(ModBlocks.GHOST_WILLOW.fence(), ModBlocks.GHOST_WILLOW.planks());
        buttonItem(ModBlocks.GHOST_WILLOW.button(), ModBlocks.GHOST_WILLOW.planks());
        doorItem(ModBlocks.GHOST_WILLOW_DOOR);

        blockTextureItem(ModBlocks.GHOST_WILLOW_SAPLING.get().asItem());

        simpleItem(ModItems.MIRE_FIBER);
        simpleItem(ModItems.WHISTLING_REEDS_SEEDS);

        withExistingParent("ghost_willow_trapdoor", modLoc("block/ghost_willow_trapdoor_bottom"));
    }

    private void simpleItem(Holder<Item> item) {
        String name = item.getKey().location().getPath();
        withExistingParent(name, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + name));
    }

    private void blockTextureItem(Item item) {
        String name = BuiltInRegistries.ITEM.getKey(item).getPath();
        withExistingParent(name, mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + name));
    }

    private void tallPlantItem(Item item) {
        String name = BuiltInRegistries.ITEM.getKey(item).getPath();
        withExistingParent(name, mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + name + "_top"));
    }

    public void doorItem(Holder<Block> block) {
        String name = block.getKey().location().getPath();
        this.withExistingParent(name, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + name));
    }

    public void fenceItem(Holder<Block> block, Holder<Block> baseBlock) {
        String name = block.getKey().location().getPath();
        String baseName = baseBlock.getKey().location().getPath();
        this.withExistingParent(name, mcLoc("block/fence_inventory"))
                .texture("texture", modLoc("block/" + baseName));
    }

    public void buttonItem(Holder<Block> block, Holder<Block> baseBlock) {
        String name = block.getKey().location().getPath();
        String baseName = baseBlock.getKey().location().getPath();
        this.withExistingParent(name, mcLoc("block/button_inventory"))
                .texture("texture", modLoc("block/" + baseName));
    }
}