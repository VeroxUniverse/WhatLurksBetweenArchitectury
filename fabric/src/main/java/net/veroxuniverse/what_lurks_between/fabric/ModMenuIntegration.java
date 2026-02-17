package net.veroxuniverse.what_lurks_between.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.screens.Screen;
import net.veroxuniverse.what_lurks_between.config.SanityConfig;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (ConfigScreenFactory) parent -> {
            Screen parentScreen = parent;
            return AutoConfig.getConfigScreen(SanityConfig.class, parentScreen).get();
        };
    }
}