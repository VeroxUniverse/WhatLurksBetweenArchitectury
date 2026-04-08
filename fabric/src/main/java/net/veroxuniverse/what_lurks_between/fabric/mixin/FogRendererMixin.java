package net.veroxuniverse.what_lurks_between.fabric.mixin;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.veroxuniverse.what_lurks_between.registry.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FogRenderer.class)
public abstract class FogRendererMixin {

    @Inject(method = "setupFog", at = @At("TAIL"))
    private static void whisperingMire$applyDenseFog(Camera camera, FogRenderer.FogMode fogMode, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        Entity entity = camera.getEntity();
        if (entity == null) return;

        Level level = entity.level();
        if (level.getBiome(entity.blockPosition()).is(ModTags.HAS_DENSE_FOG)) {

            long time = level.getDayTime() % 24000;
            boolean isNight = time > 13000 && time < 23000;

            float fogStart, fogEnd;

            if (isNight) {
                fogStart = -2.0F;
                fogEnd = 5.0F;

            } else {
                fogStart = 0.0F;
                fogEnd = 15.0F;
            }

            RenderSystem.setShaderFogStart(fogStart);
            RenderSystem.setShaderFogEnd(fogEnd);
            RenderSystem.setShaderFogShape(FogShape.CYLINDER);
        }
    }
}
