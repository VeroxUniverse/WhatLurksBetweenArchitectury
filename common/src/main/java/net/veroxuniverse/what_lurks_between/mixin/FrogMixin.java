package net.veroxuniverse.what_lurks_between.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.FrogVariant;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.level.ServerLevelAccessor;
import net.veroxuniverse.what_lurks_between.WhatLurksBetween;
import net.veroxuniverse.what_lurks_between.registry.ModBiomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Frog.class)
public abstract class FrogMixin {

    @Inject(method = "finalizeSpawn", at = @At("RETURN"))
    private void whatLurksBetween$setMireVariant(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        Frog self = (Frog) (Object) this;

        if (serverLevelAccessor.getBiome(self.blockPosition()).is(ModBiomes.WHISPERING_MIRE)) {
            Holder<FrogVariant> mireVariant = BuiltInRegistries.FROG_VARIANT.getHolderOrThrow(
                    ResourceKey.create(Registries.FROG_VARIANT,
                            ResourceLocation.fromNamespaceAndPath(WhatLurksBetween.MOD_ID, "mire")));

            self.setVariant(mireVariant);
        }
    }
}