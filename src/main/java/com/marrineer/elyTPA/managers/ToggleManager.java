package com.marrineer.elyTPA.managers;

import com.marrineer.elyTPA.ElyTPA;

import java.util.UUID;
import java.util.concurrent.*;

public class ToggleManager {
    private final ElyTPA plugin;
    private final ConcurrentHashMap<UUID, Boolean> toggleStates;

    public ToggleManager(ElyTPA plugin) {
        this.plugin = plugin;
        this.toggleStates = new ConcurrentHashMap<>();
    }
}
