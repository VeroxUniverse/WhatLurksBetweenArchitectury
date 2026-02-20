package net.veroxuniverse.what_lurks_between.sanity.effects;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.veroxuniverse.what_lurks_between.config.SanityConfig;
import net.veroxuniverse.what_lurks_between.registry.ModMobEffects;
import net.veroxuniverse.what_lurks_between.sanity.ISanityEffect;

public class AbsoluteDarknessSanityEffect implements ISanityEffect {
    @Override
    public float getThreshold() {
        return SanityConfig.INSTANCE.absoluteDarknessThreshold;
    }

    @Override
    public void apply(Player player, float currentSanity) {
        if (!player.hasEffect(ModMobEffects.ABSOLUTE_DARKNESS)) {
            player.addEffect(new MobEffectInstance(ModMobEffects.ABSOLUTE_DARKNESS, 220, 0, false, false, true));
        }
    }

    @Override
    public boolean isClientSide() {
        return false;
    }
}
