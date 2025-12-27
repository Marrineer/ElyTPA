package com.marrineer.elyTPA.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataStore {
    private final Map<UUID, PlayerData> dataMap = new HashMap<>();

    public PlayerData get(UUID uuid) {
        return dataMap.computeIfAbsent(uuid, _u -> new PlayerData());
    }

    public boolean isTpaEnabled(UUID uuid) {
        return get(uuid).isTpaEnabled();
    }

    public void setTpaEnabled(UUID uuid, boolean enabled) {
        get(uuid).setTpaEnabled(enabled);
    }

    public Map<UUID, PlayerData> raw() {
        return dataMap;
    }

    public void clear() {
        dataMap.clear();
    }
}
