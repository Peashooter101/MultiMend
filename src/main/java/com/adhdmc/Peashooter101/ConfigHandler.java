package com.adhdmc.Peashooter101;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {

    private static boolean multiplierEnabled, bonusMultiplierEnabled;
    private static double multiplier, bonusMultiplier;
    private static int bonus;
    private static ROUNDING multiplierRounding, bonusRounding;

    public enum ROUNDING {UP, DOWN, NEAREST}

    public static void reloadConfigs() {
        FileConfiguration config = MultiMend.getPlugin().getConfig();

        // Base Multiplier
        multiplierEnabled = config.getBoolean("multiplier-settings.enabled", false);
        multiplier = config.getDouble("multiplier-settings.multiplier", 1.0);
        try {
            multiplierRounding = ROUNDING.valueOf(config.getString("multiplier-settings.rounding", "nearest"));
        } catch (IllegalArgumentException e) {
            // TODO: Output Error to Console
            multiplierRounding = ROUNDING.NEAREST;
        }

        // Bonus Multiplier
        bonusMultiplierEnabled = config.getBoolean("bonus-settings.enabled", false);
        bonus = config.getInt("bonus-settings.amount", 0);
        bonusMultiplier = config.getDouble("bonus-settings.multiplier", 1.0);
        try {
            bonusRounding = ROUNDING.valueOf(config.getString("bonus-settings.rounding", "nearest"));
        } catch (IllegalArgumentException e) {
            // TODO: Output Error to Console
            multiplierRounding = ROUNDING.NEAREST;
        }
    }

    public static void saveConfigs() {
        MultiMend.getPlugin().saveConfig();
        reloadConfigs();
    }

    // Base Multiplier
    public static boolean isMultiplierEnabled() { return multiplierEnabled; }
    public static double getMultiplier() { return multiplier; }
    public static ROUNDING getMultiplierRounding() { return multiplierRounding; }

    // Bonus
    public static boolean isBonusMultiplierEnabled() { return bonusMultiplierEnabled; }
    public static int getBonus() { return bonus; }
    public static double getBonusMultiplier() { return bonusMultiplier; }
    public static ROUNDING getBonusRounding() { return bonusRounding; }

}
