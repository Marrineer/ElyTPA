package com.marrineer.elyTPA.data;

import com.marrineer.elyTPA.ElyTPA;
import org.bukkit.configuration.file.FileConfiguration;

@SuppressWarnings("unused")
public class ConfigManager {
    private static ConfigManager instance;
    private final ElyTPA plugin;
    private FileConfiguration config;

    private ConfigManager(ElyTPA plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public static ConfigManager getInstance(ElyTPA plugin) {
        if (instance == null) {
            instance = new ConfigManager(plugin);
        } else {
            instance.loadConfig();
        }
        return instance;
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        this.config = plugin.getConfig();
    }

    public int getRequestTimeout() {
        return config.getInt("settings.request-timeout", 60);
    }


    public int getCooldown() {
        return config.getInt("settings.cooldown", 30);
    }

    public boolean getSoundsEnabled() {
        return config.getBoolean("settings.enable-sounds", true);
    }

    public long getCooldownSeconds() {
        return config.getLong("settings.cooldown", 30);
    }

    public int getTeleportDelay() {
        return config.getInt("settings.teleport-delay", 3);
    }

    public String getPrefix() {
        return config.getString("global-prefix", "");
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
