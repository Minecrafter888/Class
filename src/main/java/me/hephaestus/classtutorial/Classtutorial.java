package me.hephaestus.classtutorial;

import me.hephaestus.classtutorial.Commands.Class;
import me.hephaestus.classtutorial.Listener.ClassListener;
import me.hephaestus.classtutorial.Listener.Joinlistener;
import me.hephaestus.classtutorial.Listener.MoveListener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Classtutorial extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Load the default configuration if it doesn't exist
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Set the command executor for the "/Class" command to the Class class
        getCommand("Class").setExecutor(new Class());

        // Register event listeners for player join, movement, and class-related events
        getServer().getPluginManager().registerEvents(new Joinlistener(), this);
        getServer().getPluginManager().registerEvents(new MoveListener(), this);
        getServer().getPluginManager().registerEvents(new ClassListener(this), this);

        // Get the PluginManager instance
        PluginManager pluginManager = getServer().getPluginManager();

        // Create a new permission object for the "Classtutorial.ClassChecker" permission
        Permission yourPermission = new Permission("Classtutorial.ClassChecker");

        // Register the permission with the plugin manager
        pluginManager.addPermission(yourPermission);
    }
}
