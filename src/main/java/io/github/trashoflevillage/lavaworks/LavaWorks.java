package io.github.trashoflevillage.lavaworks;

import eu.midnightdust.lib.config.MidnightConfig;
import io.github.trashoflevillage.lavaworks.config.LavaWorksConfig;
import net.fabricmc.api.ModInitializer;

public class LavaWorks implements ModInitializer {
    public static final String MOD_ID = "lavaworks";

    @Override
    public void onInitialize() {
        MidnightConfig.init(LavaWorks.MOD_ID, LavaWorksConfig.class);
    }
}
