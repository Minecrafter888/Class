package me.hephaestus.classtutorial.Listener;

import me.hephaestus.classtutorial.Classtutorial;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.UUID;

import static me.hephaestus.classtutorial.utils.ItemStacks.*;

public class MoveListener implements Listener {
    //Method to call forth Itemstacks items from ItemStacks
    public void items(){
        ItemStack pick = Pickaxe();
        ItemStack DPS = Sword();
        ItemStack TANK = Shield();
        ItemStack HEALERBOW = Bow();
        ItemStack HEALERARROW = Arrow();
        ItemStack DIVERTRIDENT = Trident();
    }
    // Method to load player configuration from file
    private YamlConfiguration loadPlayerConfiguration(UUID playerUUID) {
        File dataFolder = new File(plugin.getDataFolder(), "playerdata");
        File playerFile = new File(dataFolder, playerUUID + ".yml");

        if (playerFile.exists()) {
            return YamlConfiguration.loadConfiguration(playerFile);
        }

        return null;
    }
    // Plugin reference
    Plugin plugin = Classtutorial.getPlugin(Classtutorial.class);
    // Event handler for player movement
    @EventHandler
    public void moveListener(PlayerMoveEvent e){
        Player player = e.getPlayer();
        World world = player.getWorld();
        UUID playerUUID = player.getUniqueId();
        YamlConfiguration playerConfig = loadPlayerConfiguration(playerUUID);


        // Check if the player is in the "wild" world
        if(world.getName().equalsIgnoreCase("wild")){
            // Check if the player already has any class-related potion effects
            if(player.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)){
                return;
            }
            if(player.hasPotionEffect(PotionEffectType.FAST_DIGGING)){
                return;
            }
            if(player.hasPotionEffect(PotionEffectType.REGENERATION)){
                return;
            }
            if(player.hasPotionEffect(PotionEffectType.HEALTH_BOOST)){
                return;
            }
            else{
                // Apply class-specific effects based on the player's configured class
                if(playerConfig != null) {
                    String playerClass = playerConfig.getString("Class");
                    if(playerClass.equals("Dps")){
                        //Gives the proper items and effects and takes away those of the other classes
                        player.getInventory().addItem(Sword());
                        player.getInventory().removeItem(Shield());
                        player.getInventory().removeItem(Bow());
                        player.getInventory().removeItem(Arrow());
                        player.getInventory().removeItem(Pickaxe());
                        player.getInventory().removeItem(Trident());
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
                        player.removePotionEffect(PotionEffectType.FAST_DIGGING);
                        player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
                        player.removePotionEffect(PotionEffectType.REGENERATION);
                        player.removePotionEffect(PotionEffectType.WATER_BREATHING);

                    }
                    if(playerClass.equals("Healer")){
                        player.getInventory().addItem(Bow());
                        player.getInventory().addItem(Arrow());
                        player.getInventory().removeItem(Shield());
                        player.getInventory().removeItem(Sword());
                        player.getInventory().removeItem(Pickaxe());
                        player.getInventory().removeItem(Trident());
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1));
                        player.removePotionEffect(PotionEffectType.FAST_DIGGING);
                        player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
                        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                        player.removePotionEffect(PotionEffectType.WATER_BREATHING);
                    }
                    if(playerClass.equals("Miner")){
                        player.getInventory().addItem(Pickaxe());
                        player.getInventory().removeItem(Shield());
                        player.getInventory().removeItem(Bow());
                        player.getInventory().removeItem(Arrow());
                        player.getInventory().removeItem(Sword());
                        player.getInventory().removeItem(Trident());
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
                        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                        player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
                        player.removePotionEffect(PotionEffectType.REGENERATION);
                    }
                    if(playerClass.equals("Tank")){
                        player.getInventory().addItem(Shield());
                        player.getInventory().removeItem(Sword());
                        player.getInventory().removeItem(Bow());
                        player.getInventory().removeItem(Arrow());
                        player.getInventory().removeItem(Pickaxe());
                        player.getInventory().removeItem(Trident());
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 5));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5, 10));
                        player.removePotionEffect(PotionEffectType.FAST_DIGGING);
                        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                        player.removePotionEffect(PotionEffectType.REGENERATION);
                        player.removePotionEffect(PotionEffectType.WATER_BREATHING);
                    }
                    if(playerClass.equals("Diver")){
                        player.getInventory().addItem(Trident());
                        player.getInventory().removeItem(Sword());
                        player.getInventory().removeItem(Bow());
                        player.getInventory().removeItem(Arrow());
                        player.getInventory().removeItem(Pickaxe());
                        player.getInventory().removeItem(Shield());
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 1));
                        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                        player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
                        player.removePotionEffect(PotionEffectType.REGENERATION);
                        player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
                    }
                }
            }

        }else{
            // If the player is not in the "wild" world, exit the method
            return;
        }
    }
}
