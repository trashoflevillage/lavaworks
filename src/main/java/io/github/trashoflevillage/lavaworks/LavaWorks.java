package io.github.trashoflevillage.lavaworks;

import eu.midnightdust.lib.config.MidnightConfig;
import io.github.trashoflevillage.lavaworks.config.LavaWorksConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class LavaWorks implements ModInitializer {
    public static final String MOD_ID = "lavaworks";

    @Override
    public void onInitialize() {
        MidnightConfig.init(LavaWorks.MOD_ID, LavaWorksConfig.class);
    }

    public static int getLavaColor(@Nullable BlockRenderView view, @Nullable BlockPos pos) {
        if (LavaWorksConfig.lavaColoringEnabled && view != null) {
            RegistryEntry<Biome> biome = view.getBiomeFabric(pos);
            if (biome != null) {
                Identifier id = Identifier.of(biome.getIdAsString());
                int biomeIndex = LavaWorksConfig.biomeIds.indexOf(id);
                if (biomeIndex >= 0) {
                    Color color = Color.decode(LavaWorksConfig.lavaColors.get(biomeIndex));
                    int r = color.getRed();
                    int g = color.getGreen();
                    int b = color.getBlue();
                    return ColorHelper.Argb.getArgb(r, g, b);
                }
                return ColorHelper.Argb.getArgb(255, 255, 255);
            }
        }
        return -1;
    }

    public static int getMagmaColor(@Nullable BlockRenderView view, @Nullable BlockPos pos) {
        if (LavaWorksConfig.magmaColoringEnabled && view != null) {
            RegistryEntry<Biome> biome = view.getBiomeFabric(pos);
            if (biome != null) {
                Identifier id = Identifier.of(biome.getIdAsString());
                int biomeIndex = LavaWorksConfig.biomeIds.indexOf(id);
                if (biomeIndex >= 0) {
                    Color color = Color.decode(LavaWorksConfig.magmaColors.get(biomeIndex));
                    int r = color.getRed();
                    int g = color.getGreen();
                    int b = color.getBlue();
                    return ColorHelper.Argb.getArgb(r, g, b);
                }
                return ColorHelper.Argb.getArgb(255, 255, 255);
            }
        }
        return -1;
    }
}
