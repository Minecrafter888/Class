package me.hephaestus.classtutorial.Listener;

import me.hephaestus.classtutorial.Classtutorial;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Joinlistener implements Listener {
    Plugin plugin = Classtutorial.getPlugin(Classtutorial.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        // Retrieve the player's UUID and name
        UUID playerUUID = e.getPlayer().getUniqueId();
        String playerName = e.getPlayer().getName();

        // Define the data folder where player data will be stored
        File dataFolder = new File(plugin.getDataFolder(), "playerdata");

        // Check if the data folder exists, create it if it doesn't
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        // Create a file for the player's data
        File playerFile = new File(dataFolder, playerUUID + ".yml");

        // If the player's data file doesn't exist, create it and save default data
        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
                YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
                //default data
                playerConfig.set("Class", "human");
                playerConfig.set("Name", playerName);

                playerConfig.save(playerFile);

            } catch (IOException ev) {
                ev.printStackTrace();
            }
        }
    }
}
