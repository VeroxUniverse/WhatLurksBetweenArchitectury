package net.veroxuniverse.what_lurks_between.fabric.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.registry.ModAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(Player.class)
public class PlayerAttributeMixin {

    static {
        try {
            ModAttributes.ATTRIBUTES.register();
            System.out.println("[WhatLurksBetween] Attributes registered during Mixin bootstrap.");
        } catch (Exception e) {
            System.err.println("[WhatLurksBetween] Critical error during Mixin attribute registration: " + e.getMessage());
        }
    }

    @Inject(method = "createAttributes", at = @At("RETURN"))
    private static void wlb$injectCustomAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        AttributeSupplier.Builder builder = cir.getReturnValue();

        injectSafe(builder, "sanity_resistance");
        injectSafe(builder, "corruption");
    }

    private static void injectSafe(AttributeSupplier.Builder builder, String path) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(WhatLurksBetween.MOD_ID, path);
        Optional<Holder.Reference<Attribute>> holder = BuiltInRegistries.ATTRIBUTE.getHolder(id);

        if (holder.isPresent()) {
            builder.add(holder.get(), 0.0);
            System.out.println("[WhatLurksBetween] Successfully injected attribute: " + id);
        } else {
            System.err.println("[WhatLurksBetween] FATAL: Attribute not found in registry: " + id);
        }
    }
}