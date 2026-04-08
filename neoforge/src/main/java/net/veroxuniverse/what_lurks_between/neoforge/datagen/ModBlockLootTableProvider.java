package net.veroxuniverse.what_lurks_between.neoforge.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;
import net.veroxuniverse.what_lurks_between.registry.ModItems;

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

        this.add(ModBlocks.WHISTLING_REEDS.get(), block ->
                this.createCropDrops(block,
                        ModItems.MIRE_FIBER.get(),
                        ModItems.WHISTLING_REEDS_SEEDS.get(),
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(CropBlock.AGE, 7))
                )
        );

        this.add(ModBlocks.MOSSY_MIRE_MUD.get(),
                block -> createSingleItemTableWithSilkTouch(block, ModBlocks.MIRE_MUD.get()));

        dropSelf(ModBlocks.TENTACLE_GRASS.get());
        this.add(ModBlocks.TALL_TENTACLE_GRASS.get(), (block) ->
                this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
        );
        this.add(ModBlocks.ROOT_TENTACLES.get(), (block) ->
                this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
        );
        this.add(ModBlocks.BLEEDING_GHOST_WILLOW_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.GHOST_WILLOW_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(HAS_SHEARS.or(this.hasSilkTouch()).invert())
                                .add(LootItem.lootTableItem(ModItems.WILLOW_TEAR.get()))
                        )
        );

        this.dropSelf(ModBlocks.GHOST_WILLOW_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_GHOST_WILLOW_LOG.get());
        this.dropSelf(ModBlocks.GHOST_WILLOW_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_GHOST_WILLOW_WOOD.get());

        this.dropSelf(ModBlocks.GHOST_WILLOW_TRAPDOOR.get());
        this.add(ModBlocks.GHOST_WILLOW_DOOR.get(), this::createDoorTable);

        this.dropSelf(ModBlocks.GHOST_WILLOW_ROOTS.get());
        this.dropSelf(ModBlocks.GHOST_WILLOW_SAPLING.get());

        this.add(ModBlocks.GHOST_WILLOW_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.GHOST_WILLOW_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        addWoodSetLoot(ModBlocks.GHOST_WILLOW);
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