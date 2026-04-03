package net.veroxuniverse.what_lurks_between.neoforge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;
import net.veroxuniverse.what_lurks_between.registry.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, WhatLurksBetween.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.WET_MIRE_MUD.get())
                .add(ModBlocks.ROCKY_MIRE_MUD.get())
                .add(ModBlocks.MIRE_MUD.get())
                .add(ModBlocks.MOSSY_MIRE_MUD.get());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.MIRE_MOSS.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MIRE_ROCK.get());

        this.tag(BlockTags.PREVENT_MOB_SPAWNING_INSIDE)
                .add(ModBlocks.WET_MIRE_MUD.get());

        tag(BlockTags.DIRT)
                .add(ModBlocks.WET_MIRE_MUD.get())
                .add(ModBlocks.ROCKY_MIRE_MUD.get())
                .add(ModBlocks.MIRE_MUD.get())
                .add(ModBlocks.MOSSY_MIRE_MUD.get());

        tag(BlockTags.SNOW_LAYER_CAN_SURVIVE_ON)
                .add(ModBlocks.MIRE_MUD.get())
                .add(ModBlocks.MOSSY_MIRE_MUD.get());

        tag(ModTags.MIRE_PLANTABLE_ON)
                .add(ModBlocks.ROCKY_MIRE_MUD.get())
                .add(ModBlocks.MIRE_MUD.get())
                .add(ModBlocks.MOSSY_MIRE_MUD.get())
                .add(ModBlocks.MIRE_MOSS.get());
    }
}