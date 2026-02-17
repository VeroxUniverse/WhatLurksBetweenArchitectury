package net.veroxuniverse.what_lurks_between.sanity.effects;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.veroxuniverse.what_lurks_between.sanity.ISanityEffect;

public class FootstepEffect implements ISanityEffect {

    @Override
    public float getThreshold() {
        return 60f;
    }

    @Override
    public void apply(Player player, float currentSanity) {
        if (player.getRandom().nextFloat() < 0.01f) {

            Vec3 backPos = player.position().add(player.getLookAngle().reverse().scale(2.5));

            player.level().playLocalSound(
                    backPos.x, backPos.y, backPos.z,
                    SoundEvents.WARDEN_STEP,
                    SoundSource.AMBIENT,
                    0.35f,
                    0.9f + player.getRandom().nextFloat() * 0.2f,
                    false
            );
        }
    }

    @Override
    public boolean isClientSide() {
        return true;
    }
}