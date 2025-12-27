package com.marrineer.elyTPA.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class SoundHandler {
    private static final String SERVER_VERSION;

    static {
        String packageName = org.bukkit.Bukkit.getServer().getClass().getPackage().getName();
        SERVER_VERSION = packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    public static void playRequest(Player player) {
        try {
            Sound sound = Sound.BLOCK_NOTE_BLOCK_PLING;
            player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
        } catch (IllegalArgumentException ignore) {}
    }

    public static void playTeleport(Player player) {
        try {
            Sound sound = Sound.ENTITY_ENDERMAN_TELEPORT;
            player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
        } catch (IllegalArgumentException ignore) {}
    }

    public String getServerVersion() {
        return SERVER_VERSION;
    }
}
