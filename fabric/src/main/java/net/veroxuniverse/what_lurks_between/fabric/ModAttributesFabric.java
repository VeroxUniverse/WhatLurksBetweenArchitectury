package net.veroxuniverse.what_lurks_between.fabric;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.veroxuniverse.what_lurks_between.registry.ModAttributes;

public class ModAttributesFabric {

    public static void init() {
        FabricDefaultAttributeRegistry.register(
                EntityType.PLAYER,
                Player.createAttributes()
                        .add(BuiltInRegistries.ATTRIBUTE.wrapAsHolder(ModAttributes.SANITY_RESISTANCE.get()), 0.0D)
        );
    }
}