package net.veroxuniverse.what_lurks_between.neoforge.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;

import java.util.function.Function;

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
        simpleBlock(ModBlocks.TENTACLE_GRASS.get(),
                models().cross("tentacle_grass", modLoc("block/tentacle_grass")).renderType("cutout"));
        makeDoublePlant(ModBlocks.ROOT_TENTACLES.get(), "root_tentacles_bottom", "root_tentacles_top");

        makeWhistlingReedsCrop((CropBlock) ModBlocks.WHISTLING_REEDS.get(), "whistling_reeds");

        simpleBlockWithItem(ModBlocks.GHOST_WILLOW_LEAVES.get(), cubeAll(ModBlocks.GHOST_WILLOW_LEAVES.get()));
        simpleBlockWithItem(ModBlocks.BLEEDING_GHOST_WILLOW_LEAVES.get(), cubeAll(ModBlocks.BLEEDING_GHOST_WILLOW_LEAVES.get()));


        logBlock((RotatedPillarBlock) ModBlocks.GHOST_WILLOW_LOG.get());
        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_GHOST_WILLOW_LOG.get());

        axisBlock((RotatedPillarBlock) ModBlocks.GHOST_WILLOW_WOOD.get(), modLoc("block/ghost_willow_log"), modLoc("block/ghost_willow_log"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_GHOST_WILLOW_WOOD.get(), modLoc("block/stripped_ghost_willow_log"), modLoc("block/stripped_ghost_willow_log"));

        simpleBlockItem(ModBlocks.GHOST_WILLOW_LOG.get(), models().withExistingParent("ghost_willow_log", "minecraft:block/cube_column")
                .texture("side", modLoc("block/ghost_willow_log"))
                .texture("end", modLoc("block/ghost_willow_log_top")));

        simpleBlockItem(ModBlocks.STRIPPED_GHOST_WILLOW_LOG.get(), models().withExistingParent("stripped_ghost_willow_log", "minecraft:block/cube_column")
                .texture("side", modLoc("block/stripped_ghost_willow_log"))
                .texture("end", modLoc("block/stripped_ghost_willow_log_top")));

        simpleBlockItem(ModBlocks.GHOST_WILLOW_WOOD.get(), models().withExistingParent("ghost_willow_wood", "minecraft:block/cube_all")
                .texture("all", modLoc("block/ghost_willow_log")));

        simpleBlockItem(ModBlocks.STRIPPED_GHOST_WILLOW_WOOD.get(), models().withExistingParent("stripped_ghost_willow_wood", "minecraft:block/cube_all")
                .texture("all", modLoc("block/stripped_ghost_willow_log")));

        doorBlockWithRenderType((DoorBlock) ModBlocks.GHOST_WILLOW_DOOR.get(), modLoc("block/ghost_willow_door_bottom"), modLoc("block/ghost_willow_door_top"), "cutout");

        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.GHOST_WILLOW_TRAPDOOR.get(), modLoc("block/ghost_willow_trapdoor"), true, "cutout");
        simpleBlockItem(ModBlocks.GHOST_WILLOW_TRAPDOOR.get(), models().withExistingParent("ghost_willow_trapdoor", modLoc("block/ghost_willow_trapdoor_bottom")));

        topBottomBlock(ModBlocks.GHOST_WILLOW_ROOTS.get(), "ghost_willow_roots_side", "ghost_willow_roots_top");

        simpleBlock(ModBlocks.GHOST_WILLOW_SAPLING.get(),
                models().cross(ModBlocks.GHOST_WILLOW_SAPLING.getId().getPath(),
                        modLoc("block/ghost_willow_sapling")).renderType("cutout"));

        blockSet(ModBlocks.GHOST_WILLOW);

        logBlock((RotatedPillarBlock) ModBlocks.CRACKED_BONE_BLOCK.get());
        simpleBlockItem(ModBlocks.CRACKED_BONE_BLOCK.get(),
                models().withExistingParent("cracked_bone_block", "minecraft:block/cube_column")
                        .texture("side", modLoc("block/cracked_bone_block"))
                        .texture("end", modLoc("block/cracked_bone_block_top")));

        logBlock((RotatedPillarBlock) ModBlocks.ABYSSAL_STONE.get());
        simpleBlockItem(ModBlocks.ABYSSAL_STONE.get(),
                models().withExistingParent("abyssal_stone", "minecraft:block/cube_column")
                        .texture("side", modLoc("block/abyssal_stone"))
                        .texture("end", modLoc("block/abyssal_stone_top")));

        simpleBlockWithItem(ModBlocks.COBBLED_ABYSSAL_STONE.get(), cubeAll(ModBlocks.COBBLED_ABYSSAL_STONE.get()));

    }

    private void topBottomBlock(Block block, String side, String topBottom) {
        ModelFile model = models().cubeBottomTop(
                block.asItem().toString(),
                modLoc("block/" + side),
                modLoc("block/" + topBottom),
                modLoc("block/" + topBottom)
        ).renderType("cutout");

        simpleBlock(block, model);
        simpleBlockItem(block, model);
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

    public void makeWhistlingReedsCrop(CropBlock block, String name) {
        getVariantBuilder(block).forAllStates(state -> {
            int age = state.getValue(BlockStateProperties.AGE_7);
            DoubleBlockHalf half = state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF);

            String modelName;
            String textureName;

            if (age < 6) {
                modelName = name + "_stage" + age;
                textureName = modelName;
            } else {
                boolean isTop = (half == DoubleBlockHalf.UPPER);
                modelName = name + "_stage" + age + (isTop ? "_top" : "_bottom");

                if (!isTop && age == 7) {
                    textureName = name + "_stage6_bottom";
                } else {
                    textureName = modelName;
                }
            }

            return ConfiguredModel.builder()
                    .modelFile(models().cross(modelName, modLoc("block/" + textureName)).renderType("cutout"))
                    .build();
        });
    }

}