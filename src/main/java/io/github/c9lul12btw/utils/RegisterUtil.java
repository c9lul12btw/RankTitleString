package io.github.c9lul12btw.utils;

import io.github.c9lul12btw.RankTitleString;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;

public class RegisterUtil {

    public static void registerListener(Listener listener) {
        RankTitleString.getPlugin(RankTitleString.class).getServer().getPluginManager().registerEvents(listener, RankTitleString.getPlugin(RankTitleString.class));
    }

    public static void registerPermission(Permission perm) {
        RankTitleString.getPlugin(RankTitleString.class).getServer().getPluginManager().addPermission(perm);
    }

    public static void registerCommand(String commandName, CommandExecutor executor) {
        if (RankTitleString.getPlugin(RankTitleString.class) == null) {
            LoggerUtil.logMessage("getcommand is null");
        } else if (RankTitleString.getPlugin(RankTitleString.class).getCommand(commandName) == null) {
            LoggerUtil.logMessage("getplugin is null");
        } else if (executor == null) {
            LoggerUtil.logMessage("executor is null");
        } else if (commandName == null) {
            LoggerUtil.logMessage("executor is null");
        }
        RankTitleString.getPlugin(RankTitleString.class).getCommand(commandName).setExecutor(executor);
    }

}
