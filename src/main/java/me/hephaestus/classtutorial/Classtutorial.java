package me.hephaestus.classtutorial;

import me.hephaestus.classtutorial.Commands.Class;
import me.hephaestus.classtutorial.Listener.ClassListener;
import me.hephaestus.classtutorial.Listener.Joinlistener;
import me.hephaestus.classtutorial.Listener.MoveListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Classtutorial extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("Class").setExecutor(new Class());
        getServer().getPluginManager().registerEvents(new Joinlistener(), this);
        getServer().getPluginManager().registerEvents(new MoveListener(), this);
        getServer().getPluginManager().registerEvents(new ClassListener(this), this);
    }
}
