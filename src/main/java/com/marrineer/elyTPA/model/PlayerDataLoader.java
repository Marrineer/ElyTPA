package com.marrineer.elyTPA.model;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings("unused")
public class PlayerDataLoader {
    private final File file;
    private final YamlConfiguration yaml;

    public PlayerDataLoader(File dataFolder) {
        this.file = new File(dataFolder, "players.yml");

        if (!file.exists()) {
            try {
                //noinspection ResultOfMethodCallIgnored
                file.getParentFile().mkdirs();
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Cannot create players.yml", e);
            }
        }

        this.yaml = YamlConfiguration.loadConfiguration(file);
    }

    public void loadInfo(PlayerDataStore store) {
        store.clear();

        Set<String> keys = yaml.getKeys(false);
        if (keys.isEmpty()) return;

        for(String key : keys) {
            UUID uuid;

            try {
                uuid = UUID.fromString(key);
            } catch (IllegalArgumentException e) {
                continue;
            }

            ConfigurationSection sec = yaml.getConfigurationSection(key);
            if (sec == null) {
                store.raw().put(uuid, new PlayerData());
                continue;
            }

            boolean tpaEnabled = true;
            if (sec.isBoolean("tpa")) {
                tpaEnabled = sec.getBoolean("tpa");
            }

            store.raw().put(uuid, new PlayerData(tpaEnabled));
        }
    }

    public void saveFrom(PlayerDataStore store) {
        yaml.getKeys(false).forEach(k -> yaml.set(k, null));

        for (Map.Entry<UUID, PlayerData> entry : store.raw().entrySet()) {
            String key = entry.getKey().toString();
            PlayerData data = entry.getValue();

            yaml.set(key + ".tpa", data.isTpaEnabled());
        }

        try {
            yaml.save(file);
        } catch (IOException ignore) {}
    }
}
