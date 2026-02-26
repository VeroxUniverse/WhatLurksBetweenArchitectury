package net.veroxuniverse.what_lurks_between.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class AbsoluteDarknessEffect extends MobEffect {
    public AbsoluteDarknessEffect() {
        super(MobEffectCategory.HARMFUL, 0x0A0A0A);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        return true;
    }
    
}
