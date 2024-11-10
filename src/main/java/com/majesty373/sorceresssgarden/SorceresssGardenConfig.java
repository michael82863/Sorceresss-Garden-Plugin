package com.majesty373.sorceresssgarden;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup("sorceresssgarden")
public interface SorceresssGardenConfig extends Config {
    @ConfigItem(
            keyName = "showPanel",
            name = "Show stats",
            description = "Toggles the session stats panel",
            position = 1
    )
    default boolean showPanel() { return true; }

    @ConfigItem(
            keyName = "garden",
            name = "Garden Type",
            description = "The garden that will be used",
            position = 2
    )
    default SorceresssGarden garden() { return SorceresssGarden.WINTER; }

    @ConfigItem(
            keyName = "tilesGood",
            name = "Good Tile Color",
            description = "Color to highlight tiles when it is time to click them.",
            position = 3
    )
    default Color tilesGood() {
        return Color.GREEN;
    }

    @ConfigItem(
            keyName = "tilesBad",
            name = "Bad Tile Color",
            description = "Color to highlight tiles when it is NOT time to click them.",
            position = 4
    )
    default Color tilesBad() {
        return Color.RED;
    }

    @ConfigItem(
            keyName = "staminaWarning",
            name = "Low Stamina Threshold",
            description = "What stamina level to warn to use stamina potion (0 to disable).",
            position = 5
    )
    default int staminaThreshold() { return 25; }
}
