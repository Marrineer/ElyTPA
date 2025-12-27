package com.marrineer.elyTPA.model;

import com.marrineer.elyTPA.model.runtime.PlayerDataRuntime;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {
    private final Map<UUID, PlayerData> settings = new HashMap<>();
    private final Map<UUID, PlayerDataRuntime> runtime = new HashMap<>();

    public PlayerData settings(UUID uuid) {
        return settings.computeIfAbsent(uuid, _u -> new PlayerData());
    }

    public PlayerDataRuntime runtime(UUID uuid) {
        return runtime.computeIfAbsent(uuid, _u -> new PlayerDataRuntime());
    }

    public void removeRuntime(UUID uuid) {
        runtime.remove(uuid);
    }
}
