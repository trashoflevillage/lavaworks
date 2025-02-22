package io.github.trashoflevillage.lavaworks.config;

import eu.midnightdust.lib.config.MidnightConfig;
import io.github.trashoflevillage.lavaworks.Lavaworks;
import net.minecraft.util.Identifier;
import com.google.common.collect.Lists;

import java.util.List;

public class LavaWorksConfig extends MidnightConfig {
    public static final String GENERAL = "general";
    public static final String LAVA = "lava";
    public static final String MAGMA = "magma";

    @Comment(category = GENERAL, centered = true) public static Comment instructionsComment;
    @Comment(category = GENERAL, centered = true) public static Comment chunkReloadComment;

    @Entry(category = GENERAL) public static List<Identifier> biomeIds = Lists.newArrayList(
            Identifier.ofVanilla("soul_sand_valley"),
            Identifier.ofVanilla("basalt_deltas"),
            Identifier.ofVanilla("crimson_forest"),
            Identifier.ofVanilla("warped_forest")
    );

    @Entry(category = LAVA) public static boolean lavaColoringEnabled = true;

    @Entry(category = LAVA) public static boolean particleColoringEnabled = true;

    @Entry(category = LAVA) public static List<Identifier> lavaResourceProviders = Lists.newArrayList(
            Identifier.of(Lavaworks.MOD_ID, "splotches"),
            Identifier.of(Lavaworks.MOD_ID, "splotches"),
            Identifier.of(Lavaworks.MOD_ID, "splotches"),
            Identifier.of(Lavaworks.MOD_ID, "splotches")
    );

    @Entry(category = LAVA) public static List<String> lavaParameters = Lists.newArrayList(
            "colors=#197dff;splotchSize=10", "colors=#ffafaf;splotchSize=10", "colors=#ff5555,#ee3333;splotchSize=10", "colors=#aa33ff,#bb44ff;splotchSize=10"
    );

    @Entry(category = MAGMA) public static boolean magmaColoringEnabled = true;

    @Entry(category = MAGMA) public static List<Identifier> magmaResourceProviders = Lists.newArrayList(
            Identifier.of(Lavaworks.MOD_ID, "splotches"),
            Identifier.of(Lavaworks.MOD_ID, "splotches"),
            Identifier.of(Lavaworks.MOD_ID, "splotches"),
            Identifier.of(Lavaworks.MOD_ID, "splotches")
    );

    @Entry(category = MAGMA) public static List<String> magmaParameters = Lists.newArrayList(
            "colors=#4bafff", "colors=#ff9999", "colors=#ff7777", "colors=#9966ff"
    );
}
