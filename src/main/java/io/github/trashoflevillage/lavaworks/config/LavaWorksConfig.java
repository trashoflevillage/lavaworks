package io.github.trashoflevillage.lavaworks.config;

import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.util.Identifier;
import com.google.common.collect.Lists;

import java.util.List;

public class LavaWorksConfig extends MidnightConfig {
    public static final String CATEGORY_NAME = "biome_colors";

    @Comment(category = CATEGORY_NAME, centered = true) public static Comment instructionsComment;
    @Comment(category = CATEGORY_NAME, centered = true) public static Comment chunkReloadComment;

    @Entry(category = CATEGORY_NAME) public static List<Identifier> biomeIds = Lists.newArrayList(
            Identifier.ofVanilla("soul_sand_valley"),
            Identifier.ofVanilla("basalt_deltas"),
            Identifier.ofVanilla("crimson_forest"),
            Identifier.ofVanilla("warped_forest")
    );

    @Entry(category = CATEGORY_NAME) public static boolean lavaColoringEnabled = true;

    @Entry(category = CATEGORY_NAME) public static List<String> lavaColors = Lists.newArrayList(
            "#197dff", "#ffafaf", "#ff5555,#ee3333", "#aa33ff,#bb44ff"
    );

    @Entry(category = CATEGORY_NAME) public static int lavaSplotchSize = 10;

    @Entry(category = CATEGORY_NAME) public static boolean magmaColoringEnabled = true;

    @Entry(category = CATEGORY_NAME) public static List<String> magmaColors = Lists.newArrayList(
            "#4bafff", "#ff9999", "#ff7777", "#9966ff"
    );

    @Entry(category = CATEGORY_NAME) public static boolean particleColoringEnabled = true;
}
