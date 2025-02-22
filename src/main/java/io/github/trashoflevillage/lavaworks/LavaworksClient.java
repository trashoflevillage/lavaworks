package io.github.trashoflevillage.lavaworks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.ColorHelper;

public class LavaworksClient implements ClientModInitializer {
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
                    int color = Lavaworks.getMagmaColor(view, pos);
                    colors[0] = Lavaworks.getMagmaColor(view, pos.add(1, 0, 0));
                    colors[1] = Lavaworks.getMagmaColor(view, pos.add(-1, 0, 0));
                    colors[2] = Lavaworks.getMagmaColor(view, pos.add(0, 0, 1));
                    colors[3] = Lavaworks.getMagmaColor(view, pos.add(0, 0, -1));

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
