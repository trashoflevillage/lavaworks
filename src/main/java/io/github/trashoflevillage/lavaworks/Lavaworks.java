package io.github.trashoflevillage.lavaworks;

import eu.midnightdust.lib.config.MidnightConfig;
import io.github.trashoflevillage.lavaworks.config.LavaWorksConfig;
import io.github.trashoflevillage.lavaworks.resourceprovider.LavaworksResourceProvider;
import io.github.trashoflevillage.lavaworks.resourceprovider.SplotchesResourceProvider;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class Lavaworks implements ModInitializer {
    protected static final HashMap<Identifier, LavaworksResourceProvider> REGISTERED_RESOURCE_PROVIDERS = new HashMap<>();
    protected static final HashMap<Identifier, BiomeBehavior> DEFAULT_BIOME_BEHAVIORS = new HashMap<>();
    public static final String MOD_ID = "lavaworks";

    @Override
    public void onInitialize() {
        MidnightConfig.init(Lavaworks.MOD_ID, LavaWorksConfig.class);
        LavaworksResourceProviders.registerResourceProviders();
    }

    public static int getLavaColor(@Nullable BlockRenderView view, @Nullable BlockPos pos) {
        if (LavaWorksConfig.lavaColoringEnabled && view != null && view.hasBiomes()) {
            RegistryEntry<Biome> biome = view.getBiomeFabric(pos);
            if (biome != null) {
                Identifier id = Identifier.of(biome.getIdAsString());
                int biomeIndex = LavaWorksConfig.biomeIds.indexOf(id);
                if (biomeIndex >= 0 && LavaWorksConfig.lavaParameters.size() >= biomeIndex + 1) {
                    LavaworksResourceProvider resourceProvider = REGISTERED_RESOURCE_PROVIDERS.get(LavaWorksConfig.lavaResourceProviders.get(biomeIndex));
                    if (resourceProvider == null) resourceProvider = REGISTERED_RESOURCE_PROVIDERS.get(Identifier.of(MOD_ID, "splotches"));
                    HashMap<String, String> params = resourceProvider.parseParameters(LavaWorksConfig.lavaParameters.get(biomeIndex));
                    return resourceProvider.getLavaColorAtPosition(params, view, pos);
                } else if (DEFAULT_BIOME_BEHAVIORS.containsKey(id)) {
                    LavaworksResourceProvider resourceProvider = REGISTERED_RESOURCE_PROVIDERS.get(getDefaultBiomeBehavior(id).resourceProvider);
                    if (resourceProvider == null) resourceProvider = REGISTERED_RESOURCE_PROVIDERS.get(Identifier.of(MOD_ID, "splotches"));
                    HashMap<String, String> params = resourceProvider.parseParameters(getDefaultBiomeBehavior(id).lavaParams);
                    return resourceProvider.getLavaColorAtPosition(params, view, pos);
                }
                return ColorHelper.getArgb(255, 255, 255);
            }
        }
        return -1;
    }

    public static int getLavaColor(Identifier id, BlockPos pos) {
        int biomeIndex = LavaWorksConfig.biomeIds.indexOf(id);
        if (biomeIndex >= 0 && LavaWorksConfig.lavaParameters.size() >= biomeIndex + 1) {
            LavaworksResourceProvider resourceProvider = REGISTERED_RESOURCE_PROVIDERS.get(LavaWorksConfig.lavaResourceProviders.get(biomeIndex));
            if (resourceProvider == null) resourceProvider = REGISTERED_RESOURCE_PROVIDERS.get(Identifier.of(MOD_ID, "splotches"));
            HashMap<String, String> params = resourceProvider.parseParameters(LavaWorksConfig.lavaParameters.get(biomeIndex));
            return resourceProvider.getLavaColorAtPosition(params, id, pos);
        } else if (DEFAULT_BIOME_BEHAVIORS.containsKey(id)) {
            LavaworksResourceProvider resourceProvider = REGISTERED_RESOURCE_PROVIDERS.get(getDefaultBiomeBehavior(id).resourceProvider);
            if (resourceProvider == null) resourceProvider = REGISTERED_RESOURCE_PROVIDERS.get(Identifier.of(MOD_ID, "splotches"));
            HashMap<String, String> params = resourceProvider.parseParameters(getDefaultBiomeBehavior(id).lavaParams);
            return resourceProvider.getLavaColorAtPosition(params, id, pos);
        }
        return ColorHelper.getArgb(255, 255, 255);
    }

    public static int getMagmaColor(@Nullable BlockRenderView view, @Nullable BlockPos pos) {
        if (LavaWorksConfig.magmaColoringEnabled && view != null) {
            RegistryEntry<Biome> biome = view.getBiomeFabric(pos);
            if (biome != null) {
                Identifier id = Identifier.of(biome.getIdAsString());
                int biomeIndex = LavaWorksConfig.biomeIds.indexOf(id);
                if (biomeIndex >= 0 && LavaWorksConfig.magmaParameters.size() >= biomeIndex + 1) {
                    LavaworksResourceProvider resourceProvider = REGISTERED_RESOURCE_PROVIDERS.get(LavaWorksConfig.magmaResourceProviders.get(biomeIndex));
                    if (resourceProvider == null) resourceProvider = REGISTERED_RESOURCE_PROVIDERS.get(Identifier.of(MOD_ID, "splotches"));
                    HashMap<String, String> params = resourceProvider.parseParameters(LavaWorksConfig.magmaParameters.get(biomeIndex));
                    return resourceProvider.getMagmaColorAtPosition(params, view, pos);
                } else if (DEFAULT_BIOME_BEHAVIORS.containsKey(id)) {
                    LavaworksResourceProvider resourceProvider = REGISTERED_RESOURCE_PROVIDERS.get(getDefaultBiomeBehavior(id).resourceProvider);
                    if (resourceProvider == null) resourceProvider = REGISTERED_RESOURCE_PROVIDERS.get(Identifier.of(MOD_ID, "splotches"));
                    HashMap<String, String> params = resourceProvider.parseParameters(getDefaultBiomeBehavior(id).magmaParams);
                    return resourceProvider.getMagmaColorAtPosition(params, view, pos);
                }
                return ColorHelper.getArgb(255, 255, 255);
            }
        }
        return -1;
    }

    public static LavaworksResourceProvider getResourceProvider(Identifier key) {
        if (REGISTERED_RESOURCE_PROVIDERS.containsKey(key)) {
            return REGISTERED_RESOURCE_PROVIDERS.get(key);
        }
        return getResourceProvider(Identifier.of(Lavaworks.MOD_ID, "splotches"));
    }

    public static void addDefaultBiomeBehavior(Identifier biome, Identifier r, String l, String m) {
        DEFAULT_BIOME_BEHAVIORS.put(biome, new BiomeBehavior(r, l, m));
    }

    public static BiomeBehavior getDefaultBiomeBehavior(Identifier biome) {
        if (DEFAULT_BIOME_BEHAVIORS.containsKey(biome)) return DEFAULT_BIOME_BEHAVIORS.get(biome);
        return new BiomeBehavior(Identifier.of("lavaworks:splotches"), "", "");
    }

    public static class BiomeBehavior {
        public final Identifier resourceProvider;
        public final String lavaParams;
        public final String magmaParams;
        private BiomeBehavior(Identifier r, String l, String m) {
            resourceProvider = r;
            lavaParams = l;
            magmaParams = m;
        }
    }
}
