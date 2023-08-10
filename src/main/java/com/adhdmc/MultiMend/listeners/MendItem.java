package com.adhdmc.MultiMend.listeners;

import com.adhdmc.MultiMend.ConfigHandler;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemMendEvent;
import com.adhdmc.MultiMend.ConfigHandler.Rounding;

public class MendItem implements Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onMendItem(PlayerItemMendEvent e) {
        int mendLevel = e.getItem().getEnchantmentLevel(Enchantment.MENDING);

        // Randomness Check
        if (didRandomFail(mendLevel)) {
            e.setCancelled(true);
            return;
        }

        int xp = e.getExperienceOrb().getExperience();
        e.getPlayer().sendMessage("XP Start: " + xp);
        int repair = xp / ConfigHandler.getXpCost();
        e.getPlayer().sendMessage("Repair Start: " + repair);
        xp %= ConfigHandler.getXpCost();
        e.getPlayer().sendMessage("XP After: " + xp);

        // Base Multiplier
        if (ConfigHandler.isMultiplierEnabled()) {
            double multi = ConfigHandler.getMultiplier();
            repair = round(multi*repair, ConfigHandler.getMultiplierRounding());
        }

        // Bonuses
        if (ConfigHandler.isBonusEnabled()) {
            int bonus = ConfigHandler.getBonus();

            if (ConfigHandler.isBonusLevelBased()) {
                if (ConfigHandler.isBonusUsingMultiplier()) {
                    double multi = ConfigHandler.getBonusMultiplier();
                    bonus = round(bonus*mendLevel*multi, ConfigHandler.getBonusRounding());
                } else {
                    bonus = bonus*mendLevel;
                }
            }
            repair += bonus;
        }

        // Attempt to resolve StackOverflow error.
        if (repair == 0) {
            e.setCancelled(true);
            return;
        }
        e.setRepairAmount(repair);
        e.getPlayer().sendMessage("Repair Final: " + repair);
        e.getExperienceOrb().setExperience(xp);
        e.getPlayer().sendMessage("XP Final: " + xp);
    }

    /**
     * Determines if the mending event is cancelled due to randomness check.
     * @param level Mending Level
     * @return True if randomness check failed, otherwise False
     */
    private boolean didRandomFail(int level) {
        if (ConfigHandler.isRandomnessEnabled()) {
            int roll = (int) (Math.random()*100 + 1);
            if (ConfigHandler.isRandomnessLevelBased()) {
                roll = roll*level;
            }
            return roll >= ConfigHandler.getRandomnessChance();
        }
        return false;
    }

    private int round(double input, Rounding rounding) {
        switch (rounding) {
            case UP -> input = Math.ceil(input);
            case DOWN -> input = Math.floor(input);
            default -> input = Math.round(input);
        }
        return (int) input;
    }

}
