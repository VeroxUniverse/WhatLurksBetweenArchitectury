package net.veroxuniverse.what_lurks_between.worldgen;

import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.veroxuniverse.what_lurks_between.registry.ModBiomes;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;

public class ModSurfaceRules {

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.RuleSource mireMud = SurfaceRules.state(ModBlocks.MIRE_MUD.get().defaultBlockState());
        SurfaceRules.RuleSource mireMoss = SurfaceRules.state(ModBlocks.MIRE_MOSS.get().defaultBlockState());
        SurfaceRules.RuleSource mossyMireMud = SurfaceRules.state(ModBlocks.MOSSY_MIRE_MUD.get().defaultBlockState());
        SurfaceRules.RuleSource mireRock = SurfaceRules.state(ModBlocks.MIRE_ROCK.get().defaultBlockState());

        SurfaceRules.ConditionSource isLand = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0);

        return SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.WHISPERING_MIRE),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(isLand,
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.4D), mireMoss),
                                                        mossyMireMud
                                                )
                                        ),
                                        mireMud
                                )
                        ),

                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, mireMud),

                        SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, mireRock)
                )
        );
    }
}