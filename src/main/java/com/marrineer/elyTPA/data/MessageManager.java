package com.marrineer.elyTPA.data;

import com.marrineer.elyTPA.ElyTPA;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class MessageManager {
    private final ElyTPA plugin;
    private final File file;
    private FileConfiguration message;

    public MessageManager(ElyTPA plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "messages.yml");
        if(!file.exists()) {
            plugin.saveResource("messages.yml", false);
        }
        reloadMessage();
    }

    public void reloadMessage() {
        this.message = YamlConfiguration.loadConfiguration(file);
        FileConfiguration defaultMessage = YamlConfiguration.loadConfiguration(
                new InputStreamReader(Objects.requireNonNull(plugin.getResource("messages.yml")), StandardCharsets.UTF_8)
        );
        message.setDefaults(defaultMessage);
        message.options().copyDefaults(true);
    }

    public FileConfiguration getMessage() {
        return message;
    }
}
