package net.veroxuniverse.what_lurks_between.neoforge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;
import net.veroxuniverse.what_lurks_between.registry.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    private static final TagKey<Block> IS_HORROR_BIOME = TagKey.create(Registries.BLOCK,
            ResourceLocation.fromNamespaceAndPath("veroxlib", "is_horror_biome"));

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

        tag(BlockTags.DIRT)
                .add(ModBlocks.WET_MIRE_MUD.get())
                .add(ModBlocks.ROCKY_MIRE_MUD.get())
                .add(ModBlocks.MIRE_MUD.get())
                .add(ModBlocks.MOSSY_MIRE_MUD.get());

        tag(BlockTags.SNOW_LAYER_CAN_SURVIVE_ON)
                .add(ModBlocks.MIRE_MUD.get())
                .add(ModBlocks.MOSSY_MIRE_MUD.get());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.ROOT_TENTACLES.get())
                .add(ModBlocks.MIRE_MOSS.get())
                .add(ModBlocks.GHOST_WILLOW_LEAVES.get())
                .add(ModBlocks.TENTACLE_GRASS.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.COBBLED_ABYSSAL_STONE.get())
                .add(ModBlocks.ABYSSAL_STONE.get())
                .add(ModBlocks.MIRE_ROCK.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.GHOST_WILLOW_ROOTS.get())
                .add(ModBlocks.GHOST_WILLOW_LOG.get())
                .add(ModBlocks.STRIPPED_GHOST_WILLOW_LOG.get())
                .add(ModBlocks.GHOST_WILLOW_WOOD.get())
                .add(ModBlocks.STRIPPED_GHOST_WILLOW_WOOD.get())
                .add(ModBlocks.GHOST_WILLOW_PLANKS.get())
                .add(ModBlocks.GHOST_WILLOW_DOOR.get())
                .add(ModBlocks.GHOST_WILLOW_TRAPDOOR.get())
                .add(ModBlocks.GHOST_WILLOW.planks().get())
                .add(ModBlocks.GHOST_WILLOW.stairs().get())
                .add(ModBlocks.GHOST_WILLOW.slab().get())
                .add(ModBlocks.GHOST_WILLOW.fence().get())
                .add(ModBlocks.GHOST_WILLOW.fenceGate().get())
                .add(ModBlocks.GHOST_WILLOW.button().get())
                .add(ModBlocks.GHOST_WILLOW.pressurePlate().get());

        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.GHOST_WILLOW_LOG.get())
                .add(ModBlocks.STRIPPED_GHOST_WILLOW_LOG.get())
                .add(ModBlocks.GHOST_WILLOW_WOOD.get())
                .add(ModBlocks.STRIPPED_GHOST_WILLOW_WOOD.get());

        tag(BlockTags.PLANKS)
                .add(ModBlocks.GHOST_WILLOW_PLANKS.get())
                .add(ModBlocks.GHOST_WILLOW.planks().get());

        tag(BlockTags.LEAVES)
                .add(ModBlocks.GHOST_WILLOW_LEAVES.get());

        tag(BlockTags.REPLACEABLE_BY_TREES)
                .add(ModBlocks.TENTACLE_GRASS.get());

        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.GHOST_WILLOW_DOOR.get());

        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.GHOST_WILLOW_TRAPDOOR.get());

        tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.GHOST_WILLOW.stairs().get());

        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.GHOST_WILLOW.slab().get());

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.GHOST_WILLOW.fence().get());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.GHOST_WILLOW.fenceGate().get());

        tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.GHOST_WILLOW.button().get());

        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.GHOST_WILLOW.pressurePlate().get());

        tag(BlockTags.SAPLINGS)
                .add(ModBlocks.GHOST_WILLOW_SAPLING.get());

        tag(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH)
                .add(ModBlocks.MIRE_MUD.get())
                .add(ModBlocks.WET_MIRE_MUD.get())
                .add(Blocks.AIR)
                .add(Blocks.WATER);

        tag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
                .add(ModBlocks.GHOST_WILLOW_ROOTS.get());

        this.tag(BlockTags.PREVENT_MOB_SPAWNING_INSIDE)
                .add(ModBlocks.WET_MIRE_MUD.get());

        tag(ModTags.MIRE_PLANTABLE_ON)
                .add(ModBlocks.ROOT_TENTACLES.get())
                .add(ModBlocks.ROCKY_MIRE_MUD.get())
                .add(ModBlocks.MIRE_MUD.get())
                .add(ModBlocks.MOSSY_MIRE_MUD.get())
                .add(ModBlocks.MIRE_MOSS.get());

        tag(IS_HORROR_BIOME)
                .add(ModBlocks.MIRE_MUD.get())
                .add(ModBlocks.MOSSY_MIRE_MUD.get())
                .add(ModBlocks.ROCKY_MIRE_MUD.get())
                .add(ModBlocks.WET_MIRE_MUD.get());

        tag(BlockTags.FROGS_SPAWNABLE_ON)
                .add(ModBlocks.MIRE_MOSS.get())
                .add(ModBlocks.MOSSY_MIRE_MUD.get())
                .add(ModBlocks.MIRE_MUD.get());

        tag(BlockTags.ANIMALS_SPAWNABLE_ON)
                .add(ModBlocks.MIRE_MOSS.get())
                .add(ModBlocks.MOSSY_MIRE_MUD.get())
                .add(ModBlocks.MIRE_MUD.get());
    }
}