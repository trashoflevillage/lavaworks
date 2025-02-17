package io.github.trashoflevillage.lavaworks.config;

import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.util.Identifier;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;

public class LavaWorksConfig extends MidnightConfig {
    public static final String CATEGORY_NAME = "biome_colors";

    @Comment(category = CATEGORY_NAME, centered = true) public static Comment instructionsComment;

    @Entry(category = CATEGORY_NAME) public static List<Identifier> biomeIds = Lists.newArrayList(
            Identifier.ofVanilla("soul_sand_valley"),
            Identifier.ofVanilla("basalt_deltas"),
            Identifier.ofVanilla("crimson_forest"),
            Identifier.ofVanilla("warped_forest")
    );

    @Entry(category = CATEGORY_NAME, isColor = true) public static List<String> biomeColors = Lists.newArrayList(
            "#197dff", "#ffafaf", "#ff5555", "#7733ff"
    );
}
