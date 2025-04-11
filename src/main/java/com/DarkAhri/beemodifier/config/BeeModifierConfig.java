package com.DarkAhri.beemodifier.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class BeeModifierConfig {

    private static final String CATEGORY_GENERAL = "general";

    public static int queenWorkCycleThrottleIncrement = 50;

    private Configuration config;

    public BeeModifierConfig(File configFile) {
        config = new Configuration(configFile);
        loadConfig();
    }

    private void loadConfig() {
        config.load();

        // 设置配置项
        queenWorkCycleThrottleIncrement = config.getInt(
            "queenWorkCycleThrottleIncrement",
            CATEGORY_GENERAL,
            50, // 默认值
            1, // 最小值
            550, // 最大值
            "The value by which queenWorkCycleThrottle will be incremented each time.");

        if (config.hasChanged()) {
            config.save();
        }
    }
}
