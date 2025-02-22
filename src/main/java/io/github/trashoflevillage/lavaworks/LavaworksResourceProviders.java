package io.github.trashoflevillage.lavaworks;

import io.github.trashoflevillage.lavaworks.lavacolorproviders.LavaworksResourceProvider;
import io.github.trashoflevillage.lavaworks.lavacolorproviders.SplotchesResourceProvider;
import net.minecraft.util.Identifier;

public class LavaworksResourceProviders {
    public static LavaworksResourceProvider SPLOTCHES = register("splotches", new SplotchesResourceProvider())
            .setDefaultParameterValue("colors", "#FFFFFF")
            .setDefaultParameterValue("splotchSize", "10");

    private static LavaworksResourceProvider register(String name, LavaworksResourceProvider colorProvider) {
        return register(Identifier.of(Lavaworks.MOD_ID, name), colorProvider);
    }

    public static LavaworksResourceProvider register(Identifier id, LavaworksResourceProvider colorProvider) {
        Lavaworks.REGISTERED_RESOURCE_PROVIDERS.put(id, colorProvider);
        return colorProvider;
    }

    protected static void registerResourceProviders() {}
}
