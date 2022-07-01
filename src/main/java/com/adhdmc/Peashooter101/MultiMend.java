package com.adhdmc.Peashooter101;

import com.adhdmc.Peashooter101.events.MendItem;
import org.bukkit.plugin.java.JavaPlugin;

public final class MultiMend extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MendItem(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
