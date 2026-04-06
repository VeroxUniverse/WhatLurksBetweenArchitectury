package net.veroxuniverse.what_lurks_between.neoforge.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
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

        Block mossyMud = ModBlocks.MOSSY_MIRE_MUD.get();
        ModelFile mossyMudModel = models().cubeBottomTop(
                "mossy_mire_mud",
                modLoc("block/mossy_mire_mud_side"),
                modLoc("block/mire_mud"),
                modLoc("block/mire_moss")
        );
        simpleBlock(mossyMud, mossyMudModel);
        simpleBlockItem(mossyMud, mossyMudModel);

        makeDoublePlant(ModBlocks.TALL_TENTACLE_GRASS.get(), "tall_tentacle_grass_bottom", "tall_tentacle_grass_top");

        String reedName = ModBlocks.WHISTLING_REEDS.getId().getPath();
        simpleBlock(ModBlocks.WHISTLING_REEDS.get(),
                models().cross(reedName, modLoc("block/"+ reedName)).renderType("cutout"));

        simpleBlockWithItem(ModBlocks.MIREWOOD_LEAVES.get(), cubeAll(ModBlocks.MIREWOOD_LEAVES.get()));

        logBlock((RotatedPillarBlock) ModBlocks.MIREWOOD_LOG.get());
        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_MIREWOOD_LOG.get());

        axisBlock((RotatedPillarBlock) ModBlocks.MIREWOOD_WOOD.get(), modLoc("block/mirewood_log"), modLoc("block/mirewood_log"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_MIREWOOD_WOOD.get(), modLoc("block/stripped_mirewood_log"), modLoc("block/stripped_mirewood_log"));

        simpleBlockItem(ModBlocks.MIREWOOD_LOG.get(), models().withExistingParent("mirewood_log", "minecraft:block/cube_column")
                .texture("side", modLoc("block/mirewood_log"))
                .texture("end", modLoc("block/mirewood_log_top")));

        simpleBlockItem(ModBlocks.STRIPPED_MIREWOOD_LOG.get(), models().withExistingParent("stripped_mirewood_log", "minecraft:block/cube_column")
                .texture("side", modLoc("block/stripped_mirewood_log"))
                .texture("end", modLoc("block/stripped_mirewood_log_top")));

        simpleBlockItem(ModBlocks.MIREWOOD_WOOD.get(), models().withExistingParent("mirewood_wood", "minecraft:block/cube_all")
                .texture("all", modLoc("block/mirewood_log")));

        simpleBlockItem(ModBlocks.STRIPPED_MIREWOOD_WOOD.get(), models().withExistingParent("stripped_mirewood_wood", "minecraft:block/cube_all")
                .texture("all", modLoc("block/stripped_mirewood_log")));

        doorBlockWithRenderType((DoorBlock) ModBlocks.MIREWOOD_DOOR.get(), modLoc("block/mirewood_door_bottom"), modLoc("block/mirewood_door_top"), "cutout");

        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.MIREWOOD_TRAPDOOR.get(), modLoc("block/mirewood_trapdoor"), true, "cutout");
        simpleBlockItem(ModBlocks.MIREWOOD_TRAPDOOR.get(), models().withExistingParent("mirewood_trapdoor", modLoc("block/mirewood_trapdoor_bottom")));

        blockSet(ModBlocks.MIREWOOD);
    }

    private void blockSet(ModBlocks.WoodSet set) {
        String baseName = set.planks().getId().getPath().replace("_planks", "");
        Block planks = set.planks().get();

        simpleBlockWithItem(planks, cubeAll(planks));

        stairsBlock((StairBlock) set.stairs().get(), blockTexture(planks));
        slabBlock((SlabBlock) set.slab().get(), blockTexture(planks), blockTexture(planks));
        fenceBlock((FenceBlock) set.fence().get(), blockTexture(planks));
        fenceGateBlock((FenceGateBlock) set.fenceGate().get(), blockTexture(planks));
        buttonBlock((ButtonBlock) set.button().get(), blockTexture(planks));
        pressurePlateBlock((PressurePlateBlock) set.pressurePlate().get(), blockTexture(planks));

        simpleBlockItem(set.stairs().get(), models().stairs(baseName + "_stairs", blockTexture(planks), blockTexture(planks), blockTexture(planks)));
        simpleBlockItem(set.slab().get(), models().slab(baseName + "_slab", blockTexture(planks), blockTexture(planks), blockTexture(planks)));
        simpleBlockItem(set.fenceGate().get(), models().fenceGate(baseName + "_fence_gate", blockTexture(planks)));
        simpleBlockItem(set.pressurePlate().get(), models().pressurePlate(baseName + "_pressure_plate", blockTexture(planks)));
    }

    private void makeDoublePlant(Block block, String bottomTexture, String topTexture) {
        ModelFile bottom = models().cross(bottomTexture, modLoc("block/" + bottomTexture)).renderType("cutout");
        ModelFile top = models().cross(topTexture, modLoc("block/" + topTexture)).renderType("cutout");

        getVariantBuilder(block).forAllStates(state -> {
            DoubleBlockHalf half = state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF);
            return ConfiguredModel.builder()
                    .modelFile(half == DoubleBlockHalf.LOWER ? bottom : top)
                    .build();
        });
    }
}