package com.adhdmc.MultiMend.listeners;

import com.adhdmc.MultiMend.ConfigHandler;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemMendEvent;
import com.adhdmc.MultiMend.ConfigHandler.ROUNDING;

public class MendItem implements Listener {

    @EventHandler
    public void onMendItem(PlayerItemMendEvent e) {
        if (e.isCancelled()) { return; }

        int mendLevel = e.getItem().getEnchantmentLevel(Enchantment.MENDING);

        // Randomness Check
        if (ConfigHandler.isRandomnessEnabled()) {
            int roll = (int) (Math.random()*100 + 1);
            if (ConfigHandler.isRandomnessLevelBased()) {
                roll = roll*mendLevel;
            }
            if (roll >= ConfigHandler.getRandomnessChance()) {
                e.setCancelled(true);
                return;
            }
        }

        int repair = e.getRepairAmount();

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
    }

    private int round(double input, ROUNDING rounding) {
        switch (rounding) {
            case UP -> input = Math.ceil(input);
            case DOWN -> input = Math.floor(input);
            default -> input = Math.round(input);
        }
        return (int) input;
    }

}
