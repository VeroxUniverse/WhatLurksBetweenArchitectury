package net.veroxuniverse.what_lurks_between.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.block.UnlitLanternBlock;
import net.veroxuniverse.what_lurks_between.block.UnlitTorchBlock;
import net.veroxuniverse.what_lurks_between.block.UnlitWallTorchBlock;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(WhatLurksBetween.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> UNLIT_LANTERN = registerBlock("unlit_lantern",
            () -> new UnlitLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(state -> 0)));

    public static final RegistrySupplier<Block> UNLIT_TORCH = registerBlockWithoutItem("unlit_torch",
            () -> new UnlitTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)
                    .lightLevel(state -> 0), ParticleTypes.SMOKE));

    public static final RegistrySupplier<Block> UNLIT_WALL_TORCH = registerBlockWithoutItem("unlit_wall_torch",
            () -> new UnlitWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH)
                    .lightLevel(state -> 0), ParticleTypes.SMOKE));

    private static <T extends Block> RegistrySupplier<T> registerBlock(String name, Supplier<T> block) {
        RegistrySupplier<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistrySupplier<T> registerBlockWithoutItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> void registerBlockItem(String name, RegistrySupplier<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register() {
        BLOCKS.register();
    }
}