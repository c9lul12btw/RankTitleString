package io.github.c9lul12btw.managers;

import io.github.c9lul12btw.Rank;
import io.github.c9lul12btw.RankTitleString;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class PlaceholderManager extends PlaceholderExpansion {

    private RankTitleString plugin;

    public PlaceholderManager(RankTitleString plugin){
        this.plugin = plugin;
        RankManager.updateUsers();
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getIdentifier() {
        return "rt";
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier){

        // %rt_prefix%
        if(identifier.equals("prefix")){
            RankManager.updateUsers();
            if (Rank.StaffRank.valueOf(RankManager.data_ranks.get(player.getName().toLowerCase()).toUpperCase()) == Rank.StaffRank.NONE) {
                return "&7["
                        + Rank.TitleRank.valueOf(RankManager.data_titles.get(player.getName().toLowerCase()).toUpperCase()).getPrefix()
                        + " &7- "
                        + Rank.BuildRank.valueOf(RankManager.data_ranks.get(player.getName().toLowerCase()).toUpperCase()).getPrefix()
                        + "&7]";
            } else {
                return "&7["
                        + Rank.TitleRank.valueOf(RankManager.data_titles.get(player.getName().toLowerCase()).toUpperCase()).getPrefix()
                        + " &7- "
                        + Rank.StaffRank.valueOf(RankManager.data_ranks.get(player.getName().toLowerCase()).toUpperCase()).getPrefix()
                        + "&7]";
            }
        }

        // We return null if an invalid placeholder (f.e. %rt_placeholder3%)
        // was provided
        return null;
    }
}
