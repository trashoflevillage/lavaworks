package io.github.trashoflevillage.lavaworks;

import eu.midnightdust.lib.config.MidnightConfig;
import io.github.trashoflevillage.lavaworks.config.LavaWorksConfig;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.world.biome.FoliageColors;

public class LavaWorksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockColors blockColors = new BlockColors();
        blockColors.registerColorProvider(
                (state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(),
                Blocks.MAGMA_BLOCK
        );
    }
}
