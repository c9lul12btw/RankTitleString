package io.github.c9lul12btw.managers;

import io.github.c9lul12btw.RankTitleString;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Set;

public class RankManager {

    public static final HashMap<String, String> data_ranks = new HashMap<>();
    public static final HashMap<String, String> data_titles = new HashMap<>();

    public RankManager() {
        updateUsers();
    }

    static void updateUsers() {
        data_ranks.clear();
        data_titles.clear();
        Set<String> users = RankTitleString.getInstance().getConfig().getConfigurationSection("users").getKeys(false);
        FileConfiguration config = RankTitleString.getInstance().getConfig();
        for (String user : users) {
            data_ranks.put(user, config.getString("users." + user + ".current_rank"));
            data_titles.put(user, config.getString("users." + user + ".current_title"));
        }
    }
}
