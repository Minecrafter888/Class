package me.hephaestus.classtutorial.Commands;

import me.hephaestus.classtutorial.Classtutorial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class Class implements CommandExecutor {
    Plugin plugin = Classtutorial.getPlugin(Classtutorial.class);
    private int cooldownSeconds;
    private final Map<Player, Long> cooldowns = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the command sender is a player
        if (sender instanceof Player) {
            Player p = (Player) sender;

            // Retrieve the cooldown duration from the plugin's configuration
            int cld = plugin.getConfig().getInt("CommandCooldown");
            cooldownSeconds = cld;

            // Check if the player is on cooldown
            if (isOnCooldown(p)) {
                long secondsLeft = getCooldownSeconds(p);
                p.sendMessage(ChatColor.RED + "You must wait " + secondsLeft + " seconds before using this command again.");
                return true;
            }

            // Create an inventory to display the available classes
            Inventory invent = Bukkit.createInventory(p, 9, ChatColor.GOLD + "Classes");

            // Create an item stack representing the Attack class
            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
            ItemMeta itemmeta = sword.getItemMeta();
            itemmeta.setDisplayName(ChatColor.RED + "Attack class");
            sword.setItemMeta(itemmeta);
            invent.setItem(0, sword);

            // Create an item stack representing the Healer class
            ItemStack healer = new ItemStack(Material.GOLDEN_APPLE, 1);
            ItemMeta item = healer.getItemMeta();
            item.setDisplayName(ChatColor.GOLD + "Healer Class");
            healer.setItemMeta(item);
            invent.setItem(1, healer);

            // Create an item stack representing the Tank class
            ItemStack tank = new ItemStack(Material.SHIELD, 1);
            ItemMeta Tankmeta = tank.getItemMeta();
            Tankmeta.setDisplayName(ChatColor.WHITE + "Tank Class");
            tank.setItemMeta(Tankmeta);
            invent.setItem(2, tank);

            // Create an item stack representing the Miner class
            ItemStack miner = new ItemStack(Material.DIAMOND_PICKAXE, 1);
            ItemMeta Minermeta = miner.getItemMeta();
            Minermeta.setDisplayName(ChatColor.BLUE + "Miner Class");
            miner.setItemMeta(Minermeta);
            invent.setItem(3, miner);

            // Create an item stack representing the Diver class
            ItemStack diver = new ItemStack(Material.TRIDENT, 1);
            ItemMeta Divermeta = diver.getItemMeta();
            Divermeta.setDisplayName(ChatColor.BLUE + "Diver Class");
            diver.setItemMeta(Divermeta);
            invent.setItem(4, diver);

            // Open the inventory for the player
            p.openInventory(invent);

            // Set the cooldown for the player
            setCooldown(p, cooldownSeconds);
        }

        return true;
    }

    // Set the cooldown for a player
    private void setCooldown(Player player, int clds) {
        int cooldownconfig = plugin.getConfig().getInt("CommandCooldown");
        long cooldownTime = System.currentTimeMillis() + (cooldownconfig * 1000);
        cooldowns.put(player, cooldownTime);
    }

    // Check if a player is currently on cooldown
    private boolean isOnCooldown(Player player) {
        return cooldowns.containsKey(player) && cooldowns.get(player) > System.currentTimeMillis();
    }

    // Get the remaining cooldown time in seconds for a player
    private long getCooldownSeconds(Player player) {
        if (cooldowns.containsKey(player)) {
            return (cooldowns.get(player) - System.currentTimeMillis()) / 1000L;
        }
        return 0;
    }
}
