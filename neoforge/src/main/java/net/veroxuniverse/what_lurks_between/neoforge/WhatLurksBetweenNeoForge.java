package net.veroxuniverse.what_lurks_between.neoforge;

import mod.azure.azurelib.common.animation.cache.AzIdentityRegistry;
import mod.azure.azurelib.common.render.armor.AzArmorRendererRegistry;
import mod.azure.azurelib.common.render.item.AzItemRendererRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.item.armor.renderer.CultistRobeRenderer;
import net.veroxuniverse.what_lurks_between.item.armor.renderer.DivingGearRenderer;
import net.veroxuniverse.what_lurks_between.registry.ModAttributes;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;
import net.veroxuniverse.what_lurks_between.registry.ModItems;

@Mod(WhatLurksBetween.MOD_ID)
public final class WhatLurksBetweenNeoForge {
    public WhatLurksBetweenNeoForge(IEventBus modEventBus) {
        //ModAttributes.register();
        WhatLurksBetween.init();
        modEventBus.addListener(this::commonSetup);
        modEventBus.register(WhatLurksBetweenNeoForge.class);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            FireBlock fire = (FireBlock) Blocks.FIRE;

            registerWoodSetFlammable(fire, ModBlocks.MIREWOOD);

            fire.setFlammable(ModBlocks.MIREWOOD_LOG.get(), 5, 5);
            fire.setFlammable(ModBlocks.STRIPPED_MIREWOOD_LOG.get(), 5, 5);
            fire.setFlammable(ModBlocks.MIREWOOD_WOOD.get(), 5, 5);
            fire.setFlammable(ModBlocks.STRIPPED_MIREWOOD_WOOD.get(), 5, 5);
            fire.setFlammable(ModBlocks.MIREWOOD_PLANKS.get(), 5, 20);
            fire.setFlammable(ModBlocks.MIREWOOD_LEAVES.get(), 30, 60);
        });
    }

    private void registerWoodSetFlammable(FireBlock fire, ModBlocks.WoodSet set) {
        fire.setFlammable(set.planks().get(), 5, 20);
        fire.setFlammable(set.stairs().get(), 5, 20);
        fire.setFlammable(set.slab().get(), 5, 20);
        fire.setFlammable(set.fence().get(), 5, 20);
        fire.setFlammable(set.fenceGate().get(), 5, 20);
    }

    @SubscribeEvent
    public static void onModelRegister(ModelEvent.RegisterAdditional event) {
        AzIdentityRegistry.register(
                ModItems.CULTIST_ROBE_HELMET.get(),
                ModItems.CULTIST_ROBE_CHESTPLATE.get(),
                ModItems.CULTIST_ROBE_LEGGINGS.get(),
                ModItems.CULTIST_ROBE_BOOTS.get(),
                ModItems.DIVING_GEAR_HELMET.get(),
                ModItems.DIVING_GEAR_CHESTPLATE.get(),
                ModItems.DIVING_GEAR_LEGGINGS.get(),
                ModItems.DIVING_GEAR_BOOTS.get()
        );
    }
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        AzArmorRendererRegistry.register(
                CultistRobeRenderer::new,
                ModItems.CULTIST_ROBE_HELMET.get(),
                ModItems.CULTIST_ROBE_CHESTPLATE.get(),
                ModItems.CULTIST_ROBE_LEGGINGS.get(),
                ModItems.CULTIST_ROBE_BOOTS.get()
        );
        AzArmorRendererRegistry.register(
                DivingGearRenderer::new,
                ModItems.DIVING_GEAR_HELMET.get(),
                ModItems.DIVING_GEAR_CHESTPLATE.get(),
                ModItems.DIVING_GEAR_LEGGINGS.get(),
                ModItems.DIVING_GEAR_BOOTS.get()
        );
    }
}