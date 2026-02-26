package net.veroxuniverse.what_lurks_between.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMudMixin extends Entity {

    public LivingEntityMudMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Inject(method = "baseTick", at = @At("TAIL"))
    private void onBaseTick(CallbackInfo ci) {
        BlockPos eyePos = BlockPos.containing(this.getX(), this.getEyeY(), this.getZ());

        if (this.level().getBlockState(eyePos).is(ModBlocks.WET_MIRE_MUD.get())) {

            int currentAir = this.getAirSupply();

            this.setAirSupply(currentAir - 10);

            if (this.getAirSupply() <= -20) {
                this.setAirSupply(-20);
                this.hurt(this.damageSources().drown(), 2.0F);
            }
        }
    }
}