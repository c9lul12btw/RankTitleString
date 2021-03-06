package io.github.c9lul12btw;

import io.github.c9lul12btw.cmds.TitleCommand;
import io.github.c9lul12btw.managers.PlaceholderManager;
import io.github.c9lul12btw.gui.GuiUtil;
import io.github.c9lul12btw.utils.RegisterUtil;
import io.github.c9lul12btw.utils.LoggerUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RankTitleString extends JavaPlugin { // Must extend JavaPlugin from org.bukkit

    @Override
    public void onEnable() {
        LoggerUtil.logMessage("Plugin has been enabled!");
        Bukkit.getPluginManager().registerEvents(new GuiUtil(), this);
        saveDefaultConfig();
        this.getCommand("title").setExecutor(new TitleCommand());
        //registerCmds();
        //registerListeners();
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new PlaceholderManager(this).register();
            LoggerUtil.logMessage("[RankTitleString] PAPI is found.");
        } else {
            LoggerUtil.logMessage("[RankTitleString] PAPI is not found.");
        }
    }

    private void registerListeners() {
        // Registers all class that listen for events, the class must implement Listener at org.bukkit
    }

    private void registerCmds() {
        // Registers all the commands and sets their executor class
        RegisterUtil.registerCommand("rt", new TitleCommand());
    }

    public static RankTitleString getInstance() {
        return JavaPlugin.getPlugin(RankTitleString.class);
    }

    /**
     * Void that gets called on plugin shutdown.
     */
    @Override
    public void onDisable() {
        LoggerUtil.logMessage("[RT] Plugin has been disabled!");
    }
}
