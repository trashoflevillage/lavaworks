package io.github.trashoflevillage.lavaworks.mixin;

import io.github.trashoflevillage.lavaworks.Lavaworks;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.minecraft.client.texture.Sprite;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

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
		int color = Lavaworks.getLavaColor(view, pos);
		colors[0] = Lavaworks.getLavaColor(view, pos.add(1, 0, 0));
		colors[1] = Lavaworks.getLavaColor(view, pos.add(-1, 0, 0));
		colors[2] = Lavaworks.getLavaColor(view, pos.add(0, 0, 1));
		colors[3] = Lavaworks.getLavaColor(view, pos.add(0, 0, -1));

		for (int i : colors) {
			color = ColorHelper.average(color, i);
		}

		return color;
	}
}
