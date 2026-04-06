package net.veroxuniverse.what_lurks_between.neoforge;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;

@EventBusSubscriber(modid = WhatLurksBetween.MOD_ID)
public class WhatLurksBetweenNeoForgeEvents {

    @SubscribeEvent
    public static void onToolModification(BlockEvent.BlockToolModificationEvent event) {
        if (event.getItemAbility() == ItemAbilities.AXE_STRIP) {
            BlockState originalState = event.getState();

            if (originalState.is(ModBlocks.MIREWOOD_LOG.get())) {
                event.setFinalState(ModBlocks.STRIPPED_MIREWOOD_LOG.get().defaultBlockState()
                        .setValue(RotatedPillarBlock.AXIS, originalState.getValue(RotatedPillarBlock.AXIS)));
            }

            if (originalState.is(ModBlocks.MIREWOOD_WOOD.get())) {
                event.setFinalState(ModBlocks.STRIPPED_MIREWOOD_WOOD.get().defaultBlockState()
                        .setValue(RotatedPillarBlock.AXIS, originalState.getValue(RotatedPillarBlock.AXIS)));
            }
        }
    }
}