package net.veroxuniverse.what_lurks_between.sanity;

import net.minecraft.world.entity.player.Player;
import net.veroxuniverse.what_lurks_between.api.SanityAPI;
import net.veroxuniverse.what_lurks_between.client.ClientSanityData;
import net.veroxuniverse.what_lurks_between.sanity.effects.*;

import java.util.ArrayList;
import java.util.List;

public class SanityEffectManager {
    private static final List<ISanityEffect> EFFECTS = new ArrayList<>();

    static {
        EFFECTS.add(new WhisperingEffect());
        EFFECTS.add(new FootstepEffect());
        EFFECTS.add(new DoorCreakEffect());
        EFFECTS.add(new FakeCreeperEffect());
        EFFECTS.add(new HeartbeatEffect());
    }

    public static void tick(Player player) {
        float sanity;
        boolean cultist;

        if (player.level().isClientSide()) {
            sanity = ClientSanityData.getSanity();
            cultist = ClientSanityData.isCultist();
        } else {
            sanity = SanityAPI.getSanity(player);
            cultist = SanityAPI.isCultist(player);
        }

        if (cultist) return;

        boolean isClient = player.level().isClientSide();

        for (ISanityEffect effect : EFFECTS) {
            if (sanity <= effect.getThreshold() && effect.isClientSide() == isClient) {
                effect.apply(player, sanity);
            }
        }
    }
}