package net.veroxuniverse.what_lurks_between.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.block.*;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(WhatLurksBetween.MOD_ID, Registries.BLOCK);

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
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIRT)
                    .strength(0.5f)
                    .sound(SoundType.MUD)));

    public static final RegistrySupplier<Block> MIRE_ROCK = registerBlock("mire_rock",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(2.0f, 6.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final RegistrySupplier<Block> UNLIT_LANTERN = registerBlockWithCustomTab("unlit_lantern",
            () -> new UnlitLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(state -> 0)), ModTabs.ITEMS_TAB);

    public static final RegistrySupplier<Block> UNLIT_TORCH = registerBlockWithoutItem("unlit_torch",
            () -> new UnlitTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)
                    .lightLevel(state -> 0), ParticleTypes.SMOKE));

    public static final RegistrySupplier<Block> UNLIT_WALL_TORCH = registerBlockWithoutItem("unlit_wall_torch",
            () -> new UnlitWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH)
                    .lightLevel(state -> 0), ParticleTypes.SMOKE));

    private static <T extends Block> RegistrySupplier<T> registerBlock(String name, Supplier<T> block) {
        return registerBlockWithCustomTab(name, block, ModTabs.BLOCKS_TAB);
    }

    private static <T extends Block> RegistrySupplier<T> registerBlockWithCustomTab(String name, Supplier<T> block, RegistrySupplier<net.minecraft.world.item.CreativeModeTab> tab) {
        RegistrySupplier<T> toReturn = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().arch$tab(tab)));
        return toReturn;
    }

    private static <T extends Block> RegistrySupplier<T> registerBlockWithoutItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static void register() {
        BLOCKS.register();
    }
}