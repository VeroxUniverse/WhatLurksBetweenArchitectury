package net.veroxuniverse.what_lurks_between.neoforge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    public ModBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.MIRE_MUD.get());
        dropSelf(ModBlocks.MIRE_MOSS.get());
        dropSelf(ModBlocks.MIRE_ROCK.get());
        dropSelf(ModBlocks.ROCKY_MIRE_MUD.get());
        dropSelf(ModBlocks.WHISTLING_REEDS.get());

        this.add(ModBlocks.MOSSY_MIRE_MUD.get(),
                block -> createSingleItemTableWithSilkTouch(block, ModBlocks.MIRE_MUD.get()));

        this.add(ModBlocks.TALL_TENTACLE_GRASS.get(), (block) ->
                this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
        );

        this.dropSelf(ModBlocks.MIREWOOD_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_MIREWOOD_LOG.get());
        this.dropSelf(ModBlocks.MIREWOOD_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_MIREWOOD_WOOD.get());

        this.dropSelf(ModBlocks.MIREWOOD_TRAPDOOR.get());
        this.add(ModBlocks.MIREWOOD_DOOR.get(), this::createDoorTable);

        this.add(ModBlocks.MIREWOOD_LEAVES.get(), block ->
                createLeavesDrops(block, Blocks.OAK_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));

        addWoodSetLoot(ModBlocks.MIREWOOD);
    }

    private void addWoodSetLoot(ModBlocks.WoodSet set) {
        this.dropSelf(set.planks().get());
        this.dropSelf(set.stairs().get());
        this.dropSelf(set.fence().get());
        this.dropSelf(set.fenceGate().get());
        this.dropSelf(set.button().get());
        this.dropSelf(set.pressurePlate().get());
        this.add(set.slab().get(), this::createSlabItemTable);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        List<Block> blocks = new ArrayList<>();
        ModBlocks.BLOCKS.forEach(supplier -> blocks.add(supplier.get()));
        return blocks;
    }
}