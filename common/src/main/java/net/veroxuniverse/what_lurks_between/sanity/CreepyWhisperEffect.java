package net.veroxuniverse.what_lurks_between.sanity;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.veroxuniverse.veroxlib.api.ISanityEffect;
import net.veroxuniverse.veroxlib.config.SanityConfig;
import net.veroxuniverse.what_lurks_between.config.ModConfig;
import net.veroxuniverse.what_lurks_between.registry.ModSounds;

public class CreepyWhisperEffect implements ISanityEffect {

    @Override
    public float getThreshold() {
        return ModConfig.INSTANCE.creepyWhisper.threshold;
    }

    @Override
    public void apply(Player player, float currentSanity) {
        if (player.getRandom().nextFloat() < ModConfig.INSTANCE.creepyWhisper.chance) {
            Vec3 pos = player.position().add(
                    (player.getRandom().nextDouble() - 0.5) * 8,
                    0,
                    (player.getRandom().nextDouble() - 0.5) * 8
            );

            player.level().playLocalSound(
                    pos.x, pos.y, pos.z,
                    ModSounds.CREEPY_WHISPER.get(),
                    SoundSource.AMBIENT,
                    0.5f,
                    0.8f + player.getRandom().nextFloat() * 0.3f,
                    false);
        }
    }

    @Override
    public boolean isEnabled(SanityConfig config) {
        return ModConfig.INSTANCE.creepyWhisper.enabled;
    }

    @Override
    public boolean isClientSide() {
        return true;
    }
}