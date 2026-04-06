package net.veroxuniverse.what_lurks_between.neoforge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
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
                .add(ModBlocks.MIRE_MOSS.get())
                .add(ModBlocks.MIREWOOD_LEAVES.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MIRE_ROCK.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.MIREWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_MIREWOOD_LOG.get())
                .add(ModBlocks.MIREWOOD_WOOD.get())
                .add(ModBlocks.STRIPPED_MIREWOOD_WOOD.get())
                .add(ModBlocks.MIREWOOD_PLANKS.get())
                .add(ModBlocks.MIREWOOD_DOOR.get())
                .add(ModBlocks.MIREWOOD_TRAPDOOR.get())
                .add(ModBlocks.MIREWOOD.planks().get())
                .add(ModBlocks.MIREWOOD.stairs().get())
                .add(ModBlocks.MIREWOOD.slab().get())
                .add(ModBlocks.MIREWOOD.fence().get())
                .add(ModBlocks.MIREWOOD.fenceGate().get())
                .add(ModBlocks.MIREWOOD.button().get())
                .add(ModBlocks.MIREWOOD.pressurePlate().get());

        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.MIREWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_MIREWOOD_LOG.get())
                .add(ModBlocks.MIREWOOD_WOOD.get())
                .add(ModBlocks.STRIPPED_MIREWOOD_WOOD.get());

        tag(BlockTags.PLANKS)
                .add(ModBlocks.MIREWOOD_PLANKS.get())
                .add(ModBlocks.MIREWOOD.planks().get());

        tag(BlockTags.LEAVES)
                .add(ModBlocks.MIREWOOD_LEAVES.get());

        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.MIREWOOD_DOOR.get());

        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.MIREWOOD_TRAPDOOR.get());

        tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.MIREWOOD.stairs().get());

        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.MIREWOOD.slab().get());

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.MIREWOOD.fence().get());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.MIREWOOD.fenceGate().get());

        tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.MIREWOOD.button().get());

        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.MIREWOOD.pressurePlate().get());

        this.tag(BlockTags.PREVENT_MOB_SPAWNING_INSIDE)
                .add(ModBlocks.WET_MIRE_MUD.get());

        tag(ModTags.MIRE_PLANTABLE_ON)
                .add(ModBlocks.ROCKY_MIRE_MUD.get())
                .add(ModBlocks.MIRE_MUD.get())
                .add(ModBlocks.MOSSY_MIRE_MUD.get())
                .add(ModBlocks.MIRE_MOSS.get());

        tag(IS_HORROR_BIOME)
                .add(ModBlocks.MIRE_MUD.get())
                .add(ModBlocks.MOSSY_MIRE_MUD.get())
                .add(ModBlocks.ROCKY_MIRE_MUD.get())
                .add(ModBlocks.WET_MIRE_MUD.get());
    }
}