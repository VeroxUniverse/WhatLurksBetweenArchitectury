package net.veroxuniverse.what_lurks_between.neoforge.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, WhatLurksBetween.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        simpleBlockWithItem(ModBlocks.MIRE_MUD.get(), cubeAll(ModBlocks.MIRE_MUD.get()));
        simpleBlockWithItem(ModBlocks.MIRE_MOSS.get(), cubeAll(ModBlocks.MIRE_MOSS.get()));
        simpleBlockWithItem(ModBlocks.MIRE_ROCK.get(), cubeAll(ModBlocks.MIRE_ROCK.get()));
        simpleBlockWithItem(ModBlocks.ROCKY_MIRE_MUD.get(), cubeAll(ModBlocks.ROCKY_MIRE_MUD.get()));
        simpleBlockWithItem(ModBlocks.WET_MIRE_MUD.get(), cubeAll(ModBlocks.WET_MIRE_MUD.get()));

        Block block = ModBlocks.MOSSY_MIRE_MUD.get();
        ModelFile model = models().cubeBottomTop(
                "mossy_mire_mud",
                modLoc("block/mossy_mire_mud_side"),
                modLoc("block/mire_mud"),
                modLoc("block/mire_moss")
        );

        simpleBlock(block, model);
        simpleBlockItem(block, model);
    }
}
