package com.adhdmc.MultiMend;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {

    private static boolean multiplierEnabled, bonusEnabled, bonusLevelBased, randomnessEnabled,
            randomnessLevelBased, bonusUsingMultiplier;
    private static double multiplier, bonusMultiplier;
    private static int xpCost, bonus, randomnessChance;
    private static Rounding multiplierRounding, bonusRounding;

    public enum Rounding {UP, DOWN, NEAREST}

    public static void reloadConfigs() {
        FileConfiguration config = MultiMend.getPlugin().getConfig();

        // XP per Operation
        xpCost = config.getInt("xp-cost", 2);

        // Base Multiplier
        multiplierEnabled = config.getBoolean("multiplier-settings.enabled", false);
        multiplier = config.getDouble("multiplier-settings.multiplier", 1.0);
        try {
            multiplierRounding = Rounding.valueOf(config.getString("multiplier-settings.rounding", "nearest"));
        } catch (IllegalArgumentException e) {
            // TODO: Output Error to Console
            multiplierRounding = Rounding.NEAREST;
        }

        // Bonus Multiplier
        bonusEnabled = config.getBoolean("bonus-settings.enabled", false);
        bonus = config.getInt("bonus-settings.amount", 0);
        bonusLevelBased = config.getBoolean("bonus-settings.level-based", false);
        bonusUsingMultiplier = config.getBoolean("bonus-settings.use-multiplier-for-levels", false);
        bonusMultiplier = config.getDouble("bonus-settings.multiplier-per-level", 1.0);
        try {
            bonusRounding = Rounding.valueOf(config.getString("bonus-settings.rounding", "nearest"));
        } catch (IllegalArgumentException e) {
            // TODO: Output Error to Console
            bonusRounding = Rounding.NEAREST;
        }

        // Randomness
        randomnessEnabled = config.getBoolean("randomness-settings.enabled", false);
        randomnessLevelBased = config.getBoolean("randomness-settings.level-based", false);
        randomnessChance = config.getInt("randomness-settings.chance");
    }

    public static void saveConfigs() {
        MultiMend.getPlugin().saveDefaultConfig();
        reloadConfigs();
    }

    // XP Per Operation Cost
    public static int getXpCost() { return xpCost; }

    // Base Multiplier
    public static boolean isMultiplierEnabled() { return multiplierEnabled; }
    public static double getMultiplier() { return multiplier; }
    public static Rounding getMultiplierRounding() { return multiplierRounding; }

    // Bonus
    public static boolean isBonusEnabled() { return bonusEnabled; }
    public static int getBonus() { return bonus; }
    public static boolean isBonusLevelBased() { return bonusLevelBased; }
    public static boolean isBonusUsingMultiplier() { return bonusUsingMultiplier; }
    public static double getBonusMultiplier() { return bonusMultiplier; }
    public static Rounding getBonusRounding() { return bonusRounding; }

    // Randomness
    public static boolean isRandomnessEnabled() { return randomnessEnabled; }
    public static boolean isRandomnessLevelBased() { return randomnessLevelBased; }
    public static int getRandomnessChance() { return randomnessChance; }

}
