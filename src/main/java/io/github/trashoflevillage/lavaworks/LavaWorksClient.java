package io.github.trashoflevillage.lavaworks;

import eu.midnightdust.lib.config.MidnightConfig;
import io.github.trashoflevillage.lavaworks.config.LavaWorksConfig;
import io.github.trashoflevillage.lavaworks.mixin.LavaRenderHandlerMixin;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.biome.FoliageColors;

public class LavaWorksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerBlockColors();
        registerParticleColors();
    }

    private void registerBlockColors() {
        ColorProviderRegistry.BLOCK.register(
                (state, view, pos, tintIndex) ->
                {
                    if (pos == null) return -1;
                    int[] colors = new int[4];
                    int color = LavaWorks.getMagmaColor(view, pos);
                    colors[0] = LavaWorks.getMagmaColor(view, pos.add(1, 0, 0));
                    colors[1] = LavaWorks.getMagmaColor(view, pos.add(-1, 0, 0));
                    colors[2] = LavaWorks.getMagmaColor(view, pos.add(0, 0, 1));
                    colors[3] = LavaWorks.getMagmaColor(view, pos.add(0, 0, -1));

                    for (int i : colors) {
                        color = ColorHelper.average(color, i);
                    }

                    return color;
                },
                Blocks.MAGMA_BLOCK
        );
    }

    private void registerParticleColors() {

    }
}
