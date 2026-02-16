package net.veroxuniverse.what_lurks_between.sanity.effects;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.veroxuniverse.what_lurks_between.sanity.ISanityEffect;

public class WhisperingEffect implements ISanityEffect {

    @Override
    public float getThreshold() {
        return 50f;
    }

    @Override
    public void apply(Player player, float currentSanity) {
        if (player.getRandom().nextFloat() < 0.005f) {
            float pitch = 0.5f + (currentSanity / 100f);

            player.level().playLocalSound(
                    player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENDERMAN_STARE,
                    SoundSource.AMBIENT,
                    0.3f,
                    pitch,
                    false
            );
        }
    }

    @Override
    public boolean isClientSide() {
        return true;
    }
}