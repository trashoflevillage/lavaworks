package io.github.trashoflevillage.lavaworks.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.trashoflevillage.lavaworks.Lavaworks;
import io.github.trashoflevillage.lavaworks.config.LavaWorksConfig;
import io.github.trashoflevillage.lavaworks.resourceprovider.LavaworksResourceProvider;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.FluidRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.HashMap;

@Debug(export = true)
@Mixin(FluidRenderer.class)
public abstract class FluidRendererMixin {
//    @ModifyExpressionValue(
//            method = "render",
//            at = @At(
//                    value = "FIELD",
//                    target = "Lnet/minecraft/client/render/block/FluidRenderer;lavaSprites:[Lnet/minecraft/client/texture/Sprite;"
//            )
//    )
//    public Sprite[] modifyLavaSprite(Sprite[] original, BlockRenderView world, BlockPos pos, VertexConsumer vertexConsumer, BlockState blockState, FluidState fluidState) {
//        Identifier biomeId = Identifier.of(world.getBiomeFabric(pos).getIdAsString());
//        int biomeIndex = LavaWorksConfig.biomeIds.indexOf(biomeId);
//        if (biomeIndex >= 0 && LavaWorksConfig.lavaParameters.size() >= biomeIndex + 1) {
//            LavaworksResourceProvider resourceProvider = Lavaworks.getResourceProvider(LavaWorksConfig.lavaResourceProviders.get(biomeIndex));
//            HashMap<String, String> params = resourceProvider.parseParameters(LavaWorksConfig.lavaParameters.get(biomeIndex));
//            Sprite[] sprites = new Sprite[2];
//            sprites[0] = resourceProvider.getStillLavaSprite(params, biomeId, pos, original[0].getAtlasId());
//            sprites[1] = resourceProvider.getFlowingLavaSprite(params, biomeId, pos, original[1].getAtlasId());
//            return sprites;
//        }
//        return original;
//    }


    @Definition(
            id = "sprites",
            local = @Local(type = Sprite[].class)
    )
    @Expression("sprites[0]")
    @ModifyExpressionValue(
            method = "render",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    public Sprite modifyStillLavaSprite(Sprite original, BlockRenderView world, BlockPos pos, VertexConsumer vertexConsumer, BlockState blockState, FluidState fluidState) {
        Identifier biomeId = Identifier.of(world.getBiomeFabric(pos).getIdAsString());
        int biomeIndex = LavaWorksConfig.biomeIds.indexOf(biomeId);
        if (biomeIndex >= 0 && LavaWorksConfig.lavaParameters.size() >= biomeIndex + 1) {
            LavaworksResourceProvider resourceProvider = Lavaworks.getResourceProvider(LavaWorksConfig.lavaResourceProviders.get(biomeIndex));
            HashMap<String, String> params = resourceProvider.parseParameters(LavaWorksConfig.lavaParameters.get(biomeIndex));
            return resourceProvider.getStillLavaSprite(params, biomeId, pos, original.getAtlasId());
        }
        return original;
    }

    @Definition(
            id = "sprites",
            local = @Local(type = Sprite[].class)
    )
    @Expression("sprites[1]")
    @ModifyExpressionValue(
            method = "render",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    public Sprite modifyFlowingLavaSprite(Sprite original, BlockRenderView world, BlockPos pos, VertexConsumer vertexConsumer, BlockState blockState, FluidState fluidState) {
        Identifier biomeId = Identifier.of(world.getBiomeFabric(pos).getIdAsString());
        int biomeIndex = LavaWorksConfig.biomeIds.indexOf(biomeId);
        if (biomeIndex >= 0 && LavaWorksConfig.lavaParameters.size() >= biomeIndex + 1) {
            LavaworksResourceProvider resourceProvider = Lavaworks.getResourceProvider(LavaWorksConfig.lavaResourceProviders.get(biomeIndex));
            HashMap<String, String> params = resourceProvider.parseParameters(LavaWorksConfig.lavaParameters.get(biomeIndex));
            return resourceProvider.getFlowingLavaSprite(params, biomeId, pos, original.getAtlasId());
        }
        return original;
    }
}
