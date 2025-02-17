package io.github.trashoflevillage.lavaworks.mixin;

import io.github.trashoflevillage.lavaworks.LavaWorks;
import io.github.trashoflevillage.lavaworks.config.LavaWorksConfig;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.impl.client.rendering.fluid.FluidRenderHandlerRegistryImpl;
import net.minecraft.client.texture.Sprite;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
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

import java.awt.*;

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
		int color = LavaWorks.getLavaColor(view, pos);
		colors[0] = LavaWorks.getLavaColor(view, pos.add(1, 0, 0));
		colors[1] = LavaWorks.getLavaColor(view, pos.add(-1, 0, 0));
		colors[2] = LavaWorks.getLavaColor(view, pos.add(0, 0, 1));
		colors[3] = LavaWorks.getLavaColor(view, pos.add(0, 0, -1));

		for (int i : colors) {
			color = ColorHelper.Argb.averageArgb(color, i);
		}

		return color;
	}
}
