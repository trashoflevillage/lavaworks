package io.github.trashoflevillage.lavaworks.mixin;

import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.impl.client.rendering.fluid.FluidRenderHandlerRegistryImpl;
import net.minecraft.client.texture.Sprite;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.ColorResolver;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(targets = "net/fabricmc/fabric/impl/client/rendering/fluid/FluidRenderHandlerRegistryImpl$LavaRenderHandler")
public class LavaRenderHandlerMixin implements FluidRenderHandler {
	@Shadow
	private Sprite[] sprites;

	@Shadow
	public Sprite[] getFluidSprites(@Nullable BlockRenderView view, @Nullable BlockPos pos, FluidState state) {
		return sprites;
	}

	@Override
	public int getFluidColor(@Nullable BlockRenderView view, @Nullable BlockPos pos, FluidState state) {
		if (pos == null) return -1;
		int[] colors = new int[4];
		int color = getLavaColor(view, pos);
		colors[0] = getLavaColor(view, pos.add(1, 0, 0));
		colors[1] = getLavaColor(view, pos.add(-1, 0, 0));
		colors[2] = getLavaColor(view, pos.add(0, 0, 1));
		colors[3] = getLavaColor(view, pos.add(0, 0, -1));

		for (int i : colors) {
			color = ColorHelper.Argb.averageArgb(color, i);
		}

		return color;
	}

	@Unique
	private static int getLavaColor(@Nullable BlockRenderView view, @Nullable BlockPos pos) {
		if (view != null) {
			RegistryEntry<Biome> biome = view.getBiomeFabric(pos);
			if (biome.matchesKey(BiomeKeys.SOUL_SAND_VALLEY)) return ColorHelper.Argb.getArgb(255, 25, 125, 255);
			if (biome.matchesKey(BiomeKeys.BASALT_DELTAS)) return ColorHelper.Argb.getArgb(255, 255, 175, 175);
		}
		return -1;
	}
}
