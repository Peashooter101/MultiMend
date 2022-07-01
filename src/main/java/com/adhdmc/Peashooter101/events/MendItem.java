package com.adhdmc.Peashooter101.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemMendEvent;

public class MendItem implements Listener {

    @EventHandler
    public void onMendItem(PlayerItemMendEvent e) {
        int repair = e.getRepairAmount()*100;
        e.setRepairAmount(repair);
    }

}
