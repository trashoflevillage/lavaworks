package io.github.trashoflevillage.lavaworks;

import eu.midnightdust.lib.config.MidnightConfig;
import io.github.trashoflevillage.lavaworks.config.LavaWorksConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class LavaWorks implements ModInitializer {
    public static final String MOD_ID = "lavaworks";
    public static final Random random = Random.create(100000);
    public static final SimplexNoiseSampler noise = new SimplexNoiseSampler(random);

    @Override
    public void onInitialize() {
        MidnightConfig.init(LavaWorks.MOD_ID, LavaWorksConfig.class);
    }

    public static int getLavaColor(@Nullable BlockRenderView view, @Nullable BlockPos pos) {
        if (LavaWorksConfig.lavaColoringEnabled && view != null && view.hasBiomes()) {
            RegistryEntry<Biome> biome = view.getBiomeFabric(pos);
            if (biome != null) {
                Identifier id = Identifier.of(biome.getIdAsString());
                int biomeIndex = LavaWorksConfig.biomeIds.indexOf(id);
                if (biomeIndex >= 0 && LavaWorksConfig.lavaColors.size() >= biomeIndex + 1) {
                    Color color = Color.decode(getHexFromPosition(LavaWorksConfig.lavaColors.get(biomeIndex), pos));
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

    public static int getLavaColor(Identifier id, BlockPos pos) {
        int biomeIndex = LavaWorksConfig.biomeIds.indexOf(id);
        if (biomeIndex >= 0 && LavaWorksConfig.lavaColors.size() >= biomeIndex + 1) {
            Color color = Color.decode(getHexFromPosition(LavaWorksConfig.lavaColors.get(biomeIndex), pos));
            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();
            return ColorHelper.Argb.getArgb(r, g, b);
        }
        return ColorHelper.Argb.getArgb(255, 255, 255);
    }

    public static int getMagmaColor(@Nullable BlockRenderView view, @Nullable BlockPos pos) {
        if (LavaWorksConfig.magmaColoringEnabled && view != null) {
            RegistryEntry<Biome> biome = view.getBiomeFabric(pos);
            if (biome != null) {
                Identifier id = Identifier.of(biome.getIdAsString());
                int biomeIndex = LavaWorksConfig.biomeIds.indexOf(id);
                if (biomeIndex >= 0 && LavaWorksConfig.magmaColors.size() >= biomeIndex + 1) {
                    Color color = Color.decode(getHexFromPosition(LavaWorksConfig.magmaColors.get(biomeIndex), pos));
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

    public static String getHexFromPosition(String[] possibleColors, BlockPos pos) {
        double divisor = LavaWorksConfig.lavaSplotchSize;
        double val = noise.sample(
                (double) pos.getX() / divisor,
                (double) pos.getY() / divisor,
                (double) pos.getZ() / divisor
        );

        val = (val + 1.0) / 2.0;
        int index = (int) (val * (possibleColors.length));

        return possibleColors[index];
    }

    public static String getHexFromPosition(String possibleColors, BlockPos pos) {
        return getHexFromPosition(possibleColors.split(","), pos);
    }
}
