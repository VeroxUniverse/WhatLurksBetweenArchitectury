package net.veroxuniverse.what_lurks_between.neoforge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;
import net.veroxuniverse.what_lurks_between.registry.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {

        planksFromLogs(output, ModBlocks.GHOST_WILLOW_PLANKS.get(), ModBlocks.GHOST_WILLOW_LOG.get(), 4);
        planksFromLogs(output, ModBlocks.GHOST_WILLOW_PLANKS.get(), ModBlocks.STRIPPED_GHOST_WILLOW_LOG.get(), 4);
        planksFromLogs(output, ModBlocks.GHOST_WILLOW_PLANKS.get(), ModBlocks.GHOST_WILLOW_WOOD.get(), 4);
        planksFromLogs(output, ModBlocks.GHOST_WILLOW_PLANKS.get(), ModBlocks.STRIPPED_GHOST_WILLOW_WOOD.get(), 4);

        woodFromLogs(output, ModBlocks.GHOST_WILLOW_WOOD.get(), ModBlocks.GHOST_WILLOW_LOG.get());
        woodFromLogs(output, ModBlocks.STRIPPED_GHOST_WILLOW_WOOD.get(), ModBlocks.STRIPPED_GHOST_WILLOW_LOG.get());

        generateWoodSetRecipes(output, ModBlocks.GHOST_WILLOW);

        doorBuilder(ModBlocks.GHOST_WILLOW_DOOR.get(), Ingredient.of(ModBlocks.GHOST_WILLOW_PLANKS.get())).unlockedBy("has_planks", has(ModBlocks.GHOST_WILLOW_PLANKS.get())).save(output);
        trapdoorBuilder(ModBlocks.GHOST_WILLOW_TRAPDOOR.get(), Ingredient.of(ModBlocks.GHOST_WILLOW_PLANKS.get())).unlockedBy("has_planks", has(ModBlocks.GHOST_WILLOW_PLANKS.get())).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MIRE_FIBER.get(), 1)
                .pattern("###")
                .define('#', ModBlocks.WHISTLING_REEDS.get())
                .unlockedBy("has_whistling_reeds", has(ModBlocks.WHISTLING_REEDS.get()))
                .save(output);
    }

    private void generateWoodSetRecipes(RecipeOutput output, ModBlocks.WoodSet set) {
        ItemLike planks = set.planks().get();

        stairBuilder(set.stairs().get(), Ingredient.of(planks)).unlockedBy("has_planks", has(planks)).save(output);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, set.slab().get(), Ingredient.of(planks)).unlockedBy("has_planks", has(planks)).save(output);
        fenceBuilder(set.fence().get(), Ingredient.of(planks)).unlockedBy("has_planks", has(planks)).save(output);
        fenceGateBuilder(set.fenceGate().get(), Ingredient.of(planks)).unlockedBy("has_planks", has(planks)).save(output);
        buttonBuilder(set.button().get(), Ingredient.of(planks)).unlockedBy("has_planks", has(planks)).save(output);
        pressurePlateBuilder(RecipeCategory.REDSTONE, set.pressurePlate().get(), Ingredient.of(planks)).unlockedBy("has_planks", has(planks)).save(output);
    }

    protected static void woodFromLogs(RecipeOutput output, ItemLike wood, ItemLike log) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, wood, 3)
                .define('#', log)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_log", has(log))
                .save(output);
    }

    protected static void planksFromLogs(RecipeOutput output, ItemLike planks, ItemLike log, int count) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks, count)
                .requires(log)
                .unlockedBy("has_log", has(log))
                .save(output, getConversionRecipeName(planks, log));
    }
}