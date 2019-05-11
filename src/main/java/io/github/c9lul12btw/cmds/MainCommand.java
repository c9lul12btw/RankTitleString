package io.github.c9lul12btw.cmds;

import io.github.c9lul12btw.Rank;
import io.github.c9lul12btw.managers.RankManager;
import io.github.c9lul12btw.utils.LoggerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            LoggerUtil.logMessage("&cYou must be a player to run this command!");
            return true;
        }
        Player player = (Player) sender;
        switch(args[0].toLowerCase()) {
            case "title": {
                LoggerUtil.sendMessage(player, "Successfully changed title to " + Rank.getTitleRank(Rank.TitleRank.valueOf(args[1])));
                RankManager.data_titles.put(player.getName().toLowerCase(), args[1]);
                break;
            }
            case "rank": {
                LoggerUtil.sendMessage(player, "Successfully changed rank to " + Rank.getBuildRank(Rank.BuildRank.valueOf(args[1])));
                RankManager.data_ranks.put(player.getName().toLowerCase(), args[1]);
                break;
            }
            case "list": {
                LoggerUtil.sendMessage(player, "&eRANKS LIST: ");
                for (Rank.BuildRank rank : Rank.BuildRank.values()) {
                    LoggerUtil.sendMessage(player, "&7- " + rank.toString());
                }
                LoggerUtil.sendMessage(player, "&eTITLES LIST: ");
                for (Rank.TitleRank rank : Rank.TitleRank.values()) {
                    LoggerUtil.sendMessage(player, "&7- " + rank.toString());
                }
                break;
            }
        }
        return true;
    }
}
