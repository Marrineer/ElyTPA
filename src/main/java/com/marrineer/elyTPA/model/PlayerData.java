package com.marrineer.elyTPA.model;

public final class PlayerData {
    private boolean tpaEnabled;

    public PlayerData() {
        this(true);
    }

    public PlayerData(boolean tpaEnabled) {
        this.tpaEnabled = tpaEnabled;
    }

    public boolean isTpaEnabled() {
        return tpaEnabled;
    }

    public void setTpaEnabled(boolean tpaEnabled) {
        this.tpaEnabled = tpaEnabled;
    }
}
