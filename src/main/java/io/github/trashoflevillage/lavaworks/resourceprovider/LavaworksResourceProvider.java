package io.github.trashoflevillage.lavaworks.resourceprovider;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;

import java.util.HashMap;

public abstract class LavaworksResourceProvider {
    private HashMap<String, String> defaultParameters = new HashMap<>();

    ///
    /// Returns the color that should display at a position.
    ///
    /// @param parameters A hashmap of player-defined parameters
    ///
    public abstract int getColorAtPosition(HashMap<String, String> parameters, BlockRenderView view, BlockPos pos);

    ///
    /// Returns the lava color that should display at a position.
    ///
    /// @param parameters A hashmap of player-defined parameters
    ///
    public int getLavaColorAtPosition(HashMap<String, String> parameters, BlockRenderView view, BlockPos pos) {
        return getColorAtPosition(parameters, view, pos);
    }

    ///
    /// Returns the magma block color that should display at a position.
    ///
    /// @param parameters A hashmap of player-defined parameters
    ///
    public int getMagmaColorAtPosition(HashMap<String, String> parameters, BlockRenderView view, BlockPos pos) {
        return getColorAtPosition(parameters, view, pos);
    }

    ///
    /// Returns the color that should display at a position.
    ///
    /// @param parameters A hashmap of player-defined parameters
    /// @param id The appropriate biome's identifier.
    ///
    public abstract int getColorAtPosition(HashMap<String, String> parameters, Identifier id, BlockPos pos);

    ///
    /// Returns the lava color that should display at a position.
    ///
    /// @param parameters A hashmap of player-defined parameters
    /// @param id The appropriate biome's identifier.
    ///
    public int getLavaColorAtPosition(HashMap<String, String> parameters, Identifier id, BlockPos pos) {
        return getColorAtPosition(parameters, id, pos);
    }

    ///
    /// Returns the magma block color that should display at a position.
    ///
    /// @param parameters A hashmap of player-defined parameters
    /// @param id The appropriate biome's identifier.
    ///
    public int getMagmaColorAtPosition(HashMap<String, String> parameters, Identifier id, BlockPos pos) {
        return getColorAtPosition(parameters, id, pos);
    }

    public Sprite getStillLavaSprite(HashMap<String, String> parameters, Identifier biome, BlockPos pos, Identifier atlas) {
        Sprite[] sprites = new Sprite[2];
        return (Sprite)MinecraftClient.getInstance().getSpriteAtlas(atlas).apply(
                Identifier.ofVanilla("block/cobblestone")
        );
    }

    public Sprite getFlowingLavaSprite(HashMap<String, String> parameters, Identifier biome, BlockPos pos, Identifier atlas) {
        Sprite[] sprites = new Sprite[2];
        return (Sprite)MinecraftClient.getInstance().getSpriteAtlas(atlas).apply(
                Identifier.ofVanilla("block/blackstone")
        );
    }

    public HashMap<String, String> parseParameters(String parameters) {
        if (parameters == null) parameters = "";
        HashMap<String, String> parsed = new HashMap<>();
        for (String i : defaultParameters.keySet()) {
            parsed.put(i, defaultParameters.get(i));
        }
        String[] splitParameters = parameters.split(";");
        for (String s : splitParameters) {
            String[] keyAndValue = s.split("=");
            if (keyAndValue.length != 2) continue;
            parsed.put(keyAndValue[0], keyAndValue[1]);
        }
        return parsed;
    }

    public LavaworksResourceProvider setDefaultParameterValue(String key, String value) {
        defaultParameters.put(key, value);
        return this;
    }
}
