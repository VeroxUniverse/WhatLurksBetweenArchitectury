package net.veroxuniverse.what_lurks_between.util.sanity;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.veroxuniverse.veroxlib.VeroxLib;
import net.veroxuniverse.veroxlib.api.ISanityCondition;
import net.veroxuniverse.veroxlib.config.SanityConfig;

public class BiomeSanityProtectionCondition implements ISanityCondition {

    private static final TagKey<Biome> IS_HORROR_BIOME = TagKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(VeroxLib.MOD_ID, "is_horror_biome"));

    @Override
    public boolean shouldBlock(Player player, ConditionType type) {
        if (type != ConditionType.DECREASE) return false;

        Holder<Biome> biome = player.level().getBiome(player.blockPosition());
        if (!biome.is(IS_HORROR_BIOME)) return false;

        int light = player.level().getMaxLocalRawBrightness(player.blockPosition());
        if (light < SanityConfig.INSTANCE.darknessThreshold) return false;

        for (ItemStack stack : player.getArmorSlots()) {
            if (!stack.isEmpty() && stack.getItem() instanceof IBiomeSanityProtector) {
                return true;
            }
        }

        return false;
    }
}