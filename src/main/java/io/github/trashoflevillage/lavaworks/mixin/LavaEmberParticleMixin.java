package io.github.trashoflevillage.lavaworks.mixin;

import io.github.trashoflevillage.lavaworks.Lavaworks;
import io.github.trashoflevillage.lavaworks.config.LavaWorksConfig;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LavaEmberParticle.class)
public abstract class LavaEmberParticleMixin extends SpriteBillboardParticle {
    protected LavaEmberParticleMixin(ClientWorld clientWorld, double d, double e, double f) {
        super(clientWorld, d, e, f);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(ClientWorld clientWorld, double d, double e, double f, CallbackInfo ci) {
        if (LavaWorksConfig.particleColoringEnabled) {
            BlockPos pos = new BlockPos((int) d, (int) e, (int) f);
            int color = -1;
            if (clientWorld.getBiome(pos) != null)
                color = Lavaworks.getLavaColor(Identifier.of(clientWorld.getBiome(pos).getIdAsString()), pos);
            red = ColorHelper.getRed(color) / 255f;
            blue = ColorHelper.getBlue(color) / 255f;
            green = ColorHelper.getGreen(color) / 255f;
            alpha = ColorHelper.getAlpha(color) / 255f;
        }
    }
}
