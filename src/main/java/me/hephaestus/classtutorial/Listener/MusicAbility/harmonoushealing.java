package me.hephaestus.classtutorial.Listener.MusicAbility;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class harmonoushealing implements Listener {
    private JavaPlugin plugin;
    private Map<Player, Integer> shiftClickCounter;

    // Constructor to initialize the listener
    public harmonoushealing(JavaPlugin plugin) {
        this.plugin = plugin;
        this.shiftClickCounter = new HashMap<>();
    }

    // Event handler for player interactions
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Check if the player left-clicks with a named stick while sneaking
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (event.getClickedBlock() == null && event.getItem() != null && event.getItem().getType() == Material.STICK
                    && event.getItem().getItemMeta().hasDisplayName()
                    && event.getItem().getItemMeta().getDisplayName().equals("Rhythm Rod")) {

                // Check if the player is sneaking
                if (event.getPlayer().isSneaking()) {
                    incrementShiftClickCounter(player);

                    // Check if the player has performed the sequence twice
                    if (getShiftClickCounter(player) >= 2) {
                        resetShiftClickCounter(player);
                        activateHarmoniousHealing(player);
                    }
                } else {
                    resetShiftClickCounter(player);
                }
            } else {
                resetShiftClickCounter(player);
            }
        } else {
            resetShiftClickCounter(player);
        }
    }

    // Method to get the shift click counter for a player
    private int getShiftClickCounter(Player player) {
        return shiftClickCounter.getOrDefault(player, 0);
    }

    // Method to increment the shift click counter for a player
    private void incrementShiftClickCounter(Player player) {
        shiftClickCounter.put(player, getShiftClickCounter(player) + 1);
    }

    // Method to reset the shift click counter for a player
    private void resetShiftClickCounter(Player player) {
        shiftClickCounter.remove(player);
    }

    // Method to activate the Harmonious Healing ability
    private void activateHarmoniousHealing(Player player) {
        new BukkitRunnable() {
            private int duration = 10; // Duration in seconds
            private final double radius = 5.0; // Radius of healing aura

            @Override
            public void run() {
                // Check if the duration is still positive and the player is valid
                if (duration > 0 && player.isValid()) {
                    Location location = player.getLocation();

                    // Apply healing effect to the player
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 1));

                    // Apply healing effect to nearby players within the radius
                    for (Player nearbyPlayer : player.getWorld().getPlayers()) {
                        if (!nearbyPlayer.equals(player) && nearbyPlayer.getLocation().distance(location) <= radius) {
                            nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 1));
                        }
                    }

                    duration--;
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L); // Run the healing aura task every second
    }
}
