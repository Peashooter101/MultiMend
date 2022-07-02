package com.adhdmc.Peashooter101.listeners;

import com.adhdmc.Peashooter101.ConfigHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemMendEvent;
import com.adhdmc.Peashooter101.ConfigHandler.ROUNDING;

public class MendItem implements Listener {

    @EventHandler
    public void onMendItem(PlayerItemMendEvent e) {
        if (e.isCancelled()) { return; }

        int repair = e.getRepairAmount();

        // Base Multiplier
        if (ConfigHandler.isMultiplierEnabled()) {
            double multi = ConfigHandler.getMultiplier();
            repair = round(multi*repair, ConfigHandler.getMultiplierRounding());
        }

        // Bonuses
        if (ConfigHandler.isBonusMultiplierEnabled()) {
            double multi = ConfigHandler.getBonusMultiplier();
            int bonus = ConfigHandler.getBonus();
            repair += round(multi*bonus, ConfigHandler.getBonusRounding());
        }

        e.setRepairAmount(repair);
    }

    private int round(double input, ROUNDING rounding) {
        switch (rounding) {
            case UP:
                input = Math.ceil(input);
                break;
            case DOWN:
                input = Math.floor(input);
            default:
                input = Math.round(input);
        }
        return (int) input;
    }

}
