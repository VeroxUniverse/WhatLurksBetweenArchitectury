package net.veroxuniverse.what_lurks_between.sanity;

import net.minecraft.world.entity.player.Player;
import net.veroxuniverse.what_lurks_between.api.SanityAPI;
import net.veroxuniverse.what_lurks_between.sanity.effects.WhisperingEffect;

import java.util.ArrayList;
import java.util.List;

public class SanityEffectManager {
    private static final List<ISanityEffect> EFFECTS = new ArrayList<>();

    static {
        EFFECTS.add(new WhisperingEffect());
    }

    public static void tick(Player player) {
        float sanity = SanityAPI.getSanity(player);
        boolean isClient = player.level().isClientSide();

        for (ISanityEffect effect : EFFECTS) {
            if (sanity <= effect.getThreshold() && effect.isClientSide() == isClient) {
                effect.apply(player, sanity);
            }
        }
    }
}
