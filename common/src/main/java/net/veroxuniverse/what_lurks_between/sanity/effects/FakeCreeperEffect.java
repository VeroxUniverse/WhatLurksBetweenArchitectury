package net.veroxuniverse.what_lurks_between.sanity.effects;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.veroxuniverse.what_lurks_between.sanity.ISanityEffect;

public class FakeCreeperEffect implements ISanityEffect {
    @Override
    public float getThreshold() {
        return 30f;
    }

    @Override
    public void apply(Player player, float currentSanity) {

        if (player.getRandom().nextFloat() < 0.002f) {
            Vec3 backPos = player.position().add(player.getLookAngle().reverse().scale(1.5));

            player.level().playLocalSound(
                    backPos.x, backPos.y, backPos.z,
                    SoundEvents.CREEPER_PRIMED,
                    SoundSource.AMBIENT,
                    0.5f,
                    1.0f + player.getRandom().nextFloat() * 0.1f,
                    false
            );
        }

    }

    @Override
    public boolean isClientSide() {
        return true;
    }
}
