package net.veroxuniverse.what_lurks_between.registry;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.block.*;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(WhatLurksBetween.MOD_ID, Registries.BLOCK);

    public record WoodSet(
            RegistrySupplier<Block> planks,
            RegistrySupplier<Block> stairs,
            RegistrySupplier<Block> slab,
            RegistrySupplier<Block> fence,
            RegistrySupplier<Block> fenceGate,
            RegistrySupplier<Block> button,
            RegistrySupplier<Block> pressurePlate
    ) {}

    public static WoodSet registerWoodSet(String name, BlockBehaviour.Properties props) {
        RegistrySupplier<Block> planks = registerBlock(name + "_planks", () -> new Block(props));
        return new WoodSet(
                planks,
                registerBlock(name + "_stairs", () -> new StairBlock(planks.get().defaultBlockState(), props)),
                registerBlock(name + "_slab", () -> new SlabBlock(props)),
                registerBlock(name + "_fence", () -> new FenceBlock(props)),
                registerBlock(name + "_fence_gate", () -> new FenceGateBlock(WoodType.OAK, props)),
                registerBlock(name + "_button", () -> new ButtonBlock(BlockSetType.OAK, 30, props)),
                registerBlock(name + "_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, props))
        );
    }

    public static final RegistrySupplier<Block> MIRE_MOSS = registerBlock("mire_moss",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.GRASS)
                    .strength(0.4f)
                    .sound(SoundType.MOSS)));

    public static final RegistrySupplier<Block> MOSSY_MIRE_MUD = registerBlock("mossy_mire_mud",
            () -> new MossyMireMudBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.GRASS)
                    .strength(0.6f)
                    .sound(SoundType.MUD)
                    .speedFactor(0.5f)
                    .randomTicks()));

    public static final RegistrySupplier<Block> MIRE_MUD = registerBlock("mire_mud",
            () -> new MireMudBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIRT)
                    .strength(0.5f)
                    .sound(SoundType.MUD)
                    .speedFactor(0.5f)
                    .randomTicks()));

    public static final RegistrySupplier<Block> WET_MIRE_MUD = registerBlock("wet_mire_mud",
            () -> new WetMireMudBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIRT)
                    .strength(1.5f, 2.0f)
                    .sound(SoundType.MUD)
                    .noOcclusion()
                    .dynamicShape()
                    .pushReaction(PushReaction.DESTROY)
                    .noLootTable()
            ));

    public static final RegistrySupplier<Block> ROCKY_MIRE_MUD = registerBlock("rocky_mire_mud",
            () -> new RockyMireMudBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIRT)
                    .strength(0.5f)
                    .speedFactor(0.8f)
                    .sound(SoundType.MUD)));

    public static final RegistrySupplier<Block> MIRE_ROCK = registerBlock("mire_rock",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(2.0f, 6.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final RegistrySupplier<Block> TALL_TENTACLE_GRASS = registerBlock("tall_tentacle_grass",
            () -> new DoublePlantBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XYZ)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<Block> WHISTLING_REEDS = registerBlock("whistling_reeds",
            () -> new BushBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XYZ)
                    .pushReaction(PushReaction.DESTROY)) {
                @Override
                protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
                    BlockState maybeMud = level.getBlockState(pos.below());
                    return maybeMud.is(BlockTags.DIRT) || maybeMud.is(ModBlocks.MIRE_MUD.get());
                }
                @Override
                protected MapCodec<? extends BushBlock> codec() {
                    return RecordCodecBuilder.mapCodec(inst -> inst.stable(this));
                }
            });

    public static final RegistrySupplier<Block> MIREWOOD_LOG = registerBlock("mirewood_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
                    .mapColor(MapColor.WOOD)));

    public static final RegistrySupplier<Block> MIREWOOD_WOOD = registerBlock("mirewood_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                    .mapColor(MapColor.WOOD)));

    public static final RegistrySupplier<Block> STRIPPED_MIREWOOD_LOG = registerBlock("stripped_mirewood_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
                    .mapColor(MapColor.WOOD)));

    public static final RegistrySupplier<Block> STRIPPED_MIREWOOD_WOOD = registerBlock("stripped_mirewood_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
                    .mapColor(MapColor.WOOD)));

    public static final RegistrySupplier<Block> MIREWOOD_LEAVES = registerBlock("mirewood_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    .mapColor(MapColor.PLANT)
                    .noOcclusion()
                    .isSuffocating((state, level, pos) -> false)
                    .isViewBlocking((state, level, pos) -> false)
                    .randomTicks()
            ));

    public static final RegistrySupplier<Block> MIREWOOD_TRAPDOOR = registerBlock("mirewood_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
                    .noOcclusion()));

    public static final RegistrySupplier<Block> MIREWOOD_DOOR = registerBlock("mirewood_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
                    .noOcclusion()));

    public static final WoodSet MIREWOOD = registerWoodSet("mirewood",
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final RegistrySupplier<Block> MIREWOOD_PLANKS = MIREWOOD.planks();


    private static <T extends Block> RegistrySupplier<T> registerBlock(String name, Supplier<T> block) {
        return registerBlockWithCustomTab(name, block, ModTabs.BLOCKS_TAB);
    }

    private static <T extends Block> RegistrySupplier<T> registerBlockWithCustomTab(String name, Supplier<T> block, RegistrySupplier<net.minecraft.world.item.CreativeModeTab> tab) {
        RegistrySupplier<T> toReturn = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().arch$tab(tab)));
        return toReturn;
    }

    public static void register() {
        BLOCKS.register();
    }
}