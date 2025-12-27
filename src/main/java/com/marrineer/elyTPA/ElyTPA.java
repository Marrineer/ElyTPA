package com.marrineer.elyTPA;

import com.marrineer.elyTPA.data.ConfigManager;
import com.marrineer.elyTPA.data.MessageManager;
import com.marrineer.elyTPA.model.PlayerDataLoader;
import com.marrineer.elyTPA.model.PlayerDataStore;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class ElyTPA extends JavaPlugin {
    private PlayerDataStore store;
    private PlayerDataLoader persistence;

    private static ElyTPA instance;
    private BukkitAudiences adventure;

    private MessageManager messageManager;
    private ConfigManager configManager;

    public static ElyTPA getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;

        store = new PlayerDataStore();
        persistence = new PlayerDataLoader(getDataFolder());
        persistence.loadInfo(store);
        adventure = BukkitAudiences.create(this);

        this.messageManager = new MessageManager(this);
        this.configManager = ConfigManager.getInstance(this);


    }

    @Override
    public void onDisable() {
        persistence.saveFrom(store);
    }

    public Audience audience(Player player) {
        return adventure.player(player);
    }
    public Audience audience(CommandSender sender) {
        return adventure.sender(sender);
    }
    public FileConfiguration getMessage() {
        return messageManager.getMessage();
    }
    public void reloadMessage() {
        messageManager.reloadMessage();
    }
    public String getPrefix() {
        return configManager.getPrefix();
    }
}
