package com.adhdmc.Peashooter101;

import com.adhdmc.Peashooter101.listeners.MendItem;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class MultiMend extends JavaPlugin {

    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new MendItem(), this);
        ConfigHandler.saveConfigs();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() { return plugin; }

}
