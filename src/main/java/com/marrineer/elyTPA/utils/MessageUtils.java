package com.marrineer.elyTPA.utils;

import com.marrineer.elyTPA.ElyTPA;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.Prefix;

@SuppressWarnings({"unused"})
public class MessageUtils {
    private static final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder()
            .character('&')
            .hexColors()
            .build();
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    private static final ElyTPA plugin = ElyTPA.getInstance();
    private static final String PREFIX = plugin.getPrefix();

    // Parse placeholder
    public static String parsePAPI(Player parser, String text) {
        return PlaceholderAPI.setPlaceholders(parser, text);
    }

    // Deserialize
    public static Component deserialize(String text) {
        return MINI_MESSAGE.deserialize(text);
    }
    public static Component deserializeWithPrefix(String text) {
        return MINI_MESSAGE.deserialize(String.format("%s %s", PREFIX, text));
    }

    // Send only
    public static void sendOnly(Player player, Component text) {
        plugin.audience(player).sendMessage(text);
    }
    public static void sendOnly(CommandSender sender, Component text) {
        plugin.audience(sender).sendMessage(text);
    }

    // All in one SendMessage
    public static void sendToSender(CommandSender sender, String text) {
        if(sender instanceof Player player) {
            plugin.audience(player).sendMessage(
                    deserializeWithPrefix(parsePAPI(player, text))
            );
        } else {
            plugin.audience(sender).sendMessage(
                    deserializeWithPrefix(text)
            );
        }
    }
    public static void sendToPlayer(Player player, String text) {
        plugin.audience(player).sendMessage(
                deserializeWithPrefix(parsePAPI(player, text))
        );
    }

    // Send with parser
    public static void sendToSender(CommandSender sender, Player parser, String text) {
        if(sender instanceof Player player) {
            plugin.audience(player).sendMessage(
                    deserializeWithPrefix(parsePAPI(parser, text))
            );
        } else {
            plugin.audience(sender).sendMessage(
                    deserializeWithPrefix(parsePAPI(parser, text))
            );
        }
    }
    public static void sendToPlayer(Player player, Player parser, String text) {
        plugin.audience(player).sendMessage(
                deserializeWithPrefix(parsePAPI(parser, text))
        );
    }



}
