package io.github.trashoflevillage.lavaworks.resourceprovider;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;

import java.awt.*;
import java.util.HashMap;

public class SplotchesResourceProvider extends LavaworksResourceProvider {
    public static final Random random = Random.create(100000);
    public static final SimplexNoiseSampler noise = new SimplexNoiseSampler(random);

    private static String getHexFromPosition(String[] possibleColors, BlockPos pos, int splotchSize) {
        if (splotchSize <= 0) splotchSize = 1;
        double val = noise.sample(
                (double) pos.getX() / splotchSize,
                (double) pos.getY() / splotchSize,
                (double) pos.getZ() / splotchSize
        );

        val = (val + 1.0) / 2.0;
        int index = (int) (val * (possibleColors.length));

        return possibleColors[index];
    }

    private static String getHexFromPosition(String possibleColors, BlockPos pos, int splotchSize) {
        return getHexFromPosition(possibleColors.split(","), pos, splotchSize);
    }

    @Override
    public int getColorAtPosition(HashMap<String, String> parameters, BlockRenderView view, BlockPos pos) {
        String[] possibleColors = parameters.get("colors").split(",");
        String splotchSizeStr = parameters.get("splotchSize");
        if (splotchSizeStr == null) splotchSizeStr = "1";
        Color color = Color.decode(getHexFromPosition(possibleColors, pos, Integer.parseInt(splotchSizeStr)));
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        return ColorHelper.getArgb(r, g, b);
    }

    @Override
    public int getColorAtPosition(HashMap<String, String> parameters, Identifier id, BlockPos pos) {
        String[] possibleColors = parameters.get("colors").split(",");
        String splotchSizeStr = parameters.get("splotchSize");
        if (splotchSizeStr == null) splotchSizeStr = "1";
        Color color = Color.decode(getHexFromPosition(possibleColors, pos, Integer.parseInt(splotchSizeStr)));
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        return ColorHelper.getArgb(r, g, b);
    }
}
