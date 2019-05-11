package io.github.c9lul12btw.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LoggerUtil {

    /**
     * Sends a certain player a message.
     * @param player, msg
     */
    public static void sendMessage(Player player, String msg) {
        player.sendMessage(msg.replaceAll("&", "§"));
    }

    /**
     * Sends a message to the console
     * @param msg
     */
    public static void logMessage(String msg) {
        Bukkit.getLogger().info(msg.replaceAll("&", "§"));
    }

    /**
     * Broadcasts a message to all players
     * @param msg
     */
    public static void broadcastMessage(String msg) {
        Bukkit.broadcastMessage(msg.replaceAll("&", "§"));
    }

}
