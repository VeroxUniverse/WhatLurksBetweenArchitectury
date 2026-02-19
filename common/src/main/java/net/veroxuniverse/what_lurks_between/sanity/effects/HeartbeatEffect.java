package net.veroxuniverse.what_lurks_between.sanity.effects;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.veroxuniverse.what_lurks_between.sanity.ISanityEffect;

public class HeartbeatEffect implements ISanityEffect {
    @Override
    public float getThreshold() {
        return 20f;
    }

    @Override
    public void apply(Player player, float currentSanity) {

        if (player.tickCount % 30 == 0) {
            player.level().playLocalSound(
                    player.getX(), player.getY(), player.getZ(),
                    SoundEvents.WARDEN_HEARTBEAT,
                    SoundSource.AMBIENT,
                    0.4f,
                    0.8f,
                    false
            );
        }

    }

    @Override
    public boolean isClientSide() {
        return true;
    }
}
