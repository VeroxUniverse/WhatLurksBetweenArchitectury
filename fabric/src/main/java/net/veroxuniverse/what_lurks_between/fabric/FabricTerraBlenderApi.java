package net.veroxuniverse.what_lurks_between.fabric;

import net.veroxuniverse.what_lurks_between.worldgen.ModTerrablender;
import terrablender.api.TerraBlenderApi;

public class FabricTerraBlenderApi implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        ModTerrablender.register();
    }
}