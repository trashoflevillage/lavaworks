package io.github.trashoflevillage.lavaworks.mixin;

import io.github.trashoflevillage.lavaworks.LavaWorks;
import net.minecraft.client.particle.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Particle.class)
public abstract class ParticleMixin {
    @Shadow
    protected float red;
    @Shadow
    protected float green;
    @Shadow
    protected float blue;
    @Shadow
    protected float alpha;

    @Shadow public abstract ParticleTextureSheet getType();

    @Unique
    private boolean isTintedParticle() {
        return true;
    }

    @Inject(method = "<init>(Lnet/minecraft/client/world/ClientWorld;DDDDDD)V", at = @At("TAIL"))
    private void init(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, CallbackInfo ci) {
        if (isTintedParticle()) {
            BlockPos pos = new BlockPos((int) d, (int) e, (int) f);
            int color = -1;
            if (clientWorld.getBiome(pos) != null)
                color = LavaWorks.getLavaColor(Identifier.of(clientWorld.getBiome(pos).getIdAsString()));
            red = ColorHelper.Argb.getRed(color) / 255f;
            blue = ColorHelper.Argb.getBlue(color) / 255f;
            green = ColorHelper.Argb.getGreen(color) / 255f;
            alpha = ColorHelper.Argb.getAlpha(color) / 255f;
        }
    }
}
